package com.example.imhashvapahversion1.version1.controller.cash;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.BankAccount;
import com.example.imhashvapahversion1.version1.Entity.cash.Tax;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.*;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.CompanyCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.IndividualOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.PrivateEntrepreneurOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.Supplier;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    CashOutForLoanPaymentRepsitory cashOutForRedemptionPercentRepsitory;
    @Autowired
    CashOutForBankSpendingRepository cashOutForBankSpendingRepository;
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


    @GetMapping(value = "/cashout/cashdesk" )
    public ModelAndView cashOutcashDesk(ModelAndView modelAndView ) {

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOut);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        return modelAndView;
    }
    @GetMapping(value = "/cashоut/cashdesk/create")
    public ModelAndView cashOutcashdeskCreate(ModelAndView modelAndView ) {
        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        return modelAndView;

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
            modelAndView.addObject("fragment", this.cashOutForTaxCreate);
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
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
            modelAndView.addObject("fragment", this.cashOutForGoodsProviderCreate );
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
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
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutCreate);
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
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutCreate);
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
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutCreate);
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
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutCreate);
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
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

       modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutCreate);
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
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
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
        cashOutForRedemptionPercentRepsitory.save(cashOutForLoanPayment);
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
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
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
            modelAndView.addObject("fragment", this.cashOutForOtherExpensesCreate);
            modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
            return modelAndView;
        }

        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutCreate);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);
        cashOutForOtherExpenses.getWalletOut().setOutType("CashOutForOtherExpenses");
        cashOutForOtherExpenses.getWalletOut().setOrganization(cashOutForOtherExpenses.getOrganization());

        cashOutForOtherExpensesRepository.save(cashOutForOtherExpenses);
        return  modelAndView;
    }
    /*--CashOutForOtherExpenses--*/
}
