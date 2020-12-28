package com.tjgwebservices.tjgxmlcms.controller.topic;

import com.tjgwebservices.tjgxmlcms.model.conference.Room;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class RestTopicController {

    private SseEmitter emitter;
    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private static final String[] channels = {"channel1","channel2"};

    private static List<Room> rooms = new ArrayList<>();


    @RequestMapping(value = "/message", method = RequestMethod.GET,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody    
    public ResponseEntity<ResponseBodyEmitter> pollMessages() {
        emitter = new SseEmitter();
       cachedThreadPool.execute(() -> {
           try {
               for (int i = 0; i < channels.length; i++) {
                   emitter.send(SseEmitter
                           .event()
                           .name("message")
                           .data("[{\"data\": \""+channels[i]+"\","
                                   + "\"response\":\"polling messages\","
                                   //+ "\"RTCSessionDescription\":{\"type\":\""+type+"\",\"sdp\":\""+sdp+"\"},"
                                   + "\"channel\":\""+channels[i]+"\","
                                   + "\"event\":\"offer\"}]"));
                   TimeUnit.SECONDS.sleep(15);
               }
               emitter.complete();
           } catch (Exception e) {
               emitter.completeWithError(e);
           }
       });

       return new ResponseEntity<>(emitter, createHeaders("poll-rooms"), HttpStatus.OK);
    }    

    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody    
    public ResponseEntity<ResponseBodyEmitter> pollMessagesForId(
            @PathVariable("id") Integer id, Model model) {
        emitter = new SseEmitter();
       cachedThreadPool.execute(() -> {
           try {
               for (int i = 0; i < channels.length; i++) {
                   emitter.send(SseEmitter
                           .event()
                           .name("message")
                           .data("[{\"data\": \""+channels[i]+"\","
                                   + "\"response\":\"polling messages\","
                                   //+ "\"RTCSessionDescription\":{\"type\":\""+type+"\",\"sdp\":\""+sdp+"\"},"
                                   + "\"channel\":\""+channels[i]+"\","
                                   + "\"event\":\"offer\"}]"));
                   TimeUnit.SECONDS.sleep(15);
               }
               emitter.complete();
           } catch (Exception e) {
               emitter.completeWithError(e);
           }
       });

       return new ResponseEntity<>(emitter, createHeaders("poll-rooms"), HttpStatus.OK);
    }    

    @RequestMapping(value = "/message/{id}/{command}", method = RequestMethod.POST,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)  
    @ResponseBody 
    public ResponseEntity<ResponseBodyEmitter> roomCommands(@RequestParam String message, 
            @RequestParam String sdp, @RequestParam String type, 
            @PathVariable("id") Integer id, 
            @PathVariable("command") String command, Model model) {
        final String responseText;
        List<Room> currentRooms = rooms.stream()
            .filter((room) -> Objects.equals(room.getId(), id))
            .collect(Collectors.toList());
        System.out.println("sdp="+sdp);
        System.out.println("type="+type);
        if (command == "close-room"){
            List<Room> filteredRooms = rooms
                    .stream()
                    .filter((room) -> !Objects.equals(room.getId(), id))
                    .collect(Collectors.toList());
            rooms = filteredRooms;
            responseText = "Room id "+id+" is closed.";
            
        }else if (command == "close-all-rooms"){
            rooms = new ArrayList<Room>();
            responseText = "All rooms are closed.";
        } else {
            responseText = "Received command " + command;        
        }
        emitter = new SseEmitter();
       cachedThreadPool.execute(() -> {
           try {
                   emitter.send(SseEmitter
                           .event()
                           .name("message")
                           .data("[{\"data\":\""+responseText+"\","
                                   + "\"event\":\"answer\"}]"));
               emitter.complete();
           } catch (Exception e) {
               emitter.completeWithError(e);
           }
       });

       return new ResponseEntity<>(emitter, createHeaders("room-commands"), HttpStatus.OK);
    }    
    
    
    private HttpHeaders createHeaders(String customHeader){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source",customHeader);
        return headers;
        
    }
    
}
