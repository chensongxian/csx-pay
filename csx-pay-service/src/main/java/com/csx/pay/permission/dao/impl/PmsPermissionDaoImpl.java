package com.csx.pay.permission.dao.impl;

import com.csx.pay.permission.dao.PmsPermissionDao;
import com.csx.pay.permission.entity.PmsPermission;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao.impl
 * @Description: 权限功能点dao实现
 * @date 2018/6/5 0005
 */
@Repository
public class PmsPermissionDaoImpl extends PermissionBaseDaoImpl<PmsPermission> implements PmsPermissionDao {
    @Override
    public List<PmsPermission> findByIds(String idsStr) {
        List<String> ids=Arrays.asList(idsStr.split(","));
        return super.getSqlSession().selectList(getStatement("findByIds"),ids);
    }

    @Override
    public PmsPermission getByPermissionName(String permissionName) {
        return super.getSqlSession().selectOne(getStatement("getByPermissionName"),permissionName);
    }

    @Override
    public PmsPermission getByPermission(String permission) {
        return super.getSqlSession().selectOne(getStatement("getByPermission"),permission);
    }

    @Override
    public PmsPermission getByPermissionNameNotEqId(String permissionName, Long id) {
        Map<String,Object> params=new HashMap<>(2);
        params.put("permissionName",permissionName);
        params.put("id",id);
        return super.getSqlSession().selectOne(getStatement("getByPermissionNameNotEqId"),params);
    }

    @Override
    public List<PmsPermission> listAllByMenuId(Long menuId) {
        Map<String,Object> params=new HashMap<>(1);
        params.put("menuId",menuId);
        return super.getSqlSession().selectOne(getStatement("listAllByMenuId"),params);
    }
}
