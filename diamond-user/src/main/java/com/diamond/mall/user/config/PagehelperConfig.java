package com.diamond.mall.user.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

/**
 * mybatis分页插件pageHelper
 * @author wu_hong_yan 
 * @Date   2019-03-16
 *
 */
@Configuration
public class PagehelperConfig {

	//配置mybatis的分页插件pageHelper
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }
    
/*    @Bean
    public MybatisUpdateOrInsertSqlInterceptor mybatisUpdateOrInsertSqlInterceptor(){
    	return new MybatisUpdateOrInsertSqlInterceptor();
    }*/
/*    @Bean
    public MybatisplusMetaObjectHandler mybatisplusMetaObjectHandler() {
    	MybatisplusMetaObjectHandler handler = new MybatisplusMetaObjectHandler();
    	return handler;
    }*/
}
