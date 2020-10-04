package com.tjgwebservices.tjgxmlcms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;


@Entity
@Indexed
@Table(name = "LectureNote")
public class LectureNote {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String noteInstructor;
    private String noteLecture;
    private String noteText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteInstructor() {
        return noteInstructor;
    }

    public void setNoteInstructor(String noteInstructor) {
        this.noteInstructor = noteInstructor;
    }

    public String getNoteLecture() {
        return noteLecture;
    }

    public void setNoteLecture(String noteLecture) {
        this.noteLecture = noteLecture;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    
}
