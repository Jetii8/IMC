package com.example.controllers;

import com.example.models.IMCRecord;
import com.example.models.Usuario;
import com.example.services.IMCService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/imc/*")
public class IMCController extends HttpServlet {

    private final IMCService imcService = new IMCService(); // Inject your IMCService implementation here

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/add":
                addIMCRecord(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/fetch":
                fetchIMCRecords(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void addIMCRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario loggedUser = (Usuario) request.getSession().getAttribute("loggedUser");
        int userId = loggedUser.getIdUsuario();
        int peso = Integer.parseInt(request.getParameter("peso"));
        int estatura = loggedUser.getEstatura();
        double imc = calcularIMC(peso, estatura);

        IMCRecord imcRecord = new IMCRecord(peso, estatura, imc, LocalDateTime.now(), userId);
        imcService.addIMCRecord(imcRecord);

        // Redirect to IMC calculation page or display success message
        response.sendRedirect(request.getContextPath() + "/calculate-imc.jsp");
    }

    private void fetchIMCRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario loggedUser = (Usuario) request.getSession().getAttribute("loggedUser");
        int userId = loggedUser.getIdUsuario();
        List<IMCRecord> imcRecords = imcService.getIMCRecordsByUserId(userId);

        // Store IMC records in request scope to be displayed in JSP
        request.setAttribute("imcRecords", imcRecords);
        request.getRequestDispatcher("/view-imc.jsp").forward(request, response);
    }

    private double calcularIMC(int peso, int estatura) {
        // Calculate IMC using weight (peso) and height (estatura)
        // Example calculation:
        double alturaMetros = estatura / 100.0; // Convert height to meters
        return peso / (alturaMetros * alturaMetros); // BMI formula
    }
}
