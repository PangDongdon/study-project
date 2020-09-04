package com.study.project.utils;

import com.study.project.commom.context.NotFoundException;
import com.study.project.commom.context.ServiceException;
import com.study.project.commom.context.UnauthorizedException;
import org.apache.commons.lang3.StringUtils;

import org.springframework.util.CollectionUtils;

import java.security.InvalidParameterException;
import java.util.Collection;

/**
 * @Description:
 * @Date: 2020/9/3  16:10
 * @Author: dongdong
 */
public class Assert {
    public static void notEmpty(Collection coll, String message) {
        if (CollectionUtils.isEmpty(coll)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notBlank(String field, String message) {
        if (StringUtils.isBlank(field)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void exists(Object object, String message) {
        if (object == null) {
            throw new NotFoundException(message);
        }
    }

    public static void authorized(Object object, String message) {
        if (object == null) {
            throw new UnauthorizedException(message);
        }
    }

    public static void authorized(String object, String message) {
        if (StringUtils.isBlank(object)) {
            throw new UnauthorizedException(message);
        }
    }

    public static void authorized(boolean exp, String message) {
        if (!exp) {
            throw new UnauthorizedException(message);
        }
    }

    public static void validate(boolean exp, String message) {
        if (!exp) {
            throw new InvalidParameterException(message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new ServiceException(message);
        }
    }

    public static void isTrue(boolean expression, String message, Integer code) {
        if (!expression) {
            throw new ServiceException(message, code);
        }
    }

}
