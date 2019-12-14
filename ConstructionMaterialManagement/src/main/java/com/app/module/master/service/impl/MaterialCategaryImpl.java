package com.app.module.master.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.MaterialCategaryBean;
import com.app.beans.StatusBean;
import com.app.model.Items;
import com.app.model.MaterialCategary;
import com.app.model.Purchase;
import com.app.model.Vendor;
import com.app.module.master.dao.IMaterialCategaryDao;
import com.app.module.master.service.IMaterialCategaryService;
import com.app.utility.GenericConstant;

@Service
public class MaterialCategaryImpl implements IMaterialCategaryService {

	@Autowired
	IMaterialCategaryDao materialCategaryDao;

	@Override
	public List<MaterialCategary> getallmaterialcategary() {
		return materialCategaryDao.findAll();
	}

	@Override
	public StatusBean saveMaterialcategary(MaterialCategaryBean materialCategaryBeanRequest) {
		StatusBean statusBean = new StatusBean();
		try {
			MaterialCategary materialCategary = new MaterialCategary();
			if (materialCategaryBeanRequest != null) {
				BeanUtils.copyProperties(materialCategaryBeanRequest, materialCategary);
				materialCategaryDao.save(materialCategary);
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
