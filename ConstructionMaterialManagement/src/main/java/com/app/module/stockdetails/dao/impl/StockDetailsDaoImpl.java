package com.app.module.stockdetails.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.beans.StockDetailsBean;
import com.app.model.StockDetails;

@Repository
public class StockDetailsDaoImpl {

	@Autowired
	EntityManager em;
	
	
	public List<StockDetailsBean> getItemwiseStockDetails() {
		List<StockDetails> stockList = new ArrayList<StockDetails>();
		List<StockDetailsBean> list = em.createQuery(
				"select new com.app.beans.StockDetailsBean(SUM(stock.quantity) as quantity,stock.item.itemName,stock.item.itemId as itemId,stock.item.unit.unitName)  from StockDetails stock  GROUP BY  stock.item.itemId")
				.getResultList();

		return list;
	}
	
}
