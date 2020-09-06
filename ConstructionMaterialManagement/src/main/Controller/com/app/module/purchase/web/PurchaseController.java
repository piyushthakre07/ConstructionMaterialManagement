package com.app.module.purchase.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.beans.ItemsBean;
import com.app.beans.PurchaseBean;
import com.app.beans.StatusBean;
import com.app.beans.VendorBean;
import com.app.module.master.service.IItemsService;
import com.app.module.master.service.IVendorService;
import com.app.module.purchase.service.IPurchaseService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	IPurchaseService purchaseService;
	
	@Autowired
	IItemsService itemsService;
	
	@Autowired
	IVendorService vendorService;

	@GetMapping(value = "/viewPurchaseItem", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView viewPurchaseItem() {
		ModelAndView mv=new ModelAndView();
        mv.setViewName("/production/showPurchase");
		return mv;
	}
	
	@GetMapping(value = "/addPurchaseItem", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView addPurchaseItem() {
		ModelAndView mv=new ModelAndView();
	    List<ItemsBean> itemList=itemsService.getAllItems();
	    mv.addObject("items", itemList);
	    List<VendorBean> vendorList=vendorService.getAllVendor();
	    mv.addObject("vendors", vendorList);
        mv.setViewName("/production/purchase");
		return mv;
	}
	
	@GetMapping(value = "/getAllPurchase", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllPurchaseBean() {
		List<PurchaseBean> list = purchaseService.getAllPurchase();
		return new Gson().toJson(list);
	}
	
	@PostMapping(value = "/purchaseIteam",
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<StatusBean> purchaseIteam(
			@RequestBody PurchaseBean purchaseBeanRequest) {
		StatusBean statusBean = purchaseService.purchaseItem(purchaseBeanRequest);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
