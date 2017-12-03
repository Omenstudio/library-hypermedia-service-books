package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.weblibrary.AppConfig;
import com.github.omenstudio.weblibrary.annotation.HydraGet;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/contexts/")
public class ContextController {


    @HydraGet(path = "EntryPoint")
    public Object getEntryPointContext(HttpServletRequest request) {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("hydra", "http://www.w3.org/ns/hydra/core#");
        innerObject.addProperty("vocab", AppConfig.HTTP_ADDRESS + "/api/vocab#");
        innerObject.addProperty("EntryPoint", "vocab:EntryPoint");

        JsonObject events = new JsonObject();
        events.addProperty("@id", "vocab:EntryPoint/events");
        events.addProperty("@type", "@id");
        innerObject.add("events", events);

        return wrapWithContext(innerObject);
    }

    private JsonObject wrapWithContext(JsonObject innerObject) {
        JsonObject contextWrapper = new JsonObject();
        contextWrapper.add("@context", innerObject);
        return contextWrapper;
    }
}
