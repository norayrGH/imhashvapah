package com.example.imhashvapahversion1.version1.validate.test;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.ClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.CompanyCustomer;
import com.example.imhashvapahversion1.version1.repository.ClientOrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.CompanyCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class EqualFieldsValidator implements ConstraintValidator<EqualFields, Object> {
    private static Boolean temp = false ;
    private static Long ParrentId ;
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
            ClientOrganization clientOrganization = clientOrganizationRepository.getByName( ( String ) matchuniqueField );



            if( matchId != null && matchuniqueField instanceof ClientOrganization  ){


            }
            if (temp == true) {
                temp = false;

                setParrentId(null);
                return true;
            }
            if (matchuniqueField instanceof ClientOrganization) {
                setParrentId((Long) matchId);
                temp = true;
                return true;
            }


            /*return baseFieldValue != null && baseFieldValue.equals(matchFieldValue);*/

        } catch (Exception e) {
            // log error
            return false;
        }
        return false;
    }
 
    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    public static Long getParrentId() {
        return ParrentId;
    }

    public static void setParrentId(Long parrentId) {
        ParrentId = parrentId;
    }
}