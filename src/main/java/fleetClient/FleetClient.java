package fleetClient;



import CoordinatorPackage.containers.tecAllenIntervalContainer;
import CoordinatorPackage.containers.tecStuff;

import aima.core.util.datastructure.Pair;
import com.google.protobuf.ByteString;
import com.vividsolutions.jts.geom.Coordinate;
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

        blockingStub = FleetClientsServiceGrpc.newBlockingStub(channel);
        coordinatorBlockingStub = CoordinatorServiceGrpc.newBlockingStub(channel);
    }


    public int makeGreeting(String kan, int robotID, String type, String IP, int port, Pose startPose, Pose endPose,
                            String timeStamp, double maxAccel, double maxVel,
                            double trackingPeriodInMillis, PoseSteering[] poseSteerings, Coordinate[] footprint) {

        System.out.println("[FleetClient] makeGreeting kan: " + kan);

        ByteString poseSteeringBytes = null;
        ByteString footprintBytes = null;
        try {
            poseSteeringBytes= ByteString.copyFrom(convertToBytes(poseSteerings));
            footprintBytes = ByteString.copyFrom(convertToBytes(footprint));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Coordinator.robotsGreeting greetingBuild = Coordinator.robotsGreeting.newBuilder().setKan(kan).setRobotID(robotID).setType(type).setIP(IP).setPort(port)
                .setStartPose(Coordinator.robotPose.newBuilder().setX(startPose.getX()).setY(startPose.getY()).setZ(startPose.getZ())
                        .setRoll(startPose.getRoll()).setPitch(startPose.getPitch()).setYaw(startPose.getYaw()).build()).
                setEndPose(Coordinator.robotPose.newBuilder().setX(endPose.getX()).setY(endPose.getY()).setZ(endPose.getZ())
                .setRoll(endPose.getRoll()).setPitch(endPose.getPitch()).setYaw(endPose.getYaw()).build()).
                        setTimeStamp(timeStamp).setMaxAccel(maxAccel).setMaxVel(maxVel).setTrackingPeriodInMillis(trackingPeriodInMillis)
                        .setPoseSteering(poseSteeringBytes).setFootPrint(footprintBytes).build();

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


    public void makeRobotReport(String my_robotReport, int robotid, double x, double y, double z, double roll, double pitch, double yaw, double velocity, int pathIndex, double distanceTraveled, int criticalPoint) {
        Coordinator.requestrobotreport getRR = Coordinator.requestrobotreport.newBuilder().setReq(my_robotReport).setRobotid(robotid).setX(x).setY(y).setZ(z)
                .setRoll(roll).setPitch(pitch).setYaw(yaw).setVelocity(velocity).setPathIndex(pathIndex)
                .setDistanceTraveled(distanceTraveled).setCriticalPoint(criticalPoint).build();



        try {
            coordinatorBlockingStub.coordinatorrobotreport(getRR);

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
            coordinatorRsp = coordinatorBlockingStub.withDeadlineAfter(1, TimeUnit.SECONDS).coordinatorcriticalpoint(req);
        }
        catch(StatusRuntimeException e){
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());

        }
        if (coordinatorRsp != null){
            return coordinatorRsp.getCriticalPoint();

            }
        else {
            return 50;
        }
        //System.out.println("[FleetClient] getCriticalPoint response was: " + coordinatorRsp.getCriticalPoint() + " for robotID: " + robotID);
        //return coordinatorRsp.getCriticalPoint();
    }




    public long makeCurrentTimeRequest(){
            Coordinator.timerequest request = Coordinator.timerequest.newBuilder().build();
            Coordinator.timeresponse response;


            return coordinatorBlockingStub.coordinatorgetCurrentTime(request).getCurrentTime();

    }


/*
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

 */

    public RobotReport makeRobotReportRequest(int robotID) {
        Coordinator.trackerRobotReportRequest request = Coordinator.trackerRobotReportRequest.newBuilder().setKan("TrackerRequestRobotReport").setRobotID(robotID).build();
        Coordinator.requestrobotreport response= null;

        //System.out.println("[FleetClient] requesting inside makeRobotReportRequest");
        response = coordinatorBlockingStub.coordinatorgetRobotReportRequest(request);

        //System.out.println("[FleetClient] response from makeRobotReportRequest: " + response);

        int _robotID = response.getRobotid();
        Pose _pose = new Pose(response.getX(), response.getY(), response.getZ(), response.getRoll(), response.getPitch(), response.getYaw());
        RobotReport rR = new RobotReport(_robotID, _pose, response.getPathIndex(), response.getVelocity(), response.getDistanceTraveled(), response.getCriticalPoint());


        return rR;
    }

    /*
    public FleetVisualization makeVisualizerRequest(){

    not needed to make as a msg request if the visualzier will still be on the coordinator
    }
    */



    public void makeOnPositionUpdate(RobotReport robotReport) {

        ByteString footPrint = null;

/*
        try {
            footPrint = ByteString.copyFrom(convertToBytes(footprint));
        } catch (IOException e) {
            e.printStackTrace();
        }


 */


        Coordinator.onPositionUpdateMessage request = Coordinator.onPositionUpdateMessage.newBuilder().
               // setFootPrintBytes(footPrint).

                setRobotid(robotReport.getRobotID()).setX(robotReport.getPose().getX()).setY(robotReport.getPose().getY())
                .setZ(robotReport.getPose().getZ())
                .setRoll(robotReport.getPose().getRoll()).setPitch(robotReport.getPose().getPitch()).setYaw(robotReport.getPose().getYaw())
                .setVelocity(robotReport.getVelocity()).setPathIndex(robotReport.getPathIndex())
                .setDistanceTraveled(robotReport.getDistanceTraveled()).setCriticalPoint(robotReport.getCriticalPoint()).build();

        Coordinator.noneResponse response = null;


        coordinatorBlockingStub.coordinatorgetOnPositionUpdate(request);

    }


    public void makeOnPositionUpdate2(RobotReport robotReport) {



        Coordinator.onPositionUpdateMessage request = Coordinator.onPositionUpdateMessage.newBuilder().
                        setRobotid(robotReport.getRobotID()).setX(robotReport.getPose().getX()).setY(robotReport.getPose().getY())
                .setZ(robotReport.getPose().getZ())
                .setRoll(robotReport.getPose().getRoll()).setPitch(robotReport.getPose().getPitch()).setYaw(robotReport.getPose().getYaw())
                .setVelocity(robotReport.getVelocity()).setPathIndex(robotReport.getPathIndex())
                .setDistanceTraveled(robotReport.getDistanceTraveled()).setCriticalPoint(robotReport.getCriticalPoint()).build();

        Coordinator.noneResponse response = null;



        coordinatorBlockingStub.coordinatorgetOnPositionUpdateEven(request);

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


/*    public static void main(String[] args) throws InterruptedException {

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
    }*/

}
