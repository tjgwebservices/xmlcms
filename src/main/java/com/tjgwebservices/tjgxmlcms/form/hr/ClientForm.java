package com.tjgwebservices.tjgxmlcms.form.hr;

public class ClientForm {
    private String clientFirstName;
    private String clientLastName;
    private String clientSpecialty;
    private String clientContact;
    private Integer hrGroupId;

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
