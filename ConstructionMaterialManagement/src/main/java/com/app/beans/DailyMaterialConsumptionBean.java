package com.app.beans;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DailyMaterialConsumptionBean {

	private long materialConsumptionId;

	private Date consumptionDate;
	
	private String consumptionDateString;

	private String workType;

	private Integer consumptionQuantity;

	private ContractorBean contractor;

	private SitesBeans sites;

	private MaterialCategoryBean materialCategory;

	private ItemsBean item;

	private UserBean approveBy;

	private String remark;

}
