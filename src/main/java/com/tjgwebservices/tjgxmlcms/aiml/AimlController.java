package com.tjgwebservices.tjgxmlcms.aiml;

import com.tjgwebservices.tjgxmlcms.dbo.aiml.ArtificialIntelligenceDBO;
import com.tjgwebservices.tjgxmlcms.dbo.aiml.MachineLearningDBO;
import com.tjgwebservices.tjgxmlcms.dbo.calendar.EventDBO;
import com.tjgwebservices.tjgxmlcms.form.aiml.ArtificialIntelligenceForm;
import com.tjgwebservices.tjgxmlcms.form.aiml.MachineLearningForm;
import com.tjgwebservices.tjgxmlcms.model.aiml.ArtificialIntelligence;
import com.tjgwebservices.tjgxmlcms.model.aiml.MachineLearning;
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
public class AimlController {

    private static List<ArtificialIntelligence> artificialIntelligences = new ArrayList<ArtificialIntelligence>();
    private static List<MachineLearning> machineLearnings = new ArrayList<MachineLearning>();

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


    @RequestMapping(value = { "/aiml/aiAlgorithms" }, method = RequestMethod.GET)
    public String aiList(Model model) {
        artificialIntelligences = ArtificialIntelligenceDBO.loadArtificialIntelligences();
        model.addAttribute("artificialIntelligences", artificialIntelligences);
        titleMessage = "Event List";
        model.addAttribute("titleMessage", titleMessage);  
        return "/aiml/aiAlgorithms";
    }

    @RequestMapping(value = { "/aiml/mlAlgorithms" }, method = RequestMethod.GET)
    public String mlList(Model model) {
        machineLearnings = MachineLearningDBO.loadMachineLearnings();
        model.addAttribute("machineLearnings", machineLearnings);
        titleMessage = "Event List";
        model.addAttribute("titleMessage", titleMessage);  
        return "/aiml/aiAlgorithms";
    }

    @RequestMapping(value = { "/aiml/addAiAlgorithm" }, method = RequestMethod.GET)
    public String addArtificialIntelligenceForm(Model model) {
 
        ArtificialIntelligenceForm artificialIntelligenceForm = new ArtificialIntelligenceForm();
        model.addAttribute("artificialIntelligenceForm", artificialIntelligenceForm);
        artificialIntelligences = ArtificialIntelligenceDBO.loadArtificialIntelligences();
        model.addAttribute("artificialIntelligences", artificialIntelligences);
        titleMessage = "Add ArtificialIntelligence";
        model.addAttribute("titleMessage", titleMessage); 
        return "/aiml/addAiAlgorithm";
    }

