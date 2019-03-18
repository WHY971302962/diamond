package com.diamond.mall.user.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * 字段值自动写入数据库
 * @author wu_hong_yan 
 * @Date   2019-03-16
 *
 */
public class BaseEntity {
	
	@TableField(value="last_update_name_id",fill=FieldFill.INSERT_UPDATE)
	private Long lastUpdateNameId;
	//最后更改时间戳，可作为乐观锁字段使用
	@TableField(value="last_update_time",fill=FieldFill.INSERT_UPDATE)
	private Date lastUpdateTime;
	
}
