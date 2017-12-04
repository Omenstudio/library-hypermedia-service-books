package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.weblibrary.annotation.HydraGetRequest;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/")
public class EntryPointController {

    @HydraGetRequest
    public Object getEntryPoint(HttpServletRequest request) {
        JsonObject res = new JsonObject();
        res.addProperty("@context", "/api/contexts/EntryPoint");
        res.addProperty("@id", "/api/");
        res.addProperty("@type", "EntryPoint");
        res.addProperty("books", "/api/books/");

        return res;
    }
}
