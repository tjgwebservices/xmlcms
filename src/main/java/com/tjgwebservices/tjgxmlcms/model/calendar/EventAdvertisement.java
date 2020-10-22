package com.tjgwebservices.tjgxmlcms.model.calendar;

import com.tjgwebservices.tjgxmlcms.dbo.DatabaseObject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "EventAdvertisement")
public class EventAdvertisement extends DatabaseObject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String subTitle;
    private String adImagePath;
    private String contactInfo;
    private Integer eventId;

    public EventAdvertisement(){}
    
    public EventAdvertisement(String title, String subTitle, String adImagePath, String contactInfo, int eventId) {
        this.title = title;
        this.subTitle = subTitle;
        this.adImagePath = adImagePath;
        this.contactInfo = contactInfo;
        this.eventId = eventId;        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAdImagePath() {
        return adImagePath;
    }

    public void setAdImagePath(String adImagePath) {
        this.adImagePath = adImagePath;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
            
    
}
