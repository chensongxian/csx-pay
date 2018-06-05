package com.csx.pay.permission.entity;

/**
 * @author csx
 * @Package com.csx.pay.permission.entity
 * @Description: 权限管理-角色,操作员关联表
 * @date 2018/6/5 0005
 */
public class PmsOperatorRole extends PermissionBaseEntity{
    private static final long serialVersionUID = 174356028197753020L;
    /**角色ID*/
    private Long roleId;
    /**操作员ID*/
    private Long operatorId;

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
     * 操作员ID
     *
     * @return
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * 操作员ID
     *
     * @return
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public PmsOperatorRole() {

    }
}
