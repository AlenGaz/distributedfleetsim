package CoordinatorPackage;

import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import io.grpc.coordinator.CoordinatorServiceGrpc;
import io.grpc.hellos.Client;
import io.grpc.hellos.fleetClientGrpc;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import se.oru.coordination.coordination_oru.RobotReport;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CoordinatorClient {

    private static final Logger logger = Logger.getLogger(CoordinatorClient.class.getName());
    private final CoordinatorServiceGrpc.CoordinatorServiceBlockingStub blockingStub;
    private final fleetClientGrpc.fleetClientBlockingStub blockingStubTracker;



    public CoordinatorClient(Channel channel) {
        blockingStub = CoordinatorServiceGrpc.newBlockingStub(channel);
        blockingStubTracker = fleetClientGrpc.newBlockingStub(channel);
    }


    public RobotReport makeRobotReportRequest(Integer robotID) {
        Client.coordinatorrequestrobotid request = Client.coordinatorrequestrobotid.newBuilder()
                .setReq("requestrobotreport").setRobotid(robotID).build();     // Hardcoded what message is sent to the service "requestrobotreport", robotID is parameter
        Client.coordinatorrespondrobotreport response = null;

        System.out.println("[CoordinatorClient] Making request! " + request);
        Pose _pose;
        RobotReport rR = null;
        try {

            response = blockingStubTracker.coordinatorrobotreport(request);

            _pose = new Pose(response.getX(), response.getY(), response.getZ(), response.getRoll(), response.getPitch(), response.getYaw());
            rR = new RobotReport(robotID, _pose, response.getPathIndex(), response.getVelocity(), response.getDistanceTraveled(), response.getCriticalPoint());

        }
        catch (StatusRuntimeException e) {

            logger.log(Level.WARNING, "Rpc Failed: {0}", e.getStatus());
        }
        System.out.println("[Coordinator Client] Logging response of Coordinator RobotReport Request ... \n [[[[" + response + "]]]] \n");

        return rR;
    }

}
