package com.springBoot.CourseRegistration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


	@GetMapping("/loginForm")
	public String login() {
		return "login-form";
	}

	@GetMapping(value = "/loginForm", params = {"logout"})
	public String logoutRedirection() {
		return "redirect:courses/list";
	}

	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "access-denied";
	}

}
