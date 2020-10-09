package com.tjgwebservices.tjgxmlcms.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class DecodedMultipartFile implements MultipartFile{
    private byte[] fileContent;
    private String fileName;

    public DecodedMultipartFile(){
        fileContent = new byte[0];
        fileName = "";
    }
    
    public DecodedMultipartFile(InputStream is){
        try {
            fileContent = IOUtils.toByteArray(is);
        } catch (IOException ex) {
            fileContent = new byte[0];
            Logger.getLogger(DecodedMultipartFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getOriginalFilename() {
        return fileName;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return fileContent == null || fileContent.length ==0;
    }

    @Override
    public long getSize() {
        return fileContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return fileContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(fileContent);
        
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
        new FileOutputStream(file).write(fileContent);
    }
    
}
