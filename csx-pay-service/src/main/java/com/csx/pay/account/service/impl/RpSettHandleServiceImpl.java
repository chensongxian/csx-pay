package com.csx.pay.account.service.impl;

import com.csx.pay.account.dao.RpSettDailyCollectDao;
import com.csx.pay.account.dao.RpSettRecordDao;
import com.csx.pay.account.entity.RpAccount;
import com.csx.pay.account.entity.RpSettDailyCollect;
import com.csx.pay.account.entity.RpSettRecord;
import com.csx.pay.account.enums.SettDailyCollectStatusEnum;
import com.csx.pay.account.enums.SettDailyCollectTypeEnum;
import com.csx.pay.account.enums.SettModeTypeEnum;
import com.csx.pay.account.enums.SettRecordStatusEnum;
import com.csx.pay.account.exception.AccountBizException;
import com.csx.pay.account.exception.SettBizException;
import com.csx.pay.account.service.RpAccountQueryService;
import com.csx.pay.account.service.RpAccountTransactionService;
import com.csx.pay.account.service.RpSettHandleService;
import com.csx.pay.account.utils.AccountConfigUtil;
import com.csx.pay.account.vo.DailyCollectAccountHistoryVo;
import com.csx.pay.common.core.exception.BizException;
import com.csx.pay.common.core.utils.DateUtils;
import com.csx.pay.trade.enums.TrxTypeEnum;
import com.csx.pay.user.entity.RpUserBankAccount;
import com.csx.pay.user.entity.RpUserInfo;
import com.csx.pay.user.enums.BankAccountTypeEnum;
import com.csx.pay.user.exception.UserBizException;
import com.csx.pay.user.service.RpUserBankAccountService;
import com.csx.pay.user.service.RpUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.account.service.impl
 * @Description: 结算核心业务处理实现类
 * @date 2018/6/4 0004
 */
@Service("rpSettHandleService")
public class RpSettHandleServiceImpl implements RpSettHandleService {
    @Autowired
    private RpSettDailyCollectDao rpSettDailyCollectDao;
    @Autowired
    private RpSettRecordDao rpSettRecordDao;
    @Autowired
    private RpAccountTransactionService rpAccountTransactionService;
    @Autowired
    private RpAccountQueryService rpAccountQueryService;
    @Autowired
    private RpUserInfoService rpUserInfoService;
    @Autowired
    private RpUserBankAccountService rpUserBankAccountService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dailySettlementCollect(String userNo, Date endDate, int riskDay, String userName){
        // 根据用户编号查询账户
        RpAccount account = rpAccountQueryService.getAccountByUserNo(userNo);
        // 汇总日期
        String endDateStr = DateUtils.formatDate(endDate, "yyyy-MM-dd");
        // 汇总账户历史
        List<DailyCollectAccountHistoryVo> accountHistoryList = rpAccountQueryService.listDailyCollectAccountHistoryVo(account.getAccountNo(), endDateStr, riskDay, null);
        // 遍历统计
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (DailyCollectAccountHistoryVo collectVo : accountHistoryList) {
            // 累加可结算金额
            totalAmount = totalAmount.add(collectVo.getTotalAmount());
            // 保存结算汇总记录
            RpSettDailyCollect dailyCollect = new RpSettDailyCollect();
            dailyCollect.setAccountNo(collectVo.getAccountNo());
            dailyCollect.setUserName(userName);
            dailyCollect.setCollectDate(collectVo.getCollectDate());
            dailyCollect.setCollectType(SettDailyCollectTypeEnum.ALL.name());
            dailyCollect.setTotalAmount(collectVo.getTotalAmount());
            dailyCollect.setTotalCount(collectVo.getTotalNum());
            dailyCollect.setSettStatus(SettDailyCollectStatusEnum.SETTLLED.name());
            dailyCollect.setRiskDay(collectVo.getRiskDay());
            dailyCollect.setRemark("");
            dailyCollect.setEditTime(new Date());
            rpSettDailyCollectDao.insert(dailyCollect);
        }

        // 更新账户历史中的结算状态，并且累加可结算金额
        rpAccountTransactionService.settCollectSuccess(userNo, endDateStr, riskDay, totalAmount);
    }


    @Override
    public void launchSett(String userNo, BigDecimal settAmount){
        RpAccount account = rpAccountQueryService.getAccountByUserNo(userNo);
        RpUserInfo userInfo = rpUserInfoService.getDataByMerchentNo(userNo);
        RpUserBankAccount rpUserBankAccount = rpUserBankAccountService.getByUserNo(userNo);
        BigDecimal availableAmount = account.getAvailableSettAmount();
        if (settAmount.compareTo(availableAmount) > 0) {
            // 金额超限
            throw AccountBizException.ACCOUNT_SUB_AMOUNT_OUTLIMIT;
        }
        if (rpUserBankAccount == null) {
            throw UserBizException.USER_BANK_ACCOUNT_IS_NULL;

        }

        String settType = SettModeTypeEnum.SELFHELP_SETTLE.name();
        this.launchSett(userNo, userInfo.getUserName(), account.getAccountNo(), settAmount, rpUserBankAccount, settType);

    }



    @Transactional(rollbackFor = Exception.class)
    protected void launchSett(String userNo, String userName, String accountNo, BigDecimal settAmount, RpUserBankAccount bankAccount,String settType) {

        // 所行查询账户
        RpSettRecord settRecord = new RpSettRecord();
        settRecord.setAccountNo(accountNo);
        settRecord.setCountry("中国");
        settRecord.setProvince(bankAccount.getProvince());
        settRecord.setCity(bankAccount.getCity());
        settRecord.setAreas(bankAccount.getAreas());
        settRecord.setBankAccountAddress(bankAccount.getStreet());
        settRecord.setBankAccountName(bankAccount.getBankAccountName());
        settRecord.setBankCode(bankAccount.getBankCode());
        settRecord.setBankName(bankAccount.getBankName());
        settRecord.setBankAccountNo(bankAccount.getBankAccountNo());
        settRecord.setBankAccountType(bankAccount.getBankAccountType());
        settRecord.setOperatorLoginname("");
        settRecord.setOperatorRealname("");
        settRecord.setRemitAmount(settAmount);
        settRecord.setRemitRequestTime(new Date());
        settRecord.setSettAmount(settAmount);
        settRecord.setSettFee(BigDecimal.ZERO);
        settRecord.setSettMode(settType);
        settRecord.setSettStatus(SettRecordStatusEnum.WAIT_CONFIRM.name());
        settRecord.setUserName(userName);
        settRecord.setUserNo(userNo);
        settRecord.setMobileNo(bankAccount.getMobileNo());
        settRecord.setEditTime(new Date());
        rpSettRecordDao.insert(settRecord);

        // 冻结准备结算出去的资金
        rpAccountTransactionService.freezeAmount(userNo, settAmount);
    }


    @Override
    public void launchAutoSett(String userNo){
        RpUserInfo userInfo = rpUserInfoService.getDataByMerchentNo(userNo);
        RpAccount account = rpAccountQueryService.getAccountByUserNo(userNo);
        BigDecimal settAmount = account.getAvailableSettAmount();
        String settMinAmount = AccountConfigUtil.readConfig("sett_min_amount");
        if (settAmount.compareTo(new BigDecimal(settMinAmount)) == -1) {
            throw new BizException("每次发起结算的金额必须大于:" + settMinAmount);
        }

        RpUserBankAccount rpUserBankAccount = rpUserBankAccountService.getByUserNo(userNo);
        if (rpUserBankAccount == null) {
            throw new BizException("没有结算银行卡信息，请先绑定结算银行卡");
        }

        // 根据银行卡信息判断收款账户的类型
        String bankType = rpUserBankAccount.getBankAccountType();

        // 如果是对私账户，需要控制 1.单笔上线是否小于5W
        if (bankType.equals(BankAccountTypeEnum.PRIVATE_DEBIT_ACCOUNT.name())) {
            // 结算的金额最大值
            String settMaxAmount = AccountConfigUtil.readConfig("sett_max_amount");
            if (settAmount.compareTo(new BigDecimal(settMaxAmount)) == 1) {
                throw new BizException("每次发起结算的金额必须小于:" + settMaxAmount);
            }
        }
        // 结算记录中的userNo存企业表中企业代号
        String userName = userInfo.getUserName();
        String accountNo = account.getAccountNo();
        String settType = SettModeTypeEnum.REGULAR_SETTLE.name();
        this.launchSett(userNo, userName, accountNo, settAmount, rpUserBankAccount,settType);
    }


    @Override
    public void audit(String settId, String settStatus, String remark){
        RpSettRecord settRecord = rpSettRecordDao.getById(settId);
        if(!settRecord.getSettStatus().equals(SettRecordStatusEnum.WAIT_CONFIRM.name())){
            throw SettBizException.SETT_STATUS_ERROR;
        }
        settRecord.setSettStatus(settStatus);
        settRecord.setEditTime(new Date());
        settRecord.setRemark(remark);
        rpSettRecordDao.update(settRecord);

        //审核不通过
        if(settStatus.equals(SettRecordStatusEnum.CANCEL.name())){
            //解冻金额
            rpAccountTransactionService.unFreezeSettAmount(settRecord.getUserNo(), settRecord.getSettAmount());
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remit(String settId, String settStatus, String remark){
        RpSettRecord settRecord = rpSettRecordDao.getById(settId);
        if(!settRecord.getSettStatus().equals(SettRecordStatusEnum.CONFIRMED.name())){
            throw SettBizException.SETT_STATUS_ERROR;
        }
        settRecord.setSettStatus(settStatus);
        settRecord.setEditTime(new Date());
        settRecord.setRemitRemark(remark);
        settRecord.setRemitAmount(settRecord.getSettAmount());
        settRecord.setRemitConfirmTime(new Date());
        settRecord.setRemitRequestTime(new Date());
        rpSettRecordDao.update(settRecord);

        //打款失败
        if(settStatus.equals(SettRecordStatusEnum.REMIT_FAIL.name())){
            //解冻金额
            rpAccountTransactionService.unFreezeSettAmount(settRecord.getUserNo(), settRecord.getSettAmount());
        }else if(settStatus.equals(SettRecordStatusEnum.REMIT_SUCCESS.name())){
            rpAccountTransactionService.unFreezeAmount(settRecord.getUserNo(), settRecord.getSettAmount(), settRecord.getId(), TrxTypeEnum.REMIT.name(), remark);
        }
    }
}
