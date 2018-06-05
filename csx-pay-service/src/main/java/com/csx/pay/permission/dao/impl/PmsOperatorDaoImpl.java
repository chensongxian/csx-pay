package com.csx.pay.permission.dao.impl;

import com.csx.pay.permission.dao.PmsOperatorDao;
import com.csx.pay.permission.entity.PmsOperator;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao.impl
 * @Description: 权限操作员dao实现
 * @date 2018/6/5 0005
 */
@Repository
public class PmsOperatorDaoImpl extends PermissionBaseDaoImpl<PmsOperator> implements PmsOperatorDao {
    @Override
    public PmsOperator findByLoginName(String loginName) {
        return super.getSqlSession().selectOne(getStatement("findByLoginName"),loginName);
    }

    @Override
    public List<PmsOperator> listByRoleId(Long roleId) {
        return super.getSqlSession().selectList(getStatement("listByRoleId"),roleId);
    }
}
