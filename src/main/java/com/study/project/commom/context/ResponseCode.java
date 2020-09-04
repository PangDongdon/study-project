package com.study.project.commom.context;

import com.study.project.commom.base.CommonResult;

/**
 * @Description:
 * @Date: 2020/9/3  16:24
 * @Author: dongdong
 */
public enum ResponseCode {
    OK(200,"OK"), PARM_ERROR(400,"parm error"),
    UNAUTHORIZED(401,"Request unanthorized"),
    FORBIDDEN(403,"access forbidden"), NOT_FOUND(404,"Not found"),
    TIME_OUT(408,"connect timeout"),SERVER_ERROR(500,"System error");
    private int code;
    private String msg;

    ResponseCode(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int  getCode(){
        return code;
    }
    public String  getMsg(){
        return  msg;
    }

    public CommonResult toCommonResult(){
        return  new CommonResult(code,msg);
    }

    public <DTO> CommonResult<DTO> toCommonResult(DTO dto){
        return  new CommonResult<>(code,msg,dto);
    }

    public <DTO> CommonResult<DTO> toCommonResult(DTO dto,String message){
        return new CommonResult<>(code,message,dto);
    }

    public CommonResult toCommonResult(String msg){
        return new CommonResult(code,msg);
    }

    public CommonResult toCommonResult(Integer code,String msg){
        return new CommonResult(code,msg);
    }

}
