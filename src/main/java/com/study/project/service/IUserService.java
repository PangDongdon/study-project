package com.study.project.service;

import com.study.project.service.domin.User;

/**
 * @Description:
 * @Date: 2020/8/31  17:40
 * @Author: dongdong
 */
public interface IUserService {

    User findById(String id,String appId);

    /**渠道用户获取*/
    User findChannelById(String appId,String clientId,String cnlToken,String timestamp);
}
