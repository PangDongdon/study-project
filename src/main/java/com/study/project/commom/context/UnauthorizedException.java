package com.study.project.commom.context;

/**
 * @Description:
 * @Date: 2020/9/3  17:29
 * @Author: dongdong
 */
public class UnauthorizedException extends  RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -7693803306863377595L;

    private Integer code;

    public UnauthorizedException(String msg) {
        super(msg);
        this.code = ResponseCode.SERVER_ERROR.getCode();
    }

    public UnauthorizedException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public UnauthorizedException(String msg, Exception e) {
        super(msg, e);
        this.code = ResponseCode.SERVER_ERROR.getCode();
    }
}
