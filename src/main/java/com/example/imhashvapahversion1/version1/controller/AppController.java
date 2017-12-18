package com.example.imhashvapahversion1.version1.controller;
import com.example.imhashvapahversion1.version1.Entity.Employee;
import com.example.imhashvapahversion1.version1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value="/account")
   public ModelAndView appAction(ModelAndView modelAndView){
        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        modelAndView.addObject(employeeList);
        modelAndView.setViewName("app");
        return modelAndView;
    }



    @RequestMapping(value="/account/login/success")
    public String loginSuccessAction(){
        return "redirect:/account";
    }

    @RequestMapping(value = "/account/employee/create")
   public ModelAndView employeeCreateAction(ModelAndView modelAndView , BindingResult bindingResult){


        modelAndView.setViewName("employee/employeeCreate");
        modelAndView.addObject("employee",new Employee());
        modelAndView.addObject("allProfiles");


        return modelAndView;
    }

    @RequestMapping(value = "/account/employee/create", method = RequestMethod.POST)
    public @ResponseBody  ModelAndView  employeeUpAction( @RequestBody Employee employee
            , BindingResult bindingResult , ModelAndView modelAndView){



        if (bindingResult.hasErrors()) {


            modelAndView.setViewName("employee/employeeCreate");
            modelAndView.addObject(employee);
            return modelAndView;


        }

            employeeRepository.save(employee);



            modelAndView.addObject(employee);
            modelAndView.setViewName("app");

        return modelAndView;
    }



}
