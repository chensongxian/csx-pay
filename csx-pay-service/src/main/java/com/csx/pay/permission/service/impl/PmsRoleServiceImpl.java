package com.csx.pay.permission.service.impl;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.permission.dao.PmsRoleDao;
import com.csx.pay.permission.entity.PmsRole;
import com.csx.pay.permission.service.PmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.permission.service.impl
 * @Description: 角色service实现类
 * @date 2018/6/5 0005
 */
@Service("pmsRoleService")
public class PmsRoleServiceImpl implements PmsRoleService {
    @Autowired
    private PmsRoleDao pmsRoleDao;

    @Override
    public void saveData(PmsRole pmsRole) {
        pmsRoleDao.insert(pmsRole);
    }

    @Override
    public void updateData(PmsRole pmsRole) {
        pmsRoleDao.update(pmsRole);
    }

    @Override
    public PmsRole getDataById(Long id) {
        return pmsRoleDao.getById(id);
    }

    @Override
    public PageBean listPage(PageParam pageParam, PmsRole pmsRole) {
        Map<String,Object> params=new HashMap<>(1);
        params.put("roleName",pmsRole.getRoleName());
        return pmsRoleDao.listPage(pageParam,params);
    }

    @Override
    public List<PmsRole> listAllRole() {
        return pmsRoleDao.listAll();
    }

    @Override
    public List<PmsRole> listByPermissionId(Long permissionId) {
        return pmsRoleDao.listByPermissionId(permissionId);
    }

    @Override
    public PmsRole getByRoleNameOrRoleCode(String roleName, String roleCode) {
        return pmsRoleDao.getByRoleNameOrRoleCode(roleName,roleCode);
    }

    @Override
    public void delete(Long roleId) {
        pmsRoleDao.delete(roleId);
    }
}
