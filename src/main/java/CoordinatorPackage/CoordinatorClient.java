package CoordinatorPackage;

import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import io.grpc.coordinator.CoordinatorServiceGrpc;


import io.grpc.fleetClients.FleetClientsServiceGrpc;
import io.grpc.fleetClients.Fleetclients;

import java.util.logging.Level;
import java.util.logging.Logger;


public class CoordinatorClient {

    private static final Logger logger = Logger.getLogger(CoordinatorClient.class.getName());
    private final CoordinatorServiceGrpc.CoordinatorServiceBlockingStub blockingStub;
    private final FleetClientsServiceGrpc.FleetClientsServiceBlockingStub blockingStubClient;


    public CoordinatorClient(Channel channel) {
        blockingStub = CoordinatorServiceGrpc.newBlockingStub(channel);
        blockingStubClient = FleetClientsServiceGrpc.newBlockingStub(channel);
    }

/*
    public RobotReport makeRobotReportRequest(Integer robotID) {
        Fleetclients.coordinatorrequestrobotid request = Fleetclients.coordinatorrequestrobotid.newBuilder()
                .setReq("requestrobotreport").setRobotid(robotID).build();     // Hardcoded what message is sent to the service "requestrobotreport", robotID is parameter
        Fleetclients.coordinatorrespondrobotreport response = null;

        //System.out.println("[CoordinatorClient] Making request! " + request);
        Pose _pose;
        RobotReport rR = null;
        try {

            response = blockingStubClient.coordinatorrobotreport(request);

            _pose = new Pose(response.getX(), response.getY(), response.getZ(), response.getRoll(), response.getPitch(), response.getYaw());
            rR = new RobotReport(robotID, _pose, response.getPathIndex(), response.getVelocity(), response.getDistanceTraveled(), response.getCriticalPoint());
        } catch (StatusRuntimeException e) {

            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());
        }
        //System.out.println("[Coordinator Client] Logging response of Coordinator RobotReport Request ... \n [[[[" + response + "]]]] \n");

        return rR;
    }
*/

    public int makeCriticalPointreq(String myreq, int robotID) {
        Fleetclients.clientGetCriticalPointRequestMessage req = Fleetclients.clientGetCriticalPointRequestMessage
                .newBuilder().setKan(myreq).setRobotID(robotID).build();
        System.out.println("request in CC");
        Fleetclients.clientGetCriticalPointResponseMessage clientRsp = null;
        System.out.println("response in CC");


        try {
            System.out.println("[COORD C] sending criticalpoint request from robotid: " + robotID);
            //TODO stoops here
            clientRsp = blockingStubClient.clientCriticalpoint(req);
            System.out.println("After trackerRsp Stub");
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());

        }

        int cp = clientRsp.getCriticalPoint();
        return cp=20;
    }

}