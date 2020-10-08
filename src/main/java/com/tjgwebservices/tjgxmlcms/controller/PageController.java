package com.tjgwebservices.tjgxmlcms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 

@Controller
public class PageController {

   static {
    }
 
    @Value("${welcome.message}")
    private String message;
 
    @Value("${project.message}")
    private String projectMessage;
 
    @Value("${report.message}")
    private String reportMessage;
    
    @Value("${conference.name}")
    private String conferenceName;

    @RequestMapping(value = { "/conference" }, method = RequestMethod.GET)
    public String showConference(Model model) {
 
        model.addAttribute("message", message);
        model.addAttribute("conferenceName", conferenceName);
         
        return "conference";
    }

    @RequestMapping(value = { "/forum" }, method = RequestMethod.GET)
    public String showForum(Model model) {
 
        model.addAttribute("message", message);
        model.addAttribute("conferenceName", conferenceName);
         
        return "forum";
    }

    @RequestMapping(value = { "/learn" }, method = RequestMethod.GET)
    public String showLearn(Model model) {
 
        model.addAttribute("message", message);
        model.addAttribute("conferenceName", conferenceName);
         
        return "learn";
    }

    @RequestMapping(value = { "/workshop" }, method = RequestMethod.GET)
    public String showWorkshop(Model model) {
 
        model.addAttribute("message", message);
        model.addAttribute("conferenceName", conferenceName);
         
        return "workshop";
    }

    @RequestMapping(value = { "/project" }, method = RequestMethod.GET)
    public String showProject(Model model) {
 
        model.addAttribute("message", projectMessage);
        model.addAttribute("conferenceName", conferenceName);
         
        return "project";
    }

    @RequestMapping(value = { "/report" }, method = RequestMethod.GET)
    public String showReport(Model model) {
 
        model.addAttribute("message", reportMessage);
        model.addAttribute("conferenceName", conferenceName);
         
        return "report";
    }


    
}
