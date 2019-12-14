package com.app.module.purchase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.PurchaseBean;
import com.app.beans.StatusBean;
import com.app.module.purchase.service.IPurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	IPurchaseService purchaseService;

	@PostMapping(value = "/purchaseIteam", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> saveMaterialConsumption(
			@RequestBody PurchaseBean purchaseBeanRequest) {
		StatusBean statusBean = purchaseService.purchaseItem(purchaseBeanRequest);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
