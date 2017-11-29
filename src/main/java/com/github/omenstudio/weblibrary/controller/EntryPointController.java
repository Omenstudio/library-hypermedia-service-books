package com.github.omenstudio.weblibrary.controller;

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

    @CrossOrigin
    @GetMapping(produces = "application/ld+json")
    public ResponseEntity<String> getEntryPoint(HttpServletRequest request) {
        JsonObject res = new JsonObject();
        res.addProperty("@context", "/api/contexts/EntryPoint.jsonld");
        res.addProperty("@id", "/api/");
        res.addProperty("@type", "EntryPoint");
        res.addProperty("events", "/api/events/");

        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "Link")
                .header("Link", "<http://localhost:8080/api/vocab>; rel=\"http://www.w3.org/ns/hydra/core#apiDocumentation\"")
                .body(res.toString());
    }
}
