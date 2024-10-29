package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

    @Bean
    public OrderControllerV1 orderController() {
        return new OrderControllerInterfaceProxy(new OrderControllerV1Impl(orderService()), logTrace());
    }

    @Bean
    public OrderServiceV1 orderService() {
        return new OrderServiceInterfaceProxy(new OrderServiceV1Impl(orderRepository()), logTrace());
    }

    @Bean
    public OrderRepositoryV1 orderRepository() {
        return new OrderRepositoryInterfaceProxy(new OrderRepositoryV1Impl(), logTrace());
    }

}
