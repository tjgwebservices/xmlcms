package com.tjgwebservices.tjgxmlcms.services;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RestCustomException extends Exception{

    public RestCustomException() throws Exception{
       Logger.getLogger(RestErrorHandler.class.getName()).log(Level.SEVERE, null, "error");
        throw new Exception("Custom Exception");
    }

    RestCustomException(String message) {
       Logger.getLogger(RestErrorHandler.class.getName()).log(Level.SEVERE, null, message);

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
