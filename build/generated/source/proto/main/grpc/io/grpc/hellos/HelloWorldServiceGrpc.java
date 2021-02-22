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
    comments = "Source: hello.proto")
public final class HelloWorldServiceGrpc {

  private HelloWorldServiceGrpc() {}

  public static final String SERVICE_NAME = "gradlegRPC.HelloWorldService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.hellos.Hello.HelloRequest,
      io.grpc.hellos.Hello.HelloResponse> getHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "hello",
      requestType = io.grpc.hellos.Hello.HelloRequest.class,
      responseType = io.grpc.hellos.Hello.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.hellos.Hello.HelloRequest,
      io.grpc.hellos.Hello.HelloResponse> getHelloMethod() {
    io.grpc.MethodDescriptor<io.grpc.hellos.Hello.HelloRequest, io.grpc.hellos.Hello.HelloResponse> getHelloMethod;
    if ((getHelloMethod = HelloWorldServiceGrpc.getHelloMethod) == null) {
      synchronized (HelloWorldServiceGrpc.class) {
        if ((getHelloMethod = HelloWorldServiceGrpc.getHelloMethod) == null) {
          HelloWorldServiceGrpc.getHelloMethod = getHelloMethod =
              io.grpc.MethodDescriptor.<io.grpc.hellos.Hello.HelloRequest, io.grpc.hellos.Hello.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "hello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Hello.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Hello.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloWorldServiceMethodDescriptorSupplier("hello"))
              .build();
        }
      }
    }
    return getHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.hellos.Hello.getRobotID,
      io.grpc.hellos.Hello.robotID> getGrobotIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "grobotID",
      requestType = io.grpc.hellos.Hello.getRobotID.class,
      responseType = io.grpc.hellos.Hello.robotID.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.hellos.Hello.getRobotID,
      io.grpc.hellos.Hello.robotID> getGrobotIDMethod() {
    io.grpc.MethodDescriptor<io.grpc.hellos.Hello.getRobotID, io.grpc.hellos.Hello.robotID> getGrobotIDMethod;
    if ((getGrobotIDMethod = HelloWorldServiceGrpc.getGrobotIDMethod) == null) {
      synchronized (HelloWorldServiceGrpc.class) {
        if ((getGrobotIDMethod = HelloWorldServiceGrpc.getGrobotIDMethod) == null) {
          HelloWorldServiceGrpc.getGrobotIDMethod = getGrobotIDMethod =
              io.grpc.MethodDescriptor.<io.grpc.hellos.Hello.getRobotID, io.grpc.hellos.Hello.robotID>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "grobotID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Hello.getRobotID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Hello.robotID.getDefaultInstance()))
              .setSchemaDescriptor(new HelloWorldServiceMethodDescriptorSupplier("grobotID"))
              .build();
        }
      }
    }
    return getGrobotIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.hellos.Hello.getRobotReport,
      io.grpc.hellos.Hello.robotID> getGrobotReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "grobotReport",
      requestType = io.grpc.hellos.Hello.getRobotReport.class,
      responseType = io.grpc.hellos.Hello.robotID.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.hellos.Hello.getRobotReport,
      io.grpc.hellos.Hello.robotID> getGrobotReportMethod() {
    io.grpc.MethodDescriptor<io.grpc.hellos.Hello.getRobotReport, io.grpc.hellos.Hello.robotID> getGrobotReportMethod;
    if ((getGrobotReportMethod = HelloWorldServiceGrpc.getGrobotReportMethod) == null) {
      synchronized (HelloWorldServiceGrpc.class) {
        if ((getGrobotReportMethod = HelloWorldServiceGrpc.getGrobotReportMethod) == null) {
          HelloWorldServiceGrpc.getGrobotReportMethod = getGrobotReportMethod =
              io.grpc.MethodDescriptor.<io.grpc.hellos.Hello.getRobotReport, io.grpc.hellos.Hello.robotID>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "grobotReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Hello.getRobotReport.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.hellos.Hello.robotID.getDefaultInstance()))
              .setSchemaDescriptor(new HelloWorldServiceMethodDescriptorSupplier("grobotReport"))
              .build();
        }
      }
    }
    return getGrobotReportMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloWorldServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloWorldServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloWorldServiceStub>() {
        @java.lang.Override
        public HelloWorldServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloWorldServiceStub(channel, callOptions);
        }
      };
    return HelloWorldServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloWorldServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloWorldServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloWorldServiceBlockingStub>() {
        @java.lang.Override
        public HelloWorldServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloWorldServiceBlockingStub(channel, callOptions);
        }
      };
    return HelloWorldServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HelloWorldServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloWorldServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloWorldServiceFutureStub>() {
        @java.lang.Override
        public HelloWorldServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloWorldServiceFutureStub(channel, callOptions);
        }
      };
    return HelloWorldServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class HelloWorldServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void hello(io.grpc.hellos.Hello.HelloRequest request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Hello.HelloResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHelloMethod(), responseObserver);
    }

    /**
     */
    public void grobotID(io.grpc.hellos.Hello.getRobotID request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Hello.robotID> responseObserver) {
      asyncUnimplementedUnaryCall(getGrobotIDMethod(), responseObserver);
    }

    /**
     */
    public void grobotReport(io.grpc.hellos.Hello.getRobotReport request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Hello.robotID> responseObserver) {
      asyncUnimplementedUnaryCall(getGrobotReportMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.hellos.Hello.HelloRequest,
                io.grpc.hellos.Hello.HelloResponse>(
                  this, METHODID_HELLO)))
          .addMethod(
            getGrobotIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.hellos.Hello.getRobotID,
                io.grpc.hellos.Hello.robotID>(
                  this, METHODID_GROBOT_ID)))
          .addMethod(
            getGrobotReportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.hellos.Hello.getRobotReport,
                io.grpc.hellos.Hello.robotID>(
                  this, METHODID_GROBOT_REPORT)))
          .build();
    }
  }

  /**
   */
  public static final class HelloWorldServiceStub extends io.grpc.stub.AbstractAsyncStub<HelloWorldServiceStub> {
    private HelloWorldServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloWorldServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloWorldServiceStub(channel, callOptions);
    }

    /**
     */
    public void hello(io.grpc.hellos.Hello.HelloRequest request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Hello.HelloResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void grobotID(io.grpc.hellos.Hello.getRobotID request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Hello.robotID> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGrobotIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void grobotReport(io.grpc.hellos.Hello.getRobotReport request,
        io.grpc.stub.StreamObserver<io.grpc.hellos.Hello.robotID> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGrobotReportMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class HelloWorldServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<HelloWorldServiceBlockingStub> {
    private HelloWorldServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloWorldServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloWorldServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.hellos.Hello.HelloResponse hello(io.grpc.hellos.Hello.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getHelloMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.hellos.Hello.robotID grobotID(io.grpc.hellos.Hello.getRobotID request) {
      return blockingUnaryCall(
          getChannel(), getGrobotIDMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.hellos.Hello.robotID grobotReport(io.grpc.hellos.Hello.getRobotReport request) {
      return blockingUnaryCall(
          getChannel(), getGrobotReportMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HelloWorldServiceFutureStub extends io.grpc.stub.AbstractFutureStub<HelloWorldServiceFutureStub> {
    private HelloWorldServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloWorldServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloWorldServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.hellos.Hello.HelloResponse> hello(
        io.grpc.hellos.Hello.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHelloMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.hellos.Hello.robotID> grobotID(
        io.grpc.hellos.Hello.getRobotID request) {
      return futureUnaryCall(
          getChannel().newCall(getGrobotIDMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.hellos.Hello.robotID> grobotReport(
        io.grpc.hellos.Hello.getRobotReport request) {
      return futureUnaryCall(
          getChannel().newCall(getGrobotReportMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HELLO = 0;
  private static final int METHODID_GROBOT_ID = 1;
  private static final int METHODID_GROBOT_REPORT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HelloWorldServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HelloWorldServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HELLO:
          serviceImpl.hello((io.grpc.hellos.Hello.HelloRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.hellos.Hello.HelloResponse>) responseObserver);
          break;
        case METHODID_GROBOT_ID:
          serviceImpl.grobotID((io.grpc.hellos.Hello.getRobotID) request,
              (io.grpc.stub.StreamObserver<io.grpc.hellos.Hello.robotID>) responseObserver);
          break;
        case METHODID_GROBOT_REPORT:
          serviceImpl.grobotReport((io.grpc.hellos.Hello.getRobotReport) request,
              (io.grpc.stub.StreamObserver<io.grpc.hellos.Hello.robotID>) responseObserver);
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

  private static abstract class HelloWorldServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloWorldServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.hellos.Hello.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HelloWorldService");
    }
  }

  private static final class HelloWorldServiceFileDescriptorSupplier
      extends HelloWorldServiceBaseDescriptorSupplier {
    HelloWorldServiceFileDescriptorSupplier() {}
  }

  private static final class HelloWorldServiceMethodDescriptorSupplier
      extends HelloWorldServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HelloWorldServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (HelloWorldServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloWorldServiceFileDescriptorSupplier())
              .addMethod(getHelloMethod())
              .addMethod(getGrobotIDMethod())
              .addMethod(getGrobotReportMethod())
              .build();
        }
      }
    }
    return result;
  }
}
