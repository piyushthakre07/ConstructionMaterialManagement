package com.app.module.master.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.StatusBean;
import com.app.beans.UnitConverterBean;
import com.app.model.UnitConverter;
import com.app.module.master.dao.IUnitConverterDao;
import com.app.module.master.service.IUnitConverterService;
import com.app.utility.GenericConstant;

@Service
public class UnitConverterServiceImpl implements IUnitConverterService {

	@Autowired
	IUnitConverterDao unitConverterDao;

	@Override 
	public StatusBean SaveUnitConversionDetails(UnitConverterBean unitConverterBeanRequest) {
		StatusBean statusBean = new StatusBean();
		try {
			UnitConverter unitConverter = new UnitConverter();
			if (unitConverterBeanRequest != null) {
				BeanUtils.copyProperties(unitConverterBeanRequest, unitConverter);
			} else {
				statusBean.setStatus(false);
				statusBean.setMessage(GenericConstant.FAIL);
			}
			unitConverterDao.save(unitConverter);
			statusBean.setStatus(true);
			statusBean.setMessage(GenericConstant.SUCCESS);
		} catch (Exception e) {
			statusBean.setStatus(false);
			statusBean.setMessage(GenericConstant.SERVERERROR);
		}
		return statusBean;
	}

}
