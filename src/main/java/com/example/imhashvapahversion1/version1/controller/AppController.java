package com.example.imhashvapahversion1.version1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AppController {



    @RequestMapping(value="/account")
    String appAction(){

        return "app";
    }



    @RequestMapping(value="/account/login/success")
    String loginSuccessAction(){
        return "redirect:/account";
    }




}
