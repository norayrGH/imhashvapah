package com.example.imhashvapahversion1.version1.validate.test;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer.CustomerClientOrganization;
import com.example.imhashvapahversion1.version1.repository.customer.CompanyCustomerRepository;
import com.example.imhashvapahversion1.version1.repository.customer.CustomerClientOrganizationRepository;
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
    private CustomerClientOrganization clientOrganization = null;
    private Object matchId;
    private Object matchuniqueField;
    private Boolean existByName;
    private Boolean existByid;

    @Autowired
    private CustomerClientOrganizationRepository customerClientOrganizationRepository;
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
            if (customerClientOrganizationRepository.existsByName((String) matchuniqueField)) {
                clientOrganization = customerClientOrganizationRepository.getByName((String) matchuniqueField);
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