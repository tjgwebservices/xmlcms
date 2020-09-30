package com.tjgwebservices.tjgxmlcms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "Publisher")
public class Publisher {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String publisherName;
    
 
    public Publisher() {
 
    }
 
    public Publisher(String publisherName) {
	this.publisherName = publisherName;
    }



    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }


    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }


}

