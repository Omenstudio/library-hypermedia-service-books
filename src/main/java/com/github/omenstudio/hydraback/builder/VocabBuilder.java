package com.github.omenstudio.hydraback.builder;

import com.github.omenstudio.hydraback.utils.HydraUrlResolver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VocabBuilder {


    public static String buildVocabulary() {
        String apiDoc = "";
        try {
            apiDoc = new String(Files.readAllBytes(
                    Paths.get(ClassLoader.getSystemResource("public/vocab.json").toURI())))
                    .replaceAll("API_ADDR", HydraUrlResolver.getApiAddress())
                    .replaceAll("VOCAB_ADDR", HydraUrlResolver.getVocabAddress());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot read API DOC file");
        }

        return apiDoc;
    }


}
