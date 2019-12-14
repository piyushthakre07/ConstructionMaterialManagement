package com.app.module.saledetails.service;

import com.app.beans.DailyMaterialConsumptionBean;
import com.app.beans.StatusBean;

public interface IMaterialConsumptionService {
	public StatusBean saveMaterialConsumption(DailyMaterialConsumptionBean materialConsumptionBeanRequest);
}
