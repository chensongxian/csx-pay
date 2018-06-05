package com.csx.pay.permission.service.impl;

import com.csx.pay.common.core.enums.PublicStatusEnum;
import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.common.core.utils.StringUtil;
import com.csx.pay.permission.dao.PmsOperatorDao;
import com.csx.pay.permission.dao.PmsOperatorRoleDao;
import com.csx.pay.permission.entity.PmsOperator;
import com.csx.pay.permission.entity.PmsOperatorRole;
import com.csx.pay.permission.exception.PermissionException;
import com.csx.pay.permission.service.PmsOperatorService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author csx
 * @Package com.csx.pay.permission.service.impl
 * @Description: 操作员service实现类
 * @date 2018/6/5 0005
 */
@Service("pmsOperatorService")
public class PmsOperatorServiceImpl implements PmsOperatorService {
    @Autowired
    private PmsOperatorDao pmsOperatorDao;
    @Autowired
    private PmsOperatorRoleDao pmsOperatorRoleDao;

    private static final String ADMIN="admin";

    @Override
    public void saveData(PmsOperator pmsOperator) {
        pmsOperatorDao.insert(pmsOperator);
    }

    @Override
    public void updateData(PmsOperator pmsOperator) {
        pmsOperatorDao.update(pmsOperator);
    }

    @Override
    public PmsOperator getDataById(Long id) {
        return pmsOperatorDao.getById(id);
    }

    @Override
    public PmsOperator findOperatorByLoginName(String loginName) {
        return pmsOperatorDao.findByLoginName(loginName);
    }

    @Override
    public PageBean listPage(PageParam pageParam, PmsOperator pmsOperator) {
        Map<String, Object> paramMap = new HashMap<String, Object>(3);
        paramMap.put("loginName", pmsOperator.getLoginName());
        paramMap.put("realName", pmsOperator.getRealName());
        paramMap.put("status", pmsOperator.getStatus());

        return pmsOperatorDao.listPage(pageParam, paramMap);
    }

    @Override
    public void deleteOperatorById(Long operatorId) {
        PmsOperator operator = pmsOperatorDao.getById(operatorId);
        if(operator!=null){
            if(ADMIN.equals(operator.getLoginName())){
                throw new RuntimeException("【" + operator.getLoginName() + "】为超级管理员，不能删除！");
            }
            pmsOperatorDao.delete(operatorId);
            pmsOperatorRoleDao.deleteByOperatorId(operatorId);
        }
    }

    @Override
    public void updateOperatorPwd(Long operatorId, String newPwd) {
        PmsOperator pmsOperator = pmsOperatorDao.getById(operatorId);
        pmsOperator.setLoginPwd(newPwd);
        pmsOperatorDao.update(pmsOperator);
    }

    @Override
    public void saveOperator(PmsOperator pmsOperator, String roleOperatorStr) {
        //保存操作人信息
        pmsOperatorDao.insert(pmsOperator);
        //保存操作人角色关联关系
        if(StringUtils.isNotBlank(roleOperatorStr)&&roleOperatorStr.length()>0){
            saveOrUpdateOperatorRole(pmsOperator,roleOperatorStr);
        }
    }

    @Override
    public void updateOperator(PmsOperator pmsOperator, String roleOperatorStr) {
        pmsOperatorDao.update(pmsOperator);
        saveOrUpdateOperatorRole(pmsOperator,roleOperatorStr);
    }

    /**
     * 保存用户和角色之间的关联关系
     * .
     */
    private void saveOrUpdateOperatorRole(PmsOperator pmsOperator, String roleIdsStr) {
        // 删除原来的角色与操作员关联
        List<PmsOperatorRole> listPmsOperatorRoles = pmsOperatorRoleDao.listByOperatorId(pmsOperator.getId());
        Map<Long, PmsOperatorRole> delMap = new HashMap<Long, PmsOperatorRole>();
        for (PmsOperatorRole pmsOperatorRole : listPmsOperatorRoles) {
            delMap.put(pmsOperatorRole.getRoleId(), pmsOperatorRole);
        }
        if (StringUtils.isNotBlank(roleIdsStr)) {
            // 创建新的关联
            String[] roleIds = roleIdsStr.split(",");
            for (int i = 0; i < roleIds.length; i++) {
                long roleId = Long.parseLong(roleIds[i]);
                if (delMap.get(roleId) == null) {
                    PmsOperatorRole pmsOperatorRole = new PmsOperatorRole();
                    pmsOperatorRole.setOperatorId(pmsOperator.getId());
                    pmsOperatorRole.setRoleId(roleId);
                    pmsOperatorRole.setCreater(pmsOperator.getCreater());
                    pmsOperatorRole.setCreateTime(new Date());
                    pmsOperatorRole.setStatus(PublicStatusEnum.ACTIVE.name());
                    pmsOperatorRoleDao.insert(pmsOperatorRole);
                } else {
                    delMap.remove(roleId);
                }
            }
        }

        Iterator<Long> iterator = delMap.keySet().iterator();
        while (iterator.hasNext()) {
            long roleId = iterator.next();
            pmsOperatorRoleDao.deleteByRoleIdAndOperatorId(roleId, pmsOperator.getId());
        }
    }
}
