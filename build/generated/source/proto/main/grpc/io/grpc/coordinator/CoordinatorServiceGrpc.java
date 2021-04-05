package io.grpc.coordinator;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: coordinator.proto")
public final class CoordinatorServiceGrpc {

  private CoordinatorServiceGrpc() {}

  public static final String SERVICE_NAME = "gradlegRPC.CoordinatorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.requestrobotreport,
      io.grpc.coordinator.Coordinator.responserobotreport> getCoordinatorrobotreportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "coordinatorrobotreport",
      requestType = io.grpc.coordinator.Coordinator.requestrobotreport.class,
      responseType = io.grpc.coordinator.Coordinator.responserobotreport.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.requestrobotreport,
      io.grpc.coordinator.Coordinator.responserobotreport> getCoordinatorrobotreportMethod() {
    io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.requestrobotreport, io.grpc.coordinator.Coordinator.responserobotreport> getCoordinatorrobotreportMethod;
    if ((getCoordinatorrobotreportMethod = CoordinatorServiceGrpc.getCoordinatorrobotreportMethod) == null) {
      synchronized (CoordinatorServiceGrpc.class) {
        if ((getCoordinatorrobotreportMethod = CoordinatorServiceGrpc.getCoordinatorrobotreportMethod) == null) {
          CoordinatorServiceGrpc.getCoordinatorrobotreportMethod = getCoordinatorrobotreportMethod =
              io.grpc.MethodDescriptor.<io.grpc.coordinator.Coordinator.requestrobotreport, io.grpc.coordinator.Coordinator.responserobotreport>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "coordinatorrobotreport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.requestrobotreport.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.responserobotreport.getDefaultInstance()))
              .setSchemaDescriptor(new CoordinatorServiceMethodDescriptorSupplier("coordinatorrobotreport"))
              .build();
        }
      }
    }
    return getCoordinatorrobotreportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointRequestMessage,
      io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointResponseMessage> getCoordinatorcriticalpointMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "coordinatorcriticalpoint",
      requestType = io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointRequestMessage.class,
      responseType = io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointResponseMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointRequestMessage,
      io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointResponseMessage> getCoordinatorcriticalpointMethod() {
    io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointRequestMessage, io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointResponseMessage> getCoordinatorcriticalpointMethod;
    if ((getCoordinatorcriticalpointMethod = CoordinatorServiceGrpc.getCoordinatorcriticalpointMethod) == null) {
      synchronized (CoordinatorServiceGrpc.class) {
        if ((getCoordinatorcriticalpointMethod = CoordinatorServiceGrpc.getCoordinatorcriticalpointMethod) == null) {
          CoordinatorServiceGrpc.getCoordinatorcriticalpointMethod = getCoordinatorcriticalpointMethod =
              io.grpc.MethodDescriptor.<io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointRequestMessage, io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointResponseMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "coordinatorcriticalpoint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointRequestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointResponseMessage.getDefaultInstance()))
              .setSchemaDescriptor(new CoordinatorServiceMethodDescriptorSupplier("coordinatorcriticalpoint"))
              .build();
        }
      }
    }
    return getCoordinatorcriticalpointMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.getCurrentDependencies,
      io.grpc.coordinator.Coordinator.noneResponse> getCoordinatordependenciesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "coordinatordependencies",
      requestType = io.grpc.coordinator.Coordinator.getCurrentDependencies.class,
      responseType = io.grpc.coordinator.Coordinator.noneResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.getCurrentDependencies,
      io.grpc.coordinator.Coordinator.noneResponse> getCoordinatordependenciesMethod() {
    io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.getCurrentDependencies, io.grpc.coordinator.Coordinator.noneResponse> getCoordinatordependenciesMethod;
    if ((getCoordinatordependenciesMethod = CoordinatorServiceGrpc.getCoordinatordependenciesMethod) == null) {
      synchronized (CoordinatorServiceGrpc.class) {
        if ((getCoordinatordependenciesMethod = CoordinatorServiceGrpc.getCoordinatordependenciesMethod) == null) {
          CoordinatorServiceGrpc.getCoordinatordependenciesMethod = getCoordinatordependenciesMethod =
              io.grpc.MethodDescriptor.<io.grpc.coordinator.Coordinator.getCurrentDependencies, io.grpc.coordinator.Coordinator.noneResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "coordinatordependencies"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.getCurrentDependencies.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.noneResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CoordinatorServiceMethodDescriptorSupplier("coordinatordependencies"))
              .build();
        }
      }
    }
    return getCoordinatordependenciesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.robotsGreeting,
      io.grpc.coordinator.Coordinator.robotgreetingResponse> getCoordinatorgetGreetingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "coordinatorgetGreeting",
      requestType = io.grpc.coordinator.Coordinator.robotsGreeting.class,
      responseType = io.grpc.coordinator.Coordinator.robotgreetingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.robotsGreeting,
      io.grpc.coordinator.Coordinator.robotgreetingResponse> getCoordinatorgetGreetingMethod() {
    io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.robotsGreeting, io.grpc.coordinator.Coordinator.robotgreetingResponse> getCoordinatorgetGreetingMethod;
    if ((getCoordinatorgetGreetingMethod = CoordinatorServiceGrpc.getCoordinatorgetGreetingMethod) == null) {
      synchronized (CoordinatorServiceGrpc.class) {
        if ((getCoordinatorgetGreetingMethod = CoordinatorServiceGrpc.getCoordinatorgetGreetingMethod) == null) {
          CoordinatorServiceGrpc.getCoordinatorgetGreetingMethod = getCoordinatorgetGreetingMethod =
              io.grpc.MethodDescriptor.<io.grpc.coordinator.Coordinator.robotsGreeting, io.grpc.coordinator.Coordinator.robotgreetingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "coordinatorgetGreeting"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.robotsGreeting.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.robotgreetingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CoordinatorServiceMethodDescriptorSupplier("coordinatorgetGreeting"))
              .build();
        }
      }
    }
    return getCoordinatorgetGreetingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CoordinatorServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CoordinatorServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CoordinatorServiceStub>() {
        @java.lang.Override
        public CoordinatorServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CoordinatorServiceStub(channel, callOptions);
        }
      };
    return CoordinatorServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CoordinatorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CoordinatorServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CoordinatorServiceBlockingStub>() {
        @java.lang.Override
        public CoordinatorServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CoordinatorServiceBlockingStub(channel, callOptions);
        }
      };
    return CoordinatorServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CoordinatorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CoordinatorServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CoordinatorServiceFutureStub>() {
        @java.lang.Override
        public CoordinatorServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CoordinatorServiceFutureStub(channel, callOptions);
        }
      };
    return CoordinatorServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CoordinatorServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void coordinatorrobotreport(io.grpc.coordinator.Coordinator.requestrobotreport request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.responserobotreport> responseObserver) {
      asyncUnimplementedUnaryCall(getCoordinatorrobotreportMethod(), responseObserver);
    }

    /**
     */
    public void coordinatorcriticalpoint(io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointRequestMessage request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointResponseMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getCoordinatorcriticalpointMethod(), responseObserver);
    }

    /**
     */
    public void coordinatordependencies(io.grpc.coordinator.Coordinator.getCurrentDependencies request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.noneResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCoordinatordependenciesMethod(), responseObserver);
    }

    /**
     * <pre>
     *google.protobuf.Empty
     * </pre>
     */
    public void coordinatorgetGreeting(io.grpc.coordinator.Coordinator.robotsGreeting request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.robotgreetingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCoordinatorgetGreetingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCoordinatorrobotreportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.coordinator.Coordinator.requestrobotreport,
                io.grpc.coordinator.Coordinator.responserobotreport>(
                  this, METHODID_COORDINATORROBOTREPORT)))
          .addMethod(
            getCoordinatorcriticalpointMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointRequestMessage,
                io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointResponseMessage>(
                  this, METHODID_COORDINATORCRITICALPOINT)))
          .addMethod(
            getCoordinatordependenciesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.coordinator.Coordinator.getCurrentDependencies,
                io.grpc.coordinator.Coordinator.noneResponse>(
                  this, METHODID_COORDINATORDEPENDENCIES)))
          .addMethod(
            getCoordinatorgetGreetingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.coordinator.Coordinator.robotsGreeting,
                io.grpc.coordinator.Coordinator.robotgreetingResponse>(
                  this, METHODID_COORDINATORGET_GREETING)))
          .build();
    }
  }

  /**
   */
  public static final class CoordinatorServiceStub extends io.grpc.stub.AbstractAsyncStub<CoordinatorServiceStub> {
    private CoordinatorServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoordinatorServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CoordinatorServiceStub(channel, callOptions);
    }

    /**
     */
    public void coordinatorrobotreport(io.grpc.coordinator.Coordinator.requestrobotreport request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.responserobotreport> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoordinatorrobotreportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void coordinatorcriticalpoint(io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointRequestMessage request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointResponseMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoordinatorcriticalpointMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void coordinatordependencies(io.grpc.coordinator.Coordinator.getCurrentDependencies request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.noneResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoordinatordependenciesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *google.protobuf.Empty
     * </pre>
     */
    public void coordinatorgetGreeting(io.grpc.coordinator.Coordinator.robotsGreeting request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.robotgreetingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoordinatorgetGreetingMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CoordinatorServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CoordinatorServiceBlockingStub> {
    private CoordinatorServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoordinatorServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CoordinatorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.coordinator.Coordinator.responserobotreport coordinatorrobotreport(io.grpc.coordinator.Coordinator.requestrobotreport request) {
      return blockingUnaryCall(
          getChannel(), getCoordinatorrobotreportMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointResponseMessage coordinatorcriticalpoint(io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointRequestMessage request) {
      return blockingUnaryCall(
          getChannel(), getCoordinatorcriticalpointMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.coordinator.Coordinator.noneResponse coordinatordependencies(io.grpc.coordinator.Coordinator.getCurrentDependencies request) {
      return blockingUnaryCall(
          getChannel(), getCoordinatordependenciesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *google.protobuf.Empty
     * </pre>
     */
    public io.grpc.coordinator.Coordinator.robotgreetingResponse coordinatorgetGreeting(io.grpc.coordinator.Coordinator.robotsGreeting request) {
      return blockingUnaryCall(
          getChannel(), getCoordinatorgetGreetingMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CoordinatorServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CoordinatorServiceFutureStub> {
    private CoordinatorServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoordinatorServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CoordinatorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.coordinator.Coordinator.responserobotreport> coordinatorrobotreport(
        io.grpc.coordinator.Coordinator.requestrobotreport request) {
      return futureUnaryCall(
          getChannel().newCall(getCoordinatorrobotreportMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointResponseMessage> coordinatorcriticalpoint(
        io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointRequestMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getCoordinatorcriticalpointMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.coordinator.Coordinator.noneResponse> coordinatordependencies(
        io.grpc.coordinator.Coordinator.getCurrentDependencies request) {
      return futureUnaryCall(
          getChannel().newCall(getCoordinatordependenciesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *google.protobuf.Empty
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.coordinator.Coordinator.robotgreetingResponse> coordinatorgetGreeting(
        io.grpc.coordinator.Coordinator.robotsGreeting request) {
      return futureUnaryCall(
          getChannel().newCall(getCoordinatorgetGreetingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COORDINATORROBOTREPORT = 0;
  private static final int METHODID_COORDINATORCRITICALPOINT = 1;
  private static final int METHODID_COORDINATORDEPENDENCIES = 2;
  private static final int METHODID_COORDINATORGET_GREETING = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CoordinatorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CoordinatorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_COORDINATORROBOTREPORT:
          serviceImpl.coordinatorrobotreport((io.grpc.coordinator.Coordinator.requestrobotreport) request,
              (io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.responserobotreport>) responseObserver);
          break;
        case METHODID_COORDINATORCRITICALPOINT:
          serviceImpl.coordinatorcriticalpoint((io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointRequestMessage) request,
              (io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.coordinatorGetCriticalPointResponseMessage>) responseObserver);
          break;
        case METHODID_COORDINATORDEPENDENCIES:
          serviceImpl.coordinatordependencies((io.grpc.coordinator.Coordinator.getCurrentDependencies) request,
              (io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.noneResponse>) responseObserver);
          break;
        case METHODID_COORDINATORGET_GREETING:
          serviceImpl.coordinatorgetGreeting((io.grpc.coordinator.Coordinator.robotsGreeting) request,
              (io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.robotgreetingResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CoordinatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CoordinatorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.coordinator.Coordinator.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CoordinatorService");
    }
  }

  private static final class CoordinatorServiceFileDescriptorSupplier
      extends CoordinatorServiceBaseDescriptorSupplier {
    CoordinatorServiceFileDescriptorSupplier() {}
  }

  private static final class CoordinatorServiceMethodDescriptorSupplier
      extends CoordinatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CoordinatorServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CoordinatorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CoordinatorServiceFileDescriptorSupplier())
              .addMethod(getCoordinatorrobotreportMethod())
              .addMethod(getCoordinatorcriticalpointMethod())
              .addMethod(getCoordinatordependenciesMethod())
              .addMethod(getCoordinatorgetGreetingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
