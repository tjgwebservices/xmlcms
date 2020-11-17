package com.tjgwebservices.tjgxmlcms.controller.chat;

import com.tjgwebservices.tjgxmlcms.dbo.chat.ChatDBO;
import com.tjgwebservices.tjgxmlcms.form.chat.ChatForm;
import com.tjgwebservices.tjgxmlcms.model.chat.Chat;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class ChatController extends HttpServlet{

    
    private static List<Chat> chats = new ArrayList<Chat>();
    private SseEmitter emitter;
    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private static final String[] commands = {"test1","test2","test3","test4","test5"};

    
     @Autowired
    ServletContext context;

    static {
    }    

    @Value("${title.message}")
    private String titleMessage;


    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/chat/addChat" }, method = RequestMethod.GET)
    public String addChatForm(Model model) {
 
        ChatForm chatForm = new ChatForm();
        model.addAttribute("chatForm", chatForm);
        chats = ChatDBO.loadChats();
        model.addAttribute("chatList",chats); 
        return "/chat/addChat";
    }
    
    
    @RequestMapping(value = { "/chat/addChat" }, method = RequestMethod.POST)
    public String addChatSave(Model model, //
        @ModelAttribute("chatForm") ChatForm chatForm) {
        int userIdFrom = chatForm.getUserIdFrom();
        int userIdTo = chatForm.getUserIdTo();
        String dateTime = chatForm.getDateTime();
        int priority = chatForm.getPriority();
        String subject = chatForm.getSubject();
        String message = chatForm.getMessage();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();  
        if (subject != null && subject.length() > 0 && 
                message != null && message.length() > 0){
            Chat chat = new Chat(userIdFrom, userIdTo,
                                    formatter.format(date), priority,
                                    subject, message);
            chats.add(chat);
            ChatDBO.saveSQLChat(chat);
            return "redirect:/chat/addChat";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/chat/addChat";
    }

    @RequestMapping(value = { "/chat/newChat" }, method = RequestMethod.POST)
    public String newChatSave(Model model, //
        @ModelAttribute("chatForm") ChatForm chatForm) {
        int userIdFrom = chatForm.getUserIdFrom();
        int userIdTo = chatForm.getUserIdTo();
        int priority = chatForm.getPriority();
        String subject = chatForm.getSubject();
        String messagetext = chatForm.getMessage();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();  
        if (subject != null && subject.length() > 0 && 
                messagetext != null && messagetext.length() > 0){
            Chat chat = new Chat(userIdFrom, 0,
                                    formatter.format(date), 0,
                                    "Customer Service Request", 
                    "Customer Service Request");
            chats.add(chat);
            ChatDBO.saveSQLChat(chat);
            return "redirect:/chat/addChat";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/chat/addChat";
    }

    @RequestMapping(value = { "/chat/addChat/{id}" }, method = RequestMethod.GET)
    public String addChatIdForm(Model model, @PathVariable("id") Integer id) {
 
        ChatForm chatForm = new ChatForm();
        model.addAttribute("chatForm", chatForm);
        chats = ChatDBO.loadChats();
        List<Chat> conversationChats = chats.stream()
            .filter((chat) -> Objects.equals(chat.getUserIdFrom(), id))
            .collect(Collectors.toList());

        model.addAttribute("conversationChats",conversationChats); 
        return "/chat/addChat";
    }

    @RequestMapping(value = { "/chat/getChat/{id}" }, method = RequestMethod.POST)
    public String saveChatIdForm(Model model, @PathVariable("id") Integer id) {
 
        ChatForm chatForm = new ChatForm();
        model.addAttribute("chatForm", chatForm);
        chats = ChatDBO.loadChats();
        List<Chat> conversationChats = chats.stream()
            .filter((chat) -> Objects.equals(chat.getUserIdFrom(), id) ||
                            Objects.equals(chat.getUserIdTo(), id))
            .collect(Collectors.toList());
        
        model.addAttribute("conversationChats",conversationChats); 
        return "/chat/getChat";
    }

    @RequestMapping(value = { "/chat/checkServer" }, method = RequestMethod.GET)
    public String checkServerForm(Model model) {
 
        ChatForm chatForm = new ChatForm();
        model.addAttribute("chatForm", chatForm);
        chats = ChatDBO.loadChats();
        model.addAttribute("chatList",chats); 
 
        return "chat/checkServer";
    }
    
    @RequestMapping(value = { "/chat/checkServer" }, method = RequestMethod.POST)
    public String checkServerSave(Model model, @PathVariable("id") Integer id) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date();  
 
        ChatForm chatForm = new ChatForm();
        model.addAttribute("chatForm", chatForm);
        chats = ChatDBO.loadChats();
        List<Chat> conversationChats = chats.stream()
                
            .filter((chat) -> Objects.equals(chat.getUserIdFrom(), id))
            .filter((chat) -> chat.getDateTime().contains(formatter.format(date)))
            .collect(Collectors.toList());
        
        model.addAttribute("conversationChats",conversationChats); 
        return "/chat/checkServer";
    }

    @RequestMapping(value = { "/chat/newConversation" }, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseBodyEmitter> newConversation(Model model, //
        @RequestParam String conversationid) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","newConversation");
        int id = Integer.valueOf(conversationid);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();  
        Chat chat = new Chat(id, 0,
                                formatter.format(date), 0,
                                "Customer Service Request", 
                "Customer Service Request");
        chats.add(chat);
        ChatDBO.saveSQLChat(chat);
        try {
            String chatMessage = "[{\"to\":\""+conversationid
                   +"\", \"time\": \""+formatter.format(date)+
                   "\", \"message\": \"How can we help you today?\"}]";
           emitter.send(
                    SseEmitter
                            .event()
                            .name("[{\"conversation\": \""+conversationid+"\"}]")                    
                            .data(chatMessage));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
        return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }

    
    @RequestMapping(value = { "/chat/checkConversation" }, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseBodyEmitter> checkConversation(
            @RequestParam String conversationid, Model model) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date();  
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","checkConversation");
        emitter = new SseEmitter();
        String dataMessage = "[{\"to\":\""+conversationid+"\"time\",\"no message\"}]";
        chats = ChatDBO.loadChats();
        if (chats.size()>0) {
            List<Chat> conversationChats = chats.stream()

                .filter((chat) -> Objects.equals(chat.getUserIdFrom(), conversationid))
                .filter((chat) -> chat.getDateTime().contains(formatter.format(date)))
                .collect(Collectors.toList());

            if (conversationChats.size()>0) {
                cachedThreadPool.execute(() ->{
                    try {
                        for (Chat chat : conversationChats){
                            String chatMessage = "[{\"to\":\""+conversationid
                                    +"\", \"time\": \""+chat.getMessage()+
                                    "\", \"message\": \""+chat.getMessage()+ "\"}]";
                            emitter.send(
                                SseEmitter
                                .event()
                                .name("[{\"conversation\": \""+conversationid+"\"}]")
                                .data(chatMessage));
                        }
                        emitter.complete();
                    } catch (IOException e) {
                        emitter.completeWithError(e);
                    }

                    });
                } else {
                try {
                    String chatMessage = "[{\"to\":\""+conversationid
                            +"\", \"time\": \""+formatter.format(date)+
                            "\", \"message\": \"No Messages\"}]";
                    emitter.send(
                            SseEmitter
                                    .event()
                                    .name("[{\"conversation\": \""+conversationid+"\"}]")                    
                                    .data(chatMessage));
                } catch (IOException e) {
                    emitter.completeWithError(e);
                }
            }
        }
        return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }


    @RequestMapping(value = { "/chat/continueConversation" }, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseBodyEmitter> continueConversation(
            @RequestParam String conversationid, @RequestParam String messagetext,
            Model model) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();  
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","checkConversation");
        Chat newchat = new Chat();
        newchat.setMessage(messagetext);
        newchat.setPriority(0);
        newchat.setSubject("Customer Service");
        newchat.setDateTime(formatter.format(date));
        newchat.setUserIdFrom(Integer.valueOf(conversationid));
        newchat.setUserIdTo(0);
        ChatDBO.saveSQLChat(newchat);
        emitter = new SseEmitter();
        chats = ChatDBO.loadChats();
        List<Chat> conversationChats = chats.stream()       
            .filter((chat) -> Objects.equals(chat.getUserIdFrom(), conversationid))
            .filter((chat) -> chat.getDateTime().contains(formatter.format(date)))
            .collect(Collectors.toList());
        
        cachedThreadPool.execute(() ->{
            try {
                for (Chat chat : conversationChats){
                    String chatMessage = "[{\"to\":\""+conversationid
                            +"\", \"time\": \""+chat.getMessage()+
                            "\", \"message\": \""+chat.getMessage()+ "\"}]";
                    emitter.send(
                        SseEmitter
                        .event()
                        .name("[{\"conversation\": \""+conversationid+"\"}]")
                        .data(chatMessage));
                }
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
            
        });
        return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }
    
}
