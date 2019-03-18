package com.diamond.mall.user.controller.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.diamond.mall.user.controller.validator.annotation.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	public User(Long id, String name,String pwd,String phone, String addr) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.phone = phone;
		this.addr = addr;
	}
	private Long id;
	@NotBlank(message="名称不能为空")
	private String name;
	@JsonIgnore
	private String pwd;
	@Phone
	private String phone;
	@Min(value=8,message="最小长度为8")
	private String addr;
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name +", pwd=" + pwd +", phone=" + phone + ", addr=" + addr + "]";
	}
	
	
	
}
