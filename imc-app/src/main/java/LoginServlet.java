import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("submit");

        if ("Entrar".equals(action)) {
            // Process login logic (authentication)
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // For demonstration, simply redirect to a success page if login is successful
            if (isValidUser(username, password)) {
                response.sendRedirect(request.getContextPath() + "/calculate-imc.jsp");
            } else {
                // Redirect to login page with error message
                response.sendRedirect(request.getContextPath() + "/index.jsp?error=invalid");
            }
        } else if ("Registrarme".equals(action)) {
            // Redirect to registration page (or handle registration logic)
            response.sendRedirect(request.getContextPath() + "/register.jsp");
        } else {
            // Handle other cases if needed
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    private boolean isValidUser(String username, String password) {
        // Dummy validation logic (replace with your authentication mechanism)
        return username.equals("jessica1") && password.equals("123");
    }
}
