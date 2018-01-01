<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
 <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
<title>Welcome To My Google App Engine Beta Release 1.0 @Le Zhang</title>
</head>

<body>


<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
        pageContext.setAttribute("user", user);
%>

    <h1>Google App Engine Beta Release</h1>

	<table>
    <tr>
    <td>Welcome! ${user.nickname}
    </td>
	<td>
		<p>Hello, ${fn:escapeXml(user.nickname)}! (You can
    <a href="<%=userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
    </td>
    </tr>
      <tr>
        <td colspan="2" style="font-weight:bold;">Available service list:</td>
      </tr>
<!--       <tr> -->
<!--         <td><a href='/helloWorld.groovy'>GroovyServlet Test</a></td> -->
<!--       </tr> -->
      <tr>
        <td><a href='/ShowLast10visitors'>Show last 10 visitors</a></td>
      </tr>
      <tr>
        <td><a href='/ShowLastTenRecords'>Show last 10 records of temprature health.</a></td>
      </tr>
    <%
    } else {
%>

    <tr>
	 <td>
		<p>Hello! Please
    <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
    with your gmail account to select available service list.</p>
     </td>
	</tr>
<%
    }
%>
	</table>

<!--     <table> -->
<!--       <tr> -->
<!--         <td colspan="2" style="font-weight:bold;">Available service list:</td> -->
<!--       </tr> -->
<!--       <tr> -->
<!--         <td><a href='/helloWorld.groovy'>GroovyServlet Test</a></td> -->
<!--       </tr> -->
<!--       <tr> -->
<!--         <td><a href='/CloudSqlServlet'>Show last 10 visitors</a></td> -->
<!--       </tr> -->
<!--       <tr> -->
<!--         <td><a href='/ShowLastTenRecords'>Show last 10 records of temprature health.</a></td> -->
<!--       </tr> -->
<!--     </table> -->
</body>
</html>