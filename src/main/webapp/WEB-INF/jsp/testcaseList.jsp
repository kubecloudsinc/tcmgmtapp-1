<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="All Test Cases" nav="testcase">
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
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group btn-group-sm" role="group" aria-label="First group">
                  <c:choose>
                      <c:when test="${testCasePageList.firstPage}">
                        <button class="btn btn-info" name="add" disabled >Prev</button>
                      </c:when>
                      <c:otherwise>
                          <c:url value="/testcases.html" var="url">
                            <c:param name="navPage" value="prev"/>
                          </c:url>
                          <a href='<c:out value="${url}" />'>
                            <button class="btn btn-info" name="add" >Prev</button>
                          </a>
                      </c:otherwise>
                  </c:choose>
                  <c:forEach begin="1" end="${testCasePageList.pageCount}" step="1"  varStatus="tagStatus">
                      <c:choose>
                          <c:when test="${(testCasePageList.page + 1) == tagStatus.index}">
                            <button class="btn btn-info" name="add" disabled >${tagStatus.index}</button>
                          </c:when>
                          <c:otherwise>
                              <c:url value="/testcases.html" var="url">
                                <c:param name="navPage" value="${tagStatus.index}"/>
                              </c:url>
                              <a href='<c:out value="${url}" />'>
                                <button class="btn btn-info" name="add">${tagStatus.index}</button>
                              </a>
                          </c:otherwise>
                      </c:choose>
                  </c:forEach>
                  <c:choose>
                      <c:when test="${testCasePageList.lastPage}">
                        <button class="btn btn-info" name="add" disabled >Next</button>
                      </c:when>
                      <c:otherwise>
                          <c:url value="/testcases.html" var="url">
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
        executeDeleteAndRedirect(".deleteUrl", "testcases.html");
      });
    </script>
  </security:authorize>
</tags:page>
