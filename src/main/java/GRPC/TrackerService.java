package GRPC;

import io.grpc.hellos.Tracker;
import io.grpc.hellos.TrackerServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;

public class TrackerService extends TrackerServiceGrpc.TrackerServiceImplBase {
    public ArrayList ID_list = new ArrayList();
    String text;

    public void requestWithGetCriticalPoint (
            Tracker.getCriticalPointRequestMessage request, StreamObserver<Tracker.getCriticalPointResponseMessage> responseObserver) {

        //if(request.getRobotID() == request.getRobotID()) {
            //text = "Hello robot:" + request.getValue();
            //System.out.println("Robot id: " + request.getRobotID());
            System.out.println("Critical Point: " + request.getCriticalPoint());
            //ID_list.add(request.getCriticalPoint()); ////// if id not in list, append
            //System.out.println("The CriticalPoint: " + ID_list);

            respondWithSendingIDreceived(responseObserver);
        //}
    }


    private void respondWithSendingIDreceived(StreamObserver<Tracker.getCriticalPointResponseMessage> responseObserver) {
        Tracker.getCriticalPointResponseMessage response = Tracker.getCriticalPointResponseMessage.newBuilder().setCriticalPoint(77).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    } //Generic response to stub
}

