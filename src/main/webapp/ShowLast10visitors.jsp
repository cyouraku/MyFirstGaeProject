<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="java.util.*" %>
<%@ page import= "com.costuary.bean.VisitorBean"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <meta http-equiv="refresh" content="20" charset="UTF-8"> -->
<title>Show Last 10 Records of Temprature</title>
<script>
    var myDate;
    setInterval(function(){
        myDate = new Date();
        showTime.innerHTML = myDate.toLocaleString();
    },1000)
</script>
</head>
<body>


<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    String userNickname =  user.getNickname();
    if (user != null && "Tim.Zhang1981".equals(userNickname)) {
        pageContext.setAttribute("user", user);
%>

<div id = "showTime"></div>
<div id= "showDataRecords">
<p>Hello, ${fn:escapeXml(user.nickname)}! Welcome back to admin home!</p>
<table border = "1">
<tr>
<th>
  IP Addr:
</th>
<th>
  Addr Type:
</th>
<th>
 TIME:
</th>
</tr>
<%
List<VisitorBean> beanList = (List) request.getAttribute("visitorBean");
VisitorBean bean = null;
%>
<%
if(beanList != null){
	Iterator<VisitorBean> iter = beanList.iterator();
	while(iter.hasNext()){
	   bean = iter.next();
       out.print("<tr>"
    		   + "<td>"
    		   + bean.getVisitIP()
    		   + "</td>"
    	       + "<td>"
    	       + bean.getAddressType()
    	       + "</td>"
    	       + "<td>"
    	       + bean.getTimeStamp()
    	       + "</td>"
    		   + "</tr>"
    		   );
	}
}
%>
</table>

<%
    } else {
%>
<table>
  <tr>
	 <td>
		<p>Sorry but the website admin could
    <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">sign in</a>
    to use this service.</p>
     </td>
 </tr>
</table>
<%
    }
%>
</div>
</body>
</html>