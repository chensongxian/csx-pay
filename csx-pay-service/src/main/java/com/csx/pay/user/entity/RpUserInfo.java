package com.csx.pay.user.entity;

import com.csx.pay.common.core.entity.BaseEntity;
import com.csx.pay.common.core.enums.PublicStatusEnum;

import java.io.Serializable;

/**
 * @author csx
 * @Package com.csx.pay.user.entity
 * @Description: 用户信息实体
 * @date 2018/6/4 0004
 */
public class RpUserInfo extends BaseEntity implements Serializable {
    private String userNo;

    private String userName;

    private String accountNo;

    private static final long serialVersionUID = 1L;

    private String mobile;

    private String password;
    /** 支付密码 */
    private String payPwd;

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getStatusDesc() {
        return PublicStatusEnum.getEnum(this.getStatus()).getDesc();
    }
}
