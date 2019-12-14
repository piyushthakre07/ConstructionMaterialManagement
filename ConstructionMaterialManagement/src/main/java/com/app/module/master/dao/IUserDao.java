package com.app.module.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.User;
@Repository
public interface IUserDao extends JpaRepository<User, Long> {	
}
