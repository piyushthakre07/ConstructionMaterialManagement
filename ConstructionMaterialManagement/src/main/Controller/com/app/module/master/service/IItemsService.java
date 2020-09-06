package com.app.module.master.service;

import java.util.List;

import com.app.beans.ItemsBean;
import com.app.beans.StatusBean;

public interface IItemsService {
	public List<ItemsBean> getAllItems();

	public StatusBean saveOrUpdateItems(ItemsBean itemsBeanRequest);
	
	StatusBean deleteItems(Long itemId);

	public List<ItemsBean> getItemsByMaterialCategoryId(Long materialCategoryId);

}
