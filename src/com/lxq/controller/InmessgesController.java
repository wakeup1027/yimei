package com.lxq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxq.beans.Inmessges;
import com.lxq.beans.LifeMotto;
import com.lxq.beans.myExperience;
import com.lxq.beans.myInfo;
import com.lxq.beans.myLike;
import com.lxq.beans.myMajor;
import com.lxq.beans.myText;
import com.lxq.service.InmessgesService;

@Controller
@RequestMapping("/framework/user")
public class InmessgesController {
	@Autowired
	private InmessgesService userser;
	
	@RequestMapping(value = "/add.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(Inmessges user){
		Map<String, Object> map = new HashMap<String, Object>();
		int i = userser.addUser(user);
		if(i==1)map.put("addRes", "数据增加成功！");else map.put("addRes", "数据增加失败！");
		map.put("addUser", user);
		return map;
	}
	
	@RequestMapping(value = "/delet.action", method = RequestMethod.POST)
    @ResponseBody
	public Map<String, Object> delete(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		Inmessges user  = new Inmessges();
		user.setId(id);
		int i = userser.delete(user);
		if(i==1)map.put("addRes", "数据删除成功！");else map.put("addRes", "数据删除失败！");
		return map;
	}
	
	@RequestMapping(value = "/getlist.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getlist(){
		Map<String,Object> map = new HashMap<String,Object>();
		Inmessges InmesList = userser.list();
		LifeMotto MottoList = userser.listMotto();
		myExperience MyExperList = userser.listExper();
		myInfo InfoList = userser.listMyinfo();
		myLike  MyLikeList = userser.listLike();
		List<myMajor> MyMajor = userser.listMajor();
		List<myText> MyTextList = userser.listText();
		
		map.put("InmesList", InmesList); //获取我的信息
		map.put("MottoList", MottoList); //获取人生格言
		map.put("MyExperList", MyExperList); //获取人生经历
		map.put("InfoList", InfoList); //获取简介
		map.put("MyLikeList", MyLikeList); //获取兴趣爱好
		map.put("MyMajor", MyMajor); //获取技能
		map.put("MyTextList", MyTextList); //获取博文信息
		return map;
	}
	
	@RequestMapping(value = "/findOne.action", method = RequestMethod.POST)
	@ResponseBody
	public myText findOne(String id){
		return userser.TextDalit(id);
	}
}
