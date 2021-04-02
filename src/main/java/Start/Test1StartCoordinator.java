package Start;

import java.io.IOException;
import java.util.Comparator;

import CoordinatorPackage.CoordinatorServer;
import Visualizer.util.BrowserVisualization;
import se.oru.coordination.coordination_oru.CriticalSection;
import se.oru.coordination.coordination_oru.NetworkConfiguration;
import se.oru.coordination.coordination_oru.RobotAtCriticalSection;
import se.oru.coordination.coordination_oru.RobotReport;
import se.oru.coordination.coordination_oru.simulation2D.RemoteTrajectoryEnvelopeCoordinatorSimulation;

public class Test1StartCoordinator {

    public static void main(String[] args) throws InterruptedException {

        //1. INSTANTIATE THE COORDINATOR
        //Create a coordinator with interfaces to robots in the built-in 2D simulator
        //(FIXME we don't need to communicate the max acceleration and velocity, which will be passed while greeting).
        final RemoteTrajectoryEnvelopeCoordinatorSimulation tec = new RemoteTrajectoryEnvelopeCoordinatorSimulation();

        //Provide a heuristic (here, closest to critical section goes first)
        tec.addComparator(new Comparator<RobotAtCriticalSection> () {
            @Override
            public int compare(RobotAtCriticalSection o1, RobotAtCriticalSection o2) {
                CriticalSection cs = o1.getCriticalSection();
                RobotReport robotReport1 = o1.getRobotReport();
                RobotReport robotReport2 = o2.getRobotReport();
                return ((cs.getTe1Start()-robotReport1.getPathIndex())-(cs.getTe2Start()-robotReport2.getPathIndex()));
            }
        });

        //Define a network with uncertainties (see Mannucci et al., 2019)
        NetworkConfiguration.setDelays(0,0);
        NetworkConfiguration.PROBABILITY_OF_PACKET_LOSS = 0.1;

        //Tell the coordinator
        //FIXME These will be customized later
        // (1) what is known about the communication channel, and
        // (2) the accepted probability of constraint violation
        tec.setNetworkParameters(NetworkConfiguration.PROBABILITY_OF_PACKET_LOSS, NetworkConfiguration.getMaximumTxDelay(), 0.01);

        //Set up infrastructure that maintains the representation
        tec.setupSolver(0, 100000000);

        //Start the thread that revises precedences at every period
        tec.startInference();

        //Avoid deadlocks via global re-ordering
        tec.setBreakDeadlocks(true, false, false);

        //Start a visualization (will open a new browser tab)
        //FIXME This may run on a different PC in the future
        BrowserVisualization viz = new BrowserVisualization();
        viz.setInitialTransform(49, 5, 0);
        tec.setVisualization(viz);

        //Note: we need to pass and read a file containing the sequence of goals or missions to robots.
        //see the {@Missions} class.

/*        //2. INSTANTIATE THE COORDINATIONSERVER
        CoordinatorServer server = new CoordinatorServer(tec);
        try {
            server.start();
            server.blockUntilShutdown();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //3. SET THE SERVER IN THE COORDINATOR (now both the classes can access each other members)
        tec.setCoordinatorServer(server);*/

        //4. WAIT FOR ROBOT'S GREETING (Let's assume that all robots are greetings before the simulation runs for now)

        //
        //Start dispatching threads for each robot, each of which
        //dispatches the next mission as soon as the robot is idle
        //Missions.startMissionDispatchers(tec, robotIDs);

    }

}
