package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.FixedAsset;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.enums.DateRange;
import com.example.imhashvapahversion1.version1.repository.OrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.UniversalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Locale;


@Controller
@RequestMapping(value = "/account/organization")
public class OrganizationController extends BaseController {



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



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView organization(@PathVariable(value = "id") final Long id, ModelAndView modelAndView,HttpSession httpSession) {
        Organization organization = organizationRepository.findOne(id);
        httpSession.setAttribute("organizationId",organization);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.startViwFragment);

        return modelAndView;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView organizationDetails(ModelAndView modelAndView,HttpSession httpSession) {

        modelAndView.setViewName("app/app");

        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.organizationDetailsFragment);
        return modelAndView;

    }

    @RequestMapping(value = "/fixedasset", method = RequestMethod.GET)
    public ModelAndView organizationFixedasset( ModelAndView modelAndView ) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("fragment", this.organizationFixedasset);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("navBar", this.organizationNavBar);
        return modelAndView;

    }

    @RequestMapping(value = "/fixedasset/create", method = RequestMethod.GET)
    public ModelAndView organizationFixedassetCreate( ModelAndView modelAndView, HttpSession httpSession) {

        modelAndView.setViewName("app/app");
        FixedAsset fixedAsset = new FixedAsset();
        fixedAsset.setOrganization( (Organization) httpSession.getAttribute("organizationId"));
        modelAndView.addObject("fixedAsset", fixedAsset);
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("updateOrCreate", "create");
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.organizationFixedassetCreate);
        return modelAndView;

    }
    @PostMapping(value = "/fixedasset/create")
    public ModelAndView organizationFixedassetcreatePost(@Valid FixedAsset fixedAsset, BindingResult bindingResult, ModelAndView modelAndView) {


        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("fragment", this.organizationFixedassetCreate);
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            modelAndView.addObject("updateOrCreate", "create");
            modelAndView.addObject("fixedAsset", fixedAsset);

            return modelAndView;
        }

        universalRepository.save(fixedAsset);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.organizationFixedasset);




        return modelAndView;

    }
    @RequestMapping(value = "/fixedasset/edit/{id}", method = RequestMethod.GET)
    public ModelAndView organizationFixedassetEdit(@PathVariable(value = "id") final Long id, ModelAndView modelAndView,HttpSession httpSession) {

        modelAndView.setViewName("app/app");
        FixedAsset fixedAsset = universalRepository.findOne(id);

        modelAndView.addObject("organization", httpSession.getAttribute("organizationId"));
        modelAndView.addObject("fixedAsset", fixedAsset);
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("updateOrCreate", "update");
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.organizationFixedassetCreate);
        return modelAndView;

    }


    @PostMapping(value = "/fixedasset/update")
    public ModelAndView organizationFixedassetupdatePost(@Valid FixedAsset fixedAsset, BindingResult bindingResult, ModelAndView modelAndView ) {
        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.organizationNavBar);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("organization", fixedAsset.getOrganization());
            modelAndView.addObject("appFragment", this.appFragment);
            modelAndView.addObject("fragment", this.organizationFixedassetCreate);
            modelAndView.addObject("updateOrCreate", "update");
            modelAndView.addObject("fixedAsset", fixedAsset);
            return modelAndView;
        }


        universalRepository.save(fixedAsset);

        modelAndView.addObject("organization", fixedAsset.getOrganization());
        modelAndView.addObject("appFragment", this.appFragment);
        modelAndView.addObject("fragment", this.organizationFixedasset);




        return modelAndView;

    }



    @PostMapping("/fixedasset/show")
     public @ResponseBody ArrayList<FixedAsset> organizationFixedassetcreatePost(@RequestBody DateRange dateRange ) {

        if (dateRange.isShowAll()) {
            return (ArrayList) universalRepository.findAll();
        } else if (dateRange.getStart() != null && dateRange.getEnd() == null) {
            return (ArrayList<FixedAsset>) universalRepository.findByRangeStart(dateRange.getStart());
        } else if (dateRange.getStart() == null && dateRange.getEnd() != null) {

            return (ArrayList<FixedAsset>) universalRepository.findByEnd(dateRange.getEnd());
        } else if (dateRange.getStart() != null && dateRange.getEnd() != null) {
            return (ArrayList<FixedAsset>) universalRepository.findByRange(dateRange.getStart(), dateRange.getEnd());
        }
        return new ArrayList<>();
     }
}
