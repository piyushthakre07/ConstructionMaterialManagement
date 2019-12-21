package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.ContractorBean;
import com.app.beans.SitesBeans;
import com.app.beans.StatusBean;
import com.app.model.Contractor;
import com.app.model.Sites;
import com.app.module.master.dao.ISiteDao;
import com.app.module.master.service.ISitesService;
import com.app.utility.GenericConstant;

@Service
public class SiteServiceImpl implements ISitesService {

	@Autowired
	ISiteDao siteDao;

	@Override
	public List<SitesBeans> getAllSite() {
		List<Sites> listSites = siteDao.findAll();
		return listSites.stream().map(site -> {
			SitesBeans siteBean = new SitesBeans();
			BeanUtils.copyProperties(site, siteBean);
			if (site.getContractor() != null) {
				ContractorBean contractorBean = new ContractorBean();
				BeanUtils.copyProperties(site.getContractor(), contractorBean);
				siteBean.setContractor(contractorBean);
			}
			return siteBean;
		}).collect(Collectors.toCollection(ArrayList::new));
	}
	
	@Override
	public List<SitesBeans> getSitesByConstractorId(long contractorId) {
		List<Sites> listSites = siteDao.findAll();
		return listSites.stream().map(site -> {
			SitesBeans siteBean = new SitesBeans();
			BeanUtils.copyProperties(site, siteBean);
			if (site.getContractor() != null) {
				ContractorBean contractorBean = new ContractorBean();
				BeanUtils.copyProperties(site.getContractor(), contractorBean);
				siteBean.setContractor(contractorBean);
			}
			return siteBean;
		}).filter(site->site.getContractor().getContractorId()==contractorId).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public StatusBean saveOrUpdateSite(SitesBeans siteBeanRequest) {
		StatusBean statusBean = new StatusBean();
		try {
			Sites site = new Sites();
			if (siteBeanRequest != null) {
				BeanUtils.copyProperties(siteBeanRequest, site);
				if (siteBeanRequest.getContractor() != null) {
					Contractor contractor = new Contractor();
					contractor.setContractorId(siteBeanRequest.getContractor().getContractorId());
					site.setContractor(contractor);
				}
				siteDao.save(site);
				statusBean.setStatus(true);
				statusBean.setMessage(GenericConstant.SUCCESS);
			} else {
				statusBean.setStatus(false);
				statusBean.setMessage(GenericConstant.FAIL);
			}
		} catch (Exception e) {
			statusBean.setStatus(false);
			statusBean.setMessage(GenericConstant.SERVERERROR);
		}
		return statusBean;

	}

	@Override
	public StatusBean deleteSite(Long siteId) {
		StatusBean statusBean = new StatusBean();
		try {
			if (siteId != null) {
				siteDao.deleteById(siteId);
				statusBean.setStatus(true);
				statusBean.setMessage(GenericConstant.SUCCESS);
			} else {
				statusBean.setStatus(false);
				statusBean.setMessage(GenericConstant.FAIL);
			}
		} catch (Exception e) {
			statusBean.setStatus(false);
			statusBean.setMessage(GenericConstant.SERVERERROR);
		}
		return statusBean;

	}

}
