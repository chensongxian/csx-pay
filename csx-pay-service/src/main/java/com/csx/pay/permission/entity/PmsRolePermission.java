package com.csx.pay.permission.entity;

/**
 * @author csx
 * @Package com.csx.pay.permission.entity
 * @Description: 权限管理-角色权限关联表
 * @date 2018/6/5 0005
 */
public class PmsRolePermission extends PermissionBaseEntity{
    private static final long serialVersionUID = -9012707031072904356L;
    /**角色ID*/
    private Long roleId;
    /**权限ID*/
    private Long permissionId;

    /**
     * 角色ID
     *
     * @return
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 角色ID
     *
     * @return
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 权限ID
     *
     * @return
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 权限ID
     *
     * @return
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public PmsRolePermission() {
        super();
    }

}
