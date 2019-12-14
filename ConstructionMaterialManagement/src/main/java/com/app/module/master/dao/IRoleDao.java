package com.app.module.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Role;
@Repository
public interface IRoleDao extends JpaRepository<Role, Long> {	
}
