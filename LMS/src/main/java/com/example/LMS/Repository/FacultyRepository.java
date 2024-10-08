package com.example.LMS.Repository;

import com.example.LMS.Entity.Faculty;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Optional<Faculty> findByEmail(String email);
}
