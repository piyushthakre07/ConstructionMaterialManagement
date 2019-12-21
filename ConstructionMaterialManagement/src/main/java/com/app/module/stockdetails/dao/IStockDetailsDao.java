package com.app.module.stockdetails.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Items;
import com.app.model.StockDetails;

public interface IStockDetailsDao extends JpaRepository<StockDetails, Long>{

}
