package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Employee;
import com.example.imhashvapahversion1.version1.Entity.action.area.CircleTax;
import com.example.imhashvapahversion1.version1.Entity.enums.Address;
import com.example.imhashvapahversion1.version1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller("AppController")
public class AppController extends BaseController{

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "/account")
    public ModelAndView appAction(ModelAndView modelAndView) {

        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.addObject("fragment", this.startViwFragment);
        modelAndView.setViewName("start");

        return modelAndView;
    }


    @RequestMapping(value = "/account/login/success")
    public final String loginSuccessAction() {
        return "redirect:/account";
    }

    @RequestMapping(value = "/account/employee/create")
    public ModelAndView employeeCreateAction(ModelAndView modelAndView) {


        Employee employee = new Employee();
        HashMap addresses = (HashMap) Address.getAddresses();
        modelAndView.addObject("addresses", addresses);
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("fragment",this.employeeCreateFragment);
        modelAndView.setViewName("start");

        return modelAndView;
    }

    @RequestMapping(value = "/account/employee/create", method = RequestMethod.POST)
    public ModelAndView  employeeUpAction(
            @Valid Employee employee,
            BindingResult bindingResult,
            ModelAndView modelAndView

    ) {

        HashMap addresses = (HashMap) Address.getAddresses();
        if (bindingResult.hasErrors()) {

            modelAndView.addObject("addresses", addresses);
            modelAndView.addObject("employee", employee);
            modelAndView.addObject("fragment",this.employeeCreateFragment);
            modelAndView.setViewName("start");
            return modelAndView;


        } else if (employee.getRegistrationDate().getTime() > employee.getDateOfOpeningBalances().getTime()) {


            bindingResult.rejectValue("registrationDate", "form.validation.errors.invalidRegisteredDate");
            modelAndView.setViewName("start");

            modelAndView.addObject("addresses", addresses);
            modelAndView.addObject("employee" , employee);
            modelAndView.addObject("fragment", this.employeeCreateFragment);
            return modelAndView;
        }
        employeeRepository.save(employee);

        ModelAndView modelAndViewRedirect = new ModelAndView("redirect:/account");
        return modelAndViewRedirect;
    }


}
