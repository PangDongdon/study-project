package com.study.project.commom.base;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Date: 2020/9/3  11:15
 * @Author: dongdong
 */
@Data
public class Page<T> {
    private int page=1;// 当前页码
    private int pageSize=30;//页面大小，设置为“-1”,表示不进行分页（分页无效）

    private List<T> list=new ArrayList<>();

    public Page(){
        this.pageSize=-1;
    }
    /**
     * 构造方法
     *
     * */

    public Page(HttpServletRequest request, HttpServletResponse response){
        this(request,response,-2);
    }

    /**
     * 构造方法
     * 默认分页大小，如果传递 -1，则不分页，返回所有数据
     *
     * */

    public Page(HttpServletRequest  request,HttpServletResponse response,int defaultPageSize){
        //设置页码参数（传递repage 参数，来记住页码）
        String no=request.getParameter("page");
        if(StringUtils.isNumeric(no)){
            this.setPage(Integer.parseInt(no));
        }
        //设置页面大小参数（传递repage参数，来记住页码大小）
        String size=request.getParameter("pageSize");
        if(StringUtils.isNumeric(size)){
            this.setPageSize(Integer.parseInt(size));
        }else if(defaultPageSize !=-2){
            this.pageSize=defaultPageSize;
        }
    }

    /**
     * 构造方法
     *
     * */
    public Page(int page,int pageSize){
        this(page,page,new ArrayList<T>());
    }

    public Page(int page,int pageSize,List<T> list){
        this.setPage(page);
        this.pageSize=pageSize;
        this.list=list;
    }
}
