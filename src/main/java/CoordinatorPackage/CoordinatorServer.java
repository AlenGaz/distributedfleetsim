package CoordinatorPackage;

import java.io.IOException;
import java.util.HashSet;

import GRPC.CoordinatorServiceImpl;
import fleetClient.AbstractTrajectoryEnvelopeTracker;
import io.grpc.*;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import se.oru.coordination.coordination_oru.Dependency;
import se.oru.coordination.coordination_oru.TrackingCallback;
import se.oru.coordination.coordination_oru.simulation2D.RemoteTrajectoryEnvelopeCoordinatorSimulation;


public class CoordinatorServer {


    private static final int PORT = 50051;
    private Server server;

    public CoordinatorServer(Channel channel) {
    }

    public CoordinatorServer() {

    }


    public void start() throws IOException {



        server = ServerBuilder.forPort(PORT)
                .addService(new CoordinatorServiceImpl(new RemoteTrajectoryEnvelopeCoordinatorSimulation(2000, 1000, 3.0, 4.0)))
                .build()
                .start();
        System.out.println("Server started at port: " + server.getPort());



    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server == null) {
            return;
        }

        server.awaitTermination();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        CoordinatorServer server = new CoordinatorServer();
        server.start();


        server.blockUntilShutdown();

    }

}
