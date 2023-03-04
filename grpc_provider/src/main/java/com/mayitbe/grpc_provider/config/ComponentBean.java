package com.mayitbe.grpc_provider.config;

import com.mayitbe.grpc_provider.annotation.GrpcService;
import io.grpc.BindableService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;


@Component
public class ComponentBean implements ApplicationContextAware, SmartInitializingSingleton, DisposableBean {

    private ApplicationContext applicationContext;

    @Bean
    public GRpcServer gRpcServer() {
        return new GRpcServer();
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Object> grpcServices = applicationContext.getBeansWithAnnotation(GrpcService.class);
        ArrayList<BindableService> services = new ArrayList<>();
        for (Map.Entry<String, Object> entry : grpcServices.entrySet()) {
            if (!(entry.getValue() instanceof BindableService)) {
                throw new IllegalStateException("The bean named " + entry.getKey()
                        + " is marked with the @GrpcService , must implement the " + BindableService.class.getName());
            }
            services.add((BindableService) entry.getValue());
        }
        GRpcServer gRpcServer = applicationContext.getBean(GRpcServer.class);
        gRpcServer.start(services);
    }

    @Override
    public void destroy() throws Exception {
        GRpcServer gRpcServer = applicationContext.getBean(GRpcServer.class);
        gRpcServer.stop();
    }
}
