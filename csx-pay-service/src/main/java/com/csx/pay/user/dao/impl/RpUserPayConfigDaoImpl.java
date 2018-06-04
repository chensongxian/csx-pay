package com.csx.pay.user.dao.impl;

import com.csx.pay.common.core.dao.impl.BaseDaoImpl;
import com.csx.pay.common.core.enums.PublicStatusEnum;
import com.csx.pay.user.dao.RpUserPayConfigDao;
import com.csx.pay.user.entity.RpUserPayConfig;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.user.dao.impl
 * @Description: 用户支付配置dao实现类
 * @date 2018/6/4 0004
 */
@Repository
public class RpUserPayConfigDaoImpl extends BaseDaoImpl<RpUserPayConfig> implements RpUserPayConfigDao {
    @Override
    public RpUserPayConfig getByUserNo(String userNo, String auditStatus) {
        Map<String , Object> paramMap = new HashMap<String , Object>();
        paramMap.put("userNo",userNo);
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        paramMap.put("auditStatus", auditStatus);
        return super.getBy(paramMap);
    }
}
