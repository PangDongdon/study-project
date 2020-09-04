package com.study.project.commom.base;

/**
 * @Description:
 * @Date: 2020/9/3  16:17
 * @Author: dongdong
 */
public class CommonResult<DTO> {
    private int err;
    private String msg;
    private DTO res;


    public CommonResult(){

    }
    public CommonResult(int code,String msg){
         this(code,msg,null);
    }

    public CommonResult(int code,String msg,DTO data){
        this.err=code;
        this.msg=msg;
        this.res=data;
    }

}
