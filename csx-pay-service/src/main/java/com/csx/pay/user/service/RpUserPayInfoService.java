package com.csx.pay.user.service;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.user.entity.RpUserPayInfo;

/**
 * @author csx
 * @Package com.csx.pay.user.service
 * @Description: 用户第三方支付信息service接口
 * @date 2018/6/4 0004
 */
public interface RpUserPayInfoService {
    /**
     * 保存
     */
    void saveData(RpUserPayInfo rpUserPayInfo);

    /**
     * 更新
     */
    void updateData(RpUserPayInfo rpUserPayInfo);

    /**
     * 根据id获取数据
     *
     * @param id
     * @return
     */
    RpUserPayInfo getDataById(String id);


    /**
     * 获取分页数据
     *
     * @param pageParam
     * @return
     */
    PageBean listPage(PageParam pageParam, RpUserPayInfo rpUserPayInfo);

    /**
     * 通过商户编号获取商户支付配置信息
     * @param userNo
     * @param payWayCode
     * @return
     */
    public RpUserPayInfo getByUserNo(String userNo, String payWayCode);
}
