package com.csx.pay.permission.entity;

/**
 * @author csx
 * @Package com.csx.pay.permission.entity
 * @Description: 权限管理-角色权限关联表
 * @date 2018/6/4 0004
 */
public class PmsMenuRole extends PermissionBaseEntity{
    private static final long serialVersionUID = -9012707031072904356L;

    /** 角色ID **/
    private Long roleId;

    /** 菜单ID **/
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
