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
 * 10.swagger2
 * 11.generator 自动生成实体类
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
/**
 * 1.项目结构(目录结构风格、命名、专用名称的使用一定要统一)
 * 		1.1.1 先分类，再分业务：直筒的三层架构纯数据表驱动的代码
 * 			controller
 * 				user
 * 				order
 * 			jobs
 * 				user
 * 				order
 * 			user
 * 				service
 * 				mapper
 * 			user
 * 				sesrvice
 * 				mapper
 * 
 * 			controller对外api; user.service业务复杂度； user.mapper项目表结构
 * 
 * 
 * 		1.1.2 先分业务，再分类：数据流量比较复杂
 * 			core
 * 				storage
 * 				service
 * 			dispatcher
 * 				engine
 * 				context
 *			callback
 *				gateway
 *				hander
 *			notification
 *				sms
 *				push
 *			core:对内； dispatcher:分发； callbak:数据回调； notification:通知外部数据流；数据流向复杂的项目，应该首先关注数据流向
 *			core,dispatcher,ntofication是没有controller的 
 * 			
 * 		1.2
 * 			名称内部统一（POJO）：面向rpc、面向ui、面向服务、面向数据库
 * 			RPC：LogingRequest/LogingResponse
 * 			U I:QueryOrderParam/QueryOrderDTO
 * 			Service:OrderEntity
 * 			Mapper:OrderModel
 *
 * 2.统一的框架(项目开展之前明确相关场景的框架技术并确定框架版本，以及搭建好项目脚手架)
 * 		定时任务：spring scheduler/quartz只选其一
 * 
 * 3.统一的API
 * 		
 * 4.统一的源码工作模式
 * 
 * 5.容错处理
 * 
 * 6.一致性处理
 * 
 * 7.异常处理
 * 
 * 8.表设计
 * 
 * 9.设计模式
 * 
 * 
 * 
 * 
 * 
 * 
 * 业务代码学不到技术：
 * 	虽然只是写业务代码，如果要写的足够好，
 * 	必须要了解设计模式、理解各种弹力设计、理解事务、熟悉框架、了解中间件原理，要实现健壮的业务代码，其实很难，要考虑的东西太多了，
 * 	如果说写框架我们需要考虑不同的使用方和使用环境，这很难，写业务代码我们要考虑到千奇百怪的使用行为，要考虑到层次不齐的对接方，这不比写框架简单。
 * 
 * 
 * 		
 */
