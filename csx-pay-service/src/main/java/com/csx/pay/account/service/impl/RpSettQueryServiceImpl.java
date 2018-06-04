package com.csx.pay.account.service.impl;

import com.csx.pay.account.dao.RpSettDailyCollectDao;
import com.csx.pay.account.dao.RpSettRecordDao;
import com.csx.pay.account.entity.RpSettRecord;
import com.csx.pay.account.service.RpSettQueryService;
import com.csx.pay.common.core.exception.BizException;
import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.account.service.impl
 * @Description: 结算查询service实现类
 * @date 2018/6/4 0004
 */
@Service("rpSettQueryService")
public class RpSettQueryServiceImpl implements RpSettQueryService {
    @Autowired
    private RpSettRecordDao rpSettRecordDao;
    @Autowired
    private RpSettDailyCollectDao settDailyCollectMapper;


    @Override
    public PageBean querySettRecordListPage(PageParam pageParam, Map<String, Object> params) throws BizException {
        return rpSettRecordDao.listPage(pageParam, params);
    }


    @Override
    public PageBean querySettDailyCollectListPage(PageParam pageParam, Map<String, Object> params) throws BizException{
        return settDailyCollectMapper.listPage(pageParam, params);
    }


    @Override
    public RpSettRecord getDataById(String id){
        return rpSettRecordDao.getById(id);
    }
}
