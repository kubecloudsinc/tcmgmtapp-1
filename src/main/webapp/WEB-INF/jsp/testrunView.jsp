<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Details of test run: ${fn:escapeXml(testRun.name)}" nav="testrun">
  <c:if test="${not empty param.success}">
    <tags:alert type="success" title="Success!" message="Saved the test run case status."/>
  </c:if>
  <form:form commandName="testRun">
    <div class="container">
     <table>
      <tbody>
        <tr>
          <td><b>Test Run Name:</b></td><td></td><td></td><td>${testRun.name}</td>
        </tr>
        <tr>
          <td><b>Test Run Description:</b></td><td></td><td></td><td>${testRun.description}</td>
        </tr>
        <tr>
          <td><b>Test Run Status:</b></td><td></td><td></td><td>${runStatusMap[testRun.status]}</td>
        </tr>
        <fmt:formatDate var="createDate" type = "both" dateStyle = "short" timeStyle = "short" value = "${testRun.created}" />
        <tr>
          <td><b>Test Run Create Date:</b></td><td></td><td></td><td>${createDate}</td>
        </tr>
      </tbody>
     </table>
    </div>
    <div class="container">
    <div class="page-header"></div>
    </div>
    <div class="container">
        <span class="badge badge-primary">Test Cases in this run</span>
    </div>
    <c:choose>
        <c:when test="${fn:length(testRun.testCases) == 0}">
            <table class="table table-striped table-hover">
                <tbody>
                    <tr><td>This test run has no testcase</td></tr>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <table class="table table-striped table-hover table-condensed">
                <thead>
                    <tr>
                        <th>Test Case Id</th>
                        <th>Test Case Name</th>
                        <th>Test Case Description</th>
                        <th>Test Case Status</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach varStatus="index" var="testCaseRunStatus" items="${testRun.testCaseRunPageList.pageList}">
                        <form:hidden path="testCaseRunPageList.pageList[${index.count - 1}].testRunId"
                                                                        value="${testCaseRunStatus.testRun.id}" />
                        <tr>
                            <c:url var="viewUrl" value="/testcase.html">
                                <c:param name="id" value="${testCaseRunStatus.testCase.id}"/>
                            </c:url>
                            <td class="number"><a href="${viewUrl}">${testCaseRunStatus.testCase.id}</a>
                                <form:hidden path="testCaseRunPageList.pageList[${index.count - 1}].testCaseId"
                                                    value="${testCaseRunStatus.testCase.id}" />
                            </td>
                            <td>${fn:escapeXml(testCaseRunStatus.testCase.testName)}</td>
                            <td>${testCaseRunStatus.testCase.testDescription}</td>
                            <c:choose>
                                <c:when test="${not empty updateRun}">
                                    <td>
                                        <form:select path="testCaseRunPageList.pageList[${index.count - 1}].testCaseStatusId">
                                            <form:options items="${caseStatusMap}"/>
                                        </form:select>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        ${caseStatusMap[testCaseRunStatus.testCaseStatusId]}
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="w-25 p-3" style="background-color: #eee;">
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group btn-group-sm" role="group" aria-label="First group">
                        <c:choose>
                            <c:when test="${testRun.testCaseRunPageList.firstPage}">
                                <button type="submit" class="btn btn-info" name="nav" disabled >Prev</button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-info" name="nav" value="prev">Prev</button>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${testRun.testCaseRunPageList.pageCount}" step="1"  varStatus="tagStatus">
                            <c:choose>
                                <c:when test="${(testRun.testCaseRunPageList.page + 1) == tagStatus.index}">
                                    <button type="submit" class="btn btn-info" name="nav" disabled >${tagStatus.index}</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-info" name="nav" value="${tagStatus.index}">${tagStatus.index}</button>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${testRun.testCaseRunPageList.lastPage}">
                                <button type="submit" class="btn btn-info" name="nav" disabled >Next</button>
                            </c:when>
                            <c:otherwise>
                                <form:hidden path="navPage" value="next"/>
                                <button type="submit" class="btn btn-info" name="nav" value="next">Next</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
    <div class="w-25 p-3" style="background-color: #eee;">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
        <c:choose>
            <c:when test="${not empty updateRun}">
                <div class="btn-group mr-2" role="group" aria-label="First group">
                    <button type="submit" class="btn btn-primary" name="update" value="update">Update Test Case Status</button>
                </div>
            </c:when>
            <c:otherwise>
                <div class="btn-group mr-2" role="group" aria-label="First group">
                    <a class="backUrl btn btn-primary" href="<c:url value='/testruns.html'/>">Back</a>
                </div>
            </c:otherwise>
        </c:choose>
        </div>
    </div>
  </form:form>
</tags:page>