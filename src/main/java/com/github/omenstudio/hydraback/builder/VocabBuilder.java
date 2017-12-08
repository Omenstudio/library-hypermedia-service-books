package com.github.omenstudio.hydraback.builder;

import com.github.omenstudio.hydraback.utils.HydraUrlResolver;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class VocabBuilder {
    private static String apiDoc = null;

    private static Logger logger = LoggerFactory.getLogger(VocabBuilder.class);

    public static String buildVocabulary() {
        logger.info("#buildVocabulary: started");

        if (apiDoc == null) {
            readMainVocab();
        }

        return apiDoc;
    }


    private static void readMainVocab() {
        logger.info("#readMainVocab: start reading vocab ");



        String readedData = readFileContent(ClassLoader.getSystemResource("public/vocab/vocab.json").getPath())
                .replaceAll("API_ADDR", HydraUrlResolver.getApiAddress())
                .replaceAll("VOCAB_ADDR", HydraUrlResolver.getVocabAddress());

        logger.info("#readMainVocab: readed " + Integer.toString(readedData.length()) + " chars");

        final JsonParser parser = new JsonParser();

        JsonObject resultJson = parser.parse(readedData).getAsJsonObject();
        JsonArray classes = resultJson.getAsJsonArray("supportedClass");

        logger.info("#readMainVocab: vocab parsed as json");

        Arrays.stream(findFilesInDir("public/vocab/"))
                .filter(e -> !e.getName().equals("vocab.json"))
                .map(e -> VocabBuilder.readFileContent(e.toPath()))
                .map(parser::parse)
                .forEach(classes::add);

        logger.info("#readMainVocab: additional files are readed");

        apiDoc = resultJson.toString();
    }


    private static String readFileContent(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("#readFileContent: " + e.toString());
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
