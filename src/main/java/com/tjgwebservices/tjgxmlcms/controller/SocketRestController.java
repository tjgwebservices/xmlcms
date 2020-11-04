package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.model.conference.Room;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private static final String[] channels = {"channel1","channel2"};

    private static List<Room> rooms = new ArrayList<>();

    @RequestMapping(value = "/socket", method = RequestMethod.GET,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody    
    public ResponseEntity<ResponseBodyEmitter> pollEvents() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","poll-rooms");
        String responseText;
        List<String> ids = new ArrayList<>(); 
        
        Iterator<Room> ri = rooms.iterator();
        while (ri.hasNext()) {
            Room r = ri.next();
            ids.add("room id "+r.getId());
        }
        responseText = ids.toString().replaceAll(", ", " ").replaceAll("\\[|\\]", "");
        emitter = new SseEmitter();
       cachedThreadPool.execute(() -> {
           try {
               for (int i = 0; i < channels.length; i++) {
                   emitter.send(SseEmitter
                           .event()
                           .name("message")
                           .data("[{\"data\": \""+channels[i]+"\","
                                   + "\"response\":\""+responseText+"\","
                                   + "\"event\":\"offer\"}]"));
                   TimeUnit.SECONDS.sleep(15);
               }
               emitter.complete();
           } catch (Exception e) {
               emitter.completeWithError(e);
           }
       });

       return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }    
    
    @RequestMapping(value = "/socket/{id}", method = RequestMethod.GET,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody    
    public ResponseEntity<ResponseBodyEmitter> connectRoom(@PathVariable("id") Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","connect-room");
        String responseText;
        String roomInfo;
        List<Room> currentRooms = rooms.stream()
            .filter((room) -> Objects.equals(room.getId(), id))
            .collect(Collectors.toList());
        
        if (currentRooms.size() < 1){
            responseText = "No rooms with id "+id;
            roomInfo = "{}";
        } else {
            if (currentRooms.size() > 1){
                responseText = "Multiple rooms with id "+id;
                roomInfo = "{}";
            } else {
                
                responseText = "1 room with id: "+id+" and attendees: "+rooms.get(0).getAttendees();
                roomInfo = "{\"sdp\":\""+rooms.get(0).getSdp()+"\"},{"+
                        "\"type\": \""+rooms.get(0).getType()+"\"}";
            }
        }
        
        emitter = new SseEmitter();
       cachedThreadPool.execute(() -> {
           try {
               for (int i = 0; i < channels.length; i++) {
                   emitter.send(SseEmitter
                           .event()
                           .name("message")
                           .data("[{\"data\": \""+channels[i]+"\","
                                   + "\"response\":\""+responseText+"\","
                                   + "\"room\":"+roomInfo+"\","
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
            @RequestParam String sdp, @RequestParam String type, @PathVariable("id") Integer id, Model model) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","create-room");
        List<Room> currentRooms = rooms.stream()
            .filter((room) -> Objects.equals(room.getId(), id))
            .collect(Collectors.toList());
        System.out.println("sdp="+sdp);
        System.out.println("type="+type);
        
        if (currentRooms.size()>0){
            if (currentRooms.size()==1){
                currentRooms.get(0).setAttendees(currentRooms.get(0).getAttendees()+1);
            } else {
                System.out.println("Multiple Rooms Found");
            }
            
        }else {
            Room room = new Room(id, sdp, type, 1);
            rooms.add(room);
        }
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

    @RequestMapping(value = "/socket/{id}/{command}", method = RequestMethod.POST)  
    @ResponseBody 
    public ResponseEntity<ResponseBodyEmitter> roomCommands(@RequestParam String message, 
            @RequestParam String sdp, @RequestParam String type, 
            @PathVariable("id") Integer id, 
            @PathVariable("command") String command, Model model) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","room-commands");
        final String responseText;
        List<Room> currentRooms = rooms.stream()
            .filter((room) -> Objects.equals(room.getId(), id))
            .collect(Collectors.toList());
        System.out.println("sdp="+sdp);
        System.out.println("type="+type);
        /*
        if (currentRooms.size()>0){
            if (currentRooms.size()==1){
                currentRooms.get(0).setAttendees(currentRooms.get(0).getAttendees()+1);
            } else {
                System.out.println("Multiple Rooms Found");
            }
            
        }else {
            Room room = new Room(id, sdp, type, 1);
            rooms.add(room);
        }*/
        
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

    public static List<Room> getRooms() {
        return rooms;
    }

    public static void setRooms(List<Room> rooms) {
        SocketRestController.rooms = rooms;
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

