package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.StatusBean;
import com.app.beans.UnitsBean;
import com.app.model.Units;
import com.app.module.master.dao.IUnitsDao;
import com.app.module.master.service.IUnitsService;
import com.app.utility.GenericConstant;

@Service
public class UnitsServiceImpl implements IUnitsService {

	@Autowired
	IUnitsDao unitsDao;

	@Override
	public List<UnitsBean> getAllUnits() {
		List<Units> listUnits = unitsDao.findAll();
		return listUnits.stream().map(units -> {
			UnitsBean unitsBean = new UnitsBean();
			BeanUtils.copyProperties(units, unitsBean);
			unitsBean.setItems(null);
			return unitsBean;
		}).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public StatusBean saveOrUpdateUnits(UnitsBean unitsBeanRequest) {
		StatusBean statusBean = new StatusBean();
		try {
			Units units = new Units();
			if (unitsBeanRequest != null) {
				BeanUtils.copyProperties(unitsBeanRequest, units);
				unitsDao.save(units);
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
	public StatusBean deleteUnits(Long unitsId) {
		StatusBean statusBean = new StatusBean();
		try {
			if (unitsId != null) {
				unitsDao.deleteById(unitsId);
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
