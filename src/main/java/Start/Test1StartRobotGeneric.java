package Start;

import Visualizer.util.Missions;
import Visualizer.util.Pair;
import com.vividsolutions.jts.geom.Coordinate;
import fleetClient.AbstractTrajectoryEnvelopeTracker;
import fleetClient.FleetClient;
import fleetClient.MakeFootPrint;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import org.metacsp.multi.spatioTemporal.paths.PoseSteering;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import se.oru.coordination.coordination_oru.Dependency;
import se.oru.coordination.coordination_oru.Mission;
import se.oru.coordination.coordination_oru.TrackingCallback;
import se.oru.coordination.coordination_oru.motionplanning.AbstractMotionPlanner;
import se.oru.coordination.coordination_oru.motionplanning.ompl.ReedsSheppCarPlanner;
import se.oru.coordination.coordination_oru.simulation2D.RemoteTrajectoryEnvelopeTrackerRK4;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class Test1StartRobotGeneric {


    private static Random rand = new Random(123213);
    private static ArrayList<Pair<Integer>> placements = new ArrayList<Pair<Integer>>();
    protected double MAX_VELOCITY;
    protected double MAX_ACCELERATION;



    /**
     * We make random footprint and goal pairs for the read ReedsSheppCarPlanner.
     */
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

    private static Pose[] makeRandomStartGoalPair(int numRobots, double maxRadius, double offsetX, double offsetY) {
        Pose[] ret = new Pose[2];
        Pair<Integer> placement = new Pair<Integer>(rand.nextInt(numRobots), rand.nextInt(numRobots));
        while (placements.contains(placement)) placement = new Pair<Integer>(rand.nextInt(numRobots),rand.nextInt(numRobots));
        placements.add(placement);
        ret[0] = new Pose(offsetX+placement.getFirst()*maxRadius,offsetY+placement.getSecond()*maxRadius, 2*Math.PI*rand.nextDouble());

        placement = new Pair<Integer>(rand.nextInt(numRobots), rand.nextInt(numRobots));
        while (placements.contains(placement)) placement = new Pair<Integer>(rand.nextInt(numRobots),rand.nextInt(numRobots));
        placements.add(placement);
        ret[1] = new Pose(offsetX+placement.getFirst()*maxRadius,offsetY+placement.getSecond()*maxRadius, 2*Math.PI*rand.nextDouble());

        return ret;
    }


    //TODO request from the coordinator
/*    public AbstractTrajectoryEnvelopeTracker getNewTracker(TrajectoryEnvelope te, TrackingCallback cb) {
        RemoteTrajectoryEnvelopeTrackerRK4 ret = new RemoteTrajectoryEnvelopeTrackerRK4(te, this.getRobotTrackingPeriodInMillis(te.getRobotID()), TEMPORAL_RESOLUTION, MAX_VELOCITY, MAX_ACCELERATION, this, cb) {
            @Override
            public HashSet<Dependency> getCurrentDependencies() {
                return null;
            }
            @Override
            public long getCurrentTimeInMillis() {
                return 0;
            }
        };
        ret.setUseInternalCriticalPoints(false);
        return ret;
    }*/


    public static void main(String[] args) {

        String robot1 = "r1p";
        Pose initPose = Missions.getLocationPose(robot1);
        double minRobotRadius = 0.2;
        double maxRobotRadius = 2.0;
        int robotID = 1;
        double maxAccel = 0.5;
        double maxVel = 1;
        String target = "localhost:50051";
        int port = 50051;

        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();

        FleetClient client = new FleetClient(channel);
        ReedsSheppCarPlanner rsp = new ReedsSheppCarPlanner();

        Coordinate[] fp = makeRandomFootprint(0, 0, 3, 6, minRobotRadius, maxRobotRadius);

        rsp.setRadius(0.2);
        rsp.setTurningRadius(4.0);
        rsp.setDistanceBetweenPathPoints(0.5);
        rsp.setFootprint(fp);

        //Plan path from start to goal and vice-versa
        //FIXME: 1 (numRobots) is hardcoded
        Pose[] startAndGoal = makeRandomStartGoalPair(1, 1.5*maxRobotRadius, 1.1*maxRobotRadius, 1.1*maxRobotRadius);

        rsp.setStart(startAndGoal[0]);
        rsp.setGoals(startAndGoal[1]);

        if (!rsp.plan()) throw new Error ("No path between " + startAndGoal[0] + " and " + startAndGoal[1]);
        PoseSteering[] path = rsp.getPath();
        PoseSteering[] pathInv = rsp.getPathInv();

        //Define forward and backward missions and enqueue them
        Missions.enqueueMission(new Mission(robotID,path));
        Missions.enqueueMission(new Mission(robotID,pathInv));

        MakeFootPrint footprint = new MakeFootPrint(0, 0, 3, 6, minRobotRadius, maxRobotRadius);

        try {
            client.makeGreeting("greeting", robotID,   "simulated", InetAddress.getLocalHost().toString(), port, initPose,
                    String.valueOf( System.currentTimeMillis()), maxAccel, maxVel, 1000, footprint);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

		/*	We should pass:
				1. the IP of the coordinator.
		 		1. the robotID.
		 		2. the robot starting position.
				3. a configuration file containing informations about:
			 		- the footprint of the robot,
			 		- the forward model with max acceleration, max velocity, and the robot tracking period in millis.
			 		- the planner of the robot.
			All such information should be communicated to the cooordinator while greeting (so that the coordinator will call the
			the functions setFootprint(..), setForwardModel(..), setMotionPlanner(..) and placeRobot(..).

			What should be started here?
			A class which contains the robot tracker. So I will expect something like the following code:
			----------------------------------------------------------------------------------------------

			1. Read all the robot parameters
			2. Instantiate the tracker according to the robot parameters.
			3. do {
				makeGreeting();
			   } while (greeting succeed);
			4. Start publishing the robot report
			5. Wait for the first mission dispatched.
		  */

    }

}
