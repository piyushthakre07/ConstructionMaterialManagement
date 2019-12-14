package com.app.module.purchase.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.PurchaseBean;
import com.app.beans.StatusBean;
import com.app.model.Items;
import com.app.model.Purchase;
import com.app.model.Vendor;
import com.app.module.purchase.dao.IPurchaseDao;
import com.app.module.purchase.service.IPurchaseService;
import com.app.utility.GenericConstant;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

	@Autowired
	IPurchaseDao purchaseDao;

	@Override
	public StatusBean purchaseItem(PurchaseBean purchaseBeanRequest) {
		StatusBean statusBean = new StatusBean();
		try {
			Purchase purchase = new Purchase();
			if (purchaseBeanRequest != null) {
				BeanUtils.copyProperties(purchaseBeanRequest, purchase);
				Vendor vendor = new Vendor();
				vendor.setVendorId(purchaseBeanRequest.getVendor().getVendorId());
				purchase.setVendor(vendor);
				Items item=new  Items();
				item.setItemId(purchaseBeanRequest.getItem().getItemId());
				purchase.setItem(item);
				purchaseDao.save(purchase);
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
