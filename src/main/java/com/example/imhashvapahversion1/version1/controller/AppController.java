package com.example.imhashvapahversion1.version1.controller;
import com.example.imhashvapahversion1.version1.Entity.Employee;
import com.example.imhashvapahversion1.version1.Entity.action.area.CircleTax;
import com.example.imhashvapahversion1.version1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "/account")
    public ModelAndView appAction(ModelAndView modelAndView) {
        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        modelAndView.addObject("employeeList",employeeList);
        modelAndView.setViewName("app");
        return modelAndView;
    }


    @RequestMapping(value = "/account/login/success")
    public String loginSuccessAction() {
        return "redirect:/account";
    }

    @RequestMapping(value = "/account/employee/create")
    public ModelAndView employeeCreateAction(ModelAndView modelAndView, BindingResult bindingResult) {


        modelAndView.setViewName("employee/employeeCreate");
        CircleTax circleTax = new CircleTax();
        Employee employee = new Employee();
        employee.setCircleTax(circleTax);
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("allProfiles");


        return modelAndView;
    }

    @RequestMapping(value = "/account/employee/create", method = RequestMethod.POST)
    public ModelAndView employeeUpAction(
            @Valid Employee employee,
            BindingResult bindingResult,
            ModelAndView modelAndView
    ) {


        if (bindingResult.hasErrors()) {

           modelAndView.setViewName("employee/employeeCreate");
            modelAndView.addObject("employee", employee);
            return modelAndView;


        }else{

            if(employee.getRegistrationDate().getTime() > employee.getDateOfOpeningBalances().getTime()) {

                bindingResult.rejectValue("registrationDate", "form.validation.errors.invalidRegisteredDate");
                modelAndView.setViewName("employee/employeeCreate");
                modelAndView.addObject("employee", employee);
                return modelAndView;
            }
        }

        employeeRepository.save(employee);


        modelAndView.addObject(employee);
        modelAndView.setViewName("app");

        return modelAndView;
    }


}
