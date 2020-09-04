package com.study.project.service.domin;

import lombok.Data;

import java.util.List;

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



    }

}
