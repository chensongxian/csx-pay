package com.csx.pay.permission.service;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.permission.entity.PmsRolePermission;

import java.util.Set;

/**
 * @author csx
 * @Package com.csx.pay.permission.service
 * @Description: 角色权限service
 * @date 2018/6/5 0005
 */
public interface PmsRolePermissionService {
    /**
     * 根据操作员ID，获取所有的功能权限集
     * @param operatorId
     * @return
     */
    Set<String> getPermissionsByOperatorId(Long operatorId);

    /**
     * 创建pmsRolePermission
     * @param pmsRolePermission
     */
    void saveData(PmsRolePermission pmsRolePermission);

    /**
     * 修改pmsRolePermission
     * @param pmsRolePermission
     */
    void updateData(PmsRolePermission pmsRolePermission);

    /**
     * 根据id获取数据pmsRolePermission
     *
     * @param id
     * @return
     */
    PmsRolePermission getDataById(Long id);

    /**
     * 分页查询pmsRolePermission
     *
     * @param pageParam
     * @param pmsRolePermission
     * @return
     */
    PageBean listPage(PageParam pageParam, PmsRolePermission pmsRolePermission);

    /**
     * 保存角色和权限之间的关联关系
     * @param roleId
     * @param rolePermissionStr
     */
    void saveRolePermission(Long roleId, String rolePermissionStr);
}
