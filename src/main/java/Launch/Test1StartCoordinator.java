package Launch;

import java.io.IOException;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import GRPC.CoordinatorServiceImpl;
import Visualizer.util.BrowserVisualization;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import se.oru.coordination.coordination_oru.CriticalSection;
import se.oru.coordination.coordination_oru.NetworkConfiguration;
import se.oru.coordination.coordination_oru.RobotAtCriticalSection;
import se.oru.coordination.coordination_oru.RobotReport;
import CoordinatorPackage.RemoteTrajectoryEnvelopeCoordinatorSimulation;

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
		tec.addComparator(new Comparator<RobotAtCriticalSection> () {
			@Override
			public int compare(RobotAtCriticalSection o1, RobotAtCriticalSection o2) {
				CriticalSection cs = o1.getCriticalSection();
				RobotReport robotReport1 = o1.getRobotReport();
				RobotReport robotReport2 = o2.getRobotReport();
				return ((cs.getTe1Start()-robotReport1.getPathIndex())-(cs.getTe2Start()-robotReport2.getPathIndex()));
			}
		});

		//Define a network with uncertainties (see Mannucci et al., 2019)
		NetworkConfiguration.setDelays(0,0);
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

		//Start a visualization (will open a new browser tab)
		//FIXME This may run on a different PC in the future
		BrowserVisualization viz = new BrowserVisualization();
		viz.setInitialTransform(49, 5, 0);
		tec.setVisualization(viz);
		
		//Note: we need to pass and read a file containing the sequence of goals or missions to robots.
		//see the {@Missions} class.




		//2. INSTANTIATE THE COORDINATIONSERVER

	/*
		CoordinatorServer server = new CoordinatorServer(tec); <<--- CoordServiceImplementation
        try {
			server.start();
			server.blockUntilShutdown();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
		//2. INSTANTIATE THE COORDINATIONSERVER
		try {
			server = ServerBuilder.forPort(PORT)
					.addService(coordinatorServiceImpl)
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

		while(true){

			TimeUnit.SECONDS.sleep(1);
			System.out.println("robotIDtoClientConnection Keyset: " + coordinatorServiceImpl.robotIDtoClientConnection.keySet());
			System.out.println("[Test1StartCoordinator] robotIDtoClientConnection Keyset: " + coordinatorServiceImpl.robotIDtoClientConnection.keySet());
			System.out.println("[Test1StartCoordinator] robotIDtoClientConnection Type: " + coordinatorServiceImpl.robotIDtoClientConnection.get(1).getTimeStamp());

			// This below is just test to see if the instance of the CoordinatorServiceImpl is the same in the server and Abstract..Coordinator
			if(tec.coordinatorServicImpl.robotIDtoClientConnection.containsKey(1)){

				tec.testCoordReference();
			}

		}


		//dispatches the next mission as soon as the robot is idle
		//Missions.startMissionDispatchers(tec, robotIDs);	

	}


}