    @RequestMapping(value = { "/aiml/addAiAlgorithm" }, method = RequestMethod.POST)
    public String addArtificialIntelligenceSave(Model model, //
        @ModelAttribute("artificialIntelligenceForm") ArtificialIntelligenceForm artificialIntelligenceForm) {
        String title = artificialIntelligenceForm.getTitle();
        String description = artificialIntelligenceForm.getDescription();
        String algorithmPath = artificialIntelligenceForm.getAlgorithmPath();
        String dataSourcePath = artificialIntelligenceForm.getDataSourcePath();
        String dataTargetPath = artificialIntelligenceForm.getDataTargetPath();
        artificialIntelligences = ArtificialIntelligenceDBO.loadArtificialIntelligences();
 
        if (title != null && title.length() > 0 &&
            description != null && description.length() > 0 &&
            algorithmPath != null && algorithmPath.length() > 0 &&
            dataSourcePath != null && dataSourcePath.length() > 0 &&
            dataTargetPath != null && dataTargetPath.length() > 0 ){
            ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence(
                    title, description, algorithmPath, dataSourcePath, dataTargetPath);
            artificialIntelligences.add(artificialIntelligence);
            ArtificialIntelligenceDBO.saveSQLArtificialIntelligence(artificialIntelligence);
            return "redirect:/aiml/aiAlgorithms";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/aiml/addAiAlgorithm";
    }

    @RequestMapping(value = { "/aiml/editAiAlgorithm/{id}" }, method = RequestMethod.GET)
    public String editArtificialIntelligenceForm(Model model,
            @PathVariable("id") Integer id) {
 
        ArtificialIntelligenceForm artificialIntelligenceForm = new ArtificialIntelligenceForm();
        model.addAttribute("artificialIntelligenceForm", artificialIntelligenceForm);
        titleMessage = "Add ArtificialIntelligence";
        model.addAttribute("titleMessage", titleMessage); 
        
        ArtificialIntelligenceForm artificialIntelligenceEditForm = new ArtificialIntelligenceForm();
        List<ArtificialIntelligence> editArtificialIntelligences = artificialIntelligences.stream()
            .filter((topic) -> Objects.equals(topic.getId(), id))
            .collect(Collectors.toList());
        if (editArtificialIntelligences.size()==1){
            ArtificialIntelligence editArtificialIntelligence = editArtificialIntelligences.get(0);
            artificialIntelligenceEditForm.setTitle(editArtificialIntelligence.getTitle());
            artificialIntelligenceEditForm.setDescription(editArtificialIntelligence.getDescription());
            artificialIntelligenceEditForm.setAlgorithmPath(editArtificialIntelligence.getAlgorithmPath());
            artificialIntelligenceEditForm.setDataSourcePath(editArtificialIntelligence.getDataSourcePath());
            artificialIntelligenceEditForm.setDataTargetPath(editArtificialIntelligence.getDataTargetPath());
            artificialIntelligenceEditForm.setId(id);
            model.addAttribute("artificialIntelligenceEditForm", artificialIntelligenceEditForm);
            return "/aiml/editAiAlgorithm";            
        } else {
            model.addAttribute("errorMessage","Algorithm id not found");
            return "/aiml/aiAlgorithms";
        }
    }

    @RequestMapping(value = { "/aiml/editArtificialIntelligence" }, method = RequestMethod.POST)
    public String editTopicSave(Model model, //
        @ModelAttribute("artificialIntelligenceForm") ArtificialIntelligenceForm artificialIntelligenceForm) {
        Integer id = artificialIntelligenceForm.getId();
        String title = artificialIntelligenceForm.getTitle();
        String description = artificialIntelligenceForm.getDescription();
        String algorithmPath = artificialIntelligenceForm.getAlgorithmPath();        
        String dataSourcePath = artificialIntelligenceForm.getDataSourcePath();        
        String dataTargetPath = artificialIntelligenceForm.getDataTargetPath();   
        if (title != null && title.length() > 0 &&
            description != null && description.length() > 0 &&
            algorithmPath != null && algorithmPath.length() > 0 && 
            dataSourcePath != null && dataSourcePath.length() > 0 && 
            dataTargetPath != null && dataTargetPath.length() > 0 ){
            ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence(
            title, description, algorithmPath, dataSourcePath, dataTargetPath);
            artificialIntelligence.setId(id);
            ArtificialIntelligenceDBO.updateArtificialIntelligence(artificialIntelligence);
            return "redirect:/aiml/aiAlgorithms";
        }
        String error = "All fields are required!";
        model.addAttribute("errorMessage", error);
        return "/aiml/editAiAlgorithm";
    }


    @RequestMapping(value = { "/aiml/addMlAlgorithm" }, method = RequestMethod.GET)
    public String addMachineLearningForm(Model model) {
 
        MachineLearningForm machineLearningForm = new MachineLearningForm();
        model.addAttribute("machineLearningForm", machineLearningForm);
        machineLearnings = MachineLearningDBO.loadMachineLearnings();
        model.addAttribute("machineLearnings", machineLearnings);
        titleMessage = "Add MachineLearning";
        model.addAttribute("titleMessage", titleMessage); 
        return "/aiml/addMlAlgorithm";
    }

    @RequestMapping(value = { "/aiml/addMlAlgorithm" }, method = RequestMethod.POST)
    public String addMachineLearningSave(Model model, //
        @ModelAttribute("machineLearningForm") MachineLearningForm machineLearningForm) {
        String title = machineLearningForm.getTitle();
        String description = machineLearningForm.getDescription();
        String algorithmPath = machineLearningForm.getAlgorithmPath();
        String dataSourcePath = machineLearningForm.getDataSourcePath();
        String dataTargetPath = machineLearningForm.getDataTargetPath();
        machineLearnings = MachineLearningDBO.loadMachineLearnings();
 
        if (title != null && title.length() > 0 &&
            description != null && description.length() > 0 &&
            algorithmPath != null && algorithmPath.length() > 0 &&
            dataSourcePath != null && dataSourcePath.length() > 0 &&
            dataTargetPath != null && dataTargetPath.length() > 0 ){
            MachineLearning machineLearning = new MachineLearning(
                    title, description, algorithmPath, dataSourcePath, dataTargetPath);
            machineLearnings.add(machineLearning);
            MachineLearningDBO.saveSQLMachineLearning(machineLearning);
            return "redirect:/aiml/mlAlgorithms";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/aiml/addMlAlgorithm";
    }

    @RequestMapping(value = { "/aiml/editMlAlgorithm/{id}" }, method = RequestMethod.GET)
    public String editMachineLearningForm(Model model,
            @PathVariable("id") Integer id) {
 
        MachineLearningForm machineLearningForm = new MachineLearningForm();
        model.addAttribute("machineLearningForm", machineLearningForm);
        titleMessage = "Add MachineLearning";
        model.addAttribute("titleMessage", titleMessage); 
        
        MachineLearningForm machineLearningEditForm = new MachineLearningForm();
        List<MachineLearning> editMachineLearnings = machineLearnings.stream()
            .filter((topic) -> Objects.equals(topic.getId(), id))
            .collect(Collectors.toList());
        if (editMachineLearnings.size()==1){
            MachineLearning editMachineLearning = editMachineLearnings.get(0);
            machineLearningEditForm.setTitle(editMachineLearning.getTitle());
            machineLearningEditForm.setDescription(editMachineLearning.getDescription());
            machineLearningEditForm.setAlgorithmPath(editMachineLearning.getAlgorithmPath());
            machineLearningEditForm.setDataSourcePath(editMachineLearning.getDataSourcePath());
            machineLearningEditForm.setDataTargetPath(editMachineLearning.getDataTargetPath());
            machineLearningEditForm.setId(id);
            model.addAttribute("machineLearningEditForm", machineLearningEditForm);
            return "/aiml/editMlAlgorithm";            
        } else {
            model.addAttribute("errorMessage","Algorithm id not found");
            return "/aiml/mlAlgorithms";
        }
    }

    @RequestMapping(value = { "/aiml/editMlAlgorithm" }, method = RequestMethod.POST)
    public String editTopicSave(Model model, //
        @ModelAttribute("machineLearningForm") MachineLearningForm machineLearningForm) {
        Integer id = machineLearningForm.getId();
        String title = machineLearningForm.getTitle();
        String description = machineLearningForm.getDescription();
        String algorithmPath = machineLearningForm.getAlgorithmPath();        
        String dataSourcePath = machineLearningForm.getDataSourcePath();        
        String dataTargetPath = machineLearningForm.getDataTargetPath();   
        if (title != null && title.length() > 0 &&
            description != null && description.length() > 0 &&
            algorithmPath != null && algorithmPath.length() > 0 && 
            dataSourcePath != null && dataSourcePath.length() > 0 && 
            dataTargetPath != null && dataTargetPath.length() > 0 ){
            MachineLearning machineLearning = new MachineLearning(
            title, description, algorithmPath, dataSourcePath, dataTargetPath);
            machineLearning.setId(id);
            MachineLearningDBO.updateMachineLearning(machineLearning);
            return "redirect:/aiml/aiAlgorithms";
        }
        String error = "All fields are required!";
        model.addAttribute("errorMessage", error);
        return "/aiml/editAiAlgorithm";
    }

    
}
