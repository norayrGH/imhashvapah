package com.example.imhashvapahversion1.version1.controller.partner;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer.CustomerClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.customer.CustomerIndividual;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.otherPartner.OtherPartnerClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.otherPartner.OtherPartnerIndividual;
import com.example.imhashvapahversion1.version1.Entity.enums.DateRange;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.CompanyCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.IndividualCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.PrivateEntrepreneurCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.IndividualOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.PrivateEntrepreneurOtherPartner;

import com.example.imhashvapahversion1.version1.Entity.showClasses.PartnerCustomerShow;

import com.example.imhashvapahversion1.version1.controller.BaseController;
import com.example.imhashvapahversion1.version1.repository.*;
import com.example.imhashvapahversion1.version1.repository.cashIn.CashInFromSaleOfGoodsRepository;
import com.example.imhashvapahversion1.version1.repository.cashIn.CashInFromServiceProvisionRepository;
import com.example.imhashvapahversion1.version1.repository.customer.*;

import com.example.imhashvapahversion1.version1.repository.otherpartners.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("account/partner")
public class PartnerController extends BaseController {

    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    UniversalRepository universalRepository;
    @Autowired
    CompanyCustomerRepository companyCustomerRepository;
    @Autowired
    IndividualCustomerRepository individualCustomerRepository;
    @Autowired
    PrivateEntrepreneurCustomerRepository privateEntrepreneurCustomerRepository;
    @Autowired
    CompanyOtherPartnerRepository companyOtherPartnerRepository;
    @Autowired
    IndividualOtherPartnerRepository individualOtherPartnerRepository;
    @Autowired
    PrivateEntrepreneurOtherPartnerRepository privateEntrepreneurOtherPartnerRepository;

    @Autowired
    CashInFromSaleOfGoodsRepository cashInFromSaleOfGoodsRepository;
    @Autowired
    CashInFromServiceProvisionRepository cashInFromServiceProvisionRepository;
    @Autowired
    CustomerClientOrganizationRepository customerClientOrganizationRepository;
    @Autowired
    CustomerIndividualRepository customerIndividualRepository;
    @Autowired
    OtherPartnerClientOrganizationRepository otherPartnerClientOrganizationRepository;
    @Autowired
    OtherPartnerIndividualRepository otherPartnerIndividualRepository;

    @InitBinder()
    public void registerConversionServices(WebDataBinder dataBinder) {
        dataBinder.addCustomFormatter(new Formatter<Organization>() {

            @Override
            public String print(Organization organization, Locale locale) {
                return organization.getId().toString();
            }
            @Override
            public Organization parse(String organizationId, Locale locale){
                return organizationRepository.findOne(Long.parseLong(organizationId));
            }

        });
    }


