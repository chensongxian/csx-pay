package com.csx.pay.user.service;

import com.csx.pay.user.entity.RpUserBankAccount;

/**
 * @author csx
 * @Package com.csx.pay.user.service
 * @Description: 银行账户service接口
 * @date 2018/6/4 0004
 */
public interface RpUserBankAccountService {
    /**
     * 保存
     */
    void saveData(RpUserBankAccount rpUserBankAccount);

    /**
     * 更新
     */
    void updateData(RpUserBankAccount rpUserBankAccount);

    /**
     * 根据用户编号获取银行账户
     */
    RpUserBankAccount getByUserNo(String userNo);

    /**
     * 创建或更新
     * @param rpUserBankAccount
     */
    void createOrUpdate(RpUserBankAccount rpUserBankAccount);
}
