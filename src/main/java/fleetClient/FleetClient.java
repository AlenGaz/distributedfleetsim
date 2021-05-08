package fleetClient;


import CoordinatorPackage.containers.MakeFootPrint;
import CoordinatorPackage.containers.tecAllenIntervalContainer;
import CoordinatorPackage.containers.tecStuff;

import aima.core.util.datastructure.Pair;
import com.google.protobuf.ByteString;
import com.vividsolutions.jts.geom.Polygon;
import io.grpc.Channel;

import java.io.*;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.coordinator.Coordinator;
import io.grpc.coordinator.CoordinatorServiceGrpc;
import io.grpc.fleetClients.FleetClientsServiceGrpc;
import io.grpc.fleetClients.Fleetclients;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import org.metacsp.multi.spatioTemporal.paths.PoseSteering;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import se.oru.coordination.coordination_oru.Dependency;
import se.oru.coordination.coordination_oru.RobotReport;

public class FleetClient {

    private static final Logger logger = Logger.getLogger(FleetClient.class.getName());
    private final FleetClientsServiceGrpc.FleetClientsServiceBlockingStub blockingStub;
    private final CoordinatorServiceGrpc.CoordinatorServiceBlockingStub coordinatorBlockingStub;

    // object using in cord
    public fleetClient.FleetClient FleetClient;

    public FleetClient(Channel channel) {
        //blockingStub = HelloWorldServiceGrpc.newBlockingStub(channel);
        blockingStub = FleetClientsServiceGrpc.newBlockingStub(channel);
        coordinatorBlockingStub = CoordinatorServiceGrpc.newBlockingStub(channel);
    }


    public int makeGreeting(String kan, int robotID, String type, String IP, int port, Pose startPose, Pose endPose,
                            String timeStamp, double maxAccel, double maxVel,
                            double trackingPeriodInMillis, MakeFootPrint makeFootPrint, PoseSteering[] poseSteerings, int numberOfRobots) {

        System.out.println("[FleetClient] makeGreeting kan: " + kan);

        ByteString poseSteeringBytes = null;
        try {
            poseSteeringBytes= ByteString.copyFrom(convertToBytes(poseSteerings));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Coordinator.robotsGreeting greetingBuild = Coordinator.robotsGreeting.newBuilder().setKan(kan).setRobotID(robotID).setType(type).setIP(IP).setPort(port)
                .setStartPose(Coordinator.robotPose.newBuilder().setX(startPose.getX()).setY(startPose.getY()).setTheta(startPose.getTheta()).build()).
                setEndPose(Coordinator.robotPose.newBuilder().setX(endPose.getX()).setY(endPose.getY()).setTheta(endPose.getTheta()).build()).
                        setTimeStamp(timeStamp).setMaxAccel(maxAccel).setMaxVel(maxVel).setTrackingPeriodInMillis(trackingPeriodInMillis)
                .setMakeFootPrint(Coordinator.MakeFootPrint.newBuilder().setCenterX(makeFootPrint.getCenterX())
                        .setCenterY(makeFootPrint.getCenterY()).setMinVerts(makeFootPrint.getMinVerts()).setMaxVerts(makeFootPrint.getMaxVerts())
                        .setMinRadius(makeFootPrint.getMinRadius()).setMaxRadius(makeFootPrint.getMaxRadius()).build()).setPoseSteering(poseSteeringBytes).build();

        Coordinator.robotgreetingResponse greetingresponse = null;

        try {
            greetingresponse = coordinatorBlockingStub.coordinatorgetGreeting(greetingBuild);
            // System.out.println("[FleetClient] making greeting");
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());

        }
        if(greetingresponse!=null){
            System.out.println("[FleetClient] makegreeting request: " + kan + " got response; " + greetingresponse.getNumofReplicas());
            return greetingresponse.getNumofReplicas();
        }
        else{
            return -1;
        }


    }


