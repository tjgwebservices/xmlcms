package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.model.SocketSubscription;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Controller;

@Controller
public class SubscriptionController implements Subscription {

    private ExecutorService executor;
    private Subscriber subscriber;
    private AtomicInteger aint = new AtomicInteger();
    private AtomicBoolean isCanceled = new AtomicBoolean();

    public SubscriptionController(){}
    
    
    public SubscriptionController(Subscriber subscriber, ExecutorService executor) {
        this.subscriber = subscriber;
        this.executor = executor;       
    }

    public SubscriptionController(Subscriber subscriber, ExecutorService executor, String message) {
        SocketSubscription socketSubscription = new SocketSubscription();
        socketSubscription.setSubscriptionPlan(message);
        subscriber.onSubscribe(socketSubscription);
        this.subscriber = subscriber;
        this.executor = executor;       
    }
    
    @Override
    public void request(long n) {
        if (isCanceled.get()){
            return;
        }
        if (n < 0){
            executor.execute(() -> subscriber.onError(new IllegalArgumentException()));
        } else {
            publishItems(n);
        }
    }

    private void publishItems(long n) {
        for (int i=0; i<n; i++) {
            executor.execute(() -> {
                int j = aint.incrementAndGet();
                
                subscriber.onNext(j);
            });
        }
    }
    public void publishItem() {
            executor.execute(() -> {
                subscriber.onNext(aint.incrementAndGet());
        });
    }
    
    @Override
    public void cancel() {
        isCanceled.set(true);
    }


    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public AtomicBoolean getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(AtomicBoolean isCanceled) {
        this.isCanceled = isCanceled;
    }

        public ExecutorService getExecutor() {
        return executor;
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }

    public AtomicInteger getAint() {
        return aint;
    }

    public void setAint(AtomicInteger aint) {
        this.aint = aint;
    }

}
