<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <h2>Admin Dashboard</h2>
    <ul>
        <li><a href="/admin/dashboard">Home</a></li>
        <li><a href="/admin/users">User</a></li>
        <li><a href="/admin/category/list">Category</a></li>
        <li><a href="/admin/product/list">Paint Product</a></li>
        <li><a href="/admin/order/list">Booking</a></li>
    </ul>
</div>

<div class="main-content">

    <jsp:include page="/WEB-INF/views/${content}" />
</div>

<div class="footer">
    <p>&copy; 2025 Admin Dashboard. All rights reserved.</p>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
