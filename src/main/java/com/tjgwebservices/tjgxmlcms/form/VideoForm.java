package com.tjgwebservices.tjgxmlcms.form;

import org.springframework.web.multipart.MultipartFile;

public class VideoForm {

    private String videoName;
    private MultipartFile videoContent;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public MultipartFile getVideoContent() {
        return videoContent;
    }

    public void setVideoPoster(MultipartFile videoPoster) {
        this.videoContent = videoContent;
    }
    
}
