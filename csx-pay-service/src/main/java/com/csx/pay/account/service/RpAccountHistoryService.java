package com.csx.pay.account.service;

import com.csx.pay.account.entity.RpAccountHistory;
import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;

/**
 * @author csx
 * @Package com.csx.pay.account.service
 * @Description: 账户历史service接口
 * @date 2018/5/31 0031
 */
public interface RpAccountHistoryService {
    /**
     * 保存
     * @param rpAccountHistory
     */
    void saveData(RpAccountHistory rpAccountHistory);

    /**
     * 更新
     * @param rpAccountHistory
     */
    void updateData(RpAccountHistory rpAccountHistory);

    /**
     * 根据id获取数据
     *
     * @param id
     * @return
     */
    RpAccountHistory getDataById(String id);


    /**
     * 获取分页数据
     *
     * @param pageParam
     * @return
     */
    PageBean listPage(PageParam pageParam, RpAccountHistory rpAccountHistory);
}
