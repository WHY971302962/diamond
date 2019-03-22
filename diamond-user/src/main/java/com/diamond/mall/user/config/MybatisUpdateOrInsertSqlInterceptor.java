package com.diamond.mall.user.config;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import com.diamond.mall.user.entity.BaseEntity;

import lombok.extern.log4j.Log4j2;

/*@Intercepts(@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }))
*/
@Intercepts({@Signature(type = Executor.class,method = "update", args = {MappedStatement.class, Object.class})})  
@Log4j2
public class MybatisUpdateOrInsertSqlInterceptor implements Interceptor {

	public MybatisUpdateOrInsertSqlInterceptor(){
		
	}
	
	@Override
	public Object intercept(Invocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		Object[] args = arg0.getArgs();
		MappedStatement ms = (MappedStatement)args[0];
		BaseEntity be = (BaseEntity)args[1];
		String sql = ms.getBoundSql(be).getSql();
		log.info(sql);
		return arg0.proceed();
	}

	@Override
	public Object plugin(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		
	}

}
