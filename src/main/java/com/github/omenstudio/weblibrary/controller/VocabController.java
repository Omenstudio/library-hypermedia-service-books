package com.github.omenstudio.weblibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @CrossOrigin
    @GetMapping(produces = "application/ld+json")
    public ResponseEntity<String> getApiDocumentation(HttpServletRequest request) {
        if (apiDoc == null) {
            try {
                apiDoc = new String(Files.readAllBytes(
                        Paths.get(ClassLoader.getSystemResource("public/doc.json").toURI())
                )).replaceAll("DOC_URL", "http://localhost:8080/api/vocab");
            } catch (IOException|URISyntaxException e) {
                e.printStackTrace();
                throw new RuntimeException("Cannot read API DOC file");
            }
        }

        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "Link")
                .header("Link", "<http://localhost:8080/api/vocab>; rel=\"http://www.w3.org/ns/hydra/core#apiDocumentation\"")
                .body(apiDoc);
    }

}
