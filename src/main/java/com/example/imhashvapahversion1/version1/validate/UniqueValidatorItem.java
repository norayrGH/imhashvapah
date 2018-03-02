package com.example.imhashvapahversion1.version1.validate;


import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface UniqueValidatorItem {
    String message() default "";
    String field() default "";
    String query() default "";
}
