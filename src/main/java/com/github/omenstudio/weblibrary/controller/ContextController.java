package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.hydraback.annotation.request.HydraGetRequest;
import com.github.omenstudio.hydraback.builder.ContextBuilder;
import com.github.omenstudio.weblibrary.entity.Author;
import com.github.omenstudio.weblibrary.entity.Book;
import com.github.omenstudio.weblibrary.entity.Publisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/contexts")
public class ContextController {


    @HydraGetRequest("EntryPoint")
    public Object getEntryPointContext() {
        return ContextBuilder.buildForEntryPoint("books", "authors", "publishers");
    }

    @HydraGetRequest("Book")
    public Object getBookContext() {
        return ContextBuilder.buildForClass(Book.class);
    }

    @HydraGetRequest("BookCollection")
    public Object getBookCollection() {
        return ContextBuilder.buildForCollection(Book.class);
    }

    @HydraGetRequest("Author")
    public Object getAuthorContext() {
        return ContextBuilder.buildForClass(Author.class);
    }

    @HydraGetRequest("AuthorCollection")
    public Object getAuthorCollection() {
        return ContextBuilder.buildForCollection(Author.class);
    }

    @HydraGetRequest("Publisher")
    public Object getPublisherContext() {
        return ContextBuilder.buildForClass(Publisher.class);
    }

    @HydraGetRequest("PublisherCollection")
    public Object getPublisherCollection() {
        return ContextBuilder.buildForCollection(Publisher.class);
    }
}
