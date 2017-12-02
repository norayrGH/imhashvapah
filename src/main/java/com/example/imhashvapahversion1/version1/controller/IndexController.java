package com.example.imhashvapahversion1.version1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.UUID;

@RequestMapping(value = "/public")
@Controller
public class IndexController {






	@RequestMapping("/login")
	public ModelAndView login(ModelAndView modelAndView) {

		modelAndView.setViewName("index");
		return modelAndView;
	}


	@RequestMapping("/register")
	public String register() {

		return "register";
	}

}