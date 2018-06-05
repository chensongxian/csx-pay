package com.csx.pay.permission.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.permission.dao.PmsPermissionDao;
import com.csx.pay.permission.dao.PmsRolePermissionDao;
import com.csx.pay.permission.entity.PmsPermission;
import com.csx.pay.permission.entity.PmsRolePermission;
import com.csx.pay.permission.service.PmsOperatorRoleService;
import com.csx.pay.permission.service.PmsRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author csx
 * @Package com.csx.pay.permission.service.impl
 * @Description: 角色权限service接口实现
 * @date 2018/6/5 0005
 */
@Service("pmsRolePermissionService")
public class PmsRolePermissionServiceImpl implements PmsRolePermissionService {
    @Autowired
    private PmsRolePermissionDao pmsRolePermissionDao;

    @Autowired
    private PmsPermissionDao pmsPermissionDao;
    @Autowired
    private PmsOperatorRoleService pmsOperatorRoleService;

    /**
     * 根据操作员ID，获取所有的功能权限集
     *
     * @param operatorId
     */
    @Override
    public Set<String> getPermissionsByOperatorId(Long operatorId) {
        // 根据操作员Id查询出关联的所有角色id
        String roleIds = pmsOperatorRoleService.getRoleIdsByOperatorId(operatorId);

        String permissionIds = getActionIdsByRoleIds(roleIds);
        Set<String> permissionSet = new HashSet<String>();

        // 根据角色ID字符串得到该用户的所有权限拼成的字符串
        if (!StringUtils.isEmpty(permissionIds)) {
            List<PmsPermission> permissions = pmsPermissionDao.findByIds(permissionIds);
            for (PmsPermission permission : permissions) {
                permissionSet.add(permission.getPermission());
            }
        }
        return permissionSet;
    }

    /**
     * 根据角色ID集得到所有权限ID集
     *
     * @param roleIds
     * @return actionIds
     */
    private String getActionIdsByRoleIds(String roleIds) {
        // 得到角色－权限表中roleiId在ids中的所有关联对象
        // 构建StringBuffer
        List<PmsRolePermission> listRolePermission = pmsRolePermissionDao.listByRoleIds(roleIds);
        StringBuffer actionIdsBuf = new StringBuffer("");
        // 拼接字符串
        for (PmsRolePermission pmsRolePermission : listRolePermission) {
            actionIdsBuf.append(pmsRolePermission.getPermissionId()).append(",");
        }
        String actionIds = actionIdsBuf.toString();
        // 截取字符串
        if (StringUtils.isEmpty(actionIds) && actionIds.length() > 0) {
            // 去掉最后一个逗号
            actionIds = actionIds.substring(0, actionIds.length() - 1);
        }
        return actionIds;
    }

    // /////////////////////////////下面：基本操作方法///////////////////////////////////////////////

    /**
     * 创建pmsOperator
     */
    @Override
    public void saveData(PmsRolePermission pmsRolePermission) {
        pmsRolePermissionDao.insert(pmsRolePermission);
    }

    /**
     * 修改pmsOperator
     */
    @Override
    public void updateData(PmsRolePermission pmsRolePermission) {
        pmsRolePermissionDao.update(pmsRolePermission);
    }

    /**
     * 根据id获取数据pmsOperator
     *
     * @param id
     * @return
     */
    @Override
    public PmsRolePermission getDataById(Long id) {
        return pmsRolePermissionDao.getById(id);

    }

    /**
     * 分页查询pmsOperator
     *
     * @param pageParam
     * @param pmsRolePermission
     *
     * @return
     */
    @Override
    public PageBean listPage(PageParam pageParam, PmsRolePermission pmsRolePermission) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return pmsRolePermissionDao.listPage(pageParam, paramMap);
    }

    /**
     * 保存角色和权限之间的关联关系
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRolePermission(Long roleId, String rolePermissionStr){
        // 删除原来的角色与权限关联
        pmsRolePermissionDao.deleteByRoleId(roleId);
        if (!StringUtils.isEmpty(rolePermissionStr)) {
            // 创建新的关联
            String[] permissionIds = rolePermissionStr.split(",");
            for (int i = 0; i < permissionIds.length; i++) {
                Long permissionId = Long.valueOf(permissionIds[i]);
                PmsRolePermission item = new PmsRolePermission();
                item.setPermissionId(permissionId);
                item.setRoleId(roleId);
                pmsRolePermissionDao.insert(item);
            }
        }
    }
}
