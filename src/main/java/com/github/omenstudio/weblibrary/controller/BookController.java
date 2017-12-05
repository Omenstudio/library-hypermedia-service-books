package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.hydraback.annotation.request.HydraDeleteRequest;
import com.github.omenstudio.hydraback.annotation.request.HydraGetRequest;
import com.github.omenstudio.hydraback.annotation.request.HydraPostRequest;
import com.github.omenstudio.hydraback.annotation.request.HydraPutRequest;
import com.github.omenstudio.weblibrary.entity.Book;
import com.github.omenstudio.weblibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @HydraGetRequest
    public Object getBooks() {
        return bookRepository.findAll();
    }

    @HydraPostRequest
    public Object createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @HydraGetRequest("/{bookId}")
    public Object getBook(@PathVariable Long bookId) {
        return bookRepository.findOne(bookId);
    }

    @HydraPutRequest("/{bookId}")
    public Object changeBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return bookRepository.save(book);
    }

    @HydraDeleteRequest("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookRepository.delete(bookId);
    }



}
