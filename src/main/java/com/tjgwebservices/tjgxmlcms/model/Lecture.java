package com.tjgwebservices.tjgxmlcms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.websocket.Encoder.Binary;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "Lecture")
public class Lecture {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String lectureName;
    private Binary lecturePoster;

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

    public Binary getLecturePoster() {
        return lecturePoster;
    }

    public void setLecturePoster(Binary lecturePoster) {
        this.lecturePoster = lecturePoster;
    }

    
}
