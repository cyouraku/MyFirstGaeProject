<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="com.costuary.bean.MyLoginBean" %>
<%-- <% response.sendRedirect("InsertExpense.jsp"); %> --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String jQueryPath = basePath + "jquery-easyui-1.5.2";
	session.setAttribute("basePath", basePath);
	session.setAttribute("jQueryPath", jQueryPath);
	Map<String, String> map = new HashMap<String, String>();
	String userIp = request.getRemoteAddr();
	String sessionId = session.getId();
	map.put("userIp", userIp);
	map.put("sessionId", sessionId);
	session.setAttribute("Map", map);
	String googleUserNm = (String)session.getAttribute("googleUserNm");//Tim.Zhang1981
	MyLoginBean uBean = (MyLoginBean)session.getAttribute("userBean");
	String userNm = "";
	if(uBean != null){
		userNm = uBean.getUserName();
	} else {
		userNm = googleUserNm;
	}
	session.setAttribute("userNm",userNm);
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <meta name="viewport" content="width=device-width, initial-scale=no"> -->
<title>家庭理财网络应用程序beta1.0 written by Tim Zhang</title>
<link type="text/css" rel="stylesheet" href="${jQueryPath}/themes/metro/easyui.css" />
<link type="text/css" rel="stylesheet" href="${jQueryPath}/themes/mobile.css" />
<link type="text/css" rel="stylesheet" href="${jQueryPath}/themes/icon.css" />
<!-- try to change theme by user selecting -->
<link type="text/css" rel="stylesheet" href="${jQueryPath}/themes/material/easyui.css" />
<script type="text/javascript" src="${jQueryPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jQueryPath}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${jQueryPath}/jquery.easyui.mobile.js"></script>
<script type="text/javascript">
// 	function submitForm(){
// 		$('#costuaryMenu').form('submit');
// 	}
</script>
</head>
<body>
<div class="easyui-navpanel" >
        <header>
            <div class="m-toolbar">
            	<div class="m-title" style="font-size:20px">功能菜单</div>
<!--                 <span class="m-title" style="font-size: 20px">功能菜单</span> -->
                <div class="m-left">
                    <a href="javascript:void(0)" class="easyui-linkbutton" type="text" plain="true" outline="true" onclick="$('#selectMenu').form('reset')" style="width:150px;font-size:18px">Hello,${userNm}</a>
                </div>
            </div>
        </header>
        <div class="m-buttongroup" style="text-align: center;width:100%">
                <div style="padding:0 20px; text-align: center; margin-top: 30px">
					<form id="selectMenu" action="ShowLastTenRecords" method="get">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:true" style="width:100%;height:40px" onclick="$('#selectMenu').submit()"><span style="font-size: 16px">服务器温度记录查询</span></a>
					</form>
                </div>
                <div style="padding:0 20px; text-align: center; margin-top: 30px">
					<form id="chatRoom" action="SendMsgServlet" method="get">
					 	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:false" style="width:100%;height:40px" onclick="$('#chatRoom').submit()"><span style="font-size: 16px">聊天室</span></a>
					</form>
                </div>
                <div style="padding:0 20px; text-align: center; margin-top: 30px">
					<form id="reportSelectMenu" action="ReportSelectMenu" method="get">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:false" style="width:100%;height:40px" onclick=""><span style="font-size: 16px">To do list 2</span></a>
					</form>
                </div>
                <div style="padding:0 20px; text-align: center; margin-top: 30px">
					<form id="costuaryMenu" action="CostuaryMenu" method="get">
					 	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:false" style="width:100%;height:40px" onclick=""><span style="font-size: 16px">To do list 3</span></a>
					</form>
                </div>
                <div style="padding:0 20px; text-align: center; margin-top: 30px">
					<form id="reportSelectMenu" action="ReportSelectMenu" method="get">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:false" style="width:100%;height:40px" onclick=""><span style="font-size: 16px">To do list 4</span></a>
					</form>
                </div>
			<% if("Tim.Zhang1981".equals(userNm)) { %>
               <div style="padding:0 20px; text-align: center; margin-top: 30px">
                	<!-- To do list -->
					<form id="updateMenu" action="UpdateMenu" method="get">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:false" style="width:100%;height:40px" onclick=""><span style="font-size: 16px">To do list 5</span></a>
					</form>
                </div>
                <div style="padding:0 20px; text-align: center; margin-top: 30px">
                	<!-- To do list -->
					<form id="deleteMenu" action="DeleteMenu" method="get">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:false" style="width:100%;height:40px" onclick=""><span style="font-size: 16px">To do list 6</span></a>
					</form>
                </div>
            	<div style="padding:0 20px; text-align: center; margin-top: 30px">
                	<!-- To do list -->
					<form id="serverHealthInfo" action="ServerHealthInfo" method="get">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:false" style="width:100%;height:40px" onclick=""><span style="font-size: 16px">To do list 7</span></a>
					</form>
                </div>
            	<div style="padding:0 20px; text-align: center; margin-top: 30px">
                	<!-- To do list -->
					<form id="sessionTrackGet" action="SessionTrack" method="get">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:false" style="width:100%;height:40px" onclick=""><span style="font-size: 16px">To do list 8</span></a>
					</form>
                </div>
                <div style="padding:0 20px; text-align: center; margin-top: 30px">
                	<!-- To do list -->
					<form id="sessionTrackPost" action="SessionTrack" method="post">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:false" style="width:100%;height:40px" onclick=""><span style="font-size: 16px">To do list 9</span></a>
					</form>
                </div>
                <div style="padding:0 20px; text-align: center; margin-top: 30px">
                	<!-- To do list -->
					<form id="sessionTrackPost" action="SessionTrack" method="post">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:false" style="width:100%;height:40px" onclick="alert(document.cookie)"><span style="font-size: 16px">XSS攻击测试</span></a>
					</form>
                </div>
<% }%>
        </div>

        	<div style="padding:0 20px; text-align: center; margin-top: 30px">
				<a href="index.jsp" class="easyui-linkbutton" plain="true" outline="true" style="width:100%;height:40px" onclick="clearForm()"><span style="font-size: 16px">重新登录</span></a>
			</div>
    </div>




</body>
</html>