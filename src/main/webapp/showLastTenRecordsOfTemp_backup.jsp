<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="java.util.*" %>
<%-- <%@ page import= "com.costuary.TemperatureRecordBean"%> --%>
<%@ page import= "com.costuary.bean.XmlBean"%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- //每隔五分钟自动刷新页面<meta http-equiv="refresh" content="300" charset="UTF-8"> -->
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
    if (user != null) {
        pageContext.setAttribute("user", user);
%>

<div id = "showTime"></div>
<div id= "showDataRecords">
<table border = "1">
<tr>
<th>
  ID
</th>
<th>
 GPU(°C)
</th>
<th>
 CPU(°C)
</th>
<th>
 FAN(RPM)
</th>
<th>
 TIMESTAMP
</th>
</tr>
<%
//use SQL data base to get data
// List<TemperatureRecordBean> beanList = (List) request.getAttribute("tempBean");
// TemperatureRecordBean bean = null;

//use local xml file to get data
List<XmlBean> beanList = (List) request.getAttribute("tempBean");
XmlBean bean = null;
%>
<%
if(beanList != null){
// 	Iterator<TemperatureRecordBean> iter = beanList.iterator();
	Iterator<XmlBean> iter = beanList.iterator();
	while(iter.hasNext()){
	   bean = iter.next();
       out.print("<tr><td>"
    		   + bean.getId()
    		   + "</td>"
    		   + "<td>"
    		   + bean.getGpu()
    		   + "</td>"
    	       + "<td>"
    	       + bean.getCpu()
    	       + "</td>"
    	       + "<td>"
    	       + bean.getFan()
    	       + "</td>"
    	       + "<td>"
    	       + bean.getTimestampStr()
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
		<p>Sorry but you have to
    <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">sign in</a>
    with your gmail account to use the service.</p>
     </td>
 </tr>
</table>
<%
    }
%>
</div>
</body>
</html>