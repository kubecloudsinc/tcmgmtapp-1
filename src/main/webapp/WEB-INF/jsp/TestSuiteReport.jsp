<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Test Suite Report" nav="testSuiteReport">
	<c:choose>
		<c:when test="${fn:length(testSuiteStatusList) == 0}">
			<p>No Test Suite Results</p>
		</c:when>
		<c:otherwise>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th class="number">Test Suite Status ID</th>
						<th>Test Suite Description</th>
						<th>Test Suite Status</th>
						<th>Create Date</th>
						<th>Update Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="testSuiteStatus" items="${testSuiteStatusList}">
						<c:url var="startTestSuiteUrl" value="/test_suite_report.html">
							<c:param name="testSuiteStatus" value="${testSuiteStatus}" />
						</c:url>
						<form:form method="post" action="${startTestSuiteUrl}" modelAttribute="testSuiteStatus">
							<tr>
								<td class="number">${testSuiteStatus.id}
									<form:hidden path="id" value="${testSuiteStatus.id}"/>
								</td>
								<td class="number">${testSuiteStatus.testSuiteId}
									<form:hidden path="testSuiteId" value="${testSuiteStatus.testSuiteId}"/>
								</td>
								<td>${testSuiteStatus.testSuiteStatus}
									<form:hidden path="testSuiteStatus" value="${testSuiteStatus.testSuiteStatus}"/>
								</td>
								<td>${testSuiteStatus.created}
									 <%-- <form:hidden path="created" value="${testSuiteStatus.created}"/> --%> 
								</td>
								<td>${testSuiteStatus.updated}
									<%-- <form:hidden path="updated" value="${testSuiteStatus.updated}"/> --%>	  
								</td>
								<td>
								<a href="<c:url value='${testSuiteStatus.reportName}'/>">Latest ReportLink</a>
								</td>
								<c:choose>
									<c:when test="${testSuiteStatus.testSuiteStatus eq 'Running'}">
										<td>
											<div class="control-group">
					    						System cannot generate a report as Test Suite is Running.
					    						<a href="<c:url value='/reports/index.htm'/>">Test Status</a>
											</div>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<button type="submit" class="btn btn-primary" >Generate Report</button>
											<a href="<c:url value='/reports/index.htm'/>">Test Status</a>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</form:form>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</tags:page>