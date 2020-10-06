package com.tjgwebservices.tjgxmlcms.form;

import javax.websocket.Encoder;

public class LectureForm {

    private String lectureName;
    private Encoder.Binary lecturePoster;

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public Encoder.Binary getLecturePoster() {
        return lecturePoster;
    }

    public void setLecturePoster(Encoder.Binary lecturePoster) {
        this.lecturePoster = lecturePoster;
    }

    
}
