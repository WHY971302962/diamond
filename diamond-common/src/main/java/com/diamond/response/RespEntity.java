package com.diamond.response;

import lombok.Getter;
import lombok.Setter;

/**
 * restfull 返回数据信息
 * @author wu_hong_yan 
 * @Date   2019-03-17
 *
 */
@Getter
@Setter
public class RespEntity {
	
	//状态码
    private Long code;
    //状态码对应提示信息
    private String msg;
    //返回数据
    private Object data;
    //返回总资源数
    //private int total;
    
    public RespEntity(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespEntity(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public RespEntity(RespCode respCode, Object data) {
        this(respCode);
        this.data = data;
    }
  
}