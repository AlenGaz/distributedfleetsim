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
import se.oru.coordination.coordination_oru.util.Pair;

import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;


public class TestMultiStartRobots  {

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

    private static Pose[] makeNewGoalFromStart(int numRobots, Pose startPose, double maxRadius, double offsetX, double offsetY){

        Pose[] ret = new Pose[2];
        ret[0] = startPose;

        Pair<Integer> placement;
        placement = new Pair<Integer>(rand.nextInt(numRobots), rand.nextInt(numRobots));
        ret[1] = new Pose(offsetX + placement.getFirst() *maxRadius, placement.getSecond()*maxRadius, 2*Math.PI*rand.nextDouble());

        return ret;

    }


    /**
     * Here's a method that will concatenate two arrays and return the result (used for adding path and pathInv).
     */

    public static PoseSteering[] concatenate(PoseSteering[] pathA, PoseSteering[] pathB) {
        int pathA_Length = pathA.length;
        int pathB_Length = pathB.length;

        PoseSteering[] robotPath = (PoseSteering[]) Array.newInstance(pathA.getClass().getComponentType(), pathA_Length + pathB_Length);
        System.arraycopy(pathA, 0, robotPath, 0, pathA_Length);
        System.arraycopy(pathB, 0, robotPath, pathA_Length, pathB_Length);

        return robotPath;
    }


    static double minRobotRadius = 0.2;
    static double maxRobotRadius = 2.0;
    public static Integer port = 50051;
    double TEMPORAL_RESOLUTION;
    public static String target = "localhost:" + port.toString();  // coordinator IP;
    public static int numberOfRobots = 5;


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



    for(int i = 1 ; i <= numberOfRobots; i++){

        String robotName = "r" + i + "p";
        Random random = new Random();
        float randomFactor = random.nextFloat() * 8 + 1f;
        double MAX_VELOCITY = random.nextInt(3) + 2;
        double MAX_ACCELERATION = MAX_VELOCITY - 1;
        int robotID = i;

                ManagedChannel channel = ManagedChannelBuilder.forTarget(target).maxInboundMessageSize(100*1000*1000).usePlaintext().build();
                FleetClient client = new FleetClient(channel);


                Coordinate[] fp = makeRandomFootprint(0, 0, 3, 6, minRobotRadius, maxRobotRadius);
                Pose[] startAndGoal = makeRandomStartGoalPair(numberOfRobots, 5.1*maxRobotRadius, i+ 4.1*minRobotRadius,i + 4*maxRobotRadius);

                //Coordinate[] fp = makeRandomFootprint(0, 0, 1, 8, minRobotRadius, maxRobotRadius);
                //Pose[] startAndGoal = makeRandomStartGoalPair(3, 4.2*maxRobotRadius, 2*maxRobotRadius, 1.1*maxRobotRadius);


                //Set the robot motion planner
                ReedsSheppCarPlanner rsp = new ReedsSheppCarPlanner();
                rsp.setRadius(0.2);
                rsp.setTurningRadius(15);
                rsp.setDistanceBetweenPathPoints(0.50);
                rsp.setFootprint(fp);



                //Plan path from start to goal and vice-versa
                rsp.setStart(startAndGoal[0]);
                rsp.setGoals(startAndGoal[1]);

                //Prepare the message to communicate the robot footprint to the coordinator
                MakeFootPrint footprint = new MakeFootPrint(0, 0, 3, 6, minRobotRadius, maxRobotRadius);

                if (!rsp.plan()) throw new Error ("No path between " + startAndGoal[0] + " and " + startAndGoal[1]);
                // making the rsp path from setStart to setGoal as overallPath for sending a poseSteering.. this way the
                // solver can be recreated from this on the trajectoryenvelopeCoordinator...?



               // poseteeringsToSend should be concatetanea rsp.getPath + rsp.getPathInv()

               //Define forward and backward missions and enqueue them
               org.metacsp.multi.spatioTemporal.paths.PoseSteering[] path = rsp.getPath();
               org.metacsp.multi.spatioTemporal.paths.PoseSteering[] pathInv = rsp.getPathInv();

               //Mission m = new Mission(robotID, concatenate(path,pathInv));
               //System.out.println("elkabir->" + m);


                //2. instantiating tracker
                //TODO Trajectory Envelope te?? // get parking envelope


                //Create the parking envelope of the robot and use it to initialize a TE-tracker
                TrajectoryEnvelopeSolver solver = new TrajectoryEnvelopeSolver(0,100000000);

                // This below is to start a parked robot only..
                // TrajectoryEnvelope parkingEnvelope = solver.createParkingEnvelope(robotID, PARKING_DURATION, startAndGoal[0], startAndGoal[0].toString() , fp);

                TrajectoryEnvelope runEnvelope = solver.createEnvelopeNoParking(robotID, concatenate(path,pathInv),"(D)", fp);
                System.out.println("parkingEnvelope.getTemporalVariable() : " + runEnvelope.getTemporalVariable());
                TrackingCallback cb = new TrackingCallback(runEnvelope) {
                    @Override
                    public void beforeTrackingStart() {

                    }

                    @Override
                    public void onTrackingStart() {

                    }

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

                   /*    Attempt of requeuing new missions after a tracking finished.
                         .. what can be done here is instead having some randomization of a number
                         with a probability of setting an entirely new mission instead of having the same
                         mission which makes the robot go forward and backwards all the time

                        System.out.println("On Tracking Finished!!!! for robotID: " + robotID + " currentPose: " + currentPose);

                        Pose[] startAndGoal = makeNewGoalFromStart(numberOfRobots, currentPose, 1.1*maxRobotRadius, 1.1*minRobotRadius, 2.1*maxRobotRadius);
                        simpleGreeting("newmission",myTE.getRobotID(), MAX_ACCELERATION, MAX_VELOCITY,port, startAndGoal[0], startAndGoal[1] , client, footprint, rsp.getPath());


                        rsp.setStart(startAndGoal[0]);
                        rsp.setGoals(startAndGoal[1]);

                        // have to start an envelope too like in line 223 for the rk4
                        TrajectoryEnvelope runEnvelope = solver.createEnvelopeNoParking(robotID, rsp.getPath(),"(D)", fp);
                        RemoteTrajectoryEnvelopeTrackerRK4 rk4 = new RemoteTrajectoryEnvelopeTrackerRK4(runEnvelope, 30, 1000, MAX_VELOCITY, MAX_ACCELERATION, this) {
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
                        */
                    }

                    @Override
                    public void onNewGroundEnvelope() {

                    }
                };


