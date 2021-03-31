package io.grpc.fleetClients;

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
    comments = "Source: fleetclients.proto")
public final class FleetClientsServiceGrpc {

  private FleetClientsServiceGrpc() {}

  public static final String SERVICE_NAME = "gradlegRPC.FleetClientsService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.getCriticalPointRequestMessage,
      io.grpc.fleetClients.Fleetclients.getCriticalPointResponseMessage> getGCriticalPointMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "gCriticalPoint",
      requestType = io.grpc.fleetClients.Fleetclients.getCriticalPointRequestMessage.class,
      responseType = io.grpc.fleetClients.Fleetclients.getCriticalPointResponseMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.getCriticalPointRequestMessage,
      io.grpc.fleetClients.Fleetclients.getCriticalPointResponseMessage> getGCriticalPointMethod() {
    io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.getCriticalPointRequestMessage, io.grpc.fleetClients.Fleetclients.getCriticalPointResponseMessage> getGCriticalPointMethod;
    if ((getGCriticalPointMethod = FleetClientsServiceGrpc.getGCriticalPointMethod) == null) {
      synchronized (FleetClientsServiceGrpc.class) {
        if ((getGCriticalPointMethod = FleetClientsServiceGrpc.getGCriticalPointMethod) == null) {
          FleetClientsServiceGrpc.getGCriticalPointMethod = getGCriticalPointMethod =
              io.grpc.MethodDescriptor.<io.grpc.fleetClients.Fleetclients.getCriticalPointRequestMessage, io.grpc.fleetClients.Fleetclients.getCriticalPointResponseMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "gCriticalPoint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.fleetClients.Fleetclients.getCriticalPointRequestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.fleetClients.Fleetclients.getCriticalPointResponseMessage.getDefaultInstance()))
              .setSchemaDescriptor(new FleetClientsServiceMethodDescriptorSupplier("gCriticalPoint"))
              .build();
        }
      }
    }
    return getGCriticalPointMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.requestMessage,
      io.grpc.fleetClients.Fleetclients.responseMessage> getMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "message",
      requestType = io.grpc.fleetClients.Fleetclients.requestMessage.class,
      responseType = io.grpc.fleetClients.Fleetclients.responseMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.requestMessage,
      io.grpc.fleetClients.Fleetclients.responseMessage> getMessageMethod() {
    io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.requestMessage, io.grpc.fleetClients.Fleetclients.responseMessage> getMessageMethod;
    if ((getMessageMethod = FleetClientsServiceGrpc.getMessageMethod) == null) {
      synchronized (FleetClientsServiceGrpc.class) {
        if ((getMessageMethod = FleetClientsServiceGrpc.getMessageMethod) == null) {
          FleetClientsServiceGrpc.getMessageMethod = getMessageMethod =
              io.grpc.MethodDescriptor.<io.grpc.fleetClients.Fleetclients.requestMessage, io.grpc.fleetClients.Fleetclients.responseMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "message"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.fleetClients.Fleetclients.requestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.fleetClients.Fleetclients.responseMessage.getDefaultInstance()))
              .setSchemaDescriptor(new FleetClientsServiceMethodDescriptorSupplier("message"))
              .build();
        }
      }
    }
    return getMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.getRobotID,
      io.grpc.fleetClients.Fleetclients.robotID> getGrobotIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "grobotID",
      requestType = io.grpc.fleetClients.Fleetclients.getRobotID.class,
      responseType = io.grpc.fleetClients.Fleetclients.robotID.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.getRobotID,
      io.grpc.fleetClients.Fleetclients.robotID> getGrobotIDMethod() {
    io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.getRobotID, io.grpc.fleetClients.Fleetclients.robotID> getGrobotIDMethod;
    if ((getGrobotIDMethod = FleetClientsServiceGrpc.getGrobotIDMethod) == null) {
      synchronized (FleetClientsServiceGrpc.class) {
        if ((getGrobotIDMethod = FleetClientsServiceGrpc.getGrobotIDMethod) == null) {
          FleetClientsServiceGrpc.getGrobotIDMethod = getGrobotIDMethod =
              io.grpc.MethodDescriptor.<io.grpc.fleetClients.Fleetclients.getRobotID, io.grpc.fleetClients.Fleetclients.robotID>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "grobotID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.fleetClients.Fleetclients.getRobotID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.fleetClients.Fleetclients.robotID.getDefaultInstance()))
              .setSchemaDescriptor(new FleetClientsServiceMethodDescriptorSupplier("grobotID"))
              .build();
        }
      }
    }
    return getGrobotIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.robotReportRequest,
      io.grpc.fleetClients.Fleetclients.robotReportResponse> getGrobotReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "grobotReport",
      requestType = io.grpc.fleetClients.Fleetclients.robotReportRequest.class,
      responseType = io.grpc.fleetClients.Fleetclients.robotReportResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.robotReportRequest,
      io.grpc.fleetClients.Fleetclients.robotReportResponse> getGrobotReportMethod() {
    io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.robotReportRequest, io.grpc.fleetClients.Fleetclients.robotReportResponse> getGrobotReportMethod;
    if ((getGrobotReportMethod = FleetClientsServiceGrpc.getGrobotReportMethod) == null) {
      synchronized (FleetClientsServiceGrpc.class) {
        if ((getGrobotReportMethod = FleetClientsServiceGrpc.getGrobotReportMethod) == null) {
          FleetClientsServiceGrpc.getGrobotReportMethod = getGrobotReportMethod =
              io.grpc.MethodDescriptor.<io.grpc.fleetClients.Fleetclients.robotReportRequest, io.grpc.fleetClients.Fleetclients.robotReportResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "grobotReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.fleetClients.Fleetclients.robotReportRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.fleetClients.Fleetclients.robotReportResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FleetClientsServiceMethodDescriptorSupplier("grobotReport"))
              .build();
        }
      }
    }
    return getGrobotReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.coordinatorrequestrobotid,
      io.grpc.fleetClients.Fleetclients.coordinatorrespondrobotreport> getCoordinatorrobotreportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "coordinatorrobotreport",
      requestType = io.grpc.fleetClients.Fleetclients.coordinatorrequestrobotid.class,
      responseType = io.grpc.fleetClients.Fleetclients.coordinatorrespondrobotreport.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.coordinatorrequestrobotid,
      io.grpc.fleetClients.Fleetclients.coordinatorrespondrobotreport> getCoordinatorrobotreportMethod() {
    io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.coordinatorrequestrobotid, io.grpc.fleetClients.Fleetclients.coordinatorrespondrobotreport> getCoordinatorrobotreportMethod;
    if ((getCoordinatorrobotreportMethod = FleetClientsServiceGrpc.getCoordinatorrobotreportMethod) == null) {
      synchronized (FleetClientsServiceGrpc.class) {
        if ((getCoordinatorrobotreportMethod = FleetClientsServiceGrpc.getCoordinatorrobotreportMethod) == null) {
          FleetClientsServiceGrpc.getCoordinatorrobotreportMethod = getCoordinatorrobotreportMethod =
              io.grpc.MethodDescriptor.<io.grpc.fleetClients.Fleetclients.coordinatorrequestrobotid, io.grpc.fleetClients.Fleetclients.coordinatorrespondrobotreport>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "coordinatorrobotreport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.fleetClients.Fleetclients.coordinatorrequestrobotid.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.fleetClients.Fleetclients.coordinatorrespondrobotreport.getDefaultInstance()))
              .setSchemaDescriptor(new FleetClientsServiceMethodDescriptorSupplier("coordinatorrobotreport"))
              .build();
        }
      }
    }
    return getCoordinatorrobotreportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.clientGetCriticalPointRequestMessage,
      io.grpc.fleetClients.Fleetclients.clientGetCriticalPointResponseMessage> getClientCriticalpointMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "clientCriticalpoint",
      requestType = io.grpc.fleetClients.Fleetclients.clientGetCriticalPointRequestMessage.class,
      responseType = io.grpc.fleetClients.Fleetclients.clientGetCriticalPointResponseMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.clientGetCriticalPointRequestMessage,
      io.grpc.fleetClients.Fleetclients.clientGetCriticalPointResponseMessage> getClientCriticalpointMethod() {
    io.grpc.MethodDescriptor<io.grpc.fleetClients.Fleetclients.clientGetCriticalPointRequestMessage, io.grpc.fleetClients.Fleetclients.clientGetCriticalPointResponseMessage> getClientCriticalpointMethod;
    if ((getClientCriticalpointMethod = FleetClientsServiceGrpc.getClientCriticalpointMethod) == null) {
      synchronized (FleetClientsServiceGrpc.class) {
        if ((getClientCriticalpointMethod = FleetClientsServiceGrpc.getClientCriticalpointMethod) == null) {
          FleetClientsServiceGrpc.getClientCriticalpointMethod = getClientCriticalpointMethod =
              io.grpc.MethodDescriptor.<io.grpc.fleetClients.Fleetclients.clientGetCriticalPointRequestMessage, io.grpc.fleetClients.Fleetclients.clientGetCriticalPointResponseMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "clientCriticalpoint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.fleetClients.Fleetclients.clientGetCriticalPointRequestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.fleetClients.Fleetclients.clientGetCriticalPointResponseMessage.getDefaultInstance()))
              .setSchemaDescriptor(new FleetClientsServiceMethodDescriptorSupplier("clientCriticalpoint"))
              .build();
        }
      }
    }
    return getClientCriticalpointMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FleetClientsServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FleetClientsServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FleetClientsServiceStub>() {
        @java.lang.Override
        public FleetClientsServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FleetClientsServiceStub(channel, callOptions);
        }
      };
    return FleetClientsServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FleetClientsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FleetClientsServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FleetClientsServiceBlockingStub>() {
        @java.lang.Override
        public FleetClientsServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FleetClientsServiceBlockingStub(channel, callOptions);
        }
      };
    return FleetClientsServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FleetClientsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FleetClientsServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FleetClientsServiceFutureStub>() {
        @java.lang.Override
        public FleetClientsServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FleetClientsServiceFutureStub(channel, callOptions);
        }
      };
    return FleetClientsServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class FleetClientsServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void gCriticalPoint(io.grpc.fleetClients.Fleetclients.getCriticalPointRequestMessage request,
        io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.getCriticalPointResponseMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getGCriticalPointMethod(), responseObserver);
    }

