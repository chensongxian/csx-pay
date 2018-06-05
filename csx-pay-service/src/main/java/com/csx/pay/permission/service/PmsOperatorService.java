package com.csx.pay.permission.service;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.permission.entity.PmsOperator;

/**
 * @author csx
 * @Package com.csx.pay.permission.service
 * @Description: 操作员service接口
 * @date 2018/6/5 0005
 */
public interface PmsOperatorService {
    /**
     * 创建pmsOperator
     * @param pmsOperator
     */
    void saveData(PmsOperator pmsOperator);

    /**
     * 修改pmsOperator
     * @param pmsOperator
     */
    void updateData(PmsOperator pmsOperator);

    /**
     * 根据id获取数据pmsOperator
     * @param id
     * @return
     */
    PmsOperator getDataById(Long id);

    /**
     * 根据登录名取得操作员对象
     * @param loginName
     * @return
     */
    PmsOperator findOperatorByLoginName(String loginName);

    /**
     * 分页查询pmsOperator
     * @param pageParam
     * @param pmsOperator
     * @return
     */
    PageBean listPage(PageParam pageParam, PmsOperator pmsOperator);

    /**
     * 根据ID删除一个操作员，同时删除与该操作员关联的角色关联信息. type="admin"的超级管理员不能删除
     * @param operatorId
     */
    void deleteOperatorById(Long operatorId);

    /**
     * 根据操作员ID更新操作员密码
     * @param operatorId
     * @param newPwd (SHA1加密)
     */
    void updateOperatorPwd(Long operatorId, String newPwd);

    /**
     * 保存操作員信息及其关联的角色
     * @param pmsOperator
     * @param roleOperatorStr
     */
    void saveOperator(PmsOperator pmsOperator, String roleOperatorStr);

    /**
     * 修改操作員信息及其关联的角色
     * @param pmsOperator
     * @param roleOperatorStr
     */
    void updateOperator(PmsOperator pmsOperator, String roleOperatorStr);
}
