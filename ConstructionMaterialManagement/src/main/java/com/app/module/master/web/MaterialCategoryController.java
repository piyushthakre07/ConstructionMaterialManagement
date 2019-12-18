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

import com.app.beans.MaterialCategoryBean;
import com.app.beans.StatusBean;
import com.app.module.master.service.IMaterialCategoryService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/materialcategory")
public class MaterialCategoryController {

	@Autowired
	IMaterialCategoryService materialCategoryService;

	@GetMapping(value = "/showMaterialCategory", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView showMaterialCategory() {
		ModelAndView mv=new ModelAndView();
        mv.setViewName("/production/materialCategory");
		return mv;
	}
	
	@GetMapping(value = "/getallmaterialcategory", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getallmaterialcategory() {
		List<MaterialCategoryBean> list = materialCategoryService.getallmaterialcategory();
		return new Gson().toJson(list);
	}

	@PostMapping(value = "/saveMaterialcategory", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> saveMaterialcategory(
			@RequestBody MaterialCategoryBean materialCategoryBeanRequest) {
		StatusBean statusBean = materialCategoryService.saveOrUpdateMaterialcategory(materialCategoryBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PutMapping(value = "/updateMaterialcategory", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> updateMaterialcategory(
			@RequestBody MaterialCategoryBean materialCategoryBeanRequest) {
		StatusBean statusBean = materialCategoryService.saveOrUpdateMaterialcategory(materialCategoryBeanRequest);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping(value = "/deleteMaterialcategory/{materialCategoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> deleteMaterialcategory(
			@PathVariable("materialCategoryId") Long materialCategoryId) {
		StatusBean statusBean = materialCategoryService.deleteMaterialcategory(materialCategoryId);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
