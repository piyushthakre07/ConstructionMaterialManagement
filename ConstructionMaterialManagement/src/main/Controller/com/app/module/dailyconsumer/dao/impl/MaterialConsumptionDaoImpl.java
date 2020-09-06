package com.app.module.dailyconsumer.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.beans.DailyMaterialConsumptionBean;
@Repository
public class MaterialConsumptionDaoImpl {

	@Autowired
	EntityManager em;
	
	public List<DailyMaterialConsumptionBean> getAllMaterialConsumption(String date) {
		List<DailyMaterialConsumptionBean> list = em.createQuery(
				"select new com.app.beans.DailyMaterialConsumptionBean(contractor.contractorName,sites.siteName,sites.siteAddress,workType,materialCategory.materialCategoryName,item.itemName,SUM(consumptionQuantity),item.unit.unitName)  from DailyMaterialConsumption dmc where consumptionDate like'"+date+"%' GROUP BY  dmc.item.itemId,dmc.contractor.contractorName,dmc.sites.siteName")
				.getResultList();

		return list;
	}
}
