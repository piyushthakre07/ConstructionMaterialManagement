package com.app.module.master.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.beans.SitesBeans;
import com.app.beans.StatusBean;
import com.app.module.master.service.ISitesService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/sites")
public class SiteController {

	@Autowired
	ISitesService siteService;

	@GetMapping(value = "/showSite", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView showVendor() {
		ModelAndView mv=new ModelAndView();
        mv.setViewName("/production/sitesMaster");
		return mv;
	}
	
	@GetMapping(value = "/getAllSites", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllSites() {
		List<SitesBeans> list = siteService.getAllSite();
		return new Gson().toJson(list);
	}

	@PostMapping(value = "/saveSite", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> saveSite(
			@RequestBody SitesBeans siteBeanRequest) {
		StatusBean statusBean = siteService.saveOrUpdateSite(siteBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PutMapping(value = "/updateSite", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> updateSite(
			@RequestBody SitesBeans siteBeanRequest) {
		StatusBean statusBean = siteService.saveOrUpdateSite(siteBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping(value = "/deleteSite/{siteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> deleteSite(
			@PathVariable("siteId") Long siteId) {
		StatusBean statusBean = siteService.deleteSite(siteId);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
