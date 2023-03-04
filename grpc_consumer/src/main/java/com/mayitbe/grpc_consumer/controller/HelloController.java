package com.mayitbe.grpc_consumer.controller;

import com.mayitbe.proto_api.service.HelloRequest;
import com.mayitbe.proto_api.service.HelloResponse;
import com.mayitbe.proto_api.service.HelloServiceGrpc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class HelloController {

    @Resource
    private HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;


    @RequestMapping("/hello")
    public String hello() {
        HelloRequest request = HelloRequest.newBuilder().setGreeting("哈哈").build();

        HelloResponse helloResponse = helloServiceBlockingStub.sayHello(request);

        return helloResponse.getReply();
    }
}
