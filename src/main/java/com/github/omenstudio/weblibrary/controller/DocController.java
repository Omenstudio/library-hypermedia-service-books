package com.github.omenstudio.weblibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;


@RestController
@RequestMapping("/doc")
public class DocController {

    private String apiDoc;

    @GetMapping
    public ResponseEntity<String> getApiDocumentation(HttpServletRequest request) {
        if (apiDoc == null) {
            try {
                apiDoc = new String(Files.readAllBytes(
                        Paths.get(ClassLoader.getSystemResource("public/doc.json").toURI())
                )).replaceAll("DOC_URL", "/doc");
            } catch (IOException|URISyntaxException e) {
                e.printStackTrace();
                throw new RuntimeException("Cannot read API DOC file");
            }
        }

        return new ResponseEntity<>(apiDoc, HttpStatus.OK);
    }

}
