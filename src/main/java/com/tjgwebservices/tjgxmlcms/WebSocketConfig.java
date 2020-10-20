package com.tjgwebservices.tjgxmlcms;

import com.tjgwebservices.tjgxmlcms.controller.SocketStreamHandler;
import com.tjgwebservices.tjgxmlcms.model.SocketHandler;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscriber;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
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

    @Value("${client.ssl.trust-store}")
    private Resource  trustStore;
    
    @Value("${client.ssl.trust-store-password}")
    private String trustStorePassword;
    
    @Value("${client.ssl-key-store}")
    private Resource clientKs;
    
    @Value("${client.ssl.key-store-password}")
    private String clientKsPwd;
    
    @Value("${client.ssl.key-store-alias}")
    private String clientKsAlias;
    
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

    @Bean
    public Boolean disableSSLValidation() throws Exception {
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        
        sslContext.init(null, new TrustManager[]{new X509TrustManager(){
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
            throws CertificateException {}

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            /*
            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) 
            throws CertificateException {}
            */
            @Override
            public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }},null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
            public boolean verify(String hostname, SSLSession session){
                return true;
            }
            
        });
                return true;
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
