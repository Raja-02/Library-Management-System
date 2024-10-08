package com.example.LMS.Service;

import com.example.LMS.Entity.Admin;
import com.example.LMS.Entity.Faculty;
import com.example.LMS.Entity.Student;
import org.springframework.http.ResponseEntity;

public interface AuthServiceImpl {
    String signupStudent(Student student);
    String signupFaculty(Faculty faculty);

    String signupAdmin(Admin admin);
    ResponseEntity<String> loginStudent(String email, String password);

    ResponseEntity<String> loginFaculty(String email, String password);

    ResponseEntity<String> loginAdmin(String email, String password);

}
