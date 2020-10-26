package com.tjgwebservices.tjgxmlcms.form.chat;

public class ChatForm {

    private Integer id;
    private Integer userIdFrom;
    private Integer userIdTo;
    private String dateTime;
    private Integer priority;
    private String subject;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(Integer userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public Integer getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(Integer userIdTo) {
        this.userIdTo = userIdTo;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}
