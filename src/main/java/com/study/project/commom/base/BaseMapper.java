package com.study.project.commom.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description:
 * @Date: 2020/9/2  18:35
 * @Author: dongdong
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
