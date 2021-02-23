package GRPC;

import aima.core.util.datastructure.Triplet;
import io.grpc.hellos.Hello;
import io.grpc.hellos.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import se.oru.coordination.coordination_oru.simulation2D.TrajectoryEnvelopeCoordinatorSimulation;

import java.util.ArrayList;
import java.util.HashMap;

public class HelloWorldServiceImpl
        extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {


    public int it = 0;
    String text;
    public ArrayList ID_list = new ArrayList();
    public int x;


    public HashMap<Integer, Triplet<Double, Double, Double>> robotIDToPosXYZ = new HashMap<Integer, Triplet<Double, Double, Double>>();
    public HashMap<Integer, Double> robotIDtoVelocity = new HashMap<Integer, Double>();


    @Override
    public void hello(
            Hello.HelloRequest request,
            StreamObserver<Hello.HelloResponse> responseObserver) {
        System.out.println("handling [" + request.getName() + "]"+ " endpoint: [" + request.toString() + "]");


        if(request.getName().equals("Hello")){

            text = request.getName() + " World";
            respondWithSendingMsg(responseObserver);
        }

        if(request.getName().equals("Boss")) {
            text = "Hello big" + request.getName();

            respondWithSendingMsg(responseObserver);
        }

        if(request.getName().equals("my RobotReport")){
            System.out.println("in hello override ");

        }

    }

    @Override
    public void grobotID(
            Hello.getRobotID request,
            StreamObserver<Hello.robotID> responseObserver) {
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

 @Override
 public void grobotReport(Hello.getRobotReport request,
                          StreamObserver<Hello.robotReportResponse> responseObserver){


        double rahman = 2.3;

       // System.out.println("Here in gRobotReport; \n" + request + " !");
        if(request.getKan().equals("my RobotReport")){


            System.out.println("Got robotID: " + request.getRobotID());
            System.out.println("Got posx: " + request.getX());
            System.out.println("Got posy: " + request.getY());
            System.out.println("Got posz: " + request.getZ());
            System.out.println("Got roll: " + request.getRoll());
            System.out.println("Got pitch: " + request.getPitch());
            System.out.println("Got yaw: " + request.getYaw());
            System.out.println("Got velocity: " + request.getVelocity());
            System.out.println("Got pathIndex: " + request.getPathIndex());
            System.out.println("Got distTraveled: " + request.getDistanceTraveled());
            System.out.println("Got criticalPoint: " + request.getCriticalPoint()+"\n");

            int _robotID = request.getRobotID();
            Triplet <Double, Double, Double> posTriplet = new Triplet<Double, Double, Double>(request.getX(), request.getY(), request.getZ());
            robotIDToPosXYZ.put(_robotID,posTriplet);
            robotIDtoVelocity.put(_robotID, request.getVelocity());


        }
        System.out.println("Positions (x,y,z) HashMap: " + robotIDToPosXYZ + "\n");
        System.out.println("Velocity HashMap" + robotIDtoVelocity + "\n");
 }
    private void respondToRobotReport(StreamObserver<Hello.getRobotReport> responseObserver) {
        Hello.getRobotReport response =
                Hello.getRobotReport.newBuilder()
                        .setKan(text + "&id received" ).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    private void respondWithSendingIDreceived(StreamObserver<Hello.robotID> responseObserver) {
        Hello.robotID response =
                Hello.robotID.newBuilder()
                        .setName(text + "&id received" ).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private void respondWithSendingMsg(StreamObserver<Hello.HelloResponse> responseObserver) {
        Hello.HelloResponse response =
                Hello.HelloResponse.newBuilder()
                        .setName(text + " " + it).build();
        System.out.println("Responding with -> " + response.getName() + " !The # of clients is: " + ID_list.size());
        //System.out.println("all current clients (robot ID:s) ; " +Arrays.toString(ID_list.toArray()));


        it++;
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("Size of RobotIDTOPOSXYZ hashmap" + robotIDToPosXYZ.size());
    }
}