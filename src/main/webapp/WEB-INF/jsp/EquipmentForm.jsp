<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Equipments" nav="equipment">
  <c:choose>
    <c:when test="${fn:length(equipmentList) == 0}">
      <p>No Equipment</p>
    </c:when>
    <c:otherwise>
      <table class="table table-striped table-hover">
        <thead>
				<tr>
					<th class="number">EquipmentID</th>
					<th>AdminStateCode</th>
					<th>AdminStateCode</th>
					<th>AssemblyNumber</th>
					<th>Revision</th>
				</tr>
        </thead>
        <tbody>
          <c:forEach var="equipment" items="${equipmentList}">
            <c:url var="viewUrl" value="/equipment_form.html">
              <c:param name="id" value="${equipment.equipmentId}"/>
            </c:url>
            <c:url var="editUrl" value="/equipment_form.html">
              <c:param name="id" value="${equipment.equipmentId}"/>
            </c:url>
            <c:url var="deleteUrl" value="/equipment_form.html">
              <c:param name="id" value="${equipment.equipmentId}"/>
            </c:url>
           <tr>
              <td class="number">${equipment.equipmentId}</td>
              <td><a href="${viewUrl}">${fn:escapeXml(equipment.administrativeStateCode)}</a></td>
              <td>${fn:escapeXml(f:trimToLength(equipment.administrativeStateCode, 30))}</td>
              <td>${fn:escapeXml(f:trimToLength(equipment.assemblyNumber, 30))}</td>
              <td>
                <a href="mailto:${fn:escapeXml(equipment.assemblyRevision)}">
                  ${fn:escapeXml(f:trimToLength(equipment.assemblyRevision, 30))}
                </a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</tags:page>