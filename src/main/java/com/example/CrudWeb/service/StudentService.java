package com.example.CrudWeb.service;

import com.example.CrudWeb.exception.StudentNotFoundException;
import com.example.CrudWeb.model.Student;
import com.example.CrudWeb.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException("Student not found with id " +id));
    }

    public Student addStudent(Student student){
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new RuntimeException("Email is already in use");
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student){
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }
}
