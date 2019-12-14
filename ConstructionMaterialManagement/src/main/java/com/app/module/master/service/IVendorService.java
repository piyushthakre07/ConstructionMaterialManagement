package com.app.module.master.service;

import java.util.List;

import com.app.beans.StatusBean;
import com.app.beans.VendorBean;

public interface IVendorService {
	public List<VendorBean> getAllVendor();

	public StatusBean saveOrUpdateVendor(VendorBean vendorBeanRequest);
	
	StatusBean deleteVendor(Long vendorId);

}
