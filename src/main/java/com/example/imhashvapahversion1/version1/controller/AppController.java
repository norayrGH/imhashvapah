package com.example.imhashvapahversion1.version1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @RequestMapping(value="/")
    String appAction(){

        return "app";
    }




}
