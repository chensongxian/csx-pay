package com.csx.pay.user.service.impl;

import com.csx.pay.common.core.enums.PayWayEnum;
import com.csx.pay.common.core.enums.PublicEnum;
import com.csx.pay.common.core.enums.PublicStatusEnum;
import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.common.core.utils.StringUtil;
import com.csx.pay.user.dao.RpUserPayConfigDao;
import com.csx.pay.user.entity.RpPayProduct;
import com.csx.pay.user.entity.RpPayWay;
import com.csx.pay.user.entity.RpUserPayConfig;
import com.csx.pay.user.entity.RpUserPayInfo;
import com.csx.pay.user.exception.PayBizException;
import com.csx.pay.user.service.RpPayProductService;
import com.csx.pay.user.service.RpPayWayService;
import com.csx.pay.user.service.RpUserPayConfigService;
import com.csx.pay.user.service.RpUserPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.user.service.impl
 * @Description: TODO
 * @date 2018/6/4 0004
 */
@Service("rpUserPayConfigService")
public class RpUserPayConfigServiceImpl implements RpUserPayConfigService {
    @Autowired
    private RpUserPayConfigDao rpUserPayConfigDao;
    @Autowired
    private RpPayProductService rpPayProductService;
    @Autowired
    private RpPayWayService rpPayWayService;
    @Autowired
    private RpUserPayInfoService rpUserPayInfoService;

    @Override
    public void saveData(RpUserPayConfig rpUserPayConfig) {
        rpUserPayConfigDao.insert(rpUserPayConfig);
    }

    @Override
    public void updateData(RpUserPayConfig rpUserPayConfig) {
        rpUserPayConfigDao.update(rpUserPayConfig);
    }

    @Override
    public RpUserPayConfig getDataById(String id) {
        return rpUserPayConfigDao.getById(id);
    }

