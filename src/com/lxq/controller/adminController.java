package com.lxq.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp; 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lxq.beans.Inmessges;
import com.lxq.beans.LifeMotto;
import com.lxq.beans.myExperience;
import com.lxq.beans.myInfo;
import com.lxq.beans.myLike;
import com.lxq.beans.myMajor;
import com.lxq.beans.myText;
import com.lxq.service.adminService;

@Controller
@RequestMapping("/framework/admin")
public class adminController {
	private static final int FILE_SIZE = 16*1024;  //16K
	
	@Autowired
	private adminService admins;
	
	//==============================人生格言================================================
	@RequestMapping(value = "/getLifeMotto.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showsLifeMotto(int page, int rows) {
 	   Map<String, Object> map = new HashMap<String, Object>();
 	   map = admins.showsLifeMotto(page, rows);
       return map;
    }
	
	/**
	 * 增加“人生格言”
	 */
	@RequestMapping(value = "/addLifeMotto.action", method = RequestMethod.POST)
    @ResponseBody
    public int addLifeMotto(LifeMotto myMa){
		myMa.setCreantime(new Timestamp(new Date().getTime()));
		try{
			admins.add(myMa);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
	
	/**
	 * 删除“人生格言”
	 */
	@RequestMapping(value = "/deletLifeMotto.action", method = RequestMethod.POST)
    @ResponseBody
    public int deletLifeMotto(String idstr){
		LifeMotto mmjc = new LifeMotto();
		mmjc.setId(idstr);
		try{
			admins.delet(mmjc);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
	
	//==============================我的经历==========================================
	@RequestMapping(value = "/getExperien.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showsExperien(int page, int rows) {
 	   Map<String, Object> map = new HashMap<String, Object>();
 	   map = admins.showsExperien(page, rows);
       return map;
    }
	
	/**
	 * 增加“我的经历”
	 */
	@RequestMapping(value = "/addExperien.action", method = RequestMethod.POST)
    @ResponseBody
    public int addExperien(myExperience myMa){
		myMa.setCreantime(new Timestamp(new Date().getTime()));
		try{
			admins.add(myMa);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
	
	/**
	 * 删除“我的经历”
	 */
	@RequestMapping(value = "/deletExperien.action", method = RequestMethod.POST)
    @ResponseBody
    public int deletExperien(String idstr){
		myExperience mmjc = new myExperience();
		mmjc.setId(idstr);
		try{
			admins.delet(mmjc);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
	
	//==============================兴趣爱好==========================================
	@RequestMapping(value = "/getMyLike.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> showsMyLike(int page, int rows) {
	 	  Map<String, Object> map = new HashMap<String, Object>();
	 	  map = admins.showsMyLike(page, rows);
	      return map;
	}
		
	/**
	* 增加“兴趣爱好”
	*/
	@RequestMapping(value = "/addMyLike.action", method = RequestMethod.POST)
	@ResponseBody
	public int addMyLike(myLike myMa){
		myMa.setCreantime(new Timestamp(new Date().getTime()));
		try{
			admins.add(myMa);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
		
	/**
	* 删除“兴趣爱好”
	*/
	@RequestMapping(value = "/deletMyLike.action", method = RequestMethod.POST)
	@ResponseBody
	public int deletMyLike(String idstr){
		myLike mmjc = new myLike();
		mmjc.setId(idstr);
		try{
			admins.delet(mmjc);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
		
	//=============================我的博文=================================================
	@RequestMapping(value = "/getMyText.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> showsMyText(int page, int rows) {
	 	  Map<String, Object> map = new HashMap<String, Object>();
	 	  map = admins.showsMyText(page, rows);
	      return map;
	}
		
	/**
	* 增加“我的博文”
	*/
	@RequestMapping(value = "/addMyText.action", method = RequestMethod.POST)
	@ResponseBody
	public int addMyText(@RequestParam(value="fenMian") MultipartFile file, myText myMa, HttpServletRequest request){
		//自定义输出路径
		String fileName = file.getOriginalFilename();  //获取到上传文件的文件名
		String fileType = fileName.substring(fileName.lastIndexOf(".")); //获取到上传文件的类型
		String chuangeName = System.currentTimeMillis()+fileType;
		String path = request.getSession().getServletContext().getRealPath("uploadImg"); //设置保存路径
		String savePath = path+File.separator+chuangeName;
		InputStream inp = null;
		OutputStream out = null;
		try {
			inp = file.getInputStream();
			
			/*创建输出文件流*/
			File files =  new File(savePath);
			FileOutputStream fout = new FileOutputStream(files);
			out = new BufferedOutputStream(fout); //指定缓冲区大小
			
			/*分解输入文件流*/
			byte[] buffer = new byte[FILE_SIZE];
			int length = 0;
			while((length = inp.read(buffer))>0){
				out.write(buffer, 0, length);  //开始写入到输出文件流
			}
			
			//开始插入数据
			myMa.setCreantime(new Timestamp(new Date().getTime()));
			myMa.setImgPath("uploadImg/"+chuangeName);
			admins.add(myMa);
			
			return 1;
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally{
			try {
				inp.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}
		
	/**
	* 删除“我的博文”
	*/
	@RequestMapping(value = "/deletMyText.action", method = RequestMethod.POST)
	@ResponseBody
	public int deletMyText(String idstr){
		List<myText> mmjor = new ArrayList<myText>();
		String[] idstrCh = idstr.split(","); 
		for(String ids : idstrCh){
			myText mmjc = new myText();
			mmjc.setId(ids);
			mmjor.add(mmjc); 
		}
		try{
			admins.delet(mmjor);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
	
	//==============================关于自己==========================================
	@RequestMapping(value = "/getMyInfo.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> showsMyInfo(int page, int rows) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 map = admins.showsMyInfo(page, rows);
		 return map;
	}
			
	/**
	* 增加“关于自己”
	*/
	@RequestMapping(value = "/addMyInfo.action", method = RequestMethod.POST)
	@ResponseBody
	public int addMyInfo(myInfo myMa){
		myMa.setCreantime(new Timestamp(new Date().getTime()));
		try{
			admins.add(myMa);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
			
	/**
	* 删除“关于自己”
	*/
	@RequestMapping(value = "/deletMyInfo.action", method = RequestMethod.POST)
	@ResponseBody
	public int deletMyInfo(String idstr){
		myInfo mmjc = new myInfo();
		mmjc.setId(idstr);
		try{
			admins.delet(mmjc);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
	
	//=============================我的专业=================================================
	/**
	 * 获取“我的专业”数据链表
	 */
	@RequestMapping(value = "/getMyMajor.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> shows(int page, int rows) {
 	   Map<String, Object> map = new HashMap<String, Object>();
 	   map = admins.showsMyMajor(page, rows);
       return map;
    }
    
	/**
	 * 增加“我的专业”
	 */
	@RequestMapping(value = "/addMajor.action", method = RequestMethod.POST)
    @ResponseBody
    public int addMajor(myMajor myMa){
		try{
			admins.add(myMa);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
	
	/**
	 * 删除“我的专业”
	 */
	@RequestMapping(value = "/deletMajor.action", method = RequestMethod.POST)
    @ResponseBody
    public int deletMajor(String idstr){
		List<myMajor> mmjor = new ArrayList<myMajor>();
		String[] idstrCh = idstr.split(","); 
		for(String ids : idstrCh){
			myMajor mmjc = new myMajor();
			mmjc.setId(ids);
			mmjor.add(mmjc); 
		}
		try{
			admins.delet(mmjor);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
	
	//==========================个人简历=====================================
	/**
	 * 获取“个人简历”数据链表
	 */
	@RequestMapping(value = "/getInmesge.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showsInmesge(int page, int rows) {
 	   Map<String, Object> map = new HashMap<String, Object>();
 	   map = admins.showsInmesge(page, rows);
       return map;
    }
    
	/**
	 * 增加“个人简历”
	 */
	@RequestMapping(value = "/addInmesge.action", method = RequestMethod.POST)
    @ResponseBody
    public int addInmesge(Inmessges myMa){
		try{
			admins.add(myMa);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
	
	/**
	 * 删除“个人简历”
	 */
	@RequestMapping(value = "/deletInmesge.action", method = RequestMethod.POST)
    @ResponseBody
    public int deletInmesge(String idstr){
		Inmessges mmjc = new Inmessges();
		mmjc.setId(idstr);
		try{
			admins.delet(mmjc);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
	
}
