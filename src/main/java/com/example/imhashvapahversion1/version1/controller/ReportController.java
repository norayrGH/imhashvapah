package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.FixedAsset;
import com.example.imhashvapahversion1.version1.Entity.Organization;

import com.example.imhashvapahversion1.version1.Entity.cash.WalletData;

import com.example.imhashvapahversion1.version1.repository.OrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.UniversalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Controller;


import org.springframework.validation.BindingResult;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.text.ParseException;
import java.util.Locale;


@Controller
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
            public Organization parse(String organizationId, Locale locale) throws ParseException {
                return organizationRepository.findOne(Long.parseLong(organizationId));
            }

        });
    }





    @RequestMapping(value = "account/report/summary/{id}", method = RequestMethod.GET)
    public ModelAndView report(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {


        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar", this.reportNavBar);
        modelAndView.addObject("fragment", this.reportFragment);
        modelAndView.addObject("fragmentNavBar", this.reportFragmentNavBar);


        return modelAndView;
    }

    @RequestMapping(value = "account/report/summary/debt/{id}", method = RequestMethod.GET)
    public ModelAndView reportDebt(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {

        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar", this.reportNavBar);
        modelAndView.addObject("fragment", this.reportDebtFragment);
        modelAndView.addObject("fragmentNavBar", this.reportDebtFragmentNavBar);


        return modelAndView;
    }

}




