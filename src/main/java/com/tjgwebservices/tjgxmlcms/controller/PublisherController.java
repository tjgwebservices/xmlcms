package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.WebSocketConfig;
import com.tjgwebservices.tjgxmlcms.form.SocketDisplay;
import com.tjgwebservices.tjgxmlcms.model.SocketSubscriber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Future;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PublisherController implements Publisher{
    final ExecutorService executor = Executors.newFixedThreadPool(4);
    private List<SubscriptionController> subscriptions = Collections.synchronizedList(new ArrayList<SubscriptionController>());
    private final CompletableFuture<Void> terminated = new CompletableFuture<>();
    
    static {
        
    }
    
    @Override
    public void subscribe(Flow.Subscriber subscriber) {
        SubscriptionController subscription = new SubscriptionController(subscriber,executor);
        subscriptions.add(subscription);
        subscriber.onSubscribe(subscription);
        
    }

    @RequestMapping(value = { "/publish" }, method = RequestMethod.GET)
    public String displaySocket(Model model) {
        SocketDisplay socketDisplay = new SocketDisplay("check publish");
        SocketSubscriber subscriber = new SocketSubscriber("check publish");
        subscribe(subscriber);
        model.addAttribute("publish", socketDisplay);
        return "display";
    }

    @RequestMapping(value = { "/publish" }, method = RequestMethod.POST)
    public String pubilishSocket(Model model, 
            @ModelAttribute("socketDisplay") SocketDisplay socketDisplay) {
        if (socketDisplay == null){
            socketDisplay = new SocketDisplay("new publish");
            SocketSubscriber subscriber = new SocketSubscriber("new publish");
            subscribe(subscriber);
        }
        model.addAttribute("publish", socketDisplay.getMessage());
        return "display";
    }

    @RequestMapping(value = { "/publishMessage" }, method = RequestMethod.POST)
    public String publishMessage(Model model,
        @ModelAttribute("socketDisplay") SocketDisplay socketDisplay) {
            SocketSubscriber socketSubscriber = new SocketSubscriber(socketDisplay.getMessage());
            WebSocketConfig wsconfig = new WebSocketConfig();
            synchronized(subscriptions){
                SubscriptionController sc = new SubscriptionController(socketSubscriber, wsconfig.executorService(), socketDisplay.getMessage());
                subscriptions.add(sc);
            }
            model.addAttribute("socketDisplay", socketDisplay);
            return "addMessage";
    }

    @RequestMapping(value = { "/addMessage" }, method = RequestMethod.POST)
    public String addArticleSave(Model model, //
        @ModelAttribute("socketDisplay") SocketDisplay socketDisplay) {
        String message = socketDisplay.getMessage();
 
        if (message != null && message.length() > 0) {
            
            return "redirect:/display";
        }
        String error = "Message is required!";
        model.addAttribute("errorMessage", error);
        return "publish";
    }

    @RequestMapping(value = { "/display" }, method = RequestMethod.GET)
    public String displayPublished(Model model) {
        List<String> subscriptionList = new ArrayList<String>();
        
        for (SubscriptionController subscription : subscriptions){
            String sInteger = subscription.getAint().toString();
            String sShutdown = String.valueOf(subscription.getExecutor().isShutdown());            
            subscriptionList.add("Subscription: "+sInteger+" Shutdown: "+sShutdown);
        }
        SocketDisplay socketDisplay = new SocketDisplay();
        model.addAttribute("socketDisplay", socketDisplay);
        model.addAttribute("subscriptionList", subscriptionList);
        return "display";
    }

    public void waitUntilTerminated() throws InterruptedException {
        try {
            terminated.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void cancelSubscription(SubscriptionController subscription){
        synchronized (subscriptions) {
            if (subscriptions.size() == 0) {
                subscription.getExecutor().shutdown();
                subscription.cancel();
                subscriptions.remove(subscription);
                Future<?> submit = newSingleThreadExecutor().submit(new Runnable() {
                    @Override
                    public void run() {
                        terminated.complete(null);
                    }
                });
            }
        }
    }
}