    @Override
    public PageBean listPage(PageParam pageParam, RpUserPayConfig rpUserPayConfig) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("productCode", rpUserPayConfig.getProductCode());
        paramMap.put("userNo", rpUserPayConfig.getUserNo());
        paramMap.put("userName", rpUserPayConfig.getUserName());
        paramMap.put("productName", rpUserPayConfig.getProductName());
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        return rpUserPayConfigDao.listPage(pageParam, paramMap);
    }


    @Override
    public RpUserPayConfig getByUserNo(String userNo) {
        return rpUserPayConfigDao.getByUserNo(userNo, PublicEnum.YES.name());
    }


    @Override
    public RpUserPayConfig getByUserNo(String userNo, String auditStatus){
        return rpUserPayConfigDao.getByUserNo(userNo, auditStatus);
    }



    @Override
    public List<RpUserPayConfig> listByProductCode(String productCode){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("productCode", productCode);
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        paramMap.put("auditStatus", PublicEnum.YES.name());
        return rpUserPayConfigDao.listBy(paramMap);
    }


    @Override
    public List<RpUserPayConfig> listByProductCode(String productCode, String auditStatus){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("productCode", productCode);
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        paramMap.put("auditStatus", auditStatus);
        return rpUserPayConfigDao.listBy(paramMap);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay,
                                    String fundIntoType, String isAutoSett, String appId, String merchantId, String partnerKey,
                                    String ali_partner, String ali_sellerId, String ali_key, String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey)  throws PayBizException{

        createUserPayConfig( userNo,  userName,  productCode,  productName, riskDay,
                fundIntoType,  isAutoSett,  appId,  merchantId,  partnerKey,
                ali_partner,  ali_sellerId,  ali_key,  ali_appid,  ali_rsaPrivateKey,  ali_rsaPublicKey ,  null ,  null);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay,
                                    String fundIntoType, String isAutoSett, String appId, String merchantId, String partnerKey,
                                    String ali_partner, String ali_sellerId, String ali_key, String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey , String securityRating , String merchantServerIp)  throws PayBizException{

        RpUserPayConfig payConfig = rpUserPayConfigDao.getByUserNo(userNo, null);
        if(payConfig != null){
            throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_EXIST,"用户支付配置已存在");
        }

        RpUserPayConfig rpUserPayConfig = new RpUserPayConfig();
        rpUserPayConfig.setUserNo(userNo);
        rpUserPayConfig.setUserName(userName);
        rpUserPayConfig.setProductCode(productCode);
        rpUserPayConfig.setProductName(productName);
        rpUserPayConfig.setStatus(PublicStatusEnum.ACTIVE.name());
        rpUserPayConfig.setAuditStatus(PublicEnum.YES.name());
        rpUserPayConfig.setRiskDay(riskDay);
        rpUserPayConfig.setFundIntoType(fundIntoType);
        rpUserPayConfig.setIsAutoSett(isAutoSett);
        rpUserPayConfig.setPayKey(StringUtil.get32UUID());
        rpUserPayConfig.setPaySecret(StringUtil.get32UUID());
        rpUserPayConfig.setId(StringUtil.get32UUID());
        //安全等级
        rpUserPayConfig.setSecurityRating(securityRating);
        rpUserPayConfig.setMerchantServerIp(merchantServerIp);
        saveData(rpUserPayConfig);

        //查询支付产品下有哪些支付方式
        List<RpPayWay> payWayList = rpPayWayService.listByProductCode(productCode);
        Map<String, String> map = new HashMap<String, String>();
        //过滤重复数据
        for(RpPayWay payWay : payWayList){
            map.put(payWay.getPayWayCode(), payWay.getPayWayName());
        }

        for (String key : map.keySet()) {
            if(key.equals(PayWayEnum.WEIXIN.name())){
                //创建用户第三方支付信息
                RpUserPayInfo rpUserPayInfo = rpUserPayInfoService.getByUserNo(userNo, PayWayEnum.WEIXIN.name());
                if(rpUserPayInfo == null){
                    rpUserPayInfo = new RpUserPayInfo();
                    rpUserPayInfo.setId(StringUtil.get32UUID());
                    rpUserPayInfo.setCreateTime(new Date());
                    rpUserPayInfo.setAppId(appId);
                    rpUserPayInfo.setMerchantId(merchantId);
                    rpUserPayInfo.setPartnerKey(partnerKey);
                    rpUserPayInfo.setPayWayCode(PayWayEnum.WEIXIN.name());
                    rpUserPayInfo.setPayWayName(PayWayEnum.WEIXIN.getDesc());
                    rpUserPayInfo.setUserNo(userNo);
                    rpUserPayInfo.setUserName(userName);
                    rpUserPayInfo.setStatus(PublicStatusEnum.ACTIVE.name());
                    rpUserPayInfoService.saveData(rpUserPayInfo);
                }else{
                    rpUserPayInfo.setEditTime(new Date());
                    rpUserPayInfo.setAppId(appId);
                    rpUserPayInfo.setMerchantId(merchantId);
                    rpUserPayInfo.setPartnerKey(partnerKey);
                    rpUserPayInfo.setPayWayCode(PayWayEnum.WEIXIN.name());
                    rpUserPayInfo.setPayWayName(PayWayEnum.WEIXIN.getDesc());
                    rpUserPayInfoService.updateData(rpUserPayInfo);
                }

            }else if(key.equals(PayWayEnum.ALIPAY.name())){
                //创建用户第三方支付信息
                RpUserPayInfo rpUserPayInfo = rpUserPayInfoService.getByUserNo(userNo, PayWayEnum.ALIPAY.name());
                if(rpUserPayInfo == null){
                    rpUserPayInfo = new RpUserPayInfo();
                    rpUserPayInfo.setId(StringUtil.get32UUID());
                    rpUserPayInfo.setCreateTime(new Date());
                    rpUserPayInfo.setAppId(ali_partner);
                    rpUserPayInfo.setMerchantId(ali_sellerId);
                    rpUserPayInfo.setPartnerKey(ali_key);
                    rpUserPayInfo.setPayWayCode(PayWayEnum.ALIPAY.name());
                    rpUserPayInfo.setPayWayName(PayWayEnum.ALIPAY.getDesc());
                    rpUserPayInfo.setUserNo(userNo);
                    rpUserPayInfo.setUserName(userName);
                    rpUserPayInfo.setStatus(PublicStatusEnum.ACTIVE.name());
                    rpUserPayInfo.setOfflineAppId(ali_appid);
                    rpUserPayInfo.setRsaPrivateKey(ali_rsaPrivateKey);
                    rpUserPayInfo.setRsaPublicKey(ali_rsaPublicKey);
                    rpUserPayInfoService.saveData(rpUserPayInfo);
                }else{
                    rpUserPayInfo.setEditTime(new Date());
                    rpUserPayInfo.setAppId(ali_partner);
                    rpUserPayInfo.setMerchantId(ali_sellerId);
                    rpUserPayInfo.setPartnerKey(ali_key);
                    rpUserPayInfo.setPayWayCode(PayWayEnum.ALIPAY.name());
                    rpUserPayInfo.setPayWayName(PayWayEnum.ALIPAY.getDesc());
                    rpUserPayInfo.setOfflineAppId(ali_appid);
                    rpUserPayInfo.setRsaPrivateKey(ali_rsaPrivateKey);
                    rpUserPayInfo.setRsaPublicKey(ali_rsaPublicKey);
                    rpUserPayInfoService.updateData(rpUserPayInfo);
                }
            }
        }



    }


    @Override
    public void deleteUserPayConfig(String userNo) throws PayBizException{

        RpUserPayConfig rpUserPayConfig = rpUserPayConfigDao.getByUserNo(userNo, null);
        if(rpUserPayConfig == null){
            throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_NOT_EXIST,"用户支付配置不存在");
        }

        rpUserPayConfig.setStatus(PublicStatusEnum.UNACTIVE.name());
        rpUserPayConfig.setEditTime(new Date());
        updateData(rpUserPayConfig);
    }


    @Override
    public void updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType,
                                    String isAutoSett, String appId, String merchantId, String partnerKey,
                                    String ali_partner, String ali_sellerId, String ali_key, String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey)  throws PayBizException{

        updateUserPayConfig( userNo,  productCode,  productName,  riskDay,  fundIntoType,
                isAutoSett,  appId,  merchantId,  partnerKey,
                ali_partner,  ali_sellerId,  ali_key,  ali_appid,  ali_rsaPrivateKey,  ali_rsaPublicKey  ,  null ,  null);
    }

    @Override
    public void updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType,
                                    String isAutoSett, String appId, String merchantId, String partnerKey,
                                    String ali_partner, String ali_sellerId, String ali_key, String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey  , String securityRating , String merchantServerIp)  throws PayBizException{
        RpUserPayConfig rpUserPayConfig = rpUserPayConfigDao.getByUserNo(userNo, null);
        if(rpUserPayConfig == null){
            throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_NOT_EXIST,"用户支付配置不存在");
        }

        rpUserPayConfig.setProductCode(productCode);
        rpUserPayConfig.setProductName(productName);
        rpUserPayConfig.setRiskDay(riskDay);
        rpUserPayConfig.setFundIntoType(fundIntoType);
        rpUserPayConfig.setIsAutoSett(isAutoSett);
        rpUserPayConfig.setEditTime(new Date());
        rpUserPayConfig.setSecurityRating(securityRating);//安全等级
        rpUserPayConfig.setMerchantServerIp(merchantServerIp);
        updateData(rpUserPayConfig);

        //查询支付产品下有哪些支付方式
        List<RpPayWay> payWayList = rpPayWayService.listByProductCode(productCode);
        Map<String, String> map = new HashMap<String, String>();
        //过滤重复数据
        for(RpPayWay payWay : payWayList){
            map.put(payWay.getPayWayCode(), payWay.getPayWayName());
        }

        for (String key : map.keySet()) {
            if(key.equals(PayWayEnum.WEIXIN.name())){
                //创建用户第三方支付信息
                RpUserPayInfo rpUserPayInfo = rpUserPayInfoService.getByUserNo(userNo, PayWayEnum.WEIXIN.name());
                if(rpUserPayInfo == null){
                    rpUserPayInfo = new RpUserPayInfo();
                    rpUserPayInfo.setId(StringUtil.get32UUID());
                    rpUserPayInfo.setCreateTime(new Date());
                    rpUserPayInfo.setAppId(appId);
                    rpUserPayInfo.setMerchantId(merchantId);
                    rpUserPayInfo.setPartnerKey(partnerKey);
                    rpUserPayInfo.setPayWayCode(PayWayEnum.WEIXIN.name());
                    rpUserPayInfo.setPayWayName(PayWayEnum.WEIXIN.getDesc());
                    rpUserPayInfo.setUserNo(userNo);
                    rpUserPayInfo.setUserName(rpUserPayConfig.getUserName());
                    rpUserPayInfo.setStatus(PublicStatusEnum.ACTIVE.name());
                    rpUserPayInfoService.saveData(rpUserPayInfo);
                }else{
                    rpUserPayInfo.setEditTime(new Date());
                    rpUserPayInfo.setAppId(appId);
                    rpUserPayInfo.setMerchantId(merchantId);
                    rpUserPayInfo.setPartnerKey(partnerKey);
                    rpUserPayInfo.setPayWayCode(PayWayEnum.WEIXIN.name());
                    rpUserPayInfo.setPayWayName(PayWayEnum.WEIXIN.getDesc());
                    rpUserPayInfoService.updateData(rpUserPayInfo);
                }

            }else if(key.equals(PayWayEnum.ALIPAY.name())){
                //创建用户第三方支付信息
                RpUserPayInfo rpUserPayInfo = rpUserPayInfoService.getByUserNo(userNo, PayWayEnum.ALIPAY.name());
                if(rpUserPayInfo == null){
                    rpUserPayInfo = new RpUserPayInfo();
                    rpUserPayInfo.setId(StringUtil.get32UUID());
                    rpUserPayInfo.setCreateTime(new Date());
                    rpUserPayInfo.setAppId(ali_partner);
                    rpUserPayInfo.setMerchantId(ali_sellerId);
                    rpUserPayInfo.setPartnerKey(ali_key);
                    rpUserPayInfo.setPayWayCode(PayWayEnum.ALIPAY.name());
                    rpUserPayInfo.setPayWayName(PayWayEnum.ALIPAY.getDesc());
                    rpUserPayInfo.setUserNo(userNo);
                    rpUserPayInfo.setUserName(rpUserPayConfig.getUserName());
                    rpUserPayInfo.setStatus(PublicStatusEnum.ACTIVE.name());
                    rpUserPayInfo.setOfflineAppId(ali_appid);
                    rpUserPayInfo.setRsaPrivateKey(ali_rsaPrivateKey);
                    rpUserPayInfo.setRsaPublicKey(ali_rsaPublicKey);
                    rpUserPayInfoService.saveData(rpUserPayInfo);
                }else{
                    rpUserPayInfo.setEditTime(new Date());
                    rpUserPayInfo.setAppId(ali_partner);
                    rpUserPayInfo.setMerchantId(ali_sellerId);
                    rpUserPayInfo.setPartnerKey(ali_key);
                    rpUserPayInfo.setPayWayCode(PayWayEnum.ALIPAY.name());
                    rpUserPayInfo.setPayWayName(PayWayEnum.ALIPAY.getDesc());
                    rpUserPayInfo.setOfflineAppId(ali_appid);
                    rpUserPayInfo.setRsaPrivateKey(ali_rsaPrivateKey);
                    rpUserPayInfo.setRsaPublicKey(ali_rsaPublicKey);
                    rpUserPayInfoService.updateData(rpUserPayInfo);
                }
            }
        }
    }


    @Override
    public void audit(String userNo, String auditStatus){
        RpUserPayConfig rpUserPayConfig = getByUserNo(userNo, null);
        if(rpUserPayConfig == null){
            throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_NOT_EXIST,"支付配置不存在！");
        }

        if(auditStatus.equals(PublicEnum.YES.name())){
            //检查是否已关联生效的支付产品
            RpPayProduct rpPayProduct = rpPayProductService.getByProductCode(rpUserPayConfig.getProductCode(), PublicEnum.YES.name());
            if(rpPayProduct == null){
                throw new PayBizException(PayBizException.PAY_PRODUCT_IS_NOT_EXIST,"未关联已生效的支付产品，无法操作！");
            }

            //检查是否已设置第三方支付信息
        }
        rpUserPayConfig.setAuditStatus(auditStatus);
        rpUserPayConfig.setEditTime(new Date());
        updateData(rpUserPayConfig);
    }


    @Override
    public RpUserPayConfig getByPayKey(String payKey){
        Map<String , Object> paramMap = new HashMap<String , Object>();
        paramMap.put("payKey", payKey);
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        paramMap.put("auditStatus", PublicEnum.YES.name());
        return rpUserPayConfigDao.getBy(paramMap);
    }
}
