<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="${empty testRun.name ? 'Add a new test Run' : 'Edit: '.concat(testRun.name)}" nav="testRun">
  <c:if test="${not empty param.success}">
    <tags:alert type="success" title="Success!" message="Saved user."/>
  </c:if>
  <c:if test="${not empty param.addcase}">
      <tags:alert type="success" title="Success!" message="Add test cases success."/>
   </c:if>
  <form:form commandName="testRun">
    <div class="form-horizontal">
      <tags:showFormErrors name="testRun"/>
      <tags:textInput path="name" label="Test Run Name" required="${true}" cssClass="form-control form-control-sm"/>
      <tags:textInput path="description" label="Test Run Description" required="${false}" cssClass="form-control form-control-sm"/>
      <fmt:formatDate var="createDate" type = "both" dateStyle = "short" timeStyle = "short" value = "${testRun.created}" />
      <div class="control-group ${empty pageScope.error? '' : 'error'}">
        <form:label path="created" cssClass="control-label"><strong>Create Date :</strong></form:label>
        <div class="controls">
            <form:input path="created" cssClass="form-control form-control-sm" readonly="true" value="${createDate}"  />
        </div>
      </div>
    </div>
  <div class="container">
    <div class="page-header"></div>
  </div>
  <div class="container">
    <span class="badge badge-primary">Test Cases in this run</span>
  </div>
    <div class="container">
      <table class="table table-striped table-hover" >
        <thead>
        <tr>
         <th></th>
         <th>Test Case Id</th>
         <th>Test Case Name</th>
         <th>Test Case Description</th>
         <th>Test Case Type</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach varStatus="index" items="${testRun.testCases}">
            <tr class="form-row form-group col">
                <td>
                    <form:checkbox path="testCases[${index.count - 1}].checked" value="true"/>
                </td>
                <td>
                    <tags:textInput path="testCases[${index.count - 1}].id" readonly="${true}" />
                </td>
                <td>
                    <tags:textInput path="testCases[${index.count - 1}].testName" readonly="${true}" />
                </td>
                <td>
                    <tags:textInput path="testCases[${index.count - 1}].testDescription" readonly="${true}" />
                </td>
                <td>
                    <tags:textInput path="testCases[${index.count - 1}].testType" readonly="${true}" />
                </td>
            </tr>
            </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="form-actions">
      <table class="table table-striped table-hover" >
        <tbody>
            <tr>
                <td>
                    <button type="submit" class="btn btn-info" name="add" value="add">Add Test Cases</button>
                    <button type="submit" class="btn btn-info" name="remove" value="remove">Remove Test Cases</button>
                </td>
            </tr>
        </tbody>
      </table>
      <button type="submit" class="btn btn-primary" name="update" value="update">Create or Update Test Run</button>
    </div>
  </form:form>
</tags:page>