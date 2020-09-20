package com.tjgwebservices.tjgxmlcms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "Article")
public class Article {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String author;
    private String authorDate;
    private String title;
    private String description;
    private String content;
    
 
    public Article() {
 
    }
 
    public Article(String author, String authorDate,
            String title, String description,
            String content) {
        this.author = author;
        this.authorDate = authorDate;
        this.title = title;
        this.description = description;
        this.content = content;              
    }

        public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorDate() {
        return authorDate;
    }

    public void setAuthorDate(String authorDate) {
        this.authorDate = authorDate;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }

        public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
