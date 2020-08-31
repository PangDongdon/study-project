package com.study.project.commom.base;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @Description:
 * @Date: 2020/8/31  18:30
 * @Author: dongdong
 */
@Entity
public class BaseEntity<T> implements Serializable {
    private static final long serialVersionUID = -377434516539638050L;
}
