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

import com.app.beans.UnitsBean;
import com.app.beans.StatusBean;
import com.app.module.master.service.IUnitsService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/units")
public class UnitsController {

	@Autowired
	IUnitsService unitsService;

	@GetMapping(value = "/getAllUnits", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllUnits() {
		List<UnitsBean> list = unitsService.getAllUnits();
		return new Gson().toJson(list);
	}

	@PostMapping(value = "/saveUnits", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> saveUnits(@RequestBody UnitsBean unitsBeanRequest) {
		StatusBean statusBean = unitsService.saveOrUpdateUnits(unitsBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PutMapping(value = "/updateUnits", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> updateUnits(@RequestBody UnitsBean unitsBeanRequest) {
		StatusBean statusBean = unitsService.saveOrUpdateUnits(unitsBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping(value = "/deleteUnits/{unitsId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> deleteUnits(@PathVariable("unitsId") Long unitsId) {
		StatusBean statusBean = unitsService.deleteUnits(unitsId);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
