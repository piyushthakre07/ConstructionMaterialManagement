package com.app.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "roles_master")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	private long roleId;

	@Column(name = "role_name", length = 50)
	private String roleName;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
	private Set<User> user;
	
	@Column(name = "created_by")
	private String createdBy;

	@CreationTimestamp
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@UpdateTimestamp
	@Column(name = "modify_date")
	private Date modifyDate;

}
