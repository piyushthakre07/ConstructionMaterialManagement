package com.app.module.master.service;

import java.util.List;

import com.app.beans.MaterialCategaryBean;
import com.app.beans.StatusBean;

public interface IMaterialCategaryService {
	public List<MaterialCategaryBean> getallmaterialcategary();

	public StatusBean saveOrUpdateMaterialcategary(MaterialCategaryBean materialCategaryBeanRequest);
	
	StatusBean deleteMaterialcategary(Long materialCategaryId);

}
