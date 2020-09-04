package com.study.project.commom.base;


import com.github.pagehelper.PageHelper;
import com.study.project.utils.BeanValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Date: 2020/9/2  18:35
 * @Author: dongdong
 */
public class BaseService<D extends BaseMapper<T>,T extends  BaseEntity<T>> {
    /**
     * 持久层
     * */

    @Autowired
    protected  D dao;

    /**
     * 验证Bean实例对象
     *
     * */
    protected Validator validator;

    /**
     * 获取单条数据
     *
     * */
    public T get(T entity){
        return  dao.selectOne(entity);
    }

    /**
     * 查询列表数据
     *
     * */
    public List<T> findAllList(){
        return  dao.selectAll();
    }

    /**
     * 查询列表数据
     * */
    public List<T> findList(T entity){
        return  dao.select(entity);
    }

    /**
     * 查询列表数据多功能
     *
     * */

    public List<T>  findListByExample(Example example){
        return dao.selectByExample(example);
    }

    /**
     * 获取记录条数
     * */

    public int  getCount(T entity){
        return dao.selectCount(entity);
    }

    /**
     * 分页查询列表数据
     *
     * */
    public List<T> findPage(Page<T> page,T entity){
         int pageNo=page.getPage();
         int pageSize=page.getPageSize();
         pageNo=pageNo==0 ? 1:pageNo;
         pageSize=pageSize==0 ? 10:pageSize;
         PageHelper.startPage(pageNo,pageSize);
         return  dao.select(entity);
    }


    /**
     * 插入数据
     * */
    @Transactional(readOnly = false)
    public int insertSelective(T entity){
        entity.preInsert();
        //验证数据
        BeanValidators.validateWithException(validator,entity);
        return  dao.insert(entity);
    }


    /**
     * 插入数据
     * */
    @Transactional(readOnly = false)
    public int insert(T entity){
        entity.preInsert();
        //验证数据
        BeanValidators.validateWithException(validator,entity);
        return  dao.insert(entity);
    }

    /**
     * 更新数据
     *
     * */
    @Transactional(readOnly = false)
    public int  update(T entity){
        entity.preUpdate();
        return dao.updateByPrimaryKeySelective(entity);
    }

    @Transactional(readOnly = false)
    public int updateByexample(T entity, Map<String,Object> param){
        entity.preUpdate();
        Example example=new Example(entity.getClass());
        Example.Criteria criteria=example.createCriteria();
        //遍历查询条件
        for(Map.Entry<String,Object> m: param.entrySet()){
            criteria.andEqualTo(m.getKey(),m.getValue());
        }
        return dao.updateByExampleSelective(entity,example);
    }

    /**更新数据*/
    @Transactional(readOnly = false)
    public int  updateByExample(T entity,Example example){
        entity.preUpdate();
        return dao.updateByExampleSelective(entity,example);
    }


    /**逻辑删除*/
    @Transactional(readOnly = false)
    public int  delete(T entity){
        entity.setDeleteFlag(1);
        return  dao.updateByPrimaryKeySelective(entity);
    }

    /**物理删除*/
    @Transactional(readOnly = false)
    public int deleteWithoutFlag(T entity){
        return dao.delete(entity);
    }
}

