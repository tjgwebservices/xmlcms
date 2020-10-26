package com.tjgwebservices.tjgxmlcms.model.school;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.websocket.Encoder.Binary;
import org.hibernate.search.annotations.Indexed;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Indexed
@Table(name = "Lecture")
public class Lecture {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String lectureName;
    private MultipartFile lecturePoster;
    
    public Lecture(){}
    
    public Lecture(String lectureName, MultipartFile lecturePoster) {
        this.lectureName = lectureName;
        this.lecturePoster = lecturePoster;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public MultipartFile getLecturePoster() {
        return lecturePoster;
    }

    public void setLecturePoster(MultipartFile lecturePoster) {
        this.lecturePoster = lecturePoster;
    }

    
}
