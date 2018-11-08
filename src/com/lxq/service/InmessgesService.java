package com.lxq.service;

import java.util.List;

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
public class InmessgesService {
	@Resource
	private Dao dao;
	
	/**
	 * ����user
	 */
	public int addUser(Inmessges user){
		try {
			dao.save(user);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * ɾ��user
	 */
	public int delete(Inmessges user){
		try {
			dao.delete(user);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * ��ȡȫ����Inmessges���ݱ��������Ϣ
	 */
	public Inmessges list(){
		List<Inmessges> list = dao.find("FROM Inmessges");
		return list.get(0);
	}
	
	/**
	 * ��ȡ��������
	 */
	public LifeMotto listMotto(){
		List<LifeMotto> list = dao.find("FROM LifeMotto ORDER BY creantime DESC");
		return list.get(0);
	}
	
	/**
	 * ��ȡ��Ȥ����
	 */
	public myLike listLike(){
		List<myLike> list = dao.find("FROM myLike ORDER BY creantime DESC");
		return list.get(0);
	}
	
	/**
	 * �ҵļ��
	 */
	public myInfo listMyinfo(){
		List<myInfo> list = dao.find("FROM myInfo ORDER BY creantime DESC");
		return list.get(0);
	}
	
	/**
	 * �ҵļ�������
	 */
	public List<myMajor> listMajor(){
		List<myMajor> list = dao.find("FROM myMajor");
		return list;
	}
	
	/**
	 * �ҵĲ�������
	 */
	public List<myText> listText(){
		List<myText> list = dao.find("FROM myText ORDER BY creantime DESC");
		return list;
	}
	
	/**
	 * ��ȡ������������������
	 */
	public myText TextDalit(String id){
		List<myText> list = dao.find("FROM myText WHERE id='"+id+"'");
		return list.get(0);
	}
	
	/**
	 * �ҵľ���
	 */
	public myExperience listExper(){
		List<myExperience> list = dao.find("FROM myExperience ORDER BY creantime DESC");
		return list.get(0);
	}
	
}
