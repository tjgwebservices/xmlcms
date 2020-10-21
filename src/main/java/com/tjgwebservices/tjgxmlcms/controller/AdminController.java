package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.dbo.schools.AdministratorDBO;
import com.tjgwebservices.tjgxmlcms.dbo.schools.AdministratorGroupDBO;
import com.tjgwebservices.tjgxmlcms.dbo.schools.CourseDBO;
import com.tjgwebservices.tjgxmlcms.dbo.schools.LectureDBO;
import com.tjgwebservices.tjgxmlcms.dbo.schools.LectureNoteDBO;
import com.tjgwebservices.tjgxmlcms.dbo.schools.LecturerDBO;
import com.tjgwebservices.tjgxmlcms.dbo.schools.SchoolDBO;
import com.tjgwebservices.tjgxmlcms.dbo.schools.StudentDBO;
import com.tjgwebservices.tjgxmlcms.form.AdministratorForm;
import com.tjgwebservices.tjgxmlcms.form.AdministratorGroupForm;
import com.tjgwebservices.tjgxmlcms.form.CourseForm;
import com.tjgwebservices.tjgxmlcms.form.LectureForm;
import com.tjgwebservices.tjgxmlcms.form.LectureNoteForm;
import com.tjgwebservices.tjgxmlcms.form.LecturerForm;
import com.tjgwebservices.tjgxmlcms.form.SchoolForm;
import com.tjgwebservices.tjgxmlcms.form.StudentForm;
import com.tjgwebservices.tjgxmlcms.model.Administrator;
import com.tjgwebservices.tjgxmlcms.model.AdministratorGroup;
import com.tjgwebservices.tjgxmlcms.model.Course;
import com.tjgwebservices.tjgxmlcms.model.FileUpload;
import com.tjgwebservices.tjgxmlcms.model.Lecture;
import com.tjgwebservices.tjgxmlcms.model.LectureNote;
import com.tjgwebservices.tjgxmlcms.model.Lecturer;
import com.tjgwebservices.tjgxmlcms.model.School;
import com.tjgwebservices.tjgxmlcms.model.Student;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {
    private List<Administrator> administrators = new ArrayList<>();
    private List<AdministratorGroup> administratorGroups = new ArrayList<>();   
    private List<Course> courses = new ArrayList<>();
    private List<Lecture> lectures = new ArrayList<>();
    private List<LectureNote> lectureNotes = new ArrayList<>();
    private List<School> schools = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Lecturer> lecturers = new ArrayList<>();
    private String[] validFileExtensions = {"mpg","jpg","gif","png"};
    
    @Autowired
    ServletContext context;

    static {
    }    

    @Value("${title.message}")
    private String titleMessage;

    
    @RequestMapping(value = { "/schools/adminList" }, method = RequestMethod.GET)
    public String administratorList(Model model) {
        administrators = AdministratorDBO.loadAdministrators();
        model.addAttribute("administrators", administrators);
        administratorGroups = AdministratorGroupDBO.loadAdministratorGroups();
        model.addAttribute("administratorGroups", administratorGroups);
        courses = CourseDBO.loadCourses();
        model.addAttribute("courses",courses);
        lectures = LectureDBO.loadLectures();
        model.addAttribute("lectures",lectures);
        lectureNotes = LectureNoteDBO.loadLectureNotes();
        model.addAttribute("lectureNotes", lectureNotes);
        schools = SchoolDBO.loadSchools();
        model.addAttribute("schools",schools);
        students = StudentDBO.loadStudents();
        model.addAttribute("students",students);
        lecturers = LecturerDBO.loadLecturers();
        model.addAttribute("lecturers",lecturers);
        return "schools/adminList";
    }


    @RequestMapping(value = { "/schools/addAdministrator" }, method = RequestMethod.GET)
    public String addAdministatorForm(Model model) {
 
        AdministratorForm administratorForm = new AdministratorForm();
        model.addAttribute("administratorForm", administratorForm);
        administratorGroups = AdministratorGroupDBO.loadAdministratorGroups();
        /*Map<String,String> adminGroupOptions = new HashMap<>();
        administratorGroups.forEach(g ->
                adminGroupOptions.put(String.valueOf(g.getId()),
                        g.getGroupName()
                ));
        model.addAttribute("administratorGroups", adminGroupOptions);*/
        model.addAttribute("administratorGroups", administratorGroups);
        titleMessage = "Add Administrator";
        model.addAttribute("titleMessage", titleMessage); 
        return "schools/addAdministrator";
    }
 
    @RequestMapping(value = { "schools/addAdministrator" }, method = RequestMethod.POST)
    public String addAdministratorSave(Model model, //
        @ModelAttribute("administratorForm") AdministratorForm administratorForm) {
        String administatorName = administratorForm.getAdministratorName();
        int groupId = administratorForm.getAdministratorGroupId();
 
        if (administatorName != null && administatorName.length() > 0){
            Administrator administrator = new Administrator(administatorName, groupId);
            administrators.add(administrator);
            AdministratorDBO.saveSQLAdministrator(administrator);
            return "redirect:/schools/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "schools/addAdministrator";
    }

    @RequestMapping(value = { "schools/addAdministratorGroup" }, method = RequestMethod.GET)
    public String addAdministratorGroupForm(Model model) {
 
        AdministratorGroupForm administratorGroupForm = new AdministratorGroupForm();
        model.addAttribute("administratorGroupForm", administratorGroupForm);
        titleMessage = "Add Administrator Group";
        model.addAttribute("titleMessage", titleMessage); 
 
        return "schools/addAdministratorGroup";
    }
 
    @RequestMapping(value = { "/schools/addAdministratorGroup" }, method = RequestMethod.POST)
    public String addAdministratorGroupSave(Model model, //
        @ModelAttribute("administratorGroupForm") AdministratorGroupForm administratorGroupForm) {
        String groupName = administratorGroupForm.getGroupName();
 
        if (groupName != null && groupName.length() > 0){
            AdministratorGroup administratorGroup = new AdministratorGroup(groupName);
            administratorGroups.add(administratorGroup);
            AdministratorGroupDBO.saveSQLAdministratorGroup(administratorGroup);
            return "redirect:/schools/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "schools/addAdministratorGroups";
    }
    
    
    @RequestMapping(value = { "/schools/addCourse" }, method = RequestMethod.GET)
    public String addCourseForm(Model model) {
 
        CourseForm courseForm = new CourseForm();
        model.addAttribute("courseForm", courseForm);
        titleMessage = "Add Course";
        model.addAttribute("titleMessage", titleMessage); 
 
        return "schools/addCourse";
    }
 
    @RequestMapping(value = { "/schools/addCourse" }, method = RequestMethod.POST)
    public String addCourseSave(Model model, //
        @ModelAttribute("courseForm") CourseForm courseForm) {
        String courseName = courseForm.getCourseName();
 
        if (courseName != null && courseName.length() > 0){
            Course course = new Course(courseName);
            courses.add(course);
            CourseDBO.saveSQLCourse(course);
            return "redirect:/schools/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "schools/addCourse";
    }

    @RequestMapping(value = { "/schools/addLecture" }, method = RequestMethod.GET)
    public String addLectureForm(Model model) {
 
        LectureForm lectureForm = new LectureForm();
        model.addAttribute("lectureForm", lectureForm);
        titleMessage = "Add Lecture";
        model.addAttribute("titleMessage", titleMessage); 
 
        return "schools/addLecture";
    }
 
    @RequestMapping(value = { "/schools/addLecture" }, method = RequestMethod.POST)
    public String addLectureSave(@Validated LectureForm file, BindingResult result,
            Model model, //
        @ModelAttribute("lectureForm") LectureForm lectureForm) {
        String lectureName = lectureForm.getLectureName();
        //FileUpload posterFile = lectureForm.getLecturePoster();
        if (result.hasErrors() ||
                lectureName == null || lectureName.length() < 0){
            System.out.println("validation errors");
            String error = "All fieds are required!";
            model.addAttribute("errorMessage", error);
            return "schools/addLecture";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = file.getLecturePoster();
            String uploadPath = context.getRealPath("") + File.separator +
                    "temp" + File.separator;
            try {
                Optional<String> fileExtension = Optional.ofNullable(multipartFile.getOriginalFilename())
                .filter(f -> f.contains("."))
                .map(f -> f.substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1));
                boolean validFile = false;
                for (String extension : validFileExtensions) {
                    if (fileExtension.isPresent() && 
                        fileExtension.get().equalsIgnoreCase(extension)){
                        validFile = true;
                        break;
                    }
                }
                if (validFile){
                    model.addAttribute("message", "Valid file uploaded");
                } else {
                    String error = "Only image and video files for lecture poster!";
                    model.addAttribute("errorMessage", error);
                    return "schools/addLecture";
                }                
                FileCopyUtils.copy(multipartFile.getBytes(),
                        new File(uploadPath+multipartFile.getOriginalFilename()
                        ));
                        String fileName = multipartFile.getOriginalFilename();
                        Lecture lecture = new Lecture(lectureName,multipartFile);
                        model.addAttribute("fileName",fileName);
                        lectures.add(lecture);
                        LectureDBO.saveSQLLecture(lecture);
                        return "redirect:/schools/adminList";
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                String error = "All fieds are required!";
                model.addAttribute("errorMessage", error);
                return "schools/addLecture";
            }
        }
    }
    
    @RequestMapping(value = { "/schools/addLectureNote" }, method = RequestMethod.GET)
    public String addLectureNoteForm(Model model) {
 
        LectureNoteForm lectureNoteForm = new LectureNoteForm();
        model.addAttribute("lectureNoteForm", lectureNoteForm);
        titleMessage = "Add Lecture Note";
        model.addAttribute("titleMessage", titleMessage); 
 
        return "schools/addLectureNote";
    }
 
    @RequestMapping(value = { "/schools/addLectureNote" }, method = RequestMethod.POST)
    public String addLectureNoteSave(Model model, //
        @ModelAttribute("lectureNoteForm") LectureNoteForm lectureNoteForm) {
        String noteInstructor = lectureNoteForm.getNoteInstructor();
        String noteLecture = lectureNoteForm.getNoteLecture();
        String noteText = lectureNoteForm.getNoteText();

        if (noteInstructor != null && noteInstructor.length() > 0 &&
                noteLecture != null && noteLecture.length() > 0 &&
                noteText != null && noteText.length() > 0){
            LectureNote lectureNote = new LectureNote(noteInstructor,noteLecture,noteText);
            lectureNotes.add(lectureNote);
            LectureNoteDBO.saveSQLLectureNote(lectureNote);
            return "redirect:/schools/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "schools/addLectureNote";
    }

    @RequestMapping(value = { "/schools/addLecturer" }, method = RequestMethod.GET)
    public String addLecturerForm(Model model) {
 
        LecturerForm lecturerForm = new LecturerForm();
        model.addAttribute("lecturerForm", lecturerForm);
        titleMessage = "Add Lecturer";
        model.addAttribute("titleMessage", titleMessage); 

        return "schools/addLecturer";
    }
 
    @RequestMapping(value = { "/schools/addLecturer" }, method = RequestMethod.POST)
    public String addLecturerSave(Model model, //
        @ModelAttribute("lecturerForm") LecturerForm lecturerForm) {
        String lecturerName = lecturerForm.getLecturerName();

        if (lecturerName != null && lecturerName.length() > 0 ){
            Lecturer lecturer = new Lecturer(lecturerName);
            lecturers.add(lecturer);
            LecturerDBO.saveSQLLecturer(lecturer);
            return "redirect:/schools/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "schools/addLecturer";
    }
    
    
    
    
    
    @RequestMapping(value = { "schools/addSchool" }, method = RequestMethod.GET)
    public String addSchoolForm(Model model) {
 
        SchoolForm schoolForm = new SchoolForm();
        model.addAttribute("schoolForm", schoolForm);
        titleMessage = "Add School";
        model.addAttribute("titleMessage", titleMessage);  
        return "schools/addSchool";
    }
 
    @RequestMapping(value = { "/schools/addSchool" }, method = RequestMethod.POST)
    public String addSchoolSave(Model model, //
        @ModelAttribute("schoolForm") SchoolForm schoolForm) {
        String schoolName = schoolForm.getSchoolName();
        String schoolLecturer = schoolForm.getSchoolLecturer();

        if (schoolName != null && schoolName.length() > 0 &&
                schoolLecturer != null && schoolLecturer.length() > 0){
            School school = new School(schoolName,schoolLecturer);
            schools.add(school);
            SchoolDBO.saveSQLSchool(school);
            return "redirect:/schools/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "schools/addSchool";
    }

    @RequestMapping(value = { "/schools/addStudent" }, method = RequestMethod.GET)
    public String addStudentForm(Model model) {
 
        StudentForm studentForm = new StudentForm();
        model.addAttribute("studentForm", studentForm);
        titleMessage = "Add Student";
        model.addAttribute("titleMessage", titleMessage); 
 
        return "schools/addStudent";
    }
 
    @RequestMapping(value = { "/schools/addStudent" }, method = RequestMethod.POST)
    public String addStudentSave(Model model, //
        @ModelAttribute("studentForm") StudentForm studentForm) {
        String lastName = studentForm.getLastName();
        String firstName = studentForm.getFirstName();
        int courseId = studentForm.getCourseId();

        if (lastName != null && lastName.length() > 0 &&
                firstName != null && firstName.length() > 0 &&
                Integer.valueOf(courseId) > -1){
            Student student = new Student(lastName,firstName, courseId);
            students.add(student);
            StudentDBO.saveSQLStudent(student);
            return "redirect:/schools/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "schools/addStudent";
    }

    
}
