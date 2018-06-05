package com.csx.pay.permission.service.impl;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.permission.dao.PmsOperatorLogDao;
import com.csx.pay.permission.entity.PmsOperatorLog;
import com.csx.pay.permission.service.PmsOperatorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.permission.service.impl
 * @Description: 操作日志service实现类
 * @date 2018/6/5 0005
 */
@Service("pmsOperatorLogService")
public class PmsOperatorLogServiceImpl implements PmsOperatorLogService {
    @Autowired
    private PmsOperatorLogDao pmsOperatorLogDao;

    @Override
    public void saveData(PmsOperatorLog pmsOperatorLog) {
        pmsOperatorLogDao.insert(pmsOperatorLog);
    }

    @Override
    public void updateData(PmsOperatorLog pmsOperatorLog) {
        pmsOperatorLogDao.update(pmsOperatorLog);
    }

    @Override
    public PmsOperatorLog getDataById(Long id) {
        return pmsOperatorLogDao.getById(id);
    }

    @Override
    public PageBean listPage(PageParam pageParam, PmsOperatorLog pmsOperatorLog) {
        Map<String,Object> params=new HashMap<>();
        return pmsOperatorLogDao.listPage(pageParam,params);
    }
}
