package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.hydraback.annotation.request.HydraDeleteRequest;
import com.github.omenstudio.hydraback.annotation.request.HydraGetRequest;
import com.github.omenstudio.hydraback.annotation.request.HydraPostRequest;
import com.github.omenstudio.hydraback.annotation.request.HydraPutRequest;
import com.github.omenstudio.weblibrary.entity.Book;
import com.github.omenstudio.weblibrary.repository.AuthorRepository;
import com.github.omenstudio.weblibrary.repository.BookRepository;
import com.github.omenstudio.weblibrary.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    AuthorRepository authorRepository;

    @HydraGetRequest
    public Object getBooks() {
        return bookRepository.findAll();
    }

    @HydraPostRequest
    public Object createBook(@RequestBody Book book,
                             @RequestParam(required = false) String author,
                             @RequestParam(required = false) String publisher) {
        return bookRepository.save(book);
    }

    @HydraGetRequest("/{bookId}")
    public Object getBook(@PathVariable Long bookId) {
        return bookRepository.findOne(bookId);
    }

    @HydraPutRequest("/{bookId}")
    public Object changeBook(@PathVariable Long bookId, @RequestBody Book book) {
        book.setId(bookId);
        return bookRepository.save(book);
    }

    @HydraDeleteRequest("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookRepository.delete(bookId);
    }

}
