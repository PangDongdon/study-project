package com.study.project.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.parameters.P;

/**
 * @Description:
 * @Date: 2020/9/4  17:48
 * @Author: dongdong
 */
public class DigitalTools {



    /**
     * 判断数据小数位数
     *
     * */

    public static boolean  checkDecimalPlaces(String str,int num){
        if(isNumeric(str)){
            return false;
        }
        if(str.lastIndexOf(".")<0){
            return false;
        }
        int placeSize=str.length()-str.lastIndexOf(".")-1;
        if(placeSize>num){
            return  false;
        }
        return  true;
    }

    /**
     * 判断小数字符串是否是数字
     *
     * */

    public  static boolean isNumeric(String str){
        if(StringUtils.isNotBlank(str)){
            return false;
        }
        String reg="^-?[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }


}
