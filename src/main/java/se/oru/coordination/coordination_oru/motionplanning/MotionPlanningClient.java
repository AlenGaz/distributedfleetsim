package se.oru.coordination.coordination_oru.motionplanning;

/*

import CoordinatorPackage.AbstractTrajectoryEnvelopeCoordinator;
import CoordinatorPackage.RemoteTrajectoryEnvelopeCoordinatorSimulation;

public class MotionPlanningClient extends AbstractMotionPlanner {
	
	private RemoteTrajectoryEnvelopeCoordinatorSimulation = null;
	private boolean computing = false;
	private boolean outcome = false;
	private int robotID = -1;
	//You need the ID of the robot
	
	public ReedSheepCarPlannerRemote(int robotID, AbstractTrajectoryEnvelopeCoordinator tec) {
		this.tec = tec;
		this.robotID = robotID;
	}
	
	@Override
	public AbstractMotionPlanner getCopy(boolean copyObstacles) {
		ReedSheepCarPlannerRemote ret = new ReedSheepCarPlannerRemote(this.robotID, this.tec);
		if (this.om != null) ret.om = new OccupancyMap(this.om, copyObstacles);
		return ret;
	}
	
	@Override
	public boolean doPlanning() {
		//prepare a planning request containing:
		// - the start of the robot (this.start)
		// - the current goal(s) of the robot. (this.goal)
		// - the obstacle to add (this.om.obstacles)
		
		//send the message and return a message containing the path and if failure or success
		while (computing) try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
		return outcome;
	}
	
	@Override
	public synchronized void clearObstacles() {
		if (this.noMap) this.om = null;
		else this.om.clearObstacles();
		//Send a request to clear the obstacles in the Robot planner.
	}
}



 */