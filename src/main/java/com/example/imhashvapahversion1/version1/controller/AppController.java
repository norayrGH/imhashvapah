package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Employee;
import com.example.imhashvapahversion1.version1.Entity.action.area.CircleTax;
import com.example.imhashvapahversion1.version1.Entity.enums.Address;
import com.example.imhashvapahversion1.version1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "/account")
    public ModelAndView appAction(ModelAndView modelAndView) {
        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.setViewName("start");
        return modelAndView;
    }


    @RequestMapping(value = "/account/login/success")
    public String loginSuccessAction() {
        return "redirect:/account";
    }

    @RequestMapping(value = "/account/employee/create")
    public ModelAndView employeeCreateAction(ModelAndView modelAndView) {


        modelAndView.setViewName("employee/employeeCreate");
        CircleTax circleTax = new CircleTax();
        Employee employee = new Employee();

        HashMap addresses = (HashMap) Address.getAddresses();
        modelAndView.addObject("addresses", addresses);

        modelAndView.addObject("employee", employee);
        modelAndView.addObject("circleTax", circleTax);
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
            modelAndView.setViewName("employee/employeeCreate");
            modelAndView.addObject("employee", employee);

            return modelAndView;


        } else if (employee.getRegistrationDate().getTime() > employee.getDateOfOpeningBalances().getTime()) {


            bindingResult.rejectValue("registrationDate", "form.validation.errors.invalidRegisteredDate");
            modelAndView.setViewName("employee/employeeCreate");

            modelAndView.addObject("addresses", addresses);
            modelAndView.addObject("employee" , employee);

            return modelAndView;
        }
        employeeRepository.save(employee);

        ModelAndView modelAndViewRedirect = new ModelAndView("redirect:/account");
        return modelAndViewRedirect;
    }


}
