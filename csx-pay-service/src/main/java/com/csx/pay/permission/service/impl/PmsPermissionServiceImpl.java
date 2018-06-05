package com.csx.pay.permission.service.impl;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.permission.dao.PmsOperatorRoleDao;
import com.csx.pay.permission.dao.PmsPermissionDao;
import com.csx.pay.permission.dao.PmsRolePermissionDao;
import com.csx.pay.permission.entity.PmsOperatorRole;
import com.csx.pay.permission.entity.PmsPermission;
import com.csx.pay.permission.entity.PmsRolePermission;
import com.csx.pay.permission.exception.PermissionException;
import com.csx.pay.permission.service.PmsPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.permission.service.impl
 * @Description: 权限service接口实现
 * @date 2018/6/5 0005
 */
@Service("pmsPermissionService")
public class PmsPermissionServiceImpl implements PmsPermissionService {
    @Autowired
    private PmsPermissionDao pmsPermissionDao;
    @Autowired
    private PmsRolePermissionDao pmsRolePermissionDao;

    @Override
    public void saveData(PmsPermission pmsPermission) {
        pmsPermissionDao.insert(pmsPermission);
    }

    @Override
    public void updateData(PmsPermission pmsPermission) {
        pmsPermissionDao.update(pmsPermission);
    }

    @Override
    public PmsPermission getDataById(Long id) {
        return pmsPermissionDao.getById(id);
    }

    @Override
    public PageBean listPage(PageParam pageParam, PmsPermission pmsPermission) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("permissionName", pmsPermission.getPermissionName());
        paramMap.put("permission", pmsPermission.getPermission());
        return pmsPermissionDao.listPage(pageParam, paramMap);
    }

    @Override
    public PmsPermission getByPermissionName(String permissionName) {
        return pmsPermissionDao.getByPermissionName(permissionName);
    }

    @Override
    public PmsPermission getByPermission(String permission) {
        return pmsPermissionDao.getByPermission(permission);
    }

    @Override
    public PmsPermission getByPermissionNameNotEqId(String permissionName, Long id) {
        return pmsPermissionDao.getByPermissionNameNotEqId(permissionName,id);
    }

    @Override
    public void delete(Long permissionId) {
        pmsPermissionDao.delete(permissionId);
    }

    @Override
    public String getPermissionIdsByRoleId(Long roleId) {
        List<PmsRolePermission> rolePermissions = pmsRolePermissionDao.listByRoleId(roleId);
        StringBuffer actionIds=new StringBuffer();
        for(PmsRolePermission rolePermission:rolePermissions){
            actionIds.append(rolePermission.getId()).append(",");
        }
        return actionIds.toString();
    }

    @Override
    public List<PmsPermission> listAll() {
        Map<String,Object> params=new HashMap<>();
        return pmsPermissionDao.listBy(params);
    }
}
