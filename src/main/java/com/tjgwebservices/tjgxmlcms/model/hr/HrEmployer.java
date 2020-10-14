package com.tjgwebservices.tjgxmlcms.model.hr;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "HrEmployer")
public class HrEmployer implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String employerName;
    private String employerContact;
    private String employerContactType;
    private String employerContactInfo;
    private Integer hrGroupId;

    public HrEmployer() {}

    public HrEmployer(String employerName, String employerContact, String employerContactType, String employerContactInfo, int hrGroupId) {
        this.employerName = employerName;
        this.employerContact = employerContact;
        this.employerContactType = employerContactType;
        this.employerContactInfo = employerContactInfo;
        this.hrGroupId = hrGroupId;
        
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerContact() {
        return employerContact;
    }

    public void setEmployerContact(String employerContact) {
        this.employerContact = employerContact;
    }

    public String getEmployerContactType() {
        return employerContactType;
    }

    public void setEmployerContactType(String employerContactType) {
        this.employerContactType = employerContactType;
    }

    public String getEmployerContactInfo() {
        return employerContactInfo;
    }

    public void setEmployerContactInfo(String employerContactInfo) {
        this.employerContactInfo = employerContactInfo;
    }

    public Integer getHrGroupId() {
        return hrGroupId;
    }

    public void setHrGroupId(Integer hrGroupId) {
        this.hrGroupId = hrGroupId;
    }

    
}
