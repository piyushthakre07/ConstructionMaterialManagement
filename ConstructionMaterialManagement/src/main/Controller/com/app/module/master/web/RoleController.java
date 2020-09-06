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

import com.app.beans.RoleBean;
import com.app.beans.StatusBean;
import com.app.module.master.service.IRoleService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	IRoleService roleService;

	@GetMapping(value = "/showRole", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView showVendor() {
		ModelAndView mv=new ModelAndView();
        mv.setViewName("/production/roleMaster");
		return mv;
	}
	
	@GetMapping(value = "/getAllRole", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllRole() {
		List<RoleBean> list = roleService.getAllRole();
		return new Gson().toJson(list);
	}

	@PostMapping(value = "/saveRole", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> saveRole(@RequestBody RoleBean roleBeanRequest) {
		StatusBean statusBean = roleService.saveOrUpdateRole(roleBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PutMapping(value = "/updateRole", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> updateRole(@RequestBody RoleBean roleBeanRequest) {
		StatusBean statusBean = roleService.saveOrUpdateRole(roleBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping(value = "/deleteRole/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> deleteRole(@PathVariable("roleId") Long roleId) {
		StatusBean statusBean = roleService.deleteRole(roleId);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
