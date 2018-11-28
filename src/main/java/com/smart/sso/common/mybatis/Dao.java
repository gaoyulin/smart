package com.smart.sso.common.mybatis;

import com.smart.sso.common.model.Pagination;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Dao接口
 * 
 * @author Joe
 */
public interface Dao<T, ID extends Serializable> {

	/**
	 * 查询所有分页
	 * 
	 * @param p
	 * @return
	 */
	public List<T> findByAll(Pagination<T> p);
	
	/**
	 * 通过主键查询实体
	 *  	 pk
	 * @return T
	 */
	public T get(ID pk);

	/**
	 * 插入实体
	 *
	 *            t
	 */
	public int insert(T t);

	/**
	 * 更新实体
	 * 
	 * @param T
	 *            t
	 */
	public int update(T t);
	
	/**
	 * 删除实体
	 *
	 *            t
	 */
	public int deleteById(Collection<ID> idList);
}
