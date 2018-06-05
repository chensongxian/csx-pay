package com.csx.pay.permission.service;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.permission.entity.PmsPermission;

import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.permission.service
 * @Description: 权限service接口
 * @date 2018/6/5 0005
 */
public interface PmsPermissionService {
    /**
     * 创建pmsPermission
     * @param pmsPermission
     */
    void saveData(PmsPermission pmsPermission);

    /**
     * 修改pmsPermission
     * @param pmsPermission
     */
    void updateData(PmsPermission pmsPermission);

    /**
     * 根据id获取数据pmsPermission
     * @param id
     * @return
     */
    PmsPermission getDataById(Long id);

    /**
     * 分页查询pmsPermission
     *
     * @param pageParam
     * @param pmsPermission
     * @return
     */
    PageBean listPage(PageParam pageParam, PmsPermission pmsPermission);

    /**
     * 检查权限名称是否已存在
     *
     * @param permissionName
     * @return
     */
    PmsPermission getByPermissionName(String permissionName);

    /**
     * 检查权限是否已存在
     *
     * @param permission
     * @return
     */
    PmsPermission getByPermission(String permission);

    /**
     * 检查权限名称是否已存在(其他id)
     *
     * @param permissionName
     * @param id
     * @return
     */
    PmsPermission getByPermissionNameNotEqId(String permissionName, Long id);

    /**
     * 删除
     *
     * @param permissionId
     */
    void delete(Long permissionId);

    /**
     * 根据角色查找角色对应的功能权限ID集
     *
     * @param roleId
     * @return
     */
    String getPermissionIdsByRoleId(Long roleId);

    /**
     * 查询所有的权限
     * @return
     */
    List<PmsPermission> listAll();
}
