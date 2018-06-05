package com.csx.pay.permission.dao;


import com.csx.pay.permission.entity.PmsOperator;

import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao
 * @Description: 操作员dao
 * @date 2018/6/5 0005
 */
public interface PmsOperatorDao extends PermissionBaseDao<PmsOperator> {

	/**
	 * 根据操作员登录名获取操作员信息
	 * @param loginName
	 * @return
	 */
	PmsOperator findByLoginName(String loginName);

	/**
	 * 根据角色ID找到操作员列表
	 * @param roleId
	 * @return
	 */
	List<PmsOperator> listByRoleId(Long roleId);
}
