package com.lxq.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Dao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * ��������
	 * @param object
	 * @return
	 * @throws HibernateException
	 */
	public Serializable save(Object object) throws HibernateException{
		return getSession().save(object);
	}
	
	/**
	 * ��������
	 * @param object
	 */
	public void update(Object object) {
		getSession().update(object);
	}
	
	/**
	 * ɾ��һ������
	 * @param object
	 */
	public void delete(Object object){
		getSession().delete(object);
	}
	
	/**
	 * ɾ����������
	 * @param objects
	 */
	public <T> void delete(List<T> objects) {
		for(T object: objects){
			getSession().delete(object);
		}
	}
	
	/**
	 * ����
	 * @param hql
	 * @return
	 */
	public <T> List<T> find(String hql) {
		Query q = getSession().createQuery(hql);
		return q.list();
	}
	
	/**
	 * ����ȫ��
	 * @param c
	 * @return
	 */
	public <T> List<T> findAll(Class<T> c) {
		String hql = "from " + c.getSimpleName();
		return find(hql);
	}
	
	/**
	 * ��ҳչʾ��1��
	 * @param hql
	 * @param page
	 * @param rows
	 * @return
	 */
	public <T> List<T> findByPage(String hql, int page, int rows) {
		Query q = getSession().createQuery(hql);
		return page(q, page, rows);
	}
	
	/**
	 * ��ҳչʾ��2��
	 * @param query
	 * @param page
	 * @param rows
	 * @return
	 */
	private static<T> List<T> page(Query query, int page, int rows){
		if (page > 1) {
			page = (page - 1) * rows;
		} else {
			page = 0;
		}
		query.setFirstResult(page);
		query.setMaxResults(rows);
		return query.list();
	}
	
	/**
	 * ���ڷ�ҳ��1���������������������
	 * @param c
	 * @param hql
	 * @return
	 */
	public <T> Long count(Class<T> c,String hql) {
		if(hql.equals("")||hql==""||hql==null){
			hql = "from " + c.getSimpleName();
		}
		Query q = getSession().createQuery(hql);
		return getRows(q);
	}
	
	/**
	 * ��һ�ֻ�ȡ��Ӱ��ķ��������ڶ��������ҵ����
	 * @param c
	 * @param hql
	 * @param sqlParams
	 * @return
	 */
	public <T> Long findcount(Class<T> c,String hql,List<Object> sqlParams) {
		if(hql.equals("")||hql==""||hql==null){
			hql = "from " + c.getSimpleName();
		}
		Query q = getSession().createQuery(hql);
		for(int i=0;i<sqlParams.size();i++){
			if(sqlParams.get(i) instanceof Integer){
				q.setInteger(i,(Integer)sqlParams.get(i));
			}
			if(sqlParams.get(i) instanceof Boolean){
				q.setBoolean(i,(Boolean)sqlParams.get(i));
			}
			if(sqlParams.get(i) instanceof String){
				q.setString(i,(String)sqlParams.get(i));
			}
		}
		return getRows(q);
	}
	
	/**
	 * ���ڷ�ҳ��2��
	 * @param query
	 * @return
	 */
	public long getRows(Query query) {
		ScrollableResults scrollableResults = query.scroll();
		scrollableResults.last();
		if (scrollableResults.getRowNumber() >= 0) {
			return new Long(scrollableResults.getRowNumber() + 1);
		}
		return 0;
	}
	
	/**
	 * ͨ��hql�����������ҵķ���
	 * @param clazz
	 * @param hql
	 * @param sqlParams
	 * @return
	 */
	public <T> List<T> getListByHql(Class<T> clazz,String hql, List<Object> sqlParams) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		for(int i=0;i<sqlParams.size();i++){
			if(sqlParams.get(i) instanceof Integer){
				query.setInteger(i,(Integer)sqlParams.get(i));
			}
			if(sqlParams.get(i) instanceof Boolean){
				query.setBoolean(i,(Boolean)sqlParams.get(i));
			}
			if(sqlParams.get(i) instanceof String){
				query.setString(i,(String)sqlParams.get(i));
			}
		}
		return query.list();
	}
	
	/**
	 * ��sql������ɾ�����������޸�
	 * @param sql
	 * @return
	 */
	public int executeJDBCSql(String sql){
		return getSession().createSQLQuery(sql).executeUpdate();
	}
	
	/**
	 * ��sql����������
	 * @param sql
	 * @return
	 */
	public List executeJDBCSqlQuery(String sql){
		return getSession().createSQLQuery(sql).list();
	}
}
