package com.example.Spring.SecurityAlibou.repository;

import com.example.Spring.SecurityAlibou.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
