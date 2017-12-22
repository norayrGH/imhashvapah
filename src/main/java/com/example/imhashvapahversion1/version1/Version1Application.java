package com.example.imhashvapahversion1.version1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SpringBootApplication
public class Version1Application {

	public static void main(String[] args) {
		SpringApplication.run(Version1Application.class, args);
	}

}
