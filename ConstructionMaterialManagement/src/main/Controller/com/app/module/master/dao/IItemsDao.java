package com.app.module.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Items;
@Repository
public interface IItemsDao extends JpaRepository<Items, Long> {	
}
