package com.csx.pay.permission.entity;

/**
 * @author csx
 * @Package com.csx.pay.permission.entity
 * @Description: 权限管理-角色
 * @date 2018/6/5 0005
 */
public class PmsRole extends PermissionBaseEntity{
    private static final long serialVersionUID = -1850274607153125161L;
    /**角色编码：例如：admin*/
    private String roleCode;
    /**角色名称*/
    private String roleName;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 角色名称
     *
     * @return
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名称
     *
     * @return
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public PmsRole() {

    }
}
