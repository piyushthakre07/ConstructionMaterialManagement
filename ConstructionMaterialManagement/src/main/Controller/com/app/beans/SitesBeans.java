package com.app.beans;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class SitesBeans {

	private long siteId;
	
	private String siteName;
	
	private String siteAddress;
	
	private ContractorBean contractor;
}
