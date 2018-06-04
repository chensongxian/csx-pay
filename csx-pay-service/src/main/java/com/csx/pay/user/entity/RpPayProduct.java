package com.csx.pay.user.entity;

import com.csx.pay.common.core.entity.BaseEntity;
import com.csx.pay.common.core.enums.PublicEnum;

import java.io.Serializable;

/**
 * @author csx
 * @Package com.csx.pay.user.entity
 * @Description: 支付产品实体类
 * @date 2018/6/4 0004
 */
public class RpPayProduct extends BaseEntity implements Serializable {
    private String productCode;

    private String productName;

    private String auditStatus;

    private static final long serialVersionUID = 1L;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getAuditStatusDesc() {
        return PublicEnum.getEnum(this.getAuditStatus()).getDesc();
    }
}
