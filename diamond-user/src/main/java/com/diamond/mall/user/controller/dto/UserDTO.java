package com.diamond.mall.user.controller.dto;

import javax.validation.constraints.NotBlank;

import com.diamond.mall.user.controller.validator.annotation.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * PO、POJO持久化对象
 * BO业务对象
 * VO表现对象
 * DTO数据传输对象
 * 
 * @author Administrator
 *
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

	private Integer id;
	private String email;
	@NotBlank(message="名称不能为空")
	private String username;
	@JsonIgnore
	private String password;
	@Phone(message="手机号格式错误")
	private String phone;
	private String nike;
	private String headimg;
	private Integer distributorId;
	
	
	
}
