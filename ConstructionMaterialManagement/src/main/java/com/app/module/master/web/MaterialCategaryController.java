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

import com.app.beans.MaterialCategaryBean;
import com.app.beans.StatusBean;
import com.app.model.MaterialCategary;
import com.app.module.master.service.IMaterialCategaryService;

@RestController
@RequestMapping("/materialcategary")
public class MaterialCategaryController {

	@Autowired
	IMaterialCategaryService materialCategaryService;

	@GetMapping(value = "getallmaterialcategary", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getallmaterialcategary() {
		List<MaterialCategary> list = materialCategaryService.getallmaterialcategary();
		return list.toString();
	}

	@PostMapping(value = "/saveMaterialcategary", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> saveMaterialcategary(
			@RequestBody MaterialCategaryBean materialCategaryBeanRequest) {
		StatusBean statusBean = materialCategaryService.saveOrUpdateMaterialcategary(materialCategaryBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PutMapping(value = "/updateMaterialcategary", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> updateMaterialcategary(
			@RequestBody MaterialCategaryBean materialCategaryBeanRequest) {
		StatusBean statusBean = materialCategaryService.saveOrUpdateMaterialcategary(materialCategaryBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping(value = "/deleteMaterialcategary/{materialCategaryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> deleteMaterialcategary(
			@PathVariable("materialCategaryId") Long materialCategaryId) {
		StatusBean statusBean = materialCategaryService.deleteMaterialcategary(materialCategaryId);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
