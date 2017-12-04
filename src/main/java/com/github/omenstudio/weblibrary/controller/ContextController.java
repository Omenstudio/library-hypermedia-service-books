package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.hydraback.ContextBuilder;
import com.github.omenstudio.hydraback.annotation.HydraGetRequest;
import com.github.omenstudio.weblibrary.entity.Author;
import com.github.omenstudio.weblibrary.entity.Book;
import com.github.omenstudio.weblibrary.entity.Publisher;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/contexts/")
public class ContextController {


    @HydraGetRequest("EntryPoint")
    public Object getEntryPointContext() {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("EntryPoint", "vocab:EntryPoint");

        JsonObject books = new JsonObject();
        books.addProperty("@id", "vocab:EntryPoint/books");
        books.addProperty("@type", "@id");
        innerObject.add("books", books);

        return ContextBuilder.buildForEntryPoint(innerObject);
    }

    @HydraGetRequest("Book")
    public Object getBookContext() {
        return ContextBuilder.buildForClass(Book.class);
    }

    @HydraGetRequest("Author")
    public Object getAuthorContext() {
        return ContextBuilder.buildForClass(Author.class);
    }

    @HydraGetRequest("Publisher")
    public Object getPublisherContext() {
        return ContextBuilder.buildForClass(Publisher.class);
    }

}
