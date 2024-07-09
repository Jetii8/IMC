<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
</head>
<body>
    <h2>User Registration Form</h2>
    <form action="register" method="post">
        <label>Username:</label>
        <input type="text" name="username" required><br><br>
        
        <label>Nombre:</label>
        <input type="text" name="nombre" required><br><br>
        
        <label>Apellido:</label>
        <input type="text" name="apellido" required><br><br>
        
        <label>Edad:</label>
        <input type="number" name="edad" required><br><br>
        
        <label>Sexo:</label>
        <input type="text" name="sexo" required><br><br>
        
        <label>Estatura:</label>
        <input type="number" name="estatura" required><br><br>
        
        <label>Contraseña:</label>
        <input type="password" name="contrasena" required><br><br>
        
        <input type="submit" value="Register">
    </form>
</body>
</html>
