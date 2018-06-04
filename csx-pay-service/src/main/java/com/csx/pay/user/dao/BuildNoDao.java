package com.csx.pay.user.dao;

import com.csx.pay.common.core.dao.BaseDao;
import com.csx.pay.user.entity.SeqBuild;

/**
 * @author csx
 * @Package com.csx.pay.user.dao
 * @Description: 生成编号dao
 * @date 2018/6/4 0004
 */
public interface BuildNoDao extends BaseDao<SeqBuild> {
    /**
     * 获取下一个编号
     * @param seqBuild
     * @return
     */
    public String getSeqNextValue(SeqBuild seqBuild);
}
