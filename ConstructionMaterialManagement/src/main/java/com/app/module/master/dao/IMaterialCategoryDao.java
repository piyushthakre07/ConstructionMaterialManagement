package com.app.module.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.MaterialCategory;
@Repository
public interface IMaterialCategoryDao extends JpaRepository<MaterialCategory, Long> {	
}
