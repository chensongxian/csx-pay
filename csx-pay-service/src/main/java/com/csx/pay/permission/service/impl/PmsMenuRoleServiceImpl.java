package com.csx.pay.permission.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.csx.pay.permission.dao.PmsMenuRoleDao;
import com.csx.pay.permission.entity.PmsMenuRole;
import com.csx.pay.permission.service.PmsMenuRoleService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author csx
 * @Package com.csx.pay.permission.service.impl
 * @Description: 菜单角色service实现类
 * @date 2018/6/5 0005
 */
@Service("pmsMenuRoleService")
public class PmsMenuRoleServiceImpl implements PmsMenuRoleService {
    @Autowired
    private PmsMenuRoleDao pmsMenuRoleDao;
    @Override
    public int countMenuByRoleId(Long roleId) {
        List<PmsMenuRole> menuRoles = pmsMenuRoleDao.listByRoleId(roleId);
        if(menuRoles==null||menuRoles.isEmpty()){
            return 0;
        }else{
            return menuRoles.size();
        }
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        pmsMenuRoleDao.deleteByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRoleMenu(Long roleId, String roleMenuStr) {
        // 删除原来的角色与权限关联
        pmsMenuRoleDao.deleteByRoleId(roleId);
        if (!StringUtils.isEmpty(roleMenuStr)) {
            // 创建新的关联
            String[] menuIds = roleMenuStr.split(",");
            for (int i = 0; i < menuIds.length; i++) {
                Long menuId = Long.valueOf(menuIds[i]);
                PmsMenuRole item = new PmsMenuRole();
                item.setMenuId(menuId);
                item.setRoleId(roleId);
                pmsMenuRoleDao.insert(item);
            }
        }
    }
}
