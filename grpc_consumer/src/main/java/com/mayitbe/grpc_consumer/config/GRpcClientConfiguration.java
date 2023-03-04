package com.mayitbe.grpc_consumer.config;

import com.mayitbe.proto_api.service.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GRpcClientConfiguration {

    // 创建grpc客户端channel
    @Bean
    public ManagedChannel managedChannel() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:50051")
                .usePlaintext()
                .build();
        return channel;
    }

    @Bean
    public HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub(ManagedChannel channel) {
        return HelloServiceGrpc.newBlockingStub(channel);
    }
}
