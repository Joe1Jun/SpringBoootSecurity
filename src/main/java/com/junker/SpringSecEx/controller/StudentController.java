package com.junker.SpringSecEx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.junker.SpringSecEx.model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

	// A list of students that acts as an in-memory database for this example.
    private List<Student> students = new ArrayList<>(List.of(
        new Student(1, "Joe", 60),
        new Student(2, "Kevin", 65)
    ));

    // Endpoint to retrieve the list of students.
    @GetMapping("/students") // Maps GET requests to "/students".
    public List<Student> getStudents() {
        return students; // Returns the current list of students as a JSON response.
    }

    // Endpoint to retrieve the CSRF token.
    @GetMapping("/csrf-token") // Maps GET requests to "/csrf-token".
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        // Accesses the CSRF token from the HttpServletRequest attributes.
        return (CsrfToken) request.getAttribute("_csrf");
    }

    // Endpoint to add a student to the list.
    @PostMapping("/students") // Maps POST requests to "/students".
    public Student addStudent(@RequestBody Student student) {
        // Deserializes the JSON request body into a Student object and adds it to the list.
        students.add(student);
        return student;
    }
	
}
