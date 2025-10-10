<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detail User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
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

    <a href="/users" class="btn btn-secondary mt-3">Back</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
