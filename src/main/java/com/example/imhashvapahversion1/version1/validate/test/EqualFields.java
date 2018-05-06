package com.example.imhashvapahversion1.version1.validate.test;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EqualFieldsValidator.class})
public @interface EqualFields {
 
    String message() default "WTF!!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
 
    String id();
 
    String uniqueField();

    String hvhh();
 
}