<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Test Status" nav="testStatus">
	<c:choose>
		<c:when test="${fn:length(testStatusList) == 0}">
			<p>No Test Results</p>
		</c:when>
		<c:otherwise>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th class="number">Test Status ID</th>
						<th>Test Description</th>
						<th>Test Status</th>
						<th>Create Date</th>
						<th>Update Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="testStatus" items="${testStatusList}">
						<c:url var="startTestUrl" value="/test_status.html">
							<c:param name="testStatus" value="${testStatus}" />
						</c:url>
						<form:form method="post" action="${startTestUrl}" modelAttribute="testStatus">
							<tr>
								<td class="number">${testStatus.id}
									<form:hidden path="id" value="${testStatus.id}"/>
								</td>
								<td class="number">${testStatus.test.testDescription}
									<form:hidden path="testId" value="${testStatus.testId}"/>
								</td>
								<%-- 								
								<td class="number">${testStatus.testId}
									<form:hidden path="testId" value="${testStatus.testId}"/>
								</td>
								--%>
								<td>${testStatus.testStatus}
									<form:hidden path="testStatus" value="${testStatus.testStatus}"/>
								</td>
								<td>${testStatus.created}</td>
								<td>${testStatus.updated}</td>
								<c:choose>
									<c:when test="${testStatus.testStatus eq 'Running'}">
										<td>
											<button type="submit" class="btn btn-primary" disabled="disabled">Running</button>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<button type="submit" class="btn btn-primary" >Start</button>
										</td>
									</c:otherwise>
								</c:choose>
								<td>
									<a href="<c:url value='${testStatus.reportName}'/>">Latest ReportLink</a>
								</td>
							</tr>
						</form:form>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</tags:page>