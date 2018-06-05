package com.csx.pay.permission.dao.impl;

import com.csx.pay.permission.dao.PmsRoleDao;
import com.csx.pay.permission.entity.PmsRole;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao.impl
 * @Description: 角色dao实现类
 * @date 2018/6/5 0005
 */
@Repository
public class PmsRoleDaoImpl extends PermissionBaseDaoImpl<PmsRole> implements PmsRoleDao {
    @Override
    public List<PmsRole> listAll() {
        return super.getSqlSession().selectList(getStatement("listAll"));
    }

    @Override
    public List<PmsRole> listByPermissionId(Long permissionId) {
        return super.getSqlSession().selectList(getStatement("listByPermissionId"),permissionId);
    }

    @Override
    public PmsRole getByRoleNameOrRoleCode(String roleName, String roleCode) {
        Map<String,Object> params=new HashMap<>(2);
        params.put("roleName",roleName);
        params.put("roleCode",roleCode);
        return super.getSqlSession().selectOne(getStatement("getByRoleNameOrRoleCode"),params);
    }
}
