package CoordinatorPackage;

import fleetClient.AbstractTrajectoryEnvelopeTracker;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelopeSolver;
import se.oru.coordination.coordination_oru.RobotReport;
import se.oru.coordination.coordination_oru.TrackingCallback;

/**
 * This class provides the basic functionalities of a {@link TrajectoryEnvelope} tracker. Implementing
 * this class is equivalent to providing the interface to a particular robot system.
 * 
 * @author fpa
 *
 */
public abstract class TrajectoryEnvelopeTrackerLight extends AbstractTrajectoryEnvelopeTracker {

	/**
	 * Create a new {@link TrajectoryEnvelopeTrackerLight} to track a given {@link TrajectoryEnvelope},
	 * with a given tracking period in a given temporal resolution. The tracker will post temporal constraints
	 * to the given solver representing when the robot transitions from one sub-envelope to the next. An optional
	 * callback function will be called at every period.
	 * @param te The {@link TrajectoryEnvelope} to track.
	 * @param temporalResolution The temporal unit of measure in which the period is represented. 
	 * @param // solver The {@link TrajectoryEnvelopeSolver} to which temporal constraints will be posted.
	 * @param trackingPeriodInMillis The tracking period.
	 * @param cb An optional callback function.
	 */


	private boolean useInternalCPs = false;

	public TrajectoryEnvelopeTrackerLight(TrajectoryEnvelope te, double temporalResolution, AbstractTrajectoryEnvelopeCoordinator tec, int trackingPeriodInMillis, TrackingCallback cb) {

	}

	public TrajectoryEnvelopeTrackerLight(TrajectoryEnvelope te, Integer robotTrackingPeriodInMillis, double temporal_resolution, Double robotMaxVelocity, Double robotMaxAcceleration, RemoteTrajectoryEnvelopeCoordinatorSimulation remoteTrajectoryEnvelopeCoordinatorSimulation, TrackingCallback cb) {

	}


	@Override
	protected void startMonitoringThread() {}


	/**
	 * This method should return a {@link RobotReport}, describing the current state of the robot.
	 * @return A {@link RobotReport}, describing the current state of the robot.
	 */
	public RobotReport getRobotReport() {
		//return the last communicated robot report.
		return null;
	};

	/**
	 * This method should implement the mechanisms for notifying a robot of a new critical point.
	 * @param criticalPoint The critical point to set (index of pose along the reference trajectory
	 * beyond which the robot may not navigate). 
	 */
	public void setCriticalPoint(int criticalPoint) {
		//call your service to set the critical point
	}

	public void setUseInternalCriticalPoints(boolean b) {
		this.useInternalCPs=b;
	}
}

//Customize the RemoteTrajectoryEnvelopeCoordinator to return this type of tracker.
