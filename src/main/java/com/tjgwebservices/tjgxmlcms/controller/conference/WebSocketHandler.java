package com.tjgwebservices.tjgxmlcms.controller.conference;

import com.tjgwebservices.tjgxmlcms.model.socket.SocketSession;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WebSocketHandler  {

    List<SocketSession>sessions = new CopyOnWriteArrayList<>();

    public void handleTextMessage(SocketSession session){
                for (SocketSession webSocketSession : sessions) {
                    if (webSocketSession.isOpen() &&
                            !session.getId().equals(webSocketSession.getId())) {
                        webSocketSession.sendMessage("Test");
                    }
                }
            }
    
    public void afterConnectionEstablished(SocketSession session) {
        sessions.add(session);
    }


}

