package com.example.imhashvapahversion1.version1.controller.partner;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletOut;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashIn.*;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.Debt;
import com.example.imhashvapahversion1.version1.Entity.enums.DateRangByType;
import com.example.imhashvapahversion1.version1.Entity.enums.DateRange;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.*;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.IndividualOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.PrivateEntrepreneurOtherPartner;

import com.example.imhashvapahversion1.version1.Entity.showClasses.*;

import com.example.imhashvapahversion1.version1.controller.BaseController;
import com.example.imhashvapahversion1.version1.repository.*;
import com.example.imhashvapahversion1.version1.repository.cashIn.CashInFromCreditRepository;
import com.example.imhashvapahversion1.version1.repository.cashIn.CashInFromLoanRepository;
import com.example.imhashvapahversion1.version1.repository.cashIn.CashInFromSaleOfGoodsRepository;
import com.example.imhashvapahversion1.version1.repository.cashIn.CashInFromServiceProvisionRepository;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForCreditPaymentRepository;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForLoanPaymentRepository;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForRedemptionPercentRepository;
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
    SaleRepository saleRepository;
    @Autowired
    CashInFromCreditRepository cashInFromCreditRepository;
    @Autowired
    CashInFromLoanRepository cashInFromLoanRepository;
    @Autowired
    CashInFromSaleOfGoodsRepository cashInFromSaleOfGoodsRepository;
    @Autowired
    CashOutForCreditPaymentRepository cashOutForCreditPaymentRepository;



    @Autowired
    CashOutForRedemptionPercentRepository cashOutForRedemptionPercentRepository;
    @Autowired
    CashOutForLoanPaymentRepository cashOutForLoanPaymentRepository;

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

    private List customerList = new ArrayList();
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
    Set<SupplierShow> customerShow() {

        List<GeneralMethods> suppliers = new ArrayList();

        SupplierShow supplierShow = null;
        Set<SupplierShow> showResult = new HashSet();

        suppliers.addAll((ArrayList) companyCustomerRepository.findAll());
        suppliers.addAll((ArrayList) individualCustomerRepository.findAll());
        suppliers.addAll((ArrayList) privateEntrepreneurCustomerRepository.findAll());


        for(GeneralMethods supplier : suppliers) {
            if (supplier.getHvhh() != null || supplier.getHch()!=null) {
                supplierShow = new SupplierShow(supplier.getId(), supplier.getName(), supplier.getPhoneNumber(), supplier.getAddress(), supplier.getHvhh(), true, supplier.getClass().getSimpleName());
                showResult.add(supplierShow);
            } else {
                supplierShow = new SupplierShow(supplier.getId(), supplier.getName(), supplier.getPhoneNumber(), supplier.getAddress(), supplier.getHvhh(), false, supplier.getClass().getSimpleName());
                showResult.add(supplierShow);
            }


        }
        return showResult;

    }


    @GetMapping(value = "/customer/edit")
    public ModelAndView partnersCustomerEdit(@RequestParam("customertype")String customerType,@RequestParam("customerid")Long customerId, ModelAndView modelAndView) {


        modelAndView.setViewName("app/app");

        if(customerType.equals("CompanyCustomer"))
        {
            CompanyCustomer companyCustomer = companyCustomerRepository.findOne(customerId);
            modelAndView.addObject("companyCustomer",companyCustomer);
            modelAndView.addObject("fragment", this.companyCustomerCreate);
        }
        if(customerType.equals("IndividualCustomer"))
        {
            IndividualCustomer individualCustomer = individualCustomerRepository.findOne(customerId);
            modelAndView.addObject("individualCustomer",individualCustomer);
            modelAndView.addObject("fragment", this.individualCustomerCreate);
        }
        if(customerType.equals("PrivateEntrepreneurCustomer"))
        {
            PrivateEntrepreneurCustomer privateEntrepreneurCustomer = privateEntrepreneurCustomerRepository.findOne(customerId);
            modelAndView.addObject("privateEntrepreneurCustomer",privateEntrepreneurCustomer);
            modelAndView.addObject("fragment", this.privateEntrepreneurCustomerCreate);
        }



        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }
    @GetMapping(value = "/customer/delete")
    public ModelAndView partnersCustomerDelete(@RequestParam("customertype")String customerType,@RequestParam("customerid")Long customerId, ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");

        if(customerType.equals("CompanyCustomer"))
        {
            companyCustomerRepository.delete(customerId);

        }
        if(customerType.equals("IndividualCustomer"))
        {

            individualCustomerRepository.delete(customerId);
        }
        if(customerType.equals("PrivateEntrepreneurCustomer"))
        {

            privateEntrepreneurCustomerRepository.delete(customerId);
        }

        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.partnerCustomers);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);

        return modelAndView;
    }


    @GetMapping(value = "/customer/sale")
    public ModelAndView customerSale(ModelAndView modelAndView, HttpSession httpSession) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.partnerCustomerSale);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        return modelAndView;
    }
    @GetMapping(value = "/customer/payment")
    public ModelAndView customerPayment(ModelAndView modelAndView, HttpSession httpSession) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.partnerCustomerPayment);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        return modelAndView;
    }


    @PostMapping(value = "/customer/payment/show")
    public @ResponseBody ArrayList customerPaymentShow(@RequestBody DateRange dateRange ) {

        ArrayList<CustomerPaymentShow> showResult = new ArrayList();
        if (dateRange.isShowAll()) {

            List<CashInFromSaleOfGoods> cashInFromSaleOfGoodss = (List<CashInFromSaleOfGoods>) cashInFromSaleOfGoodsRepository.findAll();
            List<CashInFromServiceProvision> cashInFromServiceProvisions = (List<CashInFromServiceProvision>) cashInFromServiceProvisionRepository.findAll();
            for(CashInFromSaleOfGoods each:cashInFromSaleOfGoodss) {

                showResult.add(new CustomerPaymentShow(each.getId(),each.getWalletIn().getInDate(),each.getWalletIn().getInCash(),each.getSupplier().getName(),each.getWalletIn().getInType()));
            }
            for(CashInFromServiceProvision each:cashInFromServiceProvisions) {

                showResult.add(new CustomerPaymentShow(each.getId(),each.getWalletIn().getInDate(),each.getWalletIn().getInCash(),each.getSupplier().getName(),each.getWalletIn().getInType()));
            }
            return showResult;
        }else if (dateRange.getStart() != null && dateRange.getEnd() == null) {
            List<CashInFromSaleOfGoods> cashInFromSaleOfGoodss = (List<CashInFromSaleOfGoods>) cashInFromSaleOfGoodsRepository.findByRangeStart(dateRange.getStart());
            List<CashInFromServiceProvision> cashInFromServiceProvisions = (List<CashInFromServiceProvision>) cashInFromServiceProvisionRepository.findByRangeStart(dateRange.getStart());
            for(CashInFromSaleOfGoods each:cashInFromSaleOfGoodss) {

                showResult.add(new CustomerPaymentShow(each.getId(),each.getWalletIn().getInDate(),each.getWalletIn().getInCash(),each.getSupplier().getName(),each.getWalletIn().getInType()));
            }
            for(CashInFromServiceProvision each:cashInFromServiceProvisions) {

                showResult.add(new CustomerPaymentShow(each.getId(),each.getWalletIn().getInDate(),each.getWalletIn().getInCash(),each.getSupplier().getName(),each.getWalletIn().getInType()));
            }
            return showResult;
        }else if (dateRange.getStart() == null && dateRange.getEnd() != null) {
            List<CashInFromSaleOfGoods> cashInFromSaleOfGoodss = (List<CashInFromSaleOfGoods>) cashInFromSaleOfGoodsRepository.findByRangeEnd(dateRange.getEnd());
            List<CashInFromServiceProvision> cashInFromServiceProvisions = (List<CashInFromServiceProvision>) cashInFromServiceProvisionRepository.findByRangeEnd(dateRange.getEnd());
            for(CashInFromSaleOfGoods each:cashInFromSaleOfGoodss) {

                showResult.add(new CustomerPaymentShow(each.getId(),each.getWalletIn().getInDate(),each.getWalletIn().getInCash(),each.getSupplier().getName(),each.getWalletIn().getInType()));
            }
            for(CashInFromServiceProvision each:cashInFromServiceProvisions) {

                showResult.add(new CustomerPaymentShow(each.getId(),each.getWalletIn().getInDate(),each.getWalletIn().getInCash(),each.getSupplier().getName(),each.getWalletIn().getInType()));
            }
            return showResult;
        }else if (dateRange.getStart() != null && dateRange.getEnd() != null) {
            List<CashInFromSaleOfGoods> cashInFromSaleOfGoodss = (List<CashInFromSaleOfGoods>) cashInFromSaleOfGoodsRepository.findByRange(dateRange.getStart(),dateRange.getEnd());
            List<CashInFromServiceProvision> cashInFromServiceProvisions = (List<CashInFromServiceProvision>) cashInFromServiceProvisionRepository.findByRange(dateRange.getStart(),dateRange.getEnd());
            for(CashInFromSaleOfGoods each:cashInFromSaleOfGoodss) {

                showResult.add(new CustomerPaymentShow(each.getId(),each.getWalletIn().getInDate(),each.getWalletIn().getInCash(),each.getSupplier().getName(),each.getWalletIn().getInType()));
            }
            for(CashInFromServiceProvision each:cashInFromServiceProvisions) {

                showResult.add(new CustomerPaymentShow(each.getId(),each.getWalletIn().getInDate(),each.getWalletIn().getInCash(),each.getSupplier().getName(),each.getWalletIn().getInType()));
            }
            return showResult;
        }
        return showResult;
    }

    @PostMapping(value = "/customer/sale/show")
    public @ResponseBody ArrayList customerSaleShow(@RequestBody DateRange dateRange ) {
        List<Sale> sales = new ArrayList();
        ArrayList<SalesShow> showResult = new ArrayList();
        if (dateRange.isShowAll()) {
            sales = (List<Sale>) saleRepository.findAll();
            for(Sale each:sales) {

                showResult.add(new SalesShow(each.getId(),each.getSaleNumber(),each.getSaleDate(),each.getSalesAmount(),each.getSupplier().getName()));
            }
            return showResult;
        }else

        if (dateRange.getStart() != null && dateRange.getEnd() == null) {
            sales = (List<Sale>) saleRepository.findByRangeStart(dateRange.getStart());
            for(Sale each:sales) {
                showResult.add(new SalesShow(each.getId(),each.getSaleNumber(),each.getSaleDate(),each.getSalesAmount(),each.getSupplier().getName()));
            }
            return showResult;
        }else
        if (dateRange.getStart() == null && dateRange.getEnd() != null) {
            sales = (List<Sale>) saleRepository.findByEnd(dateRange.getEnd());
            for(Sale each:sales) {
                showResult.add(new SalesShow(each.getId(),each.getSaleNumber(),each.getSaleDate(),each.getSalesAmount(),each.getSupplier().getName()));
            }
            return showResult;
        }else
        if (dateRange.getStart() != null && dateRange.getEnd() != null) {
            sales = (List<Sale>) saleRepository.findByRange(dateRange.getStart(),dateRange.getEnd());
            for(Sale each:sales) {
                showResult.add(new SalesShow(each.getId(),each.getSaleNumber(),each.getSaleDate(),each.getSalesAmount(),each.getSupplier().getName()));
            }
            return showResult;
        }
        return showResult;
    }



    @GetMapping(value = "/customer/create/sale")
    public ModelAndView customerSaleCreate(ModelAndView modelAndView, HttpSession httpSession) {
        Sale sale = new Sale();
        sale.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        customerList = new ArrayList();
        List<CompanyCustomer> companyCustomers =(List<CompanyCustomer>) companyCustomerRepository.findAll();
        List<IndividualCustomer> individualCustomers = (List<IndividualCustomer>) individualCustomerRepository.findAll();
        List<PrivateEntrepreneurCustomer>  privateEntrepreneurCustomers  = (List<PrivateEntrepreneurCustomer>)privateEntrepreneurCustomerRepository.findAll();


        for(CompanyCustomer companyCustomer :companyCustomers){
            customerList.add(new Customer(companyCustomer.getId(),"CompanyCustomer",companyCustomer.getName()));
        }
        for(IndividualCustomer individualCustomer :individualCustomers){
            customerList.add(new Customer(individualCustomer.getId(),"IndividualCustomer",individualCustomer.getName()));
        }
        for(PrivateEntrepreneurCustomer privateEntrepreneurCustomer:privateEntrepreneurCustomers){
            customerList.add(new Customer(privateEntrepreneurCustomer.getId(),"PrivateEntrepreneurCustomer",privateEntrepreneurCustomer.getName()));
        }

        modelAndView.addObject("sale",sale);
        modelAndView.addObject("customerList",customerList);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.customerSaleCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }

    @GetMapping(value = "/customer/sale/edit")
    public ModelAndView customerSaleEdit(@RequestParam("saleid")Long saleId, ModelAndView modelAndView, HttpSession httpSession) {
        Sale sale = saleRepository.findOne(saleId);
        modelAndView.setViewName("app/app");
        customerList = new ArrayList();
        List<CompanyCustomer> companyCustomers =(List<CompanyCustomer>) companyCustomerRepository.findAll();
        List<IndividualCustomer> individualCustomers = (List<IndividualCustomer>) individualCustomerRepository.findAll();
        List<PrivateEntrepreneurCustomer>  privateEntrepreneurCustomers  = (List<PrivateEntrepreneurCustomer>)privateEntrepreneurCustomerRepository.findAll();


        for(CompanyCustomer companyCustomer :companyCustomers){
            customerList.add(new Customer(companyCustomer.getId(),"CompanyCustomer",companyCustomer.getName()));
        }
        for(IndividualCustomer individualCustomer :individualCustomers){
            customerList.add(new Customer(individualCustomer.getId(),"IndividualCustomer",individualCustomer.getName()));
        }
        for(PrivateEntrepreneurCustomer privateEntrepreneurCustomer:privateEntrepreneurCustomers){
            customerList.add(new Customer(privateEntrepreneurCustomer.getId(),"PrivateEntrepreneurCustomer",privateEntrepreneurCustomer.getName()));
        }

        modelAndView.addObject("sale",sale);
        modelAndView.addObject("customerList",customerList);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.customerSaleCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }
    @GetMapping(value = "/customer/sale/delete")
    public ModelAndView customerSaleDelete(@RequestParam("saleid")Long saleId, ModelAndView modelAndView, HttpSession httpSession) {

        saleRepository.delete(saleId);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.partnerCustomerSale);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value = "/customer/create/sale")
    public ModelAndView CustomerSaleCreate(@Valid Sale sale, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("sale", sale);
            modelAndView.addObject("customerList",customerList);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("appFragment", this.customerFragments);
            modelAndView.addObject("fragment", this.customerSaleCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
            return modelAndView;
        }

        if(sale.getCustomerType().equals("CompanyCustomer")){
            sale.setCompanyCustomer(companyCustomerRepository.findOne(sale.getCustomerId()));
        }
        if(sale.getCustomerType().equals("IndividualCustomer")){
            sale.setIndividualCustomer(individualCustomerRepository.findOne(sale.getCustomerId()));
        }
        if(sale.getCustomerType().equals("PrivateEntrepreneurCustomer")){
            sale.setPrivateEntrepreneurCustomer(privateEntrepreneurCustomerRepository.findOne(sale.getCustomerId()));
        }

        if(sale.getPersonalWalletIn()==true){

            if (sale.getTypeSale().equals("goods")){
               CashInFromSaleOfGoods cashInFromSaleOfGoods = new CashInFromSaleOfGoods();
               WalletIn walletIn = new WalletIn();
               walletIn.setOrganization(sale.getOrganization());
               walletIn.setInCash(sale.getSalesAmount());
               walletIn.setInDate(sale.getSaleDate());
                walletIn.setInType("CashInFromSaleOfGoods");
                if(sale.getCustomerType().equals("CompanyCustomer")){
                    cashInFromSaleOfGoods.setCompanyCustomer(sale.getCompanyCustomer());
                }
                if(sale.getCustomerType().equals("IndividualCustomer")){
                    cashInFromSaleOfGoods.setIndividualCustomer(sale.getIndividualCustomer());
                }
                if(sale.getCustomerType().equals("PrivateEntrepreneurCustomer")){
                    cashInFromSaleOfGoods.setPrivateEntrepreneurCustomer(sale.getPrivateEntrepreneurCustomer());
                }
               cashInFromSaleOfGoods.setWalletIn(walletIn);
                cashInFromSaleOfGoodsRepository.save(cashInFromSaleOfGoods);
            }
            if (sale.getTypeSale().equals("service")){
                CashInFromServiceProvision cashInFromServiceProvision = new CashInFromServiceProvision();
                WalletIn walletIn = new WalletIn();
                walletIn.setOrganization(sale.getOrganization());
                walletIn.setInCash(sale.getSalesAmount());
                walletIn.setInDate(sale.getSaleDate());
                walletIn.setInType("CashInFromServiceProvision");
                if(sale.getCustomerType().equals("CompanyCustomer")){
                    cashInFromServiceProvision.setCompanyCustomer(sale.getCompanyCustomer());
                }
                if(sale.getCustomerType().equals("IndividualCustomer")){
                    cashInFromServiceProvision.setIndividualCustomer(sale.getIndividualCustomer());
                }
                if(sale.getCustomerType().equals("PrivateEntrepreneurCustomer")){
                    cashInFromServiceProvision.setPrivateEntrepreneurCustomer(sale.getPrivateEntrepreneurCustomer());
                }
                cashInFromServiceProvision.setWalletIn(walletIn);
                cashInFromServiceProvisionRepository.save(cashInFromServiceProvision);
            }
        }

        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.customerFragments);
        modelAndView.addObject("fragment", this.partnerCustomerSale);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        saleRepository.save(sale);
        return  modelAndView;
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



    @GetMapping(value = "/customer/debt")
    public ModelAndView partnerCustomerDebt( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("activeTab", "customers");
        modelAndView.addObject("appFragment", this.partnerFragments);
        modelAndView.addObject("fragment", this.partnerCustomerDebt);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);


        return modelAndView;
    }
    @GetMapping(value = "/customer/debt/details")
    public ModelAndView partnerCustomerDebtDetails(@RequestParam("customerType")String customerType,@RequestParam("customerId")Long customerId, ModelAndView modelAndView) {

            modelAndView.setViewName("app/app");
            if(customerType.equals("CompanyCustomer")) {
                CompanyCustomer companyCustomer = companyCustomerRepository.findOne(customerId);
                modelAndView.addObject("customer",companyCustomer);
            }

        if(customerType.equals("IndividualCustomer")) {
            IndividualCustomer individualCustomer = individualCustomerRepository.findOne(customerId);
            modelAndView.addObject("customer",individualCustomer);
        }


        if(customerType.equals("PrivateEntrepreneurCustomer")) {
            PrivateEntrepreneurCustomer privateEntrepreneurCustomer = privateEntrepreneurCustomerRepository.findOne(customerId);
            modelAndView.addObject("customer",privateEntrepreneurCustomer);
        }

        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.partnerFragments);
        modelAndView.addObject("fragment", this.partnerCustomerDebtDetails);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);




        return modelAndView;
    }

    @PostMapping(value = "customer/debt/details/show")
    public @ResponseBody ArrayList supplierDebtDetails(@RequestBody DateRangByType dateRangByType ) {
        ArrayList<DebtDetailsShow> debtDetails = new ArrayList<>();
        DebtDetailsShow debtDetail;

        List<CashInFromSaleOfGoods> cashInFromSaleOfGoods = null;
        List<CashInFromServiceProvision> cashInFromServiceProvisions = null;
        List<Sale> sales = null;

        if (dateRangByType.getStart() != null && dateRangByType.getEnd() != null) {

            if (dateRangByType.getType().equals("CompanyCustomer")) {
                cashInFromSaleOfGoods = (List<CashInFromSaleOfGoods>) cashInFromSaleOfGoodsRepository.findByRangeAndCompanyCustomerId(dateRangByType.getStart(), dateRangByType.getEnd(), dateRangByType.getId());
                cashInFromServiceProvisions = (List<CashInFromServiceProvision>) cashInFromServiceProvisionRepository.findByRangeAndCompanyCustomerId(dateRangByType.getStart(), dateRangByType.getEnd(), dateRangByType.getId());
                sales = (List<Sale>) saleRepository.findByRangeAndCompanyCustomerId(dateRangByType.getStart(), dateRangByType.getEnd(), dateRangByType.getId());
            }
            if (dateRangByType.getType().equals("IndividualCustomer")) {
                cashInFromSaleOfGoods = (List<CashInFromSaleOfGoods>) cashInFromSaleOfGoodsRepository.findByRangeAndIndividualCustomerId(dateRangByType.getStart(), dateRangByType.getEnd(), dateRangByType.getId());
                cashInFromServiceProvisions = (List<CashInFromServiceProvision>) cashInFromServiceProvisionRepository.findByRangeAndIndividualCustomerId(dateRangByType.getStart(), dateRangByType.getEnd(), dateRangByType.getId());
                sales = (List<Sale>) saleRepository.findByRangeAndIndividualCustomerId(dateRangByType.getStart(), dateRangByType.getEnd(), dateRangByType.getId());
            }
            if (dateRangByType.getType().equals("PrivateEntrepreneurCustomer")) {
                cashInFromSaleOfGoods = (List<CashInFromSaleOfGoods>) cashInFromSaleOfGoodsRepository.findByRangeAndPrivateEntrepreneurCustomerId(dateRangByType.getStart(), dateRangByType.getEnd(), dateRangByType.getId());
                cashInFromServiceProvisions = (List<CashInFromServiceProvision>) cashInFromServiceProvisionRepository.findByRangeAndPrivateEntrepreneurCustomerId(dateRangByType.getStart(), dateRangByType.getEnd(), dateRangByType.getId());
                sales = (List<Sale>) saleRepository.findByRangeAndPrivateEntrepreneurCustomerId(dateRangByType.getStart(), dateRangByType.getEnd(), dateRangByType.getId());

            }

            for (Sale sale : sales) {

                debtDetail = new DebtDetailsShow(sale.getSaleDate(),
                        "Sale",
                        null,
                        Integer.valueOf(sale.getSalesAmount()),
                        sale.getId()
                );
                debtDetails.add(debtDetail);
            }


            for (CashInFromSaleOfGoods cashInFromSaleOfGood : cashInFromSaleOfGoods) {

                debtDetail = new DebtDetailsShow(cashInFromSaleOfGood.getWalletIn().getInDate(),
                        cashInFromSaleOfGood.getWalletIn().getInType(),
                        Integer.parseInt(cashInFromSaleOfGood.getWalletIn().getInCash()),
                        null,
                        cashInFromSaleOfGood.getId()
                );
                debtDetails.add(debtDetail);
            }
            for (CashInFromServiceProvision cashInFromServiceProvision : cashInFromServiceProvisions) {

                debtDetail = new DebtDetailsShow(cashInFromServiceProvision.getWalletIn().getInDate(),
                        cashInFromServiceProvision.getWalletIn().getInType(),
                        Integer.parseInt(cashInFromServiceProvision.getWalletIn().getInCash()),
                        null,
                        cashInFromServiceProvision.getId()
                );
                debtDetails.add(debtDetail);
            }

            Collections.sort(debtDetails);
            return debtDetails;
        }
        return null;
    }

    @PostMapping("/customer/debt/show")
    public @ResponseBody
    ArrayList customerDebtShow(@RequestBody DateRange dateRange ) {
        List<GetWaletIn> temp = new ArrayList();
        ArrayList showResult = new ArrayList();
        if (dateRange.getStart() != null) {

            List<CompanyCustomer> companyCustomers = (List<CompanyCustomer>) companyCustomerRepository.findByHvhhNotNull();
            List<IndividualCustomer> individualCustomers = (List<IndividualCustomer>) individualCustomerRepository.findByHchNotNull();
            List<PrivateEntrepreneurCustomer> privateEntrepreneurCustomers = (List<PrivateEntrepreneurCustomer>) privateEntrepreneurCustomerRepository.findAll();

            List<CashInFromSaleOfGoods> cashInFromSaleOfGoods = (List<CashInFromSaleOfGoods>) cashInFromSaleOfGoodsRepository.findByRangeEnd(dateRange.getStart());
            List<CashInFromServiceProvision> cashInFromServiceProvisions = (List<CashInFromServiceProvision>) cashInFromServiceProvisionRepository.findByRangeEnd(dateRange.getStart());

            List<Sale> sales = (List<Sale>) saleRepository.findByRangeforDebt(dateRange.getStart());

            ArrayList<Debt> debts = new ArrayList<>();
            Debt debt = new Debt();

            for (CompanyCustomer companyCustomer : companyCustomers) {
                debt = new Debt();
                debt.setName(companyCustomer.getName());
                debt.setId(companyCustomer.getId());
                debt.setType("CompanyCustomer");
                debt.setDebt(companyCustomer.getOpeningBalanceType().equals("debt") ? Integer.parseInt(companyCustomer.getOpeningBalance()) : 0);
                debt.setPrepayment(companyCustomer.getOpeningBalanceType().equals("prepaid") ? Integer.parseInt(companyCustomer.getOpeningBalance()) : 0);

                for (Sale sale : sales) {
                    if (sale.getCompanyCustomer() != null)
                        if (sale.getCompanyCustomer().getId() == companyCustomer.getId()) {

                            debt.setDebt(debt.getDebt() + Integer.valueOf(sale.getSalesAmount()));

                        }
                }

                for (CashInFromSaleOfGoods cashInFromSaleOfGood : cashInFromSaleOfGoods) {
                    if (cashInFromSaleOfGood.getCompanyCustomer() != null)
                        if (cashInFromSaleOfGood.getCompanyCustomer().getId() == companyCustomer.getId()) {

                            debt.setPrepayment(debt.getPrepayment() + Integer.valueOf(cashInFromSaleOfGood.getWalletIn().getInCash()));

                        }
                }

                for (CashInFromServiceProvision cashInFromServiceProvision : cashInFromServiceProvisions) {
                    if (cashInFromServiceProvision.getCompanyCustomer() != null)
                        if (cashInFromServiceProvision.getCompanyCustomer().getId() == companyCustomer.getId()) {

                            debt.setPrepayment(debt.getPrepayment() + Integer.valueOf(cashInFromServiceProvision.getWalletIn().getInCash()));

                        }
                }

                if (debt.getPrepayment() - debt.getDebt() < 0) {
                    debt.setDebt(Math.abs(debt.getPrepayment() - debt.getDebt()));
                    debt.setPrepayment(0);
                } else {
                    debt.setPrepayment(debt.getPrepayment() - debt.getDebt());
                    debt.setDebt(0);
                }
                debts.add(debt);

            }

            for (IndividualCustomer individualCustomer : individualCustomers) {
                debt = new Debt();
                debt.setName(individualCustomer.getName());
                debt.setId(individualCustomer.getId());
                debt.setType("IndividualCustomer");
                debt.setDebt(individualCustomer.getOpeningBalanceType().equals("debt") ? Integer.parseInt(individualCustomer.getOpeningBalance()) : 0);
                debt.setPrepayment(individualCustomer.getOpeningBalanceType().equals("prepaid") ? Integer.parseInt(individualCustomer.getOpeningBalance()) : 0);

                for (Sale sale : sales) {
                    if (sale.getIndividualCustomer() != null)
                        if (sale.getIndividualCustomer().getId() == individualCustomer.getId()) {
                            debt.setDebt(debt.getDebt() + Integer.valueOf(sale.getSalesAmount()));
                        }
                }

                for (CashInFromSaleOfGoods cashInFromSaleOfGood : cashInFromSaleOfGoods) {
                    if (cashInFromSaleOfGood.getIndividualCustomer() != null)
                        if (cashInFromSaleOfGood.getIndividualCustomer().getId() == individualCustomer.getId()) {
                            debt.setPrepayment(debt.getPrepayment() + Integer.valueOf(cashInFromSaleOfGood.getWalletIn().getInCash()));
                        }
                }
                for (CashInFromServiceProvision cashInFromServiceProvision : cashInFromServiceProvisions) {
                    if (cashInFromServiceProvision.getIndividualCustomer() != null)
                        if (cashInFromServiceProvision.getIndividualCustomer().getId() == individualCustomer.getId()) {
                            debt.setPrepayment(debt.getPrepayment() + Integer.valueOf(cashInFromServiceProvision.getWalletIn().getInCash()));
                        }
                }
                if (debt.getPrepayment() - debt.getDebt() < 0) {
                    debt.setDebt(Math.abs(debt.getPrepayment() - debt.getDebt()));
                    debt.setPrepayment(0);
                } else {
                    debt.setPrepayment(debt.getPrepayment() - debt.getDebt());
                    debt.setDebt(0);
                }

                debts.add(debt);
            }
            for (PrivateEntrepreneurCustomer privateEntrepreneurCustomer : privateEntrepreneurCustomers) {
                debt = new Debt();
                debt.setName(privateEntrepreneurCustomer.getName());
                debt.setId(privateEntrepreneurCustomer.getId());
                debt.setType("PrivateEntrepreneurCustomer");
                debt.setDebt(privateEntrepreneurCustomer.getOpeningBalanceType().equals("debt") ? Integer.parseInt(privateEntrepreneurCustomer.getOpeningBalance()) : 0);
                debt.setPrepayment(privateEntrepreneurCustomer.getOpeningBalanceType().equals("prepaid") ? Integer.parseInt(privateEntrepreneurCustomer.getOpeningBalance()) : 0);


                for (Sale sale : sales) {
                    if (sale.getPrivateEntrepreneurCustomer() != null)
                        if (sale.getPrivateEntrepreneurCustomer().getId() == privateEntrepreneurCustomer.getId()) {

                            debt.setDebt(debt.getDebt() + Integer.valueOf(sale.getSalesAmount()));

                        }
                }
                for (CashInFromSaleOfGoods cashInFromSaleOfGood : cashInFromSaleOfGoods) {
                    if (cashInFromSaleOfGood.getPrivateEntrepreneurCustomer() != null)
                        if (cashInFromSaleOfGood.getPrivateEntrepreneurCustomer().getId() == privateEntrepreneurCustomer.getId()) {

                            debt.setPrepayment(debt.getPrepayment() + Integer.valueOf(cashInFromSaleOfGood.getWalletIn().getInCash()));

                        }
                }
                for (CashInFromServiceProvision cashInFromServiceProvision : cashInFromServiceProvisions) {
                    if (cashInFromServiceProvision.getPrivateEntrepreneurCustomer() != null)
                        if (cashInFromServiceProvision.getPrivateEntrepreneurCustomer().getId() == privateEntrepreneurCustomer.getId()) {
                            debt.setPrepayment(debt.getPrepayment() + Integer.valueOf(cashInFromServiceProvision.getWalletIn().getInCash()));
                        }
                }
                if (debt.getPrepayment() - debt.getDebt() < 0) {
                    debt.setDebt(Math.abs(debt.getPrepayment() - debt.getDebt()));
                    debt.setPrepayment(0);
                } else {
                    debt.setPrepayment(debt.getPrepayment() - debt.getDebt());
                    debt.setDebt(0);
                }

                debts.add(debt);

            }

             return debts;
        }
        return null;
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


    @GetMapping(value = "/otherpartner/edit")
    public ModelAndView partnersOtherPartnerEdit(@RequestParam("otherpartnertype")String otherPartnerType,@RequestParam("otherpartnerid")Long otherPartnerId, ModelAndView modelAndView) {


        modelAndView.setViewName("app/app");

        if(otherPartnerType.equals("CompanyOtherPartner"))
        {
            CompanyOtherPartner companyOtherPartner = companyOtherPartnerRepository.findOne(otherPartnerId);
            modelAndView.addObject("fragment", this.companyOtherPartnerCreate);
            modelAndView.addObject("companyOtherPartner", companyOtherPartner);

        }
        if(otherPartnerType.equals("IndividualOtherPartner"))
        {
            IndividualOtherPartner individualOtherPartner = individualOtherPartnerRepository.findOne(otherPartnerId);
            modelAndView.addObject("fragment", this.individualOtherPartnerCreate);
            modelAndView.addObject("individualOtherPartner", individualOtherPartner);

        }
        if(otherPartnerType.equals("PrivateEntrepreneurOtherPartner"))
        {

            PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner = privateEntrepreneurOtherPartnerRepository.findOne(otherPartnerId);
            modelAndView.addObject("fragment", this.privateEntrepreneurOtherPartnerCreate);
            modelAndView.addObject("privateEntrepreneurOtherPartner", privateEntrepreneurOtherPartner);

        }



        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.otherPartnerFragments);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);


        return modelAndView;
    }
    @GetMapping(value = "/otherpartner/delete")
    public ModelAndView partnersOtherPartnerDelete(@RequestParam("otherpartnertype")String otherPartnerType,@RequestParam("otherpartnerid")Long otherPartnerId, ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");

        if(otherPartnerType.equals("CompanyOtherPartner"))
        {
            companyOtherPartnerRepository.delete(otherPartnerId);

        }
        if(otherPartnerType.equals("IndividualOtherPartner"))
        {

            individualOtherPartnerRepository.delete(otherPartnerId);
        }
        if(otherPartnerType.equals("PrivateEntrepreneurOtherPartner"))
        {

            privateEntrepreneurOtherPartnerRepository.delete(otherPartnerId);
        }

        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.otherPartnerFragments);
        modelAndView.addObject("fragment", this.partnerOtherPartner);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);

        return modelAndView;
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

    @PostMapping("/otherpartner/show")
    public @ResponseBody Set partnersOtherPartnerShow() {

        List<GeneralMethods> otherPartners = new ArrayList();
        Boolean temp = false;
        PartnerCustomerShow partnerCustomerShow = null;
        Set<PartnerCustomerShow> showResult = new HashSet();

        otherPartners.addAll((ArrayList) companyOtherPartnerRepository.findAll());
        otherPartners.addAll((ArrayList) individualOtherPartnerRepository.findAll());
        otherPartners.addAll((ArrayList) privateEntrepreneurOtherPartnerRepository.findAll());


        for(GeneralMethods otherPartner : otherPartners) {
            if (otherPartner.getHvhh() != null || otherPartner.getHch()!=null) {
                partnerCustomerShow = new PartnerCustomerShow(otherPartner.getId(), otherPartner.getName(), otherPartner.getPhoneNumber(), otherPartner.getAddress(), otherPartner.getHvhh(), true, otherPartner.getClass().getSimpleName());
                showResult.add(partnerCustomerShow);
            } else {
                partnerCustomerShow = new PartnerCustomerShow(otherPartner.getId(), otherPartner.getName(), otherPartner.getPhoneNumber(), otherPartner.getAddress(), otherPartner.getHvhh(), false, otherPartner.getClass().getSimpleName());
                showResult.add(partnerCustomerShow);
            }

        }
        return showResult;
    }
    @GetMapping( value = "/otherpartner/payment")
    public ModelAndView partnerOtherPartnerDebt( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.otherPartnerFragments);
        modelAndView.addObject("fragment", this.partnerOtherPartnerPayment);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);


        return modelAndView;
    }

    @PostMapping(value = "/otherpartner/payment/show")
    public @ResponseBody ArrayList otherpartnerPaymentShow(@RequestBody DateRange dateRange ) {
        List<GetWaletIn> temp = new ArrayList();
        List<GetWaletOut> temp2 = new ArrayList();
        ArrayList<PaymentShow> showResult = new ArrayList();
        if (dateRange.isShowAll()) {
            temp.addAll((ArrayList)cashInFromCreditRepository.findAll());
            temp.addAll((ArrayList)cashInFromLoanRepository.findAll());
            temp2.addAll((ArrayList)cashOutForCreditPaymentRepository.findAll());
            temp2.addAll((ArrayList)cashOutForLoanPaymentRepository.findAll());
            temp2.addAll((ArrayList)cashOutForRedemptionPercentRepository.findAll());
            for(GetWaletIn each:temp) {

                showResult.add(new PaymentShow(each.getCashInId(),each.getWalletInImpl().getInDate(),Long.parseLong(each.getWalletInImpl().getInCash()),each.getSupplier().getName(),each.getWalletInImpl().getInType()));
            }
            for(GetWaletOut each:temp2) {

                showResult.add(new PaymentShow(each.getCashOutId(),each.getWalletOutImpl().getOutDate(),Long.parseLong(each.getWalletOutImpl().getOutCash()),each.getSupplier().getName(),each.getWalletOutImpl().getOutType()));
            }
            return showResult;
        }else

        if (dateRange.getStart() != null && dateRange.getEnd() == null) {

            temp.addAll(cashInFromCreditRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromLoanRepository.findByRangeStart(dateRange.getStart()));
            temp2.addAll(cashOutForCreditPaymentRepository.findByRangeStart(dateRange.getStart()));
            temp2.addAll(cashOutForLoanPaymentRepository.findByRangeStart(dateRange.getStart()));
            temp2.addAll(cashOutForRedemptionPercentRepository.findByRangeStart(dateRange.getStart()));
            for(GetWaletIn each:temp) {

                showResult.add(new PaymentShow(each.getCashInId(),each.getWalletInImpl().getInDate(),Long.parseLong(each.getWalletInImpl().getInCash()),each.getSupplier().getName(),each.getWalletInImpl().getInType()));
            }
            for(GetWaletOut each:temp2) {

                showResult.add(new PaymentShow(each.getCashOutId(),each.getWalletOutImpl().getOutDate(),Long.parseLong(each.getWalletOutImpl().getOutCash()),each.getSupplier().getName(),each.getWalletOutImpl().getOutType()));
            }

            return showResult;
        }else
        if (dateRange.getStart() == null && dateRange.getEnd() != null) {

            temp.addAll(cashInFromCreditRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromLoanRepository.findByEnd(dateRange.getEnd()));
            temp2.addAll(cashOutForCreditPaymentRepository.findByRangeEnd(dateRange.getEnd()));
            temp2.addAll(cashOutForLoanPaymentRepository.findByRangeEnd(dateRange.getEnd()));
            temp2.addAll(cashOutForRedemptionPercentRepository.findByRangeEnd(dateRange.getEnd()));
            for(GetWaletIn each:temp) {

                showResult.add(new PaymentShow(each.getCashInId(),each.getWalletInImpl().getInDate(),Long.parseLong(each.getWalletInImpl().getInCash()),each.getSupplier().getName(),each.getWalletInImpl().getInType()));
            }
            for(GetWaletOut each:temp2) {

                showResult.add(new PaymentShow(each.getCashOutId(),each.getWalletOutImpl().getOutDate(),Long.parseLong(each.getWalletOutImpl().getOutCash()),each.getSupplier().getName(),each.getWalletOutImpl().getOutType()));
            }

            return showResult;
        }else
        if (dateRange.getStart() != null && dateRange.getEnd() != null) {


            temp.addAll(cashInFromCreditRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
            temp.addAll(cashInFromLoanRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
            temp2.addAll(cashOutForCreditPaymentRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            temp2.addAll(cashOutForLoanPaymentRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            temp2.addAll(cashOutForRedemptionPercentRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            for(GetWaletIn each:temp) {

                showResult.add(new PaymentShow(each.getCashInId(),each.getWalletInImpl().getInDate(),Long.parseLong(each.getWalletInImpl().getInCash()),each.getSupplier().getName(),each.getWalletInImpl().getInType()));
            }
            for(GetWaletOut each:temp2) {

                showResult.add(new PaymentShow(each.getCashOutId(),each.getWalletOutImpl().getOutDate(),Long.parseLong(each.getWalletOutImpl().getOutCash()),each.getSupplier().getName(),each.getWalletOutImpl().getOutType()));
            }
            return showResult;
        }
        return showResult;
    }

    /*--partner Otherpartner--*/



}




