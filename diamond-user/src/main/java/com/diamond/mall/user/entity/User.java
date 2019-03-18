package com.diamond.mall.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User extends BaseEntity{
	
	@TableId(type=IdType.AUTO)
	private Integer id;
	private String email;
	private String username;
	private String password;
	private String phone;
	private String nike;
	private String headimg;
	private Integer distributorId;
	private String userNo;
	
}
