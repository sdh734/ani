package edu.smxy.associationmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * @program: associationmanagement
 * @description: Stomp测试配置类
 * @author: SDH
 * @create: 2019-03-22 14:05
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//注册端点  设置跨域为允许所有请求 使用Sockjs协议
		registry.addEndpoint("/webSocketServer").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//设置客户端订阅前缀 topic 广播 user 单点
		registry.enableSimpleBroker("/topic","/user");
		registry.setUserDestinationPrefix("/user");
	}
}
