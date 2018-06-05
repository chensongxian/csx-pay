package com.csx.pay.permission.dao.impl;

import com.csx.pay.permission.dao.PmsRolePermissionDao;
import com.csx.pay.permission.entity.PmsRolePermission;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao.impl
 * @Description: TODO
 * @date 2018/6/5 0005
 */
@Repository
public class PmsRolePermissionDaoImpl extends PermissionBaseDaoImpl<PmsRolePermission> implements PmsRolePermissionDao {
    @Override
    public List<PmsRolePermission> listByRoleId(long roleId) {
        return super.getSqlSession().selectList(getStatement("listByRoleId"),roleId);
    }

    @Override
    public List<PmsRolePermission> listByRoleIds(String roleIdsStr) {
        List<String> roleIds=Arrays.asList(roleIdsStr.split(","));
        return super.getSqlSession().selectList(getStatement("listByRoleIds"),roleIds);
    }

    @Override
    public void deleteByRoleIdAndPermissionId(Long roleId, Long permissionId) {
        Map<String,Object> params=new HashMap<>(2);
        params.put("roleId",roleId);
        params.put("permissionId",permissionId);
        super.getSqlSession().delete(getStatement("deleteByRoleIdAndPermissionId"),params);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        super.getSqlSession().delete(getStatement("deleteByRoleId"),roleId);
    }
}
