package com.app.module.master.service;

import java.util.List;

import com.app.beans.StatusBean;
import com.app.beans.ContractorBean;

public interface IContractorService {
	public List<ContractorBean> getAllContractor();

	public StatusBean saveOrUpdateContractor(ContractorBean contractorBeanRequest);

	StatusBean deleteContractor(Long contractorId);

}
