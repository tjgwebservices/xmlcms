package com.tjgwebservices.tjgxmlcms.model.school;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "Lecturer")
public class Lecturer implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String lecturerName;
    
    public Lecturer (){}
    
    public Lecturer(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    
}
