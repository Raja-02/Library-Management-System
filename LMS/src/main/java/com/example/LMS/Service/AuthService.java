package com.example.LMS.Service;

import com.example.LMS.Entity.Admin;
import com.example.LMS.Entity.Faculty;
import com.example.LMS.Entity.Student;
import com.example.LMS.Repository.AdminRepository;
import com.example.LMS.Repository.FacultyRepository;
import com.example.LMS.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceImpl{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String signupStudent(Student student) {
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            // If the email already exists, return a user-friendly message
            return "User exists, please login.";
        }
        // Encrypt the password and save the new student
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentRepository.save(student);
        return "Student registered successfully!";
    }

    @Override
    public String signupFaculty(Faculty faculty) {

        if (facultyRepository.findByEmail(faculty.getEmail()).isPresent()) {
            return "User exists, please login.";
        }
        faculty.setPassword(passwordEncoder.encode(faculty.getPassword()));
        facultyRepository.save(faculty);
        return "Faculty registered successfully!";
    }

    @Override
    public String signupAdmin(Admin admin) {
        if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
            return "User exists, please login.";
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
        return "Faculty registered successfully!";
    }


    public ResponseEntity<String> loginStudent(String email, String password) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        if (passwordEncoder.matches(password, student.getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    // Login method for Faculty
    public ResponseEntity<String> loginFaculty(String email, String password) {
        Faculty faculty = facultyRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        if (passwordEncoder.matches(password, faculty.getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    // Login method for Admin
    public ResponseEntity<String> loginAdmin(String email, String password) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        if (passwordEncoder.matches(password, admin.getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

}
