<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>兴趣爱好</title>
	<link rel="stylesheet" type="text/css"href="<%=basePath%>easyui/themes/gray/easyui.css"/>
	<link rel="stylesheet" type="text/css"href="<%=basePath%>easyui/themes/icon.css"/>
	<link rel="stylesheet" type="text/css"href="<%=basePath%>easyui/themes/gray/datagrid.css"/>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.min.js"></script>
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
		    url: '<%=basePath %>framework/admin/getMyLike.action',
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
		
		$("input").mouseover(function(){
			$(this).css("border","1px dashed #fff");
		}).mouseout(function(){
			$(this).css("border","none");
		});
		$("#upbackbox").mouseover(function(){
			$(this).css("border","1px dashed #fff");
		}).mouseout(function(){
			$(this).css("border","none");
		});
	});
	
	function newMajor(){
		$("#majorText").textbox("setValue","");
		$("#majorName").textbox("setValue","");
		$('#dlg').dialog('open').dialog('setTitle','');
	}
	
	function saveBean(){
		var majorText = $("#majorText").textbox("getValue");
		var majorName = $("#majorName").textbox("getValue");
		$.post("<%=basePath%>framework/admin/addMyLike.action",{title:majorName,text:majorText},function(res,stutas){
			if(res==1){
				$.messager.alert('操作提示', "数据保存成功！", 'info');
				$('#dlg').dialog('close');
				$('#dg').datagrid('reload');
			}
		});
	}
	
	function dele(){
		var row = $('#dg').datagrid('getChecked');
		if(row.length<=0){
			$.messager.alert('操作提示',"没有选择数据！","warning");
			return;
		}
		if(row.length>1){
			$.messager.alert('操作提示',"只能选择一行数据！","warning");
			return;
		}
		var idstr = row[0].id;
		$.post("<%=basePath%>framework/admin/deletMyLike.action",{idstr:idstr},function(res,stutas){
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
			        "q+": Math.floor((this.getMonth() + 3) / 3),
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
</script>

<div id="upbackbox" style="background:url(../img/slideshow/slide5.png); background-size:100%; width:100%; height:500px; margin-top:0px; padding-top:200px;">
	<div style="width:600px; height:500px; margin-left:200px;">
		<div>
			<input value="轻松去黑头" style="width:120px; height:40px; font-size:18px; background:none; border:none;"/>
			<input value="收缩毛孔，光滑肌肤" style="height:30px; font-size:14px; background:none; border:none;"/>
		</div>
		<div>
			<input value="控油祛黑头组合  4件套组合" style="height:30px; font-size:14px; background:none; border:none;">
		</div>
		<div>
			<input value="清爽净透柔肤水  120ml" style="height:30px; font-size:14px; background:none; border:none;">
		</div>
		<div>
			<input value="毛孔细嫩净化乳  30g" style="height:30px; font-size:14px; background:none; border:none;">
		</div>
		<div>
			<input value="清凉薄荷啫喱  30g" style="height:30px; font-size:14px; background:none; border:none;">
		</div>
		<div>
			<input value="祛黑头净颜贴  10片装" style="height:30px; font-size:14px; background:none; border:none;">
		</div>
	</div>
</div>
<div style="border:1px dashed #000; width:50px; height:30px;">修改大图</div>
</body>
</html>