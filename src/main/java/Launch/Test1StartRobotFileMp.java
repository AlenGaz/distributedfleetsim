package Launch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import CoordinatorPackage.containers.MakeFootPrint;
import fleetClient.FleetClient;
import fleetClient.RemoteAbstractTrajectoryEnvelopeTracker;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.metacsp.multi.spatioTemporal.paths.Pose;

import com.vividsolutions.jts.geom.Coordinate;

import org.metacsp.multi.spatioTemporal.paths.PoseSteering;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import se.oru.coordination.coordination_oru.*;
import se.oru.coordination.coordination_oru.util.Missions;
import se.oru.coordination.coordination_oru.util.Pair;


public class Test1StartRobotFileMp {



    /**
     * A test class based upon the TestTrajectoryEnvelopeCoordinatorThreeRobots class,
     * this one does not require sending the motion-planning stuff
     * */

    private static Random rand = new Random(123213);
    private static ArrayList<Pair<Integer>> placements = new ArrayList<Pair<Integer>>();


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



    public static void main(String[] args) {

		/*	We should pass:
				1. the IP of the coordinator. (should be Client ?)
		 		1. the robotID.
		 		2. the robot starting position
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
				tracker.greeting();
			   } while (greeting succeed);
			4. Start publishing the robot report
			5. Wait for the first mission dispatched.
		  */



        /**
         * Necessary params for the class below
         **/
        String robotName = "r1p";
        //Pose initPose = Missions.getLocationPose(robotName);
        double minRobotRadius = 0.2;
        double maxRobotRadius = 2.0;
        int robotID = 1;
        double maxAccel = 0.5;
        double maxVel = 1;
        int port = 50051;
        double MAX_VELOCITY;
        double MAX_ACCELERATION;
        double TEMPORAL_RESOLUTION;
        Pose testPose = new Pose(0.01,0.01,0.01,1,2,3);


        String target = "localhost:50051";  // coordinator IP
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
        FleetClient client = new FleetClient(channel);

        Missions.loadLocationAndPathData("paths/test_poses_and_path_data.txt");

        Pose placeRobot1 = Missions.getLocation("r1p");
        //Pose placeRobot2 = Missions.getLocation("r2p");
        //Pose placeRobot3 = Missions.getLocation("r3p");


        //tec.placeRobot (id, Pose)



        //Plan path from start to goal and vice-versa
        //FIXME: 1 (numRobots) is hardcoded




        //2. instantiating tracker
        //TODO Trajectory Envelope te??

        RemoteAbstractTrajectoryEnvelopeTracker tracker = new RemoteAbstractTrajectoryEnvelopeTracker() {
            @Override
            protected void onTrajectoryEnvelopeUpdate(TrajectoryEnvelope te) {

            }

            @Override
            protected void setCriticalPoint(int criticalPoint) {

            }

            @Override
            public RobotReport getRobotReport() {
                return null;
            }

            @Override
            public long getCurrentTimeInMillis() {
                return 0;
            }

            @Override
            public void startTracking() { }
        };

        if(tracker.te == null || tracker.te == null){
            System.out.println("tracker.te/tracker.cb is null");
        }

        // the rk4 must be outcommented else null exceptions will just break it right now, we have problems here
	/*	RemoteTrajectoryEnvelopeTrackerRK4 rk4 = new RemoteTrajectoryEnvelopeTrackerRK4(tracker.te, 30, 1000, 2, 1.0, tracker.cb) {

			@Override
			public long getCurrentTimeInMillis() {
				return 0;
			}
		};
	*/

        MakeFootPrint footprint = new MakeFootPrint(0, 0, 3, 6, minRobotRadius, maxRobotRadius);

        /**
         * coordinatorResponse is the returned value of the stub
         * that is made as a greeting to the CoordinatorServer
         * it should return the string "Response", for now it is
         * hardcoded in the CoordinatorServiceImpl to signal back
         *
         * */

        int coordinatorResponse = 0;
    /*
        coordinatorResponse = simpleGreeting(robotID, maxAccel, maxVel, port, testPose, client, footprint);

        // to send id = 2 and 3 gonna remove these 2 greetings below later
        coordinatorResponse = simpleGreeting(2, maxAccel, maxVel, port, testPose, client, footprint);
        coordinatorResponse = simpleGreeting(3, maxAccel, maxVel, port, testPose, client, footprint);


        System.out.println("coordinatorResponse is: " + coordinatorResponse);
    */
        while(coordinatorResponse!= 0){

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("CoordinatorServer is alive... numberOfReplicas: " +coordinatorResponse);


            //coordinatorResponse = simpleGreeting(robotID, maxAccel, maxVel, port, testPose, client, footprint);
            // rk4.setNumberOfReplicas(coordinatorResponse);
        }

        // FleetClient.makeGreeting will return -1 if coordinator isnt there
        if(coordinatorResponse == -1){
            System.out.println("[Test1StartRobotGeneric] exiting...CoordinatorServer not running");
        }

    }






    /**
     * This is our simple greeting for now that is going to the client, just calling this
     * with the correct parameters will handle rest of the messaging to the server,
     * note that the String target declaration in this class needs to be correct
     * */
    public static int simpleGreeting(int robotID, double maxAccel, double maxVel, int port, Pose startPose, Pose endPose, FleetClient client, MakeFootPrint footprint, PoseSteering[] poseSteerings) {
        int requestResponse = 0;
        try {
            requestResponse = client.makeGreeting("greeting", robotID, "simulated", InetAddress.getLocalHost().toString(), port, startPose, endPose,
                    String.valueOf(System.currentTimeMillis()), maxAccel, maxVel, 1000, footprint,poseSteerings);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return requestResponse;
    }

}
