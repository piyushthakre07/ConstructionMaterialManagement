package com.app.beans;

import java.util.Set;

import lombok.Data;


@Data
public class MaterialCategaryBean {

	private long materialCategaryId;

	private String materialCategaryName;

	private Set<ItemsBean> items;
}
