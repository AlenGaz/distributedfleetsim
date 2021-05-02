package CoordinatorPackage;

import GRPC.CoordinatorServiceImpl;
import fleetClient.RemoteAbstractTrajectoryEnvelopeTracker;
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
public abstract class TrajectoryEnvelopeTrackerLight extends RemoteAbstractTrajectoryEnvelopeTracker {

	private boolean parkingFinished = false;
	private Thread th = null;
	private RobotReport robotReport;
	private boolean useInternalCPs = true;
	public RemoteAbstractTrajectoryEnvelopeCoordinator tec;
	public CoordinatorServiceImpl coordinatorServiceImpl;
	
	/**
	 * Create a new {@link TrajectoryEnvelopeTrackerLight} to track a given {@link TrajectoryEnvelope},
	 * with a given tracking period in a given temporal resolution. The tracker will post temporal constraints
	 * to the given solver representing when the robot transitions from one sub-envelope to the next. An optional
	 * callback function will be called at every period.
	 * @param te The {@link TrajectoryEnvelope} to track.
	 * @param temporalResolution The temporal unit of measure in which the period is represented.
	 * @param // solver The {@link TrajectoryEnvelopeSolver} to which temporal constraints will be posted.
	 * @param // trackingPeriodInMillis The tracking period.
	 * @param cb An optional callback function.
	 */

	public TrajectoryEnvelopeTrackerLight(RemoteAbstractTrajectoryEnvelopeCoordinator tec, TrajectoryEnvelope te, int timeStep, double temporalResolution, TrackingCallback cb, CoordinatorServiceImpl coordinatorServiceImpl) {
		super(te, temporalResolution, timeStep, cb, false);
		this.tec = tec;
		this.coordinatorServiceImpl = coordinatorServiceImpl;
		// commenting out, dont want to start a new thread per tracker on the coordinator...
		//this.th = new Thread((Runnable) this, "Light tracker " + te.getComponent());
		//this.th.start();
	}

	@Override
	public long getCurrentTimeInMillis(){
		System.out.println("[TrajectoryEnvelopeTrackerLight] .. inside getCurrentTimeInMillis()");
		return tec.getCurrentTimeInMillis();
	}



	/**
	 * This method should return a {@link RobotReport}, describing the current state of the robot.
	 * @return A {@link RobotReport}, describing the current state of the robot.
	 */

	public RobotReport getRobotReport(int robotID) {
		return coordinatorServiceImpl.robotIDtoRobotReport.get(robotID);
	}

	/**
	 * This method should implement the mechanisms for notifying a robot of a new critical point.
	 * @param criticalPoint The critical point to set (index of pose along the reference trajectory
	 * beyond which the robot may not navigate).
	 */


	public void setCriticalPoint(int criticalPoint) {
		//call your service to set the critical point
		System.out.println("in CP()");
	}

	/**
	 * Instructs the {@link TrajectoryEnvelopeSolver} that the robot has ceased to be parked here.
	 */
	public void finishParking() {
		this.parkingFinished = true;
		synchronized(th) {
			th.notify();
		}
	}


	// .. may need to do this, shouldnt have to though but problem of robotReport being null on a Light tracker is occuring..
	public void setRobotReport(RobotReport r){
		//this
	}


	public boolean isParkingFinished() {
		return parkingFinished;
	}

	public void setUseInternalCriticalPoints(boolean value) {
		this.useInternalCPs = value;
	}

}

