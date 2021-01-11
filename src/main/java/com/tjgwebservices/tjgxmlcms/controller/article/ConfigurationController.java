package com.tjgwebservices.tjgxmlcms.controller.article;

import com.tjgwebservices.tjgxmlcms.dbo.article.ConfigurationDBO;
import com.tjgwebservices.tjgxmlcms.form.article.ConfigurationForm;
import com.tjgwebservices.tjgxmlcms.model.article.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ConfigurationController {

    private static List<Configuration> configurations = new ArrayList<Configuration>();

    static {
    }
 
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/articles/configurationList" }, method = RequestMethod.GET)
    public String configurationList(Model model) {
        configurations = ConfigurationDBO.loadConfigurations();
        model.addAttribute("configurations", configurations);
 
        return "/articles/configurationList";
    }
 
    @RequestMapping(value = { "/articles/addConfiguration" }, method = RequestMethod.GET)
    public String addConfigurationForm(Model model) {
 
        ConfigurationForm configurationForm = new ConfigurationForm();
        model.addAttribute("configurationForm", configurationForm);
 
        return "articles/addConfiguration";
    }

    @RequestMapping(value = { "/articles/addConfiguration" }, method = RequestMethod.POST)
    public String addConfigurationSave(Model model, //
        @ModelAttribute("configurationForm") ConfigurationForm configurationForm) {
        configurations = ConfigurationDBO.loadConfigurations();
        String messages = configurationForm.getMessages();
        Integer views = configurationForm.getViews();
        Integer shares = configurationForm.getShares();
        Integer users = configurationForm.getUsers();
        String dateTime = configurationForm.getDateTime();
 
        if (messages != null && messages.length() > 0 
                && views != null && views > -1 
                && shares != null && shares > -1 
                && users != null && users > -1
                && dateTime != null && dateTime.length() > 0) {
            Configuration newConfiguration = new Configuration(messages, views, 
                    shares, users, dateTime);
            configurations.add(newConfiguration);
            ConfigurationDBO.saveSQLConfiguration(newConfiguration);
            return "redirect:/articles/configurationList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "articles/addConfiguration";
    }

    
    @RequestMapping(value = { "/articles/editConfiguration/{id}" }, method = RequestMethod.GET)
    public String editConfigurationForm(Model model,
            @PathVariable("id") Integer id) {
        configurations = ConfigurationDBO.loadConfigurations();
 
        ConfigurationForm configurationForm = new ConfigurationForm();
        ConfigurationForm configurationEditForm = new ConfigurationForm();
        Configuration editConfiguration = configurations.stream()
            .filter((configuration) -> configuration.getId() == id)
            .collect(Collectors.toList()).get(0);
        configurationEditForm.setMessages(editConfiguration.getMessages());
        configurationEditForm.setViews(editConfiguration.getViews());
        configurationEditForm.setShares(editConfiguration.getShares());
        configurationEditForm.setUsers(editConfiguration.getUsers());
        configurationEditForm.setDateTime(editConfiguration.getDateTime());
        model.addAttribute("configurationForm", configurationForm);
        model.addAttribute("configurationEditForm", configurationEditForm);
        return "/articles/editConfiguration/{id}";
    }
 
    @RequestMapping(value = { "/articles/editConfiguration" }, method = RequestMethod.POST)
    public String editConfigurationSave(Model model, //
        @ModelAttribute("configurationForm") ConfigurationForm configurationForm) {
        configurations = ConfigurationDBO.loadConfigurations();
        Integer id = configurationForm.getId();
        String messages = configurationForm.getMessages();
        Integer views = configurationForm.getViews();
        Integer shares = configurationForm.getShares();
        Integer users = configurationForm.getUsers();
        String dateTime = configurationForm.getDateTime();
 
        if (messages != null && messages.length() > 0 
                && views != null && views > -1 
                && shares != null && shares > -1 
                && users != null && users > -1
                && dateTime != null && dateTime.length() > 0) {
            Configuration newConfiguration = new Configuration(messages, views, 
                    shares, users, dateTime);
            newConfiguration.setId(id);
            ConfigurationDBO.updateConfiguration(newConfiguration);
            return "redirect:/articles/configurationList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "articles/editConfiguration/{id}";
    }

    
}
