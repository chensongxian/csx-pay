package com.csx.pay.user.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.csx.pay.common.core.exception.BizException;
import com.csx.pay.common.core.utils.DateUtils;
import com.csx.pay.user.dao.BuildNoDao;
import com.csx.pay.user.entity.SeqBuild;
import com.csx.pay.user.service.BuildNoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author csx
 * @Package com.csx.pay.user.service.impl
 * @Description: 生成编号service实现类，用特殊前缀来区分不同的编号
 * @date 2018/6/4 0004
 */
@Service("buildNoService")
public class BuildNoServiceImpl implements BuildNoService {
    private static final Log LOG = LogFactory.getLog(BuildNoServiceImpl.class);

    /** 对账批次号前缀 **/
    private static final String RECONCILIATION_BATCH_NO = "5555";

    /** 银行订单号 **/
    private static final String BANK_ORDER_NO_PREFIX = "6666";
    /** 支付流水号前缀 **/
    private static final String TRX_NO_PREFIX = "7777";

    /** 用户编号前缀 **/
    private static final String USER_NO_PREFIX = "8888";

    /** 账户编号前缀 **/
    private static final String ACCOUNT_NO_PREFIX = "9999";

    @Autowired
    private BuildNoDao buildNoDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String buildUserNo() {
        // 获取用户编号序列
        String userNoSeq = this.getSeqNextValue("USER_NO_SEQ");

        // 20位的用户编号规范：'8888' + yyyyMMdd(时间) + 序列的后8位
        String dateString = DateUtils.toString(new Date(), "yyyyMMdd");
        String userNo = USER_NO_PREFIX + dateString + userNoSeq.substring(userNoSeq.length() - 8, userNoSeq.length());
        return userNo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String buildAccountNo() {
        // 获取账户编号序列值，用于生成20位的账户编号
        String accountNoSeq = this.getSeqNextValue("ACCOUNT_NO_SEQ");
        // 20位的账户编号规范：'9999' + yyyyMMdd(时间) + 序列的后8位
        String dateString = DateUtils.toString(new Date(), "yyyyMMdd");
        String accountNo = ACCOUNT_NO_PREFIX + dateString + accountNoSeq.substring(accountNoSeq.length() - 8, accountNoSeq.length());

        return accountNo;
    }


    @Override
    public String buildTrxNo() {

        String trxNoSeq = this.getSeqNextValue("TRX_NO_SEQ");
        // 20位的支付流水号规范：'8888' + yyyyMMdd(时间) + 序列的后8位
        String dateString = DateUtils.toString(new Date(), "yyyyMMdd");
        String trxNo = TRX_NO_PREFIX + dateString + trxNoSeq.substring(trxNoSeq.length() - 8, trxNoSeq.length());
        return trxNo;
    }


    @Override
    public String buildBankOrderNo() {

        String bankOrderNoSeq = this.getSeqNextValue("BANK_ORDER_NO_SEQ");
        // 20位的用户编号规范：'8888' + yyyyMMdd(时间) + 序列的后8位
        String dateString = DateUtils.toString(new Date(), "yyyyMMdd");
        String bankOrderNo = BANK_ORDER_NO_PREFIX + dateString + bankOrderNoSeq.substring(bankOrderNoSeq.length() - 8, bankOrderNoSeq.length());
        return bankOrderNo;
    }


    @Override
    public String buildReconciliationNo() {
        String batchNoSeq = this.getSeqNextValue("RECONCILIATION_BATCH_NO_SEQ");
        String dateString = DateUtils.toString(new Date(), "yyyyMMdd");
        String batchNo = RECONCILIATION_BATCH_NO + dateString + batchNoSeq.substring(batchNoSeq.length() - 8, batchNoSeq.length());
        return batchNo;
    }


    @Transactional(rollbackFor = Exception.class)
    public String getSeqNextValue(String seqName) {
        String seqNextValue = null;
        try {
            SeqBuild seqBuild = new SeqBuild();
            seqBuild.setSeqName(seqName);
            seqNextValue = buildNoDao.getSeqNextValue(seqBuild);
        } catch (Exception e) {
            LOG.error("生成序号异常：" + "seqName=" + seqName, e);
            throw BizException.DB_GET_SEQ_NEXT_VALUE_ERROR;
        }
        if (StringUtils.isEmpty(seqNextValue)) {
            throw BizException.DB_GET_SEQ_NEXT_VALUE_ERROR;
        }
        return seqNextValue;
    }

}
