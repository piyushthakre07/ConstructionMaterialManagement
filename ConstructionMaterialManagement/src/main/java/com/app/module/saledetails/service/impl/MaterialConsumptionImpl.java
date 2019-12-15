package com.app.module.saledetails.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.DailyMaterialConsumptionBean;
import com.app.beans.StatusBean;
import com.app.model.Contractor;
import com.app.model.DailyMaterialConsumption;
import com.app.model.Items;
import com.app.model.MaterialCategory;
import com.app.model.Sites;
import com.app.model.User;
import com.app.module.saledetails.dao.IMaterialConsumptionDao;
import com.app.module.saledetails.service.IMaterialConsumptionService;
import com.app.utility.GenericConstant;

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
				MaterialCategory materialCategory = new MaterialCategory();
				Sites sites = new Sites();
				BeanUtils.copyProperties(materialConsumptionBeanRequest.getApproveBy(), user);
				BeanUtils.copyProperties(materialConsumptionBeanRequest.getContractor(), contractor);
				BeanUtils.copyProperties(materialConsumptionBeanRequest.getItem(), item);
				BeanUtils.copyProperties(materialConsumptionBeanRequest.getMaterialCategory(), materialCategory);
				BeanUtils.copyProperties(materialConsumptionBeanRequest.getSites(), sites);

				dailyMaterialConsumption.setApproveBy(user);
				dailyMaterialConsumption.setContractor(contractor);
				dailyMaterialConsumption.setItem(item);
				dailyMaterialConsumption.setMaterialCategory(materialCategory);
				dailyMaterialConsumption.setSites(sites);
				materialConsumptionDao.save(dailyMaterialConsumption);
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
