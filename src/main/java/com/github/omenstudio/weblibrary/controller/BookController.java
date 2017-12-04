package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.weblibrary.annotation.HydraGetRequest;
import com.github.omenstudio.weblibrary.entity.Book;
import com.github.omenstudio.weblibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books/")
public class BookController {

    @Autowired
    BookRepository bookRepository;


    @HydraGetRequest
    public Object getBooks() {
        return bookRepository.findAll();
    }

    @HydraGetRequest(path = "/{bookId}")
    public Object getBook(@PathVariable Long bookId) {
        return bookRepository.findOne(bookId);
    }



}
