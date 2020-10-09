package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.dbo.AdministratorDBO;
import com.tjgwebservices.tjgxmlcms.dbo.AdministratorGroupDBO;
import com.tjgwebservices.tjgxmlcms.dbo.ArticleDBO;
import com.tjgwebservices.tjgxmlcms.dbo.LectureDBO;
import com.tjgwebservices.tjgxmlcms.dbo.LectureNoteDBO;
import com.tjgwebservices.tjgxmlcms.dbo.SchoolDBO;
import com.tjgwebservices.tjgxmlcms.dbo.StudentDBO;
import com.tjgwebservices.tjgxmlcms.form.AdministratorForm;
import com.tjgwebservices.tjgxmlcms.form.AdministratorGroupForm;
import com.tjgwebservices.tjgxmlcms.form.LectureForm;
import com.tjgwebservices.tjgxmlcms.form.LectureNoteForm;
import com.tjgwebservices.tjgxmlcms.form.SchoolForm;
import com.tjgwebservices.tjgxmlcms.form.StudentForm;
import com.tjgwebservices.tjgxmlcms.model.Administrator;
import com.tjgwebservices.tjgxmlcms.model.AdministratorGroup;
import com.tjgwebservices.tjgxmlcms.model.Lecture;
import com.tjgwebservices.tjgxmlcms.model.LectureNote;
import com.tjgwebservices.tjgxmlcms.model.School;
import com.tjgwebservices.tjgxmlcms.model.Student;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.Encoder.Binary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
    List<Administrator> administrators = new ArrayList<>();
    List<AdministratorGroup> administratorGroups = new ArrayList<>();   
    List<Lecture> lectures = new ArrayList<>();
    List<LectureNote> lectureNotes = new ArrayList<>();
    List<School> schools = new ArrayList<>();
    List<Student> students = new ArrayList<>();

    static {
    }    
    
    @RequestMapping(value = { "/adminList" }, method = RequestMethod.GET)
    public String administratorList(Model model) {
        administrators = AdministratorDBO.loadAdministrators();
        model.addAttribute("administrators", administrators);
        administratorGroups = AdministratorGroupDBO.loadAdministratorGroups();
        model.addAttribute("administratorGroups", administratorGroups);
        lectures = LectureDBO.loadLectures();
        model.addAttribute("lectures",lectures);
        lectureNotes = LectureNoteDBO.loadLectureNotes();
        model.addAttribute("lectureNotes", lectureNotes);
        schools = SchoolDBO.loadSchools();
        model.addAttribute("schools",schools);
        students = StudentDBO.loadStudents();
        model.addAttribute("students",students);
        return "adminList";
    }


    @RequestMapping(value = { "/addAdministrator" }, method = RequestMethod.GET)
    public String addAdministatorForm(Model model) {
 
        AdministratorForm administratorForm = new AdministratorForm();
        model.addAttribute("administratorForm", administratorForm);
 
        return "addAdministrator";
    }
 
    @RequestMapping(value = { "/addAdministrator" }, method = RequestMethod.POST)
    public String addAdministratorSave(Model model, //
        @ModelAttribute("administratorForm") AdministratorForm administratorForm) {
        String administatorName = administratorForm.getAdministratorName();
        int groupId = administratorForm.getAdministratorGroupId();
 
        if (administatorName != null && administatorName.length() > 0){
            Administrator administrator = new Administrator(administatorName, groupId);
            administrators.add(administrator);
            AdministratorDBO.saveSQLAdministrator(administrator);
            return "redirect:/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "addAdministrator";
    }

    @RequestMapping(value = { "/addAdministratorGroup" }, method = RequestMethod.GET)
    public String addAdministratorGroupForm(Model model) {
 
        AdministratorGroupForm administratorGroupForm = new AdministratorGroupForm();
        model.addAttribute("administratorGroupForm", administratorGroupForm);
 
        return "addAdministratorGroup";
    }
 
    @RequestMapping(value = { "/addAdministratorGroup" }, method = RequestMethod.POST)
    public String addAdministratorGroupSave(Model model, //
        @ModelAttribute("administratorGroupForm") AdministratorGroupForm administratorGroupForm) {
        String groupName = administratorGroupForm.getGroupName();
 
        if (groupName != null && groupName.length() > 0){
            AdministratorGroup administratorGroup = new AdministratorGroup(groupName);
            administratorGroups.add(administratorGroup);
            AdministratorGroupDBO.saveSQLAdministratorGroup(administratorGroup);
            return "redirect:/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "addAdministratorGroups";
    }
    
    
    @RequestMapping(value = { "/addLecture" }, method = RequestMethod.GET)
    public String addLectureForm(Model model) {
 
        LectureForm lectureForm = new LectureForm();
        model.addAttribute("lectureForm", lectureForm);
 
        return "addLecture";
    }
 
    @RequestMapping(value = { "/addLecture" }, method = RequestMethod.POST)
    public String addLectureSave(Model model, //
        @ModelAttribute("lectureForm") LectureForm lectureForm) {
        String lectureName = lectureForm.getLectureName();
        Binary lecturePoster = lectureForm.getLecturePoster();
 
        if (lectureName != null && lectureName.length() > 0){
            Lecture lecture = new Lecture(lectureName,lecturePoster);
            lectures.add(lecture);
            LectureDBO.saveSQLLecture(lecture);
            return "redirect:/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "addLecture";
    }

    @RequestMapping(value = { "/addLectureNote" }, method = RequestMethod.GET)
    public String addLectureNoteForm(Model model) {
 
        LectureNoteForm lectureNoteForm = new LectureNoteForm();
        model.addAttribute("lectureForm", lectureNoteForm);
 
        return "addLecture";
    }
 
    @RequestMapping(value = { "/addLectureNote" }, method = RequestMethod.POST)
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
            return "redirect:/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "addLectureNote";
    }

    @RequestMapping(value = { "/addSchool" }, method = RequestMethod.GET)
    public String addSchoolForm(Model model) {
 
        SchoolForm schoolForm = new SchoolForm();
        model.addAttribute("schoolForm", schoolForm);
 
        return "addSchool";
    }
 
    @RequestMapping(value = { "/addSchool" }, method = RequestMethod.POST)
    public String addSchoolSave(Model model, //
        @ModelAttribute("schoolForm") SchoolForm schoolForm) {
        String schoolName = schoolForm.getSchoolName();
        String schoolLecturer = schoolForm.getSchoolLecturer();

        if (schoolName != null && schoolName.length() > 0 &&
                schoolLecturer != null && schoolLecturer.length() > 0){
            School school = new School(schoolName,schoolLecturer);
            schools.add(school);
            SchoolDBO.saveSQLSchool(school);
            return "redirect:/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "addSchool";
    }

    @RequestMapping(value = { "/addStudent" }, method = RequestMethod.GET)
    public String addStudentForm(Model model) {
 
        StudentForm studentForm = new StudentForm();
        model.addAttribute("studentForm", studentForm);
 
        return "addStudent";
    }
 
    @RequestMapping(value = { "/addStudent" }, method = RequestMethod.POST)
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
            return "redirect:/adminList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "addStudent";
    }
    
}
