package com.example.imhashvapahversion1.version1.validate.test;

import com.example.imhashvapahversion1.version1.Entity.partners.Customers.CompanyCustomer;
import com.example.imhashvapahversion1.version1.repository.ClientOrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.CompanyCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class EqualFieldsValidator implements ConstraintValidator<EqualFields, Object> {
 
    private String id;
    private String uniqueField;
    @Autowired
    private ClientOrganizationRepository clientOrganizationRepository;
    @Autowired
    private CompanyCustomerRepository companyCustomerRepository;
    @Override
    public void initialize(EqualFields constraint) {
        id = constraint.id();
         uniqueField = constraint.uniqueField();
    }
 
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {

            Object matchId = getFieldValue(object, id);
            Object matchuniqueField = getFieldValue(object, uniqueField);

            if (clientOrganizationRepository.existsByName( (String) matchuniqueField ) == null )
                return true;
            else
                 return false;
                /*return baseFieldValue != null && baseFieldValue.equals(matchFieldValue);*/

        } catch (Exception e) {
            // log error
            return false;
        }
    }
 
    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
 
}