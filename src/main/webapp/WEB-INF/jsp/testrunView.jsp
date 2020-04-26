<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Details of test run: ${fn:escapeXml(testRun.name)}" nav="testrun">
<div class="container">
 <table>
  <tbody>
    <tr>
      <td><b>Test Run Name:</b></td><td></td><td></td><td>${testRun.name}</td>
    </tr>
    <tr>
      <td><b>Test Run Description:</b></td><td></td><td></td><td>${testRun.description}</td>
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
         <table class="table table-striped table-hover">
             <thead>
                <tr>
                 <th>Test Case Id</th>
                 <th>Test Case Name</th>
                 <th>Test Case Description</th>
                 <th>Test Case Type</th>
                </tr>
             </thead>
             <tbody>
               <c:forEach var="testCase" items="${testRun.testCases}">
                    <tr>
                       <c:url var="viewUrl" value="/testcase.html">
                        <c:param name="id" value="${testCase.id}"/>
                       </c:url>
                       <td class="number"><a href="${viewUrl}">${testCase.id}</a></td>
                       <td>${fn:escapeXml(testCase.testName)}</a></td>
                       <td>${testCase.testDescription}</td>
                       <td>${testCase.testType}</td>
                    </tr>
               </c:forEach>
                   <th>
             </tbody>
         </table>
    </c:otherwise>
 </c:choose>
 <a class="backUrl btn btn-primary" href="<c:url value='/testruns.html'/>">Back</a>
</tags:page>