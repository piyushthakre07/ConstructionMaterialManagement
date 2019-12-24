package com.app.beans;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DailyMaterialConsumptionBean {
	
	private String contractorName;
	private String siteName;
	private String siteAddress;
	private String workType;
	private String materialCategoryName;
	private String itemName;
	private Long quantity;
	private String unitName;
	

	public DailyMaterialConsumptionBean(String contractorName, String siteName, String siteAddress, String workType,
			String materialCategoryName, String itemName, Long quantity, String unitName) {
		super();
		this.contractorName = contractorName;
		this.siteName = siteName;
		this.siteAddress = siteAddress;
		this.workType = workType;
		this.materialCategoryName = materialCategoryName;
		this.itemName = itemName;
		this.quantity = quantity;
		this.unitName = unitName;
	}
	
	public DailyMaterialConsumptionBean() {
		
	}
	
	private Long materialConsumptionId;

	private Date consumptionDate;
	
	private String consumptionDateString;

	

	private Integer consumptionQuantity;

	private ContractorBean contractor;

	private SitesBeans sites;

	private MaterialCategoryBean materialCategory;

	private ItemsBean item;

	private UserBean approveBy;

	private String remark;

}
