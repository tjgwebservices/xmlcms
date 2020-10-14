package com.tjgwebservices.tjgxmlcms.controller.hr;

import com.tjgwebservices.tjgxmlcms.dbo.hr.HrClientDBO;
import com.tjgwebservices.tjgxmlcms.dbo.hr.HrEmployerDBO;
import com.tjgwebservices.tjgxmlcms.dbo.hr.HrGroupDBO;
import com.tjgwebservices.tjgxmlcms.form.hr.ClientForm;
import com.tjgwebservices.tjgxmlcms.form.hr.EmployerForm;
import com.tjgwebservices.tjgxmlcms.form.hr.HrGroupForm;
import com.tjgwebservices.tjgxmlcms.model.hr.HrClient;
import com.tjgwebservices.tjgxmlcms.model.hr.HrEmployer;
import com.tjgwebservices.tjgxmlcms.model.hr.HrGroup;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HrController {

    private static List<HrEmployer> employers = new ArrayList<HrEmployer>();
    private static List<HrClient> clients = new ArrayList<HrClient>();
    private static List<HrGroup> hrGroups = new ArrayList<HrGroup>();

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
    
    @RequestMapping(value = { "/hr/clients" }, method = RequestMethod.GET)
    public String clientList(Model model) {
        clients = HrClientDBO.loadHrClients();
        model.addAttribute("clients", clients);
        hrGroups = HrGroupDBO.loadHrGroups();
        model.addAttribute("hrGroups",hrGroups);  
        return "hr/clients";
    }

    @RequestMapping(value = { "/hr/employers" }, method = RequestMethod.GET)
    public String employerList(Model model) {
        employers = HrEmployerDBO.loadHrEmployers();
        model.addAttribute("employers", employers);
 
        return "hr/employers";
    }

    @RequestMapping(value = { "/hr/addEmployer" }, method = RequestMethod.GET)
    public String addEmployerForm(Model model) {
 
        EmployerForm employerForm = new EmployerForm();
        model.addAttribute("employerForm", employerForm);
        hrGroups = HrGroupDBO.loadHrGroups();
        model.addAttribute("hrGroups",hrGroups); 
        return "hr/addEmployer";
    }
 
    @RequestMapping(value = { "hr/addEmployer" }, method = RequestMethod.POST)
    public String addEmployerSave(Model model, //
        @ModelAttribute("employerForm") EmployerForm employerForm) {
        String employerName = employerForm.getEmployerName();
        String employerContact = employerForm.getEmployerContact();
        String employerContactType = employerForm.getEmployerContactType();
        String employerContactInfo = employerForm.getEmployerContactInfo();
        int hrGroupId = employerForm.getHrGroupId();
 
        if (employerName != null && employerName.length() > 0 &&
            employerContact != null && employerContact.length() > 0 &&
            employerContactType != null && employerContactType.length() > 0 &&
            employerContactInfo != null && employerContactInfo.length() > 0){
            HrEmployer employer = new HrEmployer(employerName, employerContact,
                                    employerContactType, employerContactInfo,
                                    hrGroupId);
            employers.add(employer);
            HrEmployerDBO.saveSQLHrEmployer(employer);
            return "redirect:hr/employers";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "hr/addEmployer";
    }


    @RequestMapping(value = { "/hr/addClient" }, method = RequestMethod.GET)
    public String addClientForm(Model model) {
 
        ClientForm clientForm = new ClientForm();
        model.addAttribute("clientForm", clientForm);
 
        return "hr/addClient";
    }
 
    @RequestMapping(value = { "hr/addClient" }, method = RequestMethod.POST)
    public String addEmployerSave(Model model, //
        @ModelAttribute("clientForm") ClientForm clientForm) {
        String clientFirstName = clientForm.getClientFirstName();
        String clientLastName = clientForm.getClientLastName();
        String clientSpecialty = clientForm.getClientSpecialty();
        String clientContact = clientForm.getClientContact();
        int hrGroupId = clientForm.getHrGroupId();
 
        if (clientFirstName != null && clientFirstName.length() > 0 &&
            clientLastName != null && clientLastName.length() > 0 &&
            clientSpecialty != null && clientSpecialty.length() > 0 &&
            clientContact != null && clientContact.length() > 0){
            HrClient client = new HrClient(clientFirstName, clientLastName,
                                    clientSpecialty, clientContact,
                                    hrGroupId);
            clients.add(client);
            HrClientDBO.saveSQLHrClient(client);
            return "redirect:hr/clients";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "hr/addClient";
    }

    @RequestMapping(value = { "/hr/addHrGroup" }, method = RequestMethod.GET)
    public String addHrGroupForm(Model model) {
 
        HrGroupForm hrGroupForm = new HrGroupForm();
        model.addAttribute("hrGroupForm", hrGroupForm);
 
        return "hr/addHrGroup";
    }
 
    @RequestMapping(value = { "/hr/addHrGroup" }, method = RequestMethod.POST)
    public String addHrGroupSave(Model model, //
        @ModelAttribute("hrGroupForm") HrGroupForm hrGroupForm) {
        String groupName = hrGroupForm.getGroupName();
         if (groupName != null && groupName.length() > 0){
            HrGroup hrGroup = new HrGroup(groupName);
            hrGroups.add(hrGroup);
            HrGroupDBO.saveSQLHrGroup(hrGroup);
            return "redirect:hr/clients";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "hr/addGroup";
    }
    
}
