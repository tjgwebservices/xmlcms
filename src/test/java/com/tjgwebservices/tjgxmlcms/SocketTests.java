package com.tjgwebservices.tjgxmlcms;

import com.tjgwebservices.tjgxmlcms.controller.PublisherController;
import com.tjgwebservices.tjgxmlcms.controller.SubscriptionController;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscriber;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscription;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class SocketTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() throws Exception {
    }
    
    
    @Test
    void testSubscription() throws Exception {
        PublisherController publisherController = new PublisherController();
        SocketSubscriber subscriber = new SocketSubscriber();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        SubscriptionController subscription = new SubscriptionController(subscriber,executor);
        subscription.setSubscriber(subscriber);
        publisherController.subscribe(subscription.getSubscriber());
        Assertions.assertFalse(subscription.getExecutor().isShutdown());
        Assertions.assertEquals(subscription.getAint().get(),0);
    }
    
    @Test
    void testPublishSocket() throws Exception {
        Map<String,SocketSubscriber> postParameters = new HashMap<String,SocketSubscriber>();
        SocketSubscriber subscriber = new SocketSubscriber("publisher");
        postParameters.put("publisher",subscriber);
        String publishResponse = restTemplate.postForObject("/publish",postParameters,String.class);
        System.out.println(publishResponse);
        
    }

    @Test
    void testDisplaySocket() throws Exception {
        String displayResponse = restTemplate.getForObject("/display",String.class,"1");
        System.out.println(displayResponse);
        
    }
    
}
