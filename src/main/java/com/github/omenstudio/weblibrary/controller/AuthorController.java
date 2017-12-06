package com.github.omenstudio.weblibrary.controller;


import com.github.omenstudio.hydraback.annotation.request.HydraDeleteRequest;
import com.github.omenstudio.hydraback.annotation.request.HydraGetRequest;
import com.github.omenstudio.hydraback.annotation.request.HydraPostRequest;
import com.github.omenstudio.hydraback.annotation.request.HydraPutRequest;
import com.github.omenstudio.weblibrary.entity.Author;
import com.github.omenstudio.weblibrary.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @HydraGetRequest
    public Object getAuthors() {
        return authorRepository.findAll();
    }

    @HydraPostRequest
    public Object createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @HydraGetRequest("/{authorId}")
    public Object getAuthor(@PathVariable Long authorId) {
        return authorRepository.findOne(authorId);
    }

    @HydraPutRequest("/{authorId}")
    public Object changeAuthor(@PathVariable Long authorId, @RequestBody Author author) {
        author.setId(authorId);
        return authorRepository.save(author);
    }

    @HydraDeleteRequest("/{authorId}")
    public void deleteAuthor(@PathVariable Long authorId) {
        authorRepository.delete(authorId);
    }
    
}
