package api.studentgrievancesupport.com.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import api.studentgrievancesupport.com.beans.Student;
import api.studentgrievancesupport.com.beans.User;
import api.studentgrievancesupport.com.model.Role;
import api.studentgrievancesupport.com.repository.StudentRepository;
import api.studentgrievancesupport.com.repository.UserRepository;
import org.springframework.http.HttpStatus;

//Creating the REST controller
@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/students") // READ
    public List<Student> getStudents(@RequestParam(required = false) String email) {
        List<Student> students = new ArrayList<>();
        if (email != null && !email.isBlank()) {
            students = studentRepository.findById(email).isPresent()
                    ? Collections.singletonList(studentRepository.findById(email).get())
                    : Collections.emptyList();
        } else {
            students = (List<Student>) studentRepository.findAll();
        }
        return students;
    }

    @PostMapping("/students") // CREATE
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        User user = new User(student.getEmail(), student.getPassword(), Role.STUDENT);
        userRepository.save(user);
        return studentRepository.save(student);
    }

    @DeleteMapping("/students/{email}") // DELETE
    public void deleteStudent(@PathVariable String email) {
        studentRepository.deleteById(email);
        userRepository.deleteById(email);
    }

    @PutMapping("/students/{email}") // UPDATE
    Student updateStudent(@RequestBody Student newStudent, @PathVariable String email) {

        return studentRepository.findById(email).map(student -> {
            /* TODO */
            return studentRepository.save(student);
        }).orElseGet(() -> {
            newStudent.setEmail(email);
            return studentRepository.save(newStudent);
        });
    }
}