    public void makeGreeting2(Pair<String, Integer> pair, String type, String connection, String timeStamp) {

        Fleetclients.getRobotID getrobotid = Fleetclients.getRobotID.newBuilder().setKey(pair.getFirst()).setValue(pair.getSecond()).setType(type).setConnection(connection).setTimeStamp(timeStamp).build();
        Fleetclients.robotID robotid;

        try {
            System.out.println("[FleetClient] making greeting with: " + pair);
            robotid = blockingStub.grobotID(getrobotid);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());
            return;
        }
        System.out.println("Logging the response of the server ...");
    }

    public void makeRobotReport(String my_robotReport, int robotid, double x, double y, double theta, double velocity, int pathIndex, double distanceTraveled, int criticalPoint) {
        Coordinator.requestrobotreport getRR = Coordinator.requestrobotreport.newBuilder().setReq(my_robotReport).setRobotid(robotid).setX(x).setY(y).setTheta(theta).setVelocity(velocity).setPathIndex(pathIndex)
                .setDistanceTraveled(distanceTraveled).setCriticalPoint(criticalPoint).build();
       Coordinator.responserobotreport responserobotreport;


        try {
            responserobotreport = coordinatorBlockingStub.coordinatorrobotreport(getRR);

        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());
            return;
        }
    }

    public int makeCriticalPointreq(int robotID) {
        Coordinator.coordinatorGetCriticalPointRequestMessage req = Coordinator.coordinatorGetCriticalPointRequestMessage
                .newBuilder().setKan("requestcriticalpoint").setRobotID(robotID).build();
        Coordinator.coordinatorGetCriticalPointResponseMessage coordinatorRsp = null;

        try {
            coordinatorRsp = coordinatorBlockingStub.coordinatorcriticalpoint(req);
        }
        catch(StatusRuntimeException e){
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());

        }


        //System.out.println("[FleetClient] getCriticalPoint response was: " + coordinatorRsp.getCriticalPoint() + " for robotID: " + robotID);
        return coordinatorRsp.getCriticalPoint();

    }

    public void makeCurrentDependenciesMessage(HashSet<Dependency> dependencyHashSet){

        ByteString dependencyData = null;
        HashSet<Dependency> depdes = null;
        byte[] dependencyBytes = null;
        try {
            dependencyBytes = convertToBytes(dependencyHashSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[FleetClient] inside makeCurrentDpendencies"+ dependencyHashSet);



        try {
            dependencyData = ByteString.copyFrom(convertToBytes(dependencyHashSet));
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("[FleetClient] serializing currentDependencies: " + dependencyData);
        System.out.println("[FleetClient] size of bytes" + dependencyBytes.length);


        Coordinator.getCurrentDependencies req = Coordinator
                .getCurrentDependencies.newBuilder().setKan("currentDependencies").setDepBytes(dependencyData).build();
        Coordinator.noneResponse response;

        try {
            response = coordinatorBlockingStub.coordinatordependencies(req);
        }
        catch(StatusRuntimeException e){
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());
        }


        try {
            depdes = (HashSet<Dependency>) convertFromBytes(dependencyData.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("dependencyData deserialized" + depdes);

    }

    public void makeTecStuffRequest(int robotID){
        Coordinator.tecrequest request = Coordinator.tecrequest.newBuilder().setKan("RequestTecStuff").setRobotID(robotID).build();
        Coordinator.tecresponse response;

        System.out.println("[FleetClient] requesting tecStuff ");
        response = coordinatorBlockingStub.coordinatorgetTecStuff(request);
        try {
            tecStuff tesObject = (tecStuff) convertFromBytes(response.getTecStuff().toByteArray());
            System.out.println("tecStuff responded with: " + tesObject.getCurrentTimeInM());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public long makeCurrentTimeRequest(){
            Coordinator.timerequest request = Coordinator.timerequest.newBuilder().build();
            Coordinator.timeresponse response;


            return coordinatorBlockingStub.coordinatorgetCurrentTime(request).getCurrentTime();

    }

    public boolean sendAllenInterval(String kan, tecAllenIntervalContainer allen){
        ByteString allenIntervalByteString = null;
        try {
            allenIntervalByteString = ByteString.copyFrom(convertToBytes(allen));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Coordinator.allenInterval message = Coordinator.allenInterval.newBuilder().setKan(kan).setAllenIntervalBytes(allenIntervalByteString).build();
        coordinatorBlockingStub.coordinatorgetAllenInterval(message);

        return true;
    }

    public RobotReport makeRobotReportRequest(int robotID) {
        Coordinator.trackerRobotReportRequest request = Coordinator.trackerRobotReportRequest.newBuilder().setKan("TrackerRequestRobotReport").setRobotID(robotID).build();
        Coordinator.requestrobotreport response= null;

        //System.out.println("[FleetClient] requesting inside makeRobotReportRequest");
        response = coordinatorBlockingStub.coordinatorgetRobotReportRequest(request);

        //System.out.println("[FleetClient] response from makeRobotReportRequest: " + response);

        int _robotID = response.getRobotid();
        Pose _pose = new Pose(response.getX(), response.getY(), response.getTheta());
        RobotReport rR = new RobotReport(_robotID, _pose, response.getPathIndex(), response.getVelocity(), response.getDistanceTraveled(), response.getCriticalPoint());


        return rR;
    }

    /*
    public FleetVisualization makeVisualizerRequest(){

    not needed to make as a msg request if the visualzier will still be on the coordinator
    }
    */



    public static void main(String[] args) throws InterruptedException {

        String messageToSend;
        String target = "localhost:50050";


        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).maxInboundMessageSize(40*1000*1000).usePlaintext().build();

        try {
            //FleetClient client = new FleetClient(channel);
            //client.makeGreeting((messageToSend));

            //FleetClient client = new FleetClient(channel);
            //client.makeCriticalPointreq("98",99);

        }catch (StatusRuntimeException e) {
                System.out.println("Could not connect");
                return;

        } finally {

            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }


    public void makeOnPositionUpdate(TrajectoryEnvelope footprint, RobotReport robotReport) {

        ByteString footPrint = null;


        try {
            footPrint = ByteString.copyFrom(convertToBytes(footprint));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //System.out.println("[FleetClient] in makeOnPositionUpdate .....");
        Coordinator.onPositionUpdateMessage request = Coordinator.onPositionUpdateMessage.newBuilder().
               // setFootPrintBytes(footPrint).
                setRobotid(robotReport.getRobotID()).setX(robotReport.getPose().getX()).setY(robotReport.getPose().getY()).setTheta(robotReport.getPose().getTheta())
                .setVelocity(robotReport.getVelocity()).setPathIndex(robotReport.getPathIndex())
                .setDistanceTraveled(robotReport.getDistanceTraveled()).setCriticalPoint(robotReport.getCriticalPoint()).build();

        Coordinator.noneResponse response = null;



        response = coordinatorBlockingStub.coordinatorgetOnPositionUpdate(request);

    }


    public void makeOnPositionUpdate2(TrajectoryEnvelope footprint, RobotReport robotReport) {

        ByteString footPrint = null;


        try {
            footPrint = ByteString.copyFrom(convertToBytes(footprint));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //System.out.println("[FleetClient] in makeOnPositionUpdate .....");
        Coordinator.onPositionUpdateMessage request = Coordinator.onPositionUpdateMessage.newBuilder().
                // setFootPrintBytes(footPrint).
                        setRobotid(robotReport.getRobotID()).setX(robotReport.getPose().getX()).setY(robotReport.getPose().getY()).setTheta(robotReport.getPose().getTheta())
                .setVelocity(robotReport.getVelocity()).setPathIndex(robotReport.getPathIndex())
                .setDistanceTraveled(robotReport.getDistanceTraveled()).setCriticalPoint(robotReport.getCriticalPoint()).build();

        Coordinator.noneResponse response = null;



        response = coordinatorBlockingStub.coordinatorgetOnPositionUpdateEven(request);

    }


    /**
    * Methods convertToBytes for serializing and convertFromBytes for deserializing objects...
    * */
    private byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }


    private Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }


}
