package com.app.module.stockdetails.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.StatusBean;
import com.app.beans.StockDetailsBean;
import com.app.model.Items;
import com.app.model.StockDetails;
import com.app.module.stockdetails.dao.IStockDetailsDao;
import com.app.module.stockdetails.dao.impl.StockDetailsDaoImpl;
import com.app.module.stockdetails.service.IStockService;
import com.app.utility.GenericConstant;
@Service
public class StockServiceImpl implements IStockService {
@Autowired
IStockDetailsDao stockDetailsDao;

@Autowired
StockDetailsDaoImpl stockDetailsDaoImpl;
	
	@Override
	public StatusBean saveOrUpdateStock(StockDetailsBean stockDetailsBean) {
		StatusBean statusBean = new StatusBean();
		try {
			StockDetails stockDetails = new StockDetails();
			if (stockDetailsBean != null) {
				BeanUtils.copyProperties(stockDetailsBean, stockDetails);
				Items items=new Items();
				BeanUtils.copyProperties(stockDetailsBean.getItem(), items);
				stockDetails.setItem(items);
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
	
	@Override
	public List<StockDetailsBean> getItemsWiseStockDetails(){
		return stockDetailsDaoImpl.getItemwiseStockDetails();
	}
	
	@Override
	public StockDetailsBean getStockDetailsByItemId(Long itemId) {
		List<StockDetailsBean> stockDetailsBeanList = stockDetailsDaoImpl.getItemwiseStockDetails();
		StockDetailsBean stockDetailsBeanResult = new StockDetailsBean();
		for (StockDetailsBean stockDetailsBean : stockDetailsBeanList) {
			if (stockDetailsBean.getItemId().equals(itemId)) {
				stockDetailsBeanResult = stockDetailsBean;
			}
		}
		return stockDetailsBeanResult;
	}
}
