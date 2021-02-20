package GRPC;

import io.grpc.hellos.Hello;
import io.grpc.hellos.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class HelloWorldServiceImpl


        extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {


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
            ID_list.add(request.getValue()); ////// if id not in list, append
            System.out.println("The ID_list: " + ID_list);

            respondWithSendingIDreceived(responseObserver);
        }

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