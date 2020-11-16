package com.tjgwebservices.tjgxmlcms.model.game;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "Game")
public class Game implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private Integer highScore;
    private String created;
    private String lastUpdated;
    
    public Game(){}

    public Game(String title) {
        this.title = title;
        this.highScore = 0;
        this.created = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        this.lastUpdated = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }

    
    public Game(String title, Integer highScore, String created, String lastUpdated) {
        this.title = title;
        this.highScore = highScore;
        this.created = created;
        this.lastUpdated = lastUpdated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }


    
}
