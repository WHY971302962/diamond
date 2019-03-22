package com.diamond.mall.user.controller.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diamond.exception.ApiException;
import com.diamond.mall.user.service.exception.ServiceException;
import com.diamond.response.RespEntity;

/**
 * Controller 异常统一处理
 * 
 * @author Administrator
 *
 */
@ControllerAdvice(annotations = { Controller.class })
public class ApiExceptionHandlerAdvice {

	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ResponseEntity<RespEntity> handleBindException(BindException ex, HttpServletResponse response) {
		// 校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为
		// BeanPropertyBindingResult
		FieldError fieldError = ex.getBindingResult().getFieldError();
		Long code = -10002L;
		RespEntity res = new RespEntity(code, "" + fieldError.getDefaultMessage());
		return new ResponseEntity<RespEntity>(res, HttpStatus.valueOf(response.getStatus()));
	}

	/**
	 * 参数校验异常，jsr 303注解规范
	 * 
	 * @param ex
	 * @param response
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<RespEntity> methodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletResponse response) {
		FieldError fieldError = ex.getBindingResult().getFieldError();
		Long code = -10001L;
		RespEntity re = new RespEntity(code, fieldError.getDefaultMessage());
		ResponseEntity<RespEntity> responseEntity = new ResponseEntity<>(re, HttpStatus.valueOf(response.getStatus()));
		return responseEntity;
	}

	/**
	 * Http请求/响应转换为相应的java对象
	 * 
	 * @param ex
	 * @param response
	 * @return
	 */
	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseBody
	public ResponseEntity<RespEntity> JsonProcessingException(HttpMessageConversionException ex,
			HttpServletResponse response) {
		Long code = -1000L;
		RespEntity re = new RespEntity(code, "数据格式有误");
		ResponseEntity<RespEntity> responseEntity = new ResponseEntity<>(re, HttpStatus.valueOf(response.getStatus()));
		return responseEntity;
	}

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseBody
	public ResponseEntity<RespEntity> runtimeException(RuntimeException exception, HttpServletResponse response) {

		RespEntity re = null;
		if (exception instanceof ServiceException) {
			ServiceException se = ((ServiceException) exception);
			re = new RespEntity(se.getErrorCode(), se.getMessage());
		}else 
		// api异常
		if (exception instanceof ApiException) {
			//
			ApiException ae = (ApiException) exception;
			re = new RespEntity(ae.getErrorCode(), ae.getMessage());

		} else {// 未知异常
			exception.printStackTrace();
		}

		ResponseEntity<RespEntity> responseEntity = new ResponseEntity<>(re, HttpStatus.valueOf(response.getStatus()));
		return responseEntity;
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseEntity<RespEntity> exception(Exception exception, HttpServletResponse response) {

		Long code = -15001L;
		RespEntity re = new RespEntity(code, "未知异常:" + exception.getMessage());
		ResponseEntity<RespEntity> responseEntity = new ResponseEntity<>(re, HttpStatus.valueOf(response.getStatus()));
		return responseEntity;
	}

	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public
	 * Map<String,Object> handleBindException(MethodArgumentNotValidException
	 * ex) { FieldError fieldError = ex.getBindingResult().getFieldError(); //
	 * log.info("参数校验异常:{}({})",
	 * fieldError.getDefaultMessage(),fieldError.getField()); Map<String,Object>
	 * result = new HashMap<String,Object>(); result.put("respCode", "01002");
	 * result.put("respMsg", fieldError.getDefaultMessage()); return result; }
	 * 
	 * 
	 * @ExceptionHandler(BindException.class) public Map<String,Object>
	 * handleBindException(BindException ex) { //校验 除了 requestbody 注解方式的参数校验 对应的
	 * bindingresult 为 BeanPropertyBindingResult FieldError fieldError =
	 * ex.getBindingResult().getFieldError(); // log.info("必填校验异常:{}({})",
	 * fieldError.getDefaultMessage(),fieldError.getField()); Map<String,Object>
	 * result = new HashMap<String,Object>(); result.put("respCode", "01002");
	 * result.put("respMsg", fieldError.getDefaultMessage()); return result; }
	 */

	
	/**
	 * service 异常信息：
	 * 				guava抛业务异常信息编号；
	 * 				aop拦截service层业务异常编号封装成serviceexception；记录业务操作异常
	 * 				没有异常信息编号全部计入未知异常，并记录系统异常日志
	 * 
	 * api 异常信息：
	 * 				controlleradvice统一异常格式输出处理；
	 * 					
	 * 					业务正常调用异常：
	 *	  					serviceexception
	 * 						apiexception
	 * 						runtimeexception
	 *					
	 *					未知exception：
	 *					
	 *					请求/响应转换异常：
	 *					
	 *					数据校验异常
	 *
	 */
	
	
	
}
