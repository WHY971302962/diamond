package com.diamond.mall.user.controller;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamond.mall.user.controller.dto.UserDTO;
import com.diamond.mall.user.entity.User;
import com.diamond.mall.user.mapper.UserMapper;
import com.diamond.mall.user.service.user.UserServiceImpl;
import com.diamond.response.RespCode;
import com.diamond.response.RespEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * RESTful 接口设计规范
 * 
 * API 建议设计为无状态，支持幂等性
 * 
 * 
 */

/**
 * 
 * 
 *
 *-------------------------------------------------------------------
 *  GET    | http://www.shoppingmall.com/api/user 	   | 获取列表
 *-------------------------------------------------------------------
 *  POST   | http://www.shoppingmall.com/api/user 	   | 创建用户
 *-------------------------------------------------------------------
 *  PUT    | http://www.shoppingmall.com/api/user/{id} | 修改用户信息
 *-------------------------------------------------------------------
 *  DELETE | http://www.shoppingmall.com/api/user/{id} | 删除用户信息
 *-------------------------------------------------------------------
 *覆盖http方法 一些代理只支持POST 和 GET方法， 为了使用这些有限方法支持RESTful API，需要一种办法覆盖http原来的方法。
 * 使用订制的HTTP头 X-HTTP-Method-Override 来覆盖POST 方法.
 *
 *版本：
 *	http://www.shoppingmall.com/api/v1/user 
 *过滤：
 *	http://www.shoppingmall.com/api/user?age>10
 *排序：
 *	http://www.shoppingmall.com/api/user?-age,+level
 *指定返回字段：（降低网路流量）
 *	http://www.shoppingmall.com/api/user?fields=name,age,sex
 *分页：
 *	http://www.shoppingmall.com/api/user?offset=0&limit=20
 **/

/**
 * 返回结果：
 * 
 */


/**
 * 
 * @author wu_hong_yan 2019-03-15
 *
 */
@Log4j2
@Controller
public class UserController {

	/**
	 * @JsonIgnore 在输出json时忽略注解字段，但在请求时也会将该字段忽略掉，并抛出InvalidDefinitionException
	 * 所以采用抽离请求参数model自定义一套接收参数的model，与数据模型model分离
	 * 
	 * @ResponseBody 默认采用json传输数据，如果当前方法需要支持xml，则需在pom文件中添加 com.fasterxml.jackson.dataformat:jackson-dataformat-xml
	 * 请求头由JSON（Content-Type:application/json）改为 XML（Accept:text/xml | Content-Type:text/xml）即可
	 * 
	 * 
	 * 
	 * 
	 */
	@Autowired
	public UserMapper userMapper;
	
	@Autowired
	public UserServiceImpl userService;
	
	@GetMapping("/user")
	@ResponseBody
	public RespEntity getUsers(@RequestParam("offset") Integer offset,@RequestParam("limit")Integer limit) {
		log.info("offset:"+offset+"\t limit:"+limit);
		PageHelper.startPage(offset, limit);
		List<com.diamond.mall.user.entity.User> list = userMapper.query();
		int total = userMapper.queryCount();
		PageInfo<com.diamond.mall.user.entity.User> info = new PageInfo<com.diamond.mall.user.entity.User>();
		info.setTotal(total);
		info.setList(list);
		return new RespEntity(RespCode.SUCCESS,info);
	}
	
	
	@GetMapping("/user/{id}")
	@ResponseBody
	public RespEntity getUser(@PathVariable("id")Long id) {
		User user = userService.findById(id);
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(user, dto);
		return new RespEntity(RespCode.SUCCESS,dto);
	}
	
	@DeleteMapping("/user/{id}")
	@ResponseBody
	public RespEntity deleteUser(@PathVariable("id")Long id) {
		log.info(UserController.class.getName());
		return new RespEntity(RespCode.DELETE);
	}
	
	@PostMapping("/user")
	@ResponseBody
	public RespEntity createUser(@RequestBody  com.diamond.mall.user.entity.User user) {
		//userService.insert(user);//.create(user);
		userService.create(user);
		//userMapper.create(user);
		/*try {
			
			if(user.getAddr()==null)
				throw new RuntimeException();
		}catch(Exception e) {
			return new RespEntity(RespCode.FAILED);
		}*/
		return new RespEntity(RespCode.CREATE,user);
	}
	
	@PutMapping("/user")
	@ResponseBody
	public RespEntity updateUser(@RequestBody @Valid UserDTO user) {
		checkNotNull(user.getId(),"数据主键不能为空");
		
		return new RespEntity(RespCode.UPDATE,user);
	}
	
	
	
	
	

	@PostMapping("/responseentity")
	@ResponseBody
	public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO user) {
		log.info(user.toString());
		//return new RespEntity(RespCode.WARN,user);
		ResponseEntity.status(503);
		return ResponseEntity.ok(user);
	}
	
}
