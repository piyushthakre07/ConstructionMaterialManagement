package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.StatusBean;
import com.app.beans.VendorBean;
import com.app.model.Vendor;
import com.app.module.master.dao.IVendorDao;
import com.app.module.master.service.IVendorService;
import com.app.utility.GenericConstant;

@Service
public class VendorServiceImpl implements IVendorService {

	@Autowired
	IVendorDao vendorDao;

	@Override
	public List<VendorBean> getAllVendor() {
		List<Vendor> listVendor = vendorDao.findAll();
		return listVendor.stream().map(vendor -> {
			VendorBean vendorBean = new VendorBean();
			BeanUtils.copyProperties(vendor, vendorBean);
			return vendorBean;
		}).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public StatusBean saveOrUpdateVendor(VendorBean vendorBeanRequest) {
		StatusBean statusBean = new StatusBean();
		try {
			Vendor vendor = new Vendor();
			if (vendorBeanRequest != null) {
				BeanUtils.copyProperties(vendorBeanRequest, vendor);
				vendorDao.save(vendor);
				statusBean.setStatus(true);
				statusBean.setMessage(GenericConstant.SUCCESS);
			} else {
				statusBean.setStatus(false);
				statusBean.setMessage(GenericConstant.FAIL);
			}
		} catch (Exception e) {
			statusBean.setStatus(false);
			statusBean.setMessage(GenericConstant.SERVERERROR);
		}
		return statusBean;

	}

	@Override
	public StatusBean deleteVendor(Long vendorId) {
		StatusBean statusBean = new StatusBean();
		try {
			if (vendorId != null) {
				vendorDao.deleteById(vendorId);
				statusBean.setStatus(true);
				statusBean.setMessage(GenericConstant.SUCCESS);
			} else {
				statusBean.setStatus(false);
				statusBean.setMessage(GenericConstant.FAIL);
			}
		} catch (Exception e) {
			statusBean.setStatus(false);
			statusBean.setMessage(GenericConstant.SERVERERROR);
		}
		return statusBean;

	}

}
