package com.tjgwebservices.tjgxmlcms.model.socket;

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
import org.slf4j.LoggerFactory;

public class SocketSession implements Session {


    private String id;
    private WebSocketContainer container;
    private Set<MessageHandler> messageHandlers;
    private String protocolVersion;
    private String negotiatedSubprotocol;
    private List<Extension> negotiatedExtensions;
    private int maxBinaryMessageBufferSize;
    private int maxTextMessageBufferSize;
    private long maxIdleTimeout;
    private String queryString;
    private URI requestURI;
    private Map<String, List<String>> requestParameterMap;
    private Map<String, Object> userProperties;
    private Principal userPrincipal;
    private Map<String, String> pathParameters;
    private RemoteEndpoint endpoint;
    private boolean secure;
    private boolean open;
    private Set<Session> openSessions;
    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SocketSession.class);
    

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
        return secure;        
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    @Override
    public long getMaxIdleTimeout() {
        return maxIdleTimeout;
    }

    @Override
    public void setMaxIdleTimeout(long l) {
        maxIdleTimeout = l;
    }

    @Override
    public void setMaxBinaryMessageBufferSize(int i) {
        maxBinaryMessageBufferSize = i;
    }

    @Override
    public int getMaxBinaryMessageBufferSize() {
        return maxBinaryMessageBufferSize;
    }

    @Override
    public void setMaxTextMessageBufferSize(int i) {
        maxTextMessageBufferSize = i;
    }

    @Override
    public int getMaxTextMessageBufferSize() {
        return maxTextMessageBufferSize;
    }

    @Override
    public RemoteEndpoint.Async getAsyncRemote() {
        return (RemoteEndpoint.Async) endpoint;
    }

    @Override
    public RemoteEndpoint.Basic getBasicRemote() {
        return (RemoteEndpoint.Basic) endpoint;
    }

    @Override
    public void close() throws IOException {
        LOGGER.info("Socket Session");
        
    }

    @Override
    public void close(CloseReason cr) throws IOException {
        
        LOGGER.info("Socket Session Reason:"+cr.getReasonPhrase());
    }

    @Override
    public URI getRequestURI() {
        return requestURI;
    }

    @Override
    public Map<String, List<String>> getRequestParameterMap() {
        return requestParameterMap;
    }

    @Override
    public String getQueryString() {
        return queryString;
    }

    @Override
    public Map<String, String> getPathParameters() {
        return pathParameters;
    }

    @Override
    public Map<String, Object> getUserProperties() {
        return userProperties;
    }

    @Override
    public Principal getUserPrincipal() {
        return userPrincipal;
    }

    @Override
    public Set<Session> getOpenSessions() {
        return openSessions;
    }

    @Override
    public <T> void addMessageHandler(Class<T> type, MessageHandler.Partial<T> prtl) throws IllegalStateException {
        messageHandlers.add(prtl);
    }

    @Override
    public <T> void addMessageHandler(Class<T> type, MessageHandler.Whole<T> whole) throws IllegalStateException {
        messageHandlers.add(whole);
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

    public void sendMessage(String test) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
