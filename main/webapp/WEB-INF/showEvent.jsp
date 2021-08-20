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
<title><c:out value="${event.eventName}"/></title>
</head>
<body>
	<h1><c:out value="${event.eventName}"/></h1>
	<h2>Host:<c:out value="${event.user.firstName}"/> <c:out value="${event.user.lastName}"/></h2>
	<h2>Date:<c:out value="${event.date}"/></h2>
	<h2>Location:<c:out value="${event.location}"/></h2>
	<h2>People who are attending this event: <c:out value="${event.joinedUsers.size()+1}"/></h2>

</body>
</html>