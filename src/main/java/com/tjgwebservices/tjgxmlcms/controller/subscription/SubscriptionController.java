package com.tjgwebservices.tjgxmlcms.controller.subscription;

import com.tjgwebservices.tjgxmlcms.dbo.SubscriptionDBO;
import com.tjgwebservices.tjgxmlcms.dbo.account.UserDBO;
import com.tjgwebservices.tjgxmlcms.dbo.account.UserDetailsDBO;
import com.tjgwebservices.tjgxmlcms.form.SubscriptionForm;
import com.tjgwebservices.tjgxmlcms.form.account.UserForm;
import com.tjgwebservices.tjgxmlcms.model.account.User;
import com.tjgwebservices.tjgxmlcms.model.account.UserDetails;
import com.tjgwebservices.tjgxmlcms.model.socket.SocketSubscription;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SubscriptionController implements Subscription {

    private ExecutorService executor;
    private Subscriber subscriber;
    private AtomicInteger aint = new AtomicInteger();
    private AtomicBoolean isCanceled = new AtomicBoolean();
    private static List<SocketSubscription> subscriptions = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static List<UserDetails> userDetailsList = new ArrayList<>();


    static {
    }
    
    @Value("${title.message}")
    private String titleMessage;

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

    @RequestMapping(value = { "/subscriptions/subscribers" }, method = RequestMethod.GET)
    public String userList(Model model) {
        users = UserDBO.loadUsers();
        model.addAttribute("users", users);
 
        return "/subscriptions/subscribers";
    }

    @RequestMapping(value = { "/subscriptions/subscribe" }, method = RequestMethod.GET)
    public String addSubscriberForm(Model model) {
 
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
 
        return "subscriptions/subscribe";
    }

    @RequestMapping(value = { "/subscriptions/subscribe" }, method = RequestMethod.POST)
    public String addSubscriberSave(Model model, //
        @ModelAttribute("userForm") UserForm userForm) {
        users = UserDBO.loadUsers();
        String username = userForm.getUsername();
        String firstName = userForm.getFirstName();
        String lastName = userForm.getLastName();
        String email = userForm.getEmail();
        String phoneNumber = userForm.getPhoneNumber();
        String address1 = userForm.getAddress1();
        String address2 = userForm.getAddress2();
        String city = userForm.getCity();
        String statecode = userForm.getStatecode();
        String zipcode = userForm.getZipcode();
        String businessName = userForm.getBusinessName();
        String websiteName = userForm.getWebsiteName();

        String content1 = userForm.getContent1();
        String content2 = userForm.getContent2();
        String content3 = userForm.getContent3();
        
        if (username != null && username.length() > 0 
                && firstName != null && firstName.length() > 0 
                && lastName != null && lastName.length() > 0 
                && email != null && email.length() > 0){
            
            List<User> currentUsers = users.stream()
                .filter((user) -> Objects.equals(user.getUsername(), username))
                .collect(Collectors.toList());
            if (currentUsers.size()==1){
                String error = "Username already taken!";
                model.addAttribute("errorMessage", error);
                return "subscriptions/subscribe";        
            } else {
                User newUser = new User(username, firstName, 
                        lastName, email, phoneNumber, address1,
                    address2, city, statecode, zipcode,businessName,
                    websiteName);
                users.add(newUser);            
                UserDBO.saveSQLUser(newUser);
                users = UserDBO.loadUsers();
                List<User> savedUsers = users.stream()
                    .filter((user) -> Objects.equals(user.getUsername(), username))
                    .collect(Collectors.toList());            
                UserDetails newUserDetails = new UserDetails(content1, content2,
                content3, savedUsers.get(0).getId());
                userDetailsList.add(newUserDetails);
                UserDetailsDBO.saveSQLUserDetails(newUserDetails);

                return "redirect:/subscriptions/subscribers";
            }
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "subscriptions/subscribe";
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

    @RequestMapping(value = { "/subscriptions/editSubscriber/{id}" }, method = RequestMethod.GET)
    public String editSubscriptionForm(Model model,
            @PathVariable("id") Integer id) {
        users = UserDBO.loadUsers();
 
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        titleMessage = "Add Subscriber";
        model.addAttribute("titleMessage", titleMessage); 
        
        UserForm userEditForm = new UserForm();
        List<User> editUsers = users.stream()
            .filter((user) -> Objects.equals(user.getId(), id))
            .collect(Collectors.toList());
        if (editUsers.size()==1){
            User editUser  = editUsers.get(0);
            List<UserDetails> editUserDetails = userDetailsList.stream()
                .filter((userDetails) -> Objects.equals(
                        userDetails.getAccountUserId(), id))
                .collect(Collectors.toList());
            userEditForm.setUsername(editUser.getUsername());
            userEditForm.setFirstName(editUser.getFirstName());
            userEditForm.setLastName(editUser.getLastName());
            userEditForm.setEmail(editUser.getEmail());
            userEditForm.setPhoneNumber(editUser.getPhoneNumber());
            userEditForm.setAddress1(editUser.getAddress1());
            userEditForm.setAddress2(editUser.getAddress2());
            userEditForm.setCity(editUser.getCity());
            userEditForm.setStatecode(editUser.getStatecode());
            userEditForm.setZipcode(editUser.getZipcode());
            userEditForm.setBusinessName(editUser.getBusinessName());
            userEditForm.setWebsiteName(editUser.getWebsiteName());
            userEditForm.setId(id);
            if (editUserDetails.size() == 1) {
                userEditForm.setContent1(editUserDetails.get(0).getContent1());
                userEditForm.setContent2(editUserDetails.get(0).getContent2());
                userEditForm.setContent3(editUserDetails.get(0).getContent3());
            }else{
                userEditForm.setContent1("");
                userEditForm.setContent2("");
                userEditForm.setContent3("");
            }
            model.addAttribute("userEditForm", userEditForm);
            return "/subscriptions/editSubscription";            
        } else {
            model.addAttribute("errorMessage","User id not found");
            return "/subscriptions/subscribers";
        }
    }

    @RequestMapping(value = { "/subscriptions/editSubscriber/{id}" }, method = RequestMethod.POST)
    public String editSubscriberSave(Model model, @PathVariable("id") Integer id,//
        @ModelAttribute("userForm") UserForm userForm) {
        String username = userForm.getUsername();
        String firstName = userForm.getFirstName();
        String lastName = userForm.getLastName();
        String email = userForm.getEmail();
        String phoneNumber = userForm.getPhoneNumber();
        String address1 = userForm.getAddress1();
        String address2 = userForm.getAddress2();
        String city = userForm.getCity();
        String statecode = userForm.getStatecode();
        String zipcode = userForm.getZipcode();
        String businessName = userForm.getBusinessName();
        String websiteName = userForm.getWebsiteName();

        String content1 = userForm.getContent1();
        String content2 = userForm.getContent2();
        String content3 = userForm.getContent3();
        
        if (username != null && username.length() > 0 
                && firstName != null && firstName.length() > 0 
                && lastName != null && lastName.length() > 0 
                && email != null && email.length() > 0){
            
        
            User user = new User(username, firstName, 
                    lastName, email, phoneNumber, address1,
                address2, city, statecode, zipcode,businessName,
                websiteName);
            user.setId(id);
            UserDBO.updateUser(user);
            
            UserDetails userDetails = new UserDetails(content1, content2,
            content3, id);
            userDetails.setId(id);
            UserDetailsDBO.updateUserDetails(userDetails);
            return "redirect:/subscription/subscribers";
        }
        String error = "All fields are required!";
        model.addAttribute("errorMessage", error);
        return "/subscriptions/editSubscription/{id}";
    }


    @RequestMapping(value = { "/subscriptions/unsubscribe" }, method = RequestMethod.GET)
    public String unsubscribeForm(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
 
        return "subscriptions/unsubscribe";

    }
    
   @RequestMapping(value = { "/subscriptions/unsubscribe" }, method = RequestMethod.POST)
    public String unsubscribeSave(Model model, //
        @ModelAttribute("userForm") UserForm userForm) {
        String username = userForm.getUsername();
        if (username != null && username.length() > 0){
            
            List<User> currentUsers = users.stream()
                .filter((user) -> Objects.equals(user.getUsername(), username))
                .collect(Collectors.toList());
            if (currentUsers.size()==1){
                users = UserDBO.loadUsers();
                users.remove(currentUsers.get(0));
                UserDBO.deleteUser(currentUsers.get(0));
                return "subscriptions/subscribers";        
            } else {
                String error = "Username not found!";
                model.addAttribute("errorMessage", error);                
                return "redirect:/subscriptions/subscribe";
            }
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "subscriptions/subscribe";
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
