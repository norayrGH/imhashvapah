package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Organization;


import com.example.imhashvapahversion1.version1.repository.OrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.UniversalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.Formatter;

import org.springframework.stereotype.Controller;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;


@Controller
@RequestMapping("account/report")
public class ReportController extends BaseController {


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
            public Organization parse(String organizationId, Locale locale) {
                return organizationRepository.findOne(Long.parseLong(organizationId));
            }

        });
    }


    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public ModelAndView report(ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.reportNavBar);
        modelAndView.addObject("fragment", this.reportFragment);
        modelAndView.addObject("fragmentNavBar", this.reportFragmentNavBar);


        return modelAndView;
    }

    @RequestMapping(value = "/summary/debt", method = RequestMethod.GET)
    public ModelAndView reportDebt(ModelAndView modelAndView) {


        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.reportNavBar);
        modelAndView.addObject("fragment", this.reportDebtFragment);
        modelAndView.addObject("fragmentNavBar", this.reportDebtFragmentNavBar);


        return modelAndView;
    }

}




