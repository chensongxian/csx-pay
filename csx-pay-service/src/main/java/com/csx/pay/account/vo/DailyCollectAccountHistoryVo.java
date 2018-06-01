package com.csx.pay.account.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author csx
 * @Package com.csx.pay.account.vo
 * @Description: 结算日汇总vo
 * @date 2018/5/31 0031
 */
public class DailyCollectAccountHistoryVo {
    /**
     *
     */
    private static final long serialVersionUID = -2451289258390618916L;

    /**
     * 账户编号
     */
    private String accountNo;

    /**
     * 汇总日期
     */
    private Date collectDate;

    /**
     * 总金额
     */
    private BigDecimal totalAmount = BigDecimal.ZERO;

    /**
     * 总笔数
     */
    private Integer totalNum = 0;

    /**
     * 最后ID
     */
    private Long lastId = 0L;

    /**
     * 风险预存期
     */
    private Integer riskDay;

    /**
     * 账户编号
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * 账户编号
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * 汇总日期
     */
    public Date getCollectDate() {
        return collectDate;
    }

    /**
     * 汇总日期
     */
    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    /**
     * 总金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 总金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 总笔数
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * 总笔数
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 最后ID
     *
     * @return
     */
    public Long getLastId() {
        return lastId;
    }

    /**
     * 最后ID
     *
     * @return
     */
    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

    /**
     * 风险预存期
     */
    public Integer getRiskDay() {
        return riskDay;
    }

    /**
     * 风险预存期
     */
    public void setRiskDay(Integer riskDay) {
        this.riskDay = riskDay;
    }
}
