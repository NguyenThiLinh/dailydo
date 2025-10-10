<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: #f8f9fa;
        }

        .login-box {
            max-width: 420px;
            margin: 80px auto;
            padding: 40px;
            background: white;
            border-radius: 16px;
            box-shadow: 0 0 10px rgba(0,0,0,0.05);
        }

        .login-logo {
            text-align: center;
            margin-bottom: 24px;
        }

        .login-logo img {
            width: 100px;
        }
    </style>
</head>
<body>

<div class="login-box">
    <div class="login-logo">
        <img src="/images/logo.png" alt="Logo">
    </div>

    <!-- Notifications -->
    <c:if test="${param.error != null}">
        <div class="alert alert-danger">Invalid username or password</div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div class="alert alert-success">You have been logged out.</div>
    </c:if>

    <!-- Login Form -->
    <form action="<c:url value='/login' />" method="post">
        <div class="mb-3">
            <label for="email" class="form-label">Username</label>
            <input type="text" class="form-control" id="email" name="email" required>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label d-flex justify-content-between">
                <span>Password</span>
            </label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>

        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </form>

    <p class="mt-3 text-center">Don't have an account? <a href="/register" class="text-decoration-none">Register</a></p>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