    /*partner Customers*/
    @GetMapping(value = "/customer")
    public ModelAndView partners( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.partnerCustomers);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }
    @PostMapping("/customer/show")
    public @ResponseBody
    Set<PartnerCustomerShow> customerShow() {
        List<GeneralMethods> temp1 = new ArrayList();
        List<GeneralMethods> temp2 = new ArrayList();
        Boolean temp = false;
        PartnerCustomerShow partnerCustomerShow = null;
        Set<PartnerCustomerShow> showResult = new HashSet();
        temp1.addAll((ArrayList) customerClientOrganizationRepository.findAll());
        temp1.addAll((ArrayList) customerIndividualRepository.findAll());

        temp2.addAll((ArrayList) companyCustomerRepository.findAll());
        temp2.addAll((ArrayList) individualCustomerRepository.findAll());
        temp2.addAll((ArrayList) privateEntrepreneurCustomerRepository.findAll());

        for(GeneralMethods each1 : temp1) {
            for(GeneralMethods each2 : temp2) {
                if((each1.getId() == each2.getClientOrganizationId() && each1 instanceof CustomerClientOrganization) || ( each1 instanceof CustomerIndividual && each1.getId()==each2.getIndividualId())){
                    partnerCustomerShow = new PartnerCustomerShow(new Long[]{each2.getId(),each1.getId()}, each2.getName(), each2.getPhoneNumber(), each2.getAddress(), each2.getHvhh(), true,each2.getClass().getSimpleName());
                    showResult.add(partnerCustomerShow);
                    temp=true;
                }
            }
            if(temp == false){
                partnerCustomerShow = new PartnerCustomerShow(new Long[]{0L,each1.getId()}, each1.getName(), each1.getPhoneNumber(), each1.getAddress(), each1.getHvhh(), false,each1.getClass().getSimpleName());
                showResult.add(partnerCustomerShow);
            }
            temp=false;
        }
        return showResult;
    }
    @GetMapping(value = "/customer/create/individualcustomer")
    public ModelAndView individualCustomerCreate(ModelAndView modelAndView, HttpSession httpSession) {
        IndividualCustomer individualCustomer = new IndividualCustomer();
        individualCustomer.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("individualCustomer",individualCustomer);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.individualCustomerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }
    @GetMapping(value = "/customer/edit/individualcustomer")
    public ModelAndView individualCustomerEdit(@RequestParam("customerId")Long customerId,@RequestParam("customerInnerId")Long customerInnerId , ModelAndView modelAndView, HttpSession httpSession) {
        IndividualCustomer individualCustomer = new IndividualCustomer();
        if(customerId!=0)
            individualCustomer = individualCustomerRepository.findOne(customerId);
        else
            individualCustomer.setIndividual(customerIndividualRepository.findOne(customerInnerId));

        individualCustomer.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("individualCustomer",individualCustomer);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.individualCustomerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value = "/customer/create/individualcustomer")
    public ModelAndView individualCustomer(@Valid IndividualCustomer individualCustomer, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {
           modelAndView.addObject("individualCustomer", individualCustomer);
           modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("appFragment", this.customerFragments);
           modelAndView.addObject("fragment", this.individualCustomerCreate);
           modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
           return modelAndView;
       }
        individualCustomer.getIndividual().setOrganization(individualCustomer.getOrganization());
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.partnerCustomers);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        individualCustomerRepository.save(individualCustomer);
        return  modelAndView;
    }

    @GetMapping(value = "/customer/create/companycustomer")
    public ModelAndView companyCustomerCreate(ModelAndView modelAndView, HttpSession httpSession) {
        CompanyCustomer companyCustomer = new CompanyCustomer();
        companyCustomer.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("companyCustomer",companyCustomer);
         modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.companyCustomerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);

        return modelAndView;
    }
    @GetMapping(value = "/customer/edit/companycustomer")
    public ModelAndView companyCustomerEdit(@RequestParam("customerId")Long customerId,@RequestParam("customerInnerId")Long customerInnerId , ModelAndView modelAndView, HttpSession httpSession) {
        CompanyCustomer companyCustomer = new CompanyCustomer();
        if(customerId!=0)
            companyCustomer = companyCustomerRepository.findOne(customerId);
        else
            companyCustomer.setClientOrganization( customerClientOrganizationRepository.findOne(customerInnerId));

        companyCustomer.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("companyCustomer",companyCustomer);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.companyCustomerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value = "/customer/create/companycustomer")
    public ModelAndView partnerCustomer(@Valid CompanyCustomer companyCustomer, BindingResult bindingResult , ModelAndView modelAndView) {
       modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

            modelAndView.addObject("companyCustomer", companyCustomer);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("appFragment", this.customerFragments);
            modelAndView.addObject("fragment", this.companyCustomerCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
            return modelAndView;
        }


       modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
       modelAndView.addObject("fragment", this.partnerCustomers);
       modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
       companyCustomerRepository.save(companyCustomer);
       return  modelAndView;
    }


    @GetMapping(value = "/customer/create/privateentrepreneurcustomer")
    public ModelAndView privateentrepreneurcustomerCreate(ModelAndView modelAndView, HttpSession httpSession) {
        PrivateEntrepreneurCustomer privateEntrepreneurCustomer = new PrivateEntrepreneurCustomer();
        privateEntrepreneurCustomer.setOrganization((Organization) httpSession.getAttribute("organizationId"));
          modelAndView.setViewName("app/app");
        modelAndView.addObject("privateEntrepreneurCustomer",privateEntrepreneurCustomer);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.privateEntrepreneurCustomerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value = "/customer/create/privateentrepreneurcustomer")
    public ModelAndView privateentrepreneurcustomerCreate(@Valid PrivateEntrepreneurCustomer privateEntrepreneurCustomer, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

            modelAndView.addObject("privateEntrepreneurCustomer", privateEntrepreneurCustomer);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("appFragment", this.customerFragments);
            modelAndView.addObject("fragment", this.privateEntrepreneurCustomerCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerCustomers);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        privateEntrepreneurCustomerRepository.save(privateEntrepreneurCustomer);
        return  modelAndView;
    }

    /*--partner Customers--*/


    /* partner Otherpartner */

    @GetMapping(value = "/otherpartner")
    public ModelAndView partnersOtherPartner( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.otherPartnerFragments);
        modelAndView.addObject("fragment", this.partnerOtherPartner);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);


        return modelAndView;
    }
    @PostMapping("/otherpartner/show")
    public @ResponseBody Set partnersOtherPartnerShow() {


            List<GeneralMethods> temp1 = new ArrayList();
            List<GeneralMethods> temp2 = new ArrayList();
            Boolean temp = false;
            PartnerCustomerShow partnerCustomerShow = null;
            Set<PartnerCustomerShow> showResult = new HashSet();
            temp1.addAll((ArrayList) otherPartnerClientOrganizationRepository.findAll());
            temp1.addAll((ArrayList) otherPartnerIndividualRepository.findAll());

            temp2.addAll((ArrayList) companyOtherPartnerRepository.findAll());
            temp2.addAll((ArrayList) individualOtherPartnerRepository.findAll());
            temp2.addAll((ArrayList) privateEntrepreneurOtherPartnerRepository.findAll());

            for(GeneralMethods each1 : temp1) {
                for(GeneralMethods each2 : temp2) {
                    if((each1.getId() == each2.getClientOrganizationId() && each1 instanceof OtherPartnerClientOrganization) || ( each1 instanceof OtherPartnerIndividual && each1.getId()==each2.getIndividualId())){

                        partnerCustomerShow = new PartnerCustomerShow(new Long[]{each2.getId(),each1.getId()}, each2.getName(), each2.getPhoneNumber(), each2.getAddress(), each2.getHvhh(), true,each2.getClass().getSimpleName());
                        showResult.add(partnerCustomerShow);
                        temp=true;

                    }
                }
                if(temp == false){

                    partnerCustomerShow = new PartnerCustomerShow(new Long[]{0L,each1.getId()}, each1.getName(), each1.getPhoneNumber(), each1.getAddress(), each1.getHvhh(), false,each1.getClass().getSimpleName());
                    showResult.add(partnerCustomerShow);
                }
                temp=false;
            }
            return showResult;
    }

    @GetMapping(value = "/otherpartner/create/companyotherpartner")
    public ModelAndView companyOtherPartnerCreate(ModelAndView modelAndView, HttpSession httpSession) {
        CompanyOtherPartner companyOtherPartner = new CompanyOtherPartner();
        companyOtherPartner.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
       modelAndView.addObject("companyOtherPartner",companyOtherPartner);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.otherPartnerFragments);
        modelAndView.addObject("fragment", this.companyOtherPartnerCreate);
         modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value ="/otherpartner/create/companyotherpartner")
    public ModelAndView companyOtherPartnerCreate(@Valid CompanyOtherPartner companyOtherPartner, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

            modelAndView.addObject("companyOtherPartner",companyOtherPartner);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("appFragment", this.otherPartnerFragments);
            modelAndView.addObject("fragment", this.companyOtherPartnerCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);
            return modelAndView;
        }

        companyOtherPartner.getClientOrganization().setOrganization(companyOtherPartner.getOrganization());
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.otherPartnerFragments);
        modelAndView.addObject("fragment", this.partnerOtherPartner);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        companyOtherPartnerRepository.save(companyOtherPartner);
        return  modelAndView;
     }

    @GetMapping(value  = "/otherpartner/create/individualotherpartner")
    public ModelAndView individualOtherPartnerCreate(ModelAndView modelAndView, HttpSession httpSession) {
       IndividualOtherPartner individualOtherPartner = new IndividualOtherPartner();
        individualOtherPartner.setOrganization((Organization) httpSession.getAttribute("organizationId"));

        modelAndView.setViewName("app/app");
        modelAndView.addObject("individualOtherPartner",individualOtherPartner);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.otherPartnerFragments);
        modelAndView.addObject("fragment", this.individualOtherPartnerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);

        return modelAndView;
    }
    @GetMapping(value = "/otherpartner/edit/individualotherpartner")
    public ModelAndView individualOtherPartnerEdit(@RequestParam("customerId")Long customerId,@RequestParam("customerInnerId")Long customerInnerId , ModelAndView modelAndView, HttpSession httpSession) {
        IndividualOtherPartner individualOtherPartner = new IndividualOtherPartner();
        if(customerId!=0)
            individualOtherPartner = individualOtherPartnerRepository.findOne(customerId);
        else
            individualOtherPartner.setIndividual(otherPartnerIndividualRepository.findOne(customerInnerId));

        individualOtherPartner.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("individualOtherPartner",individualOtherPartner);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.otherPartnerFragments);
        modelAndView.addObject("fragment", this.individualOtherPartnerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value = "/otherpartner/create/individualotherpartner")
    public ModelAndView individualOtherPartnerCreate(@Valid IndividualOtherPartner individualOtherPartner, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {



            modelAndView.addObject("individualOtherPartner",individualOtherPartner);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("appFragment", this.otherPartnerFragments);
            modelAndView.addObject("fragment", this.individualOtherPartnerCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);
            return modelAndView;
        }

        individualOtherPartner.getIndividual().setOrganization(individualOtherPartner.getOrganization());
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.otherPartnerFragments);
        modelAndView.addObject("fragment", this.partnerOtherPartner);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);
        individualOtherPartnerRepository.save(individualOtherPartner);
        return  modelAndView;
    }

    @GetMapping(value = "/otherpartner/create/privateentrepreneurotherpartner")
    public ModelAndView privateEntrepreneurOtherPartnerCreate(ModelAndView modelAndView, HttpSession httpSession) {
         PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner = new PrivateEntrepreneurOtherPartner();
         privateEntrepreneurOtherPartner.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("privateEntrepreneurOtherPartner",privateEntrepreneurOtherPartner);
         modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.otherPartnerFragments);
        modelAndView.addObject("fragment", this.privateEntrepreneurOtherPartnerCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value ="/otherpartner/create/privateentrepreneurotherpartner")
    public ModelAndView privateEntrepreneurOtherPartnerCreate(@Valid PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

             modelAndView.addObject("privateEntrepreneurOtherPartner",privateEntrepreneurOtherPartner);
             modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("appFragment", this.otherPartnerFragments);
             modelAndView.addObject("fragment", this.privateEntrepreneurOtherPartnerCreate);
             modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);
            return  modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.otherPartnerFragments);
        modelAndView.addObject("fragment", this.partnerOtherPartner);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        privateEntrepreneurOtherPartnerRepository.save(privateEntrepreneurOtherPartner);
        return  modelAndView;
    }
    /*--partner Otherpartner--*/



    @GetMapping(value = "/customer/debt")
    public ModelAndView partnerCustomerDebt( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.partnerFragments);
        modelAndView.addObject("fragment", this.partnerFragment);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }
    @PostMapping("/customer/debt/show")
    public @ResponseBody
    ArrayList customerDebtShow(@RequestBody DateRange dateRange ) {
        List<GetWaletIn> temp = new ArrayList();
        ArrayList showResult = new ArrayList();
        if (dateRange.getStart() != null) {

            temp.addAll(cashInFromSaleOfGoodsRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromServiceProvisionRepository.findByRangeStart(dateRange.getStart()));

            for (GetWaletIn each : temp) {
                showResult.add(each.getWalletInImpl());
            }
            return showResult;
        }
        return showResult;
    }


    @GetMapping( value = "/otherpartner/debt")
    public ModelAndView partnerOtherPartnerDebt( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.otherPartnerFragments);
        modelAndView.addObject("fragment", this.partnerOtherPartnerFragment);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);


        return modelAndView;
    }


}




