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
@Table(name = "HrClient")
public class HrClient implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String clientFirstName;
    private String clientLastName;
    private String clientSpecialty;
    private String clientContact;
    private Integer hrGroupId;

    public HrClient() {
    }

    public HrClient(String clientFirstName, String clientLastName, String clientSpecialty, String clientContact, int hrGroupId) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientSpecialty = clientSpecialty;
        this.clientContact = clientContact;
        this.hrGroupId = hrGroupId;
        
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientSpecialty() {
        return clientSpecialty;
    }

    public void setClientSpecialty(String clientSpecialty) {
        this.clientSpecialty = clientSpecialty;
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    public Integer getHrGroupId() {
        return hrGroupId;
    }

    public void setHrGroupId(Integer hrGroupId) {
        this.hrGroupId = hrGroupId;
    }

}
