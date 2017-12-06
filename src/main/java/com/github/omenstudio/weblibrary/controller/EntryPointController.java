package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.hydraback.annotation.request.HydraGetRequest;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class EntryPointController {

    @HydraGetRequest
    public Object getEntryPoint() {
        JsonObject res = new JsonObject();
        res.addProperty("@context", "/api/contexts/EntryPoint");
        res.addProperty("@id", "/api/");
        res.addProperty("@type", "EntryPoint");
        res.addProperty("books", "/api/books/");
        res.addProperty("authors", "/api/authors/");
        res.addProperty("publishers", "/api/publishers/");

        return res;
    }
}
