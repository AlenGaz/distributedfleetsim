package gradlegRPC;

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
    comments = "Source: clientTracker.proto")
public final class ClientTrackerServiceGrpc {

  private ClientTrackerServiceGrpc() {}

  public static final String SERVICE_NAME = "gradlegRPC.ClientTrackerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<gradlegRPC.ClientTracker.byteRequest,
      gradlegRPC.ClientTracker.emptym> getGetTrackMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getTrack",
      requestType = gradlegRPC.ClientTracker.byteRequest.class,
      responseType = gradlegRPC.ClientTracker.emptym.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gradlegRPC.ClientTracker.byteRequest,
      gradlegRPC.ClientTracker.emptym> getGetTrackMethod() {
    io.grpc.MethodDescriptor<gradlegRPC.ClientTracker.byteRequest, gradlegRPC.ClientTracker.emptym> getGetTrackMethod;
    if ((getGetTrackMethod = ClientTrackerServiceGrpc.getGetTrackMethod) == null) {
      synchronized (ClientTrackerServiceGrpc.class) {
        if ((getGetTrackMethod = ClientTrackerServiceGrpc.getGetTrackMethod) == null) {
          ClientTrackerServiceGrpc.getGetTrackMethod = getGetTrackMethod =
              io.grpc.MethodDescriptor.<gradlegRPC.ClientTracker.byteRequest, gradlegRPC.ClientTracker.emptym>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getTrack"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gradlegRPC.ClientTracker.byteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gradlegRPC.ClientTracker.emptym.getDefaultInstance()))
              .setSchemaDescriptor(new ClientTrackerServiceMethodDescriptorSupplier("getTrack"))
              .build();
        }
      }
    }
    return getGetTrackMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClientTrackerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientTrackerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientTrackerServiceStub>() {
        @java.lang.Override
        public ClientTrackerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientTrackerServiceStub(channel, callOptions);
        }
      };
    return ClientTrackerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClientTrackerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientTrackerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientTrackerServiceBlockingStub>() {
        @java.lang.Override
        public ClientTrackerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientTrackerServiceBlockingStub(channel, callOptions);
        }
      };
    return ClientTrackerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClientTrackerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientTrackerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientTrackerServiceFutureStub>() {
        @java.lang.Override
        public ClientTrackerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientTrackerServiceFutureStub(channel, callOptions);
        }
      };
    return ClientTrackerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ClientTrackerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getTrack(gradlegRPC.ClientTracker.byteRequest request,
        io.grpc.stub.StreamObserver<gradlegRPC.ClientTracker.emptym> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTrackMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetTrackMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                gradlegRPC.ClientTracker.byteRequest,
                gradlegRPC.ClientTracker.emptym>(
                  this, METHODID_GET_TRACK)))
          .build();
    }
  }

  /**
   */
  public static final class ClientTrackerServiceStub extends io.grpc.stub.AbstractAsyncStub<ClientTrackerServiceStub> {
    private ClientTrackerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientTrackerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientTrackerServiceStub(channel, callOptions);
    }

    /**
     */
    public void getTrack(gradlegRPC.ClientTracker.byteRequest request,
        io.grpc.stub.StreamObserver<gradlegRPC.ClientTracker.emptym> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTrackMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ClientTrackerServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ClientTrackerServiceBlockingStub> {
    private ClientTrackerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientTrackerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientTrackerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public gradlegRPC.ClientTracker.emptym getTrack(gradlegRPC.ClientTracker.byteRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetTrackMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ClientTrackerServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ClientTrackerServiceFutureStub> {
    private ClientTrackerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientTrackerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientTrackerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gradlegRPC.ClientTracker.emptym> getTrack(
        gradlegRPC.ClientTracker.byteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTrackMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_TRACK = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ClientTrackerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ClientTrackerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_TRACK:
          serviceImpl.getTrack((gradlegRPC.ClientTracker.byteRequest) request,
              (io.grpc.stub.StreamObserver<gradlegRPC.ClientTracker.emptym>) responseObserver);
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

  private static abstract class ClientTrackerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClientTrackerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return gradlegRPC.ClientTracker.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClientTrackerService");
    }
  }

  private static final class ClientTrackerServiceFileDescriptorSupplier
      extends ClientTrackerServiceBaseDescriptorSupplier {
    ClientTrackerServiceFileDescriptorSupplier() {}
  }

  private static final class ClientTrackerServiceMethodDescriptorSupplier
      extends ClientTrackerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ClientTrackerServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ClientTrackerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClientTrackerServiceFileDescriptorSupplier())
              .addMethod(getGetTrackMethod())
              .build();
        }
      }
    }
    return result;
  }
}
