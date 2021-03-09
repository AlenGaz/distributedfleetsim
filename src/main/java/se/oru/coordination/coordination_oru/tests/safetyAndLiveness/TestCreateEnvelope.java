package se.oru.coordination.coordination_oru.tests.safetyAndLiveness;

import java.util.Arrays;

import org.metacsp.multi.spatioTemporal.paths.Pose;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope.SpatialEnvelope;

import com.vividsolutions.jts.geom.Coordinate;

import Coordinator.AbstractTrajectoryEnvelopeCoordinator;
import se.oru.coordination.coordination_oru.CriticalSection;
import fleetClient.motionplanning.ompl.ReedsSheppCarPlanner;

public class TestCreateEnvelope {

		public static void main(String[] args) throws InterruptedException {


			Coordinate footprint1 = new Coordinate(-1.0,0.5);
			Coordinate footprint2 = new Coordinate(1.0,0.5);
			Coordinate footprint3 = new Coordinate(1.0,-0.5);
			Coordinate footprint4 = new Coordinate(-1.0,-0.5);

			ReedsSheppCarPlanner rsp = new ReedsSheppCarPlanner();
			rsp.setRadius(0.2);
			rsp.setFootprint(footprint1, footprint2, footprint3, footprint4);
			rsp.setTurningRadius(3.0);
			rsp.setDistanceBetweenPathPoints(0.1);
			
			Pose poseFrom1 = new Pose(0.0,0.0,0.0);
			Pose poseTo1 = new Pose(10.0,10.0,Math.PI);

			Pose poseFrom2 = new Pose(0.0,10.0,0.0);
			Pose poseTo2 = new Pose(10.0,0.0,Math.PI);

			rsp.setStart(poseFrom1);
			rsp.setGoals(poseTo1);
			if (!rsp.plan()) throw new Error("Cannot plan for Robot1");
			SpatialEnvelope se1 = TrajectoryEnvelope.createSpatialEnvelope(rsp.getPath(), footprint1, footprint2, footprint3, footprint4);

			rsp.setStart(poseFrom2);
			rsp.setGoals(poseTo2);
			if (!rsp.plan()) throw new Error("Cannot plan for Robot2");
			SpatialEnvelope se2 = TrajectoryEnvelope.createSpatialEnvelope(rsp.getPath(), footprint1, footprint2, footprint3, footprint4);
			
			CriticalSection[] css = AbstractTrajectoryEnvelopeCoordinator.getCriticalSections(se1, se2, 2.0);
			System.out.println("Found " + css.length + " critical sections");
			
			System.out.println(Arrays.toString(css));
			
	}

}
