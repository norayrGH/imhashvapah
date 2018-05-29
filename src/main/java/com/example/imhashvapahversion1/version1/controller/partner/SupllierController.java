package com.example.imhashvapahversion1.version1.controller.partner;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForGoodsProvider;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForRent;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForSerivceProvider;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.Debt;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier.SupplierClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier.SupplierIndividual;
import com.example.imhashvapahversion1.version1.Entity.enums.DateRangByType;
import com.example.imhashvapahversion1.version1.Entity.enums.DateRange;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.CompanyCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.IndividualCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.purchase.PurchaseGoods;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.Supplier;
import com.example.imhashvapahversion1.version1.Entity.showClasses.DebtDetailsShow;
import com.example.imhashvapahversion1.version1.Entity.showClasses.FinancialMeans;
import com.example.imhashvapahversion1.version1.Entity.showClasses.SupplierShow;
import com.example.imhashvapahversion1.version1.controller.BaseController;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForGoodsProviderRepository;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForRentRepository;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForSerivceProviderRepository;
import com.example.imhashvapahversion1.version1.repository.purchase.PurchaseGoodsRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.*;
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
    SupplierClientOrganizationRepository supplierClientOrganizationRepository;
    @Autowired
    SupplierIndividualRepository supplierIndividualRepository;
    @Autowired
    PurchaseGoodsRepository purchaseGoodsRepository;
    @Autowired
    CashOutForGoodsProviderRepository cashOutForGoodsProviderRepository;

    private List<Supplier> suppliers=null;

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

        List<GeneralMethods> suppliers = new ArrayList();

        SupplierShow supplierShow = null;
        Set<SupplierShow> showResult = new HashSet();

        suppliers.addAll((ArrayList)privateEntrepreneurSupplierRepository.findAll());
        suppliers.addAll((ArrayList)companySupplierRepository.findAll());
        suppliers.addAll((ArrayList)individualSupplierRepository.findAll());


            for(GeneralMethods supplier : suppliers) {
                if (supplier.getHvhh() != null) {
                    supplierShow = new SupplierShow(supplier.getId(), supplier.getName(), supplier.getPhoneNumber(), supplier.getAddress(), supplier.getHvhh(), true, supplier.getClass().getSimpleName());
                    showResult.add(supplierShow);
                } else {
                    supplierShow = new SupplierShow(supplier.getId(), supplier.getName(), supplier.getPhoneNumber(), supplier.getAddress(), supplier.getHvhh(), false, supplier.getClass().getSimpleName());
                    showResult.add(supplierShow);
                }

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

        List<CompanySupplier> companySuppliers = (List<CompanySupplier>) companySupplierRepository.findByHvhhNotNull();
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
            debt.setDebt(companySupplier.getOpeningBalanceType().equals("debt")? Integer.parseInt(companySupplier.getOpeningBalance()):0);
            debt.setPrepayment(companySupplier.getOpeningBalanceType().equals("prepaid")? Integer.parseInt(companySupplier.getOpeningBalance()):0);

            for(CashOutForGoodsProvider cashOutForGoodsProvider :cashOutForGoodsProviders){
                if(cashOutForGoodsProvider.getCompanySupplier()!=null)
                    if(cashOutForGoodsProvider.getCompanySupplier().getId()==companySupplier.getId()){

                                debt.setPrepayment(debt.getPrepayment()+Integer.valueOf(cashOutForGoodsProvider.getWalletOut().getOutCash()));

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
            if( debt.getPrepayment() - debt.getDebt() < 0 )
            {
                debt.setDebt(Math.abs(debt.getPrepayment() - debt.getDebt()) );
                debt.setPrepayment(0);
            }
            else{
                debt.setPrepayment(debt.getPrepayment() - debt.getDebt());
                debt.setDebt(0);
            }

            debts.add(debt);
            debt = new Debt();

        }
        for(IndividualSupplier individualSupplier : individualSuppliers)
        {
            debt.setName(individualSupplier.getName());
            debt.setId(individualSupplier.getId());
            debt.setType("IndividualSupplier");
            debt.setDebt(individualSupplier.getOpeningBalanceType().equals("debt")? Integer.parseInt(individualSupplier.getOpeningBalance()):0);
            debt.setPrepayment(individualSupplier.getOpeningBalanceType().equals("prepaid")? Integer.parseInt(individualSupplier.getOpeningBalance()):0);

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
            if( debt.getPrepayment() - debt.getDebt() < 0 )
            {
                debt.setDebt(Math.abs(debt.getPrepayment() - debt.getDebt()) );
                debt.setPrepayment(0);
            }
            else{
                debt.setPrepayment(debt.getPrepayment() - debt.getDebt());
                debt.setDebt(0);
            }
            debts.add(debt);
            debt = new Debt();
        }
        for(PrivateEntrepreneurSupplier privateEntrepreneurSupplier : privateEntrepreneurSuppliers)
        {
            debt.setName(privateEntrepreneurSupplier.getName());
            debt.setId(privateEntrepreneurSupplier.getId());
            debt.setType("PrivateEntrepreneurSupplier");
            debt.setDebt(privateEntrepreneurSupplier.getOpeningBalanceType().equals("debt")? Integer.parseInt(privateEntrepreneurSupplier.getOpeningBalance()):0);
            debt.setPrepayment(privateEntrepreneurSupplier.getOpeningBalanceType().equals("prepaid")? Integer.parseInt(privateEntrepreneurSupplier.getOpeningBalance()):0);

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
            if( debt.getPrepayment() - debt.getDebt() < 0 )
            {
                debt.setDebt(Math.abs(debt.getPrepayment() - debt.getDebt()) );
                debt.setPrepayment(0);
            }
            else{
                debt.setPrepayment(debt.getPrepayment() - debt.getDebt());
                debt.setDebt(0);
            }
            debts.add(debt);
            debt = new Debt();
        }
        return debts;
    }
    @GetMapping( value ="/debt/details")
    public ModelAndView debtDetails(@RequestParam("supplierType")String supplierType,@RequestParam("supplierId")Long supplierId,ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(supplierType.equals("CompanySupplier")) {
            CompanySupplier companySupplier = companySupplierRepository.findOne(supplierId);
            modelAndView.addObject("supplier",companySupplier);
        }
        if(supplierType.equals("IndividualSupplier")) {
            IndividualSupplier individualSupplier = individualSupplierRepository.findOne(supplierId);
            modelAndView.addObject("supplier",individualSupplier);
        }
        if(supplierType.equals("PrivateEntrepreneurSupplier")) {
            PrivateEntrepreneurSupplier privateEntrepreneurSupplier = privateEntrepreneurSupplierRepository.findOne(supplierId);
            modelAndView.addObject("supplier",privateEntrepreneurSupplier);
        }

        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerSupplierDebtDetails);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value = "/debt/details/show")
    public @ResponseBody ArrayList supplierDebtDetails(@RequestBody DateRangByType dateRangByType ) {
        ArrayList<DebtDetailsShow> debtDetails= new ArrayList<>();
        DebtDetailsShow debtDetail = new DebtDetailsShow();

        List<CashOutForGoodsProvider> cashOutForGoodsProviders=null;
        List<CashOutForSerivceProvider> cashOutForSerivceProviders=null;
        List<CashOutForRent> cashOutForRents=null;

        if (dateRangByType.getStart() != null && dateRangByType.getEnd() == null) {
            if(dateRangByType.getType().equals("CompanySupplier")){
                cashOutForGoodsProviders = (List<CashOutForGoodsProvider>) cashOutForGoodsProviderRepository.findByRangeStartAndCompanySupplierId(dateRangByType.getStart(),dateRangByType.getId());
                cashOutForSerivceProviders = (List<CashOutForSerivceProvider>) cashOutForSerivceProviderRepository.findByRangeStartAndCompanySupplierId(dateRangByType.getStart(),dateRangByType.getId());
                cashOutForRents = (List<CashOutForRent>) cashOutForRentRepository.findByRangeStartAndCompanySupplierId(dateRangByType.getStart(),dateRangByType.getId());

            }
            if(dateRangByType.getType().equals("IndividualSupplier")){
                cashOutForGoodsProviders = (List<CashOutForGoodsProvider>) cashOutForGoodsProviderRepository.findByRangeStartAndIndividualSupplierId(dateRangByType.getStart(),dateRangByType.getId());
                cashOutForSerivceProviders = (List<CashOutForSerivceProvider>) cashOutForSerivceProviderRepository.findByRangeStartAndIndividualSupplierId(dateRangByType.getStart(),dateRangByType.getId());
                cashOutForRents = (List<CashOutForRent>) cashOutForRentRepository.findByRangeStartAndIndividualSupplierId(dateRangByType.getStart(),dateRangByType.getId());

            }
            if(dateRangByType.getType().equals("PrivateEntrepreneurSupplier")){
                cashOutForGoodsProviders = (List<CashOutForGoodsProvider>) cashOutForGoodsProviderRepository.findByRangeStartAndPrivateEntrepreneurSupplierId(dateRangByType.getStart(),dateRangByType.getId());
                cashOutForSerivceProviders = (List<CashOutForSerivceProvider>) cashOutForSerivceProviderRepository.findByRangeStartAndPrivateEntrepreneurSupplierId(dateRangByType.getStart(),dateRangByType.getId());
                cashOutForRents = (List<CashOutForRent>) cashOutForRentRepository.findByRangeStartAndPrivateEntrepreneurSupplierId(dateRangByType.getStart(),dateRangByType.getId());

            }
            for (CashOutForGoodsProvider cashOutForGoodsProvider:cashOutForGoodsProviders){

                debtDetail = new DebtDetailsShow(cashOutForGoodsProvider.getWalletOut().getOutDate(),
                        cashOutForGoodsProvider.getWalletOut().getOutType(),
                        Integer.parseInt(cashOutForGoodsProvider.getWalletOut().getOutCash()) ,
                        null,
                        cashOutForGoodsProvider.getId()
                );
                debtDetails.add(debtDetail);
            }
            for (CashOutForSerivceProvider cashOutForSerivceProvider:cashOutForSerivceProviders){

                debtDetail = new DebtDetailsShow(cashOutForSerivceProvider.getWalletOut().getOutDate(),
                        cashOutForSerivceProvider.getWalletOut().getOutType(),
                        Integer.parseInt(cashOutForSerivceProvider.getWalletOut().getOutCash()) ,
                        null,
                        cashOutForSerivceProvider.getId()
                );
                debtDetails.add(debtDetail);
            }
            for (CashOutForRent cashOutForRent:cashOutForRents){

                debtDetail = new DebtDetailsShow(cashOutForRent.getWalletOut().getOutDate(),
                        cashOutForRent.getWalletOut().getOutType(),
                        Integer.parseInt(cashOutForRent.getWalletOut().getOutCash()) ,
                        null,
                        cashOutForRent.getId()
                );
                debtDetails.add(debtDetail);
            }



            return debtDetails;
        } else if (dateRangByType.getStart() == null && dateRangByType.getEnd() != null) {
            if(dateRangByType.getType().equals("CompanySupplier")){
                 cashOutForGoodsProviders = (List<CashOutForGoodsProvider>) cashOutForGoodsProviderRepository.findByRangeEndAndCompanySupplierId(dateRangByType.getEnd(),dateRangByType.getId());
                 cashOutForSerivceProviders = (List<CashOutForSerivceProvider>) cashOutForSerivceProviderRepository.findByRangeEndAndCompanySupplierId(dateRangByType.getEnd(),dateRangByType.getId());
                 cashOutForRents = (List<CashOutForRent>) cashOutForRentRepository.findByRangeEndAndCompanySupplierId(dateRangByType.getEnd(),dateRangByType.getId());

            }
            if(dateRangByType.getType().equals("IndividualSupplier")){
                cashOutForGoodsProviders = (List<CashOutForGoodsProvider>) cashOutForGoodsProviderRepository.findByRangeEndAndIndividualSupplierId(dateRangByType.getEnd(),dateRangByType.getId());
                cashOutForSerivceProviders = (List<CashOutForSerivceProvider>) cashOutForSerivceProviderRepository.findByRangeEndAndIndividualSupplierId(dateRangByType.getEnd(),dateRangByType.getId());
                cashOutForRents = (List<CashOutForRent>) cashOutForRentRepository.findByRangeEndAndIndividualSupplierId(dateRangByType.getEnd(),dateRangByType.getId());

            }
            if(dateRangByType.getType().equals("PrivateEntrepreneurSupplier")){
                cashOutForGoodsProviders = (List<CashOutForGoodsProvider>) cashOutForGoodsProviderRepository.findByRangeEndAndPrivateEntrepreneurSupplierId(dateRangByType.getEnd(),dateRangByType.getId());
                cashOutForSerivceProviders = (List<CashOutForSerivceProvider>) cashOutForSerivceProviderRepository.findByRangeEndAndPrivateEntrepreneurSupplierId(dateRangByType.getEnd(),dateRangByType.getId());
                cashOutForRents = (List<CashOutForRent>) cashOutForRentRepository.findByRangeEndAndPrivateEntrepreneurSupplierId(dateRangByType.getEnd(),dateRangByType.getId());

            }
            for (CashOutForGoodsProvider cashOutForGoodsProvider:cashOutForGoodsProviders){

                debtDetail = new DebtDetailsShow(cashOutForGoodsProvider.getWalletOut().getOutDate(),
                        cashOutForGoodsProvider.getWalletOut().getOutType(),
                        Integer.parseInt(cashOutForGoodsProvider.getWalletOut().getOutCash()) ,
                        null,
                        cashOutForGoodsProvider.getId()
                );
                debtDetails.add(debtDetail);
            }
            for (CashOutForSerivceProvider cashOutForSerivceProvider:cashOutForSerivceProviders){

                debtDetail = new DebtDetailsShow(cashOutForSerivceProvider.getWalletOut().getOutDate(),
                        cashOutForSerivceProvider.getWalletOut().getOutType(),
                        Integer.parseInt(cashOutForSerivceProvider.getWalletOut().getOutCash()) ,
                        null,
                        cashOutForSerivceProvider.getId()
                );
                debtDetails.add(debtDetail);
            }
            for (CashOutForRent cashOutForRent:cashOutForRents){

                debtDetail = new DebtDetailsShow(cashOutForRent.getWalletOut().getOutDate(),
                        cashOutForRent.getWalletOut().getOutType(),
                        Integer.parseInt(cashOutForRent.getWalletOut().getOutCash()) ,
                        null,
                        cashOutForRent.getId()
                );
                debtDetails.add(debtDetail);
            }


            return debtDetails;
        }else if (dateRangByType.getStart() != null && dateRangByType.getEnd() != null) {

            if(dateRangByType.getType().equals("CompanySupplier")){
                 cashOutForGoodsProviders = (List<CashOutForGoodsProvider>) cashOutForGoodsProviderRepository.findByRangeAndCompanySupplierId(dateRangByType.getStart(),dateRangByType.getEnd(),dateRangByType.getId());
                 cashOutForSerivceProviders = (List<CashOutForSerivceProvider>) cashOutForSerivceProviderRepository.findByRangeAndCompanySupplierId(dateRangByType.getStart(),dateRangByType.getEnd(),dateRangByType.getId());
                 cashOutForRents = (List<CashOutForRent>) cashOutForRentRepository.findByRangeAndCompanySupplierId(dateRangByType.getStart(),dateRangByType.getEnd(),dateRangByType.getId());

            }
            if(dateRangByType.getType().equals("IndividualSupplier")){
                cashOutForGoodsProviders = (List<CashOutForGoodsProvider>) cashOutForGoodsProviderRepository.findByRangeAndIndividualSupplierId(dateRangByType.getStart(),dateRangByType.getEnd(),dateRangByType.getId());
                cashOutForSerivceProviders = (List<CashOutForSerivceProvider>) cashOutForSerivceProviderRepository.findByRangeAndIndividualSupplierId(dateRangByType.getStart(),dateRangByType.getEnd(),dateRangByType.getId());
                cashOutForRents = (List<CashOutForRent>) cashOutForRentRepository.findByRangeAndIndividualSupplierId(dateRangByType.getStart(),dateRangByType.getEnd(),dateRangByType.getId());

            }
            if(dateRangByType.getType().equals("PrivateEntrepreneurSupplier")){
                cashOutForGoodsProviders = (List<CashOutForGoodsProvider>) cashOutForGoodsProviderRepository.findByRangeAndPrivateEntrepreneurSupplierId(dateRangByType.getStart(),dateRangByType.getEnd(),dateRangByType.getId());
                cashOutForSerivceProviders = (List<CashOutForSerivceProvider>) cashOutForSerivceProviderRepository.findByRangeAndPrivateEntrepreneurSupplierId(dateRangByType.getStart(),dateRangByType.getEnd(),dateRangByType.getId());
                cashOutForRents = (List<CashOutForRent>) cashOutForRentRepository.findByRangeAndPrivateEntrepreneurSupplierId(dateRangByType.getStart(),dateRangByType.getEnd(),dateRangByType.getId());

            }
            for (CashOutForGoodsProvider cashOutForGoodsProvider:cashOutForGoodsProviders){

                debtDetail = new DebtDetailsShow(cashOutForGoodsProvider.getWalletOut().getOutDate(),
                        cashOutForGoodsProvider.getWalletOut().getOutType(),
                        Integer.parseInt(cashOutForGoodsProvider.getWalletOut().getOutCash()) ,
                        null,
                        cashOutForGoodsProvider.getId()
                );
                debtDetails.add(debtDetail);
            }
            for (CashOutForSerivceProvider cashOutForSerivceProvider:cashOutForSerivceProviders){

                debtDetail = new DebtDetailsShow(cashOutForSerivceProvider.getWalletOut().getOutDate(),
                        cashOutForSerivceProvider.getWalletOut().getOutType(),
                        Integer.parseInt(cashOutForSerivceProvider.getWalletOut().getOutCash()) ,
                        null,
                        cashOutForSerivceProvider.getId()
                );
                debtDetails.add(debtDetail);
            }
            for (CashOutForRent cashOutForRent:cashOutForRents){

                debtDetail = new DebtDetailsShow(cashOutForRent.getWalletOut().getOutDate(),
                        cashOutForRent.getWalletOut().getOutType(),
                        Integer.parseInt(cashOutForRent.getWalletOut().getOutCash()) ,
                        null,
                        cashOutForRent.getId()
                );
                debtDetails.add(debtDetail);
            }

            return debtDetails;
        }
        return null;
    }
    @GetMapping( value ="/purchase")
    public ModelAndView SupplierPurchase(ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerSupplierPurchase);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);

        return modelAndView;
    }
    @GetMapping( value ="/create/purchasechoosetype")
    public ModelAndView SupplierPurchaseType(ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerSupplierPurchaseType);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);

        return modelAndView;
    }
    @RequestMapping( value ="/create/purchase")
    public ModelAndView SupplierCreatePurchase(@RequestParam("type") String type, ModelAndView modelAndView,HttpSession httpSession) {

        suppliers = new ArrayList();
        List<CompanySupplier> companySuppliers ;
        companySuppliers = (List<CompanySupplier>) companySupplierRepository.findAll();
        List<IndividualSupplier> individualSuppliers;
        individualSuppliers = (List<IndividualSupplier>) individualSupplierRepository.findAll();
        List<PrivateEntrepreneurSupplier>  privateEntrepreneurSuppliers;
        privateEntrepreneurSuppliers = (List<PrivateEntrepreneurSupplier>) privateEntrepreneurSupplierRepository.findAll();

        for(CompanySupplier supplier:companySuppliers){
            suppliers.add(new Supplier(supplier.getId(),"CompanySupplier",supplier.getName()));
        }
        for(IndividualSupplier supplier:individualSuppliers){
            suppliers.add(new Supplier(supplier.getId(),"IndividualSupplier",supplier.getName()));
        }
        for(PrivateEntrepreneurSupplier supplier:privateEntrepreneurSuppliers){
            suppliers.add(new Supplier(supplier.getId(),"PrivateEntrepreneurSupplier",supplier.getName()));
        }

        modelAndView.setViewName("app/app");
        if(type.equals("goods")){
            PurchaseGoods  purchaseGoods = new PurchaseGoods();
            purchaseGoods.setOrganization((Organization) httpSession.getAttribute("organizationId"));
            modelAndView.addObject("purchaseGoods",purchaseGoods);
            modelAndView.addObject("fragment",partnerSupplierCreatePurchaseForGoods);}

        if(type.equals("service")){

            modelAndView.addObject("fragment",partnerSupplierCreatePurchaseForService);}

        if(type.equals("fixedasset")){
            modelAndView.addObject("fragment",partnerSupplierCreatePurchaseForFixedasset);}


        modelAndView.addObject("suppliers", suppliers);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);

        return modelAndView;
    }
    @PostMapping(value ="/create/purchasegoods")
    public ModelAndView SupplierCreatePurchaseGoods(@Valid PurchaseGoods purchaseGoods, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {

            modelAndView.addObject("purchaseGoods",purchaseGoods);
            modelAndView.addObject("suppliers", suppliers);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("fragment", this.partnerSupplierCreatePurchaseForGoods);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerSupplierPurchaseType);
        modelAndView.addObject("fragmentNavBar", this.partnerOtherPartnerFragmentNavBar);

        if(purchaseGoods.getSupplierType().equals("CompanySupplier")){
            purchaseGoods.setCompanySupplier(companySupplierRepository.findOne(purchaseGoods.getSupplierId()));
        }
        if(purchaseGoods.getSupplierType().equals("IndividualSupplier")){
            purchaseGoods.setIndividualSupplier(individualSupplierRepository.findOne(purchaseGoods.getSupplierId()));
        }
        if(purchaseGoods.getSupplierType().equals("PrivateEntrepreneurSupplier")){
            purchaseGoods.setPrivateEntrepreneurSupplier(privateEntrepreneurSupplierRepository.findOne(purchaseGoods.getSupplierId()));
        }
        purchaseGoods.setPurchaseType("PurchaseGoods");
        purchaseGoodsRepository.save(purchaseGoods);
        return  modelAndView;
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
    @GetMapping(value = "/edit/individualsupplier")
    public ModelAndView individualSupplierEdit(@RequestParam("customerId")Long customerId, ModelAndView modelAndView, HttpSession httpSession) {
        IndividualSupplier individualSupplier ;
        individualSupplier = individualSupplierRepository.findOne(customerId);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("individualSupplier",individualSupplier);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.individualSupplierCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
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
    @GetMapping(value = "/edit/companysupplier")
    public ModelAndView companySupplierEdit(@RequestParam("customerId")Long customerId , ModelAndView modelAndView, HttpSession httpSession) {
        CompanySupplier companySupplier ;

        companySupplier = companySupplierRepository.findOne(customerId);

        modelAndView.setViewName("app/app");
        modelAndView.addObject("companySupplier",companySupplier);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.companySupplierCreate);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
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
