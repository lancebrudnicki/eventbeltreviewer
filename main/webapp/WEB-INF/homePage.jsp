<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome</title>
</head>
<body>
	<h1>Welcome, Ninja</h1>
	<a href="/logout">Logout</a>
		<h1> Here are some of the events in your State:</h1>
				<table>
					<thead>
						<tr>
							<th>Name</th>
							<th>Date</th>
							<th>Location</th>
							<th>Host</th>
							<th>Action/Status</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${events}" var="event">
						<c:if test="${user.state == event.state}">
							<tr>
								<td><a href="/events/${event.id}"><c:out value="${event.eventName}"/></a></td>
								<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${event.date}" /></td>
								<td><c:out value="${event.location}"/></td>
								<td><c:out value="${event.user.firstName}"/></td>
								<td style="display: flex;">
									<c:if test="${user.id == event.user.id }">
											<a href="/events/${event.id}/edit">Edit</a>
											<form action="/delete/${event.id}" method="post">
											    <input type="hidden" name="_method" value="delete">
											    <input type="submit" value="Delete">
											</form>
									</c:if>
									<c:if test="${event.user.id != user.id}">
		                                    
		                                    <c:if test="${event.joinedUsers.contains(user)}">
		                                        <p>Joining</p>
		                                        <a href="/cancel/${event.id}">Cancel</a>        
		                                    </c:if>
		                                    <c:if test="${!event.joinedUsers.contains(user)}">
		                                        <a href="/events/join/${event.id}">Join</a>        
		                                    </c:if> 
		                            </c:if>
								</td>
							</tr>		
						</c:if>
					</c:forEach>
					</tbody>		
				</table>
			<h1>Here are some of the events in other states:</h1>
				<table>
					<thead>
						<tr>
							<th>Name</th>
							<th>Date</th>
							<th>Location</th>
							<th>State</th>
							<th>Host</th>
							<th>Action/Status</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${events}" var="event">
						<c:if test="${user.state != event.state}">
							<tr>
								<td><a href="/events/${event.id}"><c:out value="${event.eventName}"/></a></td>
								<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${event.date}" /></td>
								<td><c:out value="${event.location}"/></td>
								<td><c:out value="${event.state.name}"/></td>
								<td><c:out value="${event.user.firstName}"/></td>
								<td style="display: flex;">
									<c:if test="${user.id == event.user.id }">
											<a href="/events/${event.id}/edit">Edit</a>
											<form action="/delete/${event.id}" method="post">
											    <input type="hidden" name="_method" value="delete">
											    <input type="submit" value="Delete">
											</form>
									</c:if>
									<c:if test="${event.user.id != user.id}">
		                                    
		                                    <c:if test="${event.joinedUsers.contains(user)}">
		                                        <p>Joining</p>
		                                        <a href="/cancel/${event.id}">Cancel</a>        
		                                    </c:if>
		                                    <c:if test="${!event.joinedUsers.contains(user)}">
		                                        <a href="/events/join/${event.id}">Join</a>        
		                                    </c:if> 
		                            </c:if>
								</td>
							</tr>		
						</c:if>
					</c:forEach>
					</tbody>		
				</table>

			
			
			<h1>Create an Event</h1>
			
			
		<form:form method="post" action="/createevent" modelAttribute="createevent">
		<p>
            <form:label path="eventName">Name:</form:label>
            <form:input type="text" path="eventName"/>
            <form:errors path="eventName"/>
        </p>
        <p>
            <form:label path="date">Date:</form:label>
            <form:input type="date" path="date"/>
            <form:errors path="date"/>
        </p>
        <p>
            <form:label path="location">Location:</form:label>
            <form:input type="text" path="location"/>    
            <form:errors path="location"/>
	
        	
        	<form:select path="state">
				<form:options items="${states}" itemValue="id" itemLabel="name"></form:options>
			</form:select>
			
        </p>
        <input type="submit" value="Create"/>
		</form:form>

		
</body>
</html>