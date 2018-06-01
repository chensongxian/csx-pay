package com.csx.pay.account.service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author csx
 * @Package com.csx.pay.account.service
 * @Description: 结算核心业务处理接口
 * @date 2018/5/31 0031
 */
public interface RpSettHandleService {
    /**
     * 按单个商户发起每日待结算数据统计汇总.<br/>
     *
     * @param userNo
     *            用户编号.
     * @param endDate
     *            汇总结束日期.
     * @param riskDay
     *            风险预存期.
     * @param userName
     *            用户名称
     */
    public void dailySettlementCollect(String userNo, Date endDate, int riskDay, String userName);

    /**
     * 发起结算
     *
     * @param userNo
     * @param settAmount
     */
    public void launchSett(String userNo, BigDecimal settAmount);

    /**
     * 发起自动结算
     *
     * @param userNo
     */
    public void launchAutoSett(String userNo);

    /**
     * 结算审核
     * @param settId
     * @param settStatus
     * @param remark
     */
    public void audit(String settId, String settStatus, String remark);

    /**
     * 打款
     * @param settId
     * @param settStatus
     * @param remark
     */
    public void remit(String settId, String settStatus, String remark);
}
