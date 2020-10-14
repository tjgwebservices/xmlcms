package com.tjgwebservices.tjgxmlcms.model.research;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "Project")
public class Project implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String projectName;
    private String projectSubject;
    private String projectDescription;
    private Integer researcherId;

    public Project(){}
    
    public Project(String projectName, String projectSubject, 
            String projectDescription, int researcherId) {
        this.projectName = projectName;
        this.projectSubject = projectSubject;
        this.projectDescription = projectDescription;
        this.researcherId = researcherId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectSubject() {
        return projectSubject;
    }

    public void setProjectSubject(String projectSubject) {
        this.projectSubject = projectSubject;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Integer getResearcherId() {
        return researcherId;
    }

    public void setResearcherId(Integer researcherId) {
        this.researcherId = researcherId;
    }

    
}
