package com.tjgwebservices.tjgxmlcms.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormValidation {

    public static boolean dateValidation(String dateString){
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(dateString);
            
        } catch (ParseException ex) {
            Logger.getLogger(FormValidation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
        return true;
    }
    
    public static String dateFromTime(long time){
        Date formDate = new Date(time);
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(formDate);
        return dateString;
    } 
}
