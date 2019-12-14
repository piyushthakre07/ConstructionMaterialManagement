package com.app.module.master.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.MaterialCategary;
import com.app.module.master.service.IMaterialCategaryService;

@RestController
@RequestMapping("materialcategary")
public class MaterialCategaryController {

	@Autowired
	IMaterialCategaryService materialCategaryService;
	
	@GetMapping(value = "getallmaterialcategary", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCustomerNames(){
		List<MaterialCategary> list=materialCategaryService.getallmaterialcategary();
		return list.toString();
	}
	
}
