package org.demo.plugin.member.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import org.demo.util.GenericsUtils;

@Repository("baseDao")
public class BaseDaoImpl<T extends Object> implements BaseDao<T> {
	@Resource
	private SessionFactory sessionFactory;
	private Class<T> entityClass;
	    
	public Class<T> getEntityClass() {
	  return entityClass;
	}
	public void setEntityClass(Class<T> entityClass) {
	  this.entityClass = entityClass;
	}
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public BaseDaoImpl() {
		this.setEntityClass(GenericsUtils.getGenericClass(getClass(), 0));
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public T find(Long id){
		T t = null;
		Query query = getSession().createQuery("from "+getEntityClass().getName()+" where id = "+id);
		if(query.list() != null && query.list().size()>0){
			t = (T) query.list().get(0);
		}
		return t;
	}
	
	public List<T> find(){
		List<T> list = null;
		Criteria criteria = getSession().createCriteria(getEntityClass());
		if(criteria.list() != null && criteria.list().size()>0){
			list = criteria.list();
		}
		return list;
	}
	
	public void save(T t){
		try {
			getSession().save(t);
		} catch (Exception e) {
			getSession().merge(t);
		}
	}
	
	public void Update(T t){
		try {
			getSession().update(t);
		} catch (Exception e) {
			getSession().merge(t);
		}
	}
	public T findBy(String field, Object value) {
		T t = null;
		Criteria criteria = getSession().createCriteria(getEntityClass()).add(Restrictions.eq(field, value));
		List<T> list = criteria.list();
		if(list != null && list.size()>0){
			t = (T) list.get(0);
		}
		return t;
	}
	
}
