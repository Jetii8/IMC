package com.example.controllers;

import com.example.models.Usuario;
import com.example.services.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/usuario/*")
public class UserController extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService(); // Inject your UsuarioService implementation here

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/register":
                registerUser(request, response);
                break;
            case "/login":
                loginUser(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String sexo = request.getParameter("sexo");
        int estatura = Integer.parseInt(request.getParameter("estatura"));
        String contrasena = request.getParameter("contrasena");

        Usuario usuario = new Usuario(username, nombre, apellido, edad, sexo, estatura, contrasena);
        usuarioService.createUser(usuario);

        // Redirect to login page or display success message
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String contrasena = request.getParameter("contrasena");

        Usuario usuario = usuarioService.getUserByUsername(username);

        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            // Login successful, store user in session
            request.getSession().setAttribute("loggedUser", usuario);
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            // Login failed, redirect back to login page with error message
            request.setAttribute("errorMessage", "Usuario o contrasena incorrectos");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
