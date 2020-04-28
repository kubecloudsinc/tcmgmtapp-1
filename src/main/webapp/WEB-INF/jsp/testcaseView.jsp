<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Details of test case: ${fn:escapeXml(testCase.testName)}" nav="testcase">
  <c:if test="${not empty param.success}">
    <tags:alert type="success" title="Success!" message="Test case has been saved."/>
  </c:if>
 <table>
  <tbody>
    <tr>
      <td><b>Test Desription:</b></td><td></td><td></td><td>${testCase.testDescription}</td>
    </tr>
    <tr>
      <td><b>Test Type:</b></td><td></td><td></td><td>${testCase.testType}</td>
    </tr>
    <tr>
      <td><b>Test Setup:</b></td><td></td><td></td><td>${testCase.testSetup}</td>
    </tr>
    <tr>
      <td><b>Automated:</b></td><td></td><td></td><td>${testCase.automated}</td>
    </tr>
    <tr>
      <td><b>Expected Result:</b></td><td></td><td></td><td>${testCase.expectedResult}</td>
    </tr>
  </tbody>
 </table>
 <div class="container">
    <div class="page-header"></div>
 </div>
 <c:choose>
    <c:when test="${fn:length(testCase.testSteps) == 0}">
        <table class="table table-striped table-hover">
            <thead>
                <th>Test Steps:</th>
            </thead>
            <tbody>
                <tr><td>This test has no steps</td></tr>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
         <table class="table table-striped table-hover">
             <thead>
                <tr>
                   <th>Test Step Number</th>
                   <th>Test Step</th>
                   <th>Test Step Result</th>
                </tr>
             </thead>
             <tbody>
               <c:forEach var="testStep" items="${testCase.testSteps}">
                    <tr>
                       <td>${testStep.testStepOrder}</td>
                       <td>${fn:escapeXml(testStep.testStep)}</a></td>
                       <td>${testStep.testStepResult}</td>
                    </tr>
               </c:forEach>
             </tbody>
         </table>
    </c:otherwise>
 </c:choose>
 <a class="btn btn-info" href="<c:url value='/testcases.html'/>">Go To Test Cases</a>
 <a class="btn btn-info" href="<c:url value='/testruns.html'/>">Go To Test Runs</a>
</tags:page>