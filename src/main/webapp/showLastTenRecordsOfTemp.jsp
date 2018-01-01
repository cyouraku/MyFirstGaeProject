<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="java.util.*" %>
<%-- <%@ page import= "com.costuary.TemperatureRecordBean"%> --%>
<%@ page import= "com.costuary.bean.XmlBean"%>
<%@ page import="com.costuary.util.StringUtil" %>
<%
String basePath = (String)session.getAttribute("basePath");
String jQueryPath = (String)session.getAttribute("jQueryPath");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Show Last 10 Records of Tempratureg</title>
<link type="text/css" rel="stylesheet" href="${jQueryPath}/themes/metro/easyui.css" />
<link type="text/css" rel="stylesheet" href="${jQueryPath}/themes/mobile.css" />
<link type="text/css" rel="stylesheet" href="${jQueryPath}/themes/icon.css" />
<!-- try to change theme by user selecting -->
<link type="text/css" rel="stylesheet" href="${jQueryPath}/themes/material/easyui.css" />
<script type="text/javascript" src="${jQueryPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jQueryPath}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${jQueryPath}/jquery.easyui.mobile.js"></script>
<!-- //每隔五分钟自动刷新页面<meta http-equiv="refresh" content="300" charset="UTF-8"> -->
<!-- <title>Show Last 10 Records of Temprature</title> -->
<script type="text/javascript">
	var myDate;
	setInterval(function() {
		myDate = new Date();
		showTime.innerHTML = myDate.toLocaleString();
	}, 1000)
	var jsonValData = ${jsonValList};
	//debug
	console.log(jsonValData);
	$(function(){
	  $('#dg').datagrid({
	      data:  jsonValData
	  });
	  $('#button_check').bind('click', function(){
	  	$('#dg').datagrid({url: "ServerHealthInfoAjax"});
	  });
	});
</script>
</head>
<body>


	<!-- jquery ui testing -->
	<div id = "headerArea" class="easyui-navpanel" style="position:relative;padding:20px">
        <header>
            <div class="m-toolbar">
                <div class="m-title" style="font-size: 20px">查询结果</div>
                <div class="m-left">
<!--                     <a href="javascript:void(0)" class="easyui-linkbutton" type="text" plain="true" outline="true" onclick="location.href='AppMenu.jsp'" style="width:60px">返回</a> -->
                    <a id="button_check" href="javascript:void(0)" class="easyui-linkbutton" type="text" plain="true" outline="true" style="width: 100px">查询</a>
                </div>
<!--                 <div class="m-right"> -->
<!-- 					<a href="javascript:void(0)" class="easyui-linkbutton" type="text" plain="true" outline="true" onclick="" style="width: 100px">重置</a> -->
<!-- 				</div> -->
				<div id = "showTime"></div>
            </div>
        </header>
        <table id="dg" data-options="header:'#hh',singleSelect:true,border:false,fit:true,fitColumns:true,scrollbarSize:0" style="font-size: 16px">
        	<thead>
            	<tr>
	                <th data-options="field:'seqNo',width:80,align:'center'" style="font-size: 16px">序列</th>
	                <th data-options="field:'cpu_core1',width:80,align:'center'" style="font-size: 16px">CPU温度</th>
	                <th data-options="field:'gpu_core1',width:100,align:'center'" style="font-size: 16px">GPU温度</th>
	                <th data-options="field:'cpu_fan',width:80,align:'center'" style="font-size: 16px">风扇</th>
	                <th data-options="field:'date',width:80,align:'center'" style="font-size: 16px">时间戳</th>
            	</tr>
        	</thead>
    	</table>
    </div>


</body>
</html>