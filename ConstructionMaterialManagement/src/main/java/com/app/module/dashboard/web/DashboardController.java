package com.app.module.dashboard.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/home")
public class DashboardController {

	@GetMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView getAllItems() {
		ModelAndView mv=new ModelAndView();
        mv.setViewName("/production/dashboard");
		return mv;
	}
}
