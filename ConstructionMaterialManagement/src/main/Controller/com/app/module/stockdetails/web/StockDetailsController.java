package com.app.module.stockdetails.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.beans.StockDetailsBean;
import com.app.module.stockdetails.service.IStockService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/stock")
public class StockDetailsController {

	@Autowired
	IStockService stockService;
	
	@GetMapping(value = "/viewItemWiseStock", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView viewPurchaseItem() {
		ModelAndView mv=new ModelAndView();
        mv.setViewName("/production/showStock");
		return mv;
	}
	
	@GetMapping(value = "/getItemWiseStock", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllPurchaseBean() {
		List<StockDetailsBean> list=stockService.getItemsWiseStockDetails();
		return new Gson().toJson(list);
	}
	@GetMapping(value = "/getStockDetailsByItemId/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getStockDetailsByItemId(@PathVariable("itemId") Long itemId) {
		StockDetailsBean stockDetailsBean=stockService.getStockDetailsByItemId(itemId);
		return new Gson().toJson(stockDetailsBean);
	}
	
}
