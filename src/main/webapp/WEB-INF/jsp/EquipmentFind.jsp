<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Query Equipment">
	<c:if test="${not empty param.success}">
		<tags:alert type="success" title="Success!" message="Saved user." />
	</c:if>
	<form:form commandName="equipment" cssClass="form-horizontal">
		<tags:showFormErrors name="equipment" />
		<tags:textInput path="equipmentId" label="Equipment ID"
			required="${true}" />
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Search</button>
		</div>
		<c:choose>
			<c:when test="${equipment.equipmentId == null}">
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
					<tbody></tbody>
				</table>
				<p>No Equipments</p>
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
						<tr>
							<td class="number">${equipment.equipmentId}</td>
							<td>${equipment.administrativeStateCode}</td>
							<td>${f:trimToLength(equipment.administrativeStateCode, 30)}</td>
							<td>${f:trimToLength(equipment.assemblyNumber, 30)}</td>
							<td>${f:trimToLength(equipment.assemblyRevision, 30)}</td>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</form:form>
</tags:page>