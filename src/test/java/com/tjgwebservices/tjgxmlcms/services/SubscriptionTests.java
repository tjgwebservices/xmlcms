package com.tjgwebservices.tjgxmlcms.services;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import com.tjgwebservices.tjgxmlcms.controller.PublisherController;
import com.tjgwebservices.tjgxmlcms.controller.SubscriptionController;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscriber;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.MockRestServiceServer.bindTo;
import org.springframework.web.client.RestTemplate;

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class SubscriptionTests {

    private RestTemplate restTemplate;
    private MockRestServiceServer server = null;
    
    @BeforeEach
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
        server = bindTo(restTemplate).build();
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
        Assertions.assertEquals(0,subscription.getAint().get());
    }
    


}
