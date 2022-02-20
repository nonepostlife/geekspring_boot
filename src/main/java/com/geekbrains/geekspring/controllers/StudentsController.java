package com.geekbrains.geekspring.controllers;

import com.geekbrains.geekspring.entities.Course;
import com.geekbrains.geekspring.entities.Student;
import com.geekbrains.geekspring.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/students")
@Transactional
public class StudentsController {
    private StudentsService studentsService;

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @RequestMapping("/list")
    @Transactional
    public String showStudentsList(Model model) {
        List<Student> allStudents = studentsService.getAllStudentsList();
        model.addAttribute("studentsList", allStudents);
        return "students-list";
    }

    @RequestMapping(path = "/remove/{id}", method = RequestMethod.GET)
    @Transactional
    public String removeStudent(@PathVariable(value = "id") Long idStudent) {
        studentsService.removeById(idStudent);
        return "redirect:/students/list";
    }

    @RequestMapping(path = "/courses/{id}", method = RequestMethod.GET)
    @Transactional
    public String showCoursesByStudent(@PathVariable(value = "id") Long idStudent, Model model) {
        List<Course> studentCourses = studentsService.getCoursesByStudentId(idStudent);
        model.addAttribute("studentCourses", studentCourses);
        return "student-courses-list";
    }
}
