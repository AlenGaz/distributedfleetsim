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
    comments = "Source: client.proto")
public final class fleetClientGrpc {

  private fleetClientGrpc() {}

  public static final String SERVICE_NAME = "gradlegRPC.fleetClient";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.hellos.Client.requestMessage,
      io.grpc.hellos.Client.responseMessage> getMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "message",
      requestType = io.grpc.hellos.Client.requestMessage.class,
      responseType = io.grpc.hellos.Client.responseMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.hellos.Client.requestMessage,
      io.grpc.hellos.Client.responseMessage> getMessageMethod() {
    io.grpc.MethodDescriptor<io.grpc.hellos.Client.requestMessage, io.grpc.hellos.Client.responseMessage> getMessageMethod;
    if ((getMessageMethod = fleetClientGrpc.getMessageMethod) == null) {
      synchronized (fleetClientGrpc.class) {
        if ((getMessageMethod = fleetClientGrpc.getMessageMethod) == null) {
          fleetClientGrpc.getMessageMethod = getMessageMethod =
              io.grpc.MethodDescriptor.<io.grpc.hellos.Client.requestMessage, io.grpc.hellos.Client.responseMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "message"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Client.requestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Client.responseMessage.getDefaultInstance()))
              .setSchemaDescriptor(new fleetClientMethodDescriptorSupplier("message"))
              .build();
        }
      }
    }
    return getMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.hellos.Client.getRobotID,
      io.grpc.hellos.Client.robotID> getGrobotIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "grobotID",
      requestType = io.grpc.hellos.Client.getRobotID.class,
      responseType = io.grpc.hellos.Client.robotID.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.hellos.Client.getRobotID,
      io.grpc.hellos.Client.robotID> getGrobotIDMethod() {
    io.grpc.MethodDescriptor<io.grpc.hellos.Client.getRobotID, io.grpc.hellos.Client.robotID> getGrobotIDMethod;
    if ((getGrobotIDMethod = fleetClientGrpc.getGrobotIDMethod) == null) {
      synchronized (fleetClientGrpc.class) {
        if ((getGrobotIDMethod = fleetClientGrpc.getGrobotIDMethod) == null) {
          fleetClientGrpc.getGrobotIDMethod = getGrobotIDMethod =
              io.grpc.MethodDescriptor.<io.grpc.hellos.Client.getRobotID, io.grpc.hellos.Client.robotID>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "grobotID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Client.getRobotID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Client.robotID.getDefaultInstance()))
              .setSchemaDescriptor(new fleetClientMethodDescriptorSupplier("grobotID"))
              .build();
        }
      }
    }
    return getGrobotIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.hellos.Client.getRobotReport,
      io.grpc.hellos.Client.robotReportResponse> getGrobotReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "grobotReport",
      requestType = io.grpc.hellos.Client.getRobotReport.class,
      responseType = io.grpc.hellos.Client.robotReportResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.hellos.Client.getRobotReport,
      io.grpc.hellos.Client.robotReportResponse> getGrobotReportMethod() {
    io.grpc.MethodDescriptor<io.grpc.hellos.Client.getRobotReport, io.grpc.hellos.Client.robotReportResponse> getGrobotReportMethod;
    if ((getGrobotReportMethod = fleetClientGrpc.getGrobotReportMethod) == null) {
      synchronized (fleetClientGrpc.class) {
        if ((getGrobotReportMethod = fleetClientGrpc.getGrobotReportMethod) == null) {
          fleetClientGrpc.getGrobotReportMethod = getGrobotReportMethod =
              io.grpc.MethodDescriptor.<io.grpc.hellos.Client.getRobotReport, io.grpc.hellos.Client.robotReportResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "grobotReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Client.getRobotReport.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Client.robotReportResponse.getDefaultInstance()))
              .setSchemaDescriptor(new fleetClientMethodDescriptorSupplier("grobotReport"))
              .build();
        }
      }
    }
    return getGrobotReportMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static fleetClientStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<fleetClientStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<fleetClientStub>() {
        @java.lang.Override
        public fleetClientStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new fleetClientStub(channel, callOptions);
        }
      };
    return fleetClientStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static fleetClientBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<fleetClientBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<fleetClientBlockingStub>() {
        @java.lang.Override
        public fleetClientBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new fleetClientBlockingStub(channel, callOptions);
        }
      };
    return fleetClientBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static fleetClientFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<fleetClientFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<fleetClientFutureStub>() {
        @java.lang.Override
        public fleetClientFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new fleetClientFutureStub(channel, callOptions);
        }
      };
    return fleetClientFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class fleetClientImplBase implements io.grpc.BindableService {

    /**
     */
    public void message(io.grpc.hellos.Client.requestMessage request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Client.responseMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getMessageMethod(), responseObserver);
    }

    /**
     */
    public void grobotID(io.grpc.hellos.Client.getRobotID request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Client.robotID> responseObserver) {
      asyncUnimplementedUnaryCall(getGrobotIDMethod(), responseObserver);
    }

    /**
     */
    public void grobotReport(io.grpc.hellos.Client.getRobotReport request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Client.robotReportResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGrobotReportMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.hellos.Client.requestMessage,
                io.grpc.hellos.Client.responseMessage>(
                  this, METHODID_MESSAGE)))
          .addMethod(
            getGrobotIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.hellos.Client.getRobotID,
                io.grpc.hellos.Client.robotID>(
                  this, METHODID_GROBOT_ID)))
          .addMethod(
            getGrobotReportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.hellos.Client.getRobotReport,
                io.grpc.hellos.Client.robotReportResponse>(
                  this, METHODID_GROBOT_REPORT)))
          .build();
    }
  }

  /**
   */
  public static final class fleetClientStub extends io.grpc.stub.AbstractAsyncStub<fleetClientStub> {
    private fleetClientStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected fleetClientStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new fleetClientStub(channel, callOptions);
    }

    /**
     */
    public void message(io.grpc.hellos.Client.requestMessage request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Client.responseMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void grobotID(io.grpc.hellos.Client.getRobotID request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Client.robotID> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGrobotIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void grobotReport(io.grpc.hellos.Client.getRobotReport request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Client.robotReportResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGrobotReportMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class fleetClientBlockingStub extends io.grpc.stub.AbstractBlockingStub<fleetClientBlockingStub> {
    private fleetClientBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected fleetClientBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new fleetClientBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.hellos.Client.responseMessage message(io.grpc.hellos.Client.requestMessage request) {
      return blockingUnaryCall(
          getChannel(), getMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.hellos.Client.robotID grobotID(io.grpc.hellos.Client.getRobotID request) {
      return blockingUnaryCall(
          getChannel(), getGrobotIDMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.hellos.Client.robotReportResponse grobotReport(io.grpc.hellos.Client.getRobotReport request) {
      return blockingUnaryCall(
          getChannel(), getGrobotReportMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class fleetClientFutureStub extends io.grpc.stub.AbstractFutureStub<fleetClientFutureStub> {
    private fleetClientFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected fleetClientFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new fleetClientFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.hellos.Client.responseMessage> message(
        io.grpc.hellos.Client.requestMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.hellos.Client.robotID> grobotID(
        io.grpc.hellos.Client.getRobotID request) {
      return futureUnaryCall(
          getChannel().newCall(getGrobotIDMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.hellos.Client.robotReportResponse> grobotReport(
        io.grpc.hellos.Client.getRobotReport request) {
      return futureUnaryCall(
          getChannel().newCall(getGrobotReportMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MESSAGE = 0;
  private static final int METHODID_GROBOT_ID = 1;
  private static final int METHODID_GROBOT_REPORT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final fleetClientImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(fleetClientImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MESSAGE:
          serviceImpl.message((io.grpc.hellos.Client.requestMessage) request,
              (io.grpc.stub.StreamObserver<io.grpc.hellos.Client.responseMessage>) responseObserver);
          break;
        case METHODID_GROBOT_ID:
          serviceImpl.grobotID((io.grpc.hellos.Client.getRobotID) request,
              (io.grpc.stub.StreamObserver<io.grpc.hellos.Client.robotID>) responseObserver);
          break;
        case METHODID_GROBOT_REPORT:
          serviceImpl.grobotReport((io.grpc.hellos.Client.getRobotReport) request,
              (io.grpc.stub.StreamObserver<io.grpc.hellos.Client.robotReportResponse>) responseObserver);
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

  private static abstract class fleetClientBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    fleetClientBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.hellos.Client.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("fleetClient");
    }
  }

  private static final class fleetClientFileDescriptorSupplier
      extends fleetClientBaseDescriptorSupplier {
    fleetClientFileDescriptorSupplier() {}
  }

  private static final class fleetClientMethodDescriptorSupplier
      extends fleetClientBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    fleetClientMethodDescriptorSupplier(String methodName) {
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
      synchronized (fleetClientGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new fleetClientFileDescriptorSupplier())
              .addMethod(getMessageMethod())
              .addMethod(getGrobotIDMethod())
              .addMethod(getGrobotReportMethod())
              .build();
        }
      }
    }
    return result;
  }
}
