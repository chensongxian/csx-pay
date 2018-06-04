package com.csx.pay.user.dao.impl;

import com.csx.pay.common.core.dao.impl.BaseDaoImpl;
import com.csx.pay.user.dao.BuildNoDao;
import com.csx.pay.user.entity.SeqBuild;
import org.springframework.stereotype.Repository;

/**
 * @author csx
 * @Package com.csx.pay.user.dao.impl
 * @Description: 生成编号dao实现类
 * @date 2018/6/4 0004
 */
@Repository
public class BuildNoDaoImpl extends BaseDaoImpl<SeqBuild> implements BuildNoDao {
    @Override
    public String getSeqNextValue(SeqBuild seqBuild) {
        return super.getSqlSession().selectOne(getStatement("getSeqNextValue"),seqBuild);
    }
}
