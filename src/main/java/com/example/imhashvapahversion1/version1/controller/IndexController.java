package com.example.imhashvapahversion1.version1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
@RequestMapping(value = "/account")
@Controller
public class IndexController {






	@RequestMapping("/login")
	public String login() {


		return "index";
	}



	@RequestMapping("/register")
	public String register() {

		return "register";
	}

}