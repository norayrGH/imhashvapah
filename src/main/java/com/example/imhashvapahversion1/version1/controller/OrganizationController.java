package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.FixedAsset;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.enums.DateRange;
import com.example.imhashvapahversion1.version1.repository.OrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.UniversalRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/account/organization/")
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
            public Organization parse(String organizationId, Locale locale) throws ParseException {
                return organizationRepository.findOne(Long.parseLong(organizationId));
            }

        });
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView organization(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {


        Organization organization = organizationRepository.findOne(id);

        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("fragment", this.startViwFragment);

        return modelAndView;
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public ModelAndView organizationDetails(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {
        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("fragment", this.organizationDetailsFragment);
        return modelAndView;

    }

    @RequestMapping(value = "/fixedasset/{id}", method = RequestMethod.GET)
    public ModelAndView organizationFixedasset(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {
        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("fragment", this.organizationFixedasset);
        modelAndView.addObject("navBar", this.organizationNavBar);
        return modelAndView;

    }

    @RequestMapping(value = "/fixedasset/create/{id}", method = RequestMethod.GET)
    public ModelAndView organizationFixedassetcreate(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {
        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        FixedAsset fixedAsset = new FixedAsset();
        fixedAsset.setOrganization(organization);
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("fixedAsset", fixedAsset);
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("fragment", this.organizationFixedassetCreate);
        return modelAndView;

    }

    @RequestMapping(value = "/fixedasset/create", method = RequestMethod.POST)
    public ModelAndView organizationFixedassetcreatePost(@Valid FixedAsset fixedAsset, BindingResult bindingResult, ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("fragment", this.organizationFixedassetCreate);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            modelAndView.addObject("organization", fixedAsset.getOrganization());
            modelAndView.addObject("fixedAsset", fixedAsset);

            return modelAndView;
        }

        universalRepository.save(fixedAsset);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", fixedAsset.getOrganization());
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("fragment", this.organizationFixedasset);




        return modelAndView;

    }
    @RequestMapping(value = "/fixedasset/show")

    @ResponseBody public ArrayList<FixedAsset> organizationFixedassetcreatePost(@RequestBody DateRange dateRange ) throws ParseException {





        if(dateRange.getStart() != null && dateRange.getEnd()==null) {
            Date starter = new Date(dateRange.getStart().getYear(), dateRange.getStart().getMonth(), dateRange.getStart().getDay());
            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String starter1 = dmyFormat.format(starter);
            starter = dmyFormat.parse(starter1);

           ArrayList<FixedAsset> resultByStart =  (ArrayList)universalRepository.findByRangeStart(starter);


           return resultByStart ;

        }else if(dateRange.getStart() != null && dateRange.getEnd()!=null)
        { Date starter = new Date(dateRange.getStart().getYear(), dateRange.getStart().getMonth(), dateRange.getStart().getDay());
          Date ender = new Date(dateRange.getEnd().getYear(), dateRange.getEnd().getMonth(), dateRange.getEnd().getDay());
            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String starter1 = dmyFormat.format(starter);
            starter = dmyFormat.parse(starter1);
            String ender1 = dmyFormat.format(ender);
            ender = dmyFormat.parse(ender1);
            ArrayList<FixedAsset> resultByStart =  (ArrayList)universalRepository.findByRange(starter, ender);
            return resultByStart ;

        } else{

          return  (ArrayList)universalRepository.findAll();
        }


    }


}
