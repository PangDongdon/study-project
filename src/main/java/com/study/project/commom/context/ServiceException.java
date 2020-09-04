package com.study.project.commom.context;

/**
 * @Description:
 * @Date: 2020/9/3  17:16
 * @Author: dongdong
 */
public class ServiceException extends  RuntimeException{

    private static final long serialVersionUID = -3186008758467936510L;
    private Integer code;

    public ServiceException(String msg) {
        super(msg);
        this.code = ResponseCode.SERVER_ERROR.getCode();
    }

    public ServiceException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public ServiceException(String msg, Exception e) {
        super(msg, e);
        this.code = ResponseCode.SERVER_ERROR.getCode();
    }

}
