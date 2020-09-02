package com.study.project.commom.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Date: 2020/8/31  18:30
 * @Author: dongdong
 */
@Entity
@Data
public class BaseEntity<T> implements Serializable {
    private static final long serialVersionUID = -377434516539638050L;

    /**
     * 实体编号（唯一标识）
     *
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    protected  Long id;

    protected Date createTime; //创建日期
    protected Date updateTime;// 更新时间
    protected String remark;//备注
    protected Integer deleteFlag;//删除标记(0:正常 1：删除)

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getUpdateTime() {
        return updateTime;
    }
   @Length(min = 0,max = 255)
    public String getRemark() {
        return remark;
    }
   @JsonIgnore
   @NotNull(message = "删除标记不能为空")
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 更新之前执行方法，需要手动调用
     * */
    public void preUpdate(){
        this.updateTime=new Date();
    }

    /**
     * 删除标记
     *
     * */
    public static final Integer DEL_FLAG_NORMAL=0;
    public static final Integer DEL_FLAG_DELETE=1;

}
