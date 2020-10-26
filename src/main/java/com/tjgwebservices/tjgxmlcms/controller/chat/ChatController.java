package com.tjgwebservices.tjgxmlcms.controller.chat;

import com.tjgwebservices.tjgxmlcms.dbo.chat.ChatDBO;
import com.tjgwebservices.tjgxmlcms.form.chat.ChatForm;
import com.tjgwebservices.tjgxmlcms.model.aiml.ArtificialIntelligence;
import com.tjgwebservices.tjgxmlcms.model.chat.Chat;
import com.tjgwebservices.tjgxmlcms.model.hr.HrEmployer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatController {

    
    private static List<Chat> chats = new ArrayList<Chat>();

    
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
        model.addAttribute("hrGroups",chats); 
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
    public String getChatForm(Model model, @PathVariable("id") Integer id) {
 
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

    @RequestMapping(value = { "/chat/checkServer" }, method = RequestMethod.POST)
    public String checkServerForm(Model model, @PathVariable("id") Integer id) {
 
        ChatForm chatForm = new ChatForm();
        model.addAttribute("chatForm", chatForm);
        chats = ChatDBO.loadChats();
        List<Chat> conversationChats = chats.stream()
                
            .filter((chat) -> Objects.equals(chat.getUserIdFrom(), id))
            .filter((chat) -> chat.getDateTime().contains("CurrentDate"))
            .collect(Collectors.toList());
        
        model.addAttribute("conversationChats",conversationChats); 
        return "/chat/checkServer";
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
        if (dateTime != null && dateTime.length() > 0){
            Chat chat = new Chat(userIdFrom, userIdTo,
                                    dateTime, priority,
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
        String dateTime = chatForm.getDateTime();
        int priority = chatForm.getPriority();
        String subject = chatForm.getSubject();
        String message = chatForm.getMessage();
        if (dateTime != null && dateTime.length() > 0){
            Chat chat = new Chat(userIdFrom, 0,
                                    dateTime, 0,
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

}
