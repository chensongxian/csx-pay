package com.csx.pay.user.service.impl;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.user.dao.RpUserPayInfoDao;
import com.csx.pay.user.entity.RpUserPayInfo;
import com.csx.pay.user.service.RpUserPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.user.service.impl
 * @Description: TODO
 * @date 2018/6/4 0004
 */
@Service("rpUserPayInfoServiceImpl")
public class RpUserPayInfoServiceImpl implements RpUserPayInfoService {
    @Autowired
    private RpUserPayInfoDao rpUserPayInfoDao;

    @Override
    public void saveData(RpUserPayInfo rpUserPayInfo) {
        rpUserPayInfoDao.insert(rpUserPayInfo);
    }

    @Override
    public void updateData(RpUserPayInfo rpUserPayInfo) {
        rpUserPayInfoDao.update(rpUserPayInfo);
    }

    @Override
    public RpUserPayInfo getDataById(String id) {
        return rpUserPayInfoDao.getById(id);
    }

    @Override
    public PageBean listPage(PageParam pageParam, RpUserPayInfo rpUserPayInfo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return rpUserPayInfoDao.listPage(pageParam, paramMap);
    }


    @Override
    public RpUserPayInfo getByUserNo(String userNo, String payWayCode) {
        return rpUserPayInfoDao.getByUserNo(userNo, payWayCode);
    }
}
