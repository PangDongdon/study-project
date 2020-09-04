package com.study.project.commom.context;

/**
 * @Description:
 * @Date: 2020/9/4  16:52
 * @Author: dongdong
 */
public interface BizConstants {

    /**
     * 代理商小程序
     * */
    String AGENT_APPID="2805";

    /**
     * 司机小程序
     * */
    String  DRIVER_APPID="2806";

    /**
     * 代理商后台appId
     *
     * */
    String ADMIN_APPID="2807";

    /**
     * 渠道平台appId
     *
     * */
    String CHANNEL_APPID="3301";

    /**
     * 登录失败次数限制
     *
     * */
    int LOGNI_ERROR_TIME=10;

    /**
     * token 前缀
     *
     * */
     String TOKEN_PREFIX="Bearer ";

}
