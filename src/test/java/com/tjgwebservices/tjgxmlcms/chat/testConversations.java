package com.tjgwebservices.tjgxmlcms.chat;

import com.tjgwebservices.tjgxmlcms.dbo.chat.ChatDBO;
import com.tjgwebservices.tjgxmlcms.model.chat.Chat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testConversations {

    private static List<Chat> chats = new ArrayList<Chat>();
    private static final String[] commands = {"test1","test2","test3","test4","test5"};

    @BeforeEach
    public void setUp() throws Exception {
        chats = ChatDBO.loadChats();
        Integer testConversationId = 1111;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();  
        
        List<Chat> conversationChats = chats.stream()
            .filter((chat) -> ((Objects.equals(chat.getUserIdFrom(), testConversationId)) ||
                    Objects.equals(chat.getUserIdTo(), testConversationId)))
            .filter((chat) -> chat.getDateTime().contains(formatter.format(date)))
            .collect(Collectors.toList());

        if (conversationChats.size()<1){
            conversationChats.forEach(chat -> {
                ChatDBO.deleteSQLChat(chat);
            });
            
            Chat chat1 = new Chat(1111, 0,
                formatter.format(date), 0,
                "Customer Service Request", 
                    "Customer Service Request");
            chats.add(chat1);
            ChatDBO.saveSQLChat(chat1);
            Chat chat2 = new Chat(0, 1111,
                formatter.format(date), 0,
                "Test Request", 
                    "Test Request");
            chats.add(chat2);
            ChatDBO.saveSQLChat(chat2);
            Chat chat3 = new Chat(1111,0,
                formatter.format(date), 0,
                "Test Response", 
                    "Test Response");
            chats.add(chat3);
            ChatDBO.saveSQLChat(chat3);

        }
    }
    
    
    @Test
    public void testConversationList() throws Exception {
        chats = ChatDBO.loadChats();
        Integer conversationid = 8189;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date();  

        List<Chat> conversationChats = chats.stream()

            .filter((chat) -> ((Objects.equals(chat.getUserIdFrom(), conversationid)) ||
                    Objects.equals(chat.getUserIdTo(), conversationid)))
            .filter((chat) -> chat.getDateTime().contains(formatter.format(date)))
            .collect(Collectors.toList());
        
        Assertions.assertEquals(3,conversationChats.size());
    }   

    @Test
    public void testTotalConversationList() throws Exception {
        chats = ChatDBO.loadChats();
        Integer conversationid = 8189;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date();  

        List<Chat> conversationChats = chats.stream()

            .filter((chat) -> ((Objects.equals(chat.getUserIdFrom(), conversationid)) ||
                    Objects.equals(chat.getUserIdTo(), conversationid)))
            .collect(Collectors.toList());
        
        Assertions.assertEquals(3,conversationChats.size());
    }   


    @Test
    public void testAllGuestsTodayList() throws Exception {
        chats = ChatDBO.loadChats();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date();  

        List<Chat> conversationChats = chats.stream()

            .filter((chat) -> chat.getDateTime().contains(formatter.format(date)))
            .collect(Collectors.toList());
        
        Assertions.assertEquals(40,conversationChats.size());
    }   

}
