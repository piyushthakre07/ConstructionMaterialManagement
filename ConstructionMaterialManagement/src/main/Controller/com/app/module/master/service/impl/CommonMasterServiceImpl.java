package com.app.module.master.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.TotalCountBean;
import com.app.module.master.dao.IContractorDao;
import com.app.module.master.dao.ISiteDao;
import com.app.module.master.dao.IUserDao;
import com.app.module.master.dao.IVendorDao;
import com.app.module.master.service.ICommonMasterService;

@Service
public class CommonMasterServiceImpl implements ICommonMasterService{

	@Autowired
	IContractorDao contractorDao;
	
	@Autowired
	IVendorDao vendorDao;

	@Autowired
	IUserDao userDao;
	
	@Autowired
	ISiteDao siteDao;
	
	@Override
	public TotalCountBean getAllTotal() {
		TotalCountBean bean = new TotalCountBean();
		bean.setContractorTtl(contractorDao.count());
		bean.setVendorTtl(vendorDao.count());
		bean.setUserTtl(userDao.count());
		bean.setSitesTtl(siteDao.count());
		return bean;
	}
	
}