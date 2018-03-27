package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Organization;

import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletData;

import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.CashInFromSaleOfGoods;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.formHelpClasses.ClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.formHelpClasses.Individual;
import com.example.imhashvapahversion1.version1.repository.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value="/account/cash/")

@SessionAttributes({"modelTrans"})
public class CashController extends BaseController {

    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    WalletInRepository walletInRepository;

    @Autowired
    ClientOrganizationRepository clientOrganizationRepository;
    @Autowired
    IndividualRepository individualRepository;
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
    public ModelAndView cash(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {


        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashFragment);

        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);


        return modelAndView;
    }

    @RequestMapping(value = "cashdesk/{id}", method = RequestMethod.GET)
    public ModelAndView cashdesk(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {

        WalletData walletData = new WalletData();
        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        walletData.setOrganization(organization);
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashdeskFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);


        return modelAndView;
    }

    @RequestMapping(value = "cashdesk", method = RequestMethod.POST)
    public ModelAndView cashdeskCreate(@Valid WalletData walletData, BindingResult bindingResult, ModelAndView modelAndView) {

        Organization organization = organizationRepository.findOne(walletData.getOrganization().getId());

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashdeskFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            modelAndView.addObject("organization", organization);
            modelAndView.addObject("walletData", walletData);
            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashdeskFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

        return modelAndView;
    }

    @RequestMapping(value = "cashin/cashdesk/{id}", method = RequestMethod.GET)
    public ModelAndView cashIncashdesk(@PathVariable(value = "id") final Long id, ModelAndView modelAndView  ) {



        Organization organization = organizationRepository.findOne(id);
        WalletData walletData = new WalletData();
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInFragment);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return modelAndView;
    }
    @RequestMapping(value = "cashin/cashdesk/create/{id}", method = RequestMethod.GET )
    public ModelAndView cashIncashdeskCreate(@PathVariable(value = "id") final Long id , ModelAndView modelAndView ) {


        Organization organization = organizationRepository.findOne(id);

        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization );
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateFragment);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        return modelAndView;

    }
    @RequestMapping(value = "cashin/cashdesk/create/cashinfromsaleofgoods/{id}" , method = RequestMethod.GET )
    public   ModelAndView cashinfrompointofsaleCreate(@PathVariable(value = "id") final Long id , ModelAndView modelAndView) {
        List customerList = new ArrayList();
        Organization organization = organizationRepository.findOne(id);
        CashInFromSaleOfGoods  cashInFromSaleOfGoods = new CashInFromSaleOfGoods();
        WalletIn walletIn = new WalletIn();
        walletIn.setCashInFromSaleOfGoods(cashInFromSaleOfGoods);
        walletIn.setOrganization(organization);
        customerList.addAll( (List) clientOrganizationRepository.findAll());
        customerList.addAll( (List) individualRepository.findAll());

        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("customerList", customerList);
        modelAndView.addObject("walletIn", walletIn);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateFragmentSaleOfGoods);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfrompointofsale" )
    public   ModelAndView cashinfrompointofsaleCreateSave(@Valid WalletIn walletIn ,BindingResult bindingResult,ModelAndView modelAndView  ) {
        if (bindingResult.hasErrors()) {
            List customerList = new ArrayList();
            customerList.addAll( (List) clientOrganizationRepository.findAll());
            customerList.addAll( (List) individualRepository.findAll());

            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("fragment", this.cashInCreateFragmentSaleOfGoods);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            modelAndView.addObject("walletIn", walletIn);
            modelAndView.addObject("customerList", customerList);
            modelAndView.addObject("organization", walletIn.getOrganization());
            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        modelAndView.addObject("walletIn", walletIn);
        modelAndView.addObject("organization", walletIn.getOrganization());
        walletInRepository.save(walletIn);
        return  modelAndView;
    }
    @RequestMapping(value = "cashin/cashdesk/create/cashinfromsaleofgoods/customer/{id}" , method = RequestMethod.GET )
    public   ModelAndView cashinfrompointofsaleCreateCustomer(@PathVariable(value = "id") final Long id , ModelAndView modelAndView) {

        Organization organization = organizationRepository.findOne(id);
        WalletIn walletIn = new WalletIn();
        walletIn.setOrganization(organization);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("walletIn", walletIn);
        modelAndView.addObject("organization",organization);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCashInFromSaleOfGoodsCustomer);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @RequestMapping(value = "cashin/cashdesk/create/cashinfromsaleofgoods/create/organization/{id}" , method = RequestMethod.GET )
    public   ModelAndView cashinfrompointofsaleCreateOrganization(@PathVariable(value = "id") final Long id , ModelAndView modelAndView) {
        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        ClientOrganization clientOrganization = new ClientOrganization();
        clientOrganization.setOrganization(organization);
        modelAndView.addObject("clientOrganization", clientOrganization);
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateCashInFromSaleOfGoodsClientOrganization);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfromsaleofgoods/create/organization")
    public   ModelAndView cashinfrompointofsaleCreateOrganization(@Valid ClientOrganization clientOrganization,BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("clientOrganization", clientOrganization);
            modelAndView.addObject("organization", clientOrganization.getOrganization());
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromSaleOfGoodsClientOrganization);
            modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
            return modelAndView;
        }
        modelAndView.addObject("clientOrganization", clientOrganization);
        modelAndView.addObject("organization",clientOrganization.getOrganization());
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateFragment);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        clientOrganizationRepository.save(clientOrganization);
        return  modelAndView;
    }
    @RequestMapping(value = "cashin/cashdesk/create/cashinfromsaleofgoods/create/individual/{id}" , method = RequestMethod.GET )
    public   ModelAndView cashinfrompointofsaleCreateIndividual(@PathVariable(value = "id") final Long id , ModelAndView modelAndView) {
        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        Individual individual = new Individual();
        individual.setOrganization(organization);
        modelAndView.addObject("individual", individual);
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateCashInFromSaleOfGoodsIndividual);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfromsaleofgoods/create/individual")
    public   ModelAndView cashinfrompointofsaleCreateIndividual(@Valid Individual individual, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("individual", individual);
            modelAndView.addObject("organization", individual.getOrganization());
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromSaleOfGoodsIndividual);
            modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
            return modelAndView;
        }
        modelAndView.addObject("individual", individual);
        modelAndView.addObject("organization",individual.getOrganization());
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateFragment);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        individualRepository.save(individual);
        return  modelAndView;
    }


    @RequestMapping(value = "cashout/cashdesk/{id}", method = RequestMethod.GET)
    public ModelAndView cashOutcashdesk(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {

        Organization organization = organizationRepository.findOne(id);
        WalletData walletData = new WalletData();
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutFragment);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);


        return modelAndView;
    }

}
