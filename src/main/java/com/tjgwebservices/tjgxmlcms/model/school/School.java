package com.tjgwebservices.tjgxmlcms.model.school;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;


@Entity
@Indexed
@Table(name = "School")
public class School {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String schoolName;
    private String schoolLecturer;
    
    public School (){}
    
    public School(String schoolName, String schoolLecturer){
        this.schoolName = schoolName;
        this.schoolLecturer = schoolLecturer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolLecturer() {
        return schoolLecturer;
    }

    public void setSchoolLecturer(String schoolLecturer) {
        this.schoolLecturer = schoolLecturer;
    }

}
