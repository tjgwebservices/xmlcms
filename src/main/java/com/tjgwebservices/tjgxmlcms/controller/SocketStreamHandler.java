package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.model.socket.SocketSession;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SocketStreamHandler  {

    List<SocketSession>sessions = new CopyOnWriteArrayList<>();

    public void handleTextMessage(SocketSession session)
             {
                for (SocketSession webSocketSession : sessions) {
                    
                        System.out.println(webSocketSession.getId());
                    };
                }
    
    public void afterConnectionEstablished(SocketSession session) {
        sessions.add(session);
    }
    
}
