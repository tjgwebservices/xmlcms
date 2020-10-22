package com.tjgwebservices.tjgxmlcms.form.aiml;

public class ArtificialIntelligenceForm {

    private Integer id;
    private String title;
    private String description;
    private String algorithmPath;
    private String dataSourcePath;
    private String dataTargetPath;


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
