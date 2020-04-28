<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Test Cases" nav="testcase">
  <c:set value="${testCaseList}" var="testCasePageList" />
  <c:choose>
    <c:when test="${empty testCasePageList}">
      <p>No Test Cases</p>
    </c:when>
    <c:otherwise>
      <div class="container">
        <span class="badge badge-primary">Test Cases List</span>
      </div>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th class="number">Id</th>
            <th>Test Name</th>
            <th>Test Description</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="testcase" items="${testCasePageList.pageList}">
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
              <td>${fn:escapeXml(testcase.testName)}</td>
              <td>${fn:escapeXml(testcase.testDescription)}</td>
              <td>
                <a class="viewUrl btn btn-primary" href="${viewUrl}">View</a>
                <a class="editUrl btn btn-info" href="${editUrl}">Edit</a>
                <security:authorize ifAllGranted="ROLE_ADMIN">
                    <a class="deleteUrl btn btn-danger" href="${deleteUrl}">Delete</a>
                </security:authorize>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <div class="w-25 p-3" style="background-color: #eee;">
      <table class="table table-striped table-hover">
        <thead>
            <tr>
                <td>
                  <c:choose>
                      <%-- Show Prev as link if not on first page --%>
                      <c:when test="${testCasePageList.firstPage}">
                        <span><strong>Prev</strong></span>
                      </c:when>
                      <c:otherwise>
                          <c:url value="/testcases.html" var="url">
                            <c:param name="navPage" value="prev"/>
                          </c:url>
                          <a href='<c:out value="${url}" />'><strong>Prev</strong></a>
                      </c:otherwise>
                  </c:choose>
                  <c:forEach begin="1" end="${testCasePageList.pageCount}" step="1"  varStatus="tagStatus">
                      <c:choose>
                          <%-- In PagedListHolder page count starts from 0 --%>
                          <c:when test="${(testCasePageList.page + 1) == tagStatus.index}">
                              <span><strong>${tagStatus.index}</strong></span>
                          </c:when>
                          <c:otherwise>
                              <c:url value="/testcases.html" var="url">
                                <c:param name="navPage" value="${tagStatus.index}"/>
                              </c:url>
                              <a href='<c:out value="${url}" />'><strong>${tagStatus.index}</strong></a>
                          </c:otherwise>
                      </c:choose>
                  </c:forEach>
                  <c:choose>
                      <%-- Show Next as link if not on last page --%>
                      <c:when test="${testCasePageList.lastPage}">
                        <span><strong>Next</strong></span>
                      </c:when>
                      <c:otherwise>
                          <c:url value="/testcases.html" var="url">
                            <c:param name="navPage" value="next"/>
                          </c:url>
                          <a href='<c:out value="${url}" />'><strong>Next</strong></a>
                      </c:otherwise>
                  </c:choose>
                </c:otherwise>
              </c:choose>
          </td>
         </tr>
     </thead>
  </table>
  </div>
  <security:authorize ifAllGranted="ROLE_ADMIN">
    <script type="text/javascript">
      $(document).ready(function() {
        executeDeleteAndRemoveContainer(".deleteUrl", "tr");
      });
    </script>
  </security:authorize>
</tags:page>