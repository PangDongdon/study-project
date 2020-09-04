package com.study.project.commom.context;

import org.aspectj.weaver.ast.Not;

/**
 * @Description:
 * @Date: 2020/9/3  17:16
 * @Author: dongdong
 */
public class NotFoundException  extends  RuntimeException{
    private static final long serialVersionUID = 1973416140482148029L;

    private Integer  code;

    public NotFoundException(String msg){
        super(msg);
        this.code=ResponseCode.SERVER_ERROR.getCode();
    }

    public NotFoundException(String msg,Integer code){
        super(msg);
        this.code=code;
    }
    public NotFoundException(String msg,Exception e){
        super(msg,e);
        this.code=ResponseCode.SERVER_ERROR.getCode();
    }
}
