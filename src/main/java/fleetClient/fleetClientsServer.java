package fleetClient;

import java.io.IOException;

//import GRPC.FleetClientService;
import GRPC.FleetClientsServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;


public class fleetClientsServer {

    private static final int PORT = 50057;
    private Server server;

    /**
     * Create a new {@link AbstractTrajectoryEnvelopeTracker} to track a given {@link TrajectoryEnvelope},
     * with a given tracking period in a given temporal resolution. The tracker will post temporal constraints
     * to the given solver representing when the robot transitions from one sub-envelope to the next. An optional
     * callback function will be called at every period.
     *
     * @param te                     The {@link TrajectoryEnvelope} to track.
     * @param temporalResolution     The temporal unit of measure in which the period is represented.
     * @param tec
     * @param trackingPeriodInMillis The tracking period.
     * @param cb                     An optional callback function.
     */


    public void start() throws IOException {

        server = ServerBuilder.forPort(PORT)
                .addService(new FleetClientsServiceImpl())
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

    public static void main(String[] args)
            throws InterruptedException, IOException {
        fleetClientsServer server = new fleetClientsServer();
        server.start();

        server.blockUntilShutdown();
    }


}

