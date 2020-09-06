package com.app.module.master.service;

import java.util.List;

import com.app.beans.StatusBean;
import com.app.beans.UnitsBean;

public interface IUnitsService {
	public List<UnitsBean> getAllUnits();

	public StatusBean saveOrUpdateUnits(UnitsBean unitsBeanRequest);

	StatusBean deleteUnits(Long unitsId);

}
