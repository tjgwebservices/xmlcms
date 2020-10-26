package com.tjgwebservices.tjgxmlcms.controller.subscription;

import com.tjgwebservices.tjgxmlcms.dbo.SubscriptionDBO;
import com.tjgwebservices.tjgxmlcms.form.SubscriptionForm;
import com.tjgwebservices.tjgxmlcms.model.socket.SocketSubscription;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SubscriptionController implements Subscription {

    private ExecutorService executor;
    private Subscriber subscriber;
    private AtomicInteger aint = new AtomicInteger();
    private AtomicBoolean isCanceled = new AtomicBoolean();
    private static List<SocketSubscription> subscriptions = new ArrayList<>();

    static {
    }

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

    @RequestMapping(value = { "/subscriptions/addSubscription" }, method = RequestMethod.GET)
    public String addSubscriptionForm(Model model) {
 
        SubscriptionForm subscriptionForm = new SubscriptionForm();
        model.addAttribute("subscriptionForm", subscriptionForm);
        return "subscriptions/addSubscription";
    }
    
   @RequestMapping(value = { "/subscriptions/addSubscription" }, method = RequestMethod.POST)
    public String addSubscriptionSave(Model model, //
        @ModelAttribute("subscriptionForm") SubscriptionForm subscriptionForm) {
        String publisher = subscriptionForm.getPublisher();
        String subscriptionPlan = subscriptionForm.getSubscriptionPlan();
        String topic = subscriptionForm.getTopic();
 
        if (publisher != null && publisher.length() > 0 
                && subscriptionPlan != null && subscriptionPlan.length() > 0 
                && topic != null && topic.length() > 0) {
            SocketSubscription subscription = new SocketSubscription(
                    subscriptionPlan, publisher, topic);
            subscriptions.add(subscription);
            SubscriptionDBO.saveSubscription(subscription);
            return "redirect:/subscriptions/subscriptionList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "subscriptions/addSubscription";
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
