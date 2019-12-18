package com.app.beans;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class UserBean {

	private long userId;

	private String fullName;

	private String userName;

	private String password;

	private RoleBean role;

}
