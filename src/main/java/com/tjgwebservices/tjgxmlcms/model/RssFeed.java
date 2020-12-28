package com.tjgwebservices.tjgxmlcms.model;

import java.util.ArrayList;
import java.util.List;

public class RssFeed {

    private String channelTitle;
    private String channelLink;
    private String channelDescription;
    private List<String[]> items;

    public RssFeed (){
        items = new ArrayList<String[]>();
    }
    
    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getChannelLink() {
        return channelLink;
    }

    public void setChannelLink(String channelLink) {
        this.channelLink = channelLink;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public List<String[]> getItems() {
        return items;
    }

    public void setItems(List<String[]> items) {
        this.items = items;
    }


}

