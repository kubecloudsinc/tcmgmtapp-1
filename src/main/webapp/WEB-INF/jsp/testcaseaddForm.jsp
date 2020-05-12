<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<tags:page title="Add Test Cases To Test Run" nav="testrun">
    <div class="container">
        <span class="badge badge-primary">Test Cases List</span>
    </div>
    <div class="container">
    <c:choose>
        <c:when test="${empty testRun.testCasePageList}">
            <p>No Test Cases</p>
        </c:when>
        <c:otherwise>
            <form:form commandName="testRun">
                <div class="container">
                  <table class="table table-striped table-hover table-condensed" >
                    <thead>
                        <tr>
                             <th></th>
                             <th>Test Case Id</th>
                             <th>Test Case Author</th>
                             <th>Test Case Name</th>
                             <th>Test Case Type</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach varStatus="index" items="${testRun.testCasePageList.pageList}">
                            <tr class="form-row form-group col">
                                <td>
                                    <form:checkbox path="testCasePageList.pageList[${index.count - 1}].checked" value="true"/>
                                </td>
                                <td>
                                    <tags:textInput path="testCasePageList.pageList[${index.count - 1}].id" readonly="${true}" />
                                </td>
                                <td>
                                    <tags:textInput path="testCasePageList.pageList[${index.count - 1}].author.name" readonly="${true}" />
                                </td>
                                <td>
                                    <tags:textInput path="testCasePageList.pageList[${index.count - 1}].testName" readonly="${true}" />
                                </td>
                                <td>
                                    <tags:textInput path="testCasePageList.pageList[${index.count - 1}].testType" readonly="${true}" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                  </table>
                </div>
                <div class="w-25 p-3" style="background-color: #eee;">
                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                        <div class="btn-group btn-group-sm" role="group" aria-label="First group">
                            <c:choose>
                                <c:when test="${testRun.testCasePageList.firstPage}">
                                    <button type="submit" class="btn btn-info" name="add" disabled >Prev</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-info" name="add" value="prev">Prev</button>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach begin="1" end="${testRun.testCasePageList.pageCount}" step="1"  varStatus="tagStatus">
                                <c:choose>
                                    <c:when test="${(testRun.testCasePageList.page + 1) == tagStatus.index}">
                                        <button type="submit" class="btn btn-info" name="add" disabled >${tagStatus.index}</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn btn-info" name="add" value="${tagStatus.index}">${tagStatus.index}</button>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${testRun.testCasePageList.lastPage}">
                                    <button type="submit" class="btn btn-info" name="add" disabled >Next</button>
                                </c:when>
                                <c:otherwise>
                                    <form:hidden path="navPage" value="next"/>
                                    <button type="submit" class="btn btn-info" name="add" value="next">Next</button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="w-25 p-3" style="background-color: #eee;">
                    <button type="submit" class="btn btn-primary" name="save" value="save">Add Test Cases To Run</button>
                </div>
            </form:form>
        </c:otherwise>
    </c:choose>
    </div>
</tags:page>