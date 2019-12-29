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

import com.app.beans.UserBean;
import com.app.beans.VendorBean;
import com.app.beans.ItemsBean;
import com.app.beans.RoleBean;
import com.app.beans.StatusBean;
import com.app.module.master.service.IRoleService;
import com.app.module.master.service.IUserService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService userService;
	
	@Autowired
	IRoleService roleService;

	@GetMapping(value = "/showUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView showVendor() {
		ModelAndView mv=new ModelAndView();
        mv.setViewName("/production/userMaster");
		return mv;
	}
	
	@GetMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView addPurchaseItem() {
		ModelAndView mv=new ModelAndView();
	    List<RoleBean> roleBeanList=roleService.getAllRole();
	    mv.addObject("role", roleBeanList);
	    mv.setViewName("/production/addUser");
		return mv;
	}
	
	@GetMapping(value = "/getAllUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllUser() {
		List<UserBean> list = userService.getAllUser();
		return new Gson().toJson(list);
	}

	@PostMapping(value = "/saveUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> saveUser(@RequestBody UserBean userBeanRequest) {
		StatusBean statusBean = userService.saveOrUpdateUser(userBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PutMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> updateUser(@RequestBody UserBean userBeanRequest) {
		StatusBean statusBean = userService.saveOrUpdateUser(userBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping(value = "/deleteUser/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> deleteUser(@PathVariable("userId") Long userId) {
		StatusBean statusBean = userService.deleteUser(userId);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
