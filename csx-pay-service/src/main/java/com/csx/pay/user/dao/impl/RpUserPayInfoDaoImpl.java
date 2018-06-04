package com.csx.pay.user.dao.impl;

import com.csx.pay.common.core.dao.impl.BaseDaoImpl;
import com.csx.pay.user.dao.RpUserPayInfoDao;
import com.csx.pay.user.entity.RpUserPayInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.user.dao.impl
 * @Description: 用户第三方支付信息dao实现类
 * @date 2018/6/4 0004
 */
@Repository
public class RpUserPayInfoDaoImpl extends BaseDaoImpl<RpUserPayInfo> implements RpUserPayInfoDao {
    @Override
    public RpUserPayInfo getByUserNo(String userNo, String payWayCode) {
        Map<String , Object> paramMap = new HashMap<String , Object>();
        paramMap.put("userNo",userNo);
        paramMap.put("payWayCode",payWayCode);
        return super.getBy(paramMap);
    }
}
