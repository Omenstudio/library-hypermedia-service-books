package com.github.omenstudio.hydraback.builder;

import com.github.omenstudio.hydraback.utils.HydraUrlResolver;
import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class VocabBuilder {
    private static String apiDoc = null;


    public static String buildVocabulary() {
        if (apiDoc == null) {
            readMainVocab();
        }

        return apiDoc;
    }


    private static void readMainVocab() {
        String readedData = readFileContent("public/vocab/vocab.json")
                .replaceAll("API_ADDR", HydraUrlResolver.getApiAddress())
                .replaceAll("VOCAB_ADDR", HydraUrlResolver.getVocabAddress());

        final JsonParser parser = new JsonParser();

        JsonObject resultJson = parser.parse(readedData).getAsJsonObject();
        JsonArray classes = resultJson.getAsJsonArray("supportedClass");

        Arrays.stream(findFilesInDir("public/vocab/"))
                .filter(e -> !e.getName().equals("vocab.json"))
                .map(e -> VocabBuilder.readFileContent(e.toPath()))
                .map(parser::parse)
                .forEach(classes::add);

        apiDoc = resultJson.toString();
    }


    private static String readFileContent(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String readFileContent(File file) {
        return readFileContent(file.toPath());
    }


    private static String readFileContent(String resourcePath) {
        try {
            Path path = Paths.get(ClassLoader.getSystemResource(resourcePath).toURI());
            return readFileContent(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "";
    }


    private static File[] findFilesInDir(String resourceDir) {
        try {
            return Paths.get(ClassLoader.getSystemResource(resourceDir).toURI()).toFile().listFiles();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new File[0];
    }


}
