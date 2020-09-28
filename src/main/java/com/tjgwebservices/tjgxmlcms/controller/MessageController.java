package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.model.MessageHandler;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    static Map<Session, String> publisherMap = new HashMap<>();
    static int publisherReceiver;
    
    @Autowired
    private SimpMessageSendingOperations messageTemplate;
    
    @MessageMapping("/broadcast/sendMessage")
    @SendTo("/topics/general")
    public MessageHandler sendMessage(@Payload MessageHandler message) {
        return message;
    }

    @MessageMapping("/broadcast/addPublisher")
    @SendTo("/topics/general")
    public MessageHandler addPublisher(@Payload MessageHandler message, SimpMessageHeaderAccessor headerAccessor) {
            if (headerAccessor.getSessionAttributes() != null ){
                headerAccessor.getSessionAttributes().put("publisher", message.getSubscriber());
            }            
        return message;
    }
    
    @MessageMapping("/broadcast/messageTo/")
    @SendTo("/topics/queue/reply")
    public String processMessageFromClient(
            @Payload String message,
            Principal principal) throws Exception {
                return message;
    }
    
    @MessageExceptionHandler
    @SendToUser("/topics/queue/errors/")
    public String handleException(Throwable exception){
        return exception.getMessage();
    }
}
