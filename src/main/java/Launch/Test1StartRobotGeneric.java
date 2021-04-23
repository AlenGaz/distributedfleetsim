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

import static Launch.Test1StartRobotFileMp.makeRandomFootprint;
import static Launch.Test1StartRobotFileMp.makeRandomStartGoalPair;

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
				
        Mission m = new Mission(1, coordinatorServiceImpl.robotIDtoClientConnection.get(1).getPoseSteerings());
				Missions.enqueueMission(m);
				dispatchedRobot.put(1, true);

				Missions.enqueueMission(new Mission(10, coordinatorServiceImpl.robotIDtoClientConnection.get(1).getPoseSteerings()));
				Missions.enqueueMission(new Mission(20, coordinatorServiceImpl.robotIDtoClientConnection.get(1).getPoseSteerings()));



				
				//FIXME This may run on a different PC in the future
				BrowserVisualization viz = new BrowserVisualization();
				viz.setInitialTransform(18, 35, 20);
				tec.setVisualization(viz);

				TimeUnit.SECONDS.sleep(2);

				tec.placeRobot(1, coordinatorServiceImpl.robotIDtoClientConnection.get(1).getStartpose());
        tec.addMission(m);	


				Pose testpose = new Pose(8, 8, 0 ,3 ,0 ,0);
				tec.placeRobot(10, testpose);

				Pose testpose2 = new Pose(11, 18, 8 ,12 ,12 ,12);
				tec.placeRobot(20, testpose2);


				
				//Missions.startMissionDispatchers(tec, new int[]{1, 2, 3});


				dispatched = true;


				//------------
/*
				//Use a random polygon as this robot's geometry (footprint)
				Coordinate[] fp = makeRandomFootprint(0, 0, 3, 6, 3, 3);
				tec.setFootprint(1,fp);

				//Set a forward model (all robots have the same here)
				tec.setForwardModel(1, new ConstantAccelerationForwardModel(1, 2, tec.getTemporalResolution(), tec.getControlPeriod(), tec.getRobotTrackingPeriodInMillis(1)));

				//Define start and goal poses for the robot
				Pose[] startAndGoal = makeRandomStartGoalPair(3, 1.5*1, 1.1*1, 1.1*1);
				System.out.println("pose startandgoal START" + coordinatorServiceImpl.robotIDtoClientConnection.get(1).getStartpose());
				System.out.println("pose startandgoal END" + coordinatorServiceImpl.robotIDtoClientConnection.get(1).getEndpose());


				//Place the robot in the start pose
				tec.placeRobot(1, startAndGoal[0]);

				//Path planner for each robot (with empty map)
				ReedsSheppCarPlanner rsp = new ReedsSheppCarPlanner();
				rsp.setRadius(0.2);
				rsp.setTurningRadius(4.0);
				rsp.setDistanceBetweenPathPoints(0.5);
				rsp.setFootprint(fp);

				//Plan path from start to goal and vice-versa
				rsp.setStart(startAndGoal[0]);
				rsp.setGoals(startAndGoal[1]);
				if (!rsp.plan()) throw new Error ("No path between " + startAndGoal[0] + " and " + startAndGoal[1]);
				PoseSteering[] path = rsp.getPath();
				//PoseSteering[] pathInv = rsp.getPathInv();



				//Define forward and backward missions and enqueue them
				Missions.enqueueMission(new Mission(1,path));


				//Path planner to use for re-planning if needed
				tec.setMotionPlanner(1, rsp);


			//Avoid deadlocks via global re-ordering
			tec.setBreakDeadlocks(true, false, false);

			//Start a visualization (will open a new browser tab)
			BrowserVisualization viz = new BrowserVisualization();
			viz.setInitialTransform(49, 5, 0);
			tec.setVisualization(viz);

			//Start dispatching threads for each robot, each of which
			//dispatches the next mission as soon as the robot is idle

			Missions.startMissionDispatchers(tec, 1);
			dispatched=true;
*/
				//------------
			}



			if (!coordinatorServiceImpl.robotIDtoClientConnection.isEmpty()) {

				for (int i = 0; i < coordinatorServiceImpl.robotIDtoClientConnection.keySet().size(); i++) {
					/*	TODO This is a better way to do it than the test above and will be changed too
					if(dispatchedRobot.get(i).booleanValue() == true){
							return; }
						else{
							System.out.println("[Test..Coordinator] dispatching robot: " + i);
							Missions.enqueueMission(new Mission(i,coordinatorServiceImpl.robotIDtoClientConnection.get(i).getPoseSteerings()));
							dispatchedRobot.put(i,true);
							Missions.startMissionDispatchers(tec, new int[] {1,2,3});
						}
				}
*/

				}


			}


			//dispatches the next mission as soon as the robot is idle
			//Set<Integer> keys =  coordinatorServiceImpl.robotIDtoClientConnection.keySet(); need to make into array of integers instead.. can do it with forloop..
			//Missions.startMissionDispatchers(tec, keys.size());

		}


	}
}

