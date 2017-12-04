package com.github.omenstudio.weblibrary.aspect;

import com.github.omenstudio.weblibrary.annotation.HydraEntity;
import com.google.gson.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;


@Aspect
@Component
public class HydraResponseBuilder {

    private static final Gson gsonBuilder = new GsonBuilder().create();

    private static final JsonParser gsonParser = new JsonParser();

    @Pointcut("@annotation(com.github.omenstudio.weblibrary.annotation.HydraGetRequest)")
    public void hydraGet() {
    }


    @Around("hydraGet()")
    public Object logHydraGet(ProceedingJoinPoint thisJoinPoint) {
        String methodName = thisJoinPoint.getSignature().getName();

        Object objectFromController = null;
        try {
            objectFromController = thisJoinPoint.proceed();
            if (objectFromController == null)
                throw new NullPointerException("object returned from controller method (" + methodName + ") is null");
        } catch (Throwable throwable) {
            return ResponseEntity.notFound();
        }

        String response = buildResponse(objectFromController);

        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "Link")
                .header("Link", "<http://localhost:8080/api/vocab>; rel=\"http://www.w3.org/ns/hydra/core#apiDocumentation\"")
                .body(response);
    }

    /**
     * Transforms what we get from Web MVC controller
     * to JSON-LD format with additional properties
     *
     * @param objectFromController - object returned by Web MVC controller
     * @return response body representation in JSON-LD
     *
     * @see #serializeCollection
     * @see #serializeEntityFully
     */
    private String buildResponse(Object objectFromController) {
        if (objectFromController instanceof Collection) {
            return serializeCollection(((Collection) objectFromController));
        }

        String entitySerialized = serializeEntityFully(objectFromController);
        if (entitySerialized != null) {
            return entitySerialized;
        }

        return objectFromController.toString();
    }


    /**
     * Serialize collection of entities returned by Web MVC Controller
     *
     * @param entityCollection
     * @return
     */
    private String serializeCollection(Collection entityCollection) {
        if (entityCollection.isEmpty()) {
            return "[]";
        }

        JsonObject resultJson = new JsonObject();
        JsonArray membersJson = new JsonArray();
        String itemPathId = null,
                itemType = null;
        Iterator it = entityCollection.iterator();
        while (it.hasNext()) {
            Object obj = it.next();

            if (itemPathId == null) {
                String className = obj.getClass().getSimpleName();
                itemPathId = "/api/" + className.toLowerCase() + "s/";
                itemType = "http://schema.org/" + className;

                resultJson.addProperty("@id", itemPathId );
                resultJson.addProperty("@context", "/api/contexts/" + className + "Collection");
                resultJson.addProperty("@type", className + "Collection");
            }

            long id = 0;
            try {
                Field idField = obj.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                id = ((long) idField.get(obj));
            } catch (IllegalAccessException | NoSuchFieldException | ClassCastException e) {
                e.printStackTrace();
            }

            JsonObject itemJsonObject = new JsonObject();
            itemJsonObject.addProperty("@id", itemPathId + Long.toString(id));
            itemJsonObject.addProperty("@type", itemType);
            membersJson.add(itemJsonObject);
        }

        resultJson.add("members", membersJson);

        return resultJson.toString();
    }




    /**
     * <p>
     * Serializes single entity. Add properties @id, @context, @type,
     * which calculated based on class name.
     *
     * <p>
     * For example:
     * <pre>
     * {
     *     "@context": "/hydra/event-api/contexts/Event.jsonld",
     *     "@id": "/hydra/event-api/events/117",
     *     "@type": "Event",
     *     "name": "HOST",
     *     "description": "the host",
     *     "start_date": "2017-11-18T13:58:11Z",
     *     "end_date": "2017-11-18T14:58:11Z"
     * }
     * </pre>
     *
     * @param entityObject Potential entity object, which can be presented in JSON-LD format
     * @return null if object is not HydraEntity, String representing json-ld object representation elsewhere
     *
     * @see HydraEntity
     */
    private String serializeEntityFully(Object entityObject) {
        Annotation hydraAnnotation = Arrays.stream(entityObject.getClass().getDeclaredAnnotations())
                .filter(e -> e instanceof HydraEntity).findFirst().orElse(null);

        if (hydraAnnotation == null)
            return null;

        String className = entityObject.getClass().getSimpleName();
        long objectId = 0;
        try {
            Field field = entityObject.getClass().getDeclaredField("id");
            field.setAccessible(true);
            objectId = ((long) field.get(entityObject));
        } catch (NoSuchFieldException | IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        }

        JsonObject resultJson = gsonParser.parse(gsonBuilder.toJson(entityObject)).getAsJsonObject();
        resultJson.addProperty("@id", "/api/" + className.toLowerCase() + "s/" + objectId);
        resultJson.addProperty("@context", "/api/contexts/" + className);
        resultJson.addProperty("@type", className);

        return resultJson.toString();
    }

}