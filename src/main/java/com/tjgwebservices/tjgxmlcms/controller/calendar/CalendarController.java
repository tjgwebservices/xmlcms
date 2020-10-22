package com.tjgwebservices.tjgxmlcms.controller.calendar;

import com.tjgwebservices.tjgxmlcms.dbo.calendar.EventAdvertisementDBO;
import com.tjgwebservices.tjgxmlcms.dbo.calendar.EventDBO;
import com.tjgwebservices.tjgxmlcms.form.calendar.EventAdvertisementForm;
import com.tjgwebservices.tjgxmlcms.form.calendar.EventForm;
import com.tjgwebservices.tjgxmlcms.model.calendar.Event;
import com.tjgwebservices.tjgxmlcms.model.calendar.EventAdvertisement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CalendarController {

    private static List<Event> events = new ArrayList<Event>();
    private static List<EventAdvertisement> eventAdvertisements = new ArrayList<EventAdvertisement>();

     @Autowired
    ServletContext context;

    static {
    }    

    @Value("${title.message}")
    private String titleMessage;


    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/events/eventList" }, method = RequestMethod.GET)
    public String eventList(Model model) {
        events = EventDBO.loadEvents();
        model.addAttribute("events", events);
        titleMessage = "Event List";
        model.addAttribute("titleMessage", titleMessage);  
        return "/events/eventList";
    }

    @RequestMapping(value = { "/events/eventAdvertisementList" }, method = RequestMethod.GET)
    public String eventAdministratorList(Model model) {
        eventAdvertisements = EventAdvertisementDBO.loadEventAdvertisements();
        model.addAttribute("eventAdvertisements", eventAdvertisements);
        titleMessage = "Event Advertisement List";
        model.addAttribute("titleMessage", titleMessage);  
        return "/events/eventAdvertisementList";
    }

    @RequestMapping(value = { "/events/addEvent" }, method = RequestMethod.GET)
    public String addEventForm(Model model) {
 
        EventForm eventForm = new EventForm();
        model.addAttribute("eventForm", eventForm);
        events = EventDBO.loadEvents();
        model.addAttribute("events", events);
        titleMessage = "Add Event";
        model.addAttribute("titleMessage", titleMessage); 
        return "/events/addEvent";
    }

    @RequestMapping(value = { "/events/addEvent" }, method = RequestMethod.POST)
    public String addEventSave(Model model, //
        @ModelAttribute("eventForm") EventForm eventForm) {
        String title = eventForm.getTitle();
        String startDate = eventForm.getStartDate();
        String endDate = eventForm.getEndDate();
        String location = eventForm.getLocation();
        String description = eventForm.getDescription();
        events = EventDBO.loadEvents();
 
        if (title != null && title.length() > 0 &&
            startDate != null && startDate.length() > 0 &&
            endDate != null && endDate.length() > 0 &&
            location != null && location.length() > 0 &&
            description != null && description.length() > 0 ){
            Event event = new Event(title, startDate,
            endDate, location, description);
            events.add(event);
            EventDBO.saveSQLEvent(event);
            return "redirect:/events/eventList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/events/addEvent";
    }

    @RequestMapping(value = { "/events/editEvent/{id}" }, method = RequestMethod.GET)
    public String editEventForm(Model model,
            @PathVariable("id") Integer id) {
 
        EventForm eventForm = new EventForm();
        model.addAttribute("eventForm", eventForm);
        titleMessage = "Add Event";
        model.addAttribute("titleMessage", titleMessage); 
        
        EventForm eventEditForm = new EventForm();
        List<Event> editEvents = events.stream()
            .filter((topic) -> Objects.equals(topic.getId(), id))
            .collect(Collectors.toList());
        if (editEvents.size()==1){
            Event editEvent = editEvents.get(0);
            eventEditForm.setTitle(editEvent.getTitle());
            eventEditForm.setStartDate(editEvent.getStartDate());
            eventEditForm.setEndDate(editEvent.getEndDate());
            eventEditForm.setId(id);
            model.addAttribute("eventEditForm", eventEditForm);
            return "/events/editEvent";            
        } else {
            model.addAttribute("errorMessage","Topic id not found");
            return "/events/eventList";
        }
    }

    @RequestMapping(value = { "/events/editEvent" }, method = RequestMethod.POST)
    public String editTopicSave(Model model, //
        @ModelAttribute("eventForm") EventForm eventForm) {
        Integer id = eventForm.getId();
        String title = eventForm.getTitle();
        String startDate = eventForm.getStartDate();
        String endDate = eventForm.getEndDate();        
        String location = eventForm.getLocation();        
        String description = eventForm.getDescription();   
        if (title != null && title.length() > 0 &&
            startDate != null && startDate.length() > 0 &&
            endDate != null && endDate.length() > 0 && 
            location != null && location.length() > 0 && 
            description != null && description.length() > 0 ){
            Event event = new Event(title, startDate,
            endDate, location, description);
            event.setId(id);
            //topics.add(topic);
            EventDBO.updateEvent(event);
            return "redirect:/events/eventList";
        }
        String error = "All fields are required!";
        model.addAttribute("errorMessage", error);
        return "/research/editTopic";
    }


    @RequestMapping(value = { "/events/addEventAdvertisement" }, method = RequestMethod.GET)
    public String addEventAdvertisementForm(Model model) {
 
        EventAdvertisementForm eventAdvertisementForm = new EventAdvertisementForm();
        model.addAttribute("eventAdvertisementForm", eventAdvertisementForm);
        eventAdvertisements = EventAdvertisementDBO.loadEventAdvertisements();
        model.addAttribute("eventAdvertisements", eventAdvertisements);
        titleMessage = "Add Event Advertisement";
        model.addAttribute("titleMessage", titleMessage); 
        return "/events/addEventAdvertisement";
    }

    @RequestMapping(value = { "/events/addEventAdvertisement" }, method = RequestMethod.POST)
    public String addEventAdvertisementSave(Model model, //
        @ModelAttribute("eventAdvertisementForm") EventAdvertisementForm eventAdvertisementForm) {
        String title = eventAdvertisementForm.getTitle();
        String subTitle = eventAdvertisementForm.getSubTitle();
        String adImagePath = eventAdvertisementForm.getAdImagePath();
        String contactInfo = eventAdvertisementForm.getContactInfo();
        int eventId = eventAdvertisementForm.getEventId();
        eventAdvertisements = EventAdvertisementDBO.loadEventAdvertisements();
 
        if (title != null && title.length() > 0 &&
            subTitle != null && subTitle.length() > 0 &&
            adImagePath != null && adImagePath.length() > 0 &&
            contactInfo != null && contactInfo.length() > 0){
            EventAdvertisement eventAdvertisement = new EventAdvertisement(
            title, subTitle, adImagePath, contactInfo, eventId);
            eventAdvertisements.add(eventAdvertisement);
            EventAdvertisementDBO.saveSQLEventAdvertisement(eventAdvertisement);
            return "redirect:/events/eventAdvertisementList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/events/addEventAdvertisement";
    }

    @RequestMapping(value = { "/events/editEventAdvertisement/{id}" }, method = RequestMethod.GET)
    public String editEventAdvertisementForm(Model model,
            @PathVariable("id") Integer id) {
 
        EventAdvertisementForm eventAdvertisementForm = new EventAdvertisementForm();
        model.addAttribute("eventAdvertisementForm", eventAdvertisementForm);
        events = EventDBO.loadEvents();
        model.addAttribute("events", events);
        titleMessage = "Add Topic";
        model.addAttribute("titleMessage", titleMessage); 
        
        EventAdvertisementForm eventAdvertisementEditForm = new EventAdvertisementForm();
        List<EventAdvertisement> editEventAdvertisements = eventAdvertisements.stream()
            .filter((eventAdvertisement) -> Objects.equals(eventAdvertisement.getId(), id))
            .collect(Collectors.toList());
        if (editEventAdvertisements.size()==1){
            EventAdvertisement editEventAdvertisement = editEventAdvertisements.get(0);
            eventAdvertisementEditForm.setTitle(editEventAdvertisement.getTitle());
            eventAdvertisementEditForm.setSubTitle(editEventAdvertisement.getSubTitle());
            eventAdvertisementEditForm.setAdImagePath(editEventAdvertisement.getAdImagePath());
            eventAdvertisementEditForm.setContactInfo(editEventAdvertisement.getContactInfo());
            eventAdvertisementEditForm.setEventId(editEventAdvertisement.getEventId());
            eventAdvertisementEditForm.setId(id);
            model.addAttribute("eventAdvertisementEditForm", eventAdvertisementEditForm);
            return "/events/editEventAdvertisement";            
        } else {
            model.addAttribute("errorMessage","Event id not found");
            return "/events/eventAdvertisementList";
        }
    }

    @RequestMapping(value = { "/events/editEventAdvertisement" }, method = RequestMethod.POST)
    public String editEventAdvertisementSave(Model model, //
        @ModelAttribute("eventAdvertisementForm") EventAdvertisementForm eventAdvertisementForm) {
        Integer id = eventAdvertisementForm.getId();
        String title = eventAdvertisementForm.getTitle();
        String subTitle = eventAdvertisementForm.getSubTitle();
        String adImagePath = eventAdvertisementForm.getAdImagePath();        
        String contactInfo = eventAdvertisementForm.getContactInfo();        
        int eventId = eventAdvertisementForm.getEventId();
 
        if (title != null && title.length() > 0 &&
            subTitle != null && subTitle.length() > 0 &&
            adImagePath != null && adImagePath.length() > 0 &&
            contactInfo != null && contactInfo.length() > 0 ){
            EventAdvertisement eventAdvertisement = new EventAdvertisement(
            title, subTitle, adImagePath, contactInfo, eventId);
            eventAdvertisement.setId(id);
            EventAdvertisementDBO.updateEventAdvertisement(eventAdvertisement);
            return "redirect:/events/eventAdvertisementList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/research/editTopic";
    }
    
}
