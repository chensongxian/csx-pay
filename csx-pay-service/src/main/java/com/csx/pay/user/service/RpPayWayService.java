package com.csx.pay.user.service;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.user.entity.RpPayWay;
import com.csx.pay.user.exception.PayBizException;

import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.user.service
 * @Description: 支付方式service接口
 * @date 2018/6/4 0004
 */
public interface RpPayWayService {
    /**
     * 保存
     */
    void saveData(RpPayWay rpPayWay);

    /**
     * 更新
     */
    void updateData(RpPayWay rpPayWay);

    /**
     * 根据id获取数据
     *
     * @param id
     * @return
     */
    RpPayWay getDataById(String id);

    /**
     * 根据支付方式、渠道编码获取数据
     * @param rpTypeCode
     * @return
     */
    RpPayWay getByPayWayTypeCode(String payProductCode, String payWayCode, String rpTypeCode);


    /**
     * 获取分页数据
     *
     * @param pageParam
     * @return
     */
    PageBean listPage(PageParam pageParam, RpPayWay rpPayWay);

    /**
     * 绑定支付费率
     * @param payWayCode
     * @param payTypeCode
     * @param payRate
     */
    void createPayWay(String payProductCode, String payWayCode, String payTypeCode, Double payRate)  throws PayBizException;

    /**
     * 根据支付产品获取支付方式
     * @param payProductCode
     */
    List<RpPayWay> listByProductCode(String payProductCode);

    /**
     * 获取所有支付方式
     */
    List<RpPayWay> listAll();
}
