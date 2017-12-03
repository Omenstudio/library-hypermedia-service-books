package com.github.omenstudio.weblibrary.aspect;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.omenstudio.weblibrary.AppConfig;
import com.github.omenstudio.weblibrary.annotation.HydraEntity;
import com.google.gson.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.xml.ws.Response;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;


@Aspect
@Component
public class HydraControllerInterceptor {

    private static final Logger log = LoggerFactory.getLogger("application");

    private static final Gson gsonBuilder = new GsonBuilder().create();

    private static final JsonParser gsonParser = new JsonParser();

    @Pointcut("@annotation(com.github.omenstudio.weblibrary.annotation.HydraGet)")
    public void hydraGet() {
    }


    @Around("hydraGet()")
    public Object logHydraGet(ProceedingJoinPoint thisJoinPoint) {
        String methodName = thisJoinPoint.getSignature().getName();

        Object objectFromController = null;
        try {
            objectFromController = thisJoinPoint.proceed();
            if (objectFromController == null)
                throw new NullPointerException("object returned from controller method ("+methodName+") is null");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ResponseEntity.badRequest();
        }

        String response = null;
        Optional<Annotation> hydraAnnotation = Arrays.stream(objectFromController.getClass().getDeclaredAnnotations())
                .filter(e -> e instanceof HydraEntity).findFirst();

        if (hydraAnnotation.isPresent()) {
            HydraEntity hydraEntity = ((HydraEntity) hydraAnnotation.get());

            String className = objectFromController.getClass().getSimpleName();
            long objectId = 0;
            try {
                Field field = objectFromController.getClass().getDeclaredField("id");
                field.setAccessible(true);
                objectId = ((long) field.get(objectFromController));
            } catch (NoSuchFieldException|IllegalAccessException|NullPointerException e) {
                e.printStackTrace();
            }

            JsonObject resultJson = gsonParser.parse(gsonBuilder.toJson(objectFromController)).getAsJsonObject();
            resultJson.addProperty("@id", "/api" + hydraEntity.pathId() + objectId);
            resultJson.addProperty("@context", "/api/contexts/" + className);
            resultJson.addProperty("@type", className);

            response = resultJson.toString();
        }
        else {
            response = objectFromController.toString();
        }



        Annotation[] annotations = objectFromController.getClass().getDeclaredAnnotations();

        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "Link")
                .header("Link", "<http://localhost:8080/api/vocab>; rel=\"http://www.w3.org/ns/hydra/core#apiDocumentation\"")
                .body(response);
    }


}