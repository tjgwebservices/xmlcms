package com.tjgwebservices.tjgxmlcms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Market {


private String heading;
private String version;
private String headingtitle;
private String headingdescription;
private List<String> links;
private List<String> additionallinks;
private List<String> sitelinks;
private List<Map<String,String>> topics;
private List<Map<String,String>> services;
private List<Map<String,String>> analyses;
private List<Map<String,String>> sessions;
private List<Figure> figures;
private List<Map<String,String>> articles;

    public Market(){
        links = new ArrayList<>();
        additionallinks = new ArrayList<>();
        sitelinks = new ArrayList<>();
        topics = new ArrayList<>();
        services = new ArrayList<>();
        analyses = new ArrayList<>();
        sessions = new ArrayList<>();
        figures = new ArrayList<>();
        articles = new ArrayList<>();
        
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHeadingtitle() {
        return headingtitle;
    }

    public void setHeadingtitle(String headingtitle) {
        this.headingtitle = headingtitle;
    }

    public String getHeadingdescription() {
        return headingdescription;
    }

    public void setHeadingdescription(String headingdescription) {
        this.headingdescription = headingdescription;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public List<String> getAdditionallinks() {
        return additionallinks;
    }

    public void setAdditionallinks(List<String> additionallinks) {
        this.additionallinks = additionallinks;
    }

    public List<String> getSitelinks() {
        return sitelinks;
    }

    public void setSitelinks(List<String> sitelinks) {
        this.sitelinks = sitelinks;
    }

    public List<Map<String, String>> getTopics() {
        return topics;
    }

    public void setTopics(List<Map<String, String>> topics) {
        this.topics = topics;
    }

    public List<Map<String, String>> getServices() {
        return services;
    }

    public void setServices(List<Map<String, String>> services) {
        this.services = services;
    }

    public List<Map<String, String>> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Map<String, String>> analyses) {
        this.analyses = analyses;
    }

    public List<Map<String, String>> getSessions() {
        return sessions;
    }

    public void setSessions(List<Map<String, String>> sessions) {
        this.sessions = sessions;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public List<Map<String, String>> getArticles() {
        return articles;
    }

    public void setArticles(List<Map<String, String>> articles) {
        this.articles = articles;
    }



protected class Figure {

    String title;
    String description;
    List<String> elements;
    
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

        public List<String> getElements() {
            return elements;
        }

        public void setElements(List<String> elements) {
            this.elements = elements;
        }
    }

}
