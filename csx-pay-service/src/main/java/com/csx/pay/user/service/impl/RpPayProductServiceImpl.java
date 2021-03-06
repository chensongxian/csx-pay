package com.csx.pay.user.service.impl;

import com.csx.pay.common.core.enums.PublicEnum;
import com.csx.pay.common.core.enums.PublicStatusEnum;
import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.common.core.utils.StringUtil;
import com.csx.pay.user.dao.RpPayProductDao;
import com.csx.pay.user.entity.RpPayProduct;
import com.csx.pay.user.entity.RpPayWay;
import com.csx.pay.user.entity.RpUserPayConfig;
import com.csx.pay.user.exception.PayBizException;
import com.csx.pay.user.service.RpPayProductService;
import com.csx.pay.user.service.RpPayWayService;
import com.csx.pay.user.service.RpUserPayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.user.service.impl
 * @Description: TODO
 * @date 2018/6/4 0004
 */
@Service("rpPayProductService")
public class RpPayProductServiceImpl implements RpPayProductService {
    @Autowired
    private RpPayProductDao rpPayProductDao;

    @Autowired
    private RpPayWayService rpPayWayService;

    @Autowired
    private RpUserPayConfigService rpUserPayConfigService;

    @Override
    public void saveData(RpPayProduct rpPayProduct) {
        rpPayProductDao.insert(rpPayProduct);
    }

    @Override
    public void updateData(RpPayProduct rpPayProduct) {
        rpPayProductDao.update(rpPayProduct);
    }

    @Override
    public RpPayProduct getDataById(String id) {
        return rpPayProductDao.getById(id);
    }

    @Override
    public PageBean listPage(PageParam pageParam, RpPayProduct rpPayProduct) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        paramMap.put("auditStatus", rpPayProduct.getAuditStatus());
        paramMap.put("productName", rpPayProduct.getProductName());
        return rpPayProductDao.listPage(pageParam, paramMap);
    }


    @Override
    public RpPayProduct getByProductCode(String productCode, String auditStatus){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("productCode", productCode);
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        paramMap.put("auditStatus", auditStatus);
        return rpPayProductDao.getBy(paramMap);
    }


    @Override
    public void createPayProduct(String productCode, String productName) throws PayBizException {
        RpPayProduct rpPayProduct = getByProductCode(productCode, null);
        if(rpPayProduct != null){
            throw new PayBizException(PayBizException.PAY_PRODUCT_IS_EXIST,"支付产品已存在");
        }
        rpPayProduct = new RpPayProduct();
        rpPayProduct.setStatus(PublicStatusEnum.ACTIVE.name());
        rpPayProduct.setCreateTime(new Date());
        rpPayProduct.setId(StringUtil.get32UUID());
        rpPayProduct.setProductCode(productCode);
        rpPayProduct.setProductName(productName);
        rpPayProduct.setAuditStatus(PublicEnum.NO.name());
        saveData(rpPayProduct);
    }


    @Override
    public void deletePayProduct(String productCode) throws PayBizException{

        List<RpPayWay> payWayList = rpPayWayService.listByProductCode(productCode);
        if(!payWayList.isEmpty()){
            throw new PayBizException(PayBizException.PAY_PRODUCT_HAS_DATA,"支付产品已关联支付方式，无法删除！");
        }

        List<RpUserPayConfig> payConfigList = rpUserPayConfigService.listByProductCode(productCode);
        if(!payConfigList.isEmpty()){
            throw new PayBizException(PayBizException.PAY_PRODUCT_HAS_DATA,"支付产品已关联用户，无法删除！");
        }

        RpPayProduct rpPayProduct = getByProductCode(productCode, null);
        if(rpPayProduct.getAuditStatus().equals(PublicEnum.YES.name())){
            throw new PayBizException(PayBizException.PAY_PRODUCT_IS_EFFECTIVE,"支付产品已生效，无法删除！");
        }

        rpPayProduct.setStatus(PublicStatusEnum.UNACTIVE.name());
        updateData(rpPayProduct);
    }


    @Override
    public List<RpPayProduct> listAll(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        return rpPayProductDao.listBy(paramMap);
    }


    @Override
    public void audit(String productCode, String auditStatus) throws PayBizException{
        RpPayProduct rpPayProduct = getByProductCode(productCode, null);
        if(rpPayProduct == null){
            throw new PayBizException(PayBizException.PAY_PRODUCT_IS_NOT_EXIST,"支付产品不存在！");
        }

        if(auditStatus.equals(PublicEnum.YES.name())){
            //检查是否已设置支付方式
            List<RpPayWay> payWayList = rpPayWayService.listByProductCode(productCode);
            if(payWayList.isEmpty()){
                throw new PayBizException(PayBizException.PAY_TYPE_IS_NOT_EXIST,"支付方式未设置，无法操作！");
            }

        }else if(auditStatus.equals(PublicEnum.NO.name())){
            //检查是否已有支付配置
            List<RpUserPayConfig> payConfigList = rpUserPayConfigService.listByProductCode(productCode);
            if(!payConfigList.isEmpty()){
                throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_EXIST,"支付产品已关联用户支付配置，无法操作！");
            }
        }
        rpPayProduct.setAuditStatus(auditStatus);
        rpPayProduct.setEditTime(new Date());
        updateData(rpPayProduct);
    }
}
