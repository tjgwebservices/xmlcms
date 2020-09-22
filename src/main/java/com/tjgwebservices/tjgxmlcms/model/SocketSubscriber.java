package com.tjgwebservices.tjgxmlcms.model;

import com.tjgwebservices.tjgxmlcms.controller.SubscriptionController;
import java.util.concurrent.Flow;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;
import org.springframework.stereotype.Component;

@Component
@Entity
@Indexed
@Table(name = "Subscriber")
public class SocketSubscriber implements Flow.Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String subscriber;

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
        
    }

    public Integer getId() {
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
