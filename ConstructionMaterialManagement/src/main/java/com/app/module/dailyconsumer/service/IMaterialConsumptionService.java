package com.app.module.dailyconsumer.service;

import java.util.List;

import com.app.beans.DailyMaterialConsumptionBean;
import com.app.beans.StatusBean;

public interface IMaterialConsumptionService {
	public StatusBean saveMaterialConsumption(DailyMaterialConsumptionBean materialConsumptionBeanRequest);

	public List<DailyMaterialConsumptionBean> getAllConsumption();
}
