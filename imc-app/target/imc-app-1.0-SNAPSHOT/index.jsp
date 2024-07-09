<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Bienvenido a la calculadora IMC</h2>
    <img src="https://th.bing.com/th/id/OIP.QEUJ-0XNV346An-jDOdPtAHaIP?rs=1&pid=ImgDetMain" alt="weight loss" width="50" height="50">
    <h2>Login</h2>
    <form action="login" method="post">
        <label>Username:</label>
        <input type="text" name="username" required><br><br>
        
        <label>Password:</label>
        <input type="password" name="password" required><br><br>
        
        <input type="submit" name="submit" value="Entrar">
        <input type="submit" name="submit" value="Registrarme">
    </form>
</body>
</html>

