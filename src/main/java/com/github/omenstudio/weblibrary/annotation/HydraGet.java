package com.github.omenstudio.weblibrary.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@CrossOrigin
@RequestMapping(method = RequestMethod.GET, produces = "application/ld+json")
public @interface HydraGet {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
