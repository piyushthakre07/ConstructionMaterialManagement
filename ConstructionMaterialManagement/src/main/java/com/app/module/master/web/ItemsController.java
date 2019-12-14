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

import com.app.beans.ItemsBean;
import com.app.beans.StatusBean;
import com.app.module.master.service.IItemsService;

@RestController
@RequestMapping("/item")
public class ItemsController {

	@Autowired
	IItemsService itemsService;

	@GetMapping(value = "getAllItems", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllItems() {
		List<ItemsBean> list = itemsService.getAllItems();
		return list.toString();
	}

	@PostMapping(value = "/saveItems", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> saveItems(
			@RequestBody ItemsBean itemsBeanRequest) {
		StatusBean statusBean = itemsService.saveOrUpdateItems(itemsBeanRequest);

		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PutMapping(value = "/updateItems", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> updateItems(
			@RequestBody ItemsBean itemsBeanRequest) {
		StatusBean statusBean = itemsService.saveOrUpdateItems(itemsBeanRequest);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping(value = "/deleteItems/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StatusBean> deleteItems(
			@PathVariable("itemId") Long itemId) {
		StatusBean statusBean = itemsService.deleteItems(itemId);
		if (statusBean.isStatus())
			return new ResponseEntity<>(statusBean, HttpStatus.OK);
		else
			return new ResponseEntity<>(statusBean, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
