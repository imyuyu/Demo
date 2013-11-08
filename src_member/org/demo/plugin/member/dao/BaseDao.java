package org.demo.plugin.member.dao;

import java.util.List;

public interface BaseDao<T extends Object>{
	
	/**
	 * 根据ID获取对象
	 * @param id
	 * @return
	 */
	public T find(Long id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<T> find();
	
	/**
	 * 保存对象
	 * @param t
	 */
	public void save(T t);
	
	/**
	 * 更新对象
	 * @param t
	 */
	public void Update(T t);
	
	/**
	 * 根据列名查询
	 * @param field
	 * @param value
	 * @return
	 */
	public T findBy(String field,Object value);
}
