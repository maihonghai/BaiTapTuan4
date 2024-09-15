<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .container {
        background-color: white;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        text-align: center;
        max-width: 400px;
    }
    h1 {
        color: #333;
    }
    .button {
        background-color: #17a2b8;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        margin-top: 20px;
    }
    .button:hover {
        background-color: #138496;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>Welcome, User</h1>
        <p>Access your profile, manage settings, and view your dashboard.</p>
        <a href="/ltweb/views/logout.jsp">Logout</a>
    </div>
</body>
</html>
