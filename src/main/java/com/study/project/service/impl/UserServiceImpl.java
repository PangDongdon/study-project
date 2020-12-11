package com.study.project.service.impl;

import com.google.common.collect.Lists;
import com.study.project.dao.entity.UserEntity;
import com.study.project.service.IUserService;
import com.study.project.service.domin.User;
import com.study.project.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.Collections;
import java.util.List;

import static com.study.project.commom.context.BizConstants.ADMIN_APPID;

/**
 * @Description:
 * @Date: 2020/9/3  14:41
 * @Author: dongdong
 */
@Service
@Profile({"dev","sit"})
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserService userService;

    @Override
    public User findById(String id, String appId) {
        UserEntity user=new UserEntity();
        user.setId(Long.parseLong(id));
        user.setAppId(appId);
        user.setDeleteFlag(0);
        UserEntity userEntity=userService.get(user);
        if(userEntity==null){
            return  null;
        }
        List<String> permissions= Lists.newArrayList();
        if(ADMIN_APPID.equals(userEntity.getUserId())){
            permissions=this.getAdminPermissions(userEntity.getUserId());
        }
        return null;
    }

    private List<String> getAdminPermissions(String userId) {
        //返回登录人的权限集合
        return null;
    }

    @Override
    public User findChannelById(String appId, String clientId, String cnlToken, String timestamp) {
        return null;
    }
}
