package Launch;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import CoordinatorPackage.containers.MakeFootPrint;
import GRPC.CoordinatorServiceImpl;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;
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

		//Note: we need to pass and read a file containing the sequence of goals or missions to robots.
		//see the {@Missions} class.




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
		//Start dispatching each robot, each of which


		boolean dispatched = false;
		HashMap<Integer, Boolean> dispatchedRobot = new HashMap<Integer, Boolean>();


		//FIXME This may run on a different PC in the future
		BrowserVisualization viz = new BrowserVisualization();
		viz.setInitialTransform(10, 35, 20);
		tec.setVisualization(viz);

		while (true) {

			TimeUnit.SECONDS.sleep(2);
			System.out.println("[Test1StartCoordinator] robotIDtoClientConnection Keyset: " + coordinatorServiceImpl.robotIDtoClientConnection.keySet());
			// just a test of dispatching some robots
			for(int i = 0; i <= coordinatorServiceImpl.robotIDtoClientConnection.size(); i++) {


				if (tec.coordinatorServicImpl.robotIDtoClientConnection.containsKey(i)) {
					if(!dispatchedRobot.containsKey(i)) {


						TimeUnit.MILLISECONDS.sleep(200);

						PoseSteering[] path = coordinatorServiceImpl.robotIDtoClientConnection.get(i).getPoseSteerings();

						ReedsSheppCarPlanner rsp = new ReedsSheppCarPlanner();
						rsp.setStart(coordinatorServiceImpl.robotIDtoClientConnection.get(i).getStartPose());
						rsp.setGoals(coordinatorServiceImpl.robotIDtoClientConnection.get(i).getEndPose());


						//PoseSteering[] path = rsp.getPath();
						//PoseSteering[] pathInv = rsp.getPathInv();

						//if(!tec.isFree(i)){ return; }

						Mission m = new Mission(i, path);
						//Mission mInv = new Mission(i, pathInv);
						Missions.enqueueMission(m);
						//Missions.enqueueMission(mInv);



						dispatchedRobot.put(i, true);

						tec.placeRobot(i, coordinatorServiceImpl.robotIDtoClientConnection.get(i).getStartPose());

						System.out.println("[StartCoordinator] dispatching robot .. " + i + " with path length" + m.getPath().length);
						System.out.println("[StartCoordinator] isFree: " + tec.isFree(i));

							Missions.startMissionDispatchers(tec, true, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

							//TimeUnit.MILLISECONDS.sleep(50);

					}
				}
			}
		}
	}
}