                // initializing the RK4

                RemoteTrajectoryEnvelopeTrackerRK4 rk4 = new RemoteTrajectoryEnvelopeTrackerRK4(runEnvelope, 30, 1000, MAX_VELOCITY, MAX_ACCELERATION, cb) {
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

            int coordinatorResponse = 0;
            RobotReport rR = rk4.getRobotReport();
            client.makeRobotReport("my RobotReport", rR.getRobotID(), rR.getPose().getX()
                    , rR.getPose().getY(), rR.getPose().getTheta(), rR.getVelocity()
                    , rR.getPathIndex(), rR.getDistanceTraveled(), rR.getCriticalPoint());

            coordinatorResponse = simpleGreeting("greeting",robotID, MAX_VELOCITY, MAX_ACCELERATION, port, startAndGoal[0], startAndGoal[1], client, footprint, rsp.getPath(), numberOfRobots);


            if (coordinatorResponse != -1) {

                //System.out.println("[Test1StartRobotGeneric] CoordinatorServer is alive, numberOfReplicas: " + coordinatorResponse);
                rR = rk4.getRobotReport();
                client.makeRobotReport("my RobotReport", rR.getRobotID(), rR.getPose().getX()
                        , rR.getPose().getY(), rR.getPose().getTheta(), rR.getVelocity()
                        , rR.getPathIndex(), rR.getDistanceTraveled(), rR.getCriticalPoint());

                //System.out.println("[Test1StartRobotGeneric] asked the coordinator for currentTime and got: " + client.makeCurrentTimeRequest());

                coordinatorResponse = simpleGreeting("greeting",robotID, MAX_VELOCITY, MAX_ACCELERATION, port, startAndGoal[0], startAndGoal[1], client, footprint, rsp.getPath(), numberOfRobots);
                rk4.setNumberOfReplicas(coordinatorResponse); //FIXME is the coordinator answering only the number of replicas?
            }

            // FleetClient.makeGreeting will return -1 if coordinator isnt there //FIXME: it will never be triggered
            if (coordinatorResponse == -1)
                System.out.println("[Test1StartRobotGeneric] exiting...CoordinatorServer not running");

            }
        }



    /**
     * This is our simple greeting for now that is going to the client, just calling this
     * with the correct parameters will handle rest of the messaging to the server,
     * note that the String target declaration in this class needs to be correct
     * */

    public static int simpleGreeting(String kan, int robotID, double maxAccel, double maxVel, int port, Pose startPose, Pose endPose, FleetClient client, MakeFootPrint footprint, org.metacsp.multi.spatioTemporal.paths.PoseSteering[] poseSteerings, int numberOfRobots) {
        int requestResponse = 0;
        System.out.println("[TestMultiStartRobots] in simpleGreeting kan: " + kan);
        try {
            requestResponse = client.makeGreeting(kan, robotID, "simulated", InetAddress.getLocalHost().toString(), port, startPose, endPose,
                    String.valueOf(System.currentTimeMillis()), maxAccel, maxVel, 1000, footprint, poseSteerings, numberOfRobots);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return requestResponse;
    }




}
