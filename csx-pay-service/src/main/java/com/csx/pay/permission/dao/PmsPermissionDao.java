package com.csx.pay.permission.dao;

import com.csx.pay.permission.entity.PmsPermission;

import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao
 * @Description: 权限点dao
 * @date 2018/6/5 0005
 */
public interface PmsPermissionDao extends PermissionBaseDao<PmsPermission> {
	/**
	 * 根据实体ID集字符串获取对象列表
	 *
	 * @param ids
	 * @return
	 */
	List<PmsPermission> findByIds(String ids);

	/**
	 * 检查权限名称是否已存在
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
	 * 获取叶子菜单下所有的功能权限
	 * 
	 * @param menuId
	 * @return
	 */
	List<PmsPermission> listAllByMenuId(Long menuId);

}
