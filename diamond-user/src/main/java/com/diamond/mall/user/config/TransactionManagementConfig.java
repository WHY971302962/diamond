package com.diamond.mall.user.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author wu_hong_yan
 * @Date 2019-03-17
 *
 * 开启事物
 * 	1. 
 * 		1)开启spring事物管理  @EnableTransactionManagement
 * 		2)创建事物管理器 DataSourceTransactionManager
 * 		3)使用 @Transactional 进行事物控制，可以指定不同事物管理器
 *  2.
 */
@EnableTransactionManagement
@Configuration
public class TransactionManagementConfig {

	@Bean
	public PlatformTransactionManager platformTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
