package com.tjgwebservices.tjgxmlcms.model;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {
    List<WebSocketSession>sessions = new CopyOnWriteArrayList<>();
 
    private String publisherId;
    private String subscriptionId;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
      throws InterruptedException, IOException {
        this.subscriptionId = message.getPayload();
        
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen() && !session.getId().equals(webSocketSession.getId())) {
                webSocketSession.sendMessage(message);
            }
        }
    }
 
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }    

    public List<WebSocketSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<WebSocketSession> sessions) {
        this.sessions = sessions;
    }

}
