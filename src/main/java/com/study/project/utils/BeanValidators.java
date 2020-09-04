package com.study.project.utils;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Description:
 * @Date: 2020/9/3  11:51
 * @Author: dongdong
 */
public class BeanValidators {

    /**调用JSR303的validate方法，验证失败时抛出 Cons*/
    public  static void validateWithException(Validator validator, Object object, Class<?>...groups) throws ConstraintViolationException {
        Set constraintViolations=validator.validate(object,groups);
        if(!constraintViolations.isEmpty()){
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
