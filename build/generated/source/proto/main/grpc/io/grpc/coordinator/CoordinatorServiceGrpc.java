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

  private static volatile io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.timerequest,
      io.grpc.coordinator.Coordinator.timeresponse> getCoordinatorgetCurrentTimeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "coordinatorgetCurrentTime",
      requestType = io.grpc.coordinator.Coordinator.timerequest.class,
      responseType = io.grpc.coordinator.Coordinator.timeresponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.timerequest,
      io.grpc.coordinator.Coordinator.timeresponse> getCoordinatorgetCurrentTimeMethod() {
    io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.timerequest, io.grpc.coordinator.Coordinator.timeresponse> getCoordinatorgetCurrentTimeMethod;
    if ((getCoordinatorgetCurrentTimeMethod = CoordinatorServiceGrpc.getCoordinatorgetCurrentTimeMethod) == null) {
      synchronized (CoordinatorServiceGrpc.class) {
        if ((getCoordinatorgetCurrentTimeMethod = CoordinatorServiceGrpc.getCoordinatorgetCurrentTimeMethod) == null) {
          CoordinatorServiceGrpc.getCoordinatorgetCurrentTimeMethod = getCoordinatorgetCurrentTimeMethod =
              io.grpc.MethodDescriptor.<io.grpc.coordinator.Coordinator.timerequest, io.grpc.coordinator.Coordinator.timeresponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "coordinatorgetCurrentTime"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.timerequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.timeresponse.getDefaultInstance()))
              .setSchemaDescriptor(new CoordinatorServiceMethodDescriptorSupplier("coordinatorgetCurrentTime"))
              .build();
        }
      }
    }
    return getCoordinatorgetCurrentTimeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.trackerRobotReportRequest,
      io.grpc.coordinator.Coordinator.requestrobotreport> getCoordinatorgetRobotReportRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "coordinatorgetRobotReportRequest",
      requestType = io.grpc.coordinator.Coordinator.trackerRobotReportRequest.class,
      responseType = io.grpc.coordinator.Coordinator.requestrobotreport.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.trackerRobotReportRequest,
      io.grpc.coordinator.Coordinator.requestrobotreport> getCoordinatorgetRobotReportRequestMethod() {
    io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.trackerRobotReportRequest, io.grpc.coordinator.Coordinator.requestrobotreport> getCoordinatorgetRobotReportRequestMethod;
    if ((getCoordinatorgetRobotReportRequestMethod = CoordinatorServiceGrpc.getCoordinatorgetRobotReportRequestMethod) == null) {
      synchronized (CoordinatorServiceGrpc.class) {
        if ((getCoordinatorgetRobotReportRequestMethod = CoordinatorServiceGrpc.getCoordinatorgetRobotReportRequestMethod) == null) {
          CoordinatorServiceGrpc.getCoordinatorgetRobotReportRequestMethod = getCoordinatorgetRobotReportRequestMethod =
              io.grpc.MethodDescriptor.<io.grpc.coordinator.Coordinator.trackerRobotReportRequest, io.grpc.coordinator.Coordinator.requestrobotreport>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "coordinatorgetRobotReportRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.trackerRobotReportRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.requestrobotreport.getDefaultInstance()))
              .setSchemaDescriptor(new CoordinatorServiceMethodDescriptorSupplier("coordinatorgetRobotReportRequest"))
              .build();
        }
      }
    }
    return getCoordinatorgetRobotReportRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.onPositionUpdateMessage,
      io.grpc.coordinator.Coordinator.noneResponse> getCoordinatorgetOnPositionUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "coordinatorgetOnPositionUpdate",
      requestType = io.grpc.coordinator.Coordinator.onPositionUpdateMessage.class,
      responseType = io.grpc.coordinator.Coordinator.noneResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.onPositionUpdateMessage,
      io.grpc.coordinator.Coordinator.noneResponse> getCoordinatorgetOnPositionUpdateMethod() {
    io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.onPositionUpdateMessage, io.grpc.coordinator.Coordinator.noneResponse> getCoordinatorgetOnPositionUpdateMethod;
    if ((getCoordinatorgetOnPositionUpdateMethod = CoordinatorServiceGrpc.getCoordinatorgetOnPositionUpdateMethod) == null) {
      synchronized (CoordinatorServiceGrpc.class) {
        if ((getCoordinatorgetOnPositionUpdateMethod = CoordinatorServiceGrpc.getCoordinatorgetOnPositionUpdateMethod) == null) {
          CoordinatorServiceGrpc.getCoordinatorgetOnPositionUpdateMethod = getCoordinatorgetOnPositionUpdateMethod =
              io.grpc.MethodDescriptor.<io.grpc.coordinator.Coordinator.onPositionUpdateMessage, io.grpc.coordinator.Coordinator.noneResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "coordinatorgetOnPositionUpdate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.onPositionUpdateMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.noneResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CoordinatorServiceMethodDescriptorSupplier("coordinatorgetOnPositionUpdate"))
              .build();
        }
      }
    }
    return getCoordinatorgetOnPositionUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.onPositionUpdateMessage,
      io.grpc.coordinator.Coordinator.noneResponse> getCoordinatorgetOnPositionUpdateEvenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "coordinatorgetOnPositionUpdateEven",
      requestType = io.grpc.coordinator.Coordinator.onPositionUpdateMessage.class,
      responseType = io.grpc.coordinator.Coordinator.noneResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.onPositionUpdateMessage,
      io.grpc.coordinator.Coordinator.noneResponse> getCoordinatorgetOnPositionUpdateEvenMethod() {
    io.grpc.MethodDescriptor<io.grpc.coordinator.Coordinator.onPositionUpdateMessage, io.grpc.coordinator.Coordinator.noneResponse> getCoordinatorgetOnPositionUpdateEvenMethod;
    if ((getCoordinatorgetOnPositionUpdateEvenMethod = CoordinatorServiceGrpc.getCoordinatorgetOnPositionUpdateEvenMethod) == null) {
      synchronized (CoordinatorServiceGrpc.class) {
        if ((getCoordinatorgetOnPositionUpdateEvenMethod = CoordinatorServiceGrpc.getCoordinatorgetOnPositionUpdateEvenMethod) == null) {
          CoordinatorServiceGrpc.getCoordinatorgetOnPositionUpdateEvenMethod = getCoordinatorgetOnPositionUpdateEvenMethod =
              io.grpc.MethodDescriptor.<io.grpc.coordinator.Coordinator.onPositionUpdateMessage, io.grpc.coordinator.Coordinator.noneResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "coordinatorgetOnPositionUpdateEven"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.onPositionUpdateMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.coordinator.Coordinator.noneResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CoordinatorServiceMethodDescriptorSupplier("coordinatorgetOnPositionUpdateEven"))
              .build();
        }
      }
    }
    return getCoordinatorgetOnPositionUpdateEvenMethod;
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
    public void coordinatorgetGreeting(io.grpc.coordinator.Coordinator.robotsGreeting request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.robotgreetingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCoordinatorgetGreetingMethod(), responseObserver);
    }

    /**
     */
    public void coordinatorgetCurrentTime(io.grpc.coordinator.Coordinator.timerequest request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.timeresponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCoordinatorgetCurrentTimeMethod(), responseObserver);
    }

    /**
     */
    public void coordinatorgetRobotReportRequest(io.grpc.coordinator.Coordinator.trackerRobotReportRequest request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.requestrobotreport> responseObserver) {
      asyncUnimplementedUnaryCall(getCoordinatorgetRobotReportRequestMethod(), responseObserver);
    }

    /**
     */
    public void coordinatorgetOnPositionUpdate(io.grpc.coordinator.Coordinator.onPositionUpdateMessage request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.noneResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCoordinatorgetOnPositionUpdateMethod(), responseObserver);
    }

    /**
     */
    public void coordinatorgetOnPositionUpdateEven(io.grpc.coordinator.Coordinator.onPositionUpdateMessage request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.noneResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCoordinatorgetOnPositionUpdateEvenMethod(), responseObserver);
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
            getCoordinatorgetGreetingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.coordinator.Coordinator.robotsGreeting,
                io.grpc.coordinator.Coordinator.robotgreetingResponse>(
                  this, METHODID_COORDINATORGET_GREETING)))
          .addMethod(
            getCoordinatorgetCurrentTimeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.coordinator.Coordinator.timerequest,
                io.grpc.coordinator.Coordinator.timeresponse>(
                  this, METHODID_COORDINATORGET_CURRENT_TIME)))
          .addMethod(
            getCoordinatorgetRobotReportRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.coordinator.Coordinator.trackerRobotReportRequest,
                io.grpc.coordinator.Coordinator.requestrobotreport>(
                  this, METHODID_COORDINATORGET_ROBOT_REPORT_REQUEST)))
          .addMethod(
            getCoordinatorgetOnPositionUpdateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.coordinator.Coordinator.onPositionUpdateMessage,
                io.grpc.coordinator.Coordinator.noneResponse>(
                  this, METHODID_COORDINATORGET_ON_POSITION_UPDATE)))
          .addMethod(
            getCoordinatorgetOnPositionUpdateEvenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.coordinator.Coordinator.onPositionUpdateMessage,
                io.grpc.coordinator.Coordinator.noneResponse>(
                  this, METHODID_COORDINATORGET_ON_POSITION_UPDATE_EVEN)))
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
    public void coordinatorgetGreeting(io.grpc.coordinator.Coordinator.robotsGreeting request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.robotgreetingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoordinatorgetGreetingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void coordinatorgetCurrentTime(io.grpc.coordinator.Coordinator.timerequest request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.timeresponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoordinatorgetCurrentTimeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void coordinatorgetRobotReportRequest(io.grpc.coordinator.Coordinator.trackerRobotReportRequest request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.requestrobotreport> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoordinatorgetRobotReportRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void coordinatorgetOnPositionUpdate(io.grpc.coordinator.Coordinator.onPositionUpdateMessage request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.noneResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoordinatorgetOnPositionUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void coordinatorgetOnPositionUpdateEven(io.grpc.coordinator.Coordinator.onPositionUpdateMessage request,
        io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.noneResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCoordinatorgetOnPositionUpdateEvenMethod(), getCallOptions()), request, responseObserver);
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
    public io.grpc.coordinator.Coordinator.robotgreetingResponse coordinatorgetGreeting(io.grpc.coordinator.Coordinator.robotsGreeting request) {
      return blockingUnaryCall(
          getChannel(), getCoordinatorgetGreetingMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.coordinator.Coordinator.timeresponse coordinatorgetCurrentTime(io.grpc.coordinator.Coordinator.timerequest request) {
      return blockingUnaryCall(
          getChannel(), getCoordinatorgetCurrentTimeMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.coordinator.Coordinator.requestrobotreport coordinatorgetRobotReportRequest(io.grpc.coordinator.Coordinator.trackerRobotReportRequest request) {
      return blockingUnaryCall(
          getChannel(), getCoordinatorgetRobotReportRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.coordinator.Coordinator.noneResponse coordinatorgetOnPositionUpdate(io.grpc.coordinator.Coordinator.onPositionUpdateMessage request) {
      return blockingUnaryCall(
          getChannel(), getCoordinatorgetOnPositionUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.coordinator.Coordinator.noneResponse coordinatorgetOnPositionUpdateEven(io.grpc.coordinator.Coordinator.onPositionUpdateMessage request) {
      return blockingUnaryCall(
          getChannel(), getCoordinatorgetOnPositionUpdateEvenMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.coordinator.Coordinator.robotgreetingResponse> coordinatorgetGreeting(
        io.grpc.coordinator.Coordinator.robotsGreeting request) {
      return futureUnaryCall(
          getChannel().newCall(getCoordinatorgetGreetingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.coordinator.Coordinator.timeresponse> coordinatorgetCurrentTime(
        io.grpc.coordinator.Coordinator.timerequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCoordinatorgetCurrentTimeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.coordinator.Coordinator.requestrobotreport> coordinatorgetRobotReportRequest(
        io.grpc.coordinator.Coordinator.trackerRobotReportRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCoordinatorgetRobotReportRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.coordinator.Coordinator.noneResponse> coordinatorgetOnPositionUpdate(
        io.grpc.coordinator.Coordinator.onPositionUpdateMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getCoordinatorgetOnPositionUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.coordinator.Coordinator.noneResponse> coordinatorgetOnPositionUpdateEven(
        io.grpc.coordinator.Coordinator.onPositionUpdateMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getCoordinatorgetOnPositionUpdateEvenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COORDINATORROBOTREPORT = 0;
  private static final int METHODID_COORDINATORCRITICALPOINT = 1;
  private static final int METHODID_COORDINATORGET_GREETING = 2;
  private static final int METHODID_COORDINATORGET_CURRENT_TIME = 3;
  private static final int METHODID_COORDINATORGET_ROBOT_REPORT_REQUEST = 4;
  private static final int METHODID_COORDINATORGET_ON_POSITION_UPDATE = 5;
  private static final int METHODID_COORDINATORGET_ON_POSITION_UPDATE_EVEN = 6;

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
        case METHODID_COORDINATORGET_GREETING:
          serviceImpl.coordinatorgetGreeting((io.grpc.coordinator.Coordinator.robotsGreeting) request,
              (io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.robotgreetingResponse>) responseObserver);
          break;
        case METHODID_COORDINATORGET_CURRENT_TIME:
          serviceImpl.coordinatorgetCurrentTime((io.grpc.coordinator.Coordinator.timerequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.timeresponse>) responseObserver);
          break;
        case METHODID_COORDINATORGET_ROBOT_REPORT_REQUEST:
          serviceImpl.coordinatorgetRobotReportRequest((io.grpc.coordinator.Coordinator.trackerRobotReportRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.requestrobotreport>) responseObserver);
          break;
        case METHODID_COORDINATORGET_ON_POSITION_UPDATE:
          serviceImpl.coordinatorgetOnPositionUpdate((io.grpc.coordinator.Coordinator.onPositionUpdateMessage) request,
              (io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.noneResponse>) responseObserver);
          break;
        case METHODID_COORDINATORGET_ON_POSITION_UPDATE_EVEN:
          serviceImpl.coordinatorgetOnPositionUpdateEven((io.grpc.coordinator.Coordinator.onPositionUpdateMessage) request,
              (io.grpc.stub.StreamObserver<io.grpc.coordinator.Coordinator.noneResponse>) responseObserver);
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
              .addMethod(getCoordinatorgetGreetingMethod())
              .addMethod(getCoordinatorgetCurrentTimeMethod())
              .addMethod(getCoordinatorgetRobotReportRequestMethod())
              .addMethod(getCoordinatorgetOnPositionUpdateMethod())
              .addMethod(getCoordinatorgetOnPositionUpdateEvenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
