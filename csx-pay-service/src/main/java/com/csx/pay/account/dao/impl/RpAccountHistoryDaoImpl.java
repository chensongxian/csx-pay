package com.csx.pay.account.dao.impl;

import com.csx.pay.account.dao.RpAccountHistoryDao;
import com.csx.pay.account.entity.RpAccountHistory;
import com.csx.pay.account.vo.DailyCollectAccountHistoryVo;
import com.csx.pay.common.core.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.account.dao.impl
 * @Description: TODO
 * @date 2018/5/31 0031
 */
@Repository
public class RpAccountHistoryDaoImpl extends BaseDaoImpl<RpAccountHistory> implements RpAccountHistoryDao {
    @Override
    public List<RpAccountHistory> listPageByParams(Map<String, Object> params) {
        return this.listBy(params);
    }

    @Override
    public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(Map<String, Object> params) {
        return this.getSessionTemplate().selectList(getStatement("listDailyCollectAccountHistoryVo"), params);
    }

    @Override
    public void updateCompleteSettTo100(Map<String, Object> params) {
        this.getSessionTemplate().update(getStatement("updateCompleteSettTo100"), params);
    }
}
