package GRPC;


import aima.core.util.datastructure.Triplet;
import io.grpc.hellos.Client;
import io.grpc.hellos.fleetClientGrpc;
import io.grpc.stub.StreamObserver;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import se.oru.coordination.coordination_oru.RobotReport;
import se.oru.coordination.coordination_oru.simulation2D.TrajectoryEnvelopeCoordinatorSimulation;

import java.util.ArrayList;
import java.util.HashMap;

public class FleetClientService extends fleetClientGrpc.fleetClientImplBase {


    public int it = 0;
    String text;
    public ArrayList ID_list = new ArrayList();
    public int x;

    public HashMap<Integer, RobotReport> robotIDtoRobotReport = new HashMap<Integer, RobotReport>();



    //@Override
    public void message(
            Client.requestMessage request,
            StreamObserver<Client.responseMessage> responseObserver) {
        System.out.println("handling [" + request.getName() + "]"+ " endpoint: [" + request.toString() + "]");


        if(request.getName().equals("Hello")){
            text = request.getName() + " World";
            respondWithSendingMsg(responseObserver);
        }

        if(request.getName().equals("Boss")) {
            text = "Hello big" + request.getName();
            respondWithSendingMsg(responseObserver);
        }

    }

    //@Override
    public void grobotID(
            Client.getRobotID request,
            StreamObserver<Client.robotID> responseObserver) {
        //System.out.println("handling [" + request.getKey() + "]"+ " endpoint: [" + request.toString() + "]");

        if(request.getKey().equals("My ID")) {
            //text = "Hello robot:" + request.getValue();
            System.out.println("got Robot id: " + request.getValue());
            if(!ID_list.contains(request.getValue())){
                ID_list.add(request.getValue()); ////// if id not in list, append
            }
            TrajectoryEnvelopeCoordinatorSimulation tes = new TrajectoryEnvelopeCoordinatorSimulation();

            System.out.println("Current ID's connected: " + ID_list);

            respondWithSendingIDreceived(responseObserver);

        }

    }

    //@Override
    public void grobotReport(Client.getRobotReport request,
                             StreamObserver<Client.robotReportResponse> responseObserver){
        int _robotID = 0;

        // System.out.println("Here in gRobotReport; \n" + request + " !");
        if(request.getKan().equals("my RobotReport")){


            // Necessary fields to set gotten values into robotReport type so it goes into a robot report hashMap
            _robotID = request.getRobotID();
            Triplet<Double, Double, Double> posTriplet = new Triplet<Double, Double, Double>(request.getX(), request.getY(), request.getZ());
            Pose _pose = new Pose(request.getX(), request.getY(),request.getZ(), request.getRoll(),request.getPitch(),request.getYaw());
            RobotReport rR = new RobotReport(_robotID, _pose, request.getPathIndex(),request.getVelocity(),request.getDistanceTraveled(),request.getCriticalPoint());
            //DataStorage.robotIDtoRobotReport.put(_robotID, rR);

            robotIDtoRobotReport.put(_robotID,rR);
        }

        System.out.println("robotIDtoRobotReport: " + robotIDtoRobotReport);
        //getrR(_robotID);

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
    }


    private void respondWithSendingMsg(StreamObserver<Client.responseMessage> responseObserver) {
        Client.responseMessage response =
                Client.responseMessage.newBuilder()
                        .setName(text + " " + it).build();
        System.out.println("Responding with -> " + response.getName() + " !The # of clients is: " + ID_list.size());
        //System.out.println("all current clients (robot ID:s) ; " +Arrays.toString(ID_list.toArray()));


        it++;
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }


    public RobotReport getrR(int robotID){

        System.out.println("Returning robotID" + robotIDtoRobotReport.get(robotID));
        return robotIDtoRobotReport.get(robotID);
    }

    public HashMap<Integer, RobotReport> robotReportHashFunction(){

        System.out.println("returning robotReportHashFunction");
        return robotIDtoRobotReport;
    }

}
