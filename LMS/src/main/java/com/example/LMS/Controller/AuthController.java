package com.example.LMS.Controller;

import com.example.LMS.Entity.Admin;
import com.example.LMS.Entity.Faculty;
import com.example.LMS.Entity.Student;
import com.example.LMS.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/signup/student")
    public String signupStudent(@RequestBody Student student)
    {
        return authService.signupStudent(student);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/signup/faculty")
    public String signupFaculty(@RequestBody Faculty faculty)
    {
        return authService.signupFaculty(faculty);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/signup/admin")
    public String signupAdmin(@RequestBody Admin admin)
    {
        return authService.signupAdmin(admin);
    }


// login api's

    //student login
    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/login/student")
    public ResponseEntity<String> loginStudent(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        return authService.loginStudent(email, password);
    }

    // Faculty Login
    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/login/faculty")
    public ResponseEntity<String> loginFaculty(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        return authService.loginFaculty(email, password);
    }

    // Admin Login
    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/login/admin")
    public ResponseEntity<String> loginAdmin(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        return authService.loginAdmin(email, password);
    }
}
