package se.oru.coordination.coordination_oru;

import org.metacsp.multi.spatioTemporal.paths.Pose;

public class clientConnection {
    public String type;
    public String connection;
    public String timeStamp;


    public clientConnection(String type, String connection, String timeStamp) {
        this.type = type;
        this.connection = connection;
        this.timeStamp = timeStamp;
    }
}
