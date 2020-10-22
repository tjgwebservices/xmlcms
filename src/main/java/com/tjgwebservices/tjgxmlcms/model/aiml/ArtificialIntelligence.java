package com.tjgwebservices.tjgxmlcms.model.aiml;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "ArtificialIntelligence")
public class ArtificialIntelligence {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private String algorithmPath;
    private String dataSourcePath;
    private String dataTargetPath;

    public ArtificialIntelligence(){}
    
    public ArtificialIntelligence(String title, String description, String algorithmPath, String dataSourcePath, String dataTargetPath) {
            this.title = title;
            this.description = description;
            this.algorithmPath = algorithmPath;
            this.dataSourcePath = dataSourcePath;
            this.dataTargetPath = dataTargetPath;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlgorithmPath() {
        return algorithmPath;
    }

    public void setAlgorithmPath(String algorithmPath) {
        this.algorithmPath = algorithmPath;
    }

    public String getDataSourcePath() {
        return dataSourcePath;
    }

    public void setDataSourcePath(String dataSourcePath) {
        this.dataSourcePath = dataSourcePath;
    }

    public String getDataTargetPath() {
        return dataTargetPath;
    }

    public void setDataTargetPath(String dataTargetPath) {
        this.dataTargetPath = dataTargetPath;
    }
    
    
}
