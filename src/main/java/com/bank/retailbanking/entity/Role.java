package com.bank.retailbanking.entity;

import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role {
	@Id
	private Integer roleId;
	private String roleName;

}
