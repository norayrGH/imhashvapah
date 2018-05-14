package com.example.imhashvapahversion1.version1.controller.cash;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.Tax;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForGoodsProvider;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForTax;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.CompanyCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.Supplier;
import com.example.imhashvapahversion1.version1.controller.BaseController;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForGoodsProviderRepository;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForTaxRepository;
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
import java.util.List;


@Controller
@RequestMapping(value = "/account/cash")
public class CashOutController extends BaseController {
    @Autowired
    CashOutForTaxRepository cashOutForTaxRepository;
    @Autowired
    CashOutForGoodsProviderRepository cashOutForGoodsProviderRepository;
    @Autowired
    CompanySupplierRepository companySupplierRepository;
    @Autowired
    IndividualSupplierRepository individualSupplierRepository;
    @Autowired
    PrivateEntrepreneurSupplierRepository privateEntrepreneurSupplierRepository;
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
        List<CompanySupplier> companySuppliers = new ArrayList<>();
        companySuppliers = companySupplierRepository.findBySupplyGoodsAndServices();
        List<IndividualSupplier> individualSuppliers = new ArrayList<>();
        individualSuppliers = individualSupplierRepository.findBySupplyGoodsAndServices();
        List<PrivateEntrepreneurSupplier>  privateEntrepreneurSuppliers = new ArrayList<>();
        privateEntrepreneurSuppliers = privateEntrepreneurSupplierRepository.findBySupplyGoodsAndServices();

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

        if(cashOutForGoodsProvider.getSupplierType()=="CompanySupplier"){
            cashOutForGoodsProvider.setCompanySupplier(companySupplierRepository.findOne(cashOutForGoodsProvider.getSupplierId()));

        }
        cashOutForGoodsProviderRepository.save(cashOutForGoodsProvider);
        return  modelAndView;

    }
  /* --cashoutfortax-- */

  /*--cashoutForGoodsProvider--*/

}
