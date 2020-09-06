package com.app.module.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Contractor;
@Repository
public interface IContractorDao extends JpaRepository<Contractor, Long> {	
}
