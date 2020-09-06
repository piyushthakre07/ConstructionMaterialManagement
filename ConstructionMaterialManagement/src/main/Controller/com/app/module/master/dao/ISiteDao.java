package com.app.module.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Sites;
@Repository
public interface ISiteDao extends JpaRepository<Sites, Long> {	
}
