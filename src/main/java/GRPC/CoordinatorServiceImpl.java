package GRPC;

import CoordinatorPackage.AbstractTrajectoryEnvelopeCoordinator;
import fleetClient.AbstractTrajectoryEnvelopeTracker;
import fleetClient.TrajectoryEnvelopeCoordinator;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.coordinator.Coordinator;
import io.grpc.coordinator.CoordinatorServiceGrpc;
//import io.grpc.fleetClients.FleetClientGrpc;
//import io.grpc.fleetClients.FleetClientServiceGrpc;
import io.grpc.fleetClients.FleetClientsServiceGrpc;
import io.grpc.fleetClients.Fleetclients;
import io.grpc.stub.StreamObserver;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import se.oru.coordination.coordination_oru.Dependency;
import se.oru.coordination.coordination_oru.NetworkConfiguration;
import se.oru.coordination.coordination_oru.RobotReport;
import se.oru.coordination.coordination_oru.TrackingCallback;


import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoordinatorServiceImpl extends CoordinatorServiceGrpc.CoordinatorServiceImplBase {

    private static final Logger logger = Logger.getLogger(CoordinatorServiceImpl.class.getName());
    private final FleetClientsServiceGrpc.FleetClientsServiceBlockingStub blockingStubClient;
    public static HashMap<Integer, RobotReport> robotIDtoRobotReport = new HashMap<Integer, RobotReport>();

    private static final CoordinatorServiceImpl instance = new CoordinatorServiceImpl ();


    public void printDependencies() {

        //Set up infrastructure that maintains the representation
        //tec.setupSolver(0, 100000000);
        //Start the thread that revises precedences at every period
        //tec.startInference();

        System.out.println("getCurrentDependencies->: " + tec.getCurrentDependencies());

    }

    AbstractTrajectoryEnvelopeCoordinator tec = null;


    String target = "localhost:50057";
    ManagedChannel fleetServiceChannel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();

    public CoordinatorServiceImpl() {

        blockingStubClient = FleetClientsServiceGrpc.newBlockingStub(fleetServiceChannel);

    }

    public CoordinatorServiceImpl(AbstractTrajectoryEnvelopeCoordinator tec) {

        this.tec = tec;
        blockingStubClient = FleetClientsServiceGrpc.newBlockingStub(fleetServiceChannel);

    }


    public void coordinatorrobotreport(Coordinator.requestrobotreport request,
                                       StreamObserver<Coordinator.responserobotreport> responseObserver) {

        int _robotID = 0;

        if (request.getReq().equals("my RobotReport")) {

            /** Piecing together the robotReport to put from the request.. this specific "request"
             * is kind of like an asynch update from each client with RobotReports incoming, no actual real response is given,
             * the necessary data is being sent from the clients
             */
            _robotID = request.getRobotid();
            Pose _pose = new Pose(request.getX(), request.getY(),request.getZ(), request.getRoll(),request.getPitch(),request.getYaw());
            RobotReport rR = new RobotReport(_robotID, _pose, request.getPathIndex(),request.getVelocity(),request.getDistanceTraveled(),request.getCriticalPoint());


            printDependencies();

            robotIDtoRobotReport.put(_robotID,rR);

        }


        //System.out.println("RobotReports" + robotIDtoRobotReport);



   /*   System.out.println("Tec timeInMil" + tec.getCurrentTimeInMillis());
        System.out.println("Tec CurrentDependencies" + tec.getCurrentDependencies());
        System.out.println("Tec trackers" + tec.trackers.get(1));
        System.out.println("Tec staticDep" + tec.currentDependencies);
        System.out.println("Tec getNofRepls" + tec.getNumberOfReplicas());
        System.out.println("TEC tenvelope" + tec.getCurrentTrajectoryEnvelope(1));
        System.out.println("Tec superEnvelopes" + tec.getCurrentSuperEnvelope(1));

        */

        //AbstractTrajectoryEnvelopeCoordinator.metaCSPLogger.info("Printing something from tec" + tec.getCurrentDependencies());
        //AbstractTrajectoryEnvelopeCoordinator.metaCSPLogger.info("Printing something from metaCSP");
        //System.out.println("Printing something from tec" + tec.getCurrentDependencies());

        responsetorobotreport(responseObserver);
    }

    public HashMap<Integer, RobotReport> getHashMap() {
        System.out.println("ZZZZ" + robotIDtoRobotReport);
        return robotIDtoRobotReport;
    }

    public void responsetorobotreport(StreamObserver<Coordinator.responserobotreport> responseObserver){


        Coordinator.responserobotreport response = Coordinator.responserobotreport.newBuilder().setName("Response").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    public void responsetorobotreportwithCriticalpoint(StreamObserver<Coordinator.responserobotreport> responseObserver, int criticalP){

        // skickar tillbaka criticalpoint

    }


    public void coordinatorcriticalpoint(Coordinator.coordinatorGetCriticalPointRequestMessage request,
                                         StreamObserver<Coordinator.coordinatorGetCriticalPointResponseMessage> responseObserver) {

        int criticalPoint = 0;
        int criticalPoint2 = 0;


        if (request.getKan().equals("requestcriticalpoint")) {

            //System.out.println("[CoordinatorService] getting request: " + request + "\n");

            // SHOULD NOT BE NULL TO READ FROM THIS
            criticalPoint = robotIDtoRobotReport.get(request.getRobotID()).getCriticalPoint();
            System.out.println("[CoordinatorService] responding to criticalpoint1 request with: " + criticalPoint);

            //criticalPoint2 = tec.trackers.get(request.getRobotID()).getCriticalPoint();
            //System.out.println("[CoordinatorService] responding to criticalpoint2 request with: " + criticalPoint2);


            coordinatorcriticalpointresponse(responseObserver, criticalPoint);
        }
    }

    public int coordinatorcriticalpointresponse(StreamObserver<Coordinator.coordinatorGetCriticalPointResponseMessage> responseObserver,
                                                int criticalPoint) {

        Coordinator.coordinatorGetCriticalPointResponseMessage response =
                Coordinator.coordinatorGetCriticalPointResponseMessage.newBuilder().setCriticalPoint(criticalPoint).build();

        System.out.println("[CoordinatorService] Responding to critical point request with criticalPoint response: " + criticalPoint);


        responseObserver.onNext(response);
        responseObserver.onCompleted();

        return criticalPoint;


    }


    public RobotReport makeRobotReportRequest(String myreq, int robotID) {
        Fleetclients.robotReportRequest request = Fleetclients.robotReportRequest.newBuilder().setKan(myreq).setRobotID(robotID).build();

        RobotReport rR = null;

        Fleetclients.robotReportResponse response = null;


        try {
            //TODO stoops before blocking stub (no response)

            response = blockingStubClient.grobotReport(request);



            int _robotID = 0;
            _robotID = request.getRobotID();
            Pose _pose = new Pose(response.getX(), response.getY(),response.getZ(), response.getRoll(),response.getPitch(),response.getYaw());
            rR = new RobotReport(_robotID, _pose, response.getPathIndex(),response.getVelocity(),response.getDistanceTraveled(),response.getCriticalPoint());

            robotIDtoRobotReport.put(_robotID,rR);
        }
        catch(StatusRuntimeException e){
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());

        }

        return rR;
    }


    public void coordinatordependencies(Coordinator.getCurrentDependencies request,
                                        StreamObserver<Coordinator.noneResponse> responseObserver){

        HashSet<Dependency> depdes = null;
        byte[] dependenciesBytes = null;


        if(request.getKan().equals("currentDependencies")){

            //System.out.println("[CoordinatorServiceImpl] inside the request + " +  request.getKan());
            dependenciesBytes= request.getDepBytes().toByteArray();

            try {
                depdes = (HashSet<Dependency>) convertFromBytes(dependenciesBytes);
                //System.out.println("[CoordinatorServiceImpl] after depdes");
            } catch (IOException e) {
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            currentDepResponse(responseObserver);
        }

        System.out.println("[CoordinatorServiceImpl] deserialized into (outer): " + depdes);
    }


    public void currentDepResponse(StreamObserver<Coordinator.noneResponse> responseObserver){

        Coordinator.noneResponse response =
                Coordinator.noneResponse.newBuilder().setNone("hej").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
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