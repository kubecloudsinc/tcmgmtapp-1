<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="${empty testCaseDTO.testName ? 'Add a new test case' : 'Edit Test Case: '.concat(testCaseDTO.testName)}" nav="testcase">
  <c:if test="${not empty param.success}">
    <tags:alert type="success" title="Success!" message="Saved user."/>
  </c:if>
  <form:form commandName="testCaseDTO">
    <div class="form-horizontal">
      <tags:showFormErrors name="testCaseDTO"/>
      <tags:textInput path="testName" label="Test Case Name" required="${true}" cssClass="form-control form-control-sm"/>
      <tags:textInput path="testType" label="Test Type" required="${true}" cssClass="form-control form-control-sm"/>
      <tags:textAreaInput path="testDescription" label="Test Description" rows="3" />
      <tags:textAreaInput path="testSetup" label="Test Setup" rows="3" />
      <tags:textAreaInput path="expectedResult" label="Expected Result" rows="3" />
      <tags:booleanInput path="automated" label="Test Automated?" />
    </div>
    <div class="container">
      <table class="table table-striped table-hover" >
        <thead>
            <tr>
               <th scope="col">Test Step Number</th>
               <th scope="col">Test Step</th>
               <th scope="col">Test Step Result</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach varStatus="index" items="${testCaseDTO.testSteps}">
            <tr class="form-row form-group col">
                <td>
                    <tags:textInput path="testSteps[${index.count - 1}].testStepOrder" readonly="${true}" />
                </td>
                <td>
                    <tags:textAreaInput path="testSteps[${index.count - 1}].testStep" rows="1" />
                </td>
                <td>
                    <tags:textAreaInput path="testSteps[${index.count - 1}].testStepResult" rows="1" />
                </td>
            </tr>
            </c:forEach>
            <td>
                <c:url var="addStepUrl" value='/teststep_add.html'>
                    <c:param name="id" value="${testCaseDTO.id}"/>
                </c:url>
                <a class="backUrl btn btn-primary" href="${addStepUrl}">Add Test Step</a>
            </td>
        </tbody>
      </table>
     </div>
     <div class="form-actions">
      <button type="submit" class="btn btn-primary">Save</button>
     </div>
  </form:form>
</tags:page>