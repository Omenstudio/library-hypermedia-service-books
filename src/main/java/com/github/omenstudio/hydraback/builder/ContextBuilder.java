package com.github.omenstudio.hydraback.builder;

import com.github.omenstudio.hydraback.annotation.HydraType;
import com.github.omenstudio.hydraback.utils.HydraUrlResolver;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public class ContextBuilder {

    private final static Logger logger = LoggerFactory.getLogger(ContextBuilder.class);

    // doc this
    public static JsonObject buildForEntryPoint(String... links) {
        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("EntryPoint", "vocab:EntryPoint");

        for (String link : links) {
            JsonObject linkObject = new JsonObject();
            linkObject.addProperty("@id", "vocab:EntryPoint/" + link);
            linkObject.addProperty("@type", "@id");
            resultJson.add(link, linkObject);
        }

        return wrapContext(resultJson);
    }

    // doc this
    public static JsonObject buildForCollection(Class collectionItemClass) {
        JsonObject resultJson = new JsonObject();
        resultJson.addProperty(
                collectionItemClass.getSimpleName() + "Collection",
                "vocab:"+collectionItemClass.getSimpleName()+"Collection"
        );
        resultJson.addProperty("members","http://www.w3.org/ns/hydra/core#member");

        return wrapContext(resultJson);
    }

    // doc this
    public static JsonObject buildForClass(Class beanClass) {
        JsonObject resultJson = new JsonObject();

        // At first we write info about class
        resultJson = buildInfoAboutEntity(beanClass, resultJson);

        // Then we write info about each field
        resultJson = buildInfoAboutFields(beanClass, resultJson);

        // and in the end add some preparations
        return wrapContext(resultJson);
    }

    // doc this
    private static JsonObject buildInfoAboutEntity(Class beanClass, JsonObject jsonObject) {
        Annotation hydraTypeAnnotation = beanClass.getDeclaredAnnotation(HydraType.class);

        if (hydraTypeAnnotation == null) {
            logger.error("Can't build context for class " + beanClass.toString() + ". " +
                    "There are no @HydraType annotation on class");
            return jsonObject;
        }

        String[] values = ((HydraType) hydraTypeAnnotation).value();

        if (values.length != 1) {
            logger.error("Can't build context for class " + beanClass.toString() + ". " +
                    "Illegal @HydraType annotation arguments");
            return jsonObject;
        }

        jsonObject.addProperty(beanClass.getSimpleName(), values[0]);
        return jsonObject;
    }

    // doc this
    private static JsonObject buildInfoAboutFields(Class beanClass, JsonObject jsonObject) throws NullPointerException {
        for (Field field : beanClass.getDeclaredFields()) {
            Annotation hydraTypeAnnotation = field.getDeclaredAnnotation(HydraType.class);

            // Build context inly for those fields, which have annotation @HydraType
            if (hydraTypeAnnotation == null) {
                continue;
            }

            String[] values = ((HydraType) hydraTypeAnnotation).value();
            String[] keys = ((HydraType) hydraTypeAnnotation).keys();

            // If annotation has been used with different keys/values -> skip
            if (values.length == 0 || values.length > 1 && values.length != keys.length) {
                logger.warn("Can't build context for field " + field.getName() +
                        " of class " + beanClass.toString() + ". " +
                        "Illegal @HydraType annotation arguments");
                continue;
            }

            // If values[] has 1 value, but keys hasn't => key is field name
            if (keys.length == 0) {
                jsonObject.addProperty(field.getName(), values[0]);
            }
            // Elsewhere build additional inner json object
            else {
                JsonObject innerObject = new JsonObject();
                for (int i = 0; i < keys.length; i++) {
                    innerObject.addProperty(keys[i], values[i]);
                }
                jsonObject.add(field.getName(), innerObject);
            }

        }


        return jsonObject;
    }


    private static JsonObject wrapContext(JsonObject contextInfo) {
        contextInfo.addProperty("hydra", "http://www.w3.org/ns/hydra/core#");
        contextInfo.addProperty("vocab", HydraUrlResolver.getVocabPath() + "#");

        JsonObject contextWrapper = new JsonObject();
        contextWrapper.add("@context", contextInfo);
        return contextWrapper;
    }
}
