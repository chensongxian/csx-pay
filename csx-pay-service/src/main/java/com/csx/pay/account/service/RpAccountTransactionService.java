package com.csx.pay.account.service;

import com.csx.pay.account.entity.RpAccount;
import com.csx.pay.common.core.exception.BizException;

import java.math.BigDecimal;

/**
 * @author csx
 * @Package com.csx.pay.account.service
 * @Description: 账户操作service接口
 * @date 2018/5/31 0031
 */
public interface RpAccountTransactionService {
    /**
     * 加款:有银行流水
     * @param userNo
     * @param amount
     * @param requestNo
     * @param bankTrxNo
     * @param trxType
     * @param remark
     * @return
     * @throws BizException
     */
    RpAccount creditToAccount(String userNo, BigDecimal amount, String requestNo, String bankTrxNo, String trxType, String remark) throws BizException;

    /**
     * 减款 :有银行流水
     * @param userNo
     * @param amount
     * @param requestNo
     * @param bankTrxNo
     * @param trxType
     * @param remark
     * @return
     * @throws BizException
     */
    RpAccount debitToAccount(String userNo, BigDecimal amount, String requestNo,String bankTrxNo, String trxType, String remark) throws BizException;

    /**
     * 加款
     * @param userNo
     * @param amount
     * @param requestNo
     * @param trxType
     * @param remark
     * @return
     * @throws BizException
     */
    RpAccount creditToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws BizException;

    /**
     * 减款
     * @param userNo
     * @param amount
     * @param requestNo
     * @param trxType
     * @param remark
     * @return
     * @throws BizException
     */
    RpAccount debitToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws BizException;


    /**
     * 冻结
     * @param userNo
     * @param freezeAmount
     * @return
     * @throws BizException
     */
    RpAccount freezeAmount(String userNo, BigDecimal freezeAmount) throws BizException;

    /**
     * 结算成功：解冻+减款
     * @param userNo
     * @param amount
     * @param requestNo
     * @param trxType
     * @param remark
     * @return
     * @throws BizException
     */
    RpAccount unFreezeAmount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws BizException;

    /**
     * 结算失败：解冻
     * @param userNo
     * @param amount
     * @return
     * @throws BizException
     */
    RpAccount unFreezeSettAmount(String userNo, BigDecimal amount) throws BizException;

    /**
     * 更新账户历史中的结算状态，并且累加可结算金额
     * @param userNo
     * @param collectDate
     * @param riskDay
     * @param totalAmount
     * @throws BizException
     */
    void settCollectSuccess(String userNo, String collectDate, int riskDay, BigDecimal totalAmount) throws BizException;
}
