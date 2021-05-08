package GRPC;

import io.grpc.tracker.TrackerServiceGrpc;

public class TrackerService extends TrackerServiceGrpc.TrackerServiceImplBase {

  /*  private static int criticalPointResponse;
    public int it = 0;
    String text;
    public ArrayList ID_list = new ArrayList();
    public static HashMap<Integer, clientConnection> clientConnectionInfos = new HashMap <Integer, clientConnection>(); // contains ID, type, ip&port, timestamp
    public static HashMap<Integer, RobotReport> robotIDtoRobotReport = new HashMap<Integer, RobotReport>();
    //int criticalPointResponse = 0;


    //@Override
    public void grobotID(
            Tracker.getRobotID request,
            StreamObserver<Tracker.robotID> responseObserver) {
        System.out.println("handling [" + request + "]"+ " endpoint: [" + request.toString() + "]");

        if(request.getKey().equals("My ID")) {

            System.out.println("got Robot id: " + request.getValue());
            System.out.println("got Robot type: " + request.getType());
            System.out.println("got Robot connectionIP: " + request.getConnection());
            System.out.println("got Robot Timestamp: " + request.getTimeStamp());

            clientConnection cC = new clientConnection(request.getType(),request.getConnection(),request.getTimeStamp());
            clientConnectionInfos.put(request.getValue(), cC);
            System.out.println("Current clientconnections: " + clientConnectionInfos);

            if(!ID_list.contains(request.getValue())){
                ID_list.add(request.getValue()); ////// if id not in list, append

            }

            TrajectoryEnvelopeCoordinatorSimulation tes = new TrajectoryEnvelopeCoordinatorSimulation();
            System.out.println("Current ID's connected: " + ID_list);
            // System.out.println("Current clientConnections: " + clientConnectionInfos); The client Connection infos are not displaying correct

            respondWithSendingIDreceived(responseObserver);

        }

    }

    //@Override
    public void grobotReport(Tracker.getRobotReport request,
                             StreamObserver<Tracker.robotReportResponse> responseObserver){

        int _robotID = 0;
        if(request.getKan().equals("my RobotReport")){

            // Necessary fields to set gotten values into robotReport type so it goes into a robot report hashMap
            _robotID = request.getRobotID();
            Pose _pose = new Pose(request.getX(), request.getY(),request.getZ(), request.getRoll(),request.getPitch(),request.getYaw());
            RobotReport rR = new RobotReport(_robotID, _pose, request.getPathIndex(),request.getVelocity(),request.getDistanceTraveled(),request.getCriticalPoint());

            robotIDtoRobotReport.put(_robotID,rR);

        }

        System.out.println("RobotReports ->>>  " + robotIDtoRobotReport + "\n");
        respondToRobotReport(responseObserver);
    }

    public int requestID = 0;
    public void coordinatorrobotreport(Tracker.coordinatorrequestrobotid request,
                                       StreamObserver<Tracker.coordinatorrespondrobotreport> responseObserver){
        System.out.println("handling [" + request + "]"+ " endpoint: [" + request.toString() + "]");

        if(request.getReq().equals("requestrobotreport")){

            requestID= request.getRobotid();
        }
        System.out.println("Responding to robotrequest, the requested ID was: " + requestID);

        coordinatorresponserobotreport(responseObserver);
    }

    private void coordinatorresponserobotreport(StreamObserver<Tracker.coordinatorrespondrobotreport> responseObserver) {


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


        Tracker.coordinatorrespondrobotreport response =
                Tracker.coordinatorrespondrobotreport.newBuilder().setX(_x).setY(_y).setZ(_z)
                        .setRoll(_roll).setPitch(_pitch).setYaw(_yaw)
                        .setPathIndex(_pathindex).setVelocity(_velocity).setDistanceTraveled(_distancetraveled).setCriticalPoint(_criticalpoint).build();

        System.out.println("[FleetClientService] ... coordinatorresponse" + response);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }




    //generic response to stub
    private void respondToRobotReport(StreamObserver<Tracker.robotReportResponse> responseObserver) {
        Tracker.robotReportResponse response =
                Tracker.robotReportResponse.newBuilder()
                        .setName(text + "&id received" ).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    //Generic response to stub
    private void respondWithSendingIDreceived(StreamObserver<Tracker.robotID> responseObserver) {
        Tracker.robotID response =
                Tracker.robotID.newBuilder()
                        .setName(text + "&id received" ).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    // Generic response to stub
    private void respondWithSendingMsg(StreamObserver<Tracker.responseMessage> responseObserver) {
        Tracker.responseMessage response =
                Tracker.responseMessage.newBuilder()
                        .setName(text + " " + it).build();
        System.out.println("Responding with -> " + response.getName() + " !The # of clients is: " + ID_list.size());

        it++;
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    public void trackercriticalpoint(Tracker.trackerGetCriticalPointRequestMessage request,
                                           StreamObserver<Tracker.trackerGetCriticalPointResponseMessage> responseObserver){

        AbstractTrajectoryEnvelopeTracker tracker = new AbstractTrajectoryEnvelopeTracker() {
            @Override
            protected void onTrajectoryEnvelopeUpdate(TrajectoryEnvelope te) {}
            @Override
            protected void setCriticalPoint(int criticalPoint) {}
            @Override
            public RobotReport getRobotReport() { return null; }
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


    public static int trackerCriticalPointResponse(StreamObserver<Tracker.trackerGetCriticalPointResponseMessage> responseObserver, int criticalPoint) {

        Tracker.trackerGetCriticalPointResponseMessage response=
                Tracker.trackerGetCriticalPointResponseMessage.newBuilder().setCriticalPoint(criticalPoint).build();

        System.out.println("[TrackerService] Responding to criticalpoint request with criticalPoint response: " + criticalPointResponse);

        responseObserver.onNext(response);
        responseObserver.onCompleted();

        return criticalPointResponse;
    }
*/
}
