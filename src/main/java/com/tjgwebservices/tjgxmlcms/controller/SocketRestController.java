package com.tjgwebservices.tjgxmlcms.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
public class SocketRestController {

    private SseEmitter emitter;
    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private static final String[] channels = {"channel1","channel2","channel3","channel4","channel5"};

    @RequestMapping(value = "/socket", method = RequestMethod.GET,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody    
    public ResponseEntity<ResponseBodyEmitter> pollEvents() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","socket-list");
        emitter = new SseEmitter();
       cachedThreadPool.execute(() -> {
           try {
               for (int i = 0; i < channels.length; i++) {
                   emitter.send(SseEmitter
                           .event()
                           .name("message")
                           .data("[{\"data\": \""+channels[i]+"\","
                                   + "\"event\":\"offer\"}]"));
                   TimeUnit.SECONDS.sleep(1);
               }
               emitter.complete();
           } catch (Exception e) {
               emitter.completeWithError(e);
           }
       });

       return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }    
    
   
    @RequestMapping(value = "/socket/{id}", method = RequestMethod.POST)  
    @ResponseBody 
    public ResponseEntity<ResponseBodyEmitter> postForEvents(@RequestParam String message, 
            Model model) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","socket-list");
        emitter = new SseEmitter();
       cachedThreadPool.execute(() -> {
           try {
                   emitter.send(SseEmitter
                           .event()
                           .name("message")
                           .data("[{\"data\":\"Post request for sockets\","
                                   + "\"event\":\"offer\"}]"));
               emitter.complete();
           } catch (Exception e) {
               emitter.completeWithError(e);
           }
       });

       return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }    
  
    @GetMapping("/success")
    @ResponseBody
    public ResponseEntity<String> getSuccess() {
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }


    void sendEvents() {
        try {
            emitter.send("Alpha");
            emitter.send("Omega");

            emitter.complete();
        } catch(Exception e) {
            emitter.completeWithError(e);
        }
    }


public class RequestMessage {
    private String messageId;
    private String message;

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}

