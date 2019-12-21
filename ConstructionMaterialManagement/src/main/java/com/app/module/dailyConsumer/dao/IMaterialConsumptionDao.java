package com.app.module.dailyConsumer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.DailyMaterialConsumption;
@Repository
public interface IMaterialConsumptionDao extends JpaRepository<DailyMaterialConsumption, Long> {
	
}