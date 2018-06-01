package com.csx.pay.account.service.impl;

import com.csx.pay.account.dao.RpAccountDao;
import com.csx.pay.account.dao.RpAccountHistoryDao;
import com.csx.pay.account.entity.RpAccount;
import com.csx.pay.account.entity.RpAccountHistory;
import com.csx.pay.account.exception.AccountBizException;
import com.csx.pay.account.service.RpAccountQueryService;
import com.csx.pay.account.vo.DailyCollectAccountHistoryVo;
import com.csx.pay.common.core.enums.PublicEnum;
import com.csx.pay.common.core.enums.PublicStatusEnum;
import com.csx.pay.common.core.exception.BizException;
import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.common.core.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.account.service.impl
 * @Description: 账户查询service实现类
 * @date 2018/5/31 0031
 */
@Service("rpAccountQueryService")
public class RpAccountQueryServiceImpl implements RpAccountQueryService {
    @Autowired
    private RpAccountDao rpAccountDao;
    @Autowired
    private RpAccountHistoryDao rpAccountHistoryDao;

    private static final Logger LOGGER=LoggerFactory.getLogger(RpAccountQueryServiceImpl.class);

    @Override
    public RpAccount getAccountByAccountNo(String accountNo) throws BizException {
        LOGGER.info("根据账户编号查询账户信息");
        RpAccount account = rpAccountDao.getByAccountNo(accountNo);
        setTodayInfo(account);
        return account;
    }

    @Override
    public RpAccount getAccountByUserNo(String userNo) throws BizException {
        Map<String,Object> map=new HashMap<>(1);
        map.put("userNo",userNo);
        LOGGER.info("根据用户编号查询账户信息");
        RpAccount account = rpAccountDao.getBy(map);
        if(account==null){
            throw AccountBizException.ACCOUNT_NOT_EXIT;
        }
        setTodayInfo(account);
        return account;
    }

    /**
     * 不是同一天，将今日收益和支出清零
     * @param account
     */
    private void setTodayInfo(RpAccount account){
        if(!DateUtils.isSameDayWithToday(account.getEditTime())){
            account.setTodayExpend(BigDecimal.ZERO);
            account.setTodayIncome(BigDecimal.ZERO);
            account.setEditTime(new Date());
            rpAccountDao.update(account);
        }
    }

    @Override
    public PageBean queryAccountHistoryListPage(PageParam pageParam, String accountNo) throws BizException {
        Map<String, Object> params = new HashMap<>(1);
        params.put("accountNo", accountNo);
        return rpAccountDao.listPage(pageParam, params);
    }

    @Override
    public PageBean queryAccountHistoryListPageByRole(PageParam pageParam, Map<String, Object> params) throws BizException {
        String accountType = (String) params.get("accountType");
        if (StringUtils.isBlank(accountType)) {
            throw AccountBizException.ACCOUNT_TYPE_IS_NULL;
        }
        return rpAccountDao.listPage(pageParam, params);
    }

    @Override
    public RpAccountHistory getAccountHistoryByAccountNo_requestNo_trxType(String accountNo, String requestNo, Integer trxType) throws BizException {
        Map<String, Object> map = new HashMap<>(3);
        map.put("accountNo", accountNo);
        map.put("requestNo", requestNo);
        map.put("trxType", trxType);
        return rpAccountHistoryDao.getBy(map);
    }

    @Override
    public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(String accountNo, String statDate, Integer riskDay, Integer fundDirection) throws BizException {
        Map<String, Object> params = new HashMap<>(4);
        params.put("accountNo", accountNo);
        params.put("statDate", statDate);
        params.put("riskDay", riskDay);
        params.put("fundDirection", fundDirection);
        return rpAccountHistoryDao.listDailyCollectAccountHistoryVo(params);
    }

    @Override
    public PageBean queryAccountListPage(PageParam pageParam, Map<String, Object> params) throws BizException {
        return rpAccountDao.listPage(pageParam,params);
    }

    @Override
    public PageBean queryAccountHistoryListPage(PageParam pageParam, Map<String, Object> params) throws BizException {
        return rpAccountHistoryDao.listPage(pageParam,params);
    }

    @Override
    public List<RpAccount> listAll() throws BizException {
        Map<String,Object> params=new HashMap<>(1);
        params.put("status",PublicStatusEnum.ACTIVE.name());
        return rpAccountDao.listBy(params);
    }
}
