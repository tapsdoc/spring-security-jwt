package com.example.Spring.SecurityAlibou.repository;

import com.example.Spring.SecurityAlibou.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
