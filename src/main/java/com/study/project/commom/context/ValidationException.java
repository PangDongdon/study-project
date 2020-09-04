package com.study.project.commom.context;

/**
 * @Description:
 * @Date: 2020/9/3  17:17
 * @Author: dongdong
 */
public class ValidationException extends  RuntimeException {


    /**
     *
     */
    private static final long serialVersionUID = -6642766494295400046L;

    private Integer code;

    public ValidationException(String msg) {
        super(msg);
        this.code = ResponseCode.PARM_ERROR.getCode();
    }

    public ValidationException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public ValidationException(String msg, Exception e) {
        super(msg, e);
        this.code = ResponseCode.PARM_ERROR.getCode();
    }
}
