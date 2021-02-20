package GRPC;

import aima.core.util.datastructure.Pair;
import io.grpc.Channel;
import io.grpc.ManagedChannel;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.hellos.Hello;
import io.grpc.hellos.HelloWorldServiceGrpc;


public class HelloWorldClient {

    private static final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());
    private final HelloWorldServiceGrpc.HelloWorldServiceBlockingStub blockingStub;

    // object using in cord
    public GRPC.HelloWorldClient HelloWorldClient;

    public HelloWorldClient(Channel channel){
        //blockingStub = HelloWorldServiceGrpc.newBlockingStub(channel);
        blockingStub = HelloWorldServiceGrpc.newBlockingStub(channel);
    }

    public void makeGreeting(String name){

        Hello.HelloRequest request = Hello.HelloRequest.newBuilder().setName(name).build();
        Hello.HelloResponse response;

        try
        {
            System.out.println("making greeting with : " + name);
            response = blockingStub.hello(request);
        }
        catch(StatusRuntimeException e){
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());
            return;
        }
        System.out.println("Logging the response of the server ...");
        System.out.println(" Response is: "+ response.getName());
    }

    public void makeGreeting2(Pair<String,Integer> pair){

        Hello.getRobotID getrobotid = Hello.getRobotID.newBuilder().setKey(pair.getFirst()).setValue(pair.getSecond()).build();
        Hello.robotID robotid;

        try
        {
            System.out.println("making greeting with: " + pair);
            robotid = blockingStub.grobotID(getrobotid);
        }
        catch(StatusRuntimeException e){
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());
            return;
        }
        System.out.println("Logging the response of the server ...");
    }


    public static void main(String[] args) throws InterruptedException {

        String messageToSend;
        String target = "localhost:50051";


        Random rd = new Random();

        if(rd.nextFloat()> 0.5){
            messageToSend = "Boss";
        }
        else{
            messageToSend= "Hello";
        }

        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();

        try{
            HelloWorldClient client = new HelloWorldClient(channel);
            client.makeGreeting((messageToSend));

        }
        finally{

            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
