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

import com.app.beans.ContractorBean;
import com.app.beans.StatusBean;
import com.app.module.master.service.IContractorService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/contractor")
public class ContractorController {

	@Autowired
	IContractorService contractorService;

	@GetMapping(value = "/showContractor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView showVendor() {
		ModelAndView mv=new ModelAndView();
        mv.setViewName("/production/contractorMaster");
		return mv;
	}
	
	@GetMapping(value = "/getAllContractor", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllContractor() {
		List<ContractorBean> list = contractorService.getAllContractor();
		return new Gson().toJson(list);
	}

	@PostMapping(value = "/saveContractor", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> saveContractor(@RequestBody ContractorBean contractorBeanRequest) {
		StatusBean statusBean = contractorService.saveOrUpdateContractor(contractorBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PutMapping(value = "/updateContractor", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> updateContractor(@RequestBody ContractorBean contractorBeanRequest) {
		StatusBean statusBean = contractorService.saveOrUpdateContractor(contractorBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping(value = "/deleteContractor/{contractorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> deleteContractor(@PathVariable("contractorId") Long contractorId) {
		StatusBean statusBean = contractorService.deleteContractor(contractorId);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
