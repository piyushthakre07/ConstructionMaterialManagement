package com.app.module.stockdetails.service;

import com.app.beans.StatusBean;
import com.app.beans.StockDetailsBean;

public interface IStockService {

	StatusBean saveOrUpdateStock(StockDetailsBean stockDetailsBean);

}
