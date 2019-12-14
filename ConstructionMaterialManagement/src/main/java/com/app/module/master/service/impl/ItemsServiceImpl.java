package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.ItemsBean;
import com.app.beans.MaterialCategaryBean;
import com.app.beans.StatusBean;
import com.app.beans.UnitsBean;
import com.app.model.Items;
import com.app.model.MaterialCategary;
import com.app.model.Units;
import com.app.module.master.dao.IItemsDao;
import com.app.module.master.service.IItemsService;
import com.app.utility.GenericConstant;

@Service
public class ItemsServiceImpl implements IItemsService {

	@Autowired
	IItemsDao itemsDao;

	@Override
	public List<ItemsBean> getAllItems() {
		List<Items> listItems = itemsDao.findAll();
		return listItems.stream().map(item -> {
			ItemsBean itemBean = new ItemsBean();
			BeanUtils.copyProperties(item, itemBean);
			if (item.getMaterialCategary() != null) {
				MaterialCategaryBean materialCategaryBean = new MaterialCategaryBean();
				BeanUtils.copyProperties(item.getMaterialCategary(), materialCategaryBean);
				itemBean.setMaterialCategary(materialCategaryBean);
			}
			if (item.getUnit() != null) {
				UnitsBean unitsBean = new UnitsBean();
				BeanUtils.copyProperties(item.getUnit(), unitsBean);
				itemBean.setUnit(unitsBean);
			}
			return itemBean;
		}).collect(Collectors.toCollection(ArrayList::new));

	}

	@Override
	public StatusBean saveOrUpdateItems(ItemsBean itemsBean) {
		StatusBean statusBean = new StatusBean();
		try {
			Items item = new Items();
			if (itemsBean != null) {
				BeanUtils.copyProperties(itemsBean, item);
				if (itemsBean.getMaterialCategary() != null) {
					MaterialCategary materialCategary = new MaterialCategary();
					materialCategary.setMaterialCategaryId(itemsBean.getMaterialCategary().getMaterialCategaryId());
					item.setMaterialCategary(materialCategary);
				}
				if(itemsBean.getUnit()!=null) {
					Units units=new Units();
					units.setUnitId(itemsBean.getUnit().getUnitId());
					item.setUnit(units);
				}
				itemsDao.save(item);
				statusBean.setStatus(true);
				statusBean.setMessage(GenericConstant.SUCCESS);
			} else {
				statusBean.setStatus(false);
				statusBean.setMessage(GenericConstant.FAIL);
			}
		} catch (Exception e) {
			statusBean.setStatus(false);
			statusBean.setMessage(GenericConstant.SERVERERROR);
		}
		return statusBean;

	}

	@Override
	public StatusBean deleteItems(Long itemId) {
		StatusBean statusBean = new StatusBean();
		try {
			if (itemId != null) {
				itemsDao.deleteById(itemId);
				statusBean.setStatus(true);
				statusBean.setMessage(GenericConstant.SUCCESS);
			} else {
				statusBean.setStatus(false);
				statusBean.setMessage(GenericConstant.FAIL);
			}
		} catch (Exception e) {
			statusBean.setStatus(false);
			statusBean.setMessage(GenericConstant.SERVERERROR);
		}
		return statusBean;

	}

}
