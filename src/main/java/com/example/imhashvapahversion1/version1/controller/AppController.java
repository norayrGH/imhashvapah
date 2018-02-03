package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.action.area.CircleTax;
import com.example.imhashvapahversion1.version1.Entity.enums.Address;
import com.example.imhashvapahversion1.version1.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller("AppController")
public class AppController extends BaseController{

    @Autowired
    OrganizationRepository organizationRepository;

    @RequestMapping(value = "/account")
    public ModelAndView appAction(ModelAndView modelAndView) {

        List<Organization> organizationList = (List<Organization>) organizationRepository.findAll();
        modelAndView.addObject("organizationList", organizationList);
        modelAndView.addObject("fragment", this.startViwFragment);
        modelAndView.setViewName("start");

        return modelAndView;
    }


    @RequestMapping(value = "/account/login/success")
    public final String loginSuccessAction() {
        return "redirect:/account";
    }

    @RequestMapping(value = "/account/organization/create")
    public ModelAndView organizationCreateAction(ModelAndView modelAndView) {


        Organization organization = new Organization();
        HashMap addresses = (HashMap) Address.getAddresses();
        modelAndView.addObject("addresses", addresses);
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("fragment",this.organizationCreateFragment);
        modelAndView.setViewName("start");

        return modelAndView;
    }

    @RequestMapping(value = "/account/organization/create", method = RequestMethod.POST)
    public ModelAndView  organizationUpAction(
            @Valid Organization organization,
            BindingResult bindingResult,
            ModelAndView modelAndView

    ) {

        HashMap addresses = (HashMap) Address.getAddresses();
        if (bindingResult.hasErrors()) {

            modelAndView.addObject("addresses", addresses);
            modelAndView.addObject("organization", organization);
            modelAndView.addObject("fragment",this.organizationCreateFragment);
            modelAndView.setViewName("start");
            return modelAndView;


        } else if (organization.getRegistrationDate().getTime() > organization.getDateOfOpeningBalances().getTime()) {


            bindingResult.rejectValue("registrationDate", "form.validation.errors.invalidRegisteredDate");
            modelAndView.setViewName("start");

            modelAndView.addObject("addresses", addresses);
            modelAndView.addObject("organization" , organization);
            modelAndView.addObject("fragment", this.organizationCreateFragment);
            return modelAndView;
        }
        organizationRepository.save(organization);

        ModelAndView modelAndViewRedirect = new ModelAndView("redirect:/account");
        return modelAndViewRedirect;
    }


}
