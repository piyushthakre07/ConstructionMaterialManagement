package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.app.beans.StatusBean;
import com.app.beans.ContractorBean;
import com.app.model.Contractor;
import com.app.module.master.dao.IContractorDao;
import com.app.module.master.service.IContractorService;
import com.app.utility.GenericConstant;

@Service
public class ContractorServiceImpl implements IContractorService {

	@Autowired
	IContractorDao contractorDao;

	@Override
	public List<ContractorBean> getAllContractor() {
		List<Contractor> listContractor = contractorDao.findAll();
		return listContractor.stream().map(contractor -> {
			ContractorBean contractorBean = new ContractorBean();
			BeanUtils.copyProperties(contractor, contractorBean);
			return contractorBean;
		}).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public StatusBean saveOrUpdateContractor(ContractorBean contractorBeanRequest) {
		StatusBean statusBean = new StatusBean();
		try {
			Contractor contractor = new Contractor();
			if (contractorBeanRequest != null) {
				BeanUtils.copyProperties(contractorBeanRequest, contractor);
				contractorDao.save(contractor);
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
	public StatusBean deleteContractor(Long contractorId) {
		StatusBean statusBean = new StatusBean();
		try {
			if (contractorId != null) {
				contractorDao.deleteById(contractorId);
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
