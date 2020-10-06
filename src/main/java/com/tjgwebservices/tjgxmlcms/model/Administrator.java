package com.tjgwebservices.tjgxmlcms.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "Adminisrator")
public class Administrator implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String administratorName;
    private Integer administratorGroupId;

    public Administrator(){}
    
    public Administrator(String administratorName, Integer administratorGroupId){
        this.administratorName = administratorName;
        this.administratorGroupId = administratorGroupId;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }

    public Integer getAdministratorGroupId() {
        return administratorGroupId;
    }

    public void setAdministratorGroupId(Integer administratorGroupId) {
        this.administratorGroupId = administratorGroupId;
    }

    
}
