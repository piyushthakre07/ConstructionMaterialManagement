package com.app.beans;

import java.util.Set;

import lombok.Data;


@Data
public class MaterialCategoryBean {

	private long materialCategoryId;

	private String materialCategoryName;

	private Set<ItemsBean> items;
}
