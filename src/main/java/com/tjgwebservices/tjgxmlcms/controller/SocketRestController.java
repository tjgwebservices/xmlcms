package com.tjgwebservices.tjgxmlcms.controller;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
public class SocketRestController {

    private SseEmitter emitter;
    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private static final String[] channels = {"channel1","channel2","channel3","channel4","channel5"};

    @RequestMapping(value = "/socket", method = RequestMethod.GET,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody    
    public SseEmitter pollEvents() {
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
                           .data("[{\"data\":"+channels[i]+"}]"));
                   TimeUnit.SECONDS.sleep(1);
               }
               emitter.complete();
           } catch (Exception e) {
               emitter.completeWithError(e);
           }
       });

       return emitter;
    }    
    
    
    @RequestMapping(value = "/socket", method = RequestMethod.POST,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody    
    public SseEmitter postForEvents() {
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
                           .data("[{\"data\":\"Post request for sockets\""));
               emitter.complete();
           } catch (Exception e) {
               emitter.completeWithError(e);
           }
       });

       return emitter;
    }    

    @RequestMapping(value = "/socket/{id}", method = RequestMethod.GET,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody    
    public SseEmitter pollEvents(@PathVariable String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","poll-events");
        emitter = new SseEmitter();
       cachedThreadPool.execute(() -> {
           try {
                   emitter.send(SseEmitter
                           .event()
                           .name("message")
                           .data("[{\"data\": \"request received from "+id+"\"}]"));
                   TimeUnit.SECONDS.sleep(1);
               emitter.complete();
           } catch (Exception e) {
               emitter.completeWithError(e);
           }
       });

       return emitter;
    }

    @RequestMapping(value = "/socket/{id}", method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public SseEmitter postRequest(@PathVariable String id) {
        emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter
                    .event()
                    .name("test")
                    .data("[{\"data\": \"test message\""));
        } catch (IOException ex) {
            Logger.getLogger(SocketRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emitter;
    }

    @RequestMapping(value = "/socket/{id}", method = RequestMethod.POST,
            consumes=MediaType.TEXT_PLAIN_VALUE,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public SseEmitter postRequestText(@PathVariable String id) {
        emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter
                    .event()
                    .name("test")
                    .data("[{\"data\": \"test message\""));
        } catch (IOException ex) {
            Logger.getLogger(SocketRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emitter;
    }

    @RequestMapping(value = "/socket/{id}", method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_XML_VALUE,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody    
    public SseEmitter postRequestXML(@RequestBody RequestMessage requestMessage,@PathVariable String id) {
        emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter
                    .event()
                    .name("test")
                    .data("[{\"data\": \"test message\""));
        } catch (IOException ex) {
            Logger.getLogger(SocketRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emitter;
    }

    @RequestMapping(value = "/socket/{id}", method = RequestMethod.POST,
            consumes=MediaType.TEXT_EVENT_STREAM_VALUE,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public SseEmitter postRequestEvent(@RequestBody RequestMessage requestMessage,@PathVariable String id) {
        emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter
                    .event()
                    .name("test")
                    .data("[{\"data\": \"test message\""));
        } catch (IOException ex) {
            Logger.getLogger(SocketRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emitter;
    }
/*
    @RequestMapping(value = "/socket/{id}", method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    SseEmitter postRequestForm(@RequestParam MultiValueMap<String,String> paramMap) {
        emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter
                    .event()
                    .name("test")
                    .data("test message"));
        } catch (IOException ex) {
            Logger.getLogger(SocketRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emitter;
    }
*/
    // in another thread
    @RequestMapping(value = "/socket/{id}", method = RequestMethod.POST,
            consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public String postRequestForm2(@PathVariable String id) {
        emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter
                    .event()
                    .name("test")
                    .data("[{\"data\": \"test message\""));
        } catch (IOException ex) {
            Logger.getLogger(SocketRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/success";
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

