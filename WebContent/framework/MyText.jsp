<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>人生格言</title>
	<link rel="stylesheet" type="text/css"href="<%=basePath%>easyui/themes/gray/easyui.css"/>
	<link rel="stylesheet" type="text/css"href="<%=basePath%>easyui/themes/icon.css"/>
	<link rel="stylesheet" type="text/css"href="<%=basePath%>easyui/themes/gray/datagrid.css"/>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>easyui/locale/easyui-lang-zh_CN.js"></script>
	<style>
		html{height: 100%;}
		body{height:92%;background:#fff;}
	</style>
</head>
<body>
<script>
	$(function(){
		$('#dg').datagrid({
		    height: '100%',
		    fit:true,
		    url: '<%=basePath %>framework/admin/getMyText.action',
		    method: 'POST',
		    striped: true,
		    nowrap: true,
		    pageSize: 10,
		    pageNumber:1, 
		    pageList: [10, 20, 50, 100, 150, 200],
			pagination : true,
		    showFooter: true, 
			loadMsg : '数据加载中请稍后……',
		    toolbar:"#tb",
		    singleSelect: false,
			rownumbers:true,
			columns: [[
					    {field: 'ck', checkbox:true},
					    {field: 'title', title:'标题', width:250},
					    {field: 'creantime', title:'创建时间', width:150,formatter:fotmateDate}
		    ]]
		});
		
	});
	
	function newMajor(){
		$("#majorText").textbox("setValue","");
		$("#majorName").textbox("setValue","");
		$('#dlg').dialog('open').dialog('setTitle','');
	}
	
	function dele(){
		var row = $('#dg').datagrid('getChecked');
		var idstr = "";
		if(row.length<=0){
			$.messager.alert('操作提示',"没有选择数据！","warning");
			return;
		}else{
			for(var i=0; i<row.length; i++){
				idstr += row[i].id+",";
			}
			idstr = idstr.substring(0,idstr.length-1);
		}
		$.post("<%=basePath%>framework/admin/deletMyText.action",{idstr:idstr},function(res,stutas){
			if(res==1){
				$.messager.alert('操作提示', "删除成功！", 'info');
				$('#dg').datagrid('reload');
			}
		});
	}
	
	function fotmateDate(value){
		var dateStr = "";
		if(value=="undefined"||value=="null"||value==null){}else{
			var date = new Date(value);
			dateStr = date.format("yyyy-MM-dd HH:mm:ss");
		}
	    return dateStr;    
	}

	//js格式化日期插件代码
	Date.prototype.format = function (format) {  
			 var o = {  
			        "M+": this.getMonth() + 1, // month  
			        "d+": this.getDate(), // day  
			        "H+": this.getHours(), // hour  
			        "m+": this.getMinutes(), // minute  
			        "s+": this.getSeconds(), // second  
			        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
			        "S": this.getMilliseconds()  
			        // millisecond  
			 }
			 if (/(y+)/.test(format)){
			        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)); 
			 } 
			 for (var k in o){  
			      if (new RegExp("(" + k + ")").test(format)){
			            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length)); 
			 	  }
			 } 
			 return format;  
	}
	
	function submitForm(){
		if(checkData()){
 			$('#importfm').ajaxSubmit({  
 				url: '<%=basePath%>framework/admin/addMyText.action',
 				dataType: 'text',
 				beforeSend:function(){$("#loadBox").show();},
				complete:function(){$("#loadBox").hide();},
 				success: resutlMsg,
 				error: errorMsg
 			}); 
 			function resutlMsg(msg){
 				if(msg==1){
	       			$('#dlg').dialog('close');
	           		$('#dg').datagrid('reload');
	           		$.messager.alert('操作提示', "数据保存成功！", 'info');
	       		}else {
	       			$.messager.alert('操作提示', "数据保存不成功！", 'warning');
	       		}
			}
			function errorMsg(){
				$.messager.alert('操作提示', "导入excel出错！", 'warning');
			}
 		}
		
	}
	
	//JS校验form表单信息
    function checkData(){
    	var fileDir = $("#fenMian").filebox('getValue');
    	var suffix = fileDir.substr(fileDir.lastIndexOf("."));
    	if("" == fileDir){
    		$.messager.alert('操作提示', "选择需要导入的Excel文件！", 'warning');
    		return false;
    	}
    	if(".jpg" != suffix && ".jpeg" != suffix && ".gif" != suffix && ".png" != suffix && ".bmp" != suffix ){
    		$.messager.alert('操作提示', "图片格式不支持，请重新选择！", 'warning');
    		return false;
    	}
    	return true;
    }
</script>

<div>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="newMajor()">新增</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut',plain:true" onclick="dele()">删除</a>
</div>
<div style="width:100%; height:100%;">
	<div id="dg" style="width:100%; height:100%;"></div>
</div>

<div id="dlg" class="easyui-dialog" style="width:450px; height:350px; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
	<div style="line-height: 30px;  border-bottom: 1px solid #ccc;  margin-bottom: 15px;  font-size: 15px;">增加我的博文</div>
	<form id="importfm" method="post"  enctype="multipart/form-data" novalidate >
	<table>
		<tr style="height:35px;">
			<td><label>标题：</label></td>
			<td><input type="text" name="title" id="majorName" class="easyui-textbox" placeholder="在此填写标题" style="width:300px;"/></td>
		</tr>
		<tr style="height:35px;">
			<td><label>封面：</label></td>
			<td><input name="fenMian" id="fenMian" data-options="prompt:'请选择上传图片'" class="easyui-filebox" style="width:300px;"/></td>
		</tr>
		<tr>
			<td><label>正文：</label></td>
			<td><input type="text" name="text" id="majorText" class="easyui-textbox" data-options="multiline:true" style="width:300px; height:100px"/></td>
		</tr>
	</table>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" id="saveBean" iconCls="icon-ok" onclick="submitForm()" style="displaly:block;width: 90px">保存</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" id="saveCancel" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
</div>
</body>
</html>