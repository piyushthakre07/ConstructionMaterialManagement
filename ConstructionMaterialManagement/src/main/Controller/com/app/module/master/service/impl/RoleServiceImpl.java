package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.StatusBean;
import com.app.beans.RoleBean;
import com.app.model.Role;
import com.app.module.master.dao.IRoleDao;
import com.app.module.master.service.IRoleService;
import com.app.utility.GenericConstant;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	IRoleDao roleDao;

	@Override
	public List<RoleBean> getAllRole() {
		List<Role> listRole = roleDao.findAll();
		return listRole.stream().map(role -> {
			RoleBean roleBean = new RoleBean();
			BeanUtils.copyProperties(role, roleBean);
			return roleBean;
		}).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public StatusBean saveOrUpdateRole(RoleBean roleBeanRequest) {
		StatusBean statusBean = new StatusBean();
		try {
			Role role = new Role();
			if (roleBeanRequest != null) {
				BeanUtils.copyProperties(roleBeanRequest, role);
				roleDao.save(role);
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
	public StatusBean deleteRole(Long roleId) {
		StatusBean statusBean = new StatusBean();
		try {
			if (roleId != null) {
				roleDao.deleteById(roleId);
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
