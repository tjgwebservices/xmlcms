package com.tjgwebservices.tjgxmlcms.model.socket;

import com.tjgwebservices.tjgxmlcms.controller.subscription.SubscriptionController;
import com.tjgwebservices.tjgxmlcms.services.filters.CustomFilter;
import java.io.Serializable;
import java.util.concurrent.Flow;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Indexed
@Table(name = "Subscriber")
public class SocketSubscriber implements Flow.Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Serializable id;
    private String subscriber;
    private static Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);


    public SocketSubscriber(){}
    
    public SocketSubscriber(String subscriber){
        this.subscriber = subscriber;
    }
    
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        synchronized (subscription){
            subscription.request(1);
        }
    }

    @Override
    public void onNext(Object item) {
        if (item instanceof SubscriptionController){
            SubscriptionController subscription = (SubscriptionController) item;
            synchronized (subscription){
                subscription.publishItem();
            }
            return;
        }
        if (item instanceof SocketSubscription) {
            SocketSubscription subscription = (SocketSubscription) item;
            synchronized (subscription){
                System.out.println(subscription.getId());
            }
        }
    }


    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        LOGGER.info("Socket Subscriber Complete");
    }

    public Serializable getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    
}
