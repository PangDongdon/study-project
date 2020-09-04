package com.study.project.commom.base;

import lombok.Data;

/**
 * @Description:
 * @Date: 2020/9/3  15:53
 * @Author: dongdong
 */
@Data
public class CommonHeaders {
    private String requestId;
    private String timestamp;
    private String appId;
    private String clientId;
    private String appVersion;
    private String jsCode;
}
