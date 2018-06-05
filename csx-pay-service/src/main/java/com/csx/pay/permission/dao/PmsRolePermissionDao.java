package com.csx.pay.permission.dao;

import com.csx.pay.permission.entity.PmsRolePermission;

import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao
 * @Description: 权限角色dao
 * @date 2018/6/5 0005
 */
public interface PmsRolePermissionDao extends PermissionBaseDao<PmsRolePermission> {

	/**
	 * 根据角色ID找到角色关联的权限点.
	 * 
	 * @param roleId
	 *            .
	 * @return rolePermissionList .
	 */
	public List<PmsRolePermission> listByRoleId(final long roleId);

	/**
	 * 根据角色ID字符串获取相应角色-权限关联信息.
	 * 
	 * @param roleIdsStr
	 * @return
	 */
	public List<PmsRolePermission> listByRoleIds(String roleIdsStr);

	public void deleteByRoleIdAndPermissionId(Long roleId, Long permissionId);
	
	public void deleteByRoleId(Long roleId);
}
