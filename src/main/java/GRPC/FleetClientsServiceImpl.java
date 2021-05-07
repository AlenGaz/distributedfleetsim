package GRPC;


import fleetClient.RemoteAbstractTrajectoryEnvelopeTracker;
import io.grpc.fleetClients.FleetClientsServiceGrpc;
import io.grpc.fleetClients.Fleetclients;

import io.grpc.stub.StreamObserver;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import se.oru.coordination.coordination_oru.RobotReport;
import CoordinatorPackage.RemoteTrajectoryEnvelopeCoordinatorSimulation;

import java.util.ArrayList;
import java.util.HashMap;


public class FleetClientsServiceImpl extends FleetClientsServiceGrpc.FleetClientsServiceImplBase {



    public void clientCriticalpoints(Fleetclients.clientGetCriticalPointRequestMessage request,
                                    StreamObserver<Fleetclients.clientGetCriticalPointResponseMessage> responseObserver){

        System.out.println("handling request:" + request);
        int criticalPoint = respondWithSendingCriticalPoint(responseObserver, request.getRobotID());
    }

    public int respondWithSendingCriticalPoint(StreamObserver<Fleetclients.clientGetCriticalPointResponseMessage> responseObserver, int robotID) {


        return robotID;
    }


    //////////////////////////////////////////////

    private static int criticalPointResponse;
    public int it = 0;
    String text;
    public ArrayList ID_list = new ArrayList();

    public static HashMap<Integer, RobotReport> robotIDtoRobotReport = new HashMap<Integer, RobotReport>();
    //int criticalPointResponse = 0;


    public void grobotID(Fleetclients.getRobotID request, StreamObserver<Fleetclients.robotID> responseObserver) {
        System.out.println("handling [" + request + "]"+ " endpoint: [" + request.toString() + "]");

        if(request.getKey().equals("My ID")) {

            System.out.println("got Robot id: " + request.getValue());
            System.out.println("got Robot type: " + request.getType());
            System.out.println("got Robot connectionIP: " + request.getConnection());
            System.out.println("got Robot Timestamp: " + request.getTimeStamp());
            System.out.println("got Robot port: " + request.getPort());



            if(!ID_list.contains(request.getValue())){
                ID_list.add(request.getValue()); ////// if id not in list, append
            }

            RemoteTrajectoryEnvelopeCoordinatorSimulation tes = new RemoteTrajectoryEnvelopeCoordinatorSimulation();
            System.out.println("Current ID's connected: " + ID_list);
            // System.out.println("Current clientConnections: " + clientConnectionInfos); The client Connection infos are not displaying correct

            respondWithSendingIDreceived(responseObserver);
        }

    }


/*
    public void grobotReport(Fleetclients.robotReportRequest request,StreamObserver<Fleetclients.robotReportResponse> responseObserver) {

        System.out.println("[FC S] in grobotReport (request)");

        RobotReport report = new RobotReport();
        int _robotID = 0;
        if(request.getKan().equals("my RobotReport")) {

            // Necessary fields to set gotten values into robotReport type so it goes into a robot report hashMap
            //_robotID = request.getRobotID();
            //Pose _pose = new Pose(request.getX(), request.getY(),request.getZ(), request.getRoll(),request.getPitch(),request.getYaw());
            //RobotReport rR = new RobotReport(_robotID, _pose, request.getPathIndex(),request.getVelocity(),request.getDistanceTraveled(),request.getCriticalPoint());

            //robotIDtoRobotReport.put(_robotID,rR);

            System.out.println("[FC S] before respondToRobotReport: -> report" + report);
            respondToRobotReport(responseObserver, report.getRobotID(), report.getPose().getX(), report.getPose().getY(), report.getPose().getZ(),
                    report.getPose().getRoll(), report.getPose().getPitch(), report.getPose().getYaw(), report.getPathIndex(), report.getVelocity(),
                    report.getDistanceTraveled(), report.getCriticalPoint());
            System.out.println("[FC S] after respondToRobotReport");
        }
        //System.out.println("RobotReports ->>>  " + robotIDtoRobotReport + "\n");
    }
*/


    //generic response to stub
    public void respondToRobotReport(StreamObserver<Fleetclients.robotReportResponse> responseObserver, int _robotID, double _x, double _y, double _z,
                                     double _roll, double _pitch, double _yaw, int _pathindex, double _velocity, double _distancetraveled, int _criticalpoint) {

        System.out.println("[FC S] before robotReportResponse");
        Fleetclients.robotReportResponse response = Fleetclients.robotReportResponse.newBuilder()
                .setRobotID(_robotID).setX(_x).setY(_y).setZ(_z).setRoll(_roll).setPitch(_pitch)
                .setYaw(_yaw).setPathIndex(_pathindex).setVelocity(_velocity).setDistanceTraveled(_distancetraveled)
                .setCriticalPoint(_criticalpoint).build();
        System.out.println("[FC S] after robotReportResponse");

        responseObserver.onNext(response);
        responseObserver.onCompleted();

        //return ;
    }



