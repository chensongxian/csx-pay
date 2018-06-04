package com.csx.pay.user.service.impl;

import com.csx.pay.account.entity.RpAccount;
import com.csx.pay.account.service.RpAccountService;
import com.csx.pay.common.core.enums.PublicStatusEnum;
import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.common.core.utils.EncryptUtil;
import com.csx.pay.common.core.utils.StringUtil;
import com.csx.pay.user.dao.RpUserInfoDao;
import com.csx.pay.user.entity.RpUserInfo;
import com.csx.pay.user.service.BuildNoService;
import com.csx.pay.user.service.RpUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.user.service.impl
 * @Description: 用户信息service实现类
 * @date 2018/6/4 0004
 */
@Service("rpUserInfoService")
public class RpUserInfoServiceImpl implements RpUserInfoService {
    @Autowired
    private RpUserInfoDao rpUserInfoDao;

    @Autowired
    private BuildNoService buildNoService;

    @Autowired
    private RpAccountService rpAccountService;

    @Override
    public void saveData(RpUserInfo rpUserInfo) {
        rpUserInfoDao.insert(rpUserInfo);
    }

    @Override
    public void updateData(RpUserInfo rpUserInfo) {
        rpUserInfoDao.update(rpUserInfo);
    }

    @Override
    public RpUserInfo getDataById(String id) {
        return rpUserInfoDao.getById(id);
    }

    @Override
    public PageBean listPage(PageParam pageParam, RpUserInfo rpUserInfo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userNo", rpUserInfo.getUserNo());
        return rpUserInfoDao.listPage(pageParam, paramMap);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerOffline(String userName, String mobile, String password) {
        String userNo = buildNoService.buildUserNo();
        String accountNo = buildNoService.buildAccountNo();

        //生成用户信息
        RpUserInfo rpUserInfo = new RpUserInfo();
        rpUserInfo.setAccountNo(accountNo);
        rpUserInfo.setCreateTime(new Date());
        rpUserInfo.setId(StringUtil.get32UUID());
        rpUserInfo.setStatus(PublicStatusEnum.ACTIVE.name());
        rpUserInfo.setUserName(userName);
        rpUserInfo.setUserNo(userNo);
        rpUserInfo.setMobile(mobile);
        rpUserInfo.setPassword(EncryptUtil.encodeMD5String(password));
        rpUserInfo.setPayPwd(EncryptUtil.encodeMD5String("123456"));
        rpUserInfo.setVersion(0);
        this.saveData(rpUserInfo);

        // 生成账户信息
        RpAccount rpAccount = new RpAccount();
        rpAccount.setAccountNo(accountNo);
        rpAccount.setAccountType("0");
        rpAccount.setCreateTime(new Date());
        rpAccount.setEditTime(new Date());
        rpAccount.setId(StringUtil.get32UUID());
        rpAccount.setStatus(PublicStatusEnum.ACTIVE.name());
        rpAccount.setUserNo(userNo);
        rpAccount.setBalance(new BigDecimal("0"));
        rpAccount.setSecurityMoney(new BigDecimal("0"));
        rpAccount.setSettAmount(new BigDecimal("0"));
        rpAccount.setTodayExpend(new BigDecimal("0"));
        rpAccount.setTodayIncome(new BigDecimal("0"));
        rpAccount.setUnbalance(new BigDecimal("0"));
        rpAccount.setTotalExpend(new BigDecimal("0"));
        rpAccount.setTotalIncome(new BigDecimal("0"));
        rpAccountService.saveData(rpAccount);
    }


    @Override
    public RpUserInfo getDataByMerchentNo(String merchantNo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userNo", merchantNo);
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        return rpUserInfoDao.getBy(paramMap);
    }


    @Override
    public RpUserInfo getDataByMobile(String mobile){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("mobile", mobile);
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        return rpUserInfoDao.getBy(paramMap);
    }


    @Override
    public List<RpUserInfo> listAll(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        return rpUserInfoDao.listBy(paramMap);
    }
}
