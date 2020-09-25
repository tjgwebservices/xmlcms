package com.tjgwebservices.tjgxmlcms.model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.Extension;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class SocketSession implements Session {


    String id;
    WebSocketContainer container;
    Set<MessageHandler> messageHandlers;
    String protocolVersion;
    String negotiatedSubprotocol;
    List<Extension> negotiatedExtensions;
    int maxBinaryMessageBufferSize;
    long maxIdleTimeout;
    String queryString;
    URI requestURI;
    Map<String, List<String>> requestParameterMap;
    Map<String, Object> userProperties;
    Principal userPrincipal;

    SocketSession(){}
    
    SocketSession(String id, String uri){
        this.id = id;
        try {
            this.requestURI = new URI(uri);
        } catch (URISyntaxException ex) {
            Logger.getLogger(SocketSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isSecure() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isOpen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getMaxIdleTimeout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMaxIdleTimeout(long l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMaxBinaryMessageBufferSize(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxBinaryMessageBufferSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMaxTextMessageBufferSize(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxTextMessageBufferSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RemoteEndpoint.Async getAsyncRemote() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RemoteEndpoint.Basic getBasicRemote() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close(CloseReason cr) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public URI getRequestURI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, List<String>> getRequestParameterMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getQueryString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> getPathParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> getUserProperties() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Principal getUserPrincipal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Session> getOpenSessions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> void addMessageHandler(Class<T> type, MessageHandler.Partial<T> prtl) throws IllegalStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> void addMessageHandler(Class<T> type, MessageHandler.Whole<T> whole) throws IllegalStateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    @Override
    public WebSocketContainer getContainer() {
        return container;
    }

    public void setContainer(WebSocketContainer container) {
        this.container = container;
    }
    

    @Override
    public String getNegotiatedSubprotocol() {
        return negotiatedSubprotocol;
    }


    public void setNegotiatedSubprotocol(String negotiatedSubprotocol) {
        this.negotiatedSubprotocol = negotiatedSubprotocol;
    }
    

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    @Override
    public List<Extension> getNegotiatedExtensions() {
        return negotiatedExtensions;
    }

    @Override
    public String getProtocolVersion() {
        return protocolVersion;
    }
    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Override
    public Set<MessageHandler> getMessageHandlers() {
        return messageHandlers;
    }

    public void setMessageHandlers(Set<MessageHandler> messageHandlers) {
        this.messageHandlers = messageHandlers;
    }

    @Override
    public void addMessageHandler(MessageHandler mh) throws IllegalStateException {
            messageHandlers.add(mh);
    }


    @Override
    public void removeMessageHandler(MessageHandler mh) {
        messageHandlers.remove(mh);
    }



    public void setRequestURI(URI requestURI) {
        this.requestURI = requestURI;
    }

    public void setRequestParameterMap(Map<String, List<String>> requestParameterMap) {
        this.requestParameterMap = requestParameterMap;
    }

    public void setUserProperties(Map<String, Object> userProperties) {
        this.userProperties = userProperties;
    }

    public void setUserPrincipal(Principal userPrincipal) {
        this.userPrincipal = userPrincipal;
    }


    public void setNegotiatedExtensions(List<Extension> negotiatedExtensions) {
        this.negotiatedExtensions = negotiatedExtensions;
    }
    
}
