package com.csx.pay.account.service.impl;

import com.csx.pay.account.dao.RpAccountDao;
import com.csx.pay.account.entity.RpAccount;
import com.csx.pay.account.service.RpAccountService;
import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.account.service.impl
 * @Description: TODO
 * @date 2018/6/1 0001
 */
@Service("rpAccountService")
public class RpAccountServiceImpl implements RpAccountService {
    @Autowired
    private RpAccountDao rpAccountDao;

    @Override
    public void saveData(RpAccount rpAccount) {
        rpAccountDao.insert(rpAccount);
    }

    @Override
    public void updateData(RpAccount rpAccount) {
        rpAccountDao.update(rpAccount);
    }

    @Override
    public RpAccount getDataById(String id) {
        return rpAccountDao.getById(id);
    }

    @Override
    public PageBean listPage(PageParam pageParam, RpAccount rpAccount) {
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("accountNo", rpAccount.getAccountNo());
        return rpAccountDao.listPage(pageParam,paramMap);
    }

    @Override
    public RpAccount getDataByUserNo(String userNo) {
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("userNo", userNo);
        return rpAccountDao.getBy(paramMap);
    }
}
