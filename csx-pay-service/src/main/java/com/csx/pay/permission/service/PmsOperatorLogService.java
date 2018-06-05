package com.csx.pay.permission.service;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import com.csx.pay.permission.entity.PmsOperatorLog;

/**
 * @author csx
 * @Package com.csx.pay.permission.service
 * @Description: 操作员日志service接口
 * @date 2018/6/5 0005
 */
public interface PmsOperatorLogService {
    /**
     * 创建pmsOperatorLog
     * @param pmsOperatorLog
     */
    void saveData(PmsOperatorLog pmsOperatorLog);

    /**
     * 修改pmsOperatorLog
     * @param pmsOperatorLog
     */
    void updateData(PmsOperatorLog pmsOperatorLog);

    /**
     * 根据id获取数据pmsOperatorLog
     *
     * @param id
     * @return
     */
    PmsOperatorLog getDataById(Long id);

    /**
     * 分页查询pmsOperatorLog
     * @param pageParam
     * @param pmsOperatorLog
     * @return
     */
    PageBean listPage(PageParam pageParam, PmsOperatorLog pmsOperatorLog);
}
