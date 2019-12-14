package com.app.module.purchase.service;

import com.app.beans.PurchaseBean;
import com.app.beans.StatusBean;

public interface IPurchaseService {
	public StatusBean purchaseItem(PurchaseBean purchaseBean);
}
