package com.github.omenstudio.hydraback.utils;

import com.github.omenstudio.hydraback.annotation.JsonExclude;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class AnnotationJsonExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(JsonExclude.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}