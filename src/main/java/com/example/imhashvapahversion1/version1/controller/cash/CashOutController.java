package com.example.imhashvapahversion1.version1.controller.cash;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.BankAccount;
import com.example.imhashvapahversion1.version1.Entity.cash.Tax;

import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;

import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletOut;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.*;
import com.example.imhashvapahversion1.version1.Entity.enums.DateRange;

import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.IndividualOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.OtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.PrivateEntrepreneurOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.Supplier;

import com.example.imhashvapahversion1.version1.Entity.showClasses.CashOutShow;
import com.example.imhashvapahversion1.version1.controller.BaseController;
import com.example.imhashvapahversion1.version1.repository.BankAccountRepository;
import com.example.imhashvapahversion1.version1.repository.cashOut.*;
import com.example.imhashvapahversion1.version1.repository.otherpartners.CompanyOtherPartnerRepository;
import com.example.imhashvapahversion1.version1.repository.otherpartners.IndividualOtherPartnerRepository;
import com.example.imhashvapahversion1.version1.repository.otherpartners.PrivateEntrepreneurOtherPartnerRepository;
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
import java.util.Collection;
import java.util.List;


@Controller
@RequestMapping(value = "/account/cash")
public class CashOutController extends BaseController {
    @Autowired
    CashOutForTaxRepository cashOutForTaxRepository;
    @Autowired
    CashOutForGoodsProviderRepository cashOutForGoodsProviderRepository;
    @Autowired
    CashOutForSerivceProviderRepository cashOutForSerivceProviderRepository;
    @Autowired
    CashOutForRentRepository cashOutForRentRepository;
    @Autowired
    CashOutForBankAccountRepository cashOutForBankAccountRepository;
    @Autowired
    CashOutForCreditPaymentRepository cashOutForCreditPaymentRepository;
    @Autowired
    CashOutForRedemptionPercentRepository  cashOutForRedemptionPercentRepository;
    @Autowired
    CashOutForLoanPaymentRepository cashOutForLoanPaymentRepository;
    @Autowired
    CashOutForBankSpendingRepository cashOutForBankSpendingRepository;
    @Autowired
    CashOutForSalaryRepository cashOutForSalaryRepository;
    @Autowired
    CashOutForOtherExpensesRepository cashOutForOtherExpensesRepository;
    @Autowired
    CompanySupplierRepository companySupplierRepository;
    @Autowired
    IndividualSupplierRepository individualSupplierRepository;
    @Autowired
    PrivateEntrepreneurSupplierRepository privateEntrepreneurSupplierRepository;
    @Autowired
    CompanyOtherPartnerRepository companyOtherPartnerRepository;
    @Autowired
    IndividualOtherPartnerRepository individualOtherPartnerRepository;
    @Autowired
    PrivateEntrepreneurOtherPartnerRepository privateEntrepreneurOtherPartnerRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;
    private List<Supplier> suppliers=null;

    private List colleaguesList= new ArrayList() ;

