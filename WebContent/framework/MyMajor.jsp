<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>专业管理</title>
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
		    url: '<%=basePath %>framework/admin/getMyMajor.action',
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
					    {field: 'majorName', title:'专业', width:350}
		    ]]
		});
		
	});
	
	function newMajor(){
		$("#majorName").textbox("setValue","");
		$('#dlg').dialog('open').dialog('setTitle','');
	}
	
	function saveBean(){
		var majorName = $("#majorName").textbox("getValue");
		$.post("<%=basePath%>framework/admin/addMajor.action",{majorName:majorName},function(res,stutas){
			if(res==1){
				$.messager.alert('操作提示', "新增成功！", 'info');
				$('#dlg').dialog('close');
				$('#dg').datagrid('reload');
			}
		});
	}
	
	function dele(){
		var row = $('#dg').datagrid('getChecked');
		var idstr = "";
		if(row.length<=0){
			$.messager.alert('操作提示',"没有选择数据！","warning");
		}else{
			for(var i=0; i<row.length; i++){
				idstr += row[i].id+",";
			}
			idstr = idstr.substring(0,idstr.length-1);
		}
		$.post("<%=basePath%>framework/admin/deletMajor.action",{idstr:idstr},function(res,stutas){
			if(res==1){
				$.messager.alert('操作提示', "删除成功！", 'info');
				$('#dg').datagrid('reload');
			}
		});
	}
</script>
<div>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="newMajor()">新增</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut',plain:true" onclick="dele()">删除</a>
</div>
<div style="width:100%; height:100%;">
	<div id="dg" style="width:100%; height:100%;"></div>
</div>

<div id="dlg" class="easyui-dialog" style="width:360px; height:268px; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
	<div style="line-height: 30px;  border-bottom: 1px solid #ccc;  margin-bottom: 15px;  font-size: 15px;">增加专业信息</div>
	<table>
		<tr>
			<td><label>技能名称：</label></td>
			<td><input type="text" name="majorName" id="majorName" class="easyui-textbox" placeholder="在此填写技能名称"/></td>
		</tr>
	</table>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" id="saveBean"	iconCls="icon-ok" onclick="saveBean()" style="displaly:block;width: 90px">保存</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" id="saveCancel"	iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
</div>

</body>
</html>