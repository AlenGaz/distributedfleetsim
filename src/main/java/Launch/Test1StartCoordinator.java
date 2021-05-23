package Launch;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	public static final RemoteTrajectoryEnvelopeCoordinatorSimulation tec = new RemoteTrajectoryEnvelopeCoordinatorSimulation();


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

	}



	public static void serverDispatchRobot(Set<Integer> IDkeyset,int robotID, Pose startPose, Pose endPose, double maxAcceleration, double maxVelocity, Coordinate[] footprint, double trackingPeriodInMillis, PoseSteering[] poseSteerings) {



			RemoteTrajectoryEnvelopeCoordinatorSimulation tec = Test1StartCoordinator.tec;

			ReedsSheppCarPlanner rsp = new ReedsSheppCarPlanner();
			//rsp.setStart(coordinatorServiceImpl.robotIDtoClientConnection.get(i).getStartPose());
			rsp.setStart(startPose);
			rsp.setGoals(endPose);
			Coordinate[] fp = footprint;
			rsp.setFootprint(fp);
			rsp.setRadius(1.0);
			rsp.setTurningRadius(20);
			rsp.setDistanceBetweenPathPoints(2);
			rsp.setFootprint(fp);

			tec.setFootprint(robotID,fp);


			PoseSteering[] path = poseSteerings;


			tec.setMotionPlanner(robotID, rsp);




			tec.setForwardModel(robotID, new ConstantAccelerationForwardModel(maxAcceleration,
				maxVelocity, tec.getTemporalResolution(), tec.getControlPeriod(), (int) trackingPeriodInMillis));


			Mission m = new Mission(robotID, path);
			Missions.enqueueMission(m);


			//dispatchedRobot.put(i, true);



			tec.placeRobot(robotID, startPose);

			System.out.println("[StartCoordinator] dispatching robot .. " + robotID + " with path length" + m.getPath().length);
			System.out.println("[StartCoordinator] isFree: " + tec.isFree(robotID));



				for (int id : IDkeyset) {
					Missions.startMissionDispatchers(tec, true, id);
				}
				tec.setBreakDeadlocks(true, false, true);


		}
	}

