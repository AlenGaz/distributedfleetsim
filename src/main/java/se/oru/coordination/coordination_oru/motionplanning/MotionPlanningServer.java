package se.oru.coordination.coordination_oru.motionplanning;
/*
import fleetClient.motionplanning.AbstractMotionPlanner;

public class MotionPlanningServer {
	AbstractMotionPlanner mp = null;
	
	//here you may pass an instance of the reedsheep in the Test1RobotStart ...
	MotionPlanningServer(AbstractMotionPlanner mp) {
		this.mp = mp;
	}
	
	//Provide a method to respond to
	... callMotionPlanningInstance(... planning request, resp ) {
		if (mp == null) return null;
		synchronized (mp) {
			mp.setStart(fromPose);
			mp.setGoals(toPose);
			//mp.clearObstacles();
			if (obstaclesToConsider != null && obstaclesToConsider.length > 0) mp.addObstacles(obstaclesToConsider);
			boolean replanningSuccessful = mp.plan();
			if (!replanningSuccessful) mp.writeDebugImage();
			if (obstaclesToConsider != null && obstaclesToConsider.length > 0) mp.clearObstacles();
			if (replanningSuccessful) resp = mp.getPath();
			return resp;
		}
}
*/