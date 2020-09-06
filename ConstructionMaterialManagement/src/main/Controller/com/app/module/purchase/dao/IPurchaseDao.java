package com.app.module.purchase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Purchase;
@Repository
public interface IPurchaseDao extends JpaRepository<Purchase, Long> {
	
}
