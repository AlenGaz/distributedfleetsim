package Tracker;

import java.io.IOException;

import Coordinator.AbstractTrajectoryEnvelopeCoordinator;
import Coordinator.CoordinatorServer;
import GRPC.FleetClientService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import se.oru.coordination.coordination_oru.TrackingCallback;


public class TrackerServer extends AbstractTrajectoryEnvelopeTracker {

    private static final int PORT = 50051;
    private Server server;

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

    public static void main(String[] args)
            throws InterruptedException, IOException {
        TrackerServer server = new TrackerServer();
        server.start();

        server.blockUntilShutdown();
    }
}

