package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.controller.SocketRestController.RequestMessage;
import com.tjgwebservices.tjgxmlcms.model.socket.SocketHandler;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class TopicController extends HttpServlet {

    private SseEmitter emitter;
    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private static final String[] commands = {"test1","test2","test3","test4","test5"};
    
    @RequestMapping(value = "/topics/{id}", method = RequestMethod.GET)
    @ResponseBody    
    public ResponseEntity<ResponseBodyEmitter>  pollTopicEvents() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","event-stream-source");
        emitter = new SseEmitter();
       cachedThreadPool.execute(() -> {
           try {
               for (int i = 0; i < commands.length; i++) {
                   emitter.send(SseEmitter
                           .event()
                           .name("message")
                           .data(commands[i]));
                   TimeUnit.SECONDS.sleep(1);
               }
               emitter.complete();
           } catch (Exception e) {
               emitter.completeWithError(e);
           }
       });

       return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseBodyEmitter>  postTopicRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","json-source");
        emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter
                    .event()
                    .name("topic")
                    .data("json source topic"));
        } catch (IOException ex) {
            Logger.getLogger(SocketRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.POST,
            consumes=MediaType.TEXT_PLAIN_VALUE,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseBodyEmitter>  postTopicRequestText() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","text-plain-source");
        emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter
                    .event()
                    .name("topic")
                    .data("text plain source"));
        } catch (IOException ex) {
            Logger.getLogger(SocketRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_XML_VALUE,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody    
    public ResponseEntity<ResponseBodyEmitter>  postTopicRequestXML(@RequestBody RequestMessage requestMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","xml-source");
        emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter
                    .event()
                    .name("topic")
                    .data("xml source"));
        } catch (IOException ex) {
            Logger.getLogger(SocketRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.POST,
            consumes=MediaType.TEXT_EVENT_STREAM_VALUE,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseBodyEmitter>  postTopicRequestEvent(@RequestBody RequestMessage requestMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","event-stream"); 
        emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter
                    .event()
                    .name("topic")
                    .data("event stream"));
        } catch (IOException ex) {
            Logger.getLogger(SocketRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public String postTopicRequestForm2() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","form-encoded-value");
        emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter
                    .event()
                    .name("topic")
                    .data("form encoded value"));
        } catch (IOException ex) {
            Logger.getLogger(SocketRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/success";
    }

    
}
