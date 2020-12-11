package com.study.project.service.domin;

import com.google.common.collect.Lists;
import com.study.project.dao.entity.UserEntity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Date: 2020/8/31  17:38
 * @Author: dongdong
 */
@Data
public class User {
    private String id;
    private String userId;
    private String appId;
    private String phone;
    private String name;
    private List<ROLE> roles;
    private List<String>  permissions;

    public  enum  ROLE{
        DRIVER("2806");

        private String id;
        private boolean isAdmin=false;

        ROLE(String id){
            this.id=id;
        }
        ROLE(String id,boolean isAdmin){
            this.id=id;
            this.isAdmin=isAdmin;
        }

        public  String getId(){
            return  this.id;
        }
        public boolean isAdmin(){
            return  this.isAdmin;
        }
        public String getName(){
            return  this.name();
        }

        public  static  ROLE  map(String roleCode){
            return Arrays.stream(ROLE.values()).filter(x->x.id.equals(roleCode)).findFirst().orElse(null);
        }
    }

    public static User fromUserEntity(UserEntity entity,List<String> permissions){
        User  user=new User();
        user.setId(String.valueOf(entity.getId()));
        user.setUserId(entity.getUserId());
        user.setAppId(entity.getAppId());
        user.setName(entity.getUserName());
        user.setPhone(entity.getUserPhone());
        List<ROLE> roles;
        if(StringUtils.isBlank(entity.getRoleCode())){
         roles=Lists.newArrayList();
        }else{
            roles=Arrays.stream(entity.getRoleCode().split(",")).map(code->ROLE.valueOf(code)).collect(Collectors.toList());
        }
        user.setRoles(roles);
        user.setPermissions(permissions);
        return user;
    }




}
