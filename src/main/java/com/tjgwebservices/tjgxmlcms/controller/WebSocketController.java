package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.WebSocketConfig;
import com.tjgwebservices.tjgxmlcms.model.SocketHandler;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@WebServlet(name = "UploadFile", urlPatterns = {"/UploadFile"},
    initParams = { @WebInitParam(name = "uploadpath", value = "/var/www/upload/") })
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,
        maxFileSize         = 1024 * 1024 * 10,
        maxRequestSize      = 1024 * 1024 * 15,
        location            = "."
)
public class WebSocketController extends HttpServlet {
/*
    @MessageMapping("/socketDisplay" )
    @SendTo("/socket") 
    public String displayMessage(){
        System.out.println("Display Message");
        return "display";
    }

    @MessageMapping("/topics" )
    @SendTo("/socket") 
    public String displayTopics(){
        System.out.println("Display Topics");
        return "display";
    }
*/
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
    public String poatMessages(
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

    @RequestMapping("/socket/{id}")
    @ResponseBody
    public String handleSocketRequests(@PathVariable String id, 
            @RequestBody ModelMap model) {
        System.out.println("Handle Socket Requests");
        model.addAttribute("id",id);        
        return "process";
        
    }
    
    @GetMapping(name = "/socket", produces="application/xml")
    @ResponseBody
    public String getStreams(UriComponentsBuilder uriInfo){
        System.out.println("Handle Get Streams");
        String wssUrl = "wss://"+uriInfo.build().getHost() + "/topics/messages";
        StringBuilder sb = new StringBuilder();
        sb.append("<root>");
        sb.append(uriInfo.build().toUriString());
        sb.append("</root>");
        return sb.toString();
    }

    public String uploadFile(HttpServletRequest request, 
            HttpServletResponse response) throws IOException,
            ServletException {
        response.setContentType("text/plain;charset=UTF-8");
        ServletOutputStream os = response.getOutputStream();
        WebSocketConfig ws = new WebSocketConfig();
        String path= getInitParameter("uploadPath");
        Part filePart = request.getPart("theFile");
        String fileName = filePart.getSubmittedFileName();
        InputStream is = filePart.getInputStream();
        Files.copy(is, Paths.get(path +fileName),
                StandardCopyOption.REPLACE_EXISTING);
        return "success";
    }
}
