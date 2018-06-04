package com.csx.pay.user.dao;

import com.csx.pay.common.core.dao.BaseDao;
import com.csx.pay.user.entity.RpUserPayInfo;

/**
 * @author csx
 * @Package com.csx.pay.user.dao
 * @Description: 用户第三方支付信息dao
 * @date 2018/6/4 0004
 */
public interface RpUserPayInfoDao extends BaseDao<RpUserPayInfo> {
    /**
     * 通过商户编号获取商户第三方支付信息
     * @param userNo
     * @param payWayCode
     * @return
     */
    public  RpUserPayInfo getByUserNo(String userNo, String payWayCode);
}
