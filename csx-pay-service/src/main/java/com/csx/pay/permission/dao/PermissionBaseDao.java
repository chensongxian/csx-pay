package com.csx.pay.permission.dao;

import com.csx.pay.common.core.page.PageBean;
import com.csx.pay.common.core.page.PageParam;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author csx
 * @Package com.csx.pay.permission.dao
 * @Description: 权限点数据访问层基础支撑接口
 * @date 2018/6/5 0005
 */
public interface PermissionBaseDao<T> {
    /**
     * 单条插入数据
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 批量插入数据
     * @param list
     * @return
     */
    int insert(List<T> list);

    /**
     * 根据id单条更新数据
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 根据id批量更新数据
     * @param list
     * @return
     */
    int update(List<T> list);

    /**
     * 根据column批量更新数据
     * @param paramMap
     * @return
     */
    int update(Map<String, Object> paramMap);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 根据column查询数据
     * @param paramMap
     * @return
     */
    public T getByColumn(Map<String, Object> paramMap);

    /**
     * 根据条件查询单条数据
     * @param paramMap
     * @return
     */
    public T getBy(Map<String, Object> paramMap);

    /**
     * 根据条件查询列表数据
     * @param paramMap
     * @return
     */
    public List<T> listBy(Map<String, Object> paramMap);

    /**
     * 根据column查询列表数据
     * @param paramMap
     * @return
     */
    public List<T> listByColumn(Map<String, Object> paramMap);

    /**
     * 根据column查询记录数
     * @param paramMap
     * @return
     */
    Long getCountByColumn(Map<String, Object> paramMap);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 根据id批量删除数据
     * @param list
     * @return
     */
    int delete(List<T> list);

    /**
     * 根据column批量删除数据
     * @param paramMap
     * @return
     */
    int delete(Map<String, Object> paramMap);

    /**
     * 分页查询数据
     * @param pageParam
     * @param paramMap
     * @return
     */
    public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);

    public SqlSessionTemplate getSessionTemplate();

    public SqlSession getSqlSession();
}
