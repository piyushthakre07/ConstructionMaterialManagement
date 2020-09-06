package com.app.module.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.UnitConverter;

@Repository
public interface IUnitConverterDao extends JpaRepository<UnitConverter, Long> {

}
