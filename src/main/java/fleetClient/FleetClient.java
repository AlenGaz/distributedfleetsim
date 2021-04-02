package fleetClient;


import aima.core.util.datastructure.Pair;
import com.google.protobuf.ByteString;
import io.grpc.Channel;

import java.io.*;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.coordinator.Coordinator;
import io.grpc.coordinator.CoordinatorServiceGrpc;
import io.grpc.fleetClients.FleetClientsServiceGrpc;
import io.grpc.fleetClients.Fleetclients;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import org.sat4j.pb.tools.DependencyHelper;
import se.oru.coordination.coordination_oru.Dependency;

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


    public void makeGreeting(String kan, int robotID, String type, String IP, int port, Pose pose,
            String timeStamp, double maxAccel, double maxVel,
                             double trackingPeriodInMillis, MakeFootPrint makeFootPrint) {

        Fleetclients.makeGreeting makegreeting = Fleetclients.makeGreeting.newBuilder().setKan(kan).setRobotID(robotID).setType(type).setIP(IP).setPort(port)
                .setRpose(Fleetclients.robotPose.newBuilder().setX(pose.getX()).setY(pose.getY()).setZ(pose.getZ())
                        .setRoll(pose.getRoll()).setPitch(pose.getPitch()).setYaw(pose.getYaw()).build()).setTimeStamp(timeStamp).setMaxAccel(maxAccel).setMaxVel(maxVel)
                .setTrackingPeriodInMillis(trackingPeriodInMillis)
                .setMakeFootPrint(Fleetclients.MakeFootPrint.newBuilder().setCenterX(makeFootPrint.getCenterX()).setCenterY(makeFootPrint.centerY).setMinVerts(makeFootPrint.minVerts).setMaxVerts(makeFootPrint.maxVerts).setMinRadius(makeFootPrint.minRadius).setMaxRadius(makeFootPrint.maxRadius).build()).build();
        Fleetclients.greetingResponse greetingresponse;

        try {
            System.out.println("making greeting");
            greetingresponse = blockingStub.greetingMessage(makegreeting);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());
            return;
        }
    }

    public void makeRobotReport(String my_robotReport, int robotid, double x, double y, double z, double roll, double pitch, double yaw, double velocity, int pathIndex, double distanceTraveled, int criticalPoint) {
        Coordinator.requestrobotreport getRR = Coordinator.requestrobotreport.newBuilder().setReq(my_robotReport).setRobotid(robotid).setX(x).setY(y).setZ(z)
                .setRoll(roll).setPitch(pitch).setYaw(yaw).setVelocity(velocity).setPathIndex(pathIndex)
                .setDistanceTraveled(distanceTraveled).setCriticalPoint(criticalPoint).build();
       Coordinator.responserobotreport responserobotreport;


        try {
            responserobotreport = coordinatorBlockingStub.coordinatorrobotreport(getRR);

        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());
            return;
        }
    }

    public int makeCriticalPointreq(String myreq, int robotID) {
        Coordinator.coordinatorGetCriticalPointRequestMessage req = Coordinator.coordinatorGetCriticalPointRequestMessage
                .newBuilder().setKan(myreq).setRobotID(robotID).build();
        Coordinator.coordinatorGetCriticalPointResponseMessage coordinatorRsp = null;
        
        try {
            coordinatorRsp = coordinatorBlockingStub.coordinatorcriticalpoint(req);
        }
        catch(StatusRuntimeException e){
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());

        }

        //System.out.println("In fleet client: " + coordinatorcriticalpoint(req));
        //int cp = coordinatorRsp.getCriticalPoint();
        //System.out.println("before return" + cp);
        //return cp;

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


    public static void main(String[] args) throws InterruptedException {

        String messageToSend;
        String target = "localhost:50050";


        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();

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
