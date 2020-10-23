package com.tjgwebservices.tjgxmlcms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 

@Controller
public class ConferenceController {

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

    @RequestMapping(value = { "/conferences/conference" }, method = RequestMethod.GET)
    public String showConference(Model model) {
 
        model.addAttribute("message", message);
        model.addAttribute("conferenceName", conferenceName);
         
        return "conferences/conference";
    }

    @RequestMapping(value = { "/conferences/forum" }, method = RequestMethod.GET)
    public String showForum(Model model) {
 
        model.addAttribute("message", message);
        model.addAttribute("conferenceName", conferenceName);
         
        return "conferences/forum";
    }

    @RequestMapping(value = { "/conferences/learn" }, method = RequestMethod.GET)
    public String showLearn(Model model) {
 
        model.addAttribute("message", message);
        model.addAttribute("conferenceName", conferenceName);
         
        return "conferences/learn";
    }

    @RequestMapping(value = { "/conferences/workshop" }, method = RequestMethod.GET)
    public String showWorkshop(Model model) {
 
        model.addAttribute("message", message);
        model.addAttribute("conferenceName", conferenceName);
         
        return "conferences/workshop";
    }

    @RequestMapping(value = { "/conferences/project" }, method = RequestMethod.GET)
    public String showProject(Model model) {
 
        model.addAttribute("message", projectMessage);
        model.addAttribute("conferenceName", conferenceName);
         
        return "conferences/project";
    }

    @RequestMapping(value = { "/conferences/report" }, method = RequestMethod.GET)
    public String showReport(Model model) {
 
        model.addAttribute("message", reportMessage);
        model.addAttribute("conferenceName", conferenceName);
         
        return "conferences/report";
    }

    @RequestMapping(value = { "/conferences/room" }, method = RequestMethod.GET)
    public String showConferenceRoom(Model model) {
 
        model.addAttribute("message", reportMessage);
        model.addAttribute("conferenceName", conferenceName);
         
        return "conferences/room";
    }

    
}
