package com.csx.pay.permission.dao;

import com.csx.pay.permission.entity.PmsMenu;

import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao
 * @Description: 权限菜单
 * @date 2018/6/5 0005
 */
public interface PmsMenuDao extends PermissionBaseDao<PmsMenu>{
    /**
     * 根据角色id查找菜单列表
     * @param roleIdsStr
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List listByRoleIds(String roleIdsStr);

    /**
     * 根据父菜单ID获取该菜单下的所有子孙菜单
     * @param parentId
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List listByParent(Long parentId);

    /**
     * 根据菜单ID查找菜单（可用于判断菜单下是否还有子菜单）
     * @param parentId
     * @return
     */
    public List<PmsMenu> listByParentId(Long parentId);

    /**
     * 根据名称和是否叶子节点查询数据
     * @param map
     * @return
     */
    public List<PmsMenu> getMenuByNameAndIsLeaf(Map<String, Object> map);
}
