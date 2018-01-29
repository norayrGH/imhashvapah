package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Employee;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletData;
import com.example.imhashvapahversion1.version1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.xml.ws.BindingProvider;

@Controller("InnerAppController")
public class InnerAppController extends BaseController {
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "/account/organization/{id}", method = RequestMethod.GET)
    public ModelAndView organization(@PathVariable(value = "id") final Long id,    ModelAndView modelAndView ) {



        Employee employee = employeeRepository.findOne(id);

        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("navBar",this.organizationNavBar);
        modelAndView.addObject("fragment", this.startViwFragment);

        return modelAndView;
    }

    @RequestMapping(value = "/account/organization/details/{id}", method = RequestMethod.GET)
    public ModelAndView organizationDetails(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){
        Employee employee = employeeRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("navBar",this.organizationNavBar);
        modelAndView.addObject("fragment",this.organizationDetailsFragment);
        return modelAndView;

    }

    @RequestMapping(value = "/account/cash/{id}", method = RequestMethod.GET)
    public ModelAndView cash(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){


        Employee employee = employeeRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("navBar",this.cashNavBar);
        modelAndView.addObject("fragment",this.cashFragment);
        modelAndView.addObject("fragmentNavBar",this.cashdeskFragmentNavBar);





        return modelAndView;
    }
    @RequestMapping(value = "/account/cash/cashdesk/{id}", method = RequestMethod.GET)
    public ModelAndView cashdesk(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){

        WalletData walletData = new WalletData();
        Employee employee = employeeRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar",this.cashNavBar);
        modelAndView.addObject("fragment",this.cashdeskFragment);
        modelAndView.addObject("fragmentNavBar",this.cashdeskFragmentNavBar);




        return modelAndView;
    }
    @RequestMapping(value = "/account/cash/cashdesk", method = RequestMethod.POST)
    public ModelAndView cashdeskCreate(@Valid WalletData walletData, BindingResult bindingResult, ModelAndView modelAndView ){

        Employee employee = employeeRepository.findOne(walletData.getEmployeeId());

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("app/app");
            modelAndView.addObject("navBar" ,this.cashNavBar);
            modelAndView.addObject("fragment",this.cashdeskFragment);
            modelAndView.addObject("fragmentNavBar",this.cashdeskFragmentNavBar);
            modelAndView.addObject("employee", employee);
            modelAndView.addObject("walletData", walletData);
            return modelAndView;
        }

        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar" ,this.cashNavBar);
        modelAndView.addObject("fragment",this.cashdeskFragment);
        modelAndView.addObject("fragmentNavBar",this.cashdeskFragmentNavBar);

        return modelAndView;
    }

    @RequestMapping(value = "/account/cash/cashin/cashdesk/{id}", method = RequestMethod.GET)
    public ModelAndView cashIncashdesk(@PathVariable(value = "id") final Long id,  ModelAndView modelAndView ){

        Employee employee = employeeRepository.findOne(id);
        WalletData walletData = new WalletData();
        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar" ,this.cashNavBar);
        modelAndView.addObject("fragment",this.cashInFragment);
        modelAndView.addObject("fragmentNavBar",this.cashInFragmentNavBar);

        return modelAndView;
    }
    @RequestMapping(value = "/account/cash/cashout/cashdesk/{id}", method = RequestMethod.GET)
    public ModelAndView cashOutcashdesk(@PathVariable(value = "id") final Long id,  ModelAndView modelAndView ){

        Employee employee = employeeRepository.findOne(id);
        WalletData walletData = new WalletData();
        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("walletData", walletData);
        modelAndView.addObject("navBar"  ,this.cashNavBar);
        modelAndView.addObject("fragment",this.cashOutFragment);
        modelAndView.addObject("fragmentNavBar",this.cashOutFragmentNavBar);

        return modelAndView;
    }


    @RequestMapping(value = "account/partner/customer/debt/{id}", method = RequestMethod.GET)
    public ModelAndView partnerCustomerDebt(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){


        Employee employee = employeeRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("navBar",this.partnerNavBar);
        modelAndView.addObject("fragment",this.partnerFragment);





        return modelAndView;
    }

    @RequestMapping(value = "account/employee/{id}", method = RequestMethod.GET)
    public ModelAndView employee(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){


        Employee employee = employeeRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("navBar",this.employeeNavBar);
        modelAndView.addObject("fragment",this.employeeFragment);





        return modelAndView;
    }

    @RequestMapping(value = "account/report/summary/{id}", method = RequestMethod.GET)
    public ModelAndView reportSummary (@PathVariable(value = "id") final Long id,ModelAndView modelAndView){


        Employee employee = employeeRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("navBar",this.reportNavBar);
        modelAndView.addObject("fragment",this.reportFragment);





        return modelAndView;
    }

}




