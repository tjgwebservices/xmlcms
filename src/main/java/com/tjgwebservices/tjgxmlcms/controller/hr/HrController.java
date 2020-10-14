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
        return "/hr/clients";
    }

    @RequestMapping(value = { "/hr/employers" }, method = RequestMethod.GET)
    public String employerList(Model model) {
        employers = HrEmployerDBO.loadHrEmployers();
        model.addAttribute("employers", employers);
 
        return "/hr/employers";
    }

    @RequestMapping(value = { "/hr/hrgroups" }, method = RequestMethod.GET)
    public String groupsList(Model model) {
        hrGroups = HrGroupDBO.loadHrGroups();
        model.addAttribute("hrgroups", hrGroups);
 
        return "/hr/hrgroups";
    }

    @RequestMapping(value = { "/hr/addEmployer" }, method = RequestMethod.GET)
    public String addEmployerForm(Model model) {
 
        EmployerForm employerForm = new EmployerForm();
        model.addAttribute("employerForm", employerForm);
        hrGroups = HrGroupDBO.loadHrGroups();
        model.addAttribute("hrGroups",hrGroups); 
        return "/hr/addEmployer";
    }
 
    @RequestMapping(value = { "/hr/addEmployer" }, method = RequestMethod.POST)
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
            return "redirect:/hr/employers";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/hr/addEmployer";
    }

    @RequestMapping(value = { "/hr/editEmployer/{id}" }, method = RequestMethod.GET)
    public String editdEmployerForm(Model model,@PathVariable("id") Integer id) {
 
        EmployerForm employerForm = new EmployerForm();
        model.addAttribute("employerForm", employerForm);
        hrGroups = HrGroupDBO.loadHrGroups();
        model.addAttribute("hrGroups",hrGroups); 
        
        EmployerForm employerEditForm = new EmployerForm();
        
        List<HrEmployer> editEmployers = employers.stream()
            .filter((employer) -> employer.getId() == id)
            .collect(Collectors.toList());
        if (editEmployers.size() == 1){
        HrEmployer editEmployer = editEmployers.get(0);
        employerEditForm.setEmployerName(editEmployer.getEmployerName());
        employerEditForm.setEmployerContact(editEmployer.getEmployerContact());
        employerEditForm.setEmployerContactType(editEmployer.getEmployerContactType());
        employerEditForm.setEmployerContactInfo(editEmployer.getEmployerContactInfo());
        employerEditForm.setId(id);
        model.addAttribute("employerEditForm", employerEditForm);

        return "/hr/editEmployer";
        } else {
            model.addAttribute("errorMessage","Employer id not found");
            return "/hr/employers";            
                
        }
    }
 
    @RequestMapping(value = { "hr/editEmployer" }, method = RequestMethod.POST)
    public String editEmployerSave(Model model, //
        @ModelAttribute("employerForm") EmployerForm employerForm) {
        Integer id = employerForm.getId();
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
            employer.setId(id);
            HrEmployerDBO.updateHrEmployer(employer);
            return "redirect:/hr/employers";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/hr/addEmployer";
    }


    @RequestMapping(value = { "/hr/addClient" }, method = RequestMethod.GET)
    public String addClientForm(Model model) {
 
        ClientForm clientForm = new ClientForm();
        model.addAttribute("clientForm", clientForm);
        hrGroups = HrGroupDBO.loadHrGroups();
        model.addAttribute("hrGroups",hrGroups); 
 
        return "/hr/addClient";
    }
 
    @RequestMapping(value = { "/hr/addClient" }, method = RequestMethod.POST)
    public String addClientSave(Model model, //
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
            return "redirect:/hr/clients";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/hr/addClient";
    }
    @RequestMapping(value = { "/hr/editClient/{id}" }, method = RequestMethod.GET)
    public String editClientForm(Model model,
            @PathVariable("id") Integer id) {
 
        ClientForm clientForm = new ClientForm();
        model.addAttribute("clientForm", clientForm);
        hrGroups = HrGroupDBO.loadHrGroups();
        model.addAttribute("hrGroups",hrGroups); 
        
         ClientForm clientEditForm = new ClientForm();
        
        List<HrClient> editClients = clients.stream()
            .filter((client) -> client.getId() == id)
            .collect(Collectors.toList());
        if (editClients.size()==1){
        HrClient editClient = editClients.get(0);
        clientEditForm.setClientFirstName(editClient.getClientFirstName());
        clientEditForm.setClientLastName(editClient.getClientLastName());
        clientEditForm.setClientSpecialty(editClient.getClientSpecialty());
        clientEditForm.setClientContact(editClient.getClientContact());
        clientEditForm.setId(id);
        model.addAttribute("clientEditForm", clientEditForm);

        return "/hr/editClient";
        } else {
            model.addAttribute("errorMessage","Client id not found");
            return "/hr/clients";            
            
        }
    }
 
    @RequestMapping(value = { "/hr/editClient/{id}" }, method = RequestMethod.POST)
    public String editClientSave(Model model,
            //
        @ModelAttribute("clientForm") ClientForm clientForm,
        @PathVariable("id") Integer id) {
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
            client.setId(id);
            HrClientDBO.updateHrClient(client);
            return "redirect:/hr/clients";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/hr/addClient";
    }

    @RequestMapping(value = { "/hr/addHrGroup" }, method = RequestMethod.GET)
    public String addHrGroupForm(Model model) {
 
        HrGroupForm hrGroupForm = new HrGroupForm();
        model.addAttribute("hrGroupForm", hrGroupForm);
 
        return "/hr/addHrGroup";
    }
 
    @RequestMapping(value = { "/hr/addHrGroup" }, method = RequestMethod.POST)
    public String addHrGroupSave(Model model, //
        @ModelAttribute("hrGroupForm") HrGroupForm hrGroupForm) {
        String groupName = hrGroupForm.getGroupName();
         if (groupName != null && groupName.length() > 0){
            HrGroup hrGroup = new HrGroup(groupName);
            hrGroups.add(hrGroup);
            HrGroupDBO.saveSQLHrGroup(hrGroup);
            return "redirect:hr/hrgroups";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/hr/addHrGroup";
    }

    @RequestMapping(value = { "/hr/editHrGroup/{id}" }, method = RequestMethod.GET)
    public String editHrGroupForm(Model model,
            @PathVariable("id") Integer id) {
 
        HrGroupForm hrGroupForm = new HrGroupForm();
        model.addAttribute("hrGroupForm", hrGroupForm);
        HrGroupForm hrGroupEditForm = new HrGroupForm();
                List<HrGroup> editHrGroups = hrGroups.stream()
            .filter((hrGroup) -> hrGroup.getId() == id)
            .collect(Collectors.toList());
        if (editHrGroups.size()==1) { 
        HrGroup editHrGroup = editHrGroups.get(0);
        hrGroupEditForm.setGroupName(editHrGroup.getGroupName());
        hrGroupEditForm.setId(id);
        model.addAttribute("hrGroupEditForm", hrGroupEditForm);
 
        return "hr/editHrGroup";
        } else {
            model.addAttribute("errorMessage","HR Group id not found");
            return "hr/hrgroups";            
            
        }
    }
 
    @RequestMapping(value = { "hr/editHrGroup" }, method = RequestMethod.POST)
    public String editHrGroupSave(Model model, //
        @ModelAttribute("hrGroupForm") HrGroupForm hrGroupForm) {
        Integer id = hrGroupForm.getId();
        String groupName = hrGroupForm.getGroupName();
         if (groupName != null && groupName.length() > 0){
            HrGroup hrGroup = new HrGroup(groupName);
            hrGroup.setId(id);
            HrGroupDBO.updateHrGroup(hrGroup);
            return "redirect:/hr/hrgroups";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/hr/addGroup";
    }

    
}
