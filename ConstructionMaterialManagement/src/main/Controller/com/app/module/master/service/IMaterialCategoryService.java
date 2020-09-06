package com.app.module.master.service;

import java.util.List;

import com.app.beans.MaterialCategoryBean;
import com.app.beans.StatusBean;

public interface IMaterialCategoryService {
	public List<MaterialCategoryBean> getallmaterialcategory();

	public StatusBean saveOrUpdateMaterialcategory(MaterialCategoryBean materialCategoryBeanRequest);
	
	StatusBean deleteMaterialcategory(Long materialCategoryId);

}
