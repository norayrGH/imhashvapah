package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.FixedAsset;
import com.example.imhashvapahversion1.version1.Entity.Organization;

import com.example.imhashvapahversion1.version1.Entity.cash.WalletData;

import com.example.imhashvapahversion1.version1.repository.OrganizationRepository;
import com.example.imhashvapahversion1.version1.repository.UniversalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.validation.BindingResult;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;


@Controller("InnerAppController")
public class InnerAppController extends BaseController {
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    UniversalRepository universalRepository;
    @RequestMapping(value = "account/organization/{id}", method = RequestMethod.GET)
    public ModelAndView organization(@PathVariable(value = "id") final Long id,    ModelAndView modelAndView ) {



        Organization organization = organizationRepository.findOne(id);

        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar",this.organizationNavBar);
        modelAndView.addObject("fragment", this.startViwFragment);

        return modelAndView;
    }

    @RequestMapping(value = "account/organization/details/{id}", method = RequestMethod.GET)
    public ModelAndView organizationDetails(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){
        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar",this.organizationNavBar);
        modelAndView.addObject("fragment",this.organizationDetailsFragment);
        return modelAndView;

    }

    @RequestMapping(value = "account/organization/fixedasset/{id}", method = RequestMethod.GET)
    public ModelAndView organizationFixedasset(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){
        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("fragment",this.organizationFixedasset);
        modelAndView.addObject("navBar",this.organizationNavBar);
        return modelAndView;

    }
    @RequestMapping(value = "/account/organization/fixedasset/create/{id}", method = RequestMethod.GET)
    public ModelAndView organizationFixedassetcreate(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){
        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        FixedAsset fixedAsset = new FixedAsset();
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("fixedAsset", fixedAsset);
        modelAndView.addObject("navBar",this.organizationNavBar);
        modelAndView.addObject("fragment",this.organizationFixedassetCreate);
        return modelAndView;

    }
    @RequestMapping(value = "/account/organization/fixedasset/create", method = RequestMethod.POST)
    public ModelAndView organizationFixedassetcreatePost(@Valid FixedAsset fixedAsset ,BindingResult bindingResult, ModelAndView modelAndView  ){
        Organization organization = organizationRepository.findOne(fixedAsset.getOrganizationId());
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar",this.organizationNavBar);
            modelAndView.addObject("fragment",this.organizationFixedassetCreate);
            modelAndView.addObject("fragmentNavBar",this.cashdeskFragmentNavBar);
            modelAndView.addObject("organization", organization);
            modelAndView.addObject("fixedAsset", fixedAsset);
            return modelAndView;
        }
        universalRepository.save(fixedAsset);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar",this.organizationNavBar);
        modelAndView.addObject("fragment",this.organizationFixedasset);
        return modelAndView;

    }


    @RequestMapping(value = "account/cash/{id}", method = RequestMethod.GET)
    public ModelAndView cash(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){


        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar",this.cashNavBar);
        modelAndView.addObject("fragment",this.cashFragment);

        modelAndView.addObject("fragmentNavBar",this.cashdeskFragmentNavBar);





        return modelAndView;
    }

    @RequestMapping(value = "/account/cash/cashdesk/{id}", method = RequestMethod.GET)
    public ModelAndView cashdesk(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){

        WalletData walletData = new WalletData();
        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar",this.cashNavBar);
        modelAndView.addObject("fragment",this.cashdeskFragment);
        modelAndView.addObject("fragmentNavBar",this.cashdeskFragmentNavBar);




        return modelAndView;
    }

    @RequestMapping(value = "/account/cash/cashdesk", method = RequestMethod.POST)
    public ModelAndView cashdeskCreate(@Valid WalletData walletData, BindingResult bindingResult, ModelAndView modelAndView ){

        Organization organization = organizationRepository.findOne(walletData.getOrganizationId());

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar" ,this.cashNavBar);
            modelAndView.addObject("fragment",this.cashdeskFragment);
            modelAndView.addObject("fragmentNavBar",this.cashdeskFragmentNavBar);
            modelAndView.addObject("organization", organization);
            modelAndView.addObject("walletData", walletData);
            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar" ,this.cashNavBar);
        modelAndView.addObject("fragment",this.cashdeskFragment);
        modelAndView.addObject("fragmentNavBar",this.cashdeskFragmentNavBar);

        return modelAndView;
    }

    @RequestMapping(value = "/account/cash/cashin/cashdesk/{id}", method = RequestMethod.GET)
    public ModelAndView cashIncashdesk(@PathVariable(value = "id") final Long id,  ModelAndView modelAndView ){

        Organization organization = organizationRepository.findOne(id);
        WalletData walletData = new WalletData();
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar" ,this.cashNavBar);
        modelAndView.addObject("fragment",this.cashInFragment);
        modelAndView.addObject("fragmentNavBar",this.cashInFragmentNavBar);

        return modelAndView;
    }

    @RequestMapping(value = "/account/cash/cashout/cashdesk/{id}", method = RequestMethod.GET)
    public ModelAndView cashOutcashdesk(@PathVariable(value = "id") final Long id,  ModelAndView modelAndView ){

        Organization organization = organizationRepository.findOne(id);
        WalletData walletData = new WalletData();
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar"  ,this.cashNavBar);
        modelAndView.addObject("fragment",this.cashOutFragment);
        modelAndView.addObject("fragmentNavBar",this.cashOutFragmentNavBar);






        return modelAndView;
    }

    @RequestMapping(value = "account/partner/customer/debt/{id}", method = RequestMethod.GET)
    public ModelAndView partnerCustomerDebt(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){


        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar",this.partnerNavBar);
        modelAndView.addObject("fragment",this.partnerFragment);
        modelAndView.addObject("fragmentNavBar",this.partnerFragmentNavBar);





        return modelAndView;
    }

    @RequestMapping(value = "account/partner/supplier/debt/{id}", method = RequestMethod.GET)
    public ModelAndView partnerSupplierDebt(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){


        Organization organization = organizationRepository.findOne(id) ;
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar",this.partnerNavBar);
        modelAndView.addObject("fragment",this.partnerSupplierFragment);
        modelAndView.addObject("fragmentNavBar",this.partnerSupplierFragmentNavBar);





        return modelAndView;
    }

    @RequestMapping(value = "account/partner/otherpartner/debt/{id}", method = RequestMethod.GET)
    public ModelAndView partnerOtherPartnerDebt (@PathVariable(value = "id") final Long id,ModelAndView modelAndView){


        Organization organization = organizationRepository.findOne(id) ;
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar",this.partnerNavBar);
        modelAndView.addObject("fragment",this.partnerOtherPartnerFragment);
        modelAndView.addObject("fragmentNavBar",this.partnerOtherPartnerFragmentNavBar);





        return modelAndView;
    }

    @RequestMapping(value = "account/employee/{id}", method = RequestMethod.GET)
    public ModelAndView employee (@PathVariable(value = "id") final Long id,ModelAndView modelAndView){


        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar",this.employeeNavBar);
        modelAndView.addObject("fragment",this.employeeFragment);
        modelAndView.addObject("fragmentNavBar",this.employeeFragmentNavBar);





        return modelAndView;
    }


    @RequestMapping(value = "account/employee/debt/{id}", method = RequestMethod.GET)
    public ModelAndView employeeDebt (@PathVariable(value = "id") final Long id,ModelAndView modelAndView){


        Organization organization = organizationRepository.findOne(id) ;
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar",this.employeeNavBar);
        modelAndView.addObject("fragment",this.employeeDebtFragment);
        modelAndView.addObject("fragmentNavBar",this.employeeDebtFragmentNavBar);





        return modelAndView;
    }

    @RequestMapping(value = "account/report/summary/{id}", method = RequestMethod.GET)
    public ModelAndView report (@PathVariable(value = "id") final Long id,ModelAndView modelAndView){


        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar",this.reportNavBar);
        modelAndView.addObject("fragment",this.reportFragment);
        modelAndView.addObject("fragmentNavBar",this.reportFragmentNavBar);





        return modelAndView;
    }

    @RequestMapping(value = "account/report/summary/debt/{id}", method = RequestMethod.GET)
    public ModelAndView reportDebt (@PathVariable(value = "id") final Long id,ModelAndView modelAndView){

        Organization organization = organizationRepository.findOne(id) ;
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar",this.reportNavBar);
        modelAndView.addObject("fragment",this.reportDebtFragment);
        modelAndView.addObject("fragmentNavBar",this.reportDebtFragmentNavBar);





        return modelAndView;
    }

}




