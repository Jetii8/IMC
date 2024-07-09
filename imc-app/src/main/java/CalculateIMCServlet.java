import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // Import HttpSession

@WebServlet("/calculate-imc")
public class CalculateIMCServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the form
        int userId = Integer.parseInt(request.getParameter("userId"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        int height = Integer.parseInt(request.getParameter("height"));

        // Calculate IMC (BMI)
        double heightInMeters = height / 100.0; // Convert height to meters
        double imc = weight / (heightInMeters * heightInMeters);

        // Create a new IMC record (for demonstration, storing in session)
        IMCRecord newRecord = new IMCRecord(weight, height, imc);

        // Store the record in session
        HttpSession session = request.getSession();
        List<IMCRecord> imcRecords = (List<IMCRecord>) session.getAttribute("imcRecords");
        if (imcRecords == null) {
            imcRecords = new ArrayList<>();
        }
        imcRecords.add(newRecord);
        session.setAttribute("imcRecords", imcRecords);

        // Redirect back to calculate-imc.jsp with success message
        response.sendRedirect(request.getContextPath() + "/calculate-imc.jsp?success=true");
    }
}
