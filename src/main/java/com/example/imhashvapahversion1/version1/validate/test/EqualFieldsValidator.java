package com.example.imhashvapahversion1.version1.validate.test;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.ClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.CompanyCustomer;
import com.example.imhashvapahversion1.version1.config.ApplicationContextProvider;
import com.example.imhashvapahversion1.version1.repository.ClientOrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.CompanyCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class EqualFieldsValidator implements ConstraintValidator<EqualFields, Object> {
    // private static Boolean temp = false ;
    private static Long parrentId = null;
    private static short validCount = 0;
    /* private static short firstInitial = 0;*/
    private String id;
    private String uniqueField;
    private ClientOrganization clientOrganization = null;
    private Object matchId;
    private Object matchuniqueField;
    private Boolean existByName;
    private Boolean existByid;

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
            matchId = getFieldValue(object, id);
            matchuniqueField = getFieldValue(object, uniqueField);

             if (!(matchuniqueField instanceof String)) {
                parrentId = (Long) matchId;
            }
        } catch (Exception e) {
            return true;
        }
        if (matchuniqueField instanceof String) {
            if (clientOrganizationRepository.existsByName((String) matchuniqueField)) {
                clientOrganization = clientOrganizationRepository.getByName((String) matchuniqueField);
                if (companyCustomerRepository.existsById(parrentId, clientOrganization.getId())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }

        }
        return true;
    }






    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }


}