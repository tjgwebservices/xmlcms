package com.tjgwebservices.tjgxmlcms.model.conference;

public class Room {

    private Integer id;
    private String sdp;
    private String type;
    private Integer attendees;

    public Room (){}
    
    public Room(Integer id, String sdp, String type, Integer attendees) {
        this.id = id;
        this.sdp = sdp;
        this.type = type;
        this.attendees = attendees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSdp() {
        return sdp;
    }

    public void setSdp(String sdp) {
        this.sdp = sdp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAttendees() {
        return attendees;
    }

    public void setAttendees(Integer attendees) {
        this.attendees = attendees;
    }
           
}
