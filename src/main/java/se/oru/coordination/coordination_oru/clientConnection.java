package se.oru.coordination.coordination_oru;

import Launch.MakeFootPrint;
import org.metacsp.multi.spatioTemporal.paths.Pose;

public class clientConnection {

    private String type;
    private String ip;
    private int port;
    private Pose rpose;
    private String timeStamp;
    private double maxAccel;
    private double maxVel;
    private double trackingPeriodInMillis;
    private MakeFootPrint footPrint;

    public clientConnection(String type, String ip, int port, Pose pose,
                            String timeStamp, double maxAccel, double maxVel,
                            double trackingPeriodInMillis, MakeFootPrint footPrint) {
        this.setType(type);
        this.setIp(ip);
        this.setTimeStamp(timeStamp);
        this.setMaxAccel(maxAccel);
        this.setMaxVel(maxVel);
        this.setTrackingPeriodInMillis(trackingPeriodInMillis);
        this.setFootPrint(footPrint);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Pose getRpose() {
        return rpose;
    }

    public void setRpose(Pose rpose) {
        this.rpose = rpose;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getMaxAccel() {
        return maxAccel;
    }

    public void setMaxAccel(double maxAccel) {
        this.maxAccel = maxAccel;
    }

    public double getMaxVel() {
        return maxVel;
    }

    public void setMaxVel(double maxVel) {
        this.maxVel = maxVel;
    }

    public double getTrackingPeriodInMillis() {
        return trackingPeriodInMillis;
    }

    public void setTrackingPeriodInMillis(double trackingPeriodInMillis) {
        this.trackingPeriodInMillis = trackingPeriodInMillis;
    }

    public MakeFootPrint getFootPrint() {
        return footPrint;
    }

    public void setFootPrint(MakeFootPrint footPrint) {
        this.footPrint = footPrint;
    }
}
