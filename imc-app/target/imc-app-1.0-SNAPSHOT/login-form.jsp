<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="login" method="post">
        <label>Username:</label>
        <input type="text" name="username"><br><br>
        
        <label>Password:</label>
        <input type="password" name="password"><br><br>
        
        <input type="submit" name="submit" value="Entrar">
        <input type="submit" name="submit" value="Registrarme">
    </form>
</body>
</html>
