package com.tjgwebservices.tjgxmlcms.controller.account;

import com.tjgwebservices.tjgxmlcms.dbo.account.UserDBO;
import com.tjgwebservices.tjgxmlcms.dbo.account.UserDetailsDBO;
import com.tjgwebservices.tjgxmlcms.form.account.UserForm;
import com.tjgwebservices.tjgxmlcms.model.account.User;
import com.tjgwebservices.tjgxmlcms.model.account.UserDetails;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private static List<User> users = new ArrayList<>();
    private static List<UserDetails> userDetailsList = new ArrayList<>();

    static {
    }
 
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;

    @Value("${title.message}")
    private String titleMessage;

    @RequestMapping(value = { "/authenticated/userList" }, method = RequestMethod.GET)
    public String userList(Model model) {
        users = UserDBO.loadUsers();
        model.addAttribute("users", users);
 
        return "/authenticated/userList";
    }

    @RequestMapping(value = { "/authenticated/addUser" }, method = RequestMethod.GET)
    public String addUserForm(Model model) {
 
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
 
        return "authenticated/addUser";
    }

    @RequestMapping(value = { "/authenticated/addUser" }, method = RequestMethod.POST)
    public String addUserSave(Model model, //
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
            
        
            User newUser = new User(username, firstName, 
                    lastName, email, phoneNumber, address1,
                address2, city, statecode, zipcode,businessName,
                websiteName);
            users.add(newUser);
            UserDBO.saveSQLUser(newUser);
            
            UserDetails newUserDetails = new UserDetails(content1, content2,
            content3, newUser.getId());
            userDetailsList.add(newUserDetails);
            UserDetailsDBO.saveSQLUserDetails(newUserDetails);
            
            return "redirect:/authenticated/userList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "authenticated/addUser";
    }

    @RequestMapping(value = { "/authenticated/editUser/{id}" }, method = RequestMethod.GET)
    public String editUserForm(Model model,
            @PathVariable("id") Integer id) {
        users = UserDBO.loadUsers();
 
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        titleMessage = "Add User";
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
            return "/authenticated/editUser";            
        } else {
            model.addAttribute("errorMessage","User id not found");
            return "/authenticated/userList";
        }
    }

    @RequestMapping(value = { "/authenticated/editUser" }, method = RequestMethod.POST)
    public String editUserSave(Model model,//
        @ModelAttribute("userForm") UserForm userForm) {
        Integer id = userForm.getId();
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
            return "redirect:/authenticated/userList";
        }
        String error = "All fields are required!";
        model.addAttribute("errorMessage", error);
        return "/authenticated/editUser/{id}";
    }
    
}
