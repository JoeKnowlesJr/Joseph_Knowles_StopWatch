<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="style.css">
		<script src='stopwatch.js'></script>
		<title>StopWatch</title>
	</head>
	<body>
		<div class="container">
			<div class='spread'>
				<form action="StopWatchController" method="get">
					<input type='hidden' id='start' name='action' value='START' />
					<div><input class='btn' type="submit" value="Start"/></div>
				</form>
				<form action="StopWatchController" method="get">
					<input type='hidden' id='stop' name='action' value='STOP' />
					<div><input class='btn' type="submit" value="Stop"/></div>
				</form>
				<form action="StopWatchController" method="get">
					<input type='hidden' id='reset' name='action' value='RESET' />
					<div><input class='btn' type="submit" value="Reset"/></div>
				</form>
			</div>
			
			<fieldset>
			<legend><span class="legend-border">Current StopWatch</span></legend>
				<c:choose>
					<c:when test="${currentStopWatch != null}">
						<table>
							<thead>
								<tr>
									<th>Start Time</th>
									<th>Current Time</th>
									<th>Elapsed Time</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${currentStopWatch.getStartTime()}</td>
									<td id='currentTime'></td>
									<td>${currentStopWatch.getElapsedTime()}</td>
									<td class="btn-cell"><button class="btn-sm" onClick="history.go(0);">Update</button></td>
								</tr>
							</tbody>
						</table>
					</c:when>
					<c:otherwise >
						<H1>No StopWatch Running</H1>
					</c:otherwise>
				</c:choose>			
			</fieldset>
			
			<fieldset>
				<legend>History</legend>
				<table>
					<tr>
						<th>Start</th>
						<th>Stop</th>
						<th>Total</th>
					</tr>
					<c:set var='h' value='${history}'/>
					<c:set var='hSize' value='${history.size()}' />								
					<c:forEach var='i' begin='1' end='${hSize}' step='1'>
						<c:set var='sw' value='${h.get(hSize - i)}'/>
						<tr>
							<td><c:out value="${sw.getStartTime()}" /></td>
							<td><c:out value="${sw.getEndTime()}" /></td>
							<td><c:out value="${sw.getElapsedTime()}" /></td>
						</tr>
					</c:forEach>
				</table>				
			</fieldset>
		</div>
		<script>setInterval(updateCurrentTime, 1000);</script>	
	</body>
</html>








