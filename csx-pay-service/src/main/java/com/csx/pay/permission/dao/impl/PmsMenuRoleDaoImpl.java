package com.csx.pay.permission.dao.impl;

import com.csx.pay.permission.dao.PmsMenuRoleDao;
import com.csx.pay.permission.entity.PmsMenuRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao.impl
 * @Description: 菜单角色
 * @date 2018/6/5 0005
 */
@Repository("pmsMenuRoleDao")
public class PmsMenuRoleDaoImpl extends PermissionBaseDaoImpl<PmsMenuRole> implements PmsMenuRoleDao {
    @Override
    public void deleteByRoleId(Long roleId) {
        super.getSqlSession().delete(getStatement("deleteByRoleId"),roleId);
    }

    @Override
    public List<PmsMenuRole> listByRoleId(Long roleId) {
        return super.getSqlSession().selectList(getStatement("listByRoleId"),roleId);
    }
}
