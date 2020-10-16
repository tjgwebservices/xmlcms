package com.tjgwebservices.tjgxmlcms.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Indexed
@Table(name = "Video")
public class Video implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String videoName;
    private MultipartFile videoContent;

    public Video(){}
    
    public Video(String videoName, MultipartFile videoContent) {
        this.videoName = videoName;
        this.videoContent = videoContent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public MultipartFile getVideoContent() {
        return videoContent;
    }

    public void setVideoContent(MultipartFile videoContent) {
        this.videoContent = videoContent;
    }
    
}
