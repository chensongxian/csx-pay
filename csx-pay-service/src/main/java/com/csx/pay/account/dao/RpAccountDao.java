package com.csx.pay.account.dao;

import com.csx.pay.account.entity.RpAccount;
import com.csx.pay.common.core.dao.BaseDao;

import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.account.dao
 * @Description: 账户dao
 * @date 2018/5/31 0031
 */
public interface RpAccountDao extends BaseDao<RpAccount> {
    /**
     * 根据账户编号获取账户（激活状态）
     * @param accountNo
     * @return
     */
    RpAccount getByAccountNo(String accountNo);

    /**
     * 根据用户编号获取账户
     * @param map
     * @return
     */
    RpAccount getByUserNo(Map<String, Object> map);
}
