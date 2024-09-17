package com.example.CrudWeb.controller;

import com.example.CrudWeb.exception.GlobalExceptionHandler;
import com.example.CrudWeb.exception.StudentNotFoundException;
import com.example.CrudWeb.model.Student;
import com.example.CrudWeb.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // to retrieve list of students
    @GetMapping
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/details/{id}")
    public String getStudentById(@PathVariable Long id, Model model) {
        try{
            Student student = studentService.getStudentById(id);
            model.addAttribute("student", student);
            return "student_detail";
        }
        catch(StudentNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page";
        }
    }

    // to show new form for creating students
    @GetMapping("/new")
    public String createStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    // save a new student and redirecting to student page
    @PostMapping
    public String saveStudent(Model model,@ModelAttribute("student") Student student) {
        try{
            studentService.addStudent(student);
            return "redirect:/students";
        }
        catch(RuntimeException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "create_student";
        }

    }

    // to display form for editing existing student
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        try{
            model.addAttribute("student", studentService.getStudentById(id));
            return "edit_student";
        }
        catch(StudentNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page";
        }

    }

    //Post request to update an existing student
    @PostMapping("/{id}")
    public String updateStudent(Model model,@PathVariable Long id, @ModelAttribute("student") Student student) {
            Student existingStudent = studentService.getStudentById(id);
            existingStudent.setId(id);
            existingStudent.setName(student.getName());
            existingStudent.setAge(student.getAge());
            existingStudent.setEmail(student.getEmail());
            studentService.updateStudent(existingStudent);
            return "redirect:/students";
    }

    //request to delete a student
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
