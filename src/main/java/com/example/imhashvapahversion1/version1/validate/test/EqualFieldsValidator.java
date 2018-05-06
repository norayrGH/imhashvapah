package com.example.imhashvapahversion1.version1.validate.test;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer.CustomerClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.otherPartner.OtherPartnerClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import com.example.imhashvapahversion1.version1.repository.customer.CompanyCustomerRepository;
import com.example.imhashvapahversion1.version1.repository.customer.CustomerClientOrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.otherpartners.CompanyOtherPartnerRepository;
import com.example.imhashvapahversion1.version1.repository.otherpartners.OtherPartnerClientOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class EqualFieldsValidator implements ConstraintValidator<EqualFields, Object> {
    private static Long parrentId = null;
    private static String testHvhh = null;
    private static int valid = 0;

    private String id;
    private String parrentHvhh;
    private String uniqueField;
    private CustomerClientOrganization clientOrganization = null;
    private OtherPartnerClientOrganization otherPartnerClientOrganization = null;
    private Object matchId;
    private Object matchuniqueField;
    private Object hvhh;
    private String currentObject;

    @Autowired
    private CustomerClientOrganizationRepository customerClientOrganizationRepository;
    @Autowired
    private CompanyCustomerRepository companyCustomerRepository;
    @Autowired
    OtherPartnerClientOrganizationRepository otherPartnerClientOrganizationRepository;
    @Autowired
    CompanyOtherPartnerRepository companyOtherPartnerRepository;

    @Override
    public void initialize(EqualFields constraint) {
        id = constraint.id();
        uniqueField = constraint.uniqueField();
        parrentHvhh = constraint.hvhh();

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        Class<?> clazz = object.getClass();
        currentObject=clazz.getTypeName();
        String curentObjectName = currentObject.substring( currentObject.lastIndexOf('.')+1,currentObject.length());


        try {
            matchId = getFieldValue(object, id);
            matchuniqueField = getFieldValue(object, uniqueField);
           if(valid==1){
               valid=0;
           }else{
            hvhh = getFieldValue(object,parrentHvhh);
           }
            valid++;
        } catch (Exception e) {
            return true;
        }
        if (matchuniqueField instanceof String) {
            if(curentObjectName.equals( "CustomerClientOrganization")){
                if (customerClientOrganizationRepository.existsByName((String) matchuniqueField)) {
                    clientOrganization = customerClientOrganizationRepository.getByName((String) matchuniqueField);
                    if (companyCustomerRepository.existsById(parrentId, clientOrganization.getId())) {
                        return true;
                    } else {
                        if(!companyCustomerRepository.existsByHvhh(testHvhh))
                            return true;
                        else
                        {
                            context.buildConstraintViolationWithTemplate("email.unique.violation");
                            return false;
                        }
                    }
                } else {
                    return true;
                }

            }
            if(curentObjectName.equals("OtherPartnerClientOrganization")){
                if (otherPartnerClientOrganizationRepository.existsByName((String) matchuniqueField)) {
                    otherPartnerClientOrganization = otherPartnerClientOrganizationRepository.getByName((String) matchuniqueField);
                    if (companyOtherPartnerRepository.existsById(parrentId, otherPartnerClientOrganization.getId())) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }

            }


        }
        if (!(matchuniqueField instanceof String)) {
            parrentId = (Long) matchId;
            testHvhh = (String) hvhh;
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