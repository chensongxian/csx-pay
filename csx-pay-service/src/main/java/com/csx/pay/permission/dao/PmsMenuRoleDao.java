package com.csx.pay.permission.dao;


import com.csx.pay.permission.entity.PmsMenuRole;

import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao
 * @Description: 菜单角色关联表
 * @date 2018/6/5 0005
 */
public interface PmsMenuRoleDao extends PermissionBaseDao<PmsMenuRole> {

	/**
	 * 根据角色ID删除菜单与角色的关联记录
	 * @param roleId
	 */
	void deleteByRoleId(Long roleId);

	/**
	 * 根据角色ID统计关联到此角色的菜单数
	 * @param roleId
	 * @return
	 */
	List<PmsMenuRole> listByRoleId(Long roleId);
}