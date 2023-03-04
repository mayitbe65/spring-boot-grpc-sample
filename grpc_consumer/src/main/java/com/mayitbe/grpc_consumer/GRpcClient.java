package com.mayitbe.grpc_consumer;

import com.mayitbe.proto_api.service.HelloRequest;
import com.mayitbe.proto_api.service.HelloResponse;
import com.mayitbe.proto_api.service.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class GRpcClient {

    public void start() throws IOException {
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forTarget("localhost:50051");
        channelBuilder.usePlaintext();
        //channelBuilder.defaultLoadBalancingPolicy("random") //设置
        //channelBuilder.intercept(new MyClientInterceptor());
        //CompressorRegistry compressorRegistry = CompressorRegistry.getDefaultInstance();
        //compressorRegistry.register(null);
        //channelBuilder.compressorRegistry(compressorRegistry);
        //channelBuilder.decompressorRegistry()
        //channelBuilder.disableRetry();
        //channelBuilder.idleTimeout(10,TimeUnit.MINUTES);
        //channelBuilder.keepAliveTimeout()

        ManagedChannel channel = channelBuilder.build();

        HelloRequest request = HelloRequest.newBuilder()
                .setGreeting("this is a client request")
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub = HelloServiceGrpc.newBlockingStub(channel);
        HelloResponse helloResponse = helloServiceBlockingStub.sayHello(request);

        System.out.println(helloResponse.getReply());

        try {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        GRpcClient gRpcClient = new GRpcClient();

        gRpcClient.start();
    }
}
