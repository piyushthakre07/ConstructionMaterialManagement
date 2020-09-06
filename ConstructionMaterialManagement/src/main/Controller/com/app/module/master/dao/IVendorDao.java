package com.app.module.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Vendor;
@Repository
public interface IVendorDao extends JpaRepository<Vendor, Long> {	
}
