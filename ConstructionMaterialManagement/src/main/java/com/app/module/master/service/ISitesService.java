package com.app.module.master.service;

import java.util.List;

import com.app.beans.SitesBeans;
import com.app.beans.StatusBean;

public interface ISitesService {
	public List<SitesBeans> getAllSite();

	public StatusBean saveOrUpdateSite(SitesBeans siteBeanRequest);
	
	StatusBean deleteSite(Long siteId);

	List<SitesBeans> getSitesByConstractorId(long contractorId);

}
