package com.app.module.dailyconsumer.service.impl;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.ContractorBean;
import com.app.beans.DailyMaterialConsumptionBean;
import com.app.beans.ItemsBean;
import com.app.beans.MaterialCategoryBean;
import com.app.beans.SitesBeans;
import com.app.beans.StatusBean;
import com.app.beans.StockDetailsBean;
import com.app.beans.UnitsBean;
import com.app.beans.UserBean;
import com.app.model.Contractor;
import com.app.model.DailyMaterialConsumption;
import com.app.model.Items;
import com.app.model.MaterialCategory;
import com.app.model.Sites;
import com.app.model.User;
import com.app.module.dailyconsumer.dao.IMaterialConsumptionDao;
import com.app.module.dailyconsumer.dao.impl.MaterialConsumptionDaoImpl;
import com.app.module.dailyconsumer.service.IMaterialConsumptionService;
import com.app.module.stockdetails.service.IStockService;
import com.app.utility.GenericConstant;

@Service
public class MaterialConsumptionImpl implements IMaterialConsumptionService {

	@Autowired
	IMaterialConsumptionDao materialConsumptionDao;
	
	@Autowired
	IStockService stockService;
	
	@Autowired
	MaterialConsumptionDaoImpl materialConsumptionDaoImpl;

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
				
				StockDetailsBean stockDetailsBean=new StockDetailsBean();
				stockDetailsBean.setCreditOrDebit(GenericConstant.DEBIT);
				stockDetailsBean.setItem(materialConsumptionBeanRequest.getItem());
				stockDetailsBean.setQuantity(-materialConsumptionBeanRequest.getConsumptionQuantity());
				stockDetailsBean.setTransactionType(GenericConstant.SALE);
				stockService.saveOrUpdateStock(stockDetailsBean);
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
	public List<DailyMaterialConsumptionBean> getAllConsumption() {

		List<DailyMaterialConsumption> listConsumption = materialConsumptionDao.findAll();
		return listConsumption.stream().map(consumption -> {
			DailyMaterialConsumptionBean consumptionBean = new DailyMaterialConsumptionBean();
			BeanUtils.copyProperties(consumption, consumptionBean);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			consumptionBean.setConsumptionDateString(formatter.format(consumption.getConsumptionDate()));
			if(consumption.getApproveBy()!=null) {
				UserBean userBean=new UserBean();
				BeanUtils.copyProperties(consumption.getApproveBy(), userBean);
				consumptionBean.setApproveBy(userBean);
			}
			if(consumption.getMaterialCategory()!=null) {
				MaterialCategoryBean materialCategoryBean=new MaterialCategoryBean();
				BeanUtils.copyProperties(consumption.getMaterialCategory(), materialCategoryBean);
				consumptionBean.setMaterialCategory(materialCategoryBean);
			}
			if(consumption.getItem()!=null) {
				ItemsBean itemsBean=new ItemsBean();
				UnitsBean unitsBean = new UnitsBean();
				BeanUtils.copyProperties(consumption.getItem(), itemsBean);
				if (consumption.getItem().getUnit() != null)
					unitsBean.setUnitName(consumption.getItem().getUnit().getUnitName());
				itemsBean.setUnit(unitsBean);
				consumptionBean.setItem(itemsBean);
			}
			if(consumption.getContractor()!=null) {
				ContractorBean contractorBean=new ContractorBean();
				BeanUtils.copyProperties(consumption.getContractor(), contractorBean);
				consumptionBean.setContractor(contractorBean);
			}
			if(consumption.getSites()!=null) {
				SitesBeans sitesBeans=new SitesBeans();
				BeanUtils.copyProperties(consumption.getSites(), sitesBeans);
				consumptionBean.setSites(sitesBeans);
			}
			return consumptionBean;
		}).collect(Collectors.toCollection(ArrayList::new));
	
	}
	
	@Override
	public List<DailyMaterialConsumptionBean> getDateWiseConsumption(Date date) {
		if(date==null) {
			date=new Date();
		}
		
		List<DailyMaterialConsumptionBean> listConsumptionBean = materialConsumptionDaoImpl.getAllMaterialConsumption(new SimpleDateFormat("yyyy-MM-dd").format(date));
		return listConsumptionBean;
	}

}
