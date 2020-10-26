package com.tjgwebservices.tjgxmlcms.model.school;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "Student")
public class Student {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String lastName;
    private String firstName;
    private Integer courseId;

    public Student(){}
    
    public Student(String lastName, String firstName, int courseId){
        this.lastName = lastName;
        this.firstName = firstName;
        this.courseId = courseId;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    
}
