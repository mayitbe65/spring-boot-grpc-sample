package com.mayitbe.grpc_provider.impl;

import com.mayitbe.grpc_provider.annotation.GrpcService;
import com.mayitbe.proto_api.service.HelloRequest;
import com.mayitbe.proto_api.service.HelloResponse;
import com.mayitbe.proto_api.service.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        //不能调用父类，否则报错，目的就是告诉你一定要自己实现业务逻辑
        //super.sayHello(request, responseObserver);

        String greeting = request.getGreeting();

        System.out.println(greeting);

        HelloResponse response = HelloResponse.newBuilder()
                .setReply("hello 你好啊")
                .build();
        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}
