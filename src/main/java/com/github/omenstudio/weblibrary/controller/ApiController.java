package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.weblibrary.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/")
    public Book getBooks() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("test book");

        return book;
    }
}
