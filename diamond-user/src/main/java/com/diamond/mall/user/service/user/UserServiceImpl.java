package com.diamond.mall.user.service.user;

import static com.google.common.base.Preconditions.checkNotNull;

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
public class UserServiceImpl extends BaseServiceImpl<User>{

	@Autowired
	public UserMapper userMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={ServiceException.class})
	public Integer create(User user) {
		String errMsg = "code:%s,errMsg:%s";
	    checkNotNull(user.getNike(),"nike 不能为空");
		return userMapper.create(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={ServiceException.class})
	public Integer insert(User user) {
		return userMapper.insert(user);
	}
	
	public User findById(Long id){
		User user = userMapper.selectById(id);
		checkNotNull(user,"没有查询到用户");
		return user;
	}
}
