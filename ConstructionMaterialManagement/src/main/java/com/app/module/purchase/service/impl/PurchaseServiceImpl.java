package com.app.module.purchase.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.ContractorBean;
import com.app.beans.ItemsBean;
import com.app.beans.PurchaseBean;
import com.app.beans.StatusBean;
import com.app.beans.StockDetailsBean;
import com.app.beans.UnitsBean;
import com.app.beans.VendorBean;
import com.app.model.Contractor;
import com.app.model.Items;
import com.app.model.Purchase;
import com.app.model.Vendor;
import com.app.module.purchase.dao.IPurchaseDao;
import com.app.module.purchase.service.IPurchaseService;
import com.app.module.stockdetails.service.IStockService;
import com.app.utility.GenericConstant;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

	@Autowired
	IPurchaseDao purchaseDao;
	
	@Autowired
	IStockService stockService;

	@Override
	public List<PurchaseBean> getAllPurchase() {
		List<Purchase> listPurchase = purchaseDao.findAll();
		return listPurchase.stream().map(purchase -> {
			PurchaseBean purchaseBean = new PurchaseBean();
			BeanUtils.copyProperties(purchase, purchaseBean);
			if (purchase.getItem() != null) {
				ItemsBean itemsBean = new ItemsBean();
				UnitsBean unitsBean = new UnitsBean();
				BeanUtils.copyProperties(purchase.getItem(), itemsBean);
				if (purchase.getItem().getUnit() != null)
					unitsBean.setUnitName(purchase.getItem().getUnit().getUnitName());
				itemsBean.setUnit(unitsBean);
				purchaseBean.setItem(itemsBean);
			}
			if (purchase.getVendor() != null) {
				VendorBean vendorBean = new VendorBean();
				BeanUtils.copyProperties(purchase.getVendor(), vendorBean);
				purchaseBean.setVendor(vendorBean);
			}
			return purchaseBean;
		}).collect(Collectors.toCollection(ArrayList::new));
	}
	
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
				StockDetailsBean stockDetailsBean=new StockDetailsBean();
				stockDetailsBean.setCreditOrDebit(GenericConstant.CREDIT);
				stockDetailsBean.setItem(purchaseBeanRequest.getItem());
				stockDetailsBean.setQuantity(purchaseBeanRequest.getQuantity());
				stockDetailsBean.setTransactionType(GenericConstant.PURCHASE_TRANSACTION_TYPE);
				stockService.saveOrUpdateStock(stockDetailsBean);
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
