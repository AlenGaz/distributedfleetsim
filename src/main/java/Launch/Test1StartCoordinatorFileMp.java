package Launch;

import java.io.IOException;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import GRPC.CoordinatorServiceImpl;
import se.oru.coordination.coordination_oru.util.BrowserVisualization;
import se.oru.coordination.coordination_oru.util.Missions;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import se.oru.coordination.coordination_oru.*;
import CoordinatorPackage.RemoteTrajectoryEnvelopeCoordinatorSimulation;

public class Test1StartCoordinatorFileMp {


    /**
     * A test class based upon the TestTrajectoryEnvelopeCoordinatorThreeRobots class,
     * this one does not require sending the motion-planning stuff
     * */



    public static void main(String[] args) throws InterruptedException {

        // Necessary server stuff
        final int PORT = 50051;
        Server server = null;
        CoordinatorServiceImpl singleInstance = null;
        // . . . . . . . . . . . . . . . . . . . . .


        //1. INSTANTIATE THE COORDINATOR
        //Create a coordinator with interfaces to robots in the built-in 2D simulator
        //(FIXME we don't need to communicate the max acceleration and velocity, which will be passed while greeting).
        final RemoteTrajectoryEnvelopeCoordinatorSimulation tec = new RemoteTrajectoryEnvelopeCoordinatorSimulation();

        // V Below to make a setup in AbstractTrajectoryEnvelopeCoordinator so it has instance of the service implemnent..
        CoordinatorServiceImpl coordinatorServiceImpl = new CoordinatorServiceImpl(tec);
        tec.setupCoordinationServer(coordinatorServiceImpl);

        //Provide a heuristic (here, closest to critical section goes first)
        tec.addComparator(new Comparator<RobotAtCriticalSection>() {
            @Override
            public int compare(RobotAtCriticalSection o1, RobotAtCriticalSection o2) {
                CriticalSection cs = o1.getCriticalSection();
                RobotReport robotReport1 = o1.getRobotReport();
                RobotReport robotReport2 = o2.getRobotReport();
                return ((cs.getTe1Start() - robotReport1.getPathIndex()) - (cs.getTe2Start() - robotReport2.getPathIndex()));
            }
        });

        //Define a network with uncertainties (see Mannucci et al., 2019)
        NetworkConfiguration.setDelays(0, 0);
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



        //Example of how you can add extra info Strings to the visualization of robot status
        TrackingCallback cb = new TrackingCallback(null) {

            @Override
            public void onTrackingStart() { }

            @Override
            public void onTrackingFinished() { }

            @Override
            public String[] onPositionUpdate() {
                return new String[] {"a","b","c"};
            }

            @Override
            public void onNewGroundEnvelope() { }

            @Override
            public void beforeTrackingStart() { }

            @Override
            public void beforeTrackingFinished() { }
        };
        tec.addTrackingCallback(1, cb);
        //tec.addTrackingCallback(2, cb);
        //tec.addTrackingCallback(3, cb);



        //Note: we need to pass and read a file containing the sequence of goals or missions to robots.
        //see the {@Missions} class.


        //2. INSTANTIATE THE COORDINATIONSERVER

	/*
		CoordinatorServer server = new CoordinatorServer(tec); <<--- CoordServiceImplementation
        try {
			server.start();
			server.blockUntilShutdown();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
        //2. INSTANTIATE THE COORDINATIONSERVER
        try {
            server = ServerBuilder.forPort(PORT)
                    .addService(coordinatorServiceImpl)
                    .build()
                    .start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server started at port: " + server.getPort());


        //3. SET THE SERVER IN THE COORDINATOR (now both the classes can access each other members)
        // Done with setupCoordinationServer(coordinatorServiceImpl); line 40

        //4. WAIT FOR ROBOT'S GREETING (Let's assume that all robots are greetings before the simulation runs for now)


        //
        //Start dispatching threads for each robot, each of which


        // reading from the file for testing three robots
        Missions.loadLocationAndPathData("paths/test_poses_and_path_data.txt");

        //System.out.println("In before robotID forloop");


        //int[] robotIDs = new int[] {22,7,54,13,1,14};
        int[] robotIDs = new int[] {1};

        //System.out.println("Ha");
        //for (int robotID : robotIDs) {

            //set the forward model for every robot we have gotten greeting from
            tec.setForwardModel(1, new ConstantAccelerationForwardModel(1, 2
                                 ,tec.getTemporalResolution(), tec.getControlPeriod(), tec.getRobotTrackingPeriodInMillis(1)));

            System.out.println("HEJ HEJ HEJ HEJ HEJ HEJ HEJ HEJ ");


            tec.placeRobot(1, Missions.getLocation("r1p"));
            Missions.enqueueMission(new Mission(1, Missions.getShortestPath("r1p", "dest1")));
            Missions.enqueueMission(new Mission(1, Missions.getShortestPath("dest1", "r1p")));


            // placing each robot
/*            switch (robotID) {
                case 1:
                    System.out.println("In switch 1" + robotID);
                    tec.placeRobot(robotID, Missions.getLocation("r1p"));
                    Missions.enqueueMission(new Mission(1, Missions.getShortestPath("r1p", "dest1")));
                    Missions.enqueueMission(new Mission(1, Missions.getShortestPath("dest1", "r1p")));
                case 2:
                    System.out.println("In switch 2");
                    tec.placeRobot(robotID, Missions.getLocation("r2p"));
                    Missions.enqueueMission(new Mission(2, Missions.getShortestPath("r2p", "dest2")));
                    Missions.enqueueMission(new Mission(2, Missions.getShortestPath("dest2", "r2p")));
                case 3:
                    System.out.println("In switch 3");
                    tec.placeRobot(robotID, Missions.getLocation("r3p"));
                    Missions.enqueueMission(new Mission(3, Missions.getShortestPath("r3p", "dest3")));
                    Missions.enqueueMission(new Mission(3, Missions.getShortestPath("dest3", "r3p")));
            }*/

            //System.out.println("robotID: " + robotID);
            System.out.println("------------------------------------------------------------------------------------------------");

            Thread t = new Thread() {
                int iteration = 0;

                @Override
                public void run() {
                    while (true) {

                        // Mission to dispatch alternates between (rip -> desti) and (desti -> rip)
                        Mission m = Missions.getMission(1, iteration % 2);
                        synchronized (tec) {
                            // addMission returns true iff the robot was free to accept a new mission
                            if (tec.addMissions(m)) iteration++;


                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("robotIDtoClientConnection Keyset: " + coordinatorServiceImpl.robotIDtoClientConnection.keySet());
                            System.out.println("[Test1StartCoordinator] robotIDtoClientConnection Keyset: " + coordinatorServiceImpl.robotIDtoClientConnection.keySet());
                            System.out.println("[Test1StartCoordinator] robotIDtoClientConnection Type: " + coordinatorServiceImpl.robotIDtoClientConnection.get(1).getTimeStamp());

                            // This below is just test to see if the instance of the CoordinatorServiceImpl is the same in the server and Abstract..Coordinator
                            if (tec.coordinatorServicImpl.robotIDtoClientConnection.containsKey(1)) {

                                tec.testCoordReference();
                            }

                        }


                        //dispatches the next mission as soon as the robot is idle
                        //Missions.startMissionDispatchers(tec, robotIDs);

                    }
                }
            };
            // Start the thread!
            t.start();
       // }
    }
}




