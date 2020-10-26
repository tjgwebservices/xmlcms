package com.tjgwebservices.tjgxmlcms.controller;

import java.util.HashMap;
import java.util.Map;
import javax.websocket.Session;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    static Map<Session, String> publisherMap = new HashMap<>();
    static int publisherReceiver;
    

}
