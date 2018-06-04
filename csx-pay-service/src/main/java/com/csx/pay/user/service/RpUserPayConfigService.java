package com.csx.pay.user.service;

import com.csx.pay.common.core.entity.BaseEntity;
import com.csx.pay.common.core.enums.SecurityRatingEnum;
import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.user.entity.RpUserPayConfig;
import com.csx.pay.user.exception.PayBizException;

import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.user.service
 * @Description: 用户支付配置service接口
 * @date 2018/6/4 0004
 */
public interface RpUserPayConfigService {
    /**
     * 保存
     */
    void saveData(RpUserPayConfig rpUserPayConfig);

    /**
     * 更新
     */
    void updateData(RpUserPayConfig rpUserPayConfig);

    /**
     * 根据id获取数据
     *
     * @param id
     * @return
     */
    RpUserPayConfig getDataById(String id);


    /**
     * 获取分页数据
     *
     * @param pageParam
     * @return
     */
    PageBean listPage(PageParam pageParam, RpUserPayConfig rpUserPayConfig);

    /**
     * 根据商户编号获取已生效的支付配置
     * @param userNo
     * @return
     */
    RpUserPayConfig getByUserNo(String userNo);

    /**
     * 根据商户编号获取支付配置
     * @param userNo
     * @param auditStatus
     * @return
     */
    RpUserPayConfig getByUserNo(String userNo, String auditStatus);

    /**
     * 根据支付产品获取已生效数据
     */
    List<RpUserPayConfig> listByProductCode(String productCode);

    /**
     * 根据支付产品获取数据
     */
    List<RpUserPayConfig> listByProductCode(String productCode, String auditStatus);

    /**
     * 创建用户支付配置
     */
    void createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay, String fundIntoType,
                             String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key,
                             String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey)  throws PayBizException;

    /**
     * 创建用户支付配置
     */
    void createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay, String fundIntoType,
                             String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key,
                             String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey, String securityRating , String merchantServerIp)  throws PayBizException;

    /**
     * 删除支付产品
     * @param userNo
     */
    void deleteUserPayConfig(String userNo) throws PayBizException;

    /**
     * 修改用户支付配置
     */
    void updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType,
                             String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key,
                             String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey)  throws PayBizException;

    /**
     * 修改用户支付配置
     */
    void updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType,
                             String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key,
                             String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey, String securityRating , String merchantServerIp)  throws PayBizException;

    /**
     * 审核
     * @param userNo
     * @param auditStatus
     */
    void audit(String userNo, String auditStatus);

    /**
     * 根据商户key获取已生效的支付配置
     * @param payKey
     * @return
     */
    RpUserPayConfig getByPayKey(String payKey);

}
