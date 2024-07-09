
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to JSP form for registration
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String sexo = request.getParameter("sexo");
        int estatura = Integer.parseInt(request.getParameter("estatura"));
        String contrasena = request.getParameter("contrasena");

        // For simplicity, you can validate and process the input here
        // In a real application, you should validate inputs and store them securely

        // Set attributes to pass back to JSP
        request.setAttribute("username", username);
        request.setAttribute("nombre", nombre);
        request.setAttribute("apellido", apellido);
        request.setAttribute("edad", edad);
        request.setAttribute("sexo", sexo);
        request.setAttribute("estatura", estatura);

        // Forward to a confirmation page or another appropriate page
        request.getRequestDispatcher("calculate-imc.jsp").forward(request, response);
    }
}
