package com.app.module.saledetails.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.DailyMaterialConsumptionBean;
import com.app.beans.StatusBean;
import com.app.module.saledetails.service.IMaterialConsumptionService;

@RestController
@RequestMapping("/sale")
public class MaterialConsumptionController {

	@Autowired
	IMaterialConsumptionService materialConsumptionService;

	@PostMapping(value = "/saveMaterialConsumption", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> saveMaterialConsumption(
			@RequestBody DailyMaterialConsumptionBean materialConsumptionBeanRequest) {
		StatusBean statusBean = materialConsumptionService.saveMaterialConsumption(materialConsumptionBeanRequest);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
