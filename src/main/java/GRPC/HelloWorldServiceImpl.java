package GRPC;

import io.grpc.hellos.Hello;
import io.grpc.hellos.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import se.oru.coordination.coordination_oru.AbstractTrajectoryEnvelopeTracker;
import se.oru.coordination.coordination_oru.RobotReport;
import se.oru.coordination.coordination_oru.TrajectoryEnvelopeCoordinator;
import se.oru.coordination.coordination_oru.TrajectoryEnvelopeTrackerDummy;
import se.oru.coordination.coordination_oru.simulation2D.TrajectoryEnvelopeCoordinatorSimulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class HelloWorldServiceImpl


        extends HelloWorldServiceGrpc.HelloWorldServiceImplBase implements HelloWorldServiceImpls {


    public int it = 0;
    String text;
    public ArrayList ID_list = new ArrayList();
    public int x;



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

            System.out.println(tes.getRobotReport(request.getValue()));

            System.out.println("The ID_list: " + ID_list);
            System.out.println("HAAA "+ String.valueOf(tes.getRobotReport(request.getValue()).getPathIndex()));
            System.out.println("KAHHH" + tes.getRobotReport(request.getValue()).getPose().toString());
            respondWithSendingIDreceived(responseObserver);

        }

    }

 @Override
 public void grobotReport(Hello.getRobotReport request,
                          StreamObserver<Hello.robotID> responseObserver){

        if(request.getKan().equals("my robotReport")){

            System.out.println("Got robotID" + request.getRobotID());

            System.out.println("Got pathIndex" + request.getPathIndex());
            System.out.println("Got velocity" + request.getVelocity());
            System.out.println("Got distTraveled" + request.getDistanceTraveled());
            System.out.println("Got criticalPoint" + request.getCriticalPoint());

        }

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
    }
}