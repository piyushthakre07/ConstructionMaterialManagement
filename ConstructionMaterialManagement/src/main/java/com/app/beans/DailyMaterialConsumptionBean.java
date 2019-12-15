package com.app.beans;

import java.util.Date;

import lombok.Data;

@Data
public class DailyMaterialConsumptionBean {

	private long materialConsumptionId;

	private Date consumptionDate;

	private String workType;

	private Integer consumptionQuantity;

	private ContractorBean contractor;

	private SitesBeans sites;

	private MaterialCategoryBean materialCategory;

	private ItemsBean item;

	private UserBean approveBy;

	private String remark;

}
