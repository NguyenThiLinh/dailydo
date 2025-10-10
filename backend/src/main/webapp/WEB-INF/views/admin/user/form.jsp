<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <h1>
        <c:choose>
            <c:when test="${user.id == null}">Create User</c:when>
            <c:otherwise>Edit User</c:otherwise>
        </c:choose>
    </h1>

    <form:form method="post" modelAttribute="user"
               action="${user.id == null ? '/admin/users/save' : '/admin/users/update'}">
        <form:hidden path="id"/>

        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <form:input path="name" id="name" cssClass="form-control"/>
            <form:errors path="name" cssClass="text-danger small"/>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <form:input path="email" id="email" cssClass="form-control"/>
            <form:errors path="email" cssClass="text-danger small"/>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <form:password path="password" id="password" cssClass="form-control"
                           placeholder="${user.id == null ? '••••••••' : 'Leave blank to keep current password'}"/>
            <form:errors path="password" cssClass="text-danger small"/>
        </div>

        <button type="submit" class="btn btn-primary">
            <c:choose>
                <c:when test="${user.id == null}">Create</c:when>
                <c:otherwise>Update</c:otherwise>
            </c:choose>
        </button>

        <a href="/admin/users" class="btn btn-secondary">Back</a>
    </form:form>

</div>
