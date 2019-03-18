package com.diamond.mall.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@EnableAutoConfiguration
/*@ImportResource("classpath:transaction.xml")*/
@MapperScan(basePackageClasses = {UserApp9090.class})
/**
 * 1.druid 连接池
 * 2.mybatis_plus
 * 3.mybatis 分页插件 PageHelper
 * 4.lombok
 * 
 * 未完成
 * -----------------------
 * 5.aop 事物 （注解事务配置完成，xml方式声明式事物为配置）
 * 6.统一异常信息配置（业务操作发生异常：ServiceException ,应用发生异常：ApplicationException）
 * 7.统一日志输出配置（dao、service、controller分别按年/月/日/文件大小 输出到不同日志，后期用elk管理分布式日志）
 * 8.sql 新增、修改 填充公共字段信息
 * 9.接口幂等性设计：新增（先获取时间戳，并将时间戳存入redis缓存10分钟，业务执行时，清除redis时间戳缓存，避免重复提交）
 * 				更新（同上，更新数据时同时加入时间戳为更新条件，防止多用户多服务并发修改，时间戳由mybatis提供的interceptor修改）
 * 
 * 
 * 规范：
 * 	1.javaBean继承 BaseEntity
 *  2.dao层继承MybatisPuls插件中的BaseMapper
 *  3.service层继承...
 *  4.controller层继承...
 *  5.controller返回数据均使用RespEntity进行数据封装
 *  6.前端传输model与datamodel分离
 *  
 * @author wu_hong_yan 
 * @Date   2019-03-17
 *
 */
public class UserApp9090 
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserApp9090.class, args);
    }
}
