<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Schedule" nav="schedule">
	<c:choose>
		<c:when test="${fn:length(scheduleList) == 0}">
			<p>No Schedule</p>
		</c:when>
		<c:otherwise>
			<form:form method="post" action="${scheduleUrl}" modelAttribute="schedule">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<td>* Please select a Schedule</td>
						</tr>
						<tr>
							<th>Select</th>
							<th>Schedule Task Name</th>
							<th>Current Task Status</th>
							<th>Update Time</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="schedule" items="${scheduleList}">
							<c:url var="scheduleUrl" value="/schedule.html">
								<c:param name="schedule" value="${schedule}" />
							</c:url>
							<tr>
								<td><form:radiobutton path="id" value="${schedule.id}" /></td>
								<td>${schedule.scheduleName}</td>
								<td>${schedule.status}</td>
								<td>${schedule.updated}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="control-group">
				    Enter the user id pwd for the CCO ID if the monitoring needs to run on an internal user id so that not to expose the employee's password.
				</div>
<%-- 				
				<div class="control-group">
					<label class="control-label" for="app_username">UserName</label>
					<div class="controls">
						<form:input type="text" id="app_username" path="userName" placeholder="CCO ID"/>
					</div>
					<label class="control-label" for="app_password">Password</label>
					<div class="controls">
						<form:input type="password" id="app_password" path="password" placeholder="Password" />
					</div>
				</div>
--%>				
				<div>
					<label for="emp_ccoid">CCO ID</label>
						<form:input type="text" id="emp_ccoid" path="userName" placeholder="CCO ID"/>
					<label for="emp_ccoid_password">Password</label>
						<form:input type="password" id="emp_ccoid_password" path="password" placeholder="Password" />
				</div>
				<div class="controls">
					<button type="submit" class="btn btn-primary">Schedule/Stop</button>
				</div>
			</form:form>
		</c:otherwise>
	</c:choose>
</tags:page>