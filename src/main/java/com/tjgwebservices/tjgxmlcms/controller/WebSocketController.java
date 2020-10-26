package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.WebSocketConfig;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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

    @Autowired
    ServletContext context;
    
    @PostMapping(path="/socket", consumes="text/event-stream",
            produces="text/event-stream")
    public ResponseEntity<Object> eventStreamConsumer(){
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(1)
                .toUri();
        return ResponseEntity.created(location).build();
    }
    
    @RequestMapping(value = "/socket/{id}", method = RequestMethod.POST)
    //@ResponseBody
    public ResponseEntity<InputStreamResource> handleSocketRequests(@PathVariable String id) {
        System.out.println("Handle Socket Requests");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","conference-room");
        String mediaPath = context.getRealPath("") + File.separator +
                    "images" + File.separator + "conferenceseries.mp4";
        try {
            FileInputStream fis = new FileInputStream(mediaPath);
            byte[] b = new byte[1024];
            InputStreamResource inputStreamResource = new InputStreamResource(fis);
            headers.setContentLength(0);
            return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WebSocketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String contentResponse = "id:"+id+"\n"
                + "data:Socket Client Offer from "+id+"\n"
                        + "event: post request\n";
        InputStream stream = new ByteArrayInputStream(contentResponse.getBytes(StandardCharsets.UTF_8));
        return new ResponseEntity<>(new InputStreamResource(stream), headers, HttpStatus.OK);
        
    }

    @RequestMapping(value = "/socket/{id}", method = RequestMethod.GET)
    //@ResponseBody
    public ResponseEntity<String> startSocketRequests(@PathVariable String id) {
        System.out.println("Socket Request from"+id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","conference-room");
        
        return new ResponseEntity<>("Socket Client Offer from "+id, headers, HttpStatus.OK);
    }
    
    
    @GetMapping(name = "/socket", produces="application/xml", 
            consumes="application/xml")
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
    


    public void consumeServerSentEvent(){
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
