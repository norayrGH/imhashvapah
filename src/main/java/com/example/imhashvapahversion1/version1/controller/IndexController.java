package com.example.imhashvapahversion1.version1.controller;

import com.example.imhashvapahversion1.version1.Entity.User;
import com.example.imhashvapahversion1.version1.Entity.enums.Role;
import com.example.imhashvapahversion1.version1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@RequestMapping(value = "/public")
@Controller
public class IndexController {


	@Autowired
	UserRepository userRepository;



	@RequestMapping("/login")
	public ModelAndView login(ModelAndView modelAndView) {

		modelAndView.setViewName("index");
		return modelAndView;
	}


	@RequestMapping("/register")
	public String register() {

		return "register";
	}
	@PostMapping("/register")
	public String register(@RequestParam(value = "regEmail1") String regEmai
							,@RequestParam(value = "regPassword") String regPassword
							,@RequestParam(value = "underwear") String type) {




		User user = new User();
		user.setEmail(regEmai);
		user.setPassword(regPassword);
		user.isEnabled();
		user.getRoles().add(Role.valueOf(type));
		userRepository.save(user);

		return "redirect:/public/login";
	}
}