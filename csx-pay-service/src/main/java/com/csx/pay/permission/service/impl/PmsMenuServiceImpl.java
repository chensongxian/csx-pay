package com.csx.pay.permission.service.impl;

import com.csx.pay.permission.dao.PmsMenuDao;
import com.csx.pay.permission.dao.PmsMenuRoleDao;
import com.csx.pay.permission.entity.PmsMenu;
import com.csx.pay.permission.entity.PmsMenuRole;
import com.csx.pay.permission.service.PmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.permission.service.impl
 * @Description: 菜单service实现类
 * @date 2018/6/5 0005
 */
@Service("pmsMenuService")
public class PmsMenuServiceImpl implements PmsMenuService {
    @Autowired
    private PmsMenuDao pmsMenuDao;
    @Autowired
    private PmsMenuRoleDao pmsMenuRoleDao;

    @Override
    public void savaMenu(PmsMenu menu) {
        pmsMenuDao.insert(menu);
    }

    @Override
    public List getListByParent(Long parentId) {
        return pmsMenuDao.listByParent(parentId);
    }

    @Override
    public void delete(Long id) {
        pmsMenuDao.delete(id);
    }

    @Override
    public List listByRoleIds(String roleIdsStr) {
        return pmsMenuDao.listByRoleIds(roleIdsStr);
    }

    @Override
    public List<PmsMenu> listByParentId(Long parentId) {
        return pmsMenuDao.listByParentId(parentId);
    }

    @Override
    public List<PmsMenu> getMenuByNameAndIsLeaf(Map<String, Object> map) {
        return pmsMenuDao.getMenuByNameAndIsLeaf(map);
    }

    @Override
    public PmsMenu getById(Long pid) {
        return pmsMenuDao.getById(pid);
    }

    @Override
    public void update(PmsMenu menu) {
        pmsMenuDao.update(menu);
    }

    @Override
    public String getMenuIdsByRoleId(Long roleId) {
        List<PmsMenuRole> menuList = pmsMenuRoleDao.listByRoleId(roleId);
        StringBuffer menuIds = new StringBuffer("");
        if (menuList != null && !menuList.isEmpty()) {
            for (PmsMenuRole rm : menuList) {
                menuIds.append(rm.getMenuId()).append(",");
            }
        }
        return menuIds.toString();
    }
}
