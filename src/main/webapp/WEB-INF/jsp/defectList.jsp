<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="All Defects" nav="defects">
  <c:set value="${defectList}" var="defectPageList" />
  <c:choose>
    <c:when test="${empty defectPageList}">
      <p>No Defects</p>
    </c:when>
    <c:otherwise>
      <div class="container">
        <span class="badge badge-primary">Defect List</span>
      </div>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th><b>Defect Id</b></th>
            <th><b>Defect Name</b></th>
            <th><b>Status </b></th>
            <th><b>Product </b></th>
            <th><b>Component </b></th>
            <th><b>Severity </b></th>
            <th><b>Priority </b></th>
            <th><b>Reported By </b></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="defect" items="${defectPageList.pageList}">
            <c:url var="viewUrl" value="/defect.html">
              <c:param name="id" value="${defect.id}"/>
            </c:url>
            <c:url var="editUrl" value="/defect_form.html">
              <c:param name="id" value="${defect.id}"/>
            </c:url>
            <c:url var="deleteUrl" value="/defect_delete.html">
              <c:param name="id" value="${defect.id}"/>
            </c:url>
           <tr>
              <td class="number"><a href="${viewUrl}">${defect.id}</a></td>
              <td>${defect.name}</td>
              <td>${statusMap[defect.status]}</td>
              <td>${productMap[defect.product]}</td>
              <td>${componentMap[defect.component]}</td>
              <td>${severityMap[defect.severity]}</td>
              <td>${priorityMap[defect.priority]}</td>
              <td>${defect.reportedBy}</td>
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
                      <c:when test="${defectPageList.firstPage}">
                        <button class="btn btn-info" disabled >Prev</button>
                      </c:when>
                      <c:otherwise>
                          <c:url value="/defects.html" var="url">
                            <c:param name="navPage" value="prev"/>
                          </c:url>
                          <a href='<c:out value="${url}" />'>
                            <button class="btn btn-info" name="nav" >Prev</button>
                          </a>
                      </c:otherwise>
                  </c:choose>
                  <c:forEach begin="1" end="${defectPageList.pageCount}" step="1"  varStatus="tagStatus">
                      <c:choose>
                          <c:when test="${(defectPageList.page + 1) == tagStatus.index}">
                            <button class="btn btn-info" name="nav" disabled >${tagStatus.index}</button>
                          </c:when>
                          <c:otherwise>
                              <c:url value="/defects.html" var="url">
                                <c:param name="navPage" value="${tagStatus.index}"/>
                              </c:url>
                              <a href='<c:out value="${url}" />'>
                                <button class="btn btn-info" name="nav">${tagStatus.index}</button>
                              </a>
                          </c:otherwise>
                      </c:choose>
                  </c:forEach>
                  <c:choose>
                      <c:when test="${defectPageList.lastPage}">
                        <button class="btn btn-info" name="nav" disabled >Next</button>
                      </c:when>
                      <c:otherwise>
                          <c:url value="/defects.html" var="url">
                            <c:param name="navPage" value="next"/>
                          </c:url>
                          <a href='<c:out value="${url}" />'>
                            <button class="btn btn-info" name="nav">Next</button>
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
        executeDeleteAndRedirect(".deleteUrl", "defects.html");
      });
    </script>
  </security:authorize>
</tags:page>