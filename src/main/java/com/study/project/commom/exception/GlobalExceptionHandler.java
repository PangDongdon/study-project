package com.study.project.commom.exception;

import com.study.project.commom.base.CommonResult;
import com.study.project.commom.context.NotFoundException;
import com.study.project.commom.context.ResponseCode;
import com.study.project.commom.context.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;

/**
 * @Description: 统一异常处理
 * @Date: 2020/9/3  16:14
 * @Author: dongdong
 */
@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult handleExceptionRequest(Exception e){
        return ResponseCode.SERVER_ERROR.toCommonResult(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResult  handleExceptionRequest(MethodArgumentNotValidException e){
        String msg="参数错误";
        for(ObjectError o:e.getBindingResult().getAllErrors()){
            msg=o.getDefaultMessage();
            log.info(msg);
            break;
        }
        return  ResponseCode.PARM_ERROR.toCommonResult(msg);
    }

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseBody
    public CommonResult handleExceptionRequest(InvalidParameterException e){
        return  ResponseCode.PARM_ERROR.toCommonResult(e.getMessage());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public CommonResult handleExceptionRequest(IllegalArgumentException e) {
        return ResponseCode.PARM_ERROR.toCommonResult(e.getMessage());
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public CommonResult handleExceptionRequest(NotFoundException e) {
        return ResponseCode.NOT_FOUND.toCommonResult(e.getMessage());
    }

    @ExceptionHandler({UnauthorizedException.class, AuthenticationException.class})
    @ResponseBody
    public CommonResult handleOauthExceptionRequest(Exception e) {
        return ResponseCode.UNAUTHORIZED.toCommonResult(e.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseBody
    public CommonResult handleForbiddenExceptionRequest(AccessDeniedException e) {
        return ResponseCode.FORBIDDEN.toCommonResult(e.getMessage());
    }

}
