package com.tjgwebservices.tjgxmlcms.model;

import java.net.http.WebSocket.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class MessageListener implements Listener{
    
    @Autowired
    private SimpMessageSendingOperations messageTemplate;
    
    @EventListener
    public void handleConnectionListener(SessionConnectedEvent event) {
        
    }
    
    @EventListener
    public void handleDisconnectionListener (SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        
        String publisher = (String) headerAccessor.getSessionAttributes().get("subscriber");
        if (publisher != null) {
            MessageHandler message = new MessageHandler();
            message.setType(MessageHandler.MessageType.CLOSESUBSCRIPTION);
            message.setSubscriber(publisher);
            messageTemplate.convertAndSend("/topics/general", message);
        }
    }

}
