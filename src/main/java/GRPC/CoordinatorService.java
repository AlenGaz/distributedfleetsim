package GRPC;

import aima.core.util.datastructure.Triplet;
import io.grpc.hellos.Coordinator;
import io.grpc.hellos.CoordinatorServiceGrpc;
import io.grpc.stub.StreamObserver;
import Coordinator.AbstractTrajectoryEnvelopeCoordinator;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import se.oru.coordination.coordination_oru.RobotReport;
import se.oru.coordination.coordination_oru.simulation2D.TrajectoryEnvelopeCoordinatorSimulation;

import java.util.ArrayList;
import java.util.HashMap;import aima.core.util.datastructure.Triplet;
import io.grpc.hellos.*;
import io.grpc.hellos.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.metacsp.multi.spatioTemporal.paths.Pose;
import se.oru.coordination.coordination_oru.RobotReport;
import se.oru.coordination.coordination_oru.simulation2D.TrajectoryEnvelopeCoordinatorSimulation;

import java.util.ArrayList;
import java.util.HashMap;

public class CoordinatorService extends CoordinatorServiceGrpc.CoordinatorServiceImplBase {

}
