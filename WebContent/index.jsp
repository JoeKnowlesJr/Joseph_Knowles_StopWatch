<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="style.css" type="text/css" />
		<meta charset="ISO-8859-1">
		<title>StopWatch</title>
	</head>
	<body>
<%
	Timer currentTimer = session.getAttribute("currentTimer");

%>
		<div class='spread'>
			<form action="Start" method="get">
				<div><input type="submit" value="Start"/></div>
			</form>
			<form action="Stop" method="get">
				<div><input type="submit" value="Stop"/></div>
			</form>
			<form action="Reset" method="get">
				<div><input type="submit" value="Reset"/></div>
			</form>
		</div>
		
		<table>
			<tr>
				<th>Start</th>
				<th>Stop</th>
				<th>Total</th>
			</tr>
			
		</table>
	</body>
</html>