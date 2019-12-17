package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.ItemsBean;
import com.app.beans.MaterialCategoryBean;
import com.app.beans.StatusBean;
import com.app.model.MaterialCategory;
import com.app.module.master.dao.IMaterialCategoryDao;
import com.app.module.master.service.IMaterialCategoryService;
import com.app.utility.GenericConstant;

@Service
public class MaterialCategoryServiceImpl implements IMaterialCategoryService {

	@Autowired
	IMaterialCategoryDao materialCategoryDao;

	@Override
	public List<MaterialCategoryBean> getallmaterialcategory() {
		List<MaterialCategory> listMaterialCategory = materialCategoryDao.findAll();
		return listMaterialCategory.stream().map(materialCategory -> {
			MaterialCategoryBean materialCategoryBean = new MaterialCategoryBean();
			BeanUtils.copyProperties(materialCategory, materialCategoryBean);
			return materialCategoryBean;
		}).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public StatusBean saveOrUpdateMaterialcategory(MaterialCategoryBean materialCategoryBeanRequest) {
		StatusBean statusBean = new StatusBean();
		try {
			MaterialCategory materialCategory = new MaterialCategory();
			if (materialCategoryBeanRequest != null) {
				BeanUtils.copyProperties(materialCategoryBeanRequest, materialCategory);
				materialCategoryDao.save(materialCategory);
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
	public StatusBean deleteMaterialcategory(Long materialCategoryId) {
		StatusBean statusBean = new StatusBean();
		try {
			if (materialCategoryId != null) {
				materialCategoryDao.deleteById(materialCategoryId);
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
