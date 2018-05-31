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
import com.example.imhashvapahversion1.version1.Entity.partners.purchase.PurchaseService;
import com.example.imhashvapahversion1.version1.Entity.partners.service.PeriodicService;
import com.example.imhashvapahversion1.version1.Entity.partners.service.rent.PeriodicServiceRentArea;
import com.example.imhashvapahversion1.version1.Entity.partners.service.rent.PeriodicServiceRentCar;
import com.example.imhashvapahversion1.version1.Entity.partners.service.rent.PeriodicServiceRentOther;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.*;
import com.example.imhashvapahversion1.version1.Entity.showClasses.DebtDetailsShow;
import com.example.imhashvapahversion1.version1.Entity.showClasses.FinancialMeans;
import com.example.imhashvapahversion1.version1.Entity.showClasses.SupplierShow;
import com.example.imhashvapahversion1.version1.controller.BaseController;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForGoodsProviderRepository;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForRentRepository;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForSerivceProviderRepository;
import com.example.imhashvapahversion1.version1.repository.purchase.PurchaseGoodsRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.*;
import com.example.imhashvapahversion1.version1.repository.suppliers.service.PeriodicServiceRentAreaRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.service.PeriodicServiceRentCarRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.service.PeriodicServiceRentOtherRepository;
import com.example.imhashvapahversion1.version1.repository.suppliers.service.PeriodicServiceRepository;
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
    PeriodicServiceRentCarRepository periodicServiceRentCarRepository;
    @Autowired
    PeriodicServiceRepository periodicServiceRepository;
    @Autowired
    PeriodicServiceRentOtherRepository periodicServiceRentOtherRepository;
    @Autowired
    PeriodicServiceRentAreaRepository periodicServiceRentAreaRepository;
    @Autowired
    CashOutForGoodsProviderRepository cashOutForGoodsProviderRepository;

    private List<Supplier> suppliers=null;
    private List<Service> services=null;

    @GetMapping(value = "")
    public ModelAndView supplierPartner(ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.supplierFragments);
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
        modelAndView.addObject("appFragment", this.supplierFragments);
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
        modelAndView.addObject("appFragment", this.partnerFragments);
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
        modelAndView.addObject("appFragment", this.partnerFragments);
        modelAndView.addObject("fragment", this.partnerSupplierPurchase);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);

        return modelAndView;
    }
    @GetMapping( value ="/create/purchasechoosetype")
    public ModelAndView SupplierPurchaseType(ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.supplierFragments);
        modelAndView.addObject("fragment", this.partnerSupplierPurchaseType);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);

        return modelAndView;
    }
    @RequestMapping( value ="/create/purchase")
    public ModelAndView SupplierCreatePurchase(@RequestParam("type") String type, ModelAndView modelAndView,HttpSession httpSession) {

        suppliers = new ArrayList();
        services = new ArrayList<>();
        List<CompanySupplier> companySuppliers ;
        companySuppliers = (List<CompanySupplier>) companySupplierRepository.findAll();
        List<IndividualSupplier> individualSuppliers;
        individualSuppliers = (List<IndividualSupplier>) individualSupplierRepository.findAll();
        List<PrivateEntrepreneurSupplier>  privateEntrepreneurSuppliers;
        privateEntrepreneurSuppliers = (List<PrivateEntrepreneurSupplier>) privateEntrepreneurSupplierRepository.findAll();

        List<PeriodicService> periodicServices ;
        periodicServices = (List<PeriodicService>) periodicServiceRepository.findAll();

        List<PeriodicServiceRentArea> periodicServiceRentAreas ;
        periodicServiceRentAreas = (List<PeriodicServiceRentArea>) periodicServiceRentAreaRepository.findAll();

        List<PeriodicServiceRentCar> periodicServiceRentCars;
        periodicServiceRentCars = (List<PeriodicServiceRentCar>) periodicServiceRentCarRepository.findAll();

        List<PeriodicServiceRentOther>  periodicServiceRentOthers;
        periodicServiceRentOthers = (List<PeriodicServiceRentOther>) periodicServiceRentOtherRepository.findAll();

        for(CompanySupplier supplier:companySuppliers){
            suppliers.add(new Supplier(supplier.getId(),"CompanySupplier",supplier.getName()));
        }
        for(IndividualSupplier supplier:individualSuppliers){
            suppliers.add(new Supplier(supplier.getId(),"IndividualSupplier",supplier.getName()));
        }
        for(PrivateEntrepreneurSupplier supplier:privateEntrepreneurSuppliers){
            suppliers.add(new Supplier(supplier.getId(),"PrivateEntrepreneurSupplier",supplier.getName()));
        }


        for(PeriodicService periodicService:periodicServices){

            services.add(new Service(
                    periodicService.getId(),
                    "PeriodicService",
                    periodicService.getName(),
                    periodicService.getContractDate(),
                    periodicService.getContractNumber(),
                    periodicService.getSupplier().getId(),
                    periodicService.getSupplier().getType()

            ));
        }
        for(PeriodicServiceRentArea  periodicServiceRentArea:periodicServiceRentAreas){
            services.add(new Service(
                    periodicServiceRentArea.getId(),
                    "PeriodicServiceRentArea",
                    periodicServiceRentArea.getName(),
                    periodicServiceRentArea.getContractDate(),
                    periodicServiceRentArea.getContractNumber(),
                    periodicServiceRentArea.getSupplier().getId(),
                    periodicServiceRentArea.getSupplier().getType()

            ));
        }
        for(PeriodicServiceRentCar periodicServiceRentCar:periodicServiceRentCars){
            services.add(new Service(
                    periodicServiceRentCar.getId(),
                    "PeriodicServiceRentCar",
                    periodicServiceRentCar.getName(),
                    periodicServiceRentCar.getContractDate(),
                    periodicServiceRentCar.getContractNumber(),
                    periodicServiceRentCar.getSupplier().getId(),
                    periodicServiceRentCar.getSupplier().getType()

            ));
        }
        for(PeriodicServiceRentOther periodicServiceRentOther:periodicServiceRentOthers){
            services.add(new Service(
                    periodicServiceRentOther.getId(),
                    "PeriodicServiceRentOther",
                    periodicServiceRentOther.getName(),
                    periodicServiceRentOther.getContractDate(),
                    periodicServiceRentOther.getContractNumber(),
                    periodicServiceRentOther.getSupplier().getId(),
                    periodicServiceRentOther.getSupplier().getType()
            ));
        }
        modelAndView.setViewName("app/app");
        if(type.equals("goods")){
            PurchaseGoods  purchaseGoods = new PurchaseGoods();
            purchaseGoods.setOrganization((Organization) httpSession.getAttribute("organizationId"));
            modelAndView.addObject("purchaseGoods",purchaseGoods);
            modelAndView.addObject("fragment",partnerSupplierCreatePurchaseForGoods);}

        if(type.equals("service")){
            PurchaseService purchaseService = new PurchaseService();
            purchaseService.setOrganization((Organization) httpSession.getAttribute("organizationId"));
            modelAndView.addObject("purchaseService",purchaseService);
            modelAndView.addObject("services",services);
            modelAndView.addObject("fragment",partnerSupplierCreatePurchaseForService);
        }

        if(type.equals("fixedasset")){

            modelAndView.addObject("fragment",partnerSupplierCreatePurchaseForFixedasset);}

        modelAndView.addObject("appFragment", this.supplierFragments);
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
            modelAndView.addObject("appFragment", this.supplierFragments);
            modelAndView.addObject("fragment", this.partnerSupplierCreatePurchaseForGoods);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.supplierFragments);
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
        modelAndView.addObject("appFragment", this.supplierFragments);
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
        modelAndView.addObject("appFragment", this.supplierFragments);
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
            modelAndView.addObject("appFragment", this.supplierFragments);
            modelAndView.addObject("fragment", this.individualSupplierCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.supplierFragments);
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
        modelAndView.addObject("appFragment", this.supplierFragments);
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
        modelAndView.addObject("appFragment", this.supplierFragments);
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
            modelAndView.addObject("appFragment", this.supplierFragments);
            modelAndView.addObject("fragment", this.companySupplierCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
            return modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.supplierFragments);
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
        modelAndView.addObject("appFragment", this.supplierFragments);
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
        modelAndView.addObject("appFragment", this.supplierFragments);
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
            modelAndView.addObject("appFragment", this.supplierFragments);
            modelAndView.addObject("fragment", this.privateEntrepreneurSupplierCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);

            return  modelAndView;
        }


        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.supplierFragments);
        modelAndView.addObject("fragment", this.partnerSupplier);
        modelAndView.addObject("fragmentNavBar", this.partnerFragmentNavBar);
        privateEntrepreneurSupplierRepository.save(privateEntrepreneurSupplier);
        return  modelAndView;
    }

    @GetMapping(value = "/periodicservice")
    public ModelAndView partnerSupplierPeriodicService( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.partnerFragments);
        modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceFragment);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
        return modelAndView;
    }
    @GetMapping(value = "/create/periodicservicesetype")
    public ModelAndView partnerSupplierPeriodicServiceSelect( ModelAndView modelAndView) {

        modelAndView.setViewName("app/app");

        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.partnerFragments);
        modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceSelect);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
        return modelAndView;
    }
    @GetMapping(value = "/create/periodicservice")
    public ModelAndView partnerSupplierPeriodicServiceCreate( ModelAndView modelAndView,HttpSession httpSession) {
        modelAndView.setViewName("app/app");
        suppliers = new ArrayList();
        List<CompanySupplier> companySuppliers ;
        companySuppliers = (List<CompanySupplier>) companySupplierRepository.findBySupplyForRent();
        List<IndividualSupplier> individualSuppliers;
        individualSuppliers = (List<IndividualSupplier>) individualSupplierRepository.findBySupplyForRent();
        List<PrivateEntrepreneurSupplier>  privateEntrepreneurSuppliers;
        privateEntrepreneurSuppliers = (List<PrivateEntrepreneurSupplier>) privateEntrepreneurSupplierRepository.findBySupplyForRent();

        for(CompanySupplier supplier:companySuppliers){
            suppliers.add(new Supplier(supplier.getId(),"CompanySupplier",supplier.getName()));
        }
        for(IndividualSupplier supplier:individualSuppliers){
            suppliers.add(new Supplier(supplier.getId(),"IndividualSupplier",supplier.getName()));
        }
        for(PrivateEntrepreneurSupplier supplier:privateEntrepreneurSuppliers){
            suppliers.add(new Supplier(supplier.getId(),"PrivateEntrepreneurSupplier",supplier.getName()));
        }

        PeriodicService periodicService=new PeriodicService();
        periodicService.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.addObject("appFragment", this.partnerFragments);
        modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceCreate);
        modelAndView.addObject("periodicService",periodicService);
        modelAndView.addObject("suppliers",suppliers);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
        return modelAndView;
    }
    @GetMapping(value = "/create/periodicservice/rent")
    public ModelAndView partnerSupplierPeriodicServiceCreate(@RequestParam("type")String type, ModelAndView modelAndView,HttpSession httpSession) {
        modelAndView.setViewName("app/app");
        suppliers = new ArrayList();
        List<CompanySupplier> companySuppliers ;
        companySuppliers = (List<CompanySupplier>) companySupplierRepository.findBySupplyForRent();
        List<IndividualSupplier> individualSuppliers;
        individualSuppliers = (List<IndividualSupplier>) individualSupplierRepository.findBySupplyForRent();
        List<PrivateEntrepreneurSupplier>  privateEntrepreneurSuppliers;
        privateEntrepreneurSuppliers = (List<PrivateEntrepreneurSupplier>) privateEntrepreneurSupplierRepository.findBySupplyForRent();

        for(CompanySupplier supplier:companySuppliers){
            suppliers.add(new Supplier(supplier.getId(),"CompanySupplier",supplier.getName()));
        }
        for(IndividualSupplier supplier:individualSuppliers){
            suppliers.add(new Supplier(supplier.getId(),"IndividualSupplier",supplier.getName()));
        }
        for(PrivateEntrepreneurSupplier supplier:privateEntrepreneurSuppliers){
            suppliers.add(new Supplier(supplier.getId(),"PrivateEntrepreneurSupplier",supplier.getName()));
        }

        if(type.equals("area")){
            PeriodicServiceRentArea periodicServiceRentArea =new PeriodicServiceRentArea();
            periodicServiceRentArea.setOrganization((Organization) httpSession.getAttribute("organizationId"));
            modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceRentAreaCreate);
            modelAndView.addObject("periodicServiceRentArea",periodicServiceRentArea);
        }if(type.equals("car")){
            PeriodicServiceRentCar periodicServiceRentCar=new PeriodicServiceRentCar();
            periodicServiceRentCar.setOrganization((Organization) httpSession.getAttribute("organizationId"));
            modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceRentCarCreate);
            modelAndView.addObject("periodicServiceRentCar",periodicServiceRentCar);
        }
        if(type.equals("other")){
            PeriodicServiceRentOther periodicServiceRentOther=new PeriodicServiceRentOther();
            periodicServiceRentOther.setOrganization((Organization) httpSession.getAttribute("organizationId"));
            modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceRentOtherCreate);
            modelAndView.addObject("periodicServiceRentOther",periodicServiceRentOther);
        }
        modelAndView.addObject("appFragment", this.partnerFragments);
        modelAndView.addObject("suppliers",suppliers);
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value ="/create/periodicservice/periodicservicerentarea")
    public ModelAndView partnerSupplierPeriodicServiceRentAreaCreate(@Valid PeriodicServiceRentArea periodicServiceRentArea, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("suppliers",suppliers);
            modelAndView.addObject("periodicServiceRentArea",periodicServiceRentArea);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("appFragment", this.partnerFragments);
            modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceRentAreaCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);

            return  modelAndView;
        }
        if(periodicServiceRentArea.getSupplierType().equals("CompanySupplier")){
            periodicServiceRentArea.setCompanySupplier(companySupplierRepository.findOne(periodicServiceRentArea.getSupplierId()));
        }
        if(periodicServiceRentArea.getSupplierType().equals("IndividualSupplier")){
            periodicServiceRentArea.setIndividualSupplier(individualSupplierRepository.findOne(periodicServiceRentArea.getSupplierId()));
        }
        if(periodicServiceRentArea.getSupplierType().equals("PrivateEntrepreneurSupplier")){
            periodicServiceRentArea.setPrivateEntrepreneurSupplier(privateEntrepreneurSupplierRepository.findOne(periodicServiceRentArea.getSupplierId()));
        }

        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.partnerFragments);
        modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceSelect);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
        periodicServiceRentAreaRepository.save(periodicServiceRentArea);
        return  modelAndView;
    }
    @PostMapping(value ="/create/periodicservice/periodicservicerentcar")
    public ModelAndView partnerSupplierPeriodicServiceRentCarCreate(@Valid PeriodicServiceRentCar periodicServiceRentCar, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("suppliers",suppliers);
            modelAndView.addObject("periodicServiceRentCar",periodicServiceRentCar);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("appFragment", this.partnerFragments);
            modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceRentCarCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);

            return  modelAndView;
        }
        if(periodicServiceRentCar.getSupplierType().equals("CompanySupplier")){
            periodicServiceRentCar.setCompanySupplier(companySupplierRepository.findOne(periodicServiceRentCar.getSupplierId()));
        }
        if(periodicServiceRentCar.getSupplierType().equals("IndividualSupplier")){
            periodicServiceRentCar.setIndividualSupplier(individualSupplierRepository.findOne(periodicServiceRentCar.getSupplierId()));
        }
        if(periodicServiceRentCar.getSupplierType().equals("PrivateEntrepreneurSupplier")){
            periodicServiceRentCar.setPrivateEntrepreneurSupplier(privateEntrepreneurSupplierRepository.findOne(periodicServiceRentCar.getSupplierId()));
        }

        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.partnerFragments);
        modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceSelect);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
        periodicServiceRentCarRepository.save(periodicServiceRentCar);
        return  modelAndView;
    }
    @PostMapping(value ="/create/periodicservice/periodicservicerentother")
    public ModelAndView partnerSupplierPeriodicServiceRentOtherCreate(@Valid PeriodicServiceRentOther periodicServiceRentOther, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("suppliers",suppliers);
            modelAndView.addObject("periodicServiceRentOther",periodicServiceRentOther);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceRentOtherCreate);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);

            return  modelAndView;
        }

        if(periodicServiceRentOther.getSupplierType().equals("CompanySupplier")){
            periodicServiceRentOther.setCompanySupplier(companySupplierRepository.findOne(periodicServiceRentOther.getSupplierId()));
        }
        if(periodicServiceRentOther.getSupplierType().equals("IndividualSupplier")){
            periodicServiceRentOther.setIndividualSupplier(individualSupplierRepository.findOne(periodicServiceRentOther.getSupplierId()));
        }
        if(periodicServiceRentOther.getSupplierType().equals("PrivateEntrepreneurSupplier")){
            periodicServiceRentOther.setPrivateEntrepreneurSupplier(privateEntrepreneurSupplierRepository.findOne(periodicServiceRentOther.getSupplierId()));
        }

        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceSelect);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
        periodicServiceRentOtherRepository.save(periodicServiceRentOther);
        return  modelAndView;
    }

    @PostMapping(value ="/create/periodicservice")
    public ModelAndView partnerSupplierPeriodicServiceCreate(@Valid PeriodicService periodicService, BindingResult bindingResult , ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("suppliers",suppliers);
            modelAndView.addObject("periodicService",periodicService);
            modelAndView.addObject("appFragment", this.partnerFragments);
            modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceCreate);
            modelAndView.addObject("suppliers",suppliers);
            modelAndView.addObject("navBar", this.partnerNavBar);
            modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);

            return  modelAndView;
        }
        if(periodicService.getSupplierType().equals("CompanySupplier")){
            periodicService.setCompanySupplier(companySupplierRepository.findOne(periodicService.getSupplierId()));
        }
        if(periodicService.getSupplierType().equals("IndividualSupplier")){
            periodicService.setIndividualSupplier(individualSupplierRepository.findOne(periodicService.getSupplierId()));
        }
        if(periodicService.getSupplierType().equals("PrivateEntrepreneurSupplier")){
            periodicService.setPrivateEntrepreneurSupplier(privateEntrepreneurSupplierRepository.findOne(periodicService.getSupplierId()));
        }
        modelAndView.addObject("navBar", this.partnerNavBar);
        modelAndView.addObject("appFragment", this.partnerFragments);
        modelAndView.addObject("fragment", this.partnerSupplierPeriodicServiceSelect);
        modelAndView.addObject("fragmentNavBar", this.partnerSupplierFragmentNavBar);
        periodicServiceRepository.save(periodicService);
        return  modelAndView;
    }
}
