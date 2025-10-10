<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome Home</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f8ff;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }
        .container {
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            padding: 40px;
        }
        h1 {
            color: #4CAF50;
            font-size: 2.5em;
            margin-bottom: 20px;
        }
        p {
            font-size: 1.2em;
            color: #555;
            margin-bottom: 30px;
        }
        a {
            color: #007BFF;
            font-size: 1.2em;
            text-decoration: none;
            border: 2px solid #007BFF;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
        }
        a:hover {
            background-color: #007BFF;
            color: white;
        }
        .footer {
            margin-top: 30px;
            font-size: 0.9em;
            color: #888;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome Home!</h1>
    <p>It's great to have you back. Explore, discover, and make the most out of your experience here.</p>
    <a href="<c:url value='/logout' />">Logout</a>
    <div class="footer">
        <p>&copy; 2025 Your Website | All Rights Reserved</p>
    </div>
</div>
</body>
</html>
