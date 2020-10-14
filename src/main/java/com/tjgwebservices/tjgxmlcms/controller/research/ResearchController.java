package com.tjgwebservices.tjgxmlcms.controller.research;

import com.tjgwebservices.tjgxmlcms.dbo.research.ProjectDBO;
import com.tjgwebservices.tjgxmlcms.dbo.research.ResearcherDBO;
import com.tjgwebservices.tjgxmlcms.dbo.research.TopicDBO;
import com.tjgwebservices.tjgxmlcms.form.research.ProjectForm;
import com.tjgwebservices.tjgxmlcms.form.research.ResearcherForm;
import com.tjgwebservices.tjgxmlcms.form.research.TopicForm;
import com.tjgwebservices.tjgxmlcms.model.research.Project;
import com.tjgwebservices.tjgxmlcms.model.research.Researcher;
import com.tjgwebservices.tjgxmlcms.model.research.Topic;
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
public class ResearchController {
    private List<Topic> topics = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<Researcher> researchers = new ArrayList<>();

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

    @RequestMapping(value = { "/research/researchers" }, method = RequestMethod.GET)
    public String researcherList(Model model) {
        researchers = ResearcherDBO.loadResearchers();
        model.addAttribute("researchers", researchers);
        titleMessage = "Researcher List";
        model.addAttribute("titleMessage", titleMessage);  
        return "research/researchers";
    }

    @RequestMapping(value = { "/research/topics" }, method = RequestMethod.GET)
    public String topicList(Model model) {
        topics = TopicDBO.loadTopics();
        model.addAttribute("topics", topics);
        titleMessage = "Topic List";
        model.addAttribute("titleMessage", titleMessage);  
        return "research/topics";
    }

    @RequestMapping(value = { "/research/projects" }, method = RequestMethod.GET)
    public String projectList(Model model) {
        projects = ProjectDBO.loadProjects();
        model.addAttribute("projects", projects);
        titleMessage = "Project List";
        model.addAttribute("titleMessage", titleMessage);  
        return "research/topics";
    }

    @RequestMapping(value = { "/research/addTopic" }, method = RequestMethod.GET)
    public String addTopicForm(Model model) {
 
        TopicForm topicForm = new TopicForm();
        model.addAttribute("topicForm", topicForm);
        researchers = ResearcherDBO.loadResearchers();
        model.addAttribute("researchers", researchers);
        titleMessage = "Add Topic";
        model.addAttribute("titleMessage", titleMessage); 
        return "research/addTopic";
    }

