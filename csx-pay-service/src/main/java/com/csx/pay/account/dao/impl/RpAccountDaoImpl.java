package com.csx.pay.account.dao.impl;

import com.csx.pay.account.dao.RpAccountDao;
import com.csx.pay.account.entity.RpAccount;
import com.csx.pay.common.core.dao.impl.BaseDaoImpl;
import com.csx.pay.common.core.enums.PublicStatusEnum;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.account.dao.impl
 * @Description: 账户DAO实现类
 * @date 2018/5/31 0031
 */
@Repository
public class RpAccountDaoImpl extends BaseDaoImpl<RpAccount> implements RpAccountDao {
    @Override
    public RpAccount getByAccountNo(String accountNo){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("accountNo", accountNo);
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        return this.getBy(paramMap);
    }

    @Override
    public RpAccount getByUserNo(Map<String, Object> map){
        return this.getSessionTemplate().selectOne(getStatement("getByUserNo"), map);
    }
}
