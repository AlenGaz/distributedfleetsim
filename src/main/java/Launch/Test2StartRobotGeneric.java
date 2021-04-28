package Launch;

import CoordinatorPackage.containers.MakeFootPrint;
import com.vividsolutions.jts.geom.Coordinate;
import fleetClient.FleetClient;
import fleetClient.RemoteTrajectoryEnvelopeTrackerRK4;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import org.metacsp.multi.spatioTemporal.paths.PoseSteering;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelopeSolver;
import se.oru.coordination.coordination_oru.Mission;
import se.oru.coordination.coordination_oru.RobotReport;
import se.oru.coordination.coordination_oru.motionplanning.ompl.ReedsSheppCarPlanner;
import se.oru.coordination.coordination_oru.util.Missions;
import se.oru.coordination.coordination_oru.util.Pair;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Test2StartRobotGeneric {

    private static Random rand = new Random(123213);
    private static ArrayList<Pair<Integer>> placements = new ArrayList<Pair<Integer>>();
    static private int PARKING_DURATION = 3000;

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
			All such information should be communicated to the coordinator while greeting (so that the coordinator will call the
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
        String robotName = "r2p";
        double minRobotRadius = 0.2;
        double maxRobotRadius = 2.0;
        int robotID = 2;
        double maxAccel = 2.0;
        double maxVel = 1;
        Integer port = 50051;
        double MAX_VELOCITY;
        double MAX_ACCELERATION;
        double TEMPORAL_RESOLUTION;
        Pose testPose = new Pose(0.01,0.01,0.01,1,2,3);


        String target = "localhost:" + port.toString();  // coordinator IP
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).maxInboundMessageSize(100*1000*1000).usePlaintext().build();
        FleetClient client = new FleetClient(channel);


        Coordinate[] fp = makeRandomFootprint(0, 0, 3, 6, minRobotRadius, maxRobotRadius);
        Pose[] startAndGoal = makeRandomStartGoalPair(3, 4.5*maxRobotRadius, 2.1*maxRobotRadius, 1.1*maxRobotRadius);

        //Coordinate[] fp = makeRandomFootprint(0, 0, 1, 8, minRobotRadius, maxRobotRadius);
        //Pose[] startAndGoal = makeRandomStartGoalPair(3, 4.2*maxRobotRadius, 2*maxRobotRadius, 1.1*maxRobotRadius);


        //Set the robot motion planner
        ReedsSheppCarPlanner rsp = new ReedsSheppCarPlanner();
        rsp.setRadius(0.4);
        rsp.setTurningRadius(6.0);
        rsp.setDistanceBetweenPathPoints(1.0);
        rsp.setFootprint(fp);



        //Plan path from start to goal and vice-versa
        rsp.setStart(startAndGoal[0]);
        rsp.setGoals(startAndGoal[1]);
        if (!rsp.plan()) throw new Error ("No path between " + startAndGoal[0] + " and " + startAndGoal[1]);
        // making the rsp path from setStart to setGoal as overallPath for sending a poseSteering.. this way the
        // solver can be recreated from this on the trajectoryenvelopeCoordinator...?

        //Define forward and backward missions and enqueue them
        Missions.enqueueMission(new Mission(robotID, rsp.getPath()));
        Missions.enqueueMission(new Mission(robotID, rsp.getPathInv()));

        //2. instantiating tracker
        //TODO Trajectory Envelope te?? // get parking envelope


        //Create the parking envelope of the robot and use it to initialize a TE-tracker
        TrajectoryEnvelopeSolver solver = new TrajectoryEnvelopeSolver(0,100000000);
        TrajectoryEnvelope parkingEnvelope = solver.createParkingEnvelope(robotID, PARKING_DURATION, startAndGoal[0], startAndGoal[0].toString() , fp);
        TrajectoryEnvelope runEnvelope = solver.createEnvelopeNoParking(robotID, rsp.getPath(),"(D)", fp);
        TrajectoryEnvelope runEnvelope2 = solver.createEnvelopeNoParking(robotID, rsp.getPath(), "(D)", fp);
        RemoteTrajectoryEnvelopeTrackerRK4 rk4 = new RemoteTrajectoryEnvelopeTrackerRK4(runEnvelope, 30, 1000, 2, 1.0, null) {
            @Override
            public RobotReport getRobotReport(int robotID) {
                return null;
            }

            @Override
            public long getCurrentTimeInMillis() {
                //System.out.println("getCurrentTimeInMillis: " + this.getCurrentTimeInMillis());
                //return this.getCurrentTimeInMillis();
                return 0;
            }
        };

        //Prepare the message to communicate the robot footprint to the coordinator
        MakeFootPrint footprint = new MakeFootPrint(0, 0, 3, 6, minRobotRadius, maxRobotRadius);

        /**
         * coordinatorResponse is the returned value of the stub
         * that is made as a greeting to the CoordinatorServer
         * it should return the string "Response", for now it is
         * hardcoded in the CoordinatorServiceImpl to signal back
         **/

        int coordinatorResponse = 0;
        RobotReport rR = rk4.getRobotReport();
        client.makeRobotReport("my RobotReport", rR.getRobotID(), rR.getPose().getX()
                , rR.getPose().getY(), rR.getPose().getZ(), rR.getPose().getRoll(),
                rR.getPose().getPitch(), rR.getPose().getYaw(), rR.getVelocity()
                , rR.getPathIndex(), rR.getDistanceTraveled(), rR.getCriticalPoint());

        coordinatorResponse = simpleGreeting(robotID, maxAccel, maxVel, port, startAndGoal[0], startAndGoal[1], client, footprint, rsp.getPath());

        // to send id = 2 and 3 gonna remove these 2 greetings below later
        coordinatorResponse = simpleGreeting(2, maxAccel, maxVel, port, startAndGoal[0], startAndGoal[1], client, footprint, rsp.getPath());
        coordinatorResponse = simpleGreeting(3, maxAccel, maxVel, port, startAndGoal[0], startAndGoal[1], client, footprint, rsp.getPath());


        System.out.println("[Test1StartRobotGeneric] coordinatorResponse is: " + coordinatorResponse);
        //client.makeTecStuffRequest(1);

        while (coordinatorResponse != -1) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[Test1StartRobotGeneric] CoordinatorServer is alive, numberOfReplicas: " + coordinatorResponse);


            rR = rk4.getRobotReport();
            client.makeRobotReport("my RobotReport", rR.getRobotID(), rR.getPose().getX()
                    , rR.getPose().getY(), rR.getPose().getZ(), rR.getPose().getRoll(),
                    rR.getPose().getPitch(), rR.getPose().getYaw(), rR.getVelocity()
                    , rR.getPathIndex(), rR.getDistanceTraveled(), rR.getCriticalPoint());

            System.out.println("[Test1StartRobotGeneric] asked the coordinator for currentTime and got: " + client.makeCurrentTimeRequest());

            coordinatorResponse = simpleGreeting(robotID, maxAccel, maxVel, port, startAndGoal[0], startAndGoal[1], client, footprint, rsp.getPath());
            rk4.setNumberOfReplicas(coordinatorResponse); //FIXME is the coordinator answering only the number of replicas?
        }

        // FleetClient.makeGreeting will return -1 if coordinator isnt there //FIXME: it will never be triggered
        if (coordinatorResponse == -1) System.out.println("[Test1StartRobotGeneric] exiting...CoordinatorServer not running");

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
                    String.valueOf(System.currentTimeMillis()), maxAccel, maxVel, 1000, footprint, poseSteerings);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return requestResponse;
    }

}
