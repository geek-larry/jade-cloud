package com.jade.elasticsearchdb.annotation;

import java.lang.annotation.*;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface ESFieldAnnotation {
    String value() default "";

    String type() default "string";
}
