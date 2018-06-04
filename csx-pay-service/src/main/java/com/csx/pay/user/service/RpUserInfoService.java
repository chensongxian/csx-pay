package com.csx.pay.user.service;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.user.entity.RpUserInfo;

import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.user.service
 * @Description: 用户信息service接口
 * @date 2018/6/4 0004
 */
public interface RpUserInfoService {
    /**
     * 保存
     */
    void saveData(RpUserInfo rpUserInfo);

    /**
     * 更新
     */
    void updateData(RpUserInfo rpUserInfo);

    /**
     * 根据id获取数据
     *
     * @param id
     * @return
     */
    RpUserInfo getDataById(String id);


    /**
     * 获取分页数据
     *
     * @param pageParam
     * @return
     */
    PageBean listPage(PageParam pageParam, RpUserInfo rpUserInfo);

    /**
     * 用户线下注册
     *
     * @param userName
     *            用户名
     * @param mobile
     *            手机号
     * @param password
     *            密码
     * @return
     */
    void registerOffline(String userName, String mobile, String password) ;

    /**
     * 根据商户编号获取商户信息
     * @param merchantNo
     * @return
     */
    RpUserInfo getDataByMerchentNo(String merchantNo);

    /**
     * 根据手机号获取商户信息
     * @param mobile
     * @return
     */
    RpUserInfo getDataByMobile(String mobile);

    /**
     * 获取所有用户
     * @return
     */
    List<RpUserInfo> listAll();
}
