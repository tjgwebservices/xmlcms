package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.model.socket.SocketHandler;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class TopicController {

    @RequestMapping(value = "/topics", 
            method = RequestMethod.GET,
            produces = {"application/json","application/xml"})
    @ResponseBody
    public String topicRequests(@RequestBody ModelMap model){
        return "{\"id\":\"1\",\"message\":\"test\"}";  
    }

    @RequestMapping(value = "/topics", 
            method = RequestMethod.POST,
            produces = {"application/json","application/xml"})
    @ResponseBody
    public String topicPosts(ModelMap model){
        return "{\"id\":\"1\",\"message\":\"test\"}";  
    }

    @RequestMapping(value = "/topics/{id}", 
            method = RequestMethod.GET,
            produces = {"application/json","application/xml"})
    @ResponseBody
    public String topicMessages(
    @PathVariable String id, ModelMap model){
        return "{\"id\":\""+id+"\",\"message\":\"test\"}";  
    }

    @RequestMapping(value = "/topics/{id}", 
            method = RequestMethod.POST,
            produces = {"application/json","application/xml"})
    @ResponseBody
    public String postMessages(
    @PathVariable String id, @RequestBody ModelMap model){
        return "{\"id\":\""+id+"\",\"message\":\"test\"}";  
    }
    
    @RequestMapping("/topics/{id}")
    @ResponseBody
    public String handleTopicRequests(@PathVariable String id, 
            @RequestBody ModelMap model) {
        System.out.println("Handle Topic Requests");
        model.addAttribute("id",id);  
        return "process";
        
    }
    @RequestMapping("/topics/{id}/{messages}")
    @ResponseBody 
    public ResponseEntity<List<SocketHandler>> handleTopicMessages(@PathVariable String id,
            @PathVariable String message,
            @RequestBody ModelMap model) {
        System.out.println("Handle Topic messages");
        model.addAttribute("id",id);
        model.addAttribute("message",message);
        List<SocketHandler> list = new ArrayList<SocketHandler>();
        SocketHandler ws = new SocketHandler(message);
        return new ResponseEntity<List<SocketHandler>>(list,HttpStatus.OK);
        
    }

    
}
