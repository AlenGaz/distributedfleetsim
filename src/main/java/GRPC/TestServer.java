package GRPC;

import io.grpc.Server;
import io.grpc.ServerBuilder;
//import io.grpc.hellos.fleetClientGrpc;


import java.io.IOException;

public class TestServer {
    private static final int PORT = 50059;
    private Server server;

    public void start() throws IOException {
       /* server = ServerBuilder.forPort(PORT)
                .addService(new FleetClientService() )
                .build()
                .start();
        System.out.println("Server started at port: " + server.getPort());*/
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server == null) {
            return;
        }

        server.awaitTermination();
    }

    public static void main(String[] args)
            throws InterruptedException, IOException {
        TestServer server = new TestServer();
        server.start();

        server.blockUntilShutdown();
    }
}