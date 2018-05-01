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
   // private static Boolean temp = false ;
    private static Long parrentId =null;
    private static short validCount = 0;
   /* private static short firstInitial = 0;*/
    private String  id;
    private String  uniqueField;
    private static Boolean isEditable;
    private static Boolean unique;
    private static Boolean result;
    private ClientOrganization clientOrganization = null;
    @Autowired
    private ClientOrganizationRepository clientOrganizationRepository;
    @Autowired
    private CompanyCustomerRepository companyCustomerRepository;
    @Override
    public void initialize(EqualFields constraint) {
         id = constraint.id();
         uniqueField = constraint.uniqueField();

    }

    static{
        isEditable=false;
        unique = false;
        result = false;
    }

    public EqualFieldsValidator() {
        this.isEditable=false;
        this.unique=false;
        this.result=false;
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        Object matchId;
        Object matchuniqueField;

        try {
             matchId  = getFieldValue(object, id);
             matchuniqueField = getFieldValue(object, uniqueField);

            switch (validCount) {
                case 0:
                    if(!(matchuniqueField instanceof String)){
                        parrentId = (Long) matchId;
                        validCount++;
                    }
                    break;
                case 1:
                    if (matchuniqueField instanceof String) {

                        if (clientOrganizationRepository.existsByName((String) matchuniqueField)) {
                            clientOrganization = clientOrganizationRepository.getByName((String) matchuniqueField);
                            if (companyCustomerRepository.existsById(parrentId, clientOrganization.getId())) {

                                validCount++;
                                return true;
                            }else{
                                validCount++;
                                return false;
                            }
                        }else{

                          validCount++;
                            return true;
                        }

                    }
                    break;
                case 2 :
                    validCount++;
                        break;
                default:
                    validCount=2;
            }
            /*if(result == true) {
                isEditable = false;
                unique = false;
                result = false;
                return true;
            }

            if(unique==true & isEditable==true){
                    result=true;
            }*/
            /*if(result == true & clientOrganization != null){

                return true;
            }
                if (matchuniqueField instanceof String) {

                    if (clientOrganizationRepository.existsByName((String) matchuniqueField)) {
                        clientOrganization = clientOrganizationRepository.getByName((String) matchuniqueField);
                        if (companyCustomerRepository.existsById(parrentId, clientOrganization.getId())) {
                            result = true;
                            return true;
                        }else{
                            result = false;
                            return false;
                        }
                    }else{

                        result=true;
                        return true;
                    }

                }
                if(!(matchuniqueField instanceof String)){
                    parrentId = (Long) matchId;

                }*/


            return true;

        } catch (Exception e) {



            return true;
        }

    }

    @Override
    protected void finalize() throws Throwable {

        try {
            isEditable=false;
            unique = false;
            result = false;
        } finally {
            super.finalize();
        }
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }


}