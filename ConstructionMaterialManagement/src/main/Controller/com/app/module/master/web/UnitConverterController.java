package com.app.module.master.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.StatusBean;
import com.app.beans.UnitConverterBean;
import com.app.module.master.service.IUnitConverterService;

@RestController
@RequestMapping("/unitconverter")
public class UnitConverterController {

	@Autowired
	IUnitConverterService unitConverterService;

	@PostMapping(value = "/saveUnitConversionDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> SaveUnitConversionDetails(
			@RequestBody UnitConverterBean unitConverterBeanRequest) {
		StatusBean statusBean = unitConverterService.SaveUnitConversionDetails(unitConverterBeanRequest);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
