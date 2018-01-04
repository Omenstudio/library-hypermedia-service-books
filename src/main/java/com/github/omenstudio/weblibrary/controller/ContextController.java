package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.hydra.annotation.context.HydraContextClass;
import com.github.omenstudio.hydra.annotation.context.HydraContextCollection;
import com.github.omenstudio.hydra.annotation.context.HydraContextEntryPoint;
import com.github.omenstudio.hydra.annotation.request.HydraGetRequest;
import com.github.omenstudio.weblibrary.entity.Author;
import com.github.omenstudio.weblibrary.entity.Book;
import com.github.omenstudio.weblibrary.entity.Publisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/contexts/")
public class ContextController {


    @HydraContextEntryPoint
    @HydraGetRequest("EntryPoint")
    public Object getEntryPointContext() {
        return new String[] { "books", "authors", "publishers" };
    }

    @HydraContextClass
    @HydraGetRequest("Book")
    public Object getBookContext() {
        return Book.class;
    }

    @HydraContextCollection
    @HydraGetRequest("BookCollection")
    public Object getBookCollection() {
        return Book.class;
    }

    @HydraContextClass
    @HydraGetRequest("Author")
    public Object getAuthorContext() {
        return Author.class;
    }

    @HydraContextCollection
    @HydraGetRequest("AuthorCollection")
    public Object getAuthorCollection() {
        return Author.class;
    }

    @HydraContextClass
    @HydraGetRequest("Publisher")
    public Object getPublisherContext() {
        return Publisher.class;
    }

    @HydraContextCollection
    @HydraGetRequest("PublisherCollection")
    public Object getPublisherCollection() {
        return Publisher.class;
    }
}
