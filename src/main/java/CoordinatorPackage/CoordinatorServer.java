/*
package CoordinatorPackage;

import java.io.IOException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Random;

import GRPC.CoordinatorServiceImpl;
import io.grpc.*;
import se.oru.coordination.coordination_oru.*;


public class CoordinatorServer {


    private static final int PORT = 50051;
    private Server server;
    private static CoordinatorServiceImpl singleInstance = null;

    public CoordinatorServer(Channel channel) {
    }

    public CoordinatorServer() {

    }



    public static CoordinatorServiceImpl getInstance(){

        if (singleInstance == null) {
            singleInstance = new CoordinatorServiceImpl();
        }
        return singleInstance;
    }

    public void start() throws IOException {
        final RemoteTrajectoryEnvelopeCoordinatorSimulation tec = new RemoteTrajectoryEnvelopeCoordinatorSimulation(2000, 1000, 3.0,4.0);

        tec.addComparator(new Comparator<RobotAtCriticalSection> () {
            @Override
            public int compare(RobotAtCriticalSection o1, RobotAtCriticalSection o2) {
                Random rand = new Random(Calendar.getInstance().getTimeInMillis());
                return rand.nextInt(2)-1;
            }
        });
        tec.addComparator(new Comparator<RobotAtCriticalSection>() {
            @Override
            public int compare(RobotAtCriticalSection o1, RobotAtCriticalSection o2) {
                CriticalSection cs = o1.getCriticalSection();
                RobotReport robotReport1 = o1.getRobotReport();
                RobotReport robotReport2 = o2.getRobotReport();
                return ((cs.getTe1Start()-robotReport1.getPathIndex())-(cs.getTe2Start()-robotReport2.getPathIndex()));
            }
        });

        tec.setUseInternalCriticalPoints(false);
        tec.setYieldIfParking(true);
        tec.setBreakDeadlocks(false, true, false);
        tec.setCheckCollisions(true);

        //Need to setup infrastructure that maintains the representation
        tec.setupSolver(0, 100000000);

        //Start the thread that checks and enforces dependencies at every clock tick
        tec.startInference();


        CoordinatorServiceImpl csi = new CoordinatorServiceImpl(tec);

        server = ServerBuilder.forPort(PORT)
                .addService(csi)
                .build()
                .start();
        tec.setupCoordinationServer(csi);
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

        CoordinatorServiceImpl singleInstance= getInstance();

        //   instantiation = new CoordinatorServiceImpl(new RemoteTrajectoryEnvelopeCoordinatorSimulation(2000, 1000, 3.0, 4.0)
        //   tec.startInference())


        //Instantiate a trajectory envelope coordinator.




        server.blockUntilShutdown();

    }

}
*/