    /**
     * <pre>
     *&#47;//////////////////////////
     * </pre>
     */
    public void message(io.grpc.fleetClients.Fleetclients.requestMessage request,
        io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.responseMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getMessageMethod(), responseObserver);
    }

    /**
     */
    public void grobotID(io.grpc.fleetClients.Fleetclients.getRobotID request,
        io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.robotID> responseObserver) {
      asyncUnimplementedUnaryCall(getGrobotIDMethod(), responseObserver);
    }

    /**
     */
    public void grobotReport(io.grpc.fleetClients.Fleetclients.robotReportRequest request,
        io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.robotReportResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGrobotReportMethod(), responseObserver);
    }

    /**
     */
    public void coordinatorrobotreport(io.grpc.fleetClients.Fleetclients.coordinatorrequestrobotid request,
        io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.coordinatorrespondrobotreport> responseObserver) {
      asyncUnimplementedUnaryCall(getCoordinatorrobotreportMethod(), responseObserver);
    }

    /**
     */
    public void clientCriticalpoint(io.grpc.fleetClients.Fleetclients.clientGetCriticalPointRequestMessage request,
        io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.clientGetCriticalPointResponseMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getClientCriticalpointMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGCriticalPointMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.fleetClients.Fleetclients.getCriticalPointRequestMessage,
                io.grpc.fleetClients.Fleetclients.getCriticalPointResponseMessage>(
                  this, METHODID_G_CRITICAL_POINT)))
          .addMethod(
            getMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.fleetClients.Fleetclients.requestMessage,
                io.grpc.fleetClients.Fleetclients.responseMessage>(
                  this, METHODID_MESSAGE)))
          .addMethod(
            getGrobotIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.fleetClients.Fleetclients.getRobotID,
                io.grpc.fleetClients.Fleetclients.robotID>(
                  this, METHODID_GROBOT_ID)))
          .addMethod(
            getGrobotReportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.fleetClients.Fleetclients.robotReportRequest,
                io.grpc.fleetClients.Fleetclients.robotReportResponse>(
                  this, METHODID_GROBOT_REPORT)))
          .addMethod(
            getCoordinatorrobotreportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.fleetClients.Fleetclients.coordinatorrequestrobotid,
                io.grpc.fleetClients.Fleetclients.coordinatorrespondrobotreport>(
                  this, METHODID_COORDINATORROBOTREPORT)))
          .addMethod(
            getClientCriticalpointMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.fleetClients.Fleetclients.clientGetCriticalPointRequestMessage,
                io.grpc.fleetClients.Fleetclients.clientGetCriticalPointResponseMessage>(
                  this, METHODID_CLIENT_CRITICALPOINT)))
          .build();
    }
  }

  /**
   */
  public static final class FleetClientsServiceStub extends io.grpc.stub.AbstractAsyncStub<FleetClientsServiceStub> {
    private FleetClientsServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FleetClientsServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FleetClientsServiceStub(channel, callOptions);
    }

    /**
     */
    public void gCriticalPoint(io.grpc.fleetClients.Fleetclients.getCriticalPointRequestMessage request,
        io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.getCriticalPointResponseMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGCriticalPointMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *&#47;//////////////////////////
     * </pre>
     */
    public void message(io.grpc.fleetClients.Fleetclients.requestMessage request,
        io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.responseMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void grobotID(io.grpc.fleetClients.Fleetclients.getRobotID request,
        io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.robotID> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGrobotIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void grobotReport(io.grpc.fleetClients.Fleetclients.robotReportRequest request,
        io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.robotReportResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGrobotReportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void coordinatorrobotreport(io.grpc.fleetClients.Fleetclients.coordinatorrequestrobotid request,
        io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.coordinatorrespondrobotreport> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoordinatorrobotreportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void clientCriticalpoint(io.grpc.fleetClients.Fleetclients.clientGetCriticalPointRequestMessage request,
        io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.clientGetCriticalPointResponseMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClientCriticalpointMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FleetClientsServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<FleetClientsServiceBlockingStub> {
    private FleetClientsServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FleetClientsServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FleetClientsServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.fleetClients.Fleetclients.getCriticalPointResponseMessage gCriticalPoint(io.grpc.fleetClients.Fleetclients.getCriticalPointRequestMessage request) {
      return blockingUnaryCall(
          getChannel(), getGCriticalPointMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *&#47;//////////////////////////
     * </pre>
     */
    public io.grpc.fleetClients.Fleetclients.responseMessage message(io.grpc.fleetClients.Fleetclients.requestMessage request) {
      return blockingUnaryCall(
          getChannel(), getMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.fleetClients.Fleetclients.robotID grobotID(io.grpc.fleetClients.Fleetclients.getRobotID request) {
      return blockingUnaryCall(
          getChannel(), getGrobotIDMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.fleetClients.Fleetclients.robotReportResponse grobotReport(io.grpc.fleetClients.Fleetclients.robotReportRequest request) {
      return blockingUnaryCall(
          getChannel(), getGrobotReportMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.fleetClients.Fleetclients.coordinatorrespondrobotreport coordinatorrobotreport(io.grpc.fleetClients.Fleetclients.coordinatorrequestrobotid request) {
      return blockingUnaryCall(
          getChannel(), getCoordinatorrobotreportMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.fleetClients.Fleetclients.clientGetCriticalPointResponseMessage clientCriticalpoint(io.grpc.fleetClients.Fleetclients.clientGetCriticalPointRequestMessage request) {
      return blockingUnaryCall(
          getChannel(), getClientCriticalpointMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FleetClientsServiceFutureStub extends io.grpc.stub.AbstractFutureStub<FleetClientsServiceFutureStub> {
    private FleetClientsServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FleetClientsServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FleetClientsServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.fleetClients.Fleetclients.getCriticalPointResponseMessage> gCriticalPoint(
        io.grpc.fleetClients.Fleetclients.getCriticalPointRequestMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGCriticalPointMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *&#47;//////////////////////////
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.fleetClients.Fleetclients.responseMessage> message(
        io.grpc.fleetClients.Fleetclients.requestMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.fleetClients.Fleetclients.robotID> grobotID(
        io.grpc.fleetClients.Fleetclients.getRobotID request) {
      return futureUnaryCall(
          getChannel().newCall(getGrobotIDMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.fleetClients.Fleetclients.robotReportResponse> grobotReport(
        io.grpc.fleetClients.Fleetclients.robotReportRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGrobotReportMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.fleetClients.Fleetclients.coordinatorrespondrobotreport> coordinatorrobotreport(
        io.grpc.fleetClients.Fleetclients.coordinatorrequestrobotid request) {
      return futureUnaryCall(
          getChannel().newCall(getCoordinatorrobotreportMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.fleetClients.Fleetclients.clientGetCriticalPointResponseMessage> clientCriticalpoint(
        io.grpc.fleetClients.Fleetclients.clientGetCriticalPointRequestMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getClientCriticalpointMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_G_CRITICAL_POINT = 0;
  private static final int METHODID_MESSAGE = 1;
  private static final int METHODID_GROBOT_ID = 2;
  private static final int METHODID_GROBOT_REPORT = 3;
  private static final int METHODID_COORDINATORROBOTREPORT = 4;
  private static final int METHODID_CLIENT_CRITICALPOINT = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FleetClientsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FleetClientsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_G_CRITICAL_POINT:
          serviceImpl.gCriticalPoint((io.grpc.fleetClients.Fleetclients.getCriticalPointRequestMessage) request,
              (io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.getCriticalPointResponseMessage>) responseObserver);
          break;
        case METHODID_MESSAGE:
          serviceImpl.message((io.grpc.fleetClients.Fleetclients.requestMessage) request,
              (io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.responseMessage>) responseObserver);
          break;
        case METHODID_GROBOT_ID:
          serviceImpl.grobotID((io.grpc.fleetClients.Fleetclients.getRobotID) request,
              (io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.robotID>) responseObserver);
          break;
        case METHODID_GROBOT_REPORT:
          serviceImpl.grobotReport((io.grpc.fleetClients.Fleetclients.robotReportRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.robotReportResponse>) responseObserver);
          break;
        case METHODID_COORDINATORROBOTREPORT:
          serviceImpl.coordinatorrobotreport((io.grpc.fleetClients.Fleetclients.coordinatorrequestrobotid) request,
              (io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.coordinatorrespondrobotreport>) responseObserver);
          break;
        case METHODID_CLIENT_CRITICALPOINT:
          serviceImpl.clientCriticalpoint((io.grpc.fleetClients.Fleetclients.clientGetCriticalPointRequestMessage) request,
              (io.grpc.stub.StreamObserver<io.grpc.fleetClients.Fleetclients.clientGetCriticalPointResponseMessage>) responseObserver);
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

  private static abstract class FleetClientsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FleetClientsServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.fleetClients.Fleetclients.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FleetClientsService");
    }
  }

  private static final class FleetClientsServiceFileDescriptorSupplier
      extends FleetClientsServiceBaseDescriptorSupplier {
    FleetClientsServiceFileDescriptorSupplier() {}
  }

  private static final class FleetClientsServiceMethodDescriptorSupplier
      extends FleetClientsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FleetClientsServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (FleetClientsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FleetClientsServiceFileDescriptorSupplier())
              .addMethod(getGCriticalPointMethod())
              .addMethod(getMessageMethod())
              .addMethod(getGrobotIDMethod())
              .addMethod(getGrobotReportMethod())
              .addMethod(getCoordinatorrobotreportMethod())
              .addMethod(getClientCriticalpointMethod())
              .build();
        }
      }
    }
    return result;
  }
}
