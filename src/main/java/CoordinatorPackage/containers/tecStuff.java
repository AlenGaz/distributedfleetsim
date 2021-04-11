package CoordinatorPackage.containers;

import se.oru.coordination.coordination_oru.util.FleetVisualization;
import org.metacsp.multi.spatioTemporal.paths.TrajectoryEnvelopeSolver;
import se.oru.coordination.coordination_oru.Dependency;

import java.io.Serializable;
import java.util.HashMap;

public class tecStuff implements Serializable{

    /***
     *
     * A container for serializing necessary data for that is exchanged via messaging between fleetClients and CoordinatorServer
     *
     */

    private long currentTimeInM;
    private TrajectoryEnvelopeSolver teSolver;
    private FleetVisualization fleetVisualizer;
    private HashMap<Integer, Dependency> currDependencies;

    //maybe private AbstractTrajectoryEnvelopeTracker trak

    public tecStuff(int robotID){
    }

    public long getCurrentTimeInM() {
        return currentTimeInM;
    }

    public void setCurrentTimeInMill(long currentTimeInMill) {
        this.currentTimeInM = currentTimeInMill;
    }

    public TrajectoryEnvelopeSolver getTeSolver() {
        return teSolver;
    }

    public void setTeSolver(TrajectoryEnvelopeSolver teSolver) {
        this.teSolver = teSolver;
    }

    public FleetVisualization getFleetVisualizer() {
        return fleetVisualizer;
    }

    public void setFleetVisualizer(FleetVisualization fleetVisualizer) {
        this.fleetVisualizer = fleetVisualizer;
    }

    public HashMap<Integer, Dependency> getCurrDependencies() {
        return currDependencies;
    }

    public void setCurrDependencies(HashMap<Integer, Dependency> currDependencies) {
        this.currDependencies = currDependencies;
    }






}

