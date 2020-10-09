package com.tjgwebservices.tjgxmlcms.form;

import org.springframework.web.multipart.MultipartFile;

public class LectureForm {

    private String lectureName;
    private MultipartFile lecturePoster;

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public MultipartFile getLecturePoster() {
        return lecturePoster;
    }

    public void setLecturePoster(MultipartFile lecturePoster) {
        this.lecturePoster = lecturePoster;
    }

    
}
