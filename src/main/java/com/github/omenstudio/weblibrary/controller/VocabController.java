package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.hydraback.annotation.request.HydraGetRequest;
import com.github.omenstudio.hydraback.builder.VocabBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/vocab")
public class VocabController {

    @HydraGetRequest
    public Object getApiDocumentation() {
        return VocabBuilder.buildVocabulary();
    }

}
