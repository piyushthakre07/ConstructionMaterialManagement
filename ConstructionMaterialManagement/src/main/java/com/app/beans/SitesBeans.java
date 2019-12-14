package com.app.beans;

import lombok.Data;


@Data
public class SitesBeans {

	private long siteId;
	
	private String siteName;
	
	private String siteAddress;
	
	private ContractorBean contractor;
}
