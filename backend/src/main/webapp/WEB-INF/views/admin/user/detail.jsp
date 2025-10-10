<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <h1 class="mb-4">Detail</h1>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" style="color:red;">
                ${errorMessage}
        </div>
    </c:if>

    <c:if test="${not empty user}">
        <div class="card">
            <div class="card-body">
                <p><strong>ID:</strong> <span>${user.id}</span></p>
                <p><strong>Name:</strong> <span>${user.name}</span></p>
                <p><strong>Email:</strong> <span>${user.email}</span></p>
            </div>
        </div>
    </c:if>

    <a href="/admin/users" class="btn btn-secondary mt-3">Back</a>
</div>

