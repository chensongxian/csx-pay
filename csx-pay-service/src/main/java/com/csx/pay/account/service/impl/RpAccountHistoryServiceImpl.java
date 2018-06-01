package com.csx.pay.account.service.impl;

import com.csx.pay.account.dao.RpAccountHistoryDao;
import com.csx.pay.account.entity.RpAccountHistory;
import com.csx.pay.account.service.RpAccountHistoryService;
import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.account.service.impl
 * @Description: 账户历史service实现类
 * @date 2018/5/31 0031
 */
@Service("rpAccountHistoryService")
public class RpAccountHistoryServiceImpl implements RpAccountHistoryService {
    @Autowired
    private RpAccountHistoryDao rpAccountHistoryDao;

    @Override
    public void saveData(RpAccountHistory rpAccountHistory) {
        rpAccountHistoryDao.insert(rpAccountHistory);
    }

    @Override
    public void updateData(RpAccountHistory rpAccountHistory) {
        rpAccountHistoryDao.update(rpAccountHistory);
    }

    @Override
    public RpAccountHistory getDataById(String id) {
        return rpAccountHistoryDao.getById(id);
    }

    @Override
    public PageBean listPage(PageParam pageParam, RpAccountHistory rpAccountHistory) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("accountNo",rpAccountHistory.getAccountNo());
        return rpAccountHistoryDao.listPage(pageParam,paramMap);
    }
}
