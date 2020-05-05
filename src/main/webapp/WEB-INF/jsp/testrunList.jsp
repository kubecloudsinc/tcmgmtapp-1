<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Test Runs" nav="testrun">
  <c:set value="${testRunList}" var="testRunPageList" />
  <c:if test="${not empty param.success}">
    <tags:alert type="success" title="Success!" message="Saved Test Run."/>
  </c:if>
  <c:choose>
    <c:when test="${empty testRunPageList}">
      <p>No Test Runs</p>
    </c:when>
    <c:otherwise>
      <div class="container">
        <span class="badge badge-primary">Test Runs List</span>
      </div>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th class="number">Id</th>
            <th>Test Run Name</th>
            <th>Test Run Description</th>
            <th>Test Run Status</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="testrun" items="${testRunPageList.pageList}">
            <c:url var="viewUrl" value="/testrun.html">
              <c:param name="id" value="${testrun.id}"/>
            </c:url>
            <c:url var="updateUrl" value="/testruncase_update.html">
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
                <td>${fn:escapeXml(statusMap[testrun.status])}</td>
                <td>
                  <a class="viewUrl btn btn-primary" href="${viewUrl}">View</a>
                  <a class="updateUrl btn btn-primary" href="${updateUrl}">Update Status</a>
                  <security:authorize ifAllGranted="ROLE_ADMIN">
                    <a class="editUrl btn btn-info" href="${editUrl}">Edit</a>
                    <a class="deleteUrl btn btn-danger" href="${deleteUrl}">Delete</a>
                  </security:authorize>
                </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <div class="w-25 p-3" style="background-color: #eee;">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group btn-group-sm" role="group" aria-label="First group">
              <c:choose>
                  <c:when test="${testRunPageList.firstPage}">
                    <button class="btn btn-info" name="add" disabled >Prev</button>
                  </c:when>
                  <c:otherwise>
                      <c:url value="/testruns.html" var="url">
                        <c:param name="navPage" value="prev"/>
                      </c:url>
                      <a href='<c:out value="${url}" />'>
                        <button class="btn btn-info" name="add" >Prev</button>
                      </a>
                  </c:otherwise>
              </c:choose>
              <c:forEach begin="1" end="${testRunPageList.pageCount}" step="1"  varStatus="tagStatus">
                  <c:choose>
                      <c:when test="${(testRunPageList.page + 1) == tagStatus.index}">
                        <button class="btn btn-info" name="add" disabled >${tagStatus.index}</button>
                      </c:when>
                      <c:otherwise>
                          <c:url value="/testruns.html" var="url">
                            <c:param name="navPage" value="${tagStatus.index}"/>
                          </c:url>
                          <a href='<c:out value="${url}" />'>
                            <button class="btn btn-info" name="add">${tagStatus.index}</button>
                          </a>
                      </c:otherwise>
                  </c:choose>
              </c:forEach>
              <c:choose>
                  <c:when test="${testRunPageList.lastPage}">
                    <button class="btn btn-info" name="add" disabled >Next</button>
                  </c:when>
                  <c:otherwise>
                      <c:url value="/testruns.html" var="url">
                        <c:param name="navPage" value="next"/>
                      </c:url>
                      <a href='<c:out value="${url}" />'>
                        <button class="btn btn-info" name="add">Next</button>
                      </a>
                  </c:otherwise>
              </c:choose>
            </div>
        </div>
      </div>
    </c:otherwise>
  </c:choose>
  <security:authorize ifAllGranted="ROLE_ADMIN">
    <script type="text/javascript">
      $(document).ready(function() {
        executeDeleteAndRedirect(".deleteUrl", "testruns.html");
      });
    </script>
  </security:authorize>
</tags:page>