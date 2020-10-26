package com.tjgwebservices.tjgxmlcms.messaging;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjgwebservices.tjgxmlcms.model.socket.SocketMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SocketMessageTests {

    String testMessage = "{\"message\":\"test\"}";
    
    @Test
    public void testMessageObject() throws Exception{                
        SocketMessage message = new ObjectMapper().readValue(testMessage, SocketMessage.class);
        Assertions.assertEquals("test",message.message);
    }

    @Test
    public void testMessageObject2() throws Exception{   
        ObjectMapper mapper = new ObjectMapper();
        SocketMessage msg = mapper.reader().forType(SocketMessage.class).readValue(testMessage);
        Assertions.assertEquals("test",msg.message);
    }

    @Test
    public void testMapper1()throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonString= "{\"message\" : \"test\"}";
        SocketMessage result= mapper.readValue(jsonString, SocketMessage.class);
        Assertions.assertEquals("test",result.message);
    }

    @Test
    public void testMapper2()throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonString= "{\"message\" : \"test\"}";
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SocketMessage msg = mapper.readValue(jsonString, SocketMessage.class);
        JsonNode jsonNodeRoot = mapper.readTree(jsonString);
        JsonNode jsonNodeMessage = jsonNodeRoot.get("message");
        String message = jsonNodeMessage.asText();
        Assertions.assertEquals("test",message);
    }
    
   
 }