    @RequestMapping(value = { "/research/addTopic" }, method = RequestMethod.POST)
    public String addTopicSave(Model model, //
        @ModelAttribute("topicForm") TopicForm topicForm) {
        String topicName = topicForm.getTopicName();
        String topicSubject = topicForm.getTopicSubject();
        String topicDescription = topicForm.getTopicDescription();
        int researcherId = topicForm.getResearcherId();
 
        if (topicName != null && topicName.length() > 0 &&
            topicSubject != null && topicSubject.length() > 0 &&
            topicDescription != null && topicDescription.length() > 0){
            Topic topic = new Topic(topicName, topicSubject,
            topicDescription, researcherId);
            topics.add(topic);
            TopicDBO.saveSQLTopic(topic);
            return "redirect:/research/topics";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "research/addTopic";
    }

    @RequestMapping(value = { "/research/editTopic/{id}" }, method = RequestMethod.GET)
    public String editTopicForm(Model model,
            @PathVariable("id") Integer id) {
 
        TopicForm topicForm = new TopicForm();
        model.addAttribute("topicForm", topicForm);
        researchers = ResearcherDBO.loadResearchers();
        model.addAttribute("researchers", researchers);
        titleMessage = "Add Topic";
        model.addAttribute("titleMessage", titleMessage); 
        
        TopicForm topicEditForm = new TopicForm();
        Topic editTopic = topics.stream()
            .filter((topic) -> topic.getId() == id)
            .collect(Collectors.toList()).get(0);
        topicEditForm.setTopicName(editTopic.getTopicName());
        topicEditForm.setTopicSubject(editTopic.getTopicSubject());
        topicEditForm.setTopicDescription(editTopic.getTopicDescription());
        model.addAttribute("topicEditForm", topicEditForm);

        return "research/editTopic/{id}";
    }

    @RequestMapping(value = { "/research/editTopic/{id}" }, method = RequestMethod.POST)
    public String editTopicSave(Model model, //
        @ModelAttribute("topicForm") TopicForm topicForm,
        @PathVariable("id") Integer id) {
        String topicName = topicForm.getTopicName();
        String topicSubject = topicForm.getTopicSubject();
        String topicDescription = topicForm.getTopicDescription();
        int researcherId = topicForm.getResearcherId();
 
        if (topicName != null && topicName.length() > 0 &&
            topicSubject != null && topicSubject.length() > 0 &&
            topicDescription != null && topicDescription.length() > 0){
            Topic topic = new Topic(topicName, topicSubject,
            topicDescription, researcherId);
            topic.setId(id);
            //topics.add(topic);
            TopicDBO.updateTopic(topic);
            return "redirect:/research/topics";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "research/editTopic";
    }

    @RequestMapping(value = { "/research/addProject" }, method = RequestMethod.GET)
    public String addProjectForm(Model model) {
 
        ProjectForm projectForm = new ProjectForm();
        model.addAttribute("projectForm", projectForm);
        researchers = ResearcherDBO.loadResearchers();
        model.addAttribute("researchers", researchers);
        titleMessage = "Add Project";
        model.addAttribute("titleMessage", titleMessage); 
        return "research/addProject";
    }

    @RequestMapping(value = { "/research/addProject" }, method = RequestMethod.POST)
    public String addProjectSave(Model model, //
        @ModelAttribute("projectForm") ProjectForm projectForm) {
        String projectName = projectForm.getProjectName();
        String projectSubject = projectForm.getProjectSubject();
        String projectDescription = projectForm.getProjectDescription();
        int researcherId = projectForm.getResearcherId();
 
        if (projectName != null && projectName.length() > 0 &&
            projectSubject != null && projectSubject.length() > 0 &&
            projectDescription != null && projectDescription.length() > 0){
            Project project = new Project(projectName, projectSubject,
            projectDescription, researcherId);
            projects.add(project);
            ProjectDBO.saveSQLProject(project);
            return "redirect:/research/projects";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "research/addProject";
    }

    @RequestMapping(value = { "/research/editProject/{id}" }, method = RequestMethod.GET)
    public String editProjectForm(Model model,
            @PathVariable("id") Integer id) {
 
        ProjectForm projectForm = new ProjectForm();
        model.addAttribute("projectForm", projectForm);
        researchers = ResearcherDBO.loadResearchers();
        model.addAttribute("researchers", researchers);
        titleMessage = "Edit Project";
        model.addAttribute("titleMessage", titleMessage); 
        
        ProjectForm projectEditForm = new ProjectForm();
        Project editProject = projects.stream()
            .filter((project) -> project.getId() == id)
            .collect(Collectors.toList()).get(0);
        projectEditForm.setProjectName(editProject.getProjectName());
        projectEditForm.setProjectSubject(editProject.getProjectSubject());
        projectEditForm.setProjectDescription(editProject.getProjectDescription());
        model.addAttribute("projectEditForm", projectEditForm);

        return "research/editProject/{id}";
    }

    @RequestMapping(value = { "/research/editProject/{id}" }, method = RequestMethod.POST)
    public String editProjectSave(Model model, //
        @ModelAttribute("projectForm") ProjectForm projectForm,
        @PathVariable("id") Integer id) {
        String projectName = projectForm.getProjectName();
        String projectSubject = projectForm.getProjectSubject();
        String projectDescription = projectForm.getProjectDescription();
        int researcherId = projectForm.getResearcherId();
 
        if (projectName != null && projectName.length() > 0 &&
            projectSubject != null && projectSubject.length() > 0 &&
            projectDescription != null && projectDescription.length() > 0){
            Project project = new Project(projectName, projectSubject,
            projectDescription, researcherId);
            project.setId(id);
            ProjectDBO.updateProject(project);
            return "redirect:/research/projects";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "research/editProject";
    }

    @RequestMapping(value = { "/research/addResearcher" }, method = RequestMethod.GET)
    public String addResearcherForm(Model model) {
 
        ResearcherForm researcherForm = new ResearcherForm();
        model.addAttribute("researcherForm", researcherForm);
        titleMessage = "Add Researcher";
        model.addAttribute("titleMessage", titleMessage); 
        return "research/addResearcher";
    }

    @RequestMapping(value = { "/research/addResearcher" }, method = RequestMethod.POST)
    public String addResearcherSave(Model model, //
        @ModelAttribute("researcherForm") ResearcherForm researcherForm) {
        String researcherFirstName = researcherForm.getResearcherFirstName();
        String researcherLastName = researcherForm.getResearcherLastName();
        String researcherDegree = researcherForm.getResearcherDegree();
        String researcherMajor = researcherForm.getResearcherMajor();
        String researcherInstitution = researcherForm.getResearcherInstitution();
        String researcherSpecialty = researcherForm.getResearcherSpecialty();
 
        if (researcherFirstName != null && researcherFirstName.length() > 0 &&
            researcherLastName != null && researcherLastName.length() > 0 &&
            researcherDegree != null && researcherDegree.length() > 0 &&
            researcherMajor != null && researcherMajor.length() > 0 &&
            researcherInstitution != null && researcherInstitution.length() > 0 &&
            researcherSpecialty != null && researcherSpecialty.length() > 0){
            Researcher researcher = new Researcher(researcherFirstName, 
            researcherLastName, researcherDegree, researcherMajor,
            researcherInstitution, researcherSpecialty);
            researchers.add(researcher);
            ResearcherDBO.saveSQLResearcher(researcher);
            return "redirect:/research/projects";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "research/addResearcher";
    }

    @RequestMapping(value = { "/research/editResearcher/{id}" }, method = RequestMethod.GET)
    public String editResearcherForm(Model model,
            @PathVariable("id") Integer id) {
 
        ResearcherForm researcherForm = new ResearcherForm();
        model.addAttribute("researcherForm", researcherForm);
        titleMessage = "Add Researcher";
        model.addAttribute("titleMessage", titleMessage); 
        ResearcherForm reseacherEditForm = new ResearcherForm();
        Researcher editReseacher = researchers.stream()
            .filter((reseacher) -> reseacher.getId() == id)
            .collect(Collectors.toList()).get(0);
        reseacherEditForm.setResearcherFirstName(editReseacher.getResearcherFirstName());
        reseacherEditForm.setResearcherLastName(editReseacher.getResearcherLastName());
        reseacherEditForm.setResearcherLastName(editReseacher.getResearcherLastName());
        reseacherEditForm.setResearcherDegree(editReseacher.getResearcherDegree());
        reseacherEditForm.setResearcherMajor(editReseacher.getResearcherMajor());
        reseacherEditForm.setResearcherInstitution(editReseacher.getResearcherInstitution());
        reseacherEditForm.setResearcherSpecialty(editReseacher.getResearcherInstitution());
        model.addAttribute("reseacherEditForm", reseacherEditForm);

        return "research/addResearcher/{id}";
    }

    @RequestMapping(value = { "/research/editResearcher/{id}" }, method = RequestMethod.POST)
    public String editResearcherSave(Model model, //
        @ModelAttribute("researcherForm") ResearcherForm researcherForm,
        @PathVariable("id") Integer id) {
        String researcherFirstName = researcherForm.getResearcherFirstName();
        String researcherLastName = researcherForm.getResearcherLastName();
        String researcherDegree = researcherForm.getResearcherDegree();
        String researcherMajor = researcherForm.getResearcherMajor();
        String researcherInstitution = researcherForm.getResearcherInstitution();
        String researcherSpecialty = researcherForm.getResearcherSpecialty();
 
        if (researcherFirstName != null && researcherFirstName.length() > 0 &&
            researcherLastName != null && researcherLastName.length() > 0 &&
            researcherDegree != null && researcherDegree.length() > 0 &&
            researcherMajor != null && researcherMajor.length() > 0 &&
            researcherInstitution != null && researcherInstitution.length() > 0 &&
            researcherSpecialty != null && researcherSpecialty.length() > 0){
            Researcher researcher = new Researcher(researcherFirstName, 
            researcherLastName, researcherDegree, researcherMajor,
            researcherInstitution, researcherSpecialty);
            researcher.setId(id);
            ResearcherDBO.updateResearcher(researcher);
            return "redirect:/researchers";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "research/editResearcher/{id}";
    }

    
}
