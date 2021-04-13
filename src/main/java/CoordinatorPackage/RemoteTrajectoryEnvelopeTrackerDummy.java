package CoordinatorPackage;

import fleetClient.RemoteAbstractTrajectoryEnvelopeTracker;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelope;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelopeSolver;
import se.oru.coordination.coordination_oru.RobotReport;
import se.oru.coordination.coordination_oru.TrackingCallback;

/**
 * This class is used as a Dummy Tracker that is on the Coordinator Server for just setting a tracker for a parked robot
 * This should be 0 computational cost and is just for following the structure of the previous Abstract...Coordinator when
 * changing it to a RemoteAbstract...Coordinator
 *
 * author: Alen G
 *
 */
public abstract class RemoteTrajectoryEnvelopeTrackerDummy extends TrajectoryEnvelopeTrackerLight implements Runnable {

    private Thread th = null;
    private boolean parkingFinished = false;
    private int currentIndex = -1;
    private long DELTA_FUTURE = 0;
    public RemoteAbstractTrajectoryEnvelopeCoordinator tec; ///OKAY Because DummyTracker in the Coordinator can pass its RemoteAbstract..Coordinator instance..

    /**
     * Create a new {@link RemoteTrajectoryEnvelopeTrackerDummy} representing that a robot with a given ID is parked
     * in a particular {@link TrajectoryEnvelope}.
     * @param te The {@link TrajectoryEnvelope} where the robot is parked.
     * @param timeStep The period at which the tracker should check whether it has "finished" parking.
     * @param temporalResolution The temporal resolution in which the timeStep is expressed.
     * @param //solver The {@link TrajectoryEnvelopeSolver} that maintains the parking {@link TrajectoryEnvelope}.
     * @param cb An optional callback that will be called during tracking.
     */
    public RemoteTrajectoryEnvelopeTrackerDummy(TrajectoryEnvelope te, int timeStep, double temporalResolution, RemoteAbstractTrajectoryEnvelopeCoordinator tec, TrackingCallback cb) {
        super();
        //super(te, temporalResolution, timeStep, cb);
        this.te = te;
        this.traj = te.getTrajectory();
        this.temporalResolution = temporalResolution;
        this.th = new Thread(this, "Parking tracker " + te.getComponent());
        this.th.start();
    }

    @Override
    protected void onTrajectoryEnvelopeUpdate(TrajectoryEnvelope te) { }

    @Override
    public void startTracking() { }

    @Override
    public void setCriticalPoint(int criticalPointToSet) { }

    @Override
    public RobotReport getRobotReport() {
        return new RobotReport(te.getRobotID(), traj.getPose()[0], currentIndex, 0.0, 0.0, -1);
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

    /**
     * Assesses whether the robot is still parked here.
     * @return <code>true</code> iff the robot is still parked.
     */
    public boolean isParkingFinished() {
        return parkingFinished;
    }

//	private boolean canInterrupt() {
//		for (AllenIntervalConstraint meets : getConstriants(AllenIntervalConstraint.Type.Meets, te, tec.getSolver())) {
//			TrajectoryEnvelope driving = (TrajectoryEnvelope)meets.getTo();
//			AllenIntervalConstraint[] startsCons = getConstriants(AllenIntervalConstraint.Type.Starts, driving, tec.getSolver());
//			TrajectoryEnvelope drivingFirstSubEnv = driving;
//			if (startsCons != null && startsCons.length > 0) drivingFirstSubEnv = (TrajectoryEnvelope)startsCons[0].getFrom();
//			for (AllenIntervalConstraint before : getConstriants(AllenIntervalConstraint.Type.BeforeOrMeets, drivingFirstSubEnv, tec.getSolver())) {
//				//All the befores that involve the next driving
//				if (before.getTo().equals(drivingFirstSubEnv)) {
//					//Someone depends on when this parking finishes, so cannot interrupt!
//					metaCSPLogger.info("Robot " + te.getRobotID() + " should wait to end parking: " + before);
//					return false;
//				}
//			}
//		}
//		//Nobody depends on when this parking finishes, so can interrupt now...
//		metaCSPLogger.info("Robot " + te.getRobotID() + " can end parking (end time bounds are [" + te.getTemporalVariable().getEET() + ", " + te.getTemporalVariable().getLET() + "])");
//		return true;
//	}

    @Override
    public void run() {

        //Just do prolong the earliest end time until finished by external call to finishParking()
        metaCSPLogger.info("Parking starts for Robot " + te.getRobotID());
        updateDeadline(this.te, DELTA_FUTURE);
        onPositionUpdate();
		/*while (!parkingFinished) {
			updateDeadline(this.te, DELTA_FUTURE);
			onPositionUpdate();
			try { Thread.sleep(trackingPeriodInMillis); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}*/
        synchronized(th) {
            try {
                th.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                metaCSPLogger.severe(e.toString());
            }
        }
        metaCSPLogger.info("Parking finishes for Robot " + te.getRobotID());

        fixDeadline(te, 0);

//		//Now wait until earliest end time (as there could be some driving envelope after this which should not start)
//		while (this.te.getTemporalVariable().getEET() > getCurrentTimeInMillis()) {
//
//			//Find out if nobody is waiting, in which case break early!
//			synchronized(solver) {
//				if (canInterrupt()) {
//					fixDeadline(te, 0);
//					break;
//				}
//			}
//
//			try { Thread.sleep(trackingPeriodInMillis); }
//			catch (InterruptedException e) { e.printStackTrace(); }
//		}

        //Deadline will now be fixed (by superclass) so it is decided that this parking cannot be prolonged
        //(and thus scheduling will not be able to move FW any driving envelopes that are met by this parking)

        //Signals thread in abstract tracker to stop
        currentIndex++;

    }

    @Override
    public void onPositionUpdate() {
        if (tec.getVisualization() != null) tec.getVisualization().displayRobotState(te.getFootprint(), getRobotReport());
    }


}