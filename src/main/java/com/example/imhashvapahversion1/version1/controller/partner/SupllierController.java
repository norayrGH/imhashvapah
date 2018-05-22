package com.example.imhashvapahversion1.version1.controller.partner;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForGoodsProvider;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForRent;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForSerivceProvider;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.Debt;
import com.example.imhashvapahversion1.version1.Entity.enums.DateRange;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import com.example.imhashvapahversion1.version1.Entity.showClasses.FinancialMeans;
import com.example.imhashvapahversion1.version1.Entity.showClasses.SupplierShow;
import com.example.imhashvapahversion1.version1.controller.BaseController;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForGoodsProviderRepository;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForRentRepository;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForSerivceProviderRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.CompanySupplierRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.IndividualSupplierRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.PrivateEntrepreneurSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Controller
@RequestMapping("account/partner/supplier")
public class SupllierController extends BaseController {


    @Autowired
    CompanySupplierRepository companySupplierRepository;
    @Autowired
    IndividualSupplierRepository individualSupplierRepository;
    @Autowired
    PrivateEntrepreneurSupplierRepository privateEntrepreneurSupplierRepository;
    @Autowired
    CashOutForSerivceProviderRepository cashOutForSerivceProviderRepository;
    @Autowired
    CashOutForRentRepository cashOutForRentRepository;
    @Autowired

    CashOutForGoodsProviderRepository cashOutForGoodsProviderRepository;

    @GetMapping(value = "")
    public ModelAndView supplierPartner(ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerSupplier);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value = "/show")
    public @ResponseBody
    Set<SupplierShow> supplierPartnerShow() {
        List<GeneralMethods> temp2 = new ArrayList();
        Boolean temp = false;
        SupplierShow supplierShow = null;
        Set<SupplierShow> showResult = new HashSet();

        temp2.addAll((ArrayList)privateEntrepreneurSupplierRepository.findAll());
        temp2.addAll((ArrayList)companySupplierRepository.findAll());
        temp2.addAll((ArrayList)individualSupplierRepository.findAll());

        for(GeneralMethods each2 : temp2) {
            supplierShow = new SupplierShow(new Long[]{each2.getId()}, each2.getName(), each2.getPhoneNumber(), each2.getAddress(), each2.getHvhh(), true, each2.getClass().getSimpleName());
            showResult.add(supplierShow);
        }

        return showResult;
    }
    @GetMapping(value = "/debt")
    public ModelAndView partnerSupplierDebt( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerSupplierFragment);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value = "/debt/show")
    public @ResponseBody ArrayList supplierDebt(@RequestBody DateRange dateRange ) {
        ArrayList<Debt> debts= new ArrayList<>();
        Debt debt = new Debt();

        List<CompanySupplier> companySuppliers = (List<CompanySupplier>) companySupplierRepository.findAll();
        List<IndividualSupplier> individualSuppliers = (List<IndividualSupplier>) individualSupplierRepository.findAll();
        List<PrivateEntrepreneurSupplier> privateEntrepreneurSuppliers = (List<PrivateEntrepreneurSupplier>) privateEntrepreneurSupplierRepository.findAll();

        List<CashOutForGoodsProvider> cashOutForGoodsProviders = (List<CashOutForGoodsProvider>) cashOutForGoodsProviderRepository.findByRangeStart(dateRange.getStart());
        List<CashOutForSerivceProvider> cashOutForSerivceProviders = (List<CashOutForSerivceProvider>) cashOutForSerivceProviderRepository.findByRangeStart(dateRange.getStart());
        List<CashOutForRent> cashOutForRents = (List<CashOutForRent>) cashOutForRentRepository.findByRangeStart(dateRange.getStart());

        for(CompanySupplier companySupplier : companySuppliers)
        {
            debt.setName(companySupplier.getName());
            debt.setId(companySupplier.getId());
            debt.setType("CompanySupplier");
            for(CashOutForGoodsProvider cashOutForGoodsProvider :cashOutForGoodsProviders){
                if(cashOutForGoodsProvider.getCompanySupplier()!=null)
                    if(cashOutForGoodsProvider.getCompanySupplier().getId()==companySupplier.getId()){

                                debt.getPrepayment()+Integer.valueOf(cashOutForGoodsProvider.getWalletOut().getOutCash());


                    }
            }
            for(CashOutForSerivceProvider cashOutForSerivceProvider: cashOutForSerivceProviders){
                if(cashOutForSerivceProvider.getCompanySupplier()!=null)
                    if(cashOutForSerivceProvider.getCompanySupplier().getId()==companySupplier.getId()){
                        debt.setPrepayment(debt.getPrepayment()+Integer.valueOf(cashOutForSerivceProvider.getWalletOut().getOutCash()));
                    }
            }
            for(CashOutForRent cashOutForRent : cashOutForRents){
                if(cashOutForRent.getCompanySupplier()!=null)
                    if(cashOutForRent.getCompanySupplier().getId()==companySupplier.getId()){
                        debt.setPrepayment(debt.getPrepayment()+Integer.valueOf(cashOutForRent.getWalletOut().getOutCash()));
                    }
            }
            debts.add(debt);
            debt = new Debt();
        }
        for(IndividualSupplier individualSupplier : individualSuppliers)
        {
            debt.setName(individualSupplier.getName());
            debt.setId(individualSupplier.getId());
            debt.setType("IndividualSupplier");
            for(CashOutForGoodsProvider cashOutForGoodsProvider :cashOutForGoodsProviders){
                if(cashOutForGoodsProvider.getIndividualSupplier()!=null)
                    if(cashOutForGoodsProvider.getIndividualSupplier().getId()==individualSupplier.getId()){
                        debt.setPrepayment(debt.getPrepayment()+Integer.valueOf(cashOutForGoodsProvider.getWalletOut().getOutCash()));

                    }
            }
            for(CashOutForSerivceProvider cashOutForSerivceProvider: cashOutForSerivceProviders){
                if(cashOutForSerivceProvider.getIndividualSupplier()!=null)
                    if(cashOutForSerivceProvider.getIndividualSupplier().getId()==individualSupplier.getId()){
                        debt.setPrepayment(debt.getPrepayment()+Integer.valueOf(cashOutForSerivceProvider.getWalletOut().getOutCash()));
                    }
            }
            for(CashOutForRent cashOutForRent : cashOutForRents){
                if(cashOutForRent.getIndividualSupplier()!=null)
                    if(cashOutForRent.getIndividualSupplier().getId()==individualSupplier.getId()){
                        debt.setPrepayment(debt.getPrepayment()+Integer.valueOf(cashOutForRent.getWalletOut().getOutCash()));
                    }
            }
            debts.add(debt);
            debt = new Debt();
        }
        for(PrivateEntrepreneurSupplier privateEntrepreneurSupplier : privateEntrepreneurSuppliers)
        {
            debt.setName(privateEntrepreneurSupplier.getName());
            debt.setId(privateEntrepreneurSupplier.getId());
            debt.setType("PrivateEntrepreneurSupplier");
            for(CashOutForGoodsProvider cashOutForGoodsProvider :cashOutForGoodsProviders){
                if(cashOutForGoodsProvider.getPrivateEntrepreneurSupplier()!=null)
                    if(cashOutForGoodsProvider.getPrivateEntrepreneurSupplier().getId()==privateEntrepreneurSupplier.getId()){
                        debt.setPrepayment(debt.getPrepayment()+Integer.valueOf(cashOutForGoodsProvider.getWalletOut().getOutCash()));
                    }
            }
            for(CashOutForSerivceProvider cashOutForSerivceProvider: cashOutForSerivceProviders){
                if(cashOutForSerivceProvider.getPrivateEntrepreneurSupplier()!=null)
                    if(cashOutForSerivceProvider.getPrivateEntrepreneurSupplier().getId()==privateEntrepreneurSupplier.getId()){
                        debt.setPrepayment(debt.getPrepayment()+Integer.valueOf(cashOutForSerivceProvider.getWalletOut().getOutCash()));
                    }
            }
            for(CashOutForRent cashOutForRent : cashOutForRents){
                if(cashOutForRent.getPrivateEntrepreneurSupplier()!=null)
                    if(cashOutForRent.getPrivateEntrepreneurSupplier().getId()==privateEntrepreneurSupplier.getId()){
                        debt.setPrepayment(debt.getPrepayment()+Integer.valueOf(cashOutForRent.getWalletOut().getOutCash()));
                    }
            }
            debts.add(debt);
            debt = new Debt();
        }
        return debts;
    }

    @GetMapping(value = "/edit/privateentrepreneursupplier")
    public ModelAndView privateentrepreneursupplierEdit(@RequestParam("supplierId")Long supplierId, ModelAndView modelAndView, HttpSession httpSession){
        PrivateEntrepreneurSupplier privateEntrepreneurSupplier  = new PrivateEntrepreneurSupplier();
        privateEntrepreneurSupplier = privateEntrepreneurSupplierRepository.findOne(supplierId);

        privateEntrepreneurSupplier.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("privateEntrepreneurSupplier",privateEntrepreneurSupplier);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.privateEntrepreneurSupplierCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        return modelAndView;

    }
    @GetMapping(value = "/edit/individualsupplier")
    public ModelAndView individualsupplierdit(@RequestParam("supplierId")Long supplierId,ModelAndView modelAndView, HttpSession httpSession){

        IndividualSupplier individualSupplier;
        individualSupplier = individualSupplierRepository.findOne(supplierId);


        modelAndView.setViewName("app/app");
        modelAndView.addObject("individualSupplier",individualSupplier);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.individualSupplierCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        return modelAndView;

    }
    @GetMapping(value = "/edit/companysupplier")
    public ModelAndView companysupplierEdit(@RequestParam("supplierId")Long supplierId,ModelAndView modelAndView, HttpSession httpSession){
        CompanySupplier companySupplier = companySupplierRepository.findOne(supplierId);


        modelAndView.setViewName("app/app");
        modelAndView.addObject("companySupplier",companySupplier);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.companySupplierCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);

        return modelAndView;
    }

    @GetMapping( value ="/create/individualsupplier")
    public ModelAndView individualSupplierCreate(ModelAndView modelAndView, HttpSession httpSession) {
        IndividualSupplier individualSupplier = new IndividualSupplier();
        individualSupplier.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("individualSupplier",individualSupplier);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.individualSupplierCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value ="/create/individualsupplier")
    public ModelAndView individualSupplierCreate(@Valid IndividualSupplier individualSupplier, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

            modelAndView.addObject("individualSupplier",individualSupplier);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("fragment", this.individualSupplierCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerSupplier);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);
        individualSupplierRepository.save(individualSupplier);
        return  modelAndView;
    }

    @GetMapping( value ="/create/companysupplier")
    public ModelAndView companySupplierCreate(ModelAndView modelAndView, HttpSession httpSession) {
        CompanySupplier companySupplier = new CompanySupplier();
        companySupplier.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("companySupplier",companySupplier);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.companySupplierCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value ="/create/companysupplier")
    public ModelAndView companySupplierCreate(@Valid CompanySupplier companySupplier, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

            modelAndView.addObject("companySupplier",companySupplier);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("fragment", this.companySupplierCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerSupplier);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);
        companySupplierRepository.save(companySupplier);
        return  modelAndView;
    }

    @GetMapping(value = "/create/privateentrepreneursupplier")
    public ModelAndView privateEntrepreneurSupplierCreate(ModelAndView modelAndView, HttpSession httpSession) {
        PrivateEntrepreneurSupplier privateEntrepreneurSupplier = new PrivateEntrepreneurSupplier();
        privateEntrepreneurSupplier.setOrganization((Organization) httpSession.getAttribute("organizationId"));


        modelAndView.setViewName("app/app");
        modelAndView.addObject("privateEntrepreneurSupplier",privateEntrepreneurSupplier);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.privateEntrepreneurSupplierCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);

        return modelAndView;
    }
    @PostMapping(value ="/create/privateentrepreneursupplier")
    public ModelAndView privateEntrepreneurSupplierCreate(@Valid PrivateEntrepreneurSupplier privateEntrepreneurSupplier, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

            modelAndView.addObject("privateEntrepreneurSupplier",privateEntrepreneurSupplier);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("fragment", this.privateEntrepreneurSupplierCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);

            return  modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerSupplier);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        privateEntrepreneurSupplierRepository.save(privateEntrepreneurSupplier);
        return  modelAndView;
    }



}
