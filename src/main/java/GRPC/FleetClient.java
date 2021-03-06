package GRPC;

import aima.core.util.datastructure.Pair;


import io.grpc.Channel;
import io.grpc.ManagedChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.hellos.Client;
import io.grpc.hellos.fleetClientGrpc;
import org.metacsp.multi.spatioTemporal.paths.Pose;


public class FleetClient {

    private static final Logger logger = Logger.getLogger(FleetClient.class.getName());
    private final fleetClientGrpc.fleetClientBlockingStub blockingStub;

    // object using in cord
    public GRPC.FleetClient FleetClient;

    public FleetClient(Channel channel){
        //blockingStub = HelloWorldServiceGrpc.newBlockingStub(channel);
        blockingStub = fleetClientGrpc.newBlockingStub(channel);
    }

    public void makeGreeting(String name){

        Client.requestMessage request = Client.requestMessage.newBuilder().setName(name).build();
        Client.responseMessage response;

        try
        {
            //System.out.println("making greeting with : " + name);
            response = blockingStub.message(request);
        }
        catch(StatusRuntimeException e){
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());
            return;
        }
        System.out.println("Logging the response of the server ...");
        System.out.println(" Response is: "+ response.getName());
    }

    public void makeGreeting2(Pair<String,Integer> pair){

        Client.getRobotID getrobotid = Client.getRobotID.newBuilder().setKey(pair.getFirst()).setValue(pair.getSecond()).build();
        Client.robotID robotid;

        try
        {
            //System.out.println("making greeting with: " + pair);
            robotid = blockingStub.grobotID(getrobotid);
        }
        catch(StatusRuntimeException e){
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());
            return;
        }
        System.out.println("Logging the response of the server ...");
    }


    public void makeRobotReport(String my_robotReport, int robotid, double x, double y, double z, double roll, double pitch, double yaw, double velocity, int pathIndex, double distanceTraveled, int criticalPoint){
        Client.getRobotReport getRR = Client.getRobotReport.newBuilder().setKan(my_robotReport).setRobotID(robotid).setX(x).setY(y).setZ(z).setRoll(roll).setPitch(pitch).setYaw(yaw).setVelocity(velocity).setPathIndex(pathIndex).setDistanceTraveled(distanceTraveled).setCriticalPoint(criticalPoint).build();
        Client.robotReportResponse robotRsp;


        try
        {
            System.out.println("sending robotReport pathindex " + pathIndex );
            robotRsp = blockingStub.grobotReport(getRR);
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
            FleetClient client = new FleetClient(channel);
            client.makeGreeting((messageToSend));

        }
        finally{

            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }


}
