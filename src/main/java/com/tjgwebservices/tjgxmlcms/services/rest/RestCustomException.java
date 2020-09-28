package com.tjgwebservices.tjgxmlcms.services.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RestCustomException extends Exception{

    public RestCustomException() throws Exception{
       Logger.getLogger(RestCustomException.class.getName()).log(Level.SEVERE, null, "error");
        throw new Exception("Custom Exception");
    }

    RestCustomException(String message) throws Exception{
       Logger.getLogger(RestCustomException.class.getName()).log(Level.SEVERE, null, message);
        throw new Exception("Custom Exception");
    }
    
}
