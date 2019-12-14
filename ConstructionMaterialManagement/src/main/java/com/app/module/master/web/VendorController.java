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

import com.app.beans.VendorBean;
import com.app.beans.StatusBean;
import com.app.module.master.service.IVendorService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	IVendorService siteService;

	@GetMapping(value = "/getAllVendor", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllVendor() {
		List<VendorBean> list = siteService.getAllVendor();
		return new Gson().toJson(list);
	}

	@PostMapping(value = "/saveVendor", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> saveVendor(@RequestBody VendorBean siteBeanRequest) {
		StatusBean statusBean = siteService.saveOrUpdateVendor(siteBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PutMapping(value = "/updateVendor", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> updateVendor(@RequestBody VendorBean siteBeanRequest) {
		StatusBean statusBean = siteService.saveOrUpdateVendor(siteBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping(value = "/deleteVendor/{siteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> deleteVendor(@PathVariable("siteId") Long siteId) {
		StatusBean statusBean = siteService.deleteVendor(siteId);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
