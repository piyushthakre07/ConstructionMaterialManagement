package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.RoleBean;
import com.app.beans.StatusBean;
import com.app.beans.UserBean;
import com.app.model.Role;
import com.app.model.User;
import com.app.module.master.dao.IUserDao;
import com.app.module.master.service.IUserService;
import com.app.utility.GenericConstant;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;

	@Override
	public List<UserBean> getAllUser() {
		List<User> listUser = userDao.findAll();
		return listUser.stream().map(user -> {
			UserBean userBean = new UserBean();
			BeanUtils.copyProperties(user, userBean);
			if(user.getRole()!=null) {
				RoleBean roleBean=new RoleBean();
				BeanUtils.copyProperties(user.getRole(), roleBean);
				userBean.setRole(roleBean);
			}
			return userBean;
		}).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public StatusBean saveOrUpdateUser(UserBean userBeanRequest) {
		StatusBean statusBean = new StatusBean();
		try {
			User user = new User();
			if (userBeanRequest != null) {
				BeanUtils.copyProperties(userBeanRequest, user);
				if(userBeanRequest.getRole()!=null) {
					Role role=new Role();
					BeanUtils.copyProperties(userBeanRequest.getRole(), role);
					user.setRole(role);
				}
				userDao.save(user);
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
	public StatusBean deleteUser(Long userId) {
		StatusBean statusBean = new StatusBean();
		try {
			if (userId != null) {
				userDao.deleteById(userId);
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
