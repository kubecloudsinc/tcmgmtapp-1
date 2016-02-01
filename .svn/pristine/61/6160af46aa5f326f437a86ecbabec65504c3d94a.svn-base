<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Test Suite Status" nav="testSuiteStatus">
	<c:choose>
		<c:when test="${fn:length(testSuiteStatusList) == 0}">
			<p>No Test Suite Results</p>
		</c:when>
		<c:otherwise>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th class="number">Test Suite ID</th>
						<th>Test Suite Name</th>
						<th>Test Suite Status</th>
						<th>Update Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="testSuiteStatus" items="${testSuiteStatusList}">
						<c:url var="startTestSuiteUrl" value="/test_suite_status.html">
							<c:param name="testSuiteStatus" value="${testSuiteStatus}" />
						</c:url>
						<c:url var="testSuiteEmailUrl" value="/test_suite_email.html">
							<c:param name="testSuiteStatus" value="${testSuiteStatus}" />
						</c:url>
						<tr>
						<form:form method="post" action="${startTestSuiteUrl}" modelAttribute="testSuiteStatus">
							
								<td class="number">${testSuiteStatus.testSuiteId}
									<form:hidden path="testSuiteId" value="${testSuiteStatus.testSuiteId}"/>
									<form:hidden path="id" value="${testSuiteStatus.id}"/>
								</td>
								<td class="number">${testSuiteStatus.testSuite.testSuiteName}</td>
								<td>${testSuiteStatus.testSuiteStatus}</td>
								<td>${testSuiteStatus.updated}</td>
								<c:choose>
									<c:when test="${testSuiteStatus.testSuiteStatus eq 'Running'}">
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
									<a href="<c:url value='${testSuiteStatus.reportName}'/>">Latest ReportLink</a>
								</td>
						</form:form>
						<form:form method="post" action="${testSuiteEmailUrl}" modelAttribute="testSuiteStatus">
							<td>
								<button type="submit" class="btn btn-primary" >Send Email</button>
								<form:hidden path="testSuiteId" value="${testSuiteStatus.testSuiteId}"/>
								<form:hidden path="id" value="${testSuiteStatus.id}"/>
							</td>
						</form:form>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</tags:page>