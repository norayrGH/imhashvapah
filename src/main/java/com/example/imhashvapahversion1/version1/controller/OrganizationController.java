package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.FixedAsset;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.enums.DateRange;
import com.example.imhashvapahversion1.version1.repository.OrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.UniversalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.DateUtils;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
    public ModelAndView organizationFixedassetCreate(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {
        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        FixedAsset fixedAsset = new FixedAsset();
        fixedAsset.setOrganization(organization);
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("fixedAsset", fixedAsset);
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("updateOrCreate", "create");
        modelAndView.addObject("fragment", this.organizationFixedassetCreate);
        return modelAndView;

    }

    @RequestMapping(value = "/fixedasset/edit/{id}", method = RequestMethod.GET)
    public ModelAndView organizationFixedassetEdit(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        FixedAsset fixedAsset = universalRepository.findOne(id);
        Organization organization = fixedAsset.getOrganization();
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("fixedAsset", fixedAsset);
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("updateOrCreate", "update");
        modelAndView.addObject("fragment", this.organizationFixedassetCreate);
        return modelAndView;

    }


    @RequestMapping(value = "/fixedasset/update", method = RequestMethod.POST)
    public ModelAndView organizationFixedassetupdatePost(@Valid FixedAsset fixedAsset, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.organizationNavBar);

        if (bindingResult.hasErrors()) {

            modelAndView.addObject("organization", fixedAsset.getOrganization());
            modelAndView.addObject("fragment", this.organizationFixedassetCreate);
            modelAndView.addObject("updateOrCreate", "update");
            modelAndView.addObject("fixedAsset", fixedAsset);
            return modelAndView;
        }


        universalRepository.save(fixedAsset);



        modelAndView.addObject("organization", fixedAsset.getOrganization());
        modelAndView.addObject("fragment", this.organizationFixedasset);




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
            modelAndView.addObject("updateOrCreate", "create");
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

    @RequestMapping(value = "/fixedasset/show", method = RequestMethod.POST)
     public @ResponseBody ArrayList<FixedAsset> organizationFixedassetcreatePost(@RequestBody DateRange dateRange ) throws ParseException {





        if (dateRange.isShowAll())
            return (ArrayList) universalRepository.findAll();





        if (dateRange.getStart() != null && dateRange.getEnd() == null) {

            Calendar calStart = Calendar.getInstance();
            calStart.set(Calendar.YEAR,dateRange.getStart().toLocalDate().getYear());
            calStart.set(Calendar.MONTH,  dateRange.getStart().toLocalDate().getDayOfMonth() - 1);
            calStart.set(Calendar.DAY_OF_MONTH, dateRange.getStart().toLocalDate().getMonthValue()+1);
            calStart.set(Calendar.HOUR_OF_DAY,0);
            calStart.set(Calendar.MINUTE,0);
            calStart.set(Calendar.SECOND,0);
            java.sql.Date dateStart = new java.sql.Date(calStart.getTimeInMillis());


            ArrayList resultByStart = (ArrayList) universalRepository.findByRangeStart(dateStart);


            return resultByStart;

        } else if (dateRange.getStart() == null && dateRange.getEnd() != null) {

            Calendar calEnd = Calendar.getInstance();
            calEnd.set(Calendar.YEAR,dateRange.getEnd().toLocalDate().getYear());
            calEnd.set(Calendar.MONTH,  dateRange.getEnd().toLocalDate().getDayOfMonth() - 1);
            calEnd.set(Calendar.DAY_OF_MONTH, dateRange.getEnd().toLocalDate().getMonthValue()+1);
            calEnd.set(Calendar.HOUR_OF_DAY,0);
            calEnd.set(Calendar.MINUTE,0);
            calEnd.set(Calendar.SECOND,0);
            java.sql.Date dateEnd = new java.sql.Date(calEnd.getTimeInMillis());

            ArrayList<FixedAsset> resultByStart = (ArrayList) universalRepository.findByEnd( dateEnd);
            return resultByStart;
        } else if (dateRange.getStart() != null && dateRange.getEnd() != null) {
            Calendar calStart = Calendar.getInstance();
            calStart.set(Calendar.YEAR,dateRange.getStart().toLocalDate().getYear());
            calStart.set(Calendar.MONTH,  dateRange.getStart().toLocalDate().getDayOfMonth() - 1);
            calStart.set(Calendar.DAY_OF_MONTH, dateRange.getStart().toLocalDate().getMonthValue()+1);
            calStart.set(Calendar.HOUR_OF_DAY,0);
            calStart.set(Calendar.MINUTE,0);
            calStart.set(Calendar.SECOND,0);
            java.sql.Date dateStart = new java.sql.Date(calStart.getTimeInMillis());


            Calendar calEnd = Calendar.getInstance();
            calEnd.set(Calendar.YEAR,dateRange.getEnd().toLocalDate().getYear());
            calEnd.set(Calendar.MONTH,  dateRange.getEnd().toLocalDate().getDayOfMonth() - 1);
            calEnd.set(Calendar.DAY_OF_MONTH, dateRange.getEnd().toLocalDate().getMonthValue()+1);
            calEnd.set(Calendar.HOUR_OF_DAY,0);
            calEnd.set(Calendar.MINUTE,0);
            calEnd.set(Calendar.SECOND,0);
            java.sql.Date dateEnd = new java.sql.Date(calEnd.getTimeInMillis());

            ArrayList<FixedAsset> resultByStart = (ArrayList) universalRepository.findByRange(dateStart, dateEnd);


                return resultByStart;

        }
        return null;
    }


}
