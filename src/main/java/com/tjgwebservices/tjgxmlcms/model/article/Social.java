package com.tjgwebservices.tjgxmlcms.model.article;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "Social")
public class Social {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String postname;
    private String content;
    private Integer reviewed;
    private String published;

    public Social(){}
    
    public Social(String postname, String content, Integer reviewed, 
            String published) {
        this.postname = postname;
        this.content = content;
        this.reviewed = reviewed;
        this.published = published;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReviewed() {
        return reviewed;
    }

    public void setReviewed(Integer reviewed) {
        this.reviewed = reviewed;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

}
