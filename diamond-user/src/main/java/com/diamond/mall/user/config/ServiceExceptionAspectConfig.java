package com.diamond.mall.user.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import com.diamond.mall.user.service.exception.ServiceException;
 

@Aspect
@Configuration
public class ServiceExceptionAspectConfig {

	@Pointcut("execution (* com.diamond.mall.user.service.user..*(..))")
	private void pointcut(){}
	
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp)throws Throwable {
		
		try{
			pjp.proceed();
		}catch(RuntimeException re){
			re.printStackTrace();
			String msg = re.getMessage();
			//约定异常代码
			
			//为捕捉异常
			throw new ServiceException("",re.getMessage());
			//z状态码无法灵活更改提示信息
			
			//错误消息进行匹配解析
		}
		
		return null;
	}
}
