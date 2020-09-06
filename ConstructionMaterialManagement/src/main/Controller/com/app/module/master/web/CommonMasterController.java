package com.app.module.master.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.TotalCountBean;
import com.app.module.master.service.ICommonMasterService;
import com.google.gson.Gson;

@RestController
@RequestMapping(value="/commonMaster")
public class CommonMasterController{
	
	@Autowired
	ICommonMasterService commonMasterService;

	@GetMapping(value = "/getTotalCount", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllContractor() {
		TotalCountBean bean = commonMasterService.getAllTotal();
		return new Gson().toJson(bean);
	}

}