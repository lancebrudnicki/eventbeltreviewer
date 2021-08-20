<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Events</title>
</head>
<body>
	<h1><c:out value="${event.eventName}"></c:out></h1>
		<form:form action="/events/${event.id}/edit" method="POST" modelAttribute="event">
		    <input type="hidden" name="_method" value="put">
		    <form:input type="hidden" path="user" value="${event.user.id}"/>
		    <p>
		        <form:label path="eventName">Name</form:label>
		        <form:errors path="eventName"/>
		        <form:input path="eventName"/>
		    </p>
		    <p>
		        <form:label path="date">Date</form:label>
		        <form:errors path="date"/>
		        <form:input type="date" path="date"/>
		    </p>
		    <p>
		        <form:label path="location">Location</form:label>
		        <form:errors path="location"/>
		        <form:input path="location"/>
		    	
		    	<form:select path="state">
					<form:options items="${states}" itemValue="id" itemLabel="name"></form:options>
				</form:select>	
		    </p>
		    
		    <input type="submit" value="Submit"/>
		</form:form>
</body>
</html>