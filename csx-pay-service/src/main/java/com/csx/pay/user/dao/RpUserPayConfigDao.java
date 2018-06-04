package com.csx.pay.user.dao;

import com.csx.pay.common.core.dao.BaseDao;
import com.csx.pay.user.entity.RpUserPayConfig;

/**
 * @author csx
 * @Package com.csx.pay.user.dao
 * @Description: 用户支付配置dao
 * @date 2018/6/4 0004
 */
public interface RpUserPayConfigDao extends BaseDao<RpUserPayConfig> {
    /**
     * 根据用户编号获取用户支付信息
     * @param userNo
     * @return
     */
    RpUserPayConfig getByUserNo(String userNo, String auditStatus);
}
