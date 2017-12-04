package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.weblibrary.AppConfig;
import com.github.omenstudio.weblibrary.annotation.HydraGetRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;


@RestController
@RequestMapping("/api/vocab")
public class VocabController {

    private String apiDoc;

    @HydraGetRequest
    public Object getApiDocumentation(HttpServletRequest request) {
        if (apiDoc == null) {
            try {
                apiDoc = new String(Files.readAllBytes(
                        Paths.get(ClassLoader.getSystemResource("public/vocab.json").toURI())
                )).replaceAll("APP_ADDR", AppConfig.HTTP_ADDRESS);
            } catch (IOException|URISyntaxException e) {
                e.printStackTrace();
                throw new RuntimeException("Cannot read API DOC file");
            }
        }

        return apiDoc;
    }

}
