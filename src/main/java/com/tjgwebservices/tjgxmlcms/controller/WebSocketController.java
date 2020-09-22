package com.tjgwebservices.tjgxmlcms.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/socketDisplay" )
    @SendTo("/socket") 
    public String displayMessage(){
        return "display";
    }


}
