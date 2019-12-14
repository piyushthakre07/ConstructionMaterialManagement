package com.app.module.saledetails.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.DailyMaterialConsumptionBean;
import com.app.beans.StatusBean;
import com.app.model.Contractor;
import com.app.model.DailyMaterialConsumption;
import com.app.model.Items;
import com.app.model.MaterialCategary;
import com.app.model.Sites;
import com.app.model.User;
import com.app.module.saledetails.dao.IMaterialConsumptionDao;
import com.app.module.saledetails.service.IMaterialConsumptionService;

@Service
public class MaterialConsumptionImpl implements IMaterialConsumptionService {

	@Autowired
	IMaterialConsumptionDao materialConsumptionDao;

	@Override
	public StatusBean saveMaterialConsumption(DailyMaterialConsumptionBean materialConsumptionBeanRequest) {
		StatusBean statusBean = new StatusBean();
		try {
			DailyMaterialConsumption dailyMaterialConsumption = new DailyMaterialConsumption();
			if (materialConsumptionBeanRequest != null) {
				BeanUtils.copyProperties(materialConsumptionBeanRequest, dailyMaterialConsumption);
				User user = new User();
				Contractor contractor = new Contractor();
				Items item = new Items();
				MaterialCategary materialCategary = new MaterialCategary();
				Sites sites = new Sites();
				BeanUtils.copyProperties(materialConsumptionBeanRequest.getApproveBy(), user);
				BeanUtils.copyProperties(materialConsumptionBeanRequest.getContractor(), contractor);
				BeanUtils.copyProperties(materialConsumptionBeanRequest.getItem(), item);
				BeanUtils.copyProperties(materialConsumptionBeanRequest.getMaterialCategary(), materialCategary);
				BeanUtils.copyProperties(materialConsumptionBeanRequest.getSites(), sites);

				dailyMaterialConsumption.setApproveBy(user);
				dailyMaterialConsumption.setContractor(contractor);
				dailyMaterialConsumption.setItem(item);
				dailyMaterialConsumption.setMaterialCategary(materialCategary);
				dailyMaterialConsumption.setSites(sites);
				materialConsumptionDao.save(dailyMaterialConsumption);
				statusBean.setStatus(true);
				statusBean.setMessage("Success");
			} else {
				statusBean.setStatus(false);
				statusBean.setMessage("fail");
			}
		} catch (Exception e) {
			statusBean.setStatus(false);
			statusBean.setMessage("Internal Server Error");
		}
		return statusBean;
	}

}
