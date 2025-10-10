<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="/css/style.css">
<div>
    <h1>${category.id == null ? 'Create' : 'Edit'} product categories</h1>

    <form:form method="post" action="/admin/category/save" modelAttribute="category">
        <form:hidden path="id"/>

        <div class="form-group">
            <label for="name">Name</label>
            <form:input path="name" id="name" required="true"/>
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <form:textarea path="description" id="description" rows="4"/>
        </div>

        <div class="form-group">
            <label>Status</label>
            <form:radiobutton path="status" value="1"/>
            <form:radiobutton path="status" value="0"/>

        </div>

        <div class="form-group">
            <button type="submit">Submit</button>
        </div>
    </form:form>
</div>
</body>
</html>
