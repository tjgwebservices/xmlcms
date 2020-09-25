package com.tjgwebservices.tjgxmlcms.model;

public class MessageHandler {
    private MessageType type;
    private String message;
    private String subscriber;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }
    
    public enum MessageType {
        NEWSUBSCRIPTION, BROADCAST, CLOSESUBSCRIPTION
    }
}
