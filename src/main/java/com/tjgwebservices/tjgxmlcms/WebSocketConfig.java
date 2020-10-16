package com.tjgwebservices.tjgxmlcms;

import com.tjgwebservices.tjgxmlcms.controller.SocketStreamHandler;
import com.tjgwebservices.tjgxmlcms.controller.WebSocketController;
import com.tjgwebservices.tjgxmlcms.model.SocketHandler;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscriber;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends AbstractWebSocketHandler implements WebSocketConfigurer{

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketHandler(), "/socket")
          .setAllowedOrigins("*");
        registry
              .addHandler(new SocketStreamHandler(), "/topics")
          .setAllowedOrigins("*");
        registry
              .addHandler(new SocketStreamHandler(), "/socket/*")
          .setAllowedOrigins("*");
        registry
              .addHandler(new SocketStreamHandler(), "/socketDisplay")
          .setAllowedOrigins("*");
        registry
              .addHandler(new SocketStreamHandler(), "/topics/messages/**")
          .setAllowedOrigins("*");
  
    }  
    
    @Bean
    public ExecutorService executorService(){
        ExecutorService executor = Executors.newFixedThreadPool(4);
        return executor;
    }

    @Bean
    public Flow.Subscriber subscriber(){
        SocketSubscriber subscriber = new SocketSubscriber();
        return subscriber;
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("New Connection Established");
    }

    @Override
    public void handleTextMessage(WebSocketSession webSocketSession,
            TextMessage textMessage){
        System.out.println("Message, length: "+textMessage.getPayloadLength());
    }
    
    public void aferConnectionClosed(WebSocketSession session, CloseStatus  status){
        System.out.println("Connection closed");
    }
    
}
