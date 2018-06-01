package com.csx.pay.account.dao;

import com.csx.pay.account.entity.RpAccountHistory;
import com.csx.pay.account.vo.DailyCollectAccountHistoryVo;
import com.csx.pay.common.core.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.account.dao
 * @Description: 账户历史dao
 * @date 2018/5/31 0031
 */
public interface RpAccountHistoryDao extends BaseDao<RpAccountHistory> {
    /**
     * 根据条件获取账户历史列表
     * @param params
     * @return
     */
    List<RpAccountHistory> listPageByParams(Map<String, Object> params);

    /**
     * 获取结算日汇总vo列表
     * @param params
     * @return
     */
    List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(Map<String, Object> params);

    /**
     * 更新账户风险预存期外的账户历史记录记为结算完成
     * @param params
     */
    void updateCompleteSettTo100(Map<String, Object> params);
}
