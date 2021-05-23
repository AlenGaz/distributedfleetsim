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
import se.oru.coordination.coordination_oru.RobotReport;
import se.oru.coordination.coordination_oru.TrackingCallback;
import se.oru.coordination.coordination_oru.motionplanning.ompl.ReedsSheppCarPlanner;
import se.oru.coordination.coordination_oru.util.Missions;
import se.oru.coordination.coordination_oru.util.Pair;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class TestMultiStartRobots  {

    private static Random rand = new Random(123213);
    private static ArrayList<Pair<Integer>> placements = new ArrayList<Pair<Integer>>();
    static private int PARKING_DURATION = 3000;
    public static HashMap<Integer, robotConfig> robotIDtoRobotConfig = new HashMap<Integer, robotConfig>();
    public static HashMap<Integer, Integer> robotRounds = new HashMap<Integer, Integer>();

    /**
     * We make random footprint and goal pairs for the  ReedsSheppCarPlanner.
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

    private static Pose[] makeNewGoalFromStart(int numRobots, Pose startPose, double maxRadius, double offsetX, double offsetY){

        Pose[] ret = new Pose[2];
        ret[0] = startPose;

        Pair<Integer> placement;
        placement = new Pair<Integer>(rand.nextInt(numRobots), rand.nextInt(numRobots));
        ret[1] = new Pose(offsetX + placement.getFirst() *maxRadius, placement.getSecond()*maxRadius, 2*Math.PI*rand.nextDouble());

        return ret;

    }



    static double minRobotRadius = 1.0;
    static double maxRobotRadius = 8.0;



    public static Integer port = 50051;

    double TEMPORAL_RESOLUTION;

   //public static String target = "localhost:" + port.toString();  // coordinator IP;
     public static String target = "178.174.203.103:" + port.toString();  // coordinator IP;


    public static int numberOfRobots = 5;

    public static void main(String[] args) {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		/*
		We should pass:
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



    for(int i = 1 ; i <= numberOfRobots; i++){

        String robotName = "r" + i + "p";
        Random random = new Random();
        float randomFactor = random.nextFloat() *8 + 1f;
        double MAX_VELOCITY = random.nextInt(10) + 100;
        double MAX_ACCELERATION = MAX_VELOCITY - 1;
        int robotID = i;

                ManagedChannel channel = ManagedChannelBuilder.forTarget(target).maxInboundMessageSize(100*1000*1000).usePlaintext().build();
                //ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost:", 50051).maxInboundMessageSize(100*1000*1000).usePlaintext().build();
                FleetClient client = new FleetClient(channel);


                Coordinate[] fp = makeRandomFootprint(0, 0, 3, 6, minRobotRadius, maxRobotRadius);
                Pose[] startAndGoal = makeRandomStartGoalPair(numberOfRobots, 5.2*maxRobotRadius, 2*i+ randomFactor*minRobotRadius,2*i + randomFactor*maxRobotRadius);

                //Coordinate[] fp = makeRandomFootprint(0, 0, 1, 8, minRobotRadius, maxRobotRadius);
                //Pose[] startAndGoal = makeRandomStartGoalPair(3, 4.2*maxRobotRadius, 2*maxRobotRadius, 1.1*maxRobotRadius);


                //Set the robot motion planner
                ReedsSheppCarPlanner rsp = new ReedsSheppCarPlanner();
                rsp.setRadius(minRobotRadius);
                rsp.setTurningRadius(30);
                rsp.setDistanceBetweenPathPoints(1);
                rsp.setFootprint(fp);



                //Plan path from start to goal and vice-versa
                rsp.setStart(startAndGoal[0]);
                rsp.setGoals(startAndGoal[1]);

                //Prepare the message to communicate the robot footprint to the coordinator
                //fixme MakeFootPrint is the same as makeRandomFootPrint
                MakeFootPrint footprint = new MakeFootPrint(0, 0, 3, 6, minRobotRadius, maxRobotRadius);

                if (!rsp.plan()) throw new Error ("No path between " + startAndGoal[0] + " and " + startAndGoal[1]);
                // making the rsp path from setStart to setGoal as overallPath for sending a poseSteering.. this way the
                // solver can be recreated from this on the trajectoryenvelopeCoordinator...?



               // poseteeringsToSend should be concatetanea rsp.getPath + rsp.getPathInv()


                //Define forward and backward missions and enqueue them /// doesnt matter if we do or not...
               // Missions.enqueueMission(new Mission(robotID, rsp.getPath()));
               // Missions.enqueueMission(new Mission(robotID, rsp.getPathInv()));

                //2. instantiating tracker
                //TODO Trajectory Envelope te?? // get parking envelope


                //Create the parking envelope of the robot and use it to initialize a TE-tracker
                TrajectoryEnvelopeSolver solver = new TrajectoryEnvelopeSolver(0,100000000);

                // This below is to start a parked robot only..
                // TrajectoryEnvelope parkingEnvelope = solver.createParkingEnvelope(robotID, PARKING_DURATION, startAndGoal[0], startAndGoal[0].toString() , fp);

                TrajectoryEnvelope runEnvelope = solver.createEnvelopeNoParking(robotID, rsp.getPath(),"(D)", fp);
                System.out.println("parkingEnvelope.getTemporalVariable() : " + runEnvelope.getTemporalVariable());
                TrackingCallback cb = new TrackingCallback(runEnvelope) {
                    @Override
                    public void beforeTrackingStart() {}

                    @Override
                    public void onTrackingStart() {}

                    @Override
                    public String[] onPositionUpdate() {
                        return new String[0];
                    }


                    @Override
                    public void beforeTrackingFinished() {

                    }

                    @Override
                    public void onTrackingFinished() { System.out.println("On Tracking Finished!!! emptyParamMethod");}

                    @Override
                    public void onTrackingFinished(int robotID, Pose currentPose) {
                        //fixme requeing a mission like this doesnt reverse the mission

                       /**
                        *
                        * Attempt of requeuing new missions after a tracking finished.
                        * .. what can be done here is instead having some randomization of a number
                        * with a probability of setting an entirely new mission instead of having the same
                        * mission which makes the robot go forward and backwards all the time
                        * */

                        System.out.println("On Tracking Finished!!!! for robotID: " + robotID + " currentPose: " + currentPose);

                        robotConfig thisConfig = robotIDtoRobotConfig.get(robotID);


                        // have to start an envelope too like in line 223 for the rk4

                        int roundToIterate = robotRounds.get(robotID);
                        PoseSteering[] newPath;
                        if (roundToIterate % 2 == 1){
                            newPath = thisConfig.getPath();
                        }
                        else
                        {
                            newPath = thisConfig.getPathInv();
                        }


                        TrajectoryEnvelope runEnvelope = solver.createEnvelopeNoParking(robotID, newPath,"(D)", thisConfig.getFootprint());
                        RemoteTrajectoryEnvelopeTrackerRK4 rk4 = new RemoteTrajectoryEnvelopeTrackerRK4(runEnvelope, 30, 1000, MAX_VELOCITY, MAX_ACCELERATION, this, client) {
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
                        simpleGreeting("newmission",myTE.getRobotID(), MAX_ACCELERATION, MAX_VELOCITY,port, thisConfig.getStartPose(), thisConfig.getEndPose() , client, newPath, thisConfig.getFootprint());

                        robotRounds.put(robotID, roundToIterate + 1);

                    }

                    @Override
                    public void onNewGroundEnvelope() {

                    }
                };


                // initializing the RK4

                RemoteTrajectoryEnvelopeTrackerRK4 rk4 = new RemoteTrajectoryEnvelopeTrackerRK4(runEnvelope, 30, 1000, MAX_VELOCITY, MAX_ACCELERATION, cb, client) {
                    @Override
                    public RobotReport getRobotReport(int robotID) {
                        return getRobotReport();
                    }

                    @Override
                    public long getCurrentTimeInMillis() {
                        //System.out.println("getCurrentTimeInMillis: " + this.getCurrentTimeInMillis());
                        //return this.getCurrentTimeInMillis();
                        return 0;
                    }

                };


            /**
             * coordinatorResponse is the returned value of the stub
             * that is made as a greeting to the CoordinatorServer
             * it should return the string "Response", for now it is
             * hardcoded in the CoordinatorServiceImpl to signal back
             **/

            // saving robots setups in a hashmap
            robotConfig saveConfig = new robotConfig(startAndGoal[0], startAndGoal[1], rsp.getPath(), rsp.getPathInv(), fp);
            System.out.println("pathINv inside multistart: " + rsp.getPathInv().length);
            System.out.println("path inside multistart: " + rsp.getPath().length);
            robotIDtoRobotConfig.put(robotID, saveConfig);
            robotRounds.put(robotID, 0);

            int coordinatorResponse = 0;
            RobotReport rR = rk4.getRobotReport();
            client.makeRobotReport("my RobotReport", rR.getRobotID(), rR.getPose().getX()
                    , rR.getPose().getY(), rR.getPose().getZ(), rR.getPose().getRoll(),
                    rR.getPose().getPitch(), rR.getPose().getYaw(), rR.getVelocity()
                    , rR.getPathIndex(), rR.getDistanceTraveled(), rR.getCriticalPoint());

            coordinatorResponse = simpleGreeting("greeting",robotID, MAX_VELOCITY, MAX_ACCELERATION, port, startAndGoal[0], startAndGoal[1], client, rsp.getPath(), fp);


            if (coordinatorResponse != -1) {

                //System.out.println("[Test1StartRobotGeneric] CoordinatorServer is alive, numberOfReplicas: " + coordinatorResponse);
                rR = rk4.getRobotReport();
                client.makeRobotReport("my RobotReport", rR.getRobotID(), rR.getPose().getX()
                        , rR.getPose().getY(), rR.getPose().getZ(), rR.getPose().getRoll(),
                        rR.getPose().getPitch(), rR.getPose().getYaw(), rR.getVelocity()
                        , rR.getPathIndex(), rR.getDistanceTraveled(), rR.getCriticalPoint());

                //System.out.println("[Test1StartRobotGeneric] asked the coordinator for currentTime and got: " + client.makeCurrentTimeRequest());

                //coordinatorResponse = simpleGreeting("greeting",robotID, MAX_VELOCITY, MAX_ACCELERATION, port, startAndGoal[0], startAndGoal[1], client, footprint, rsp.getPath(), numberOfRobots);
                rk4.setNumberOfReplicas(coordinatorResponse); //
            }

            // FleetClient.makeGreeting will return -1 if coordinator isnt there
            if (coordinatorResponse == -1)
                System.out.println("[Test1StartRobotGeneric] exiting...CoordinatorServer not running");

            }
        }



    /**
     * This is our simple greeting for now that is going to the client, just calling this
     * with the correct parameters will handle rest of the messaging to the server,
     * note that the String target declaration in this class needs to be correct
     * */

    public static int simpleGreeting(String kan, int robotID, double maxAccel, double maxVel, int port, Pose startPose, Pose endPose, FleetClient client,  PoseSteering[] poseSteerings, Coordinate[] footprint) {
        int requestResponse = 0;
        System.out.println("[TestMultiStartRobots] in simpleGreeting kan: " + kan);
        try {
            requestResponse = client.makeGreeting(kan, robotID, "simulated", InetAddress.getLocalHost().toString(), port, startPose, endPose,
                    String.valueOf(System.currentTimeMillis()), maxAccel, maxVel, 1000, poseSteerings, footprint);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return requestResponse;
    }



}
