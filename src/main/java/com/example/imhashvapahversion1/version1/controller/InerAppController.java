package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.Employee;
import com.example.imhashvapahversion1.version1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InerAppController {
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "account/organization/index/{id}",method = RequestMethod.GET)
    public ModelAndView organization(@PathVariable(value = "id")final Long id){


        Employee employee=employeeRepository.findOne(id);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("app/app");
        modelAndView.addObject("employee",employee);

        return modelAndView;
    }

}
