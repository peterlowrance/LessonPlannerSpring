package com.example.project;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping()
public @interface ProjectDataController {
    /**
     * Used as alias for @RequestMapping.
     */
    @AliasFor(annotation = RequestMapping.class)
    String value() default "";

    /**
     * Overrides for produced content type.
     *
     * @NOT IMPLMENTED
     */
    String produces() default "application/json";

    /**
     * Overrides for expected content type.
     *
     * @NOT IMPLMENTED
     */
    String consumes() default "application/json";
}