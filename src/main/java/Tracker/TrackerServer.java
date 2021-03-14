package Tracker;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import Coordinator.AbstractTrajectoryEnvelopeCoordinator;
import Coordinator.CoordinatorServer;
import GRPC.FleetClientService;
import aima.core.util.datastructure.Pair;
import fleetClient.FleetClient;
import io.grpc.*;
import io.grpc.hellos.Tracker;
import io.grpc.hellos.TrackerServiceGrpc;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import se.oru.coordination.coordination_oru.TrackingCallback;


public class TrackerServer {

    private final TrackerServiceGrpc.TrackerServiceBlockingStub blockingStub;
    private static final Logger logger = Logger.getLogger(FleetClient.class.getName());
    private static final int PORT = 50051;
    private Server server;

    public TrackerServer(Channel channel) {
        //blockingStub = HelloWorldServiceGrpc.newBlockingStub(channel);
        blockingStub = TrackerServiceGrpc.newBlockingStub(channel);
    }

    public void start() throws IOException {

        ///hWs = new HelloWorldServiceImpl();

        server = ServerBuilder.forPort(PORT)
                .addService(new FleetClientService())
                .build()
                .start();
        System.out.println("Server started at port: " + server.getPort());

        ///HelloWorldServiceImpl hWs = new HelloWorldServiceImpl();
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server == null) {
            return;
        }

        server.awaitTermination();
    }

    public Integer requestCriticalPoint (int criticalPoint) {

        Tracker.getCriticalPointRequestMessage request = Tracker.getCriticalPointRequestMessage.newBuilder().setCriticalPoint(criticalPoint).build();
        Tracker.getCriticalPointResponseMessage response = null;

        try {
            response = blockingStub.gCriticalPoint(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());
            //return;
        }
        System.out.println("Logging the response of the server ...");

        return response.getCriticalPoint();
    }


/*    public static void main(String[] args)
            throws InterruptedException, IOException {
        TrackerServer server = new TrackerServer();
        server.start();

        server.blockUntilShutdown();
    }*/

    public static void main(String[] args) throws InterruptedException {

        String messageToSend;
        String target = "localhost:50051";


        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();

        try {
            //FleetClient client = new FleetClient(channel);
            //client.makeGreeting3((messageToSend));

            //test();

        }catch (StatusRuntimeException e) {
            System.out.println("Could not connect");
            return;

        } finally {

            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }


}

