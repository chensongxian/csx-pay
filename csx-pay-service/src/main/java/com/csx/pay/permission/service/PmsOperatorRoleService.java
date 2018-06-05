package com.csx.pay.permission.service;

import com.csx.pay.permission.entity.PmsOperator;
import com.csx.pay.permission.entity.PmsOperatorRole;

import java.util.List;
import java.util.Set;

/**
 * @author csx
 * @Package com.csx.pay.permission.service
 * @Description: 操作员角色service接口
 * @date 2018/6/5 0005
 */
public interface PmsOperatorRoleService {
    /**
     * 根据操作员ID获得该操作员的所有角色id所拼成的String，每个ID用“,”分隔
     *
     * @param operatorId
     *            操作员ID
     * @return roleIds
     */
    String getRoleIdsByOperatorId(Long operatorId);

    /**
     * 根据操作员id，获取该操作员所有角色的编码集合
     *
     * @param operatorId
     * @return
     */
    Set<String> getRoleCodeByOperatorId(Long operatorId);

    /**
     * 根据角色ID查询用户
     *
     * @param roleId
     * @return
     */
    List<PmsOperator> listOperatorByRoleId(Long roleId);

    /**
     * 保存操作員信息及其关联的角色.
     *
     * @param pmsOperator
     *            .
     * @param roleOperatorStr
     *            .
     */
    void saveOperator(PmsOperator pmsOperator, String roleOperatorStr);

    /**
     * 修改操作員信息及其关联的角色.
     *
     * @param pmsOperator
     *            .
     * @param roleOperatorStr
     *            .
     */
    void updateOperator(PmsOperator pmsOperator, String roleOperatorStr);

    /**
     * 根据角色ID统计有多少个操作员关联到此角色.
     *
     * @param roleId
     *            .
     * @return count.
     */
    int countOperatorByRoleId(Long roleId);

    /**
     * 根据操作员ID获得所有操作员－角色关联列表
     */
    List<PmsOperatorRole> listOperatorRoleByOperatorId(Long operatorId);
}
