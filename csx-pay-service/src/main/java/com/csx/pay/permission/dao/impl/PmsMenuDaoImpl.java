package com.csx.pay.permission.dao.impl;

import com.csx.pay.permission.dao.PmsMenuDao;
import com.csx.pay.permission.entity.PmsMenu;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao.impl
 * @Description: TODO
 * @date 2018/6/5 0005
 */
@Repository("pmsMenuDao")
public class PmsMenuDaoImpl extends PermissionBaseDaoImpl<PmsMenu> implements PmsMenuDao {
    @Override
    public List listByRoleIds(String roleIdsStr) {
        List<String> roldIds = Arrays.asList(roleIdsStr.split(","));
        return super.getSqlSession().selectList(getStatement("listByRoleIds"), roldIds);
    }

    @Override
    public List listByParent(Long parentId) {
        return super.getSqlSession().selectList(getStatement("listByParent"), parentId);
    }

    @Override
    public List<PmsMenu> listByParentId(Long parentId) {
        return super.getSqlSession().selectList(getStatement("listByParentId"), parentId);
    }

    @Override
    public List<PmsMenu> getMenuByNameAndIsLeaf(Map<String, Object> map) {
        return super.getSqlSession().selectList(getStatement("listBy"), map);
    }
}
