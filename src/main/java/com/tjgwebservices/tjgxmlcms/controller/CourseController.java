package com.tjgwebservices.tjgxmlcms.controller;

import com.tjgwebservices.tjgxmlcms.dbo.schools.LectureDBO;
import com.tjgwebservices.tjgxmlcms.dbo.schools.StudentDBO;
import com.tjgwebservices.tjgxmlcms.model.Lecture;
import com.tjgwebservices.tjgxmlcms.model.Student;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseController {

    List<Student> students = new ArrayList<>();
    List<Lecture> lectures = new ArrayList<>();

    @Autowired
    ServletContext context;

    static {
    }    
    
    @RequestMapping(value = { "/courses/course" }, method = RequestMethod.GET)
    public String displayCoursePage(Model model) {
       students = StudentDBO.loadStudents();
        model.addAttribute("students",students);
        lectures = LectureDBO.loadLectures();
        model.addAttribute("lectures",lectures);
         return "course";
    }
    
}
