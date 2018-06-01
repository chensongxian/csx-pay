package com.csx.pay.account.service;

import com.csx.pay.account.entity.RpSettRecord;
import com.csx.pay.common.core.exception.BizException;
import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;

import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.account.service
 * @Description: 结算查询service接口
 * @date 2018/5/31 0031
 */
public interface RpSettQueryService {
    /**
     * 根据参数分页查询结算记录
     *
     * @param pageParam
     * @param params
     *            ：map的key为 ：accountNo、userNo、settStatus...可以参考实体
     *
     * @return
     * @throws BizException
     */
    public PageBean querySettRecordListPage(PageParam pageParam, Map<String, Object> params) throws BizException;

    /**
     * 根据参数分页查询结算日汇总记录
     *
     * @param pageParam
     * @param params
     *            ：map的key为 ：accountNo...可以参考实体
     *
     * @return
     * @throws BizException
     */
    public PageBean querySettDailyCollectListPage(PageParam pageParam, Map<String, Object> params) throws BizException;

    /**
     * 根据id获取数据
     *
     * @param id
     * @return
     */
    public RpSettRecord getDataById(String id);
}
