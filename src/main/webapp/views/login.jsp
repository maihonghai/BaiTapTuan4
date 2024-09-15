<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f2f5;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    .form-container {
        background-color: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 400px;
    }

    .form-container h2 {
        text-align: center;
        margin-bottom: 20px;
    }

    .form-container label {
        font-size: 14px;
        margin-bottom: 5px;
        display: block;
    }

    .form-container input[type="text"],
    .form-container input[type="password"] {
        width: 100%;
        padding: 12px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }

    .form-container button[type="submit"] {
        width: 100%;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 10px 0;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .form-container button[type="submit"]:hover {
        background-color: #45a049;
    }

    .form-container .cancelbtn {
        width: 100%;
        background-color: #f44336;
        color: white;
        padding: 14px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .form-container .cancelbtn:hover {
        background-color: #e53935;
    }

    .form-container .psw {
        text-align: right;
        margin-top: 10px;
    }

    .form-container .psw a {
        color: #4CAF50;
        text-decoration: none;
    }

    .form-container .psw a:hover {
        text-decoration: underline;
    }

    .alert {
        color: red;
        text-align: center;
        margin-bottom: 15px;
    }

</style>
</head>
<body>
    <div class="form-container">
        <h2>Login</h2>

        <c:if test="${alert != null}">
            <div class="alert">${alert}</div>
        </c:if>

        <form action="/ltweb/login" method="post">
            <label for="uname"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="uname" required>

            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="psw" required>

            <button type="submit">Login</button>

            <label>
                <input type="checkbox" checked="checked" name="remember"> Remember me
            </label>

            <div class="psw">
                Forgot <a href="/ltweb/forgot-password">password?</a>
            </div>
            <div class="register">
                 <a href="/ltweb/register">Register</a>
            </div>

        </form>
    </div>
</body>
</html>
