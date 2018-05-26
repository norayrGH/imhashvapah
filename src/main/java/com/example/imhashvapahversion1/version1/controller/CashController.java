package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Organization;

import com.example.imhashvapahversion1.version1.Entity.cash.BankAccount;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletData;

import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.CashInFromPointOfSale;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.CashInFromSaleOfGoods;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.CashInFromServiceProvision;
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
    CashInFromSaleOfGoodsRepository cashInFromSaleOfGoodsRepository;
    @Autowired
    ClientOrganizationRepository clientOrganizationRepository;
    @Autowired
    IndividualRepository individualRepository;
    @Autowired
    CashInFromPointOfSaleRepository cashInFromPointOfSaleRepository;
    @Autowired
    CashInFromServiceProvisionRepository cashInFromServiceProvisionRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;


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

    @RequestMapping(value = "bankaccount/{id}", method = RequestMethod.GET)
    public ModelAndView bankАccount(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {


        Organization organization = organizationRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.bankaccount);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);


        return modelAndView;
    }

    @RequestMapping(value = "bankaccount/create/{id}", method = RequestMethod.GET)
    public ModelAndView bankАccountCreate(@PathVariable(value = "id") final Long id, ModelAndView modelAndView) {


        Organization organization = organizationRepository.findOne(id);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setOrganization(organization);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("bankAccount", bankAccount);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.bankaccountCreate);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);


        return modelAndView;
    }

    @PostMapping(value = "bankaccount/create/getbankname")
    public @ResponseBody String getBankName(@RequestBody Integer accountStartNumbers) {

        if (11500 >= accountStartNumbers && accountStartNumbers <= 11554)
            return "ՀԱՅԲԻԶՆԵՍԲԱՆԿ ՓԲԸ";
        if (11800 >= accountStartNumbers && accountStartNumbers <= 11817)
            return "ԱՆԵԼԻՔ ԲԱՆԿ ՓԲԸ";
        if (15100 >= accountStartNumbers && accountStartNumbers <= 15170)
            return "ԱՐԱՐԱՏԲԱՆԿ ԲԲԸ";
        if (15700 >= accountStartNumbers && accountStartNumbers <= 15715)
            return "ԱՄԵՐԻԱԲԱՆԿ ՓԲԸ";

        if (16000 >= accountStartNumbers && accountStartNumbers <= 16104)
            return "ՎՏԲ-ՀԱՅԱՍՏԱՆ ԲԱՆԿ ՓԲԸ";


        if (16300 >= accountStartNumbers && accountStartNumbers <= 16334)
            return "ՀԱՅԷԿՈՆՈՄԲԱՆԿ ԲԲԸ";


        if (16335 >= accountStartNumbers && accountStartNumbers <= 16345)
            return "ՀԷԲ Ագարակ մասնաճյուղ";


        if (16346 >= accountStartNumbers && accountStartNumbers <= 16367)
            return "ՀԱՅԷԿՈՆՈՄԲԱՆԿ ԲԲԸ";



        if (16600 >= accountStartNumbers && accountStartNumbers <= 16612)
            return "ԷՎՈԿԱԲԱՆԿ ՓԲԸ";


        if (17500 >= accountStartNumbers && accountStartNumbers <= 17505)
            return "ԲՏԱ Ինվեստ Բանկ";


        if (18100 >= accountStartNumbers && accountStartNumbers <= 18102)
            return "Զարգացման Հայկական  բանկ ԲԲԸ";

        if (19300 >= accountStartNumbers && accountStartNumbers <= 19337)
            return "ԿՈՆՎԵՐՍ ԲԱՆԿ ՓԲԸ";
        if (20500 >= accountStartNumbers && accountStartNumbers <= 20523)
            return "ԻՆԵԿՈԲԱՆԿ ՓԲԸ";
        if (20800 == accountStartNumbers)
            return "ՄԵԼԼԱԹ ԲԱՆԿ ՓԲԸ ";

        if (21400 >= accountStartNumbers && accountStartNumbers <= 21405)
            return "ԲԻԲԼՈՍ ԲԱՆԿ ԱՐՄԵՆԻԱ ՓԲԸ";


        if (21700 >= accountStartNumbers && accountStartNumbers <= 21713)
            return "Էյչ-Էս-Բի-Սի ԲԱՆԿ ՀԱՅԱՍՏԱՆ ՓԲԸ";
        if (22000 >= accountStartNumbers && accountStartNumbers <= 22058)
            return "ԱԿԲԱ-ԿՐԵԴԻՏ ԱԳՐԻԿՈԼ ԲԱՆԿ ՓԲԸ";
        if (22300 >= accountStartNumbers && accountStartNumbers <= 22324)
            return "ԱՐՑԱԽԲԱՆԿ ՓԲԸ";

        if (23500 == accountStartNumbers)
            return "Կասկադ Բանկ ՓԲԸ ՓԲԸ";

        if (23800 >= accountStartNumbers && accountStartNumbers <= 23810)
            return "Առէկսիմբանկ ՓԲԸ";
        if (24100 >= accountStartNumbers && accountStartNumbers <= 24151)
            return "ՅՈՒՆԻԲԱՆԿ ԲԲԸ";
        if (24400 == accountStartNumbers )
            return "ՄԻՋՊԵՏԱԿԱՆ ԲԱՆԿ ՆԵՐԿԱՅԱՑՈՒՑՉՈՒԹՅՈՒՆ ՀԱՅԱՍՏԱՆՈՒՄ";
        if (24700 >= accountStartNumbers && accountStartNumbers <= 25000)
            return "ԱՐԴՇԻՆԲԱՆԿ ՓԲԸ";
        if ( 25300== accountStartNumbers )
            return "Պրոկրեդիտբանկ ՓԲԸ";

        if (80100 == accountStartNumbers)
            return "ԱՐՔԱ";
        if (80200 == accountStartNumbers)
            return "Արմենիան Քարդ";
        if (90000 == accountStartNumbers)
            return "Կենտրոնական գանձապետարան";
        if (90001 == accountStartNumbers)
            return "Երևանի թիվ 1 ՏԳԲ";
        if (90002 == accountStartNumbers)
            return "Երևանի թիվ 4 ՏԳԲ";
        if (90003 == accountStartNumbers)
            return "Երևանի թիվ 3 ՏԳԲ";
        if (90005 == accountStartNumbers)
            return "Երևանի թիվ 2 ՏԳԲ";
        if (90010 == accountStartNumbers)
            return "Աբովյանի գանձապետական բաժամունք";
        if (90011 == accountStartNumbers)
            return "Եղվարդի քաղաքային գանձապետական բաժամունք";
        if (90012 == accountStartNumbers)
            return "Հրազդանի գանձապետական բաժամունք";
        if (90013 == accountStartNumbers)
            return "Չարենցավանի գանձապետական բաժամունք";
        if (90014 == accountStartNumbers)
            return "Մարտունու գանձապետական բաժամունք";
        if (90015 == accountStartNumbers)
            return "Վարդենիսի գանձապետական բաժամունք";
        if (90016 == accountStartNumbers)
            return "Սևանի գանձապետական բաժամունք";
        if (90017 == accountStartNumbers)
            return "Գավառի գանձապետական բաժամունք";
        if (90018 == accountStartNumbers)
            return "Ճամբարակի գանձապետական բաժամունք";
        if (90019 == accountStartNumbers)
            return "Աշոցկի գանձապետական բաժամունք";
        if (90020 == accountStartNumbers)
            return "Արթիկի գանձապետական բաժամունք";
        if (90021 == accountStartNumbers)
            return "Գյումրիի գանձապետական բաժամունք";
        if (90022 == accountStartNumbers)
            return "Ամասիայի գանձապետական բաժամունք";
        if (90023 == accountStartNumbers)
            return "Վանաձորի գանձապետական բաժամունք";
        if (90024 == accountStartNumbers)
            return "Սպիտակի գանձապետական բաժամունք";
        if (90025 == accountStartNumbers)
            return "Ստեփանավանի քաղաքային գանձապետական բաժամունք";
        if (90026 == accountStartNumbers)
            return "Ալավերդու գանձապետական բաժամունք";
        if (90027 == accountStartNumbers)
            return "Տաշիրի քաղաքային գանձապետական բաժամունք";
        if (90028 == accountStartNumbers)
            return "Գորիսի գանձապետական բաժամունք";
        if (90029 == accountStartNumbers)
            return "Սիսիանի գանձապետական բաժամունք";
        if (90030 == accountStartNumbers)
            return "Մեղրու գանձապետական բաժամունք";
        if (90031 == accountStartNumbers)
            return "Կապանի գանձապետական բաժամունք";
        if (90032 == accountStartNumbers)
            return "Վաղարշապատի գանձապետական բաժամունք";
        if (90033 == accountStartNumbers)
            return "Արմավիրի գանձապետական բաժամունք";
        if (90034 == accountStartNumbers)
            return "Վայքի գանձապետական բաժամունք";

        if (90035 == accountStartNumbers)
            return "Եղեգնաձորի գանձապետական բաժամունք";

        if (90036 == accountStartNumbers)
            return "Ջերմուկի գանձապետական բաժամունք";

        if (90037 == accountStartNumbers)
            return "Իջևանի գանձապետական բաժամունք";

        if (90038 == accountStartNumbers)
            return "Բերդի գանձապետական բաժամունք";

        if (90039 == accountStartNumbers)
            return "Նոյեմբերանի գանձապետական բաժամունք";

        if (90040 == accountStartNumbers)
            return "Դիլիջանի գանձապետական բաժամունք";
        if (90041 == accountStartNumbers)
            return "Արտաշատի գանձապետական բաժամունք";
        if (90042 == accountStartNumbers)
            return "Արարատի քաղաքային գանձապետական բաժամունք";
        if (90043 == accountStartNumbers)
            return "Մասիսի գանձապետական բաժամունք";
        if (90044 == accountStartNumbers)
            return "Աշտարակի քաղաքային գանձապետական բաժամունք";
        if (90045 == accountStartNumbers)
            return "Ապարանի գանձապետական բաժամունք";
        if (90046 == accountStartNumbers)
            return "Թալինի գանձապետական բաժամունք";
        if (90047 == accountStartNumbers)
            return "Բաղրամյանի գանձապետական բաժամունք";
        if (90048 == accountStartNumbers)
            return "Ծաղկահովիտի գանձապետական բաժամունք";
        if (90049 == accountStartNumbers)
            return "Մարալիկի գանձապետական բաժամունք";
        if (90300 == accountStartNumbers)
            return "Գանձապետական պահառու";
        if (91500 == accountStartNumbers)
            return "ԼՂՀ կենտրոնական գանձապետարան";
        if (91501 == accountStartNumbers)
            return "ԼՂՀ գանձ. Ստեփանակերտի տեղ.բաժ.";
        if (91502 == accountStartNumbers)
            return "ԼՂՀ գանձ.Հադրութի տեղ.բաժ.";
        if (91503 == accountStartNumbers)
            return "ԼՂՀ գանձ.Շուշիի  տեղ.բաժ.";
        if (91504 == accountStartNumbers)
            return "ԼՂՀ գանձ.Ասկերանի տեղ.բաժ.  ";
        if (91505 == accountStartNumbers)
            return "ԼՂՀ գանձ.Մարտակերտու տեղ.բաժ.";
        if (91506 == accountStartNumbers)
            return "ԼՂՀ գանձ.Մարտունու տեղ.բաժ.";

        return "";
    }


    @PostMapping(value = "bankaccount/create")
    public  ModelAndView bankАccountCreate(@Valid BankAccount bankAccount , BindingResult bindingResult, ModelAndView modelAndView  ) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bankAccount", bankAccount);
            modelAndView.addObject("organization", bankAccount.getOrganization());
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("fragment", this.bankaccountCreate);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

            return modelAndView;
        }
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization",bankAccount.getOrganization());
        modelAndView.addObject( "navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.bankaccount);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        bankAccountRepository.save(bankAccount);
        return  modelAndView;
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
    public   ModelAndView cashinfromsaleofGoodsCreate(@PathVariable(value = "id") final Long id , ModelAndView modelAndView) {
        List customerList = new ArrayList();
        Organization organization = organizationRepository.findOne(id);
         WalletIn walletIn=new WalletIn();
        CashInFromSaleOfGoods  cashInFromSaleOfGoods = new CashInFromSaleOfGoods();
        cashInFromSaleOfGoods.setWalletIn(walletIn);
        cashInFromSaleOfGoods.setOrganization(organization);
        customerList.addAll( (List) clientOrganizationRepository.findAll());
        customerList.addAll( (List) individualRepository.findAll());

        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("customerList", customerList);
        modelAndView.addObject("cashInFromSaleOfGoods", cashInFromSaleOfGoods);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateFragmentSaleOfGoods);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfromsaleofgoods" )
    public   ModelAndView cashinfromsaleofGoodsCreate(@Valid CashInFromSaleOfGoods cashInFromSaleOfGoods ,BindingResult bindingResult,ModelAndView modelAndView  ) {
        List customerList = new ArrayList();
        if (bindingResult.hasErrors()) {


            customerList.addAll( (List) clientOrganizationRepository.findAll());
            customerList.addAll( (List) individualRepository.findAll());

            modelAndView.addObject("cashInFromSaleOfGoods", cashInFromSaleOfGoods);
            modelAndView.addObject("customerList", customerList);
            modelAndView.addObject("organization", cashInFromSaleOfGoods.getOrganization());
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("fragment", this.cashInCreateFragmentSaleOfGoods);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        modelAndView.addObject("organization", cashInFromSaleOfGoods.getOrganization());
        cashInFromSaleOfGoodsRepository.save(cashInFromSaleOfGoods);
        return  modelAndView;
    }

    @RequestMapping(value = "cashin/cashdesk/create/cashinfrompointofsale/{id}" , method = RequestMethod.GET )
    public   ModelAndView cashinfrompointofSaleCreate(@PathVariable(value = "id") final Long id , ModelAndView modelAndView) {

        Organization organization = organizationRepository.findOne(id);
        WalletIn walletIn=new WalletIn();
        CashInFromPointOfSale cashInFromPointOfSale = new CashInFromPointOfSale();
        cashInFromPointOfSale.setWalletIn(walletIn);
        cashInFromPointOfSale.setOrganization(organization);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("cashInFromPointOfSale", cashInFromPointOfSale);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateCashInFromPointOfSale);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfrompointofsale" )
    public   ModelAndView cashinfrompointofSaleCreate(@Valid CashInFromPointOfSale cashInFromPointOfSale ,BindingResult bindingResult,ModelAndView modelAndView  ) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("cashInFromPointOfSale", cashInFromPointOfSale);
            modelAndView.addObject("organization", cashInFromPointOfSale.getOrganization());
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromPointOfSale);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        modelAndView.addObject("organization", cashInFromPointOfSale.getOrganization());
        cashInFromPointOfSaleRepository.save(cashInFromPointOfSale);
        return  modelAndView;
    }

    @RequestMapping(value = "cashin/cashdesk/create/cashinfromserviceprovision/{id}" , method = RequestMethod.GET )
    public   ModelAndView cashinfrompointofsaleCreate(@PathVariable(value = "id") final Long id , ModelAndView modelAndView) {
        List customerList = new ArrayList();
        Organization organization = organizationRepository.findOne(id);
        WalletIn walletIn=new WalletIn();
        CashInFromServiceProvision cashInFromServiceProvision = new CashInFromServiceProvision();
        cashInFromServiceProvision.setWalletIn(walletIn);
        cashInFromServiceProvision.setOrganization(organization);
        customerList.addAll( (List) clientOrganizationRepository.findAll());
        customerList.addAll( (List) individualRepository.findAll());

        modelAndView.setViewName("app/app");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("customerList", customerList);
        modelAndView.addObject("cashInFromServiceProvision", cashInFromServiceProvision);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateCashInFromServiceProvision);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfromserviceprovision" )
    public   ModelAndView cashinfrompointofsaleCreateSave(@Valid CashInFromServiceProvision cashInFromServiceProvision ,BindingResult bindingResult,ModelAndView modelAndView  ) {
        List customerList = new ArrayList();
        if (bindingResult.hasErrors()) {


            customerList.addAll( (List) clientOrganizationRepository.findAll());
            customerList.addAll( (List) individualRepository.findAll());

            modelAndView.addObject("cashInFromServiceProvision", cashInFromServiceProvision);
            modelAndView.addObject("customerList", customerList);
            modelAndView.addObject("organization", cashInFromServiceProvision.getOrganization());
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromServiceProvision);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        modelAndView.addObject("organization", cashInFromServiceProvision.getOrganization());
        cashInFromServiceProvisionRepository.save(cashInFromServiceProvision);
        return  modelAndView;
    }

    @RequestMapping(value = "cashin/cashdesk/create/cashinfromsaleofgoods/customer/{id}" , method = RequestMethod.GET )
    public   ModelAndView cashinfrompointofsaleCreateCustomer(@PathVariable(value = "id") final Long id , ModelAndView modelAndView) {

        Organization organization = organizationRepository.findOne(id);


        modelAndView.setViewName("app/app");
      //  modelAndView.addObject("walletIn", walletIn);
        modelAndView.addObject("organization",organization);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCashInFromSaleOfGoodsCustomer);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @RequestMapping(value = "cashin/cashdesk/create/cashinfromserviceprovision/customer/{id}" , method = RequestMethod.GET )
    public   ModelAndView cashinfromserviceprovisionCustomer(@PathVariable(value = "id") final Long id , ModelAndView modelAndView) {

        Organization organization = organizationRepository.findOne(id);


        modelAndView.setViewName("app/app");
        //  modelAndView.addObject("walletIn", walletIn);
        modelAndView.addObject("organization",organization);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCashInFromServiceProvisionCustomer);
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
    public   ModelAndView cashinfrompointofsaleCreateOrganization(@Valid ClientOrganization clientOrganization, BindingResult bindingResult, ModelAndView modelAndView) {
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
