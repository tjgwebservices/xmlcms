package com.tjgwebservices.tjgxmlcms.model.socket;

import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;

public class SocketClient extends HttpClient {
    
    private Redirect redirect;
    private Optional<CookieHandler> cookieHandler;
    private Optional<Duration> connectTimeout;
    
    public SocketClient() {}
    
    public SocketClient(Redirect redirect){
        this.redirect = redirect;
    }

    
    @Override
    public Optional<CookieHandler> cookieHandler() {
        return cookieHandler;
    }

    @Override
    public Optional<Duration> connectTimeout() {
        return this.connectTimeout;
    }

    @Override
    public Redirect followRedirects() {
        Logger.getLogger(SocketClient.class.getName()).log(Level.INFO, null, "Follow Redirects");
        return this.redirect;
        
    }

    @Override
    public Optional<ProxySelector> proxy() {
        Logger.getLogger(SocketClient.class.getName()).log(Level.INFO, null, "Proxy");
        return this.proxy();
    }

    @Override
    public SSLContext sslContext() {
        Logger.getLogger(SocketClient.class.getName()).log(Level.INFO, null, "ssl Context");
        return null;
    }

    @Override
    public SSLParameters sslParameters() {
        Logger.getLogger(SocketClient.class.getName()).log(Level.INFO, null, "ssl Parameters");
        return this.sslParameters();
    }

    @Override
    public Optional<Authenticator> authenticator() {
        Logger.getLogger(SocketClient.class.getName()).log(Level.INFO, null, "authenticator");
        return this.authenticator();
    }

    @Override
    public Version version() {
        Logger.getLogger(SocketClient.class.getName()).log(Level.INFO, null, "version");
        return this.version();
    }

    @Override
    public Optional<Executor> executor() {
        Logger.getLogger(SocketClient.class.getName()).log(Level.INFO, null, "executor");
        return this.executor();
    }

    @Override
    public <T> HttpResponse<T> send(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {
        Logger.getLogger(SocketClient.class.getName()).log(Level.INFO, null, "send");
        return null;
    }

    @Override
    public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) {
        Logger.getLogger(SocketClient.class.getName()).log(Level.INFO, null, "send");
        return null;
    }

    @Override
    public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler, HttpResponse.PushPromiseHandler<T> pushPromiseHandler) {
        Logger.getLogger(SocketClient.class.getName()).log(Level.INFO, null, "send");
        return null;                
    }
    
}
