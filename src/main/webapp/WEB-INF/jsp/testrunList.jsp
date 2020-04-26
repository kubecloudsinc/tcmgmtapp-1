<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Test Runs" nav="testrun">
  <c:choose>
    <c:when test="${fn:length(testRunList) == 0}">
      <p>No Test Runs</p>
    </c:when>
    <c:otherwise>
      <table class="table table-striped table-hover">
        <thead>
            <th>
                <a class="addUrl btn btn-primary" href="<c:url value='/testrun_form.html'/>">
                    Add New Test Run
                </a>
            </th>
        </thead>
      </table>
      <div class="container">
        <span class="badge badge-primary">Test Runs List</span>
      </div>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th class="number">Id</th>
            <th>Test Run Name</th>
            <th>Test Run Description</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="testrun" items="${testRunList}">
            <c:url var="viewUrl" value="/testrun.html">
              <c:param name="id" value="${testrun.id}"/>
            </c:url>
            <c:url var="editUrl" value="/testrun_form.html">
              <c:param name="id" value="${testrun.id}"/>
            </c:url>
            <c:url var="deleteUrl" value="/testrun_delete.html">
              <c:param name="id" value="${testrun.id}"/>
            </c:url>
           <tr>
              <td class="number"><a href="${viewUrl}">${testrun.id}</a></td>
              <td>${fn:escapeXml(testrun.name)}</td>
              <td>${fn:escapeXml(testrun.description)}</td>
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