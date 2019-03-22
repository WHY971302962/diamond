package com.diamond.mall.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.diamond.mall.user.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{

	
	/*@Insert("insert into user(email,username,password,phone,nike,headimg,distributor_id,user_no) "
			+"values (#{email},#{username},#{password},#{phone},#{nike},#{headimg},#{distributorId},#{userNo})")*/
	@Insert("insert into user(email,username,nike) "
			+"values (#{email},#{username},#{nike})")
	@Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
	public Integer create(User user);
	
	@Select("select * from user")
	public List<User> query();
	
	@Select("select count(1) from user")
	public int queryCount();
	
}
