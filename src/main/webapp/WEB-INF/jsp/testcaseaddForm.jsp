<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Add Test Cases To Test Run" nav="testrun">
    <div class="container">
        <span class="badge badge-primary">Test Cases List</span>
    </div>
    <form:form commandName="testRunDTO">
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
                <c:forEach varStatus="index" items="${testRunDTO.testCases}">
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
            <button type="submit" class="btn btn-primary" name="save" value="save">Add Test Cases To Run</button>
        </div>
    </form:form>
</tags:page>