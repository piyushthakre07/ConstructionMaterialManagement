package com.app.module.master.service;

import java.util.List;

import com.app.beans.StatusBean;
import com.app.beans.UserBean;

public interface IUserService {
	public List<UserBean> getAllUser();

	public StatusBean saveOrUpdateUser(UserBean userBeanRequest);

	StatusBean deleteUser(Long userId);

}
