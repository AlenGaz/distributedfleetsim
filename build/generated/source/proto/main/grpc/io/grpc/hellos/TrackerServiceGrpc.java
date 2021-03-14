package io.grpc.hellos;

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
    comments = "Source: tracker.proto")
public final class TrackerServiceGrpc {

  private TrackerServiceGrpc() {}

  public static final String SERVICE_NAME = "gradlegRPC.TrackerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.hellos.Tracker.getCriticalPointRequestMessage,
      io.grpc.hellos.Tracker.getCriticalPointResponseMessage> getGCriticalPointMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "gCriticalPoint",
      requestType = io.grpc.hellos.Tracker.getCriticalPointRequestMessage.class,
      responseType = io.grpc.hellos.Tracker.getCriticalPointResponseMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.hellos.Tracker.getCriticalPointRequestMessage,
      io.grpc.hellos.Tracker.getCriticalPointResponseMessage> getGCriticalPointMethod() {
    io.grpc.MethodDescriptor<io.grpc.hellos.Tracker.getCriticalPointRequestMessage, io.grpc.hellos.Tracker.getCriticalPointResponseMessage> getGCriticalPointMethod;
    if ((getGCriticalPointMethod = TrackerServiceGrpc.getGCriticalPointMethod) == null) {
      synchronized (TrackerServiceGrpc.class) {
        if ((getGCriticalPointMethod = TrackerServiceGrpc.getGCriticalPointMethod) == null) {
          TrackerServiceGrpc.getGCriticalPointMethod = getGCriticalPointMethod =
              io.grpc.MethodDescriptor.<io.grpc.hellos.Tracker.getCriticalPointRequestMessage, io.grpc.hellos.Tracker.getCriticalPointResponseMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "gCriticalPoint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Tracker.getCriticalPointRequestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Tracker.getCriticalPointResponseMessage.getDefaultInstance()))
              .setSchemaDescriptor(new TrackerServiceMethodDescriptorSupplier("gCriticalPoint"))
              .build();
        }
      }
    }
    return getGCriticalPointMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TrackerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerServiceStub>() {
        @java.lang.Override
        public TrackerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerServiceStub(channel, callOptions);
        }
      };
    return TrackerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TrackerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerServiceBlockingStub>() {
        @java.lang.Override
        public TrackerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerServiceBlockingStub(channel, callOptions);
        }
      };
    return TrackerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TrackerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TrackerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TrackerServiceFutureStub>() {
        @java.lang.Override
        public TrackerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TrackerServiceFutureStub(channel, callOptions);
        }
      };
    return TrackerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TrackerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void gCriticalPoint(io.grpc.hellos.Tracker.getCriticalPointRequestMessage request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Tracker.getCriticalPointResponseMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getGCriticalPointMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGCriticalPointMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.hellos.Tracker.getCriticalPointRequestMessage,
                io.grpc.hellos.Tracker.getCriticalPointResponseMessage>(
                  this, METHODID_G_CRITICAL_POINT)))
          .build();
    }
  }

  /**
   */
  public static final class TrackerServiceStub extends io.grpc.stub.AbstractAsyncStub<TrackerServiceStub> {
    private TrackerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerServiceStub(channel, callOptions);
    }

    /**
     */
    public void gCriticalPoint(io.grpc.hellos.Tracker.getCriticalPointRequestMessage request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Tracker.getCriticalPointResponseMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGCriticalPointMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TrackerServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<TrackerServiceBlockingStub> {
    private TrackerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.hellos.Tracker.getCriticalPointResponseMessage gCriticalPoint(io.grpc.hellos.Tracker.getCriticalPointRequestMessage request) {
      return blockingUnaryCall(
          getChannel(), getGCriticalPointMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TrackerServiceFutureStub extends io.grpc.stub.AbstractFutureStub<TrackerServiceFutureStub> {
    private TrackerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TrackerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.hellos.Tracker.getCriticalPointResponseMessage> gCriticalPoint(
        io.grpc.hellos.Tracker.getCriticalPointRequestMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGCriticalPointMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_G_CRITICAL_POINT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TrackerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TrackerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_G_CRITICAL_POINT:
          serviceImpl.gCriticalPoint((io.grpc.hellos.Tracker.getCriticalPointRequestMessage) request,
              (io.grpc.stub.StreamObserver<io.grpc.hellos.Tracker.getCriticalPointResponseMessage>) responseObserver);
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

  private static abstract class TrackerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TrackerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.hellos.Tracker.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TrackerService");
    }
  }

  private static final class TrackerServiceFileDescriptorSupplier
      extends TrackerServiceBaseDescriptorSupplier {
    TrackerServiceFileDescriptorSupplier() {}
  }

  private static final class TrackerServiceMethodDescriptorSupplier
      extends TrackerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TrackerServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TrackerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TrackerServiceFileDescriptorSupplier())
              .addMethod(getGCriticalPointMethod())
              .build();
        }
      }
    }
    return result;
  }
}
