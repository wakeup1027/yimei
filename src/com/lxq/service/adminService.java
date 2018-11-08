package com.lxq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxq.beans.Inmessges;
import com.lxq.beans.LifeMotto; 
import com.lxq.beans.myExperience;
import com.lxq.beans.myInfo;
import com.lxq.beans.myLike;
import com.lxq.beans.myMajor;
import com.lxq.beans.myText;
import com.lxq.dao.Dao;

@Service
@Transactional
public class adminService<T> {
	
	@Resource
	private Dao dao;
	
	/**
	 * ����
	 */
	public void add(T sd){
		dao.save(sd);
	}
	
	/**
	 * ɾ����������
	 */
	public void delet(List<T> sd){
		dao.delete(sd);
	}
	
	/**
	 * ɾ����������
	 */
	public void delet(T sd){
		dao.delete(sd);
	}
	
	/**
	 * ��ȡ���ҵ�רҵ��ȫ������
	 */
	public Map<String, Object> showsMyMajor(int page, int rows){
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "FROM myMajor";
		List<myMajor> orders = this.dao.findByPage(hql, Integer.valueOf(page), Integer.valueOf(rows));
		Long total = this.dao.count(myMajor.class,hql);
		map.put("rows", orders);
	    map.put("total", total);
	    return map;
	}
	
	/**
	 * ��ȡ�ҵġ��������ԡ�ȫ������
	 * @param page
	 * @param rows
	 * @return
	 */
	public Map<String, Object> showsLifeMotto(int page, int rows){
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "FROM LifeMotto";
		List<LifeMotto> orders = this.dao.findByPage(hql, Integer.valueOf(page), Integer.valueOf(rows));
		Long total = this.dao.count(LifeMotto.class,hql);
		map.put("rows", orders);
	    map.put("total", total);
	    return map;
	}
	
	/**
	 * ��ȡ���ҵľ�����
	 */
	public Map<String, Object> showsExperien(int page, int rows){
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "FROM myExperience";
		List<myExperience> orders = this.dao.findByPage(hql, Integer.valueOf(page), Integer.valueOf(rows));
		Long total = this.dao.count(myExperience.class,hql);
		map.put("rows", orders);
	    map.put("total", total);
	    return map;
	}
	
	/**
	 * ��ȡ����Ȥ���á�
	 */
	public Map<String, Object> showsMyLike(int page, int rows){
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "FROM myLike";
		List<myLike> orders = this.dao.findByPage(hql, Integer.valueOf(page), Integer.valueOf(rows));
		Long total = this.dao.count(myLike.class,hql);
		map.put("rows", orders);
	    map.put("total", total);
	    return map;
	}
	
	/**
	 * ��ȡ���ҵĲ��ġ�
	 */
	public Map<String, Object> showsMyText(int page, int rows){
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "FROM myText";
		List<myText> orders = this.dao.findByPage(hql, Integer.valueOf(page), Integer.valueOf(rows));
		Long total = this.dao.count(myText.class,hql);
		map.put("rows", orders);
	    map.put("total", total);
	    return map;
	}
	
	/**
	 * ��ȡ�������Լ���
	 */
	public Map<String, Object> showsMyInfo(int page, int rows){
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "FROM myInfo";
		List<myInfo> orders = this.dao.findByPage(hql, Integer.valueOf(page), Integer.valueOf(rows));
		Long total = this.dao.count(myInfo.class,hql);
		map.put("rows", orders);
	    map.put("total", total);
	    return map;
	}
	
	/**
	 * ��ȡ�����˼�����
	 */
	public Map<String, Object> showsInmesge(int page, int rows){
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "FROM Inmessges";
		List<Inmessges> orders = this.dao.findByPage(hql, Integer.valueOf(page), Integer.valueOf(rows));
		Long total = this.dao.count(Inmessges.class,hql);
		map.put("rows", orders);
	    map.put("total", total);
	    return map;
	}
}
