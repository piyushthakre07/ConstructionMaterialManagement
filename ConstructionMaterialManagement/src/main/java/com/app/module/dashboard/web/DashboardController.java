package com.app.module.dashboard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.beans.TotalCountBean;
import com.app.module.master.service.ICommonMasterService;

@Controller
@RequestMapping(value = "/home")
public class DashboardController {
	
	@Autowired
	ICommonMasterService commonMasterService;
	
	@GetMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView getAllItems() {
		ModelAndView mv=new ModelAndView();
		TotalCountBean bean = commonMasterService.getAllTotal();
		mv.addObject("totalCount", bean);
        mv.setViewName("/production/dashboard");
		return mv;
	}
}
