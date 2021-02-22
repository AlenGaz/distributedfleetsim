package GRPC;

import io.grpc.hellos.Hello;
import io.grpc.stub.StreamObserver;

public interface HelloWorldServiceImpls {
    void grobotReport(Hello.getRobotReportGrpc request,
                      StreamObserver<Hello.robotID> responseObserver);
}
