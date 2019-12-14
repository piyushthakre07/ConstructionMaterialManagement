package com.app.module.master.service;

import java.util.List;

import com.app.beans.RoleBean;
import com.app.beans.StatusBean;

public interface IRoleService {

	public List<RoleBean> getAllRole();

	public StatusBean saveOrUpdateRole(RoleBean roleBeanRequest);

	StatusBean deleteRole(Long roleId);

}
