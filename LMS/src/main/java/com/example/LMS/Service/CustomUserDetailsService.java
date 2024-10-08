package com.example.LMS.Service;

import com.example.LMS.Entity.Admin;
import com.example.LMS.Entity.Faculty;
import com.example.LMS.Entity.Student;
import com.example.LMS.Repository.AdminRepository;
import com.example.LMS.Repository.FacultyRepository;
import com.example.LMS.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Check if the user is a Student
        Student student = studentRepository.findByEmail(email).orElse(null);
        if (student != null) {
            return User.withUsername(student.getEmail())
                    .password(student.getPassword())
                    .roles("STUDENT")
                    .build();
        }

        // Check if the user is a Faculty
        Faculty faculty = facultyRepository.findByEmail(email).orElse(null);
        if (faculty != null) {
            return User.withUsername(faculty.getEmail())
                    .password(faculty.getPassword())
                    .roles("FACULTY")
                    .build();
        }

        // Check if the user is an Admin
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin != null) {
            return User.withUsername(admin.getEmail())
                    .password(admin.getPassword())
                    .roles("ADMIN")
                    .build();
        }

        // If user not found in any role
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
