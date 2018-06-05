package com.csx.pay.permission.dao.impl;

import com.csx.pay.permission.dao.PmsOperatorRoleDao;
import com.csx.pay.permission.entity.PmsOperatorRole;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao.impl
 * @Description: 操作员与角色dao实现
 * @date 2018/6/5 0005
 */
@Repository
public class PmsOperatorRoleDaoImpl extends PermissionBaseDaoImpl<PmsOperatorRole> implements PmsOperatorRoleDao {
    @Override
    public List<PmsOperatorRole> listByOperatorId(Long operatorId) {
        return super.getSqlSession().selectList(getStatement("listByOperatorId"),operatorId);
    }

    @Override
    public List<PmsOperatorRole> listByRoleId(Long roleId) {
        return super.getSqlSession().selectList(getStatement("listByRoleId"),roleId);
    }

    @Override
    public void deleteByOperatorId(Long operatorId) {
        super.getSqlSession().delete(getStatement("deleteByOperatorId"),operatorId);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        super.getSqlSession().delete(getStatement("deleteByRoleId"),roleId);
    }

    @Override
    public void deleteByRoleIdAndOperatorId(Long roleId, Long operatorId) {
        Map<String,Object> params=new HashMap<>(2);
        params.put("roleId",roleId);
        params.put("operatorId",operatorId);
        super.getSqlSession().delete(getStatement("deleteByRoleIdAndOperatorId"),params);
    }
}
