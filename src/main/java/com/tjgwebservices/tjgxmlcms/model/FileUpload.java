package com.tjgwebservices.tjgxmlcms.model;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

    private String filename;
    private MultipartFile file;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
