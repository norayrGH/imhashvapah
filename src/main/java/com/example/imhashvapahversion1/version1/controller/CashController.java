package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Organization;

import com.example.imhashvapahversion1.version1.Entity.cash.*;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.*;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashIn.*;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForSerivceProvider;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut.CashOutForTax;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.ClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.Individual;
import com.example.imhashvapahversion1.version1.Entity.enums.DateRange;
import com.example.imhashvapahversion1.version1.repository.*;
import com.example.imhashvapahversion1.version1.repository.cashIn.*;
import com.example.imhashvapahversion1.version1.repository.cashOut.CashOutForTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.config.TxNamespaceHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;
@Controller
@RequestMapping(value="/account/cash")
@SessionAttributes({"modelTrans"})
public class CashController extends BaseController {
    @Autowired
    CashInFromBankAccountRepository cashInFromBankAccountRepository;
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
    @Autowired
    CashInFromLoanRepository cashInFromLoanRepository;
    @Autowired
    CashInFromCreditRepository cashInFromCreditRepository;
    @Autowired
    WalletDataRepository walletDataRepository;
    @Autowired
    CashOutForTaxRepository cashOutForTaxRepository;
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
 /*   @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }*/
    @GetMapping(value = "")
    public ModelAndView cash(ModelAndView modelAndView) {


        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value = "/show")
    public @ResponseBody ArrayList cashShow(@RequestBody DateRange dateRange ) {
        List<GetWaletIn> temp = new ArrayList();
        ArrayList showResult = new ArrayList();
         if (dateRange.getStart() != null && dateRange.getEnd() == null) {
            temp.addAll(cashInFromBankAccountRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromCreditRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromLoanRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromPointOfSaleRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromSaleOfGoodsRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromServiceProvisionRepository.findByRangeStart(dateRange.getStart()));
            for(GetWaletIn each:temp) {
                showResult.add(each.getWalletInImpl());
            }
            return showResult;
        }else if (dateRange.getStart() == null && dateRange.getEnd() != null) {
            temp.addAll(cashInFromBankAccountRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromCreditRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromLoanRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromPointOfSaleRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromSaleOfGoodsRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromServiceProvisionRepository.findByEnd(dateRange.getEnd()));
            for(GetWaletIn each:temp) {
                showResult.add(each.getWalletInImpl());
            }
            return showResult;
        }else if (dateRange.getStart() != null && dateRange.getEnd() != null) {
             temp.addAll(cashInFromBankAccountRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            temp.addAll(cashInFromCreditRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            temp.addAll(cashInFromLoanRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            temp.addAll(cashInFromPointOfSaleRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            temp.addAll(cashInFromSaleOfGoodsRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            temp.addAll(cashInFromServiceProvisionRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            for(GetWaletIn each:temp) {
                showResult.add(each.getWalletInImpl());
            }
            return showResult;
        }
        return showResult;
    }

    @GetMapping(value = "/cashdesk")
    public ModelAndView cashdesk( ModelAndView modelAndView, HttpSession httpSession) {

        WalletData walletData = new WalletData();
        modelAndView.setViewName("app/app");
        walletData.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashdeskFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value = "/cashdesk")
    public ModelAndView cashdeskCreate(@Valid WalletData walletData, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashdeskFragment);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
            modelAndView.addObject("walletData", walletData);
            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashdeskFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        walletDataRepository.save(walletData);
        return modelAndView;
    }

    @GetMapping(value = "/bankaccount")
    public ModelAndView bankАccount( ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.bankaccount);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);


        return modelAndView;
    }
    @GetMapping(value = "/bankaccount/create")
    public ModelAndView bankАccountCreate( ModelAndView modelAndView,HttpSession httpSession) {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("bankAccount", bankAccount);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.bankaccountCreate);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);


        return modelAndView;
    }
    @PostMapping(value = "bankaccount/create/getbankname")
    public @ResponseBody String getBankName(@RequestBody Integer accountStartNumbers) {

        if (11500 >= accountStartNumbers && accountStartNumbers <= 11554)
            return "ՀԱՅԲԻԶՆԵՍԲԱՆԿ ՓԲԸ ";
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
    @GetMapping(value = "cashin/cashdesk")
    public ModelAndView cashIncashdesk(ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInFragment);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        return modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/show")
    public @ResponseBody ArrayList cashinCashdeskShow(@RequestBody DateRange dateRange ) {
        List<GetWaletIn> temp = new ArrayList();
        ArrayList showResult = new ArrayList();
        if (dateRange.isShowAll()) {
        temp.addAll((ArrayList)cashInFromBankAccountRepository.findAll());
        temp.addAll((ArrayList)cashInFromCreditRepository.findAll());
        temp.addAll((ArrayList)cashInFromLoanRepository.findAll());
        temp.addAll((ArrayList)cashInFromPointOfSaleRepository.findAll());
        temp.addAll((ArrayList)cashInFromSaleOfGoodsRepository.findAll());
        temp.addAll((ArrayList)cashInFromServiceProvisionRepository.findAll());
        for(GetWaletIn each:temp) {
            showResult.add(each.getWalletInImpl());
        }
            return showResult;
        }else

        if (dateRange.getStart() != null && dateRange.getEnd() == null) {
            temp.addAll(cashInFromBankAccountRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromCreditRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromLoanRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromPointOfSaleRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromSaleOfGoodsRepository.findByRangeStart(dateRange.getStart()));
            temp.addAll(cashInFromServiceProvisionRepository.findByRangeStart(dateRange.getStart()));
            for(GetWaletIn each:temp) {
                showResult.add(each.getWalletInImpl());
            }
            return showResult;
        }else
        if (dateRange.getStart() == null && dateRange.getEnd() != null) {
            temp.addAll(cashInFromBankAccountRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromCreditRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromLoanRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromPointOfSaleRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromSaleOfGoodsRepository.findByEnd(dateRange.getEnd()));
            temp.addAll(cashInFromServiceProvisionRepository.findByEnd(dateRange.getEnd()));
            for(GetWaletIn each:temp) {
                showResult.add(each.getWalletInImpl());
            }
            return showResult;
        }else
        if (dateRange.getStart() != null && dateRange.getEnd() != null) {
            temp.addAll(cashInFromBankAccountRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            temp.addAll(cashInFromCreditRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            temp.addAll(cashInFromLoanRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            temp.addAll(cashInFromPointOfSaleRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            temp.addAll(cashInFromSaleOfGoodsRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            temp.addAll(cashInFromServiceProvisionRepository.findByRange(dateRange.getStart(),dateRange.getEnd()));
            for(GetWaletIn each:temp) {
                showResult.add(each.getWalletInImpl());
            }
            return showResult;
        }
        return showResult;
    }
    @GetMapping(value = "cashin/cashdesk/create")
    public ModelAndView cashIncashdeskCreate(ModelAndView modelAndView ) {


        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateFragment);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);
        return modelAndView;

    }



    @GetMapping(value = "cashin/cashdesk/create/cashinfromsaleofgoods")
    public   ModelAndView cashinfromsaleofGoodsCreate(ModelAndView modelAndView, HttpSession httpSession) {
        List customerList = new ArrayList();

         WalletIn walletIn=new WalletIn();
        CashInFromSaleOfGoods cashInFromSaleOfGoods = new CashInFromSaleOfGoods();
        cashInFromSaleOfGoods.setWalletIn(walletIn);
        cashInFromSaleOfGoods.setOrganization((Organization) httpSession.getAttribute("organizationId"));

        customerList.addAll( (List) clientOrganizationRepository.findAll());
        customerList.addAll( (List) individualRepository.findAll());

        modelAndView.setViewName("app/app");
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
        cashInFromSaleOfGoods.getWalletIn().setInType("cashinfromsaleofgoods");
        cashInFromSaleOfGoodsRepository.save(cashInFromSaleOfGoods);
        return  modelAndView;
    }

    @GetMapping(value = "cashin/cashdesk/create/cashinfrombankaccount")
    public   ModelAndView cashinFromBankAccount( ModelAndView modelAndView , HttpSession httpSession) {
        ArrayList accountList;

        WalletIn walletIn=new WalletIn();
        CashInFromBankAccount cashInFromBankAccount = new CashInFromBankAccount();
        cashInFromBankAccount.setWalletIn(walletIn);
        cashInFromBankAccount.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        accountList=(ArrayList)bankAccountRepository.findAll();


        modelAndView.setViewName("app/app");

        modelAndView.addObject("accountList", accountList);
        modelAndView.addObject("cashInFromBankAccount", cashInFromBankAccount);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateBankAccount);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfrombankaccount" )
    public   ModelAndView cashinFromBankAccountCreate(@Valid CashInFromBankAccount cashInFromBankAccount ,BindingResult bindingResult,ModelAndView modelAndView  ) {
        ArrayList accountList;
        if (bindingResult.hasErrors()) {

            accountList=(ArrayList)bankAccountRepository.findAll();


            modelAndView.addObject("cashInFromSaleOfGoods", cashInFromBankAccount);
            modelAndView.addObject("accountList", accountList);
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar", this.organizationNavBar);
            modelAndView.addObject("fragment", this.cashInCreateBankAccount);
            modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);

            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        cashInFromBankAccount.getWalletIn().setInType("cashinfrombankaccount");
        cashInFromBankAccountRepository.save(cashInFromBankAccount);
        return  modelAndView;
    }

    @GetMapping(value = "cashin/cashdesk/create/cashinfromloan")
    public   ModelAndView cashInFromLoan( ModelAndView modelAndView ,HttpSession httpSession) {
        List colleaguesList = new ArrayList();
        WalletIn walletIn=new WalletIn();
        CashInFromLoan cashInFromLoan = new CashInFromLoan();
        cashInFromLoan.setWalletIn(walletIn);
        cashInFromLoan.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        colleaguesList.addAll( (List) clientOrganizationRepository.findAll() );
        colleaguesList.addAll( (List) individualRepository.findAll() );

        modelAndView.setViewName("app/app");
        modelAndView.addObject("colleaguesList", colleaguesList);
        modelAndView.addObject("cashInFromLoan", cashInFromLoan);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateCashInFromLoan);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfromloan" )
    public   ModelAndView cashInFromLoanCreate(@Valid CashInFromLoan cashInFromLoan ,BindingResult bindingResult,ModelAndView modelAndView  ) {
        List colleaguesList = new ArrayList();
        if (bindingResult.hasErrors()) {


            WalletIn walletIn=new WalletIn();
            cashInFromLoan.setWalletIn(walletIn);
            cashInFromLoan.setOrganization(cashInFromLoan.getOrganization());
            colleaguesList.addAll( (List) clientOrganizationRepository.findAll() );
            colleaguesList.addAll( (List) individualRepository.findAll() );

            modelAndView.setViewName("app/app");
            modelAndView.addObject("organization",cashInFromLoan.getOrganization());
            modelAndView.addObject("colleaguesList", colleaguesList);
            modelAndView.addObject("cashInFromLoan", cashInFromLoan);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromLoan);
            modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        cashInFromLoan.getWalletIn().setInType("cashinfromloan");
        cashInFromLoanRepository.save(cashInFromLoan);
        return  modelAndView;
    }

    @GetMapping(value = "cashin/cashdesk/create/cashinfrompointofsale")
    public   ModelAndView cashinfrompointofSaleCreate( ModelAndView modelAndView,HttpSession httpSession) {


        WalletIn walletIn=new WalletIn();
        CashInFromPointOfSale cashInFromPointOfSale = new CashInFromPointOfSale();
        cashInFromPointOfSale.setWalletIn(walletIn);
        cashInFromPointOfSale.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
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
        cashInFromPointOfSale.getWalletIn().setInType("cashinfrompointofsale");
        cashInFromPointOfSaleRepository.save(cashInFromPointOfSale);
        return  modelAndView;
    }

    @GetMapping(value = "cashin/cashdesk/create/cashinfromserviceprovision")
    public   ModelAndView cashinfrompointofsaleCreate( ModelAndView modelAndView ,HttpSession httpSession) {
        List customerList = new ArrayList();

        WalletIn walletIn=new WalletIn();
        CashInFromServiceProvision cashInFromServiceProvision = new CashInFromServiceProvision();
        cashInFromServiceProvision.setWalletIn(walletIn);
        cashInFromServiceProvision.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        customerList.addAll( (List) clientOrganizationRepository.findAll());
        customerList.addAll( (List) individualRepository.findAll());
        modelAndView.setViewName("app/app");
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
        cashInFromServiceProvision.getWalletIn().setInType("cashinfromserviceprovision");
        cashInFromServiceProvisionRepository.save(cashInFromServiceProvision);
        return  modelAndView;
    }

    @GetMapping(value = "cashin/cashdesk/create/cashinfromcredit")
    public   ModelAndView createCashInFromCredit(ModelAndView modelAndView,HttpSession httpSession) {
        List colleaguesList = new ArrayList();

        WalletIn walletIn=new WalletIn();
        CashInFromCredit cashInFromCredit = new CashInFromCredit();
        cashInFromCredit.setWalletIn(walletIn);
        cashInFromCredit.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        colleaguesList.addAll( (List) clientOrganizationRepository.findAll() );
        colleaguesList.addAll( (List) individualRepository.findAll() );

        modelAndView.setViewName("app/app");
        modelAndView.addObject("colleaguesList", colleaguesList);
        modelAndView.addObject("cashInFromCredit", cashInFromCredit);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashInCreateCashInFromCredit);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "cashin/cashdesk/create/cashinfromcredit" )
    public   ModelAndView createCashInFromCredit(@Valid CashInFromCredit cashInFromCredit ,BindingResult bindingResult,ModelAndView modelAndView,HttpSession httpSession  ) {
        List colleaguesList = new ArrayList();
        if (bindingResult.hasErrors()) {


            WalletIn walletIn=new WalletIn();
            cashInFromCredit.setWalletIn(walletIn);
            cashInFromCredit.setOrganization((Organization)httpSession.getAttribute("organizationId"));
            colleaguesList.addAll( (List) clientOrganizationRepository.findAll() );
            colleaguesList.addAll( (List) individualRepository.findAll() );

            modelAndView.setViewName("app/app");
            modelAndView.addObject("organization",cashInFromCredit.getOrganization());
            modelAndView.addObject("colleaguesList", colleaguesList);
            modelAndView.addObject("cashInFromCredit", cashInFromCredit);
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.cashInCreateCashInFromCredit);
            modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.organizationNavBar);
        modelAndView.addObject("fragment", this.cashFragment);
        modelAndView.addObject("fragmentNavBar", this.cashdeskFragmentNavBar);
        cashInFromCredit.getWalletIn().setInType("cashinfromcredit");
        cashInFromCreditRepository.save(cashInFromCredit);
        return  modelAndView;
    }


    @GetMapping(value = "create/customer")
    public   ModelAndView cashinfrompointofsaleCreateCustomer( ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.customerAndColleaguesCreate);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @GetMapping(value = "create/customer/clientorganization" )
    public   ModelAndView cashinfrompointofsaleCreateOrganization( ModelAndView modelAndView ,HttpSession httpSession) {

        modelAndView.setViewName("app/app");
        ClientOrganization clientOrganization = new ClientOrganization();
        clientOrganization.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.addObject("clientOrganization", clientOrganization);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.createClientOrganization);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "create/customer/clientorganization")
    public   ModelAndView cashinfrompointofsaleCreateOrganization(@Valid ClientOrganization clientOrganization, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("clientOrganization", clientOrganization);
            modelAndView.addObject("organization", clientOrganization.getOrganization());
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.createClientOrganization);
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
    @GetMapping(value = "create/customer/individual" )
    public   ModelAndView cashinfrompointofsaleCreateIndividual( ModelAndView modelAndView,HttpSession httpSession) {

        modelAndView.setViewName("app/app");
        Individual individual = new Individual();
        individual.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.addObject("individual", individual);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.createIndividual);
        modelAndView.addObject("fragmentNavBar", this.cashInFragmentNavBar);

        return  modelAndView;
    }
    @PostMapping(value = "create/customer/individual")
    public   ModelAndView cashinfrompointofsaleCreateIndividual(@Valid Individual individual, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("app/app");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("individual", individual);
            modelAndView.addObject("organization", individual.getOrganization());
            modelAndView.addObject("navBar", this.cashNavBar);
            modelAndView.addObject("fragment", this.createIndividual);
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

  /* @GetMapping(value = "/cashout/cashdesk/create/cashoutforserivceprovider")
     public ModelAndView cashOutForSerivceProviderCreate(ModelAndView modelAndView ,HttpSession httpSession) {
        CashOutForSerivceProvider cashOutForTax = new CashOutForSerivceProvider();
        Tax tax = new Tax();
        WalletOut walletOut  =  new WalletOut();

        cashOutForTax.setTax(tax);
        cashOutForTax.setWalletOut(walletOut);
        cashOutForTax.setOrganization((Organization) httpSession.getAttribute("organizationId"));
        modelAndView.setViewName("app/app");
        modelAndView.addObject("cashOutForTax",cashOutForTax);
        modelAndView.addObject("navBar", this.cashNavBar);
        modelAndView.addObject("fragment", this.cashOutForSerivceProviderCreate);
        modelAndView.addObject("fragmentNavBar", this.cashOutFragmentNavBar);

        return modelAndView;
    }
    @PostMapping(value = "/cashout/cashdesk/create/cashoutforserivceprovider")
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
*/
          /* /cashout/cashdesk/create/cashoutforgoodsprovider

           /cashout/cashdesk/create/cashoutforrent
           /cashout/cashdesk/create/cashoutforbankaccount
           /cashout/cashdesk/create/cashoutforcreditpayment
           /cashout/cashdesk/create/cashoutforredemptionpercent
           /cashout/cashdesk/create/cashoutforloanpayment
           /cashout/cashdesk/create/cashoutforbankspending
           /cashout/cashdesk/create/cashoutforotherexpenses*/

}
