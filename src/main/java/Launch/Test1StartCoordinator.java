package Launch;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import CoordinatorPackage.containers.MakeFootPrint;
import GRPC.CoordinatorServiceImpl;
import com.vividsolutions.jts.geom.Coordinate;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import org.metacsp.multi.spatioTemporal.paths.PoseSteering;
import se.oru.coordination.coordination_oru.*;
import se.oru.coordination.coordination_oru.motionplanning.ompl.ReedsSheppCarPlanner;
import se.oru.coordination.coordination_oru.util.BrowserVisualization;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import CoordinatorPackage.RemoteTrajectoryEnvelopeCoordinatorSimulation;
import se.oru.coordination.coordination_oru.util.Missions;


public class Test1StartCoordinator {



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

		// V Below to make a setup in AbstractTrajectoryEnvelopeCoordinator so it has instance of the service implemnent..
		CoordinatorServiceImpl coordinatorServiceImpl = new CoordinatorServiceImpl(tec);
		tec.setupCoordinationServer(coordinatorServiceImpl);

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
		//tec.setBreakDeadlocks(true, false, false);

		//Note: we need to pass and read a file containing the sequence of goals or missions to robots.
		//see the {@Missions} class.


		//2. INSTANTIATE THE COORDINATIONSERVER


		//2. INSTANTIATE THE COORDINATIONSERVER
		try {
			server = ServerBuilder.forPort(PORT)
					.addService(coordinatorServiceImpl).maxInboundMessageSize(100000000)
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

		boolean dispatched = false;
		HashMap<Integer, Boolean> dispatchedRobot = new HashMap<Integer, Boolean>();
		while (true) {

			TimeUnit.SECONDS.sleep(1);
			System.out.println("[Test1StartCoordinator] robotIDtoClientConnection Keyset: " + coordinatorServiceImpl.robotIDtoClientConnection.keySet());


			// just a test of dispatching some robots
			if (tec.coordinatorServicImpl.robotIDtoClientConnection.containsKey(1) && !dispatched) {

				//FIXME This may run on a different PC in the future
				BrowserVisualization viz = new BrowserVisualization();
				viz.setInitialTransform(18, 35, 20);
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

				TimeUnit.SECONDS.sleep(2);

				Mission m = new Mission(1, coordinatorServiceImpl.robotIDtoClientConnection.get(1).getPoseSteerings());
				Missions.enqueueMission(m);
				dispatchedRobot.put(1, true);


				Pose testpose = new Pose(8, 8, 0 ,3 ,0 ,0);

				tec.placeRobot(1,coordinatorServiceImpl.robotIDtoClientConnection.get(1).getStartPose());
				tec.addMissions(m);


				Missions.startMissionDispatchers(tec, new int[]{1, 2, 3});


				dispatched = true;

			}

		}


	}
}

