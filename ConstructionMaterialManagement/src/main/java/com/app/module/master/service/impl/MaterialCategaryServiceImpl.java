package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.MaterialCategaryBean;
import com.app.beans.StatusBean;
import com.app.model.MaterialCategary;
import com.app.module.master.dao.IMaterialCategaryDao;
import com.app.module.master.service.IMaterialCategaryService;
import com.app.utility.GenericConstant;

@Service
public class MaterialCategaryServiceImpl implements IMaterialCategaryService {

	@Autowired
	IMaterialCategaryDao materialCategaryDao;

	@Override
	public List<MaterialCategaryBean> getallmaterialcategary() {
		List<MaterialCategary> listMaterialCategary = materialCategaryDao.findAll();
		return listMaterialCategary.stream().map(materialCategary -> {
			MaterialCategaryBean materialCategaryBean = new MaterialCategaryBean();
			BeanUtils.copyProperties(materialCategary, materialCategaryBean);
			return materialCategaryBean;
		}).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public StatusBean saveOrUpdateMaterialcategary(MaterialCategaryBean materialCategaryBeanRequest) {
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
	@Override
	public StatusBean deleteMaterialcategary(Long materialCategaryId) {
		StatusBean statusBean = new StatusBean();
		try {
			if (materialCategaryId != null) {
				materialCategaryDao.deleteById(materialCategaryId);
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
