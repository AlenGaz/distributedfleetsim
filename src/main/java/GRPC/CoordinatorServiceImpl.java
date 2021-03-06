package GRPC;

import CoordinatorPackage.RemoteAbstractTrajectoryEnvelopeCoordinator;
import CoordinatorPackage.RemoteTrajectoryEnvelopeCoordinator;
import CoordinatorPackage.containers.tecStuff;
import CoordinatorPackage.containers.MakeFootPrint;
import Launch.Test1StartCoordinator;
import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import com.vividsolutions.jts.geom.Coordinate;
import fleetClient.RemoteAbstractTrajectoryEnvelopeTracker;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.coordinator.Coordinator;
import io.grpc.coordinator.CoordinatorServiceGrpc;
import io.grpc.fleetClients.FleetClientsServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.metacsp.multi.allenInterval.AllenIntervalConstraint;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import org.metacsp.multi.spatioTemporal.paths.PoseSteering;
import com.vividsolutions.jts.geom.Polygon;

import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import se.oru.coordination.coordination_oru.*;
import se.oru.coordination.coordination_oru.motionplanning.ompl.ReedsSheppCarPlanner;
import se.oru.coordination.coordination_oru.util.Missions;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class CoordinatorServiceImpl extends CoordinatorServiceGrpc.CoordinatorServiceImplBase {

    private static final Logger logger = Logger.getLogger(CoordinatorServiceImpl.class.getName());
  //  private final FleetClientsServiceGrpc.FleetClientsServiceBlockingStub blockingStubClient;

    public HashMap<Integer, RobotReport> robotIDtoRobotReport = new HashMap<Integer, RobotReport>();
    public volatile HashMap<Integer, clientConnection> robotIDtoClientConnection = new HashMap<Integer,clientConnection>();


    RemoteAbstractTrajectoryEnvelopeCoordinator tec = null;


   // String target = "localhost:50057";
   // ManagedChannel fleetServiceChannel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();

    public CoordinatorServiceImpl() {

  //     blockingStubClient = FleetClientsServiceGrpc.newBlockingStub(fleetServiceChannel);

    }

    public CoordinatorServiceImpl(RemoteAbstractTrajectoryEnvelopeCoordinator tec) {

        this.tec = tec;
    //    blockingStubClient = FleetClientsServiceGrpc.newBlockingStub(fleetServiceChannel);
    }


    public void coordinatorgetGreeting(Coordinator.robotsGreeting request,
                                       StreamObserver<Coordinator.robotgreetingResponse> responseObserver) {

        System.out.println("[CoordinatorServiceImpl] gotGreeting kan: " + request.getKan());

        int _robotID = 0;
        String _type;
        String _ip;
        int _port;
        Pose _startPose = null;
        Pose _endPose = null;
        String _timeStamp;
        double _maxAccel = 0;
        double _maxVel = 0;
        double _trackingPeriod = 0;
        Coordinate[] _footprint = new Coordinate[0];
        PoseSteering[] _poseSteering = new PoseSteering[0];

        if (request.getKan().equals("greeting")) {

            _robotID = request.getRobotID();
            _type = request.getType();
            _ip = request.getIP();
            _port = request.getPort();
            _startPose = new Pose(request.getStartPose().getX(), request.getStartPose().getY(), request.getStartPose().getZ(),
                    request.getStartPose().getRoll(), request.getStartPose().getPitch(), request.getStartPose().getYaw());
            _endPose = new Pose(request.getEndPose().getX(), request.getEndPose().getY(), request.getEndPose().getZ(),
                    request.getEndPose().getRoll(), request.getEndPose().getPitch(), request.getEndPose().getYaw());
            _maxAccel = request.getMaxAccel();
            _maxVel = request.getMaxVel();
            _timeStamp = request.getTimeStamp();
            _trackingPeriod = request.getTrackingPeriodInMillis();


            // deserializing poseSteering that comes from the robot
            _poseSteering = null;
            try {
                _poseSteering = (PoseSteering[]) convertFromBytes(request.getPoseSteering().toByteArray());
                _footprint = (Coordinate[])  convertFromBytes(request.getFootPrint().toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            clientConnection _clientConnection = new clientConnection(_type, _ip, _port, _startPose, _endPose, _timeStamp, _maxAccel, _maxVel, _trackingPeriod, _footprint, _poseSteering);
            robotIDtoClientConnection.put(_robotID, _clientConnection);
            Test1StartCoordinator.serverDispatchRobot(robotIDtoClientConnection.keySet(),_robotID, _startPose, _endPose, _maxAccel, _maxVel, _footprint, _trackingPeriod, _poseSteering);
        }
        //System.out.println("Got ClientConnections timeStamp: " + robotIDtoClientConnection.get(1).getTimeStamp());
        System.out.println("Got ClientConnections type: " + robotIDtoClientConnection.keySet());

        if (request.getKan().equals("newmission")) {


            System.out.println("[CoordinatorServiceImpl] got new mission request");
            PoseSteering[] path = null;
            try {
                path = (PoseSteering[]) convertFromBytes(request.getPoseSteering().toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ReedsSheppCarPlanner rsp = new ReedsSheppCarPlanner();
            rsp.setStart(robotIDtoClientConnection.get(_robotID).getEndPose());
            rsp.setGoals(robotIDtoClientConnection.get(_robotID).getStartPose());




            Mission m = new Mission(_robotID, path);
            Missions.enqueueMission(m);


            tec.placeRobot(_robotID, robotIDtoClientConnection.get(_robotID).getStartPose());
            Test1StartCoordinator.serverDispatchRobot(robotIDtoClientConnection.keySet(),_robotID, _startPose, _endPose, _maxAccel, _maxVel, _footprint, _trackingPeriod, _poseSteering);



           // tec.stopInference();
            // doesnt work to reset coordination
           // tec.startTrackingAddedMissions();
           // tec.setupInferenceCallback();
            //tec.startTrackingAddedMissions();
           // tec.addMissions(m);


        }



            robotgreetingResponse(responseObserver, _robotID);

    }

    public void robotgreetingResponse(StreamObserver<Coordinator.robotgreetingResponse> responseObserver, int robotID){

        /**
         * Number of replicas can be used to warn if id's are sent that alrdy exist
         * */

        //System.out.println("[CoordinatorServiceImpl] tec.numberOfReplicas is: " + tec.getNumberOfReplicas());
        robotIDtoClientConnection.containsKey(robotID);
        Coordinator.robotgreetingResponse response = Coordinator.robotgreetingResponse.newBuilder().setName("Response").setNumofReplicas(tec.getNumberOfReplicas()).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
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


            //System.out.println("[CoordinatorServiceImpl] got robotReport with report being: " + rR);
            robotIDtoRobotReport.put(_robotID,rR);



        }

        responsetorobotreport(responseObserver);
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


            //criticalPoint = robotIDtoRobotReport.get(request.getRobotID()).getCriticalPoint(); this is old news (these criticalPoints the robot has already known )
            //criticalPoint = tec.trackers.get(request.getRobotID()).getCriticalPoint(); this gives null

            //criticalPoint = tec.robotIDtoCriticalPoint.get(request.getRobotID())!= null ? tec.getCurrentDependencies().get(request.getRobotID()).getWaitingPoint() : robotIDtoRobotReport.get(request.getRobotID()).getCriticalPoint();

            if(tec.getCurrentDependencies().get(request.getRobotID())!= null){
                criticalPoint = tec.getCurrentDependencies().get(request.getRobotID()).getWaitingPoint();
            // System.out.println("[CoordinatorServiceImpl] criticalPoint response is: " + criticalPoint + " for robotID: " + request.getRobotID());

            }
            else{
              //  System.out.println("[CoordinatorServiceImpl] criticalPoint set to -1.. ");
                        criticalPoint = -1;
            }


            coordinatorcriticalpointresponse(responseObserver, criticalPoint);
        }
    }

    public int coordinatorcriticalpointresponse(StreamObserver<Coordinator.coordinatorGetCriticalPointResponseMessage> responseObserver,
                                                int criticalPoint) {
        Coordinator.coordinatorGetCriticalPointResponseMessage response =
                Coordinator.coordinatorGetCriticalPointResponseMessage.newBuilder().setCriticalPoint(criticalPoint).build();


        responseObserver.onNext(response);
        responseObserver.onCompleted();

        return criticalPoint;


    }





    // Handling a time request without string Kan parameter, just responding in this
    public void coordinatorgetCurrentTime(Coordinator.timerequest request,
                                       StreamObserver<Coordinator.timeresponse> responseObserver){

        System.out.println("[CoordinatorServiceImpl] got request of current time, answering with: " + tec.getCurrentTimeInMillis());

        Coordinator.timeresponse response = Coordinator.timeresponse.newBuilder().setCurrentTime(tec.getCurrentTimeInMillis()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }



    public void coordinatorgetRobotReportRequest(Coordinator.trackerRobotReportRequest request,
                                                 StreamObserver<Coordinator.requestrobotreport> responseObserver){

        //System.out.println("[CoordinatorServiceImpl] Getting RobotReport Request from tracker ");
        if(request.getKan().equals("TrackerRequestRobotReport")){

            RobotReport robotReport = new RobotReport();


            /**
             * The logic in this coordinator getRobotREportrequest is that if there is no robotReport for a robot requesting its report
             * inside the tec.getRobotReport, then we will just echo back what the robot has previously sent as a report to us
             * this is because we cant answer with any other robotreport before there is robotreports filled in the tec
             * */

            if(tec.getRobotReport(request.getRobotID()) == null){
               // System.out.println("Responding with robotReport with robotIDtoRobotReport" + tec.getRobotReport(request.getRobotID()));
                robotReport = robotIDtoRobotReport.get(request.getRobotID());
            }
            else {
              //  System.out.println("Responding with robotReport with tec.getRobotReport" + tec.getRobotReport(request.getRobotID()));
                robotReport = tec.getRobotReport(request.getRobotID());
            }
            coordinatorgetRobotReportResponse(responseObserver, robotReport);
        }

    }

    public void coordinatorgetRobotReportResponse(StreamObserver<Coordinator.requestrobotreport> responseObserver, RobotReport rr){

        Coordinator.requestrobotreport response = Coordinator.requestrobotreport.newBuilder()
                .setRobotid(rr.getRobotID()).setX(rr.getPose().getX()).setY(rr.getPose().getY()).setZ(rr.getPose().getZ())
                .setRoll(rr.getPose().getRoll()).setPitch(rr.getPose().getPitch()).setYaw(rr.getPose().getYaw())
                .setPathIndex(rr.getPathIndex()).setVelocity(rr.getVelocity()).setDistanceTraveled(rr.getDistanceTraveled())
                .setCriticalPoint(rr.getCriticalPoint()).build();

        //System.out.println("[CoordinatorServiceImpl] responding with RobotReport to Tracker ");

        try {
            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            responseObserver.onNext(response);
            responseObserver.onCompleted();
    }

    public void coordinatorgetOnPositionUpdate(Coordinator.onPositionUpdateMessage request,
                                               StreamObserver<Coordinator.noneResponse> responseObserver){

       // System.out.println("[coordinatorgetOnPositionUpdateOdd from robot: " + request.getRobotid());
        //Polygon footPrint = null;
        TrajectoryEnvelope footPrint = null;
     /*   if(request.getFootPrintBytes() != null) {
            try {
                //footPrint = (Polygon) convertFromBytes(request.getFootPrintBytes().toByteArray());
                footPrint = (TrajectoryEnvelope) convertFromBytes(request.getFootPrintBytes().toByteArray());

                System.out.println("[CoordinatorServiceImpl] size of the message of footPrint is: " + request.getFootPrintBytes().toByteArray().length);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

      */
        coordinatorgetOnPositionUpdateResponse(responseObserver);

        RobotReport rr = new RobotReport(request.getRobotid(),
                new Pose(request.getX(), request.getY(), request.getZ(),request.getRoll(),request.getPitch(),request.getYaw()),
                request.getPathIndex(),request.getVelocity(),request.getDistanceTraveled(),request.getCriticalPoint());
        robotIDtoRobotReport.put(request.getRobotid(),rr);


       /**
        * Visualizing the movement that is done from the integration in RK4
        *

        *  some alternative below
        *  System.out.println("[CoordinatorServiceImpl] in onPositionUpdate() got footprint" + footPrint);
        *  tec.getVisualization().displayRobotState(footPrint, rr, "Hi");
        *  tec.getVisualization().displayRobotState(tec.getCurrentTrajectoryEnvelope(1), rr, "A");
        *  tec.getVisualization().displayRobotState(tec.getCurrentTrajectoryEnvelope(2), rr, "A");
        * */



        // in extra info shown in, % of path: critical point: {RobotID@waitingPoint}
   /*     tec.getVisualization().displayRobotState(tec.getCurrentTrajectoryEnvelope(rr.getRobotID()), rr
                , ":cp:" + robotIDtoRobotReport.get(request.getRobotid()).getCriticalPoint()
                + " {R" + String.valueOf(tec.getCurrentDependencies().get(request.getRobotid()).getDrivingRobotID())
                + "@" + String.valueOf(tec.getCurrentDependencies().get(request.getRobotid()).getWaitingPoint()) + "}");
*/
            synchronized (tec.getCurrentDependencies()) {
                if ((tec.getCurrentDependencies().containsKey(request.getRobotid())) && tec.getCurrentDependencies().containsKey(request.getRobotid())) {


                    tec.getVisualization().displayRobotState(tec.getCurrentSuperEnvelope(rr.getRobotID()), rr,
                            " ¡ " + tec.getCurrentTrajectoryEnvelope(rr.getRobotID()).getPathLength());


          /*  tec.getVisualization().displayRobotState(footPrint, rr,
                    String.valueOf(tec.getCurrentTrajectoryEnvelope(rr.getRobotID()).getPathLength()));

           */
                } else {
                    tec.getVisualization().displayRobotState(tec.getCurrentTrajectoryEnvelope(rr.getRobotID()), rr,
                            String.valueOf(" | " + rr.getCriticalPoint()));

                    //    trying with Polygon instead of the trajecvelope
        /*      tec.getVisualization().displayRobotState(footPrint, rr,
                    String.valueOf(" | " + rr.getCriticalPoint()));
       */
                }

                // trying to just display the geometry and send


                //tec.getVisualization().addEnvelope(footPrint);

                // here below displaying Dependency arrows, its not supposed to be here just now..


                for (int robotID : tec.getCurrentDependencies().keySet()) {
                    Dependency dep = tec.getCurrentDependencies().get(robotID);

                    if (dep == null) {
                        System.out.println("[CoordinatorServiceImpl] returning cause dep is null ");
                        return;
                    }
                    RemoteAbstractTrajectoryEnvelopeTracker waitingTrackers = tec.trackers.get(dep.getWaitingRobotID());
                    RemoteAbstractTrajectoryEnvelopeTracker drivingTrackers = tec.trackers.get(dep.getDrivingRobotID());

                    if (waitingTrackers != null) {
                        if (drivingTrackers != null) {
                            RobotReport rrDriving = drivingTrackers.getRobotReport();
                            RobotReport rrWaiting = waitingTrackers.getRobotReport();
                            String arrowIdentifier = "_" + dep.getWaitingRobotID() + "-" + dep.getDrivingRobotID();


                            tec.getVisualization().displayDependency(rrWaiting, rrDriving, arrowIdentifier);
                        }
                    }
                }
            }
        //tec.getVisualization().updateVisualization();





    }



    public void coordinatorgetOnPositionUpdateEven(Coordinator.onPositionUpdateMessage request,
                                               StreamObserver<Coordinator.noneResponse> responseObserver) {

      //  System.out.println("[coordinatorgetOnPositionUpdateEven from robot: " + request.getRobotid());
        //Polygon footPrint = null;
        TrajectoryEnvelope footPrint = null;
     /*   if(request.getFootPrintBytes() != null) {
            try {
                //footPrint = (Polygon) convertFromBytes(request.getFootPrintBytes().toByteArray());
                footPrint = (TrajectoryEnvelope) convertFromBytes(request.getFootPrintBytes().toByteArray());

                System.out.println("[CoordinatorServiceImpl] size of the message of footPrint is: " + request.getFootPrintBytes().toByteArray().length);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

      */

        RobotReport rr = new RobotReport(request.getRobotid(),
                new Pose(request.getX(), request.getY(), request.getZ(),request.getRoll(),request.getPitch(),request.getYaw()),
                request.getPathIndex(),request.getVelocity(),request.getDistanceTraveled(),request.getCriticalPoint());


        coordinatorgetOnPositionUpdateResponse(responseObserver);

        // in extra info shown in, % of path: critical point: {RobotID@waitingPoint}
   /*     tec.getVisualization().displayRobotState(tec.getCurrentTrajectoryEnvelope(rr.getRobotID()), rr
                , ":cp:" + robotIDtoRobotReport.get(request.getRobotid()).getCriticalPoint()
                + " {R" + String.valueOf(tec.getCurrentDependencies().get(request.getRobotid()).getDrivingRobotID())
                + "@" + String.valueOf(tec.getCurrentDependencies().get(request.getRobotid()).getWaitingPoint()) + "}");
*/
        synchronized (tec.getCurrentDependencies()) {
            if ((tec.getCurrentDependencies().containsKey(request.getRobotid())) && tec.getCurrentDependencies().containsKey(request.getRobotid())) {


                tec.getVisualization().displayRobotState(tec.getCurrentSuperEnvelope(rr.getRobotID()), rr,
                        " ¡ " + tec.getCurrentTrajectoryEnvelope(rr.getRobotID()).getPathLength());


          /*  tec.getVisualization().displayRobotState(footPrint, rr,
                    String.valueOf(tec.getCurrentTrajectoryEnvelope(rr.getRobotID()).getPathLength()));

           */
            } else {
                tec.getVisualization().displayRobotState(tec.getCurrentTrajectoryEnvelope(rr.getRobotID()), rr,
                        String.valueOf(" | " + rr.getCriticalPoint()));

                //    trying with Polygon instead of the trajecvelope
        /*      tec.getVisualization().displayRobotState(footPrint, rr,
                    String.valueOf(" | " + rr.getCriticalPoint()));
       */
            }

            // trying to just display the geometry and send


            //tec.getVisualization().addEnvelope(footPrint);

            // here below displaying Dependency arrows, its not supposed to be here just now..


            for (int robotID : tec.getCurrentDependencies().keySet()) {
                Dependency dep = tec.getCurrentDependencies().get(robotID);

                if (dep == null) {
                    System.out.println("[CoordinatorServiceImpl] returning cause dep is null ");
                    return;
                }
                RemoteAbstractTrajectoryEnvelopeTracker waitingTrackers = tec.trackers.get(dep.getWaitingRobotID());
                RemoteAbstractTrajectoryEnvelopeTracker drivingTrackers = tec.trackers.get(dep.getDrivingRobotID());

                if (waitingTrackers != null) {
                    if (drivingTrackers != null) {
                        RobotReport rrDriving = drivingTrackers.getRobotReport();
                        RobotReport rrWaiting = waitingTrackers.getRobotReport();
                        String arrowIdentifier = "_" + dep.getWaitingRobotID() + "-" + dep.getDrivingRobotID();


                        tec.getVisualization().displayDependency(rrWaiting, rrDriving, arrowIdentifier);
                    }
                }
            }
        }




    }

    public void coordinatorgetOnPositionUpdateResponse(StreamObserver<Coordinator.noneResponse> responseObserver){


        Coordinator.noneResponse response =
                Coordinator.noneResponse.newBuilder().setNone("").build();

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

    public static void main(String[] args) throws InterruptedException, IOException {


    }


}