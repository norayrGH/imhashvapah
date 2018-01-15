package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Employee;
import com.example.imhashvapahversion1.version1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("InnerAppController")
public class InnerAppController extends BaseController {
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "account/organization/index/{id}", method = RequestMethod.GET)
    public ModelAndView organization(@PathVariable(value = "id") final Long id,    ModelAndView modelAndView ) {



        Employee employee = employeeRepository.findOne(id);

        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("fragment", this.startViwFragment);

        return modelAndView;
    }
















    @RequestMapping(value = "account/organization/details/{id}", method = RequestMethod.GET)
    public ModelAndView organizationDetails(@PathVariable(value = "id") final Long id,ModelAndView modelAndView){
        Employee employee = employeeRepository.findOne(id);
        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("fragment",this.organizationDetailsFragment);
        return modelAndView;

    }
}
