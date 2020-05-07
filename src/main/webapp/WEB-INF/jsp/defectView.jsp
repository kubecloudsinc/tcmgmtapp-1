<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Details of defect: ${fn:escapeXml(defect.name)}" nav="defects">
  <c:if test="${not empty param.success}">
    <tags:alert type="success" title="Success!" message="Defect has been saved."/>
  </c:if>
 <table>
  <tbody>
    <tr>
      <div class=container>
      <td><b>Defect Id:</b></td><td></td><td></td>
      <td>${defect.id}</td>
      <div class=container>
    </tr>
    <tr>
      <td><b>Status :</b></td><td></td><td></td><td>${statusMap[defect.status]}</td>
    </tr>
    <tr>
      <td><b>Defect reported By :</b></td><td></td><td></td><td>${defect.reportedBy}</td>
    </tr>
    <tr>
      <td><b>Product :</b></td><td></td><td></td><td>${productMap[defect.product]}</td>
    </tr>
    <tr>
      <td><b>Product Component :</b></td><td></td><td></td><td>${componentMap[defect.component]}</td>
    </tr>
    <tr>
      <td><b>Severity :</b></td><td></td><td></td><td>${severityMap[defect.severity]}</td>
    </tr>
    <tr>
      <td><b>Priority :</b></td><td></td><td></td><td>${priorityMap[defect.priority]}</td>
    </tr>
    <tr>
      <td><b>Defect Description:</b></td><td></td><td></td><td>${defect.description}</td>
    </tr>
  </tbody>
 </table>
 <div class="container">
    <div class="page-header"></div>
 </div>
 <a class="btn btn-info" href="<c:url value='/defects.html'/>">Go To All Defects</a>
</tags:page>