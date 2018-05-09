package com.example.imhashvapahversion1.version1.validate.test;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer.CustomerClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.otherPartner.OtherPartnerClientOrganization;

import com.example.imhashvapahversion1.version1.Entity.partners.Customers.CompanyCustomer;
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
    private static int validationCount = 0;
    private  static boolean accept;
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
        currentObject = clazz.getTypeName();
        String curentObjectName = currentObject.substring(currentObject.lastIndexOf('.') + 1, currentObject.length());


        try {
            matchId = getFieldValue(object, id);
            matchuniqueField = getFieldValue(object, uniqueField);

                if(curentObjectName.equals("CompanyCustomer") || curentObjectName.equals("CompanyOtherPartner")){

                    hvhh = getFieldValue(object, parrentHvhh);
                }

        } catch (Exception e) {
            return true;
        }
        if(accept==true){
            accept =false;
            validationCount=0;
            testHvhh="";
            return true;
        }
        if(validationCount==1 ){
            if(curentObjectName.equals("CustomerClientOrganization")){
                if(companyCustomerRepository.existsByHvhh(testHvhh)){
                    if(parrentId == companyCustomerRepository.getIdByHvhh(testHvhh)){
                        validationCount=0;
                        testHvhh = "";
                        return true;

                    }else{
                        validationCount=0;
                        testHvhh="";
                        return false;
                    }
                }

            }

            if(curentObjectName.equals("OtherPartnerClientOrganization")){
                if(companyOtherPartnerRepository.existsByHvhh( testHvhh)){
                    if(parrentId == companyOtherPartnerRepository.getIdByHvhh( testHvhh)){
                        validationCount=0;
                        return true;

                    }else{
                        validationCount=0;
                        return false;
                    }

                }

            }

        }

        if (validationCount == 0 && curentObjectName.equals("CustomerClientOrganization")) {

            if (!customerClientOrganizationRepository.existsByName((String) matchuniqueField)) {

                validationCount=0;
                return true;
            } else {
                validationCount=0;
                return false;
            }
        }

        if (validationCount == 0 && curentObjectName.equals("OtherPartnerClientOrganization")) {

            if (!otherPartnerClientOrganizationRepository.existsByName((String) matchuniqueField)) {

                validationCount=0;
                return true;
            } else {
                validationCount=0;
                return false;
            }
        }

        if(hvhh!=null & matchId==null & curentObjectName.equals("CompanyCustomer"))
        { testHvhh = (String) hvhh;
            if(companyCustomerRepository.existsByHvhh((String) hvhh)){
               validationCount++;
                return true;
            }else {
                accept = true;
                validationCount++;
                return true;
            }
        }

        if(hvhh!=null & matchId==null & curentObjectName.equals("CompanyOtherPartner"))
        {
            testHvhh = (String) hvhh;
            if(companyOtherPartnerRepository.existsByHvhh((String) hvhh)) {
               validationCount++;
               return true;

            }else{
                accept = true;
                validationCount++;
                return true;
            }
        }

        if(matchId!=null && validationCount==0){
            testHvhh = (String) hvhh;
            parrentId = (Long) matchId;
            validationCount++;
            return true ;
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