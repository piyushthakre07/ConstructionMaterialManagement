package com.app.beans;

import lombok.Data;


@Data
public class UserBean {

	private long userId;

	private String fullName;

	private String userName;

	private String password;

	private RoleBean role;

}
