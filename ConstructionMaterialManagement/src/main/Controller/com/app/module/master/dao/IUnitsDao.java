package com.app.module.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Units;
@Repository
public interface IUnitsDao extends JpaRepository<Units, Long> {	
}
