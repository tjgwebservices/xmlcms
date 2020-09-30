package com.tjgwebservices.tjgxmlcms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "Journal")
public class SerialJournal {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String journalName;
    
 
    public SerialJournal() {
 
    }
 
    public SerialJournal(String journal) {
	this.journalName = journalName;
}



        public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }


    public String getJournalName() {
        return journalName;
    }

    public void setJournal(String journalName) {
        this.journalName = journalName;
    }


}

