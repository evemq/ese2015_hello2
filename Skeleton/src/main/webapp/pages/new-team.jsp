<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />


<h1>Create a new team</h1>


<form:form method="post" modelAttribute="teamForm" action="createTeam" id="teamForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>Enter A Teamname</legend>

        <c:set var="teamNameErrors"><form:errors path="teamName"/></c:set>
        <div class="control-group<c:if test="${not empty emailErrors}"> error</c:if>">
            <label class="control-label" for="field-teamName">Teamname</label>

            <div class="controls">
                <form:input path="teamName" id="field-teamName" tabindex="1" maxlength="45" placeholder="Teamname"/>
                <form:errors path="teamName" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <br>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary button">Sign up</button>
            <button type="button" class="btn button">Cancel</button>
        </div>
    </fieldset>
</form:form>

	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>
<c:import url="template/footer.jsp" />