package com.tjgwebservices.tjgxmlcms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Consulting {

private String heading;
private String version;
private String headingtitle;
private String headingdescription;
private String theme;
private String componenttitle;
private List<Map<String,String>> links;
private List<Map<String,String>> tabs;
private List<Map<String,String>> sideimages;
private List<String[]> mainarticles;
private List<String[]> subarticles;
private List<String[]> leadarticles;


    public Consulting(){
        links=new ArrayList<Map<String,String>>();
        tabs=new ArrayList<Map<String,String>>();
        sideimages=new ArrayList<Map<String,String>>();
        mainarticles=new ArrayList<String[]>();
        subarticles=new ArrayList<String[]>();
        leadarticles=new ArrayList<String[]>();
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getComponenttitle() {
        return componenttitle;
    }

    public void setComponenttitle(String componenttitle) {
        this.componenttitle = componenttitle;
    }

    public List<Map<String, String>> getLinks() {
        return links;
    }

    public void setLinks(List<Map<String, String>> links) {
        this.links = links;
    }

    public List<Map<String, String>> getTabs() {
        return tabs;
    }

    public void setTabs(List<Map<String, String>> tabs) {
        this.tabs = tabs;
    }

    public List<Map<String, String>> getSideimages() {
        return sideimages;
    }

    public void setSideimages(List<Map<String, String>> sideimages) {
        this.sideimages = sideimages;
    }

    public List<String[]> getMainarticles() {
        return mainarticles;
    }

    public void setMainarticles(List<String[]> mainarticles) {
        this.mainarticles = mainarticles;
    }

    public List<String[]> getSubarticles() {
        return subarticles;
    }

    public void setSubarticles(List<String[]> subarticles) {
        this.subarticles = subarticles;
    }

    public List<String[]> getLeadarticles() {
        return leadarticles;
    }

    public void setLeadarticles(List<String[]> leadarticles) {
        this.leadarticles = leadarticles;
    }



}
