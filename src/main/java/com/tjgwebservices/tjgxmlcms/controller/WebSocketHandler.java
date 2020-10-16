package com.tjgwebservices.tjgxmlcms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjgwebservices.tjgxmlcms.model.SocketMessage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {

    List<WebSocketSession>sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
                SocketMessage messageString = new ObjectMapper().readValue(
                        StringEscapeUtils.unescapeJava(
                        message.getPayload()
                                .substring(1,message.getPayload().length() - 1)), 
                        SocketMessage.class);
                System.out.println(messageString);

                
                for (WebSocketSession webSocketSession : sessions) {
                    if (webSocketSession.isOpen() &&
                            !session.getId().equals(webSocketSession.getId())) {
                        webSocketSession.sendMessage(message);
                    }
                }
            }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws
            Exception {
        sessions.add(session);
    }


}

