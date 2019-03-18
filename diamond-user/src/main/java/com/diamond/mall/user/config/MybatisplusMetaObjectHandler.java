package com.diamond.mall.user.config;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.diamond.mall.user.entity.BaseEntity;

/**
 * mybatis_plus 填充公共字段信息系
 * 
 * @author wu_hong_yan
 * @Date 2019-03-16
 *
 */
@Component
public class MybatisplusMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		// TODO Auto-generated method stub
		Object object = metaObject.getOriginalObject();
		if (object instanceof BaseEntity) {
			this.setFieldValByName("lastUpdateNameId", 1L, metaObject);
			this.setFieldValByName("lastUpdateTime", new Timestamp(new Date().getTime()), metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		// TODO Auto-generated method stub
		Object object = metaObject.getOriginalObject();
		if (object instanceof BaseEntity) {
			Object lastUpdateNameId = this.getFieldValByName("lastUpdateNameId", metaObject);
			if(lastUpdateNameId==null)
				this.setFieldValByName("lastUpdateNameId", 1L, metaObject);
			Object lastUpdateTime = this.getFieldValByName("lastUpdateTime", metaObject);
			if(lastUpdateTime==null)
				this.setFieldValByName("lastUpdateTime", new Timestamp(new Date().getTime()), metaObject);
		}
	}

}
