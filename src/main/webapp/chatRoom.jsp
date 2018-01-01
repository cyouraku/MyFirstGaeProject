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
<title>Chat Room</title>
<link type="text/css" rel="stylesheet" href="${jQueryPath}/themes/metro/easyui.css" />
<link type="text/css" rel="stylesheet" href="${jQueryPath}/themes/mobile.css" />
<link type="text/css" rel="stylesheet" href="${jQueryPath}/themes/icon.css" />
<!-- try to change theme by user selecting -->
<link type="text/css" rel="stylesheet" href="${jQueryPath}/themes/material/easyui.css" />
<script type="text/javascript" src="${jQueryPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jQueryPath}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${jQueryPath}/jquery.easyui.mobile.js"></script>
<script type="text/javascript">
//semd msg
function send() {
	var message = document.getElementById('text').value;
	alert(message)
	  $.post("SendMsgAjax",{Message:message},function(result){
		       setMessageInnerHTML(result);
		  });
}
//output msg on web page
function setMessageInnerHTML(innerHTML) {
	document.getElementById('message').innerHTML += innerHTML + '<br/>';
}
</script>
</head>
<body>
    <div class="easyui-navpanel">
        <header>
            <div class="m-toolbar">
                <div class="m-title">聊天室</div>
                <div class="m-left">
                    <a href="javascript:void(0)" class="easyui-linkbutton m-back" plain="true" outline="true" onclick="location.href='AppMenu.jsp'">返回</a>
                </div>
<!--                 <div class="m-right"> -->
<!--                     <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" outline="true">查询</a> -->
<!--                 </div> -->
            </div>
        </header>
        <div id="message"></div>
        <footer style="padding:2px 3px">
<!--          	<form id="textForm" action = "SendMsgServlet" method="post"> -->
            	<input id="text" type="text" name="Message" class="easyui-textbox" prompt="type something here" style="width:80%;height:40px;font-size:16px;padding:5px"><button class="easyui-linkbutton" style="width:20%;height:40px" onclick="send()">Send</button>
<!--             </form> -->
        </footer>
    </div>
</body>
</html>