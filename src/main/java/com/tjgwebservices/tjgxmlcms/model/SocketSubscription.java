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
    private String subscription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    @Override
    public void request(long n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
