package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.repository.OrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.UniversalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Locale;


@Controller
@RequestMapping("account/employee")
public class EmployeeController extends BaseController {


    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    UniversalRepository universalRepository;



    @InitBinder()
    public void registerConversionServices(WebDataBinder dataBinder) {
        dataBinder.addCustomFormatter(new Formatter<Organization>() {

            @Override
            public String print(Organization organization, Locale locale) {
                return organization.getId().toString();
            }
            @Override
            public Organization parse(String organizationId, Locale locale) throws ParseException {
                return organizationRepository.findOne(Long.parseLong(organizationId));
            }

        });
    }



    @GetMapping(value = "")
    public ModelAndView employee(ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.employeeNavBar);
        modelAndView.addObject("fragment", this.employeeFragment);
        modelAndView.addObject("fragmentNavBar", this.employeeFragmentNavBar);


        return modelAndView;
    }


    @GetMapping(value = "/debt")
    public ModelAndView employeeDebt( ModelAndView modelAndView) {


        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.employeeNavBar);
        modelAndView.addObject("fragment", this.employeeDebtFragment);
        modelAndView.addObject("fragmentNavBar", this.employeeDebtFragmentNavBar);


        return modelAndView;
    }

}




