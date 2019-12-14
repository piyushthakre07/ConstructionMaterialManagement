package com.app.module.master.service;

import java.util.List;

import com.app.beans.MaterialCategaryBean;
import com.app.beans.StatusBean;
import com.app.model.MaterialCategary;

public interface IMaterialCategaryService {
	public List<MaterialCategary> getallmaterialcategary();

	public StatusBean saveMaterialcategary(MaterialCategaryBean materialCategaryBeanRequest);
}
