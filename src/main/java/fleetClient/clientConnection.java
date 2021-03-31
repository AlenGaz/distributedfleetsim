package fleetClient;

import org.metacsp.multi.spatioTemporal.paths.Pose;

public class clientConnection {
    public String type;
    public String connection;
    public String timeStamp;
    public Integer port;

    public clientConnection(String type, String connection, String timeStamp, Integer port) {
        this.type = type;
        this.connection = connection;
        this.timeStamp = timeStamp;
        this.port = port;
    }
}
