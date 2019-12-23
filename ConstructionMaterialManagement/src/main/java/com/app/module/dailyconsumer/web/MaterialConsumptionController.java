package com.app.module.dailyconsumer.web;

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

import com.app.beans.ContractorBean;
import com.app.beans.DailyMaterialConsumptionBean;
import com.app.beans.MaterialCategoryBean;
import com.app.beans.StatusBean;
import com.app.beans.UserBean;
import com.app.module.dailyconsumer.service.IMaterialConsumptionService;
import com.app.module.master.service.IContractorService;
import com.app.module.master.service.IItemsService;
import com.app.module.master.service.IMaterialCategoryService;
import com.app.module.master.service.ISitesService;
import com.app.module.master.service.IUserService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/consumption")
public class MaterialConsumptionController {

	@Autowired
	IMaterialConsumptionService materialConsumptionService;

	
	@Autowired
	IContractorService contractorService;
	
	@Autowired
	ISitesService sitesService;
	
	@Autowired
	IMaterialCategoryService materialCategoryService;
	
	@Autowired
	IItemsService itemsService;
	
	@Autowired
	IUserService userService;
	
	
	
	@GetMapping(value = "/viewConsumption", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView viewPurchaseItem() {
		ModelAndView mv=new ModelAndView();
        mv.setViewName("/production/showConsumption");
		return mv;
	}
	
	@GetMapping(value = "/addConsumption", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView addPurchaseItem() {
		ModelAndView mv = new ModelAndView();
		List<ContractorBean> contractorList = contractorService.getAllContractor();
		mv.addObject("contractors", contractorList);
		List<MaterialCategoryBean> materialCategoryList = materialCategoryService.getallmaterialcategory();
		mv.addObject("materialCategories", materialCategoryList);
		List<UserBean> userList = userService.getAllUser();
		mv.addObject("users", userList);
		mv.setViewName("/production/consumption");
		return mv;
	}
	
	@GetMapping(value = "/getAllConsumption", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllConsumption() {
		List<DailyMaterialConsumptionBean> list = materialConsumptionService.getAllConsumption();
		return new Gson().toJson(list);
	}
	@PostMapping(value = "/saveMaterialConsumption", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> saveMaterialConsumption(
			@RequestBody DailyMaterialConsumptionBean materialConsumptionBeanRequest) {
		StatusBean statusBean = materialConsumptionService.saveMaterialConsumption(materialConsumptionBeanRequest);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