    public int requestID = 0;
    public void coordinatorrobotreport(Fleetclients.coordinatorrequestrobotid request,
                                       StreamObserver<Fleetclients.coordinatorrespondrobotreport> responseObserver){
        System.out.println("handling [" + request + "]"+ " endpoint: [" + request.toString() + "]");

        if(request.getReq().equals("requestrobotreport")){

            requestID= request.getRobotid();
        }
        System.out.println("Responding to robotrequest, the requested ID was: " + requestID);

        coordinatorresponserobotreport(responseObserver);
    }


    //this was when i thought that coordinator would send the robotid further somewhere..not relevant anymore (2021-03-19)
    private void coordinatorresponserobotreport(StreamObserver<Fleetclients.coordinatorrespondrobotreport> responseObserver) {


        System.out.println("[FleetClientService] ... in responsemethod");
        double _x = robotIDtoRobotReport.get(requestID).getPose().getX();
        double _y = robotIDtoRobotReport.get(requestID).getPose().getY();
        double _z = robotIDtoRobotReport.get(requestID).getPose().getZ();
        double _roll = robotIDtoRobotReport.get(requestID).getPose().getRoll();
        double _pitch = robotIDtoRobotReport.get(requestID).getPose().getPitch();
        double _yaw = robotIDtoRobotReport.get(requestID).getPose().getYaw();
        int _pathindex = robotIDtoRobotReport.get(requestID).getPathIndex();
        double _velocity = robotIDtoRobotReport.get(requestID).getVelocity();
        double _distancetraveled = robotIDtoRobotReport.get(requestID).getDistanceTraveled();
        int _criticalpoint = robotIDtoRobotReport.get(requestID).getCriticalPoint();

/*
        Fleetclients.coordinatorrespondrobotreport response =
                Fleetclients.coordinatorrespondrobotreport.newBuilder().setX(_x).setY(_y).setZ(_z)
                        .setRoll(_roll).setPitch(_pitch).setYaw(_yaw)
                        .setPathIndex(_pathindex).setVelocity(_velocity).setDistanceTraveled(_distancetraveled).setCriticalPoint(_criticalpoint).build();

        System.out.println("[FleetClientService] ... coordinatorresponse" + response);


        responseObserver.onNext(response);*/
        responseObserver.onCompleted();
    }


    //Generic response to stub
    private void respondWithSendingIDreceived(StreamObserver<Fleetclients.robotID> responseObserver) {
        Fleetclients.robotID response =
                Fleetclients.robotID.newBuilder()
                        .setName(text + "&id received" ).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    // Generic response to stub
    private void respondWithSendingMsg(StreamObserver<Fleetclients.responseMessage> responseObserver) {
        Fleetclients.responseMessage response =
                Fleetclients.responseMessage.newBuilder()
                        .setName(text + " " + it).build();
        System.out.println("Responding with -> " + response.getName() + " !The # of clients is: " + ID_list.size());

        it++;
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    public void clientCriticalpoint(Fleetclients.clientGetCriticalPointRequestMessage request,
                                     StreamObserver<Fleetclients.clientGetCriticalPointResponseMessage> responseObserver){

        RemoteAbstractTrajectoryEnvelopeTracker tracker = new RemoteAbstractTrajectoryEnvelopeTracker() {
            @Override
            protected void onTrajectoryEnvelopeUpdate(TrajectoryEnvelope te) {}
            @Override
            public void setCriticalPoint(int criticalPoint) {}
            @Override
            public RobotReport getRobotReport() { return null; }

            @Override
            public RobotReport getRobotReport(int robotID) {
                return null;
            }

            @Override public long getCurrentTimeInMillis() { return 0; }

            @Override
            public void startTracking() { }
        };

        System.out.println("Before IF cond");
        //if(request.getKan().equals("coordinatorrequestcriticalpoint"))
        //TODO robotIDtoRobotReport empty
        System.out.println("After IF robotIDtoRobotReport: " + robotIDtoRobotReport);
        System.out.println("After IF request: " + request.getRobotID());
        System.out.println("After IF getCriticalPoint: " + robotIDtoRobotReport.get(1).getCriticalPoint());
        //TODO stoops here
        //criticalPointResponse = robotIDtoRobotReport.get(request.getRobotID()).getCriticalPoint();
        criticalPointResponse = 13;
        System.out.println("Before criticalPointResponse cond");
        System.out.println("[TrackerService] getting request" + request + criticalPointResponse);
        System.out.println("After criticalPointResponse cond");

        trackerCriticalPointResponse(responseObserver,criticalPointResponse);
        //return criticalPointResponse;
    }


    public static int trackerCriticalPointResponse(StreamObserver<Fleetclients.clientGetCriticalPointResponseMessage> responseObserver, int criticalPoint) {

        Fleetclients.clientGetCriticalPointResponseMessage response=
                Fleetclients.clientGetCriticalPointResponseMessage.newBuilder().setCriticalPoint(criticalPoint).build();

        System.out.println("[TrackerService] Responding to criticalpoint request with criticalPoint response: " + criticalPointResponse);

        responseObserver.onNext(response);
        responseObserver.onCompleted();

        return criticalPointResponse;
    }




}