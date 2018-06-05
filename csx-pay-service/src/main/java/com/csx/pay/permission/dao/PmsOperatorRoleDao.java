package com.csx.pay.permission.dao;


import com.csx.pay.permission.entity.PmsOperatorRole;

import java.util.List;


/**
 * @author csx
 * @Package com.csx.pay.permission.dao
 * @Description: 操作员与角色dao
 * @date 2018/6/5 0005
 */
public interface PmsOperatorRoleDao extends PermissionBaseDao<PmsOperatorRole> {

	/**
	 * 根据操作员ID查找该操作员关联的角色
	 * @param operatorId
	 * @return
	 */
	List<PmsOperatorRole> listByOperatorId(Long operatorId);

	/**
	 * 根据角色ID查找该操作员关联的操作员
	 * @param roleId
	 * @return
	 */
	List<PmsOperatorRole> listByRoleId(Long roleId);

	/**
	 * 根据操作员ID删除与角色的关联记录
	 * @param operatorId
	 */
	void deleteByOperatorId(Long operatorId);

	/**
	 * 根据角色ID删除操作员与角色的关联关系
	 * @param roleId
	 */
	void deleteByRoleId(Long roleId);

	/**
	 * 根据角色ID和操作员ID删除关联数据(用于更新操作员的角色)
	 * @param roleId
	 * @param operatorId
	 */
	void deleteByRoleIdAndOperatorId(Long roleId, Long operatorId);

}
