package CoordinatorPackage.containers;

import org.metacsp.multi.allenInterval.AllenIntervalConstraint;

import java.io.Serializable;

public class tecAllenIntervalContainer implements Serializable {

    /**
     * Used to serialize an AllenIntervalConstraint for
     * getSolver addConstraint/removeConstraint. The parameter will be networked and done
     * in the coordinator
     */

    public tecAllenIntervalContainer(AllenIntervalConstraint aic){
        this.allenIntervalConstraint = aic;
    }

    private AllenIntervalConstraint allenIntervalConstraint;

    public AllenIntervalConstraint getAllenIntervalConstraint() {
        return allenIntervalConstraint;
    }

    public void setAllenIntervalConstraint(AllenIntervalConstraint allenIntervalConstraint) {
        this.allenIntervalConstraint = allenIntervalConstraint;
    }
}
