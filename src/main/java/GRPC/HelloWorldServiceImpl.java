package GRPC;

import aima.core.util.datastructure.Triplet;
import io.grpc.hellos.Hello;
import io.grpc.hellos.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import se.oru.coordination.coordination_oru.RobotReport;
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
    public HashMap<Integer, RobotReport> robotIDtoRobotReport = new HashMap<Integer, RobotReport>();


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

<<<<<<< HEAD
      int _robotID = 0;
        
=======
>>>>>>> a0d4f5b60a3afbe4b88b6b72065cbd0cab2b811d
       // System.out.println("Here in gRobotReport; \n" + request + " !");
        if(request.getKan().equals("my RobotReport")){


            // Necessary fields to set gotten values into robotReport type so it goes into a robot report hashMap
            _robotID = request.getRobotID();
            Triplet <Double, Double, Double> posTriplet = new Triplet<Double, Double, Double>(request.getX(), request.getY(), request.getZ());
            Pose _pose = new Pose(request.getX(), request.getY(),request.getZ(), request.getRoll(),request.getPitch(),request.getYaw());
            RobotReport rR = new RobotReport(_robotID, _pose, request.getPathIndex(),request.getVelocity(),request.getDistanceTraveled(),request.getCriticalPoint());

            robotIDToPosXYZ.put(_robotID,posTriplet);
            robotIDtoVelocity.put(_robotID, request.getVelocity());
            robotIDtoRobotReport.put(_robotID, rR);
        }

        System.out.println("!!robotReport!just.!" + robotIDtoRobotReport+ "\n");


        respondToRobotReport(responseObserver);
 }
    private void respondToRobotReport(StreamObserver<Hello.robotReportResponse> responseObserver) {
        Hello.robotReportResponse response =
                Hello.robotReportResponse.newBuilder()
                        .setName(text + "&id received" ).build();
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
