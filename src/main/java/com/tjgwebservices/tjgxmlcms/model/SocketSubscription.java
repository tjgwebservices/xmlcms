package com.tjgwebservices.tjgxmlcms.model;

import java.util.concurrent.Flow.Subscription;
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
@Table(name = "Subscription")
public class SocketSubscription implements Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String subscriptionPlan;
    private String publisher;
    private String topic;
    
    public SocketSubscription() {}
    
    public SocketSubscription(String subscriptionPlan, String publisher,
            String topic){
        this.subscriptionPlan = subscriptionPlan;
        this.publisher = publisher;
        this.topic = topic;
    }


    @Override
    public void request(long n) {
        
    }

    @Override
    public void cancel() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(String subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
}