    @GetMapping(value = "/cashout/cashdesk" )
    public ModelAndView cashOutcashDesk(ModelAndView modelAndView ) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOut);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        return modelAndView;
    }
    @PostMapping(value = "cashout/cashdesk/show")
    public @ResponseBody
    ArrayList cashOutCashdeskShow(@RequestBody DateRange dateRange ) {
        List<GetWaletOut> temp = new ArrayList();
        ArrayList<CashOutShow> showResult = new ArrayList();
        if (dateRange.isShowAll()) {
            temp.addAll((ArrayList)cashOutForBankAccountRepository.findAll());
            temp.addAll((ArrayList)cashOutForBankSpendingRepository.findAll());
            temp.addAll((ArrayList)cashOutForCreditPaymentRepository.findAll());
            temp.addAll((ArrayList)cashOutForGoodsProviderRepository.findAll());
            temp.addAll((ArrayList)cashOutForLoanPaymentRepository.findAll());
            temp.addAll((ArrayList)cashOutForOtherExpensesRepository.findAll());
            temp.addAll((ArrayList)cashOutForRedemptionPercentRepository.findAll());
            temp.addAll((ArrayList)cashOutForRentRepository.findAll());
            temp.addAll((ArrayList)cashOutForSalaryRepository.findAll());
            temp.addAll((ArrayList)cashOutForSerivceProviderRepository.findAll());
            temp.addAll((ArrayList)cashOutForTaxRepository.findAll());
            for(GetWaletOut each:temp) {
                showResult.add(new CashOutShow(each.getCashOutId(),each.getWalletOutImpl().getOutType(),each.getWalletOutImpl().getOutDate(),each.getWalletOutImpl().getOutCash()));
            }

            return showResult;
        }else

        if (dateRange.getStart() != null && dateRange.getEnd() == null) {
            temp.addAll(cashOutForBankAccountRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashOutForBankSpendingRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashOutForCreditPaymentRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashOutForGoodsProviderRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashOutForLoanPaymentRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashOutForOtherExpensesRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashOutForRedemptionPercentRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashOutForRentRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashOutForSerivceProviderRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashOutForTaxRepository.findByRangeStart(dateRange.getStart()));
            for(GetWaletOut each:temp) {
                showResult.add(new CashOutShow(each.getCashOutId(),each.getWalletOutImpl().getOutType(),each.getWalletOutImpl().getOutDate(),each.getWalletOutImpl().getOutCash()));
            }

            return showResult;
        }else
        if (dateRange.getStart() == null && dateRange.getEnd() != null) {
            temp.addAll(cashOutForBankAccountRepository.findByRangeEnd(dateRange.getEnd()));
            temp.addAll(cashOutForBankSpendingRepository.findByRangeEnd(dateRange.getEnd()));
            temp.addAll(cashOutForCreditPaymentRepository.findByRangeEnd(dateRange.getEnd()));
            temp.addAll(cashOutForGoodsProviderRepository.findByRangeEnd(dateRange.getEnd()));
            temp.addAll(cashOutForLoanPaymentRepository.findByRangeEnd(dateRange.getEnd()));
            temp.addAll(cashOutForOtherExpensesRepository.findByRangeEnd(dateRange.getEnd()));
            temp.addAll(cashOutForRedemptionPercentRepository.findByRangeEnd(dateRange.getEnd()));
            temp.addAll(cashOutForRentRepository.findByRangeEnd(dateRange.getEnd()));
            temp.addAll(cashOutForSerivceProviderRepository.findByRangeEnd(dateRange.getEnd()));
            temp.addAll(cashOutForTaxRepository.findByRangeEnd(dateRange.getEnd()));
            for(GetWaletOut each:temp) {
                showResult.add(new CashOutShow(each.getCashOutId(),each.getWalletOutImpl().getOutType(),each.getWalletOutImpl().getOutDate(),each.getWalletOutImpl().getOutCash()));
            }

            return showResult;
        }else
        if (dateRange.getStart() != null && dateRange.getEnd() != null) {

            temp.addAll(cashOutForBankAccountRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
            temp.addAll(cashOutForBankSpendingRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
            temp.addAll(cashOutForCreditPaymentRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
            temp.addAll(cashOutForGoodsProviderRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
            temp.addAll(cashOutForLoanPaymentRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
            temp.addAll(cashOutForOtherExpensesRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
            temp.addAll(cashOutForRedemptionPercentRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
            temp.addAll(cashOutForRentRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
            temp.addAll(cashOutForSerivceProviderRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;
            temp.addAll(cashOutForTaxRepository.findByRange(dateRange.getStart(),dateRange.getEnd())) ;

            for(GetWaletOut each:temp) {
                showResult.add(new CashOutShow(each.getCashOutId(),each.getWalletOutImpl().getOutType(),each.getWalletOutImpl().getOutDate(),each.getWalletOutImpl().getOutCash()));
            }

            return showResult;
        }
        return showResult;
    }

    @GetMapping(value = "/cashоut/cashdesk/create")
    public ModelAndView cashOutcashdeskCreate(ModelAndView modelAndView ) {
        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        return modelAndView;

    }





    @GetMapping(value = "/cashout/cashdesk/delete")
    public ModelAndView cashinDelete(@RequestParam("cashouttype") String cashOutType,@RequestParam("cashoutid") Long cashOutId, ModelAndView modelAndView, HttpSession httpSession) {
        modelAndView.setViewName("app/app");

        if(cashOutType.equals("CashOutForBankAccount"))
            cashOutForBankAccountRepository.delete(cashOutId);
        if(cashOutType.equals("CashOutForBankSpending"))
            cashOutForBankSpendingRepository.delete(cashOutId);
        if(cashOutType.equals("CashOutForCreditPayment"))
           cashOutForCreditPaymentRepository.delete(cashOutId);

        if(cashOutType.equals("CashOutForGoodsProvider"))
            cashOutForGoodsProviderRepository.delete(cashOutId);

        if(cashOutType.equals("CashOutForLoanPayment"))
           cashOutForLoanPaymentRepository.delete(cashOutId);

        if(cashOutType.equals("CashOutForOtherExpenses"))
            cashOutForOtherExpensesRepository.delete(cashOutId);

        if(cashOutType.equals("CashOutForRedemptionPercent"))
           cashOutForRedemptionPercentRepository.delete(cashOutId);
        if(cashOutType.equals("CashOutForRent"))
            cashOutForRentRepository.delete(cashOutId);
        if(cashOutType.equals("CashOutForSalary"))
            cashOutForSalaryRepository.delete(cashOutId);
        if(cashOutType.equals("CashOutForSerivceProvider"))
            cashOutForSerivceProviderRepository.delete(cashOutId);

        if(cashOutType.equals("CashOutForTax"))
            cashOutForTaxRepository.delete(cashOutId);


        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOut);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        return  modelAndView;
    }


    @GetMapping(value = "/cashout/cashdesk/edit")
    public ModelAndView cashinEdit(@RequestParam("cashouttype") String cashOutType,@RequestParam("cashoutid") Long cashOutId, ModelAndView modelAndView, HttpSession httpSession) {
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


        if(cashOutType.equals("CashOutForBankAccount")){

            CashOutForBankAccount cashOutForBankAccount = cashOutForBankAccountRepository.findOne(cashOutId);
            modelAndView.addObject("cashOutForBankAccount",cashOutForBankAccount);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForBankAccountCreate);
        }
        if(cashOutType.equals("CashOutForBankSpending")){

            CashOutForBankSpending cashOutForBankSpending = cashOutForBankSpendingRepository.findOne(cashOutId);
            modelAndView.addObject("cashOutForBankSpending",cashOutForBankSpending);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForBankSpendingCreate);
        }
        if(cashOutType.equals("CashOutForCreditPayment")){

            colleaguesList = new ArrayList();
            List<CompanyOtherPartner> companyOtherPartners ;
            companyOtherPartners = (List<CompanyOtherPartner>) companyOtherPartnerRepository.findAll();
            List<IndividualOtherPartner> individualOtherPartners;
            individualOtherPartners = (List<IndividualOtherPartner>) individualOtherPartnerRepository.findAll();
            List<PrivateEntrepreneurOtherPartner>  privateEntrepreneurOtherPartners;
            privateEntrepreneurOtherPartners = (List<PrivateEntrepreneurOtherPartner>) privateEntrepreneurOtherPartnerRepository.findAll();
            for(CompanyOtherPartner companyOtherPartner:companyOtherPartners){
                colleaguesList.add(new OtherPartner(companyOtherPartner.getId(),"CompanyOtherPartner",companyOtherPartner.getName()));
            }
            for(IndividualOtherPartner individualOtherPartner:individualOtherPartners){
                colleaguesList.add(new OtherPartner(individualOtherPartner.getId(),"IndividualOtherPartner",individualOtherPartner.getName()));
            }
            for(PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner:privateEntrepreneurOtherPartners){
                colleaguesList.add(new OtherPartner(privateEntrepreneurOtherPartner.getId(),"PrivateEntrepreneurOtherPartner",privateEntrepreneurOtherPartner.getName()));
            }

            CashOutForCreditPayment cashOutForCreditPayment = cashOutForCreditPaymentRepository.findOne(cashOutId);
            modelAndView.addObject("suppliers", colleaguesList);
            modelAndView.addObject("cashOutForCreditPayment",cashOutForCreditPayment);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForCreditPaymentCreate);
        }

        if(cashOutType.equals("CashOutForGoodsProvider")){

            CashOutForGoodsProvider cashOutForGoodsProvider = cashOutForGoodsProviderRepository.findOne(cashOutId);
            modelAndView.addObject("cashOutForGoodsProvider",cashOutForGoodsProvider);
            modelAndView.addObject("suppliers",suppliers);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForGoodsProviderCreate);
        }
        if(cashOutType.equals("CashOutForLoanPayment")){

            colleaguesList = new ArrayList();
            List<CompanyOtherPartner> companyOtherPartners ;
            companyOtherPartners = (List<CompanyOtherPartner>) companyOtherPartnerRepository.findAll();
            List<IndividualOtherPartner> individualOtherPartners;
            individualOtherPartners = (List<IndividualOtherPartner>) individualOtherPartnerRepository.findAll();
            List<PrivateEntrepreneurOtherPartner>  privateEntrepreneurOtherPartners;
            privateEntrepreneurOtherPartners = (List<PrivateEntrepreneurOtherPartner>) privateEntrepreneurOtherPartnerRepository.findAll();
            for(CompanyOtherPartner companyOtherPartner:companyOtherPartners){
                colleaguesList.add(new OtherPartner(companyOtherPartner.getId(),"CompanyOtherPartner",companyOtherPartner.getName()));
            }
            for(IndividualOtherPartner individualOtherPartner:individualOtherPartners){
                colleaguesList.add(new OtherPartner(individualOtherPartner.getId(),"IndividualOtherPartner",individualOtherPartner.getName()));
            }
            for(PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner:privateEntrepreneurOtherPartners){
                colleaguesList.add(new OtherPartner(privateEntrepreneurOtherPartner.getId(),"PrivateEntrepreneurOtherPartner",privateEntrepreneurOtherPartner.getName()));
            }
            CashOutForLoanPayment cashOutForLoanPayment = cashOutForLoanPaymentRepository.findOne(cashOutId);
            modelAndView.addObject("suppliers", colleaguesList);
            modelAndView.addObject("cashOutForLoanPayment",cashOutForLoanPayment);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForLoanPaymentCreate);
        }
        if(cashOutType.equals("CashOutForOtherExpenses")){

            CashOutForOtherExpenses cashOutForOtherExpenses = cashOutForOtherExpensesRepository.findOne(cashOutId);
            modelAndView.addObject("cashOutForOtherExpenses",cashOutForOtherExpenses);
            modelAndView.addObject("suppliers",suppliers);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForOtherExpensesCreate);
        }
        if(cashOutType.equals("CashOutForRedemptionPercent")){
            colleaguesList = new ArrayList();
            List<CompanyOtherPartner> companyOtherPartners ;
            companyOtherPartners = (List<CompanyOtherPartner>) companyOtherPartnerRepository.findAll();
            List<IndividualOtherPartner> individualOtherPartners;
            individualOtherPartners = (List<IndividualOtherPartner>) individualOtherPartnerRepository.findAll();
            List<PrivateEntrepreneurOtherPartner>  privateEntrepreneurOtherPartners;
            privateEntrepreneurOtherPartners = (List<PrivateEntrepreneurOtherPartner>) privateEntrepreneurOtherPartnerRepository.findAll();
            for(CompanyOtherPartner companyOtherPartner:companyOtherPartners){
                colleaguesList.add(new OtherPartner(companyOtherPartner.getId(),"CompanyOtherPartner",companyOtherPartner.getName()));
            }
            for(IndividualOtherPartner individualOtherPartner:individualOtherPartners){
                colleaguesList.add(new OtherPartner(individualOtherPartner.getId(),"IndividualOtherPartner",individualOtherPartner.getName()));
            }
            for(PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner:privateEntrepreneurOtherPartners){
                colleaguesList.add(new OtherPartner(privateEntrepreneurOtherPartner.getId(),"PrivateEntrepreneurOtherPartner",privateEntrepreneurOtherPartner.getName()));
            }

            CashOutForRedemptionPercent cashOutForRedemptionPercent =cashOutForRedemptionPercentRepository.findOne(cashOutId);
            modelAndView.addObject("cashOutForRedemptionPercent",cashOutForRedemptionPercent);
            modelAndView.addObject("suppliers",colleaguesList);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForOtherExpensesCreate);
        }
        if(cashOutType.equals("CashOutForRent")){

            CashOutForRent cashOutForRent = cashOutForRentRepository.findOne(cashOutId);
            modelAndView.addObject("cashOutForRent",cashOutForRent);
            modelAndView.addObject("suppliers",suppliers);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForRentCreate);

        }if(cashOutType.equals("CashOutForSerivceProvider")){

            CashOutForSerivceProvider cashOutForSerivceProvider = cashOutForSerivceProviderRepository.findOne(cashOutId);
            modelAndView.addObject("cashOutForSerivceProvider",cashOutForSerivceProvider);
            modelAndView.addObject("suppliers",suppliers);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForSerivceProviderCreate);

        }if(cashOutType.equals("CashOutForTax")){

            CashOutForTax cashOutForTax = cashOutForTaxRepository.findOne(cashOutId);
            modelAndView.addObject("cashOutForTax",cashOutForTax);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForSerivceProviderCreate);
        }


        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        return  modelAndView;
    }



    /*cashoutfortax*/
    @GetMapping(value = "/cashout/cashdesk/create/cashoutfortax")
    public ModelAndView cashOutforTaxCreate(ModelAndView modelAndView ,HttpSession httpSession) {
        CashOutForTax cashOutForTax = new CashOutForTax();
        Tax tax = new Tax();
        WalletOut walletOut  =  new WalletOut();
        walletOut.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        walletOut.setOutType("CashOutForTax");
        cashOutForTax.setTax(tax);
        cashOutForTax.setWalletOut(walletOut);
        cashOutForTax.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("cashOutForTax",cashOutForTax);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOutForTaxCreate);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        return modelAndView;
    }
    @PostMapping(value = "/cashout/cashdesk/create/cashoutfortax")
    public   ModelAndView cashinfrompointofsaleCreateIndividual(@Valid CashOutForTax cashOutForTax, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("cashOutForTax", cashOutForTax);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForTaxCreate);

            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        cashOutForTaxRepository.save(cashOutForTax);
        return  modelAndView;

    }
  /* --cashoutfortax-- */



  /*cashoutForGoodsProvider*/
    @GetMapping(value = "cashout/cashdesk/create/cashoutforgoodsprovider")
    public ModelAndView cashoutForGoodsProvider(ModelAndView modelAndView ,HttpSession httpSession) {

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


        CashOutForGoodsProvider cashOutForGoodsProvider = new CashOutForGoodsProvider();
        WalletOut walletOut = new WalletOut();
        cashOutForGoodsProvider.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        cashOutForGoodsProvider.setWalletOut(walletOut);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("cashOutForGoodsProvider", cashOutForGoodsProvider);
        modelAndView.addObject("suppliers", suppliers);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOutForGoodsProviderCreate );
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
    return modelAndView;
    }
    @PostMapping(value = "cashout/cashdesk/create/cashoutforgoodsprovider")
   public   ModelAndView cashoutForGoodsProvider(@Valid CashOutForGoodsProvider cashOutForGoodsProvider, BindingResult bindingResult, ModelAndView modelAndView) {
         modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("cashOutForGoodsProvider", cashOutForGoodsProvider);
            modelAndView.addObject("suppliers", suppliers);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForGoodsProviderCreate );
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        if(cashOutForGoodsProvider.getSupplierType().equals("CompanySupplier")){
            cashOutForGoodsProvider.setCompanySupplier(companySupplierRepository.findOne(cashOutForGoodsProvider.getSupplierId()));
        }
        if(cashOutForGoodsProvider.getSupplierType().equals("IndividualSupplier")){
            cashOutForGoodsProvider.setIndividualSupplier(individualSupplierRepository.findOne(cashOutForGoodsProvider.getSupplierId()));
        }
        if(cashOutForGoodsProvider.getSupplierType().equals("PrivateEntrepreneurSupplier")){
            cashOutForGoodsProvider.setPrivateEntrepreneurSupplier(privateEntrepreneurSupplierRepository.findOne(cashOutForGoodsProvider.getSupplierId()));
        }
        cashOutForGoodsProvider.getWalletOut().setOutType("CashOutForGoodsProvider");
        cashOutForGoodsProvider.getWalletOut().setOrganization(cashOutForGoodsProvider.getOrganization());
        cashOutForGoodsProviderRepository.save(cashOutForGoodsProvider);

        return  modelAndView;

    }
  /*--cashoutForGoodsProvider--*/


  /*CashOutForSerivceProvider*/
  @GetMapping(value = "cashout/cashdesk/create/cashoutforserivceprovider")
  public ModelAndView cashOutForSerivceProviderCreate(ModelAndView modelAndView ,HttpSession httpSession) {

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


      CashOutForSerivceProvider cashOutForSerivceProvider = new CashOutForSerivceProvider();
      WalletOut walletOut = new WalletOut();
      cashOutForSerivceProvider.setOrganization((Organization) httpSession.getAttribute("organizationId"));
      cashOutForSerivceProvider.setWalletOut(walletOut);
      modelAndView.setViewName("app/app");
      modelAndView.addObject("cashOutForSerivceProvider", cashOutForSerivceProvider);
      modelAndView.addObject("suppliers", suppliers);
      modelAndView.addObject("navBar", this.cashNavBar);
      modelAndView.addObject("appFragment", this.cashOutFragments);
      modelAndView.addObject("fragment", this.cashOutForSerivceProviderCreate );
      modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
      return modelAndView;
  }
  @PostMapping(value = "cashout/cashdesk/create/cashoutforserivceprovider")
  public   ModelAndView cashOutForSerivceProviderCreate(@Valid CashOutForSerivceProvider cashOutForSerivceProvider, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("cashOutForGoodsProvider", cashOutForSerivceProvider);
            modelAndView.addObject("suppliers", suppliers);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashOutForSerivceProviderCreate );
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutCreate);
      modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        if(cashOutForSerivceProvider.getSupplierType().equals("CompanySupplier")){
            cashOutForSerivceProvider.setCompanySupplier(companySupplierRepository.findOne(cashOutForSerivceProvider.getSupplierId()));
        }
        if(cashOutForSerivceProvider.getSupplierType().equals("IndividualSupplier")){
            cashOutForSerivceProvider.setIndividualSupplier(individualSupplierRepository.findOne(cashOutForSerivceProvider.getSupplierId()));
          }
        if(cashOutForSerivceProvider.getSupplierType().equals("PrivateEntrepreneurSupplier")){
            cashOutForSerivceProvider.setPrivateEntrepreneurSupplier(privateEntrepreneurSupplierRepository.findOne(cashOutForSerivceProvider.getSupplierId()));
        }
      cashOutForSerivceProvider.getWalletOut().setOutType("CashOutForSerivceProvider");
      cashOutForSerivceProvider.getWalletOut().setOrganization(cashOutForSerivceProvider.getOrganization());
      cashOutForSerivceProviderRepository.save(cashOutForSerivceProvider);

        return  modelAndView;

    }
    /*--CashOutForSerivceProvider--*/

    /*CashOutForRent*/

    @GetMapping(value = "cashout/cashdesk/create/cashoutforrent")
    public ModelAndView   cashOutForRentCreate(ModelAndView modelAndView ,HttpSession httpSession) {

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


        CashOutForRent cashOutForRent = new CashOutForRent();
        WalletOut walletOut = new WalletOut();
        cashOutForRent.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        cashOutForRent.setWalletOut(walletOut);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("cashOutForRent", cashOutForRent);
        modelAndView.addObject("suppliers", suppliers);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutForRentCreate );
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value = "cashout/cashdesk/create/cashoutforrent")
    public   ModelAndView cashOutForRentCreate(@Valid CashOutForRent cashOutForRent, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("cashOutForRent", cashOutForRent);
            modelAndView.addObject("suppliers", suppliers);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashOutForRentCreate);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        if(cashOutForRent.getSupplierType().equals("CompanySupplier")){
            cashOutForRent.setCompanySupplier(companySupplierRepository.findOne(cashOutForRent.getSupplierId()));
        }
        if(cashOutForRent.getSupplierType().equals("IndividualSupplier")){
            cashOutForRent.setIndividualSupplier(individualSupplierRepository.findOne(cashOutForRent.getSupplierId()));
        }
        if(cashOutForRent.getSupplierType().equals("PrivateEntrepreneurSupplier")){
            cashOutForRent.setPrivateEntrepreneurSupplier(privateEntrepreneurSupplierRepository.findOne(cashOutForRent.getSupplierId()));
        }

        cashOutForRent.getWalletOut().setOutType("CashOutForRent");
        cashOutForRent.getWalletOut().setOrganization(cashOutForRent.getOrganization());
        cashOutForRentRepository.save(cashOutForRent);
        return  modelAndView;
    }

    /*--CashOutForRent--*/

    /*CashOutForBankAccount*/
    @GetMapping(value = "cashout/cashdesk/create/cashoutforbankaccount")
    public ModelAndView cashOutForBankAccountCreate(ModelAndView modelAndView ,HttpSession httpSession) {

        List <BankAccount> bankAccounts = new ArrayList();
        bankAccounts.addAll((Collection<? extends BankAccount>) bankAccountRepository.findAll());

        CashOutForBankAccount cashOutForBankAccount  = new CashOutForBankAccount();
        WalletOut walletOut = new WalletOut();

        cashOutForBankAccount.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        cashOutForBankAccount.setWalletOut(walletOut);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("cashOutForBankAccount", cashOutForBankAccount);
        modelAndView.addObject("bankAccounts", bankAccounts);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutForBankAccountCreate);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value = "cashout/cashdesk/create/cashoutforbankaccount")
    public ModelAndView cashOutForBankAccountCreate(@Valid CashOutForBankAccount cashOutForBankAccount, BindingResult bindingResult, ModelAndView modelAndView) {
        List <BankAccount> bankAccounts = new ArrayList();
        bankAccounts.addAll((Collection<? extends BankAccount>) bankAccountRepository.findAll());

        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("cashOutForBankAccount", cashOutForBankAccount);
            modelAndView.addObject("bankAccounts", bankAccounts);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashOutForBankAccountCreate);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        cashOutForBankAccount.setBankAccount(bankAccountRepository.findOne(cashOutForBankAccount.getBankAccountId()));
        cashOutForBankAccount.getWalletOut().setOutType("CashOutForBankAccount");
        cashOutForBankAccount.getWalletOut().setOrganization(cashOutForBankAccount.getOrganization());
        cashOutForBankAccountRepository.save(cashOutForBankAccount);
        return  modelAndView;
    }
    /*--CashOutForBankAccount--*/
    /*CashOutForCreditPayment*/
    @GetMapping(value = "cashout/cashdesk/create/cashoutforcreditpayment")
    public ModelAndView   cashOutForCreditPaymentCreate(ModelAndView modelAndView ,HttpSession httpSession) {

        suppliers = new ArrayList();
       List<CompanyOtherPartner> companyOtherPartners = (List) companyOtherPartnerRepository.findAll();
       List<IndividualOtherPartner> individualOtherPartners=(List<IndividualOtherPartner>) individualOtherPartnerRepository.findAll();
        List<PrivateEntrepreneurOtherPartner>  privateEntrepreneurOtherPartners= (List<PrivateEntrepreneurOtherPartner>) privateEntrepreneurOtherPartnerRepository.findAll();

        for(CompanyOtherPartner supplier:companyOtherPartners){
            suppliers.add(new Supplier(supplier.getId(),"CompanyOtherPartner",supplier.getName()));
        }
        for(IndividualOtherPartner supplier:individualOtherPartners){
            suppliers.add(new Supplier(supplier.getId(),"IndividualOtherPartner",supplier.getName()));
        }
        for(PrivateEntrepreneurOtherPartner supplier:privateEntrepreneurOtherPartners){
            suppliers.add(new Supplier(supplier.getId(),"PrivateEntrepreneurOtherPartner",supplier.getName()));
        }

        CashOutForCreditPayment cashOutForCreditPayment = new CashOutForCreditPayment();
        WalletOut walletOut = new WalletOut();
        cashOutForCreditPayment.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        cashOutForCreditPayment.setWalletOut(walletOut);
        modelAndView.setViewName("app/app");
         modelAndView.addObject("cashOutForCreditPayment", cashOutForCreditPayment);
        modelAndView.addObject("suppliers", suppliers);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutForCreditPaymentCreate );
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value = "cashout/cashdesk/create/cashoutforcreditpayment")
    public   ModelAndView cashOutForCreditPaymentCreate(@Valid CashOutForCreditPayment cashOutForCreditPayment, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("cashOutForRent", cashOutForCreditPayment);
            modelAndView.addObject("suppliers", suppliers);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashOutForCreditPaymentCreate);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        if( cashOutForCreditPayment.getOtherPartnerType().equals("CompanyOtherPartner")){
            cashOutForCreditPayment.setCompanyOtherPartner(companyOtherPartnerRepository.findOne(cashOutForCreditPayment.getOtherPartnerId()));
        }
        if( cashOutForCreditPayment.getOtherPartnerType().equals("IndividualOtherPartner")){
            cashOutForCreditPayment.setIndividualOtherPartner(individualOtherPartnerRepository.findOne(cashOutForCreditPayment.getOtherPartnerId()));
        }
        if( cashOutForCreditPayment.getOtherPartnerType().equals(" PrivateEntrepreneurOtherPartner")){
            cashOutForCreditPayment.setPrivateEntrepreneurOtherPartner( privateEntrepreneurOtherPartnerRepository.findOne(cashOutForCreditPayment.getOtherPartnerId()));
        }

        cashOutForCreditPayment.getWalletOut().setOutType("CashOutForCreditPayment");
        cashOutForCreditPayment.getWalletOut().setOrganization(cashOutForCreditPayment.getOrganization());
        cashOutForCreditPaymentRepository.save(cashOutForCreditPayment);
        return  modelAndView;
    }
    /*--CashOutForCreditPayment--*/
    /*CashOutForRedemptionPercent*/
    @GetMapping(value = "cashout/cashdesk/create/cashoutforredemptionpercent")
    public ModelAndView   CashOutForRedemptionPercentCreate(ModelAndView modelAndView ,HttpSession httpSession) {

        suppliers = new ArrayList();
        List<CompanyOtherPartner> companyOtherPartners = (List) companyOtherPartnerRepository.findAll();
        List<IndividualOtherPartner> individualOtherPartners=(List<IndividualOtherPartner>) individualOtherPartnerRepository.findAll();
        List<PrivateEntrepreneurOtherPartner>  privateEntrepreneurOtherPartners= (List<PrivateEntrepreneurOtherPartner>) privateEntrepreneurOtherPartnerRepository.findAll();

         for(CompanyOtherPartner supplier:companyOtherPartners){
            suppliers.add(new Supplier(supplier.getId(),"CompanyOtherPartner",supplier.getName()));
        }
        for(IndividualOtherPartner supplier:individualOtherPartners){
            suppliers.add(new Supplier(supplier.getId(),"IndividualOtherPartner",supplier.getName()));
        }
        for(PrivateEntrepreneurOtherPartner supplier:privateEntrepreneurOtherPartners){
            suppliers.add(new Supplier(supplier.getId(),"PrivateEntrepreneurOtherPartner",supplier.getName()));
        }

        CashOutForRedemptionPercent cashOutForRedemptionPercent = new CashOutForRedemptionPercent();
         WalletOut walletOut = new WalletOut();
        cashOutForRedemptionPercent.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        cashOutForRedemptionPercent.setWalletOut(walletOut);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("cashOutForRedemptionPercent", cashOutForRedemptionPercent);
        modelAndView.addObject("suppliers", suppliers);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutForRedemptionPercentCreate );
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
       return modelAndView;
    }
     @PostMapping(value = "cashout/cashdesk/create/cashoutforredemptionpercent")
    public   ModelAndView CashOutForRedemptionPercentCreate(@Valid CashOutForRedemptionPercent cashOutForRedemptionPercent, BindingResult bindingResult, ModelAndView modelAndView) {
       modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("cashOutForRedemptionPercent", cashOutForRedemptionPercent);
            modelAndView.addObject("suppliers", suppliers);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashOutForRedemptionPercentCreate);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

       modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutCreate);
         modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        if( cashOutForRedemptionPercent.getOtherPartnerType().equals("CompanyOtherPartner")){
            cashOutForRedemptionPercent.setCompanyOtherPartner(companyOtherPartnerRepository.findOne(cashOutForRedemptionPercent.getOtherPartnerId()));
        }
        if( cashOutForRedemptionPercent.getOtherPartnerType().equals("IndividualOtherPartner")){
            cashOutForRedemptionPercent.setIndividualOtherPartner(individualOtherPartnerRepository.findOne(cashOutForRedemptionPercent.getOtherPartnerId()));
        }
        if( cashOutForRedemptionPercent.getOtherPartnerType().equals(" PrivateEntrepreneurOtherPartner")){
            cashOutForRedemptionPercent.setPrivateEntrepreneurOtherPartner( privateEntrepreneurOtherPartnerRepository.findOne(cashOutForRedemptionPercent.getOtherPartnerId()));
        }

         cashOutForRedemptionPercent.getWalletOut().setOutType("CashOutForRedemptionPercent");
         cashOutForRedemptionPercent.getWalletOut().setOrganization(cashOutForRedemptionPercent.getOrganization());
         cashOutForRedemptionPercentRepository.save(cashOutForRedemptionPercent);
        return  modelAndView;
    }
    /*--CashOutForRedemptionPercent--*/
    /*CashOutForLoanPayment*/
    @GetMapping(value = "cashout/cashdesk/create/cashoutforloanpayment")
    public ModelAndView   CashOutForLoanPaymentCreate(ModelAndView modelAndView ,HttpSession httpSession) {

        suppliers = new ArrayList();
        List<CompanyOtherPartner> companyOtherPartners = (List) companyOtherPartnerRepository.findAll();
        List<IndividualOtherPartner> individualOtherPartners=(List<IndividualOtherPartner>) individualOtherPartnerRepository.findAll();
        List<PrivateEntrepreneurOtherPartner>  privateEntrepreneurOtherPartners= (List<PrivateEntrepreneurOtherPartner>) privateEntrepreneurOtherPartnerRepository.findAll();

        for(CompanyOtherPartner supplier:companyOtherPartners){
            suppliers.add(new Supplier(supplier.getId(),"CompanyOtherPartner",supplier.getName()));
        }
        for(IndividualOtherPartner supplier:individualOtherPartners){
            suppliers.add(new Supplier(supplier.getId(),"IndividualOtherPartner",supplier.getName()));
        }
        for(PrivateEntrepreneurOtherPartner supplier:privateEntrepreneurOtherPartners){
            suppliers.add(new Supplier(supplier.getId(),"PrivateEntrepreneurOtherPartner",supplier.getName()));
        }

        CashOutForLoanPayment  cashOutForLoanPayment = new CashOutForLoanPayment();
          WalletOut walletOut = new WalletOut();

        cashOutForLoanPayment.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        cashOutForLoanPayment.setWalletOut(walletOut);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("cashOutForLoanPayment", cashOutForLoanPayment);
        modelAndView.addObject("suppliers", suppliers);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutForLoanPaymentCreate );
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value = "cashout/cashdesk/create/cashoutforloanpayment")
    public   ModelAndView CashOutForLoanPaymentCreate(@Valid CashOutForLoanPayment cashOutForLoanPayment, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("cashOutForLoanPayment", cashOutForLoanPayment);
            modelAndView.addObject("suppliers", suppliers);
            modelAndView.addObject("navBar", this.cashNavBar);
          modelAndView.addObject("fragment", this.cashOutForLoanPaymentCreate);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        if( cashOutForLoanPayment.getOtherPartnerType().equals("CompanyOtherPartner")){
            cashOutForLoanPayment.setCompanyOtherPartner(companyOtherPartnerRepository.findOne(cashOutForLoanPayment.getOtherPartnerId()));
        }
        if( cashOutForLoanPayment.getOtherPartnerType().equals("IndividualOtherPartner")){
            cashOutForLoanPayment.setIndividualOtherPartner(individualOtherPartnerRepository.findOne(cashOutForLoanPayment.getOtherPartnerId()));
        }
        if( cashOutForLoanPayment.getOtherPartnerType().equals(" PrivateEntrepreneurOtherPartner")){
            cashOutForLoanPayment.setPrivateEntrepreneurOtherPartner( privateEntrepreneurOtherPartnerRepository.findOne(cashOutForLoanPayment.getOtherPartnerId()));
        }
        cashOutForLoanPayment.getWalletOut().setOutType("CashOutForLoanPayment");
        cashOutForLoanPayment.getWalletOut().setOrganization(cashOutForLoanPayment.getOrganization());
        cashOutForLoanPaymentRepository.save(cashOutForLoanPayment);
        return  modelAndView;
    }

    /*--CashOutForLoanPayment--*/

    /*CashOutForBankSpending*/

    @GetMapping(value = "cashout/cashdesk/create/cashoutforbankspending")
    public ModelAndView   CashOutForBankSpendingCreate(ModelAndView modelAndView ,HttpSession httpSession) {


        CashOutForBankSpending  cashOutForBankSpending = new CashOutForBankSpending();
        WalletOut walletOut = new WalletOut();
        cashOutForBankSpending.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        cashOutForBankSpending.setWalletOut(walletOut);
       modelAndView.setViewName("app/app");
        modelAndView.addObject("cashOutForBankSpending", cashOutForBankSpending);
        modelAndView.addObject("suppliers", suppliers);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOutForBankSpendingCreate );
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
         return modelAndView;
    }
    @PostMapping(value = "cashout/cashdesk/create/cashoutforbankspending")
    public   ModelAndView CashOutForBankSpendingCreate(@Valid CashOutForBankSpending cashOutForBankSpending, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("cashOutForBankSpending", cashOutForBankSpending);
            modelAndView.addObject("suppliers", suppliers);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashOutForBankSpendingCreate);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        cashOutForBankSpending.getWalletOut().setOutType("CashOutForBankSpending");
        cashOutForBankSpending.getWalletOut().setOrganization(cashOutForBankSpending.getOrganization());
        cashOutForBankSpendingRepository.save(cashOutForBankSpending);
        return  modelAndView;
    }

    /*--CashOutForBankSpending--*/
    /*CashOutForOtherExpenses*/
    @GetMapping(value = "cashout/cashdesk/create/cashoutforotherexpenses")
    public ModelAndView   CashOutForOtherExpensesCreate(ModelAndView modelAndView ,HttpSession httpSession) {


        CashOutForOtherExpenses  cashOutForOtherExpenses = new CashOutForOtherExpenses();
         WalletOut walletOut = new WalletOut();
        cashOutForOtherExpenses.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        cashOutForOtherExpenses.setWalletOut(walletOut);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("cashOutForOtherExpenses", cashOutForOtherExpenses);
        modelAndView.addObject("suppliers", suppliers);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOutForOtherExpensesCreate );
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value = "cashout/cashdesk/create/cashoutforotherexpenses")
    public   ModelAndView CashOutForOtherExpensesCreate(@Valid CashOutForOtherExpenses cashOutForOtherExpenses, BindingResult bindingResult, ModelAndView modelAndView) {
      modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("cashOutForOtherExpenses", cashOutForOtherExpenses);
            modelAndView.addObject("suppliers", suppliers);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("appFragment", this.cashOutFragments);
            modelAndView.addObject("fragment", this.cashOutForOtherExpensesCreate);
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("appFragment", this.cashOutFragments);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        cashOutForOtherExpenses.getWalletOut().setOutType("CashOutForOtherExpenses");
        cashOutForOtherExpenses.getWalletOut().setOrganization(cashOutForOtherExpenses.getOrganization());

        cashOutForOtherExpensesRepository.save(cashOutForOtherExpenses);
        return  modelAndView;
    }
    /*--CashOutForOtherExpenses--*/
}
