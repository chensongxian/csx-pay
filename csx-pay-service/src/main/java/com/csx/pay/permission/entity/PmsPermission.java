package com.csx.pay.permission.entity;

/**
 * @author csx
 * @Package com.csx.pay.permission.entity
 * @Description: 权限管理-权限实体表
 * @date 2018/6/5 0005
 */
public class PmsPermission extends PermissionBaseEntity{
    private static final long serialVersionUID = -2175597348886393330L;
    /**权限名称*/
    private String permissionName;
    /**权限标识*/
    private String permission;

    /**
     * 权限名称
     *
     * @return
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * 权限名称
     *
     * @return
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * 权限标识
     *
     * @return
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 权限标识
     *
     * @return
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }
}
