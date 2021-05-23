package Launch;

import com.vividsolutions.jts.geom.Coordinate;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import org.metacsp.multi.spatioTemporal.paths.PoseSteering;

public class robotConfig{

    private Pose startPose;
    private Pose endPose;

    public PoseSteering[] getPath() {
        return path;
    }

    public void setPath(PoseSteering[] path) {
        this.path = path;
    }

    private PoseSteering[] path;

    public Pose getStartPose() {
        return startPose;
    }

    public void setStartPose(Pose startPose) {
        this.startPose = startPose;
    }

    private PoseSteering[] pathInv;
    private Coordinate[] footprint;




    public PoseSteering[] getPathInv() {
        return pathInv;
    }

    public void setPathInv(PoseSteering[] pathInv) {
        this.pathInv = pathInv;
    }

   public robotConfig(Pose startPose, Pose endPose, PoseSteering[] path, PoseSteering[] pathInv, Coordinate[] footprint){
       setEndPose(endPose);
       setPathInv(pathInv);
       setFootprint(footprint);
       setStartPose(startPose);
       setPath(path);
    }

    public Pose getEndPose() {
        return endPose;
    }

    public void setEndPose(Pose endPose) {
        this.endPose = endPose;
    }

    public Coordinate[] getFootprint() {
        return footprint;
    }

    public void setFootprint(Coordinate[] footprint) {
        this.footprint = footprint;
    }
}

