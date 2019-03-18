package com.diamond.mall.user.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.diamond.exception.ServiceException;
import com.diamond.mall.user.entity.User;
import com.diamond.mall.user.mapper.UserMapper;
import com.diamond.mall.user.service.BaseServiceImpl;

@Service
@Transactional(readOnly=true)
public class UserService extends BaseServiceImpl<User>{

	@Autowired
	public UserMapper userMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={ServiceException.class})
	public Integer create(User user) {
		return userMapper.create(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={ServiceException.class})
	public Integer insert(User user) {
		return userMapper.insert(user);
	}
	
	
}
