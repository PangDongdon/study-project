package com.study.project.dao;

import com.study.project.commom.base.BaseMapper;
import com.study.project.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Date: 2020/8/31  17:34
 * @Author: dongdong
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
