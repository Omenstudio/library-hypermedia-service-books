package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.weblibrary.annotation.HydraGet;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/")
public class EntryPointController {

    @HydraGet
    public Object getEntryPoint(HttpServletRequest request) {
        JsonObject res = new JsonObject();
        res.addProperty("@context", "/api/contexts/EntryPoint");
        res.addProperty("@id", "/api/");
        res.addProperty("@type", "EntryPoint");
        res.addProperty("books", "/api/books/");

        return res;
    }
}
