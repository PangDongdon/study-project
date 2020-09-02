package com.study.project.dao.entity;

import com.study.project.commom.base.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;

/**
 * @Description:
 * @Date: 2020/8/31  17:36
 * @Author: dongdong
 */
@Data
@Table(name = "user")
public class UserEntity extends BaseEntity<UserEntity> {


    private static final long serialVersionUID = -5050294019011844256L;
    @Length(min = 1, max = 16, message = "app编码长度必须介于 1 和 16 之间")
    private String appId; // app编码

    @Length(min = 0, max = 20, message = "用户id长度必须介于0 和 20 之间")
    private String userId; // 用户id

    @Length(min = 0, max = 32, message = "用户名长度必须介于 0 和 32 之间")
    private String userName; // 用户名

    @Length(min = 1, max = 16, message = "用户手机号长度必须介于 1 和 16 之间")
    private String userPhone; // 用户手机号

    @Length(min = 1, max = 32, message = "角色编码长度必须介于 1 和 32 之间")
    private String roleCode; // 角色编码

}
