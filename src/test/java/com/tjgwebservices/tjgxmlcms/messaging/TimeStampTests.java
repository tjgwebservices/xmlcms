package com.tjgwebservices.tjgxmlcms.messaging;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TimeStampTests {

    @BeforeEach
    public void setUp() throws Exception {
    }        

    
    String test1="11/15/2020 9:56:25";
    String test2="2020-12-05 05:06:48.209";
    String test3 ="Nov 12, 2018 13:02:56.12345678";
    
    @Test
    @Disabled
    public void testTimeStamp1() throws Exception {
        Timestamp testTimeStamp;
        testTimeStamp = convertTimeStamp(test1);
        
        Assertions.assertEquals("0001",testTimeStamp.toString());
    }
    
    @Test
    @Disabled
    public void testTimeStamp2() throws Exception {
        Timestamp testTimeStamp;
        testTimeStamp = convertTimeStamp(test2);
            Date date = new Date();
            
        boolean currentDate = testTimeStamp.toString().contains(date.toString());
        Assertions.assertEquals(true,currentDate);
    }
    
    @Test
    @Disabled
    public void testTimeStamp3() throws Exception {
        Timestamp testTimeStamp;
        testTimeStamp = convertTimeStamp(test3);
        Assertions.assertEquals("2018-11-12 13:02:56.123",testTimeStamp.toString());
    }
    
        
    private static Timestamp convertTimeStamp(String timestampAsString){
        String pattern = "MMM dd, yyyy HH:mm:ss.SS";
        //String timestampAsString = 
        Timestamp timestamp;
        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(timestampAsString));
        timestamp = Timestamp.valueOf(localDateTime);
        } catch (DateTimeParseException e){
            Date date = new Date();
            timestamp = new Timestamp(date.getTime());
        }
        return timestamp;        
    }
    
}
