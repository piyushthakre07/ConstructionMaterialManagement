package com.app.module.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.MaterialCategary;
@Repository
public interface IMaterialCategaryDao extends JpaRepository<MaterialCategary, Long> {
	/* List<MaterialCategary> findAll(); */
}
