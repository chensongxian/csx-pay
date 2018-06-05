package com.csx.pay.permission.service;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.permission.entity.PmsRole;

import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.permission.service
 * @Description: 角色service
 * @date 2018/6/5 0005
 */
public interface PmsRoleService {
    /**
     * 创建pmsRole
     * @param pmsRole
     */
    void saveData(PmsRole pmsRole);

    /**
     * 修改pmsRole
     * @param pmsRole
     */
    void updateData(PmsRole pmsRole);

    /**
     * 根据id获取数据pmsRole
     *
     * @param id
     * @return
     */
    PmsRole getDataById(Long id);

    /**
     * 分页查询pmsRole
     * @param pageParam
     * @param pmsRole
     * @return
     */
    PageBean listPage(PageParam pageParam, PmsRole pmsRole);

    /**
     * 获取所有角色列表，以供添加操作员时选择
     * @return
     */
    List<PmsRole> listAllRole();

    /**
     * 判断此权限是否关联有角色
     *
     * @param permissionId
     * @return
     */
    List<PmsRole> listByPermissionId(Long permissionId);

    /**
     * 根据角色名或者角色编号查询角色
     *
     * @param roleName
     * @param roleCode
     * @return
     */
    PmsRole getByRoleNameOrRoleCode(String roleName, String roleCode);

    /**
     * 删除
     *
     * @param roleId
     */
    void delete(Long roleId);
}
