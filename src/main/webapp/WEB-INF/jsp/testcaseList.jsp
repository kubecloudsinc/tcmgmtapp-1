<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Test Cases" nav="testcase">
  <c:choose>
    <c:when test="${fn:length(testCaseList) == 0}">
      <p>No Test Cases</p>
    </c:when>
    <c:otherwise>
      <table class="table table-striped table-hover">
        <thead>
            <th>
                <a class="addUrl btn btn-primary" href="<c:url value='/testcase_form.html'/>">
                    Add New Test Case
                </a>
            </th>
        </thead>
      </table>
      <div class="container">
        <span class="badge badge-primary">Test Cases List</span>
      </div>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th class="number">Id</th>
            <th>Test Description</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="testcase" items="${testCaseList}">
            <c:url var="viewUrl" value="/testcase.html">
              <c:param name="id" value="${testcase.id}"/>
            </c:url>
            <c:url var="editUrl" value="/testcase_form.html">
              <c:param name="id" value="${testcase.id}"/>
            </c:url>
            <c:url var="deleteUrl" value="/testcase_delete.html">
              <c:param name="id" value="${testcase.id}"/>
            </c:url>
           <tr>
              <td class="number"><a href="${viewUrl}">${testcase.id}</a></td>
              <td>${fn:escapeXml(testcase.testDescription)}</td>
              <security:authorize ifAllGranted="ROLE_ADMIN">
                  <td>
                    <a class="viewUrl btn btn-primary" href="${viewUrl}">View</a>
                    <a class="editUrl btn btn-info" href="${editUrl}">Edit</a>
                    <a class="deleteUrl btn btn-danger" href="${deleteUrl}">Delete</a>
                  </td>
                </security:authorize>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
  <security:authorize ifAllGranted="ROLE_ADMIN">
    <script type="text/javascript">
      $(document).ready(function() {
        executeDeleteAndRemoveContainer(".deleteUrl", "tr");
      });
    </script>
  </security:authorize>
</tags:page>