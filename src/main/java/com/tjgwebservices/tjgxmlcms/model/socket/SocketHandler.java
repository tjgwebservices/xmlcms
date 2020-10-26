package com.tjgwebservices.tjgxmlcms.model.socket;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SocketHandler  {
    List<SocketSession>sessions = new CopyOnWriteArrayList<>();
 
    private String publisherId;
    private String subscriptionId;


    public SocketHandler() {}
    
    public SocketHandler(String message) {
        sessions.add((SocketSession) new SocketSession("0",message));
    }

    public void handleTextMessage(SocketSession session){
        this.subscriptionId = String.valueOf(sessions.size()+1);
        
        for (SocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen() && !session.getId().equals(webSocketSession.getId())) {
                webSocketSession.sendMessage("Test");
            }
        }
    }
 
    public void afterConnectionEstablished(SocketSession session) throws Exception {
        sessions.add(session);
    }    

    public List<SocketSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<SocketSession> sessions) {
        this.sessions = sessions;
    }

}
