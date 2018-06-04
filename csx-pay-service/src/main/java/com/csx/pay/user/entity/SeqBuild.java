package com.csx.pay.user.entity;

import com.csx.pay.common.core.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author csx
 * @Package com.csx.pay.user.entity
 * @Description: 此实体没有关联的表，只作用于序列查找时传参使用
 * @date 2018/6/4 0004
 */
public class SeqBuild extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 序列名称 **/
    private String seqName;

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }
}
