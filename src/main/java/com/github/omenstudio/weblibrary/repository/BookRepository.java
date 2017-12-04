package com.github.omenstudio.weblibrary.repository;

import com.github.omenstudio.weblibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
