package com.mayitbe.grpc_provider;

import com.mayitbe.grpc_provider.config.GRpcServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GrpcProviderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GrpcProviderApplication.class, args);
        GRpcServer gRpcServer = context.getBean(GRpcServer.class);
        gRpcServer.awaitTermination();
    }

}
