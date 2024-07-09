<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calculate IMC</title>
    <script>
        function calculateIMC() {
            var weight = document.getElementById("weight").value;
            var height = 170; // Dummy height for demo purposes
            var imc = weight / ((height / 100) * (height / 100));
            var currentDate = new Date().toLocaleString(); // Dummy created date

            // Update table with the new record
            var tableBody = document.getElementById("imcRecords");
            var newRow = tableBody.insertRow();
            newRow.innerHTML = "<td>" + weight + "</td><td>" + height + "</td><td>" + imc.toFixed(2) + "</td><td>" + currentDate + "</td>";
        }
    </script>
    
    <%
        // Define IMCRecord class
        class IMCRecord {
            private int weight;
            private int height;
            private double imc;
            private String createdOn;

            public IMCRecord(int weight, int height, double imc, String createdOn) {
                this.weight = weight;
                this.height = height;
                this.imc = imc;
                this.createdOn = createdOn;
            }

            public int getWeight() {
                return weight;
            }

            public int getHeight() {
                return height;
            }

            public double getImc() {
                return imc;
            }

            public String getCreatedOn() {
                return createdOn;
            }
        }
    %>
</head>
<body>
    <h1>Calculate IMC</h1>
    
    <form onsubmit="calculateIMC(); return false;">
        <label for="weight">Weight (in kg):</label>
        <input type="number" id="weight" name="weight" required>
        <button type="submit">Calculate IMC</button>
    </form>

    <br>
    
    <!-- Dummy IMC records table -->
    <table border="1">
        <thead>
            <tr>
                <th>Peso</th>
                <th>Estatura</th>
                <th>IMC</th>
                <th>Creado en</th>
            </tr>
        </thead>
        <tbody id="imcRecords">
            <!-- Dummy data, replace with dynamic data if needed -->
        </tbody>
    </table>
</body>
</html>
