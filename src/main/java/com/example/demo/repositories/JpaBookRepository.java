package com.example.demo.repositories;

import com.example.demo.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookRepository extends JpaRepository<BookEntity, Long> {
    Page<BookEntity> findByName(String name, org.springframework.data.domain.Pageable pageable);
}
