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
@RequestMapping("/api/contexts/")
public class ContextController {


    @CrossOrigin
    @GetMapping(value = "EntryPoint", produces = "application/ld+json")
    public ResponseEntity<String> getEntryPointContext(HttpServletRequest request) {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("hydra", "http://www.w3.org/ns/hydra/core#");
        innerObject.addProperty("vocab", "http://localhost:8080/api/vocab#");
        innerObject.addProperty("EntryPoint", "vocab:EntryPoint");

        JsonObject events = new JsonObject();
        events.addProperty("@id", "vocab:EntryPoint/events");
        events.addProperty("@type", "@id");
        innerObject.add("events", events);

        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "Link")
                .header("Link", "<http://localhost:8080/api/vocab>; rel=\"http://www.w3.org/ns/hydra/core#apiDocumentation\"")
                .body(wrapWithContext(innerObject).toString());
    }

    private JsonObject wrapWithContext(JsonObject innerObject) {
        JsonObject contextWrapper = new JsonObject();
        contextWrapper.add("@context", innerObject);
        return contextWrapper;
    }
}
