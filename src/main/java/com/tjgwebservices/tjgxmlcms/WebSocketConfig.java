package com.tjgwebservices.tjgxmlcms;

import com.tjgwebservices.tjgxmlcms.model.SocketHandler;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscriber;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketHandler(), "/socket")
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

}
