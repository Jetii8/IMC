package com.example.services;

import com.example.models.IMCRecord;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IMCServiceImpl implements IMCService {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private static final String JDBC_USER = "your_db_username";
    private static final String JDBC_PASSWORD = "your_db_password";

    // SQL Queries
    private static final String SQL_INSERT = "INSERT INTO imc (imc, creado_en, usuario_id_usuario) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_BY_USER_ID = "SELECT * FROM imc WHERE usuario_id_usuario = ?";

    @Override
    public void addIMCRecord(IMCRecord imcRecord) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
            stmt.setDouble(1, imcRecord.getImc());
            stmt.setTimestamp(2, Timestamp.valueOf(imcRecord.getCreadoEn()));
            stmt.setInt(3, imcRecord.getUsuarioIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<IMCRecord> getIMCRecordsByUserId(int userId) {
        List<IMCRecord> records = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_USER_ID)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                records.add(mapIMCRecord(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return records;
    }

    private IMCRecord mapIMCRecord(ResultSet rs) throws SQLException {
        return new IMCRecord(
                rs.getInt("id_imc"),
                rs.getDouble("imc"),
                rs.getTimestamp("creado_en").toLocalDateTime(),
                rs.getInt("usuario_id_usuario")
        );
    }
}
