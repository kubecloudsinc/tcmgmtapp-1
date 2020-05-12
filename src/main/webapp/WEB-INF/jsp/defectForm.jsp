<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="${empty defect.name ? 'Add a new Defect' : 'Edit: '.concat(defect.name)}" nav="defects">
  <c:if test="${not empty param.success}">
    <tags:alert type="success" title="Success!" message="Saved the defect."/>
  </c:if>
  <form:form commandName="defect">
    <div class="form-horizontal">
        <tags:showFormErrors name="defect"/>
        <tags:textInput path="name" label="Defect Name" required="${true}" cssClass="form-control form-control-sm"/>
        <tags:textInput path="reportedBy.name" label="Reported By" cssClass="form-control form-control-sm" readonly="${true}" />
        <div class="control-group ${empty pageScope.error? '' : 'error'}">
            <form:label path="product" cssClass="control-label"><strong>Product :</strong></form:label>
            <div class="controls">
                <form:select path="product">
                    <form:options items="${productMap}"/>
                </form:select>
            </div>
        </div>
        <div class="control-group ${empty pageScope.error? '' : 'error'}">
            <form:label path="component" cssClass="control-label"><strong>Product Component :</strong></form:label>
            <div class="controls">
                <form:select path="component">
                    <form:options items="${componentMap}"/>
                </form:select>
            </div>
        </div>
        <div class="control-group ${empty pageScope.error? '' : 'error'}">
            <form:label path="priority" cssClass="control-label"><strong>Defect Priority :</strong></form:label>
            <div class="controls">
                <form:select path="priority">
                    <form:options items="${priorityMap}"/>
                </form:select>
            </div>
        </div>
        <div class="control-group ${empty pageScope.error? '' : 'error'}">
            <form:label path="severity" cssClass="control-label"><strong>Defect Severity :</strong></form:label>
            <div class="controls">
                <form:select path="severity">
                    <form:options items="${severityMap}"/>
                </form:select>
            </div>
        </div>
        <div class="control-group ${empty pageScope.error? '' : 'error'}">
            <form:label path="status" cssClass="control-label"><strong>Defect Status :</strong></form:label>
            <div class="controls">
                <form:select path="status">
                    <form:options items="${statusMap}"/>
                </form:select>
            </div>
        </div>
        <tags:textAreaInput path="description" label="Defect Description" required="${false}" cssClass="form-control form-control-sm"/>
        <div class="page-header"></div>
    </div>
    <div class="form-actions">
      <button type="submit" class="btn btn-primary" name="save" value="save">Save Defect</button>
    </div>
  </form:form>
</tags:page>