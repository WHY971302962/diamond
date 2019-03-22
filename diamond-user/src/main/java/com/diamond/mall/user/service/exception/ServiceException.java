package com.diamond.mall.user.service.exception;

/**
 * service 层抛ServiceException异常或其子类异常
 * service 层配置aop拦截所有exception封装为 ServiceException
 * @author Administrator
 *
 */
public class ServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 

	protected String errorCode;

	protected Object data;

	public ServiceException(String errorCode, String message, Object data, Throwable e) {

		super(message, e);

		this.errorCode = errorCode;

		this.data = data;

	}

	public ServiceException(String errorCode, String message, Object data) {

		this(errorCode, message, data, null);

	}

	public ServiceException(String errorCode, String message) {

		this(errorCode, message, null, null);

	}

	public ServiceException(String message, Throwable e) {

		this(null, message, null, e);

	}

	public ServiceException() {

	}

	public ServiceException(Throwable e) {

		super(e);

	}

	public String getErrorCode() {

		return errorCode;

	}

	public void setErrorCode(String errorCode) {

		this.errorCode = errorCode;

	}

	public Object getData() {

		return data;

	}

	public void setData(Object data) {

		this.data = data;

	}
}
