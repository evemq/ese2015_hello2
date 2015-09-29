<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<c:if test="${user!=null}">
<h1>Your Profile!</h1>
        <legend>Your Information</legend>

        <table>
        <tbody>
        <tr><td>Id</td><td><c:out value="${user.id}" /></td></tr>
        <tr><td>First Name</td><td><c:out value="${user.firstName}"></c:out></td></tr>
        <tr><td>Last Name</td><td><c:out value="${user.lastName}"></c:out></td></tr>
        <tr><td>Team</td><td>
        <c:choose>
        <c:when test="${user.team!=null}">
        <c:out value="${user.team.teamName}"></c:out>
        </c:when>
        <c:otherwise>
        n/a
        </c:otherwise>
        </c:choose>
        </td></tr>
        </tbody>
        </table>
 </c:if>

	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>

<c:import url="template/footer.jsp" />