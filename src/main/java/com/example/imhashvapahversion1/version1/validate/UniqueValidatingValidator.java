package com.example.imhashvapahversion1.version1.validate;

import com.example.imhashvapahversion1.version1.repository.UniversalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class UniqueValidatingValidator implements ConstraintValidator<UniqueValidator, Object> {

    @Autowired
    EntityManager entityManager;
    @Autowired
    UniversalRepository universalRepository;
    private UniqueValidator constraintAnnotation;


    @Override
    public void initialize(UniqueValidator ca) {
        constraintAnnotation = ca;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isValid = true;
        Class aClass = value.getClass();


        if (entityManager == null) {

            for (UniqueValidatorItem item : constraintAnnotation.items()) {
                try {

                    Field field = aClass.getField(item.field());
                    Object fieldValue = field.get(value);

                    isValid = isValid && entityManager
                            .createQuery(item.query(), Long.class)
                            .setParameter("UniqueValidatorParameter", fieldValue)
                            .getSingleResult() == 0;
                } catch (Exception e) {
                    e.printStackTrace();
                    isValid = false;
                }


                return isValid;
            }
        }


            /*for (UniqueValidatorItem item : constraintAnnotation.items()) {
                try {
                    Field field = aClass.getField(item.field());
                    Field fieldId = aClass.getField("id");
                    Object fieldValue = field.get(value);
                    Object idValue = fieldId.get(value);

                    isValid = isValid && entityManager
                            .createQuery(item.query(), Long.class)
                            .setParameter("UniqueValidatorParameter", fieldValue)
                            .setParameter("entityID", idValue)
                            .getSingleResult() == 0;

               *//* if(idValue == null && fieldValue != null) {

                    return false;
                }*//*
                } catch (Exception e) {
                    e.printStackTrace();
                    isValid = false;
                }

            }*/

            return isValid;
        }
    }
