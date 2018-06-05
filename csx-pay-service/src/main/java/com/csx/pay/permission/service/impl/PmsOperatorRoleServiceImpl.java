package com.csx.pay.permission.service.impl;

import com.csx.pay.common.core.utils.StringUtil;
import com.csx.pay.permission.dao.PmsOperatorDao;
import com.csx.pay.permission.dao.PmsOperatorRoleDao;
import com.csx.pay.permission.dao.PmsRoleDao;
import com.csx.pay.permission.entity.PmsOperator;
import com.csx.pay.permission.entity.PmsOperatorRole;
import com.csx.pay.permission.entity.PmsRole;
import com.csx.pay.permission.service.PmsOperatorRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author csx
 * @Package com.csx.pay.permission.service.impl
 * @Description: 操作员角色关联service实现类
 * @date 2018/6/5 0005
 */
@Service("pmsOperatorRoleService")
public class PmsOperatorRoleServiceImpl implements PmsOperatorRoleService {
    @Autowired
    private PmsOperatorDao pmsOperatorDao;
    @Autowired
    private PmsOperatorRoleDao pmsOperatorRoleDao;
    @Autowired
    private PmsRoleDao pmsRoleDao;

    @Override
    public String getRoleIdsByOperatorId(Long operatorId) {
        // 得到操作员和角色列表
        List<PmsOperatorRole> rpList = pmsOperatorRoleDao.listByOperatorId(operatorId);
        // 构建StringBuffer来拼字符串
        StringBuffer roleIdsBuf = new StringBuffer("");
        for (PmsOperatorRole rp : rpList) {
            roleIdsBuf.append(rp.getRoleId()).append(",");
        }
        String roleIds = roleIdsBuf.toString();
        // 截取字符串
        if (StringUtils.isNotBlank(roleIds) && roleIds.length() > 0) {
            roleIds = roleIds.substring(0, roleIds.length() - 1);
        }
        return roleIds;
    }

    @Override
    public Set<String> getRoleCodeByOperatorId(Long operatorId) {
        List<PmsOperatorRole> rpList = pmsOperatorRoleDao.listByOperatorId(operatorId);
        Set<String> roleCodeSet = new HashSet<>();

        for (PmsOperatorRole rp : rpList) {
            Long roleId = rp.getRoleId();
            PmsRole role = pmsRoleDao.getById(roleId);
            if (role == null) {
                continue;
            }
            roleCodeSet.add(role.getRoleCode());
        }

        return roleCodeSet;
    }

    @Override
    public List<PmsOperator> listOperatorByRoleId(Long roleId) {
        return pmsOperatorDao.listByRoleId(roleId);
    }

    @Override
    public void saveOperator(PmsOperator pmsOperator, String operatorRoleStr) {
        //保存操作员信息
        pmsOperatorDao.insert(pmsOperator);
        //保存角色关联信息
        if(StringUtils.isNotBlank(operatorRoleStr)&&operatorRoleStr.length()>0){
            saveOrUpdateOperatorRole(pmsOperator.getId(),operatorRoleStr);
        }
    }

    @Override
    public void updateOperator(PmsOperator pmsOperator, String roleOperatorStr) {
        //更新操作员信息
        pmsOperatorDao.update(pmsOperator);
        saveOrUpdateOperatorRole(pmsOperator.getId(),roleOperatorStr);
    }

    @Override
    public int countOperatorByRoleId(Long roleId) {
        List<PmsOperatorRole> operatorList = pmsOperatorRoleDao.listByRoleId(roleId);
        if (operatorList == null || operatorList.isEmpty()) {
            return 0;
        } else {
            return operatorList.size();
        }
    }

    @Override
    public List<PmsOperatorRole> listOperatorRoleByOperatorId(Long operatorId) {
        return pmsOperatorRoleDao.listByOperatorId(operatorId);
    }

    /**
     * 保存或更新用户与角色之间的关联关系
     * @param operatorId
     * @param roleIdsStr
     */
    private void saveOrUpdateOperatorRole(Long operatorId, String roleIdsStr) {
        List<PmsOperatorRole> operatorRoles = pmsOperatorRoleDao.listByOperatorId(operatorId);
        Map<Long,PmsOperatorRole> delMap=new HashMap<>();
        for(PmsOperatorRole operatorRole:operatorRoles){
            delMap.put(operatorRole.getRoleId(),operatorRole);
        }

        if(StringUtils.isNotBlank(roleIdsStr)){
            String[] roleIds = roleIdsStr.split(",");
            for(int i=0;i<roleIds.length;i++){
                Long roleId=Long.valueOf(roleIds[i]);
                if(delMap.get(roleId)==null){
                    PmsOperatorRole operatorRole=new PmsOperatorRole();
                    operatorRole.setRoleId(roleId);
                    operatorRole.setOperatorId(operatorId);
                    pmsOperatorRoleDao.insert(operatorRole);
                }else{
                    delMap.remove(roleId);
                }
            }
        }
        Iterator<Long> iterator = delMap.keySet().iterator();
        while ((iterator.hasNext())){
            Long roleId = iterator.next();
            pmsOperatorRoleDao.deleteByRoleIdAndOperatorId(roleId,operatorId);

        }
    }
}
