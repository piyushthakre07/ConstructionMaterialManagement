package com.app.beans;

import java.util.Set;

import lombok.Data;

@Data
public class RoleBean {

	private long roleId;

	private String roleName;

	private Set<UserBean> user;

}
