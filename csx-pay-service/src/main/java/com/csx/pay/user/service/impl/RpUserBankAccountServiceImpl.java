package com.csx.pay.user.service.impl;

import com.csx.pay.common.core.enums.PublicEnum;
import com.csx.pay.common.core.enums.PublicStatusEnum;
import com.csx.pay.common.core.utils.StringUtil;
import com.csx.pay.user.dao.RpUserBankAccountDao;
import com.csx.pay.user.entity.RpUserBankAccount;
import com.csx.pay.user.enums.BankCodeEnum;
import com.csx.pay.user.service.RpUserBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.user.service.impl
 * @Description: 用户银行账户service实现类
 * @date 2018/6/4 0004
 */
@Service("rpUserBankAccountService")
public class RpUserBankAccountServiceImpl implements RpUserBankAccountService {
    @Autowired
    private RpUserBankAccountDao rpUserBankAccountDao;

    @Override
    public void saveData(RpUserBankAccount rpUserBankAccount) {
        rpUserBankAccountDao.insert(rpUserBankAccount);
    }

    @Override
    public void updateData(RpUserBankAccount rpUserBankAccount) {
        rpUserBankAccountDao.update(rpUserBankAccount);
    }


    @Override
    public RpUserBankAccount getByUserNo(String userNo){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userNo", userNo);
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        return rpUserBankAccountDao.getBy(paramMap);
    }


    @Override
    public void createOrUpdate(RpUserBankAccount rpUserBankAccount){
        RpUserBankAccount bankAccount = getByUserNo(rpUserBankAccount.getUserNo());
        if(bankAccount == null){
            bankAccount = new RpUserBankAccount();
            bankAccount.setId(StringUtil.get32UUID());
            bankAccount.setCreateTime(new Date());
            bankAccount.setEditTime(new Date());
            bankAccount.setAreas(rpUserBankAccount.getAreas());
            bankAccount.setBankAccountName(rpUserBankAccount.getBankAccountName());
            bankAccount.setBankAccountNo(rpUserBankAccount.getBankAccountNo());
            bankAccount.setBankAccountType(rpUserBankAccount.getBankAccountType());
            bankAccount.setBankCode(rpUserBankAccount.getBankCode());
            bankAccount.setBankName(BankCodeEnum.getEnum(rpUserBankAccount.getBankCode()).getDesc());
            bankAccount.setCardNo(rpUserBankAccount.getCardNo());
            bankAccount.setCardType(rpUserBankAccount.getCardType());
            bankAccount.setCity(rpUserBankAccount.getCity());
            bankAccount.setIsDefault(PublicEnum.YES.name());
            bankAccount.setMobileNo(rpUserBankAccount.getMobileNo());
            bankAccount.setProvince(rpUserBankAccount.getProvince());
            bankAccount.setRemark(rpUserBankAccount.getRemark());
            bankAccount.setStatus(PublicStatusEnum.ACTIVE.name());
            bankAccount.setUserNo(rpUserBankAccount.getUserNo());
            bankAccount.setStreet(rpUserBankAccount.getStreet());
            rpUserBankAccountDao.insert(bankAccount);
        }else{
            bankAccount.setEditTime(new Date());
            bankAccount.setAreas(rpUserBankAccount.getAreas());
            bankAccount.setBankAccountName(rpUserBankAccount.getBankAccountName());
            bankAccount.setBankAccountNo(rpUserBankAccount.getBankAccountNo());
            bankAccount.setBankAccountType(rpUserBankAccount.getBankAccountType());
            bankAccount.setBankCode(rpUserBankAccount.getBankCode());
            bankAccount.setBankName(BankCodeEnum.getEnum(rpUserBankAccount.getBankCode()).getDesc());
            bankAccount.setCardNo(rpUserBankAccount.getCardNo());
            bankAccount.setCardType(rpUserBankAccount.getCardType());
            bankAccount.setCity(rpUserBankAccount.getCity());
            bankAccount.setIsDefault(PublicEnum.YES.name());
            bankAccount.setMobileNo(rpUserBankAccount.getMobileNo());
            bankAccount.setProvince(rpUserBankAccount.getProvince());
            bankAccount.setRemark(rpUserBankAccount.getRemark());
            bankAccount.setStatus(PublicStatusEnum.ACTIVE.name());
            bankAccount.setUserNo(rpUserBankAccount.getUserNo());
            bankAccount.setStreet(rpUserBankAccount.getStreet());
            rpUserBankAccountDao.update(bankAccount);
        }
    }
}
