package com.app.module.stockdetails.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.StatusBean;
import com.app.beans.StockDetailsBean;
import com.app.model.StockDetails;
import com.app.module.stockdetails.dao.IStockDetailsDao;
import com.app.module.stockdetails.service.IStockService;
import com.app.utility.GenericConstant;
@Service
public class StockServiceImpl implements IStockService {
@Autowired
IStockDetailsDao stockDetailsDao;
	
	@Override
	public StatusBean saveOrUpdateStock(StockDetailsBean stockDetailsBean) {
		StatusBean statusBean = new StatusBean();
		try {
			StockDetails stockDetails = new StockDetails();
			if (stockDetailsBean != null) {
				BeanUtils.copyProperties(stockDetailsBean, stockDetails);
				stockDetailsDao.save(stockDetails);
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
