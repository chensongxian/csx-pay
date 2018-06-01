package com.csx.pay.account.service.impl;

import com.csx.pay.account.dao.RpAccountDao;
import com.csx.pay.account.dao.RpAccountHistoryDao;
import com.csx.pay.account.entity.RpAccount;
import com.csx.pay.account.entity.RpAccountHistory;
import com.csx.pay.account.enums.AccountFundDirectionEnum;
import com.csx.pay.account.exception.AccountBizException;
import com.csx.pay.account.service.RpAccountTransactionService;
import com.csx.pay.common.core.enums.PublicEnum;
import com.csx.pay.common.core.exception.BizException;
import com.csx.pay.common.core.utils.DateUtils;
import com.csx.pay.common.core.utils.StringUtil;
import com.csx.pay.trade.enums.TrxTypeEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.account.service.impl
 * @Description: TODO
 * @date 2018/6/1 0001
 */
@Service("rpAccountTransactionService")
public class RpAccountTransactionServiceImpl implements RpAccountTransactionService {
    private static final Log LOG = LogFactory.getLog(RpAccountTransactionServiceImpl.class);

    @Autowired
    private RpAccountDao rpAccountDao;
    @Autowired
    private RpAccountHistoryDao rpAccountHistoryDao;

    /**
     *根据用户编号编号获取账户信息
     * @param userNo 用户编号
     * @param isPessimist 是否加行锁
     * @return
     */
    private RpAccount getByUserNoIsPessimist(String userNo, boolean isPessimist) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("userNo", userNo);
        map.put("isPessimist", isPessimist);
        return rpAccountDao.getByUserNo(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RpAccount creditToAccount(String userNo, BigDecimal amount, String requestNo, String bankTrxNo, String trxType, String remark) throws BizException {
        RpAccount account=getByUserNoIsPessimist(userNo,true);
        if(account==null){
            throw AccountBizException.ACCOUNT_NOT_EXIT;
        }
        /*
         * 不是同一天将今天开支和收益置零
         */
        Date lastModifyDate=account.getEditTime();
        if(!DateUtils.isSameDayWithToday(lastModifyDate)){
            account.setTodayIncome(BigDecimal.ZERO);
            account.setTodayExpend(BigDecimal.ZERO);
        }
        /*
         * 总收益累加和今日收益
         */
        if(TrxTypeEnum.EXPENSE.equals(trxType)){
            //业务类型是交易

            account.setTotalIncome(account.getTotalIncome().add(amount));

            /***** 根据上次修改时间，统计今日收益 *******/
            if(DateUtils.isSameDayWithToday(lastModifyDate)){
                //如果是同一天
                account.setTodayIncome(account.getTodayIncome().add(amount));
            }else{
                //如果不是同一天
                account.setTodayIncome(amount);
            }
            /*****************************************/
        }

        String completeSett = PublicEnum.NO.name();
        String isAllowSett = PublicEnum.YES.name();

        /** 设置余额的值 **/
        account.setBalance(account.getBalance().add(amount));
        account.setEditTime(new Date());

        // 记录账户历史
        RpAccountHistory accountHistoryEntity = new RpAccountHistory();
        accountHistoryEntity.setCreateTime(new Date());
        accountHistoryEntity.setEditTime(new Date());
        accountHistoryEntity.setIsAllowSett(isAllowSett);
        accountHistoryEntity.setAmount(amount);
        accountHistoryEntity.setBalance(account.getBalance());
        accountHistoryEntity.setRequestNo(requestNo);
        accountHistoryEntity.setBankTrxNo(bankTrxNo);
        accountHistoryEntity.setIsCompleteSett(completeSett);
        accountHistoryEntity.setRemark(remark);
        accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.ADD.name());
        accountHistoryEntity.setAccountNo(account.getAccountNo());
        accountHistoryEntity.setTrxType(trxType);
        accountHistoryEntity.setId(StringUtil.get32UUID());
        accountHistoryEntity.setUserNo(userNo);

        this.rpAccountHistoryDao.insert(accountHistoryEntity);
        this.rpAccountDao.update(account);
        LOG.info("账户加款成功，并记录了账户历史");
        return account;
    }

    @Override
    public RpAccount debitToAccount(String userNo, BigDecimal amount, String requestNo, String bankTrxNo, String trxType, String remark) throws BizException {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RpAccount creditToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws BizException {
        return this.creditToAccount(userNo, amount, requestNo, null,trxType, remark);
    }

    @Override
    public RpAccount debitToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws BizException {
        return null;
    }

    @Override
    public RpAccount freezeAmount(String userNo, BigDecimal freezeAmount) throws BizException {
        return null;
    }

    @Override
    public RpAccount unFreezeAmount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws BizException {
        return null;
    }

    @Override
    public RpAccount unFreezeSettAmount(String userNo, BigDecimal amount) throws BizException {
        return null;
    }

    @Override
    public void settCollectSuccess(String accountNo, String collectDate, int riskDay, BigDecimal totalAmount) throws BizException {

    }
}
