package com.study.project.dao.entity;

import com.study.project.commom.base.BaseEntity;
import lombok.Data;

import javax.persistence.Table;

/**
 * @Description:
 * @Date: 2020/8/31  17:36
 * @Author: dongdong
 */
@Data
@Table(name = "user")
public class UserEntity extends BaseEntity<UserEntity> {


}
