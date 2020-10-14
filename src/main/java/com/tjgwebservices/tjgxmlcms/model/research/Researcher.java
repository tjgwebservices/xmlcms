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
@Table(name = "Researcher")
public class Researcher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String researcherFirstName;
    private String researcherLastName;
    private String researcherDegree;
    private String researcherMajor;
    private String researcherInstitution;
    private String researcherSpecialty;

    public Researcher(){}
    
    public Researcher(String researcherFirstName, String researcherLastName, 
            String researcherDegree, String researcherMajor, 
            String researcherInstitution, String researcherSpecialty) {
        this.researcherFirstName = researcherFirstName;
        this.researcherLastName = researcherLastName;
        this.researcherDegree = researcherDegree;
        this.researcherMajor = researcherMajor;
        this.researcherInstitution = researcherInstitution;
        this.researcherSpecialty = researcherSpecialty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResearcherFirstName() {
        return researcherFirstName;
    }

    public void setResearcherFirstName(String researcherFirstName) {
        this.researcherFirstName = researcherFirstName;
    }

    public String getResearcherLastName() {
        return researcherLastName;
    }

    public void setResearcherLastName(String researcherLastName) {
        this.researcherLastName = researcherLastName;
    }

    public String getResearcherDegree() {
        return researcherDegree;
    }

    public void setResearcherDegree(String researcherDegree) {
        this.researcherDegree = researcherDegree;
    }

    public String getResearcherMajor() {
        return researcherMajor;
    }

    public void setResearcherMajor(String researcherMajor) {
        this.researcherMajor = researcherMajor;
    }

    public String getResearcherInstitution() {
        return researcherInstitution;
    }

    public void setResearcherInstitution(String researcherInstitution) {
        this.researcherInstitution = researcherInstitution;
    }

    public String getResearcherSpecialty() {
        return researcherSpecialty;
    }

    public void setResearcherSpecialty(String researcherSpecialty) {
        this.researcherSpecialty = researcherSpecialty;
    }
    
}
