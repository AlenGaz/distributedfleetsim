package GRPC;


import aima.core.util.datastructure.Triplet;
import io.grpc.hellos.Client;
import io.grpc.hellos.fleetClientGrpc;
import io.grpc.stub.StreamObserver;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import se.oru.coordination.coordination_oru.RobotReport;
import se.oru.coordination.coordination_oru.clientConnection;
import se.oru.coordination.coordination_oru.simulation2D.TrajectoryEnvelopeCoordinatorSimulation;


import java.util.ArrayList;
import java.util.HashMap;



public final class FleetClientService extends fleetClientGrpc.fleetClientImplBase {


    public int it = 0;
    String text;
    public ArrayList ID_list = new ArrayList();
    public static HashMap <Integer, clientConnection> clientConnectionInfos = new HashMap <Integer, clientConnection>(); // contains ID, type, ip&port, timestamp
    public static ConcurrentHashMap<Integer, RobotReport> robotIDtoRobotReport = new ConcurrentHashMap<Integer, RobotReport>();


    //@Override
    public void grobotID(
            Client.getRobotID request,
            StreamObserver<Client.robotID> responseObserver) {
        //System.out.println("handling [" + request + "]"+ " endpoint: [" + request.toString() + "]");

        if(request.getKey().equals("My ID")) {

            //System.out.println("got Robot id: " + request.getValue());
            //System.out.println("got Robot type: " + request.getType());
            //System.out.println("got Robot connectionIP: " + request.getConnection());
            //System.out.println("got Robot Timestamp: " + request.getTimeStamp());

            clientConnection cC = new clientConnection(request.getType(),request.getConnection(),request.getTimeStamp());
            clientConnectionInfos.put(request.getValue(), cC);
            //System.out.println("Current clientconnections: " + clientConnectionInfos);

            if(!ID_list.contains(request.getValue())){
                ID_list.add(request.getValue()); ////// if id not in list, append

            }

            TrajectoryEnvelopeCoordinatorSimulation tes = new TrajectoryEnvelopeCoordinatorSimulation();
            //System.out.println("Current ID's connected: " + ID_list);
            // System.out.println("Current clientConnections: " + clientConnectionInfos); The client Connection infos are not displaying correct

            respondWithSendingIDreceived(responseObserver);

        }

    }

    //@Override
    public void grobotReport(Client.getRobotReport request,
                             StreamObserver<Client.robotReportResponse> responseObserver){

        int _robotID = 0;
        if(request.getKan().equals("my RobotReport")){

            // Necessary fields to set gotten values into robotReport type so it goes into a robot report hashMap
            _robotID = request.getRobotID();
            Pose _pose = new Pose(request.getX(), request.getY(),request.getZ(), request.getRoll(),request.getPitch(),request.getYaw());
            RobotReport rR = new RobotReport(_robotID, _pose, request.getPathIndex(),request.getVelocity(),request.getDistanceTraveled(),request.getCriticalPoint());

            robotIDtoRobotReport.put(_robotID,rR);

        }

        //System.out.println("RobotReports ->>>  " + robotIDtoRobotReport+ "\n");
        respondToRobotReport(responseObserver);
    }

    private void respondToRobotReport(StreamObserver<Client.robotReportResponse> responseObserver) {
        Client.robotReportResponse response =
                Client.robotReportResponse.newBuilder()
                        .setName(text + "&id received" ).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    private void respondWithSendingIDreceived(StreamObserver<Client.robotID> responseObserver) {
        Client.robotID response =
                Client.robotID.newBuilder()
                        .setName(text + "&id received" ).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    } //Generic response to stub


    private void respondWithSendingMsg(StreamObserver<Client.responseMessage> responseObserver) {
        Client.responseMessage response =
                Client.responseMessage.newBuilder()
                        .setName(text + " " + it).build();
        System.out.println("Responding with -> " + response.getName() + " !The # of clients is: " + ID_list.size());

        it++;
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    } // Generic response to stub


    // Doesn't work
    /*public static HashMap<Integer, RobotReport> robotReportHashFunction(){
        System.out.println("returning robotReportHashFunction");
        //return robotIDtoRobotReport;
    }*/
}