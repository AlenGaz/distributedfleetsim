package Launch;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
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

	private static Random rand = new Random(123213);


	private static Coordinate[] makeRandomFootprint(int centerX, int centerY, int minVerts, int maxVerts, double minRadius, double maxRadius) {
		// Split a full circle into numVerts step, this is how much to advance each part
		int numVerts = minVerts+rand.nextInt(maxVerts-minVerts);
		double angleStep = Math.PI * 2 / numVerts;
		Coordinate[] ret = new Coordinate[numVerts];
		for(int i = 0; i < numVerts; ++i) {
			double targetAngle = angleStep * i; // This is the angle we want if all parts are equally spaced
			double angle = targetAngle + (rand.nextDouble() - 0.5) * angleStep * 0.25; // add a random factor to the angle, which is +- 25% of the angle step
			double radius = minRadius + rand.nextDouble() * (maxRadius - minRadius); // make the radius random but within minRadius to maxRadius
			double x = Math.cos(angle) * radius;
			double y = Math.sin(angle) * radius;
			ret[i] = new Coordinate(x, y);
		}
		return ret;
	}

	public static void main(String[] args) throws InterruptedException {

		// Necessary server stuff . . . . . . . . . .
		final int PORT = 50051;
		//final int PORT = 7777;
		Server server = null;
		CoordinatorServiceImpl singleInstance = null;
		// . . . . . . . . . . . . . . . . . . . . .


		//1. INSTANTIATE THE COORDINATOR
		//Create a coordinator with interfaces to robots in the built-in 2D simulator
		//(FIXME we don't need to communicate the max acceleration and velocity, which will be passed while greeting).
		final RemoteTrajectoryEnvelopeCoordinatorSimulation tec = new RemoteTrajectoryEnvelopeCoordinatorSimulation();

		// V Below to make a setup in AbstractTrajectoryEnvelopeCoordinator so it has instance of the service implement..
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
		//fixme never tried removing this..
		tec.setBreakDeadlocks(true, true, true);

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



			// just a test of dispatching some robots
			for(int i = 0; i <= coordinatorServiceImpl.robotIDtoClientConnection.size(); i++) {
				// need to loop through every object in the hashmap, this causes the ids to must be sequential


				if (tec.coordinatorServicImpl.robotIDtoClientConnection.containsKey(i)) {
					if(!dispatchedRobot.containsKey(i)) {
				System.out.println("[Test1StartCoordinator] robotIDtoClientConnection Keyset: " + coordinatorServiceImpl.robotIDtoClientConnection.keySet());
						PoseSteering[] path = coordinatorServiceImpl.robotIDtoClientConnection.get(i).getPoseSteerings();

						ReedsSheppCarPlanner rsp = new ReedsSheppCarPlanner();
						rsp.setStart(coordinatorServiceImpl.robotIDtoClientConnection.get(i).getStartPose());
						rsp.setGoals(coordinatorServiceImpl.robotIDtoClientConnection.get(i).getEndPose());
						MakeFootPrint fp = coordinatorServiceImpl.robotIDtoClientConnection.get(i).getFootPrint();
						fp.getCenterX();
						Coordinate[] _fp = makeRandomFootprint(fp.getCenterX(), fp.getCenterY(),fp.getMinVerts(), fp.getMaxVerts(),fp.getMinRadius(), fp.getMaxRadius());
						rsp.setFootprint(_fp);



						//PoseSteering[] path = rsp.getPath();
						//PoseSteering[] pathInv = rsp.getPathInv();

						//if(!tec.isFree(i)){ return; }

						tec.setForwardModel(i, new ConstantAccelerationForwardModel(coordinatorServiceImpl.robotIDtoClientConnection.get(i).getMaxAccel(),
								coordinatorServiceImpl.robotIDtoClientConnection.get(i).getMaxVel(), tec.getTemporalResolution(), tec.getControlPeriod(), (int) coordinatorServiceImpl.robotIDtoClientConnection.get(i).getTrackingPeriodInMillis()));

						Mission m = new Mission(i, path);
						//Mission mInv = new Mission(i, pathInv);
						Missions.enqueueMission(m);
						//Missions.enqueueMission(mInv);



						dispatchedRobot.put(i, true);

						//tec.placeRobot(i, coordinatorServiceImpl.robotIDtoClientConnection.get(i).getStartPose(), );

						tec.placeRobot(i, coordinatorServiceImpl.robotIDtoClientConnection.get(i).getStartPose());

						System.out.println("[StartCoordinator] dispatching robot .. " + i + " with path length" + m.getPath().length);
						System.out.println("[StartCoordinator] isFree: " + tec.isFree(i));


						// use number Of Robots to make the third parameter of start missions dispatchers


							Missions.startMissionDispatchers(tec, true, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
									11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30
									, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50
									, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70
									, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
									91, 92, 93, 94, 95, 96, 97, 98, 99,100,101,102,103,104,105,106,107,108,109,110,111,
									112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,
									134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155
									, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173);


					}
				}
			}
		}
	}
}
