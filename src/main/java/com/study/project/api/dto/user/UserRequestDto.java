package com.study.project.api.dto.user;

import lombok.Data;

/**
 * @Description:
 * @Date: 2020/8/31  17:26
 * @Author: dongdong
 */
@Data
public class UserRequestDto {
    /**用户手机号*/
    private String phone;
    /**姓名*/
    private String  name;
    /**验证码*/
    private String code;

}
