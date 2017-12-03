package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.weblibrary.AppConfig;
import com.github.omenstudio.weblibrary.annotation.HydraGet;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/contexts/")
public class ContextController {


    @HydraGet(path = "EntryPoint")
    public Object getEntryPointContext(HttpServletRequest request) {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("EntryPoint", "vocab:EntryPoint");

        JsonObject books = new JsonObject();
        books.addProperty("@id", "vocab:EntryPoint/books");
        books.addProperty("@type", "@id");
        innerObject.add("books", books);

        return wrapContextInfo(innerObject);
    }

    @HydraGet(path = "Book")
    public Object getBookContext(HttpServletRequest request) {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("Book", "http://schema.org/Event");
        innerObject.addProperty("title", "http://schema.org/description");

        return wrapContextInfo(innerObject);
    }







    private JsonObject wrapContextInfo(JsonObject contextInfo) {
        contextInfo.addProperty("hydra", "http://www.w3.org/ns/hydra/core#");
        contextInfo.addProperty("vocab", AppConfig.HTTP_ADDRESS + "/api/vocab#");

        JsonObject contextWrapper = new JsonObject();
        contextWrapper.add("@context", contextInfo);
        return contextWrapper;
    }
}
