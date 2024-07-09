package com.example.services;

import com.example.models.Usuario;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private static final String JDBC_USER = "your_db_username";
    private static final String JDBC_PASSWORD = "your_db_password";

    // SQL Queries
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM usuario WHERE id_usuario = ?";
    private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM usuario WHERE username = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario (username, nombre, apellido, edad, sexo, estatura, contrasena, creado_en) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET username = ?, nombre = ?, apellido = ?, edad = ?, sexo = ?, estatura = ?, contrasena = ?, actualizado_en = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

    @Override
    public Usuario getUserById(int idUsuario) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapUsuario(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Usuario getUserByUsername(String username) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_USERNAME)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapUsuario(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Usuario> getAllUsers() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL)) {
            while (rs.next()) {
                usuarios.add(mapUsuario(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public void createUser(Usuario usuario) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setInt(4, usuario.getEdad());
            stmt.setString(5, usuario.getSexo());
            stmt.setInt(6, usuario.getEstatura());
            stmt.setString(7, usuario.getContrasena());
            stmt.setTimestamp(8, Timestamp.valueOf(usuario.getCreadoEn()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateUser(Usuario usuario) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setInt(4, usuario.getEdad());
            stmt.setString(5, usuario.getSexo());
            stmt.setInt(6, usuario.getEstatura());
            stmt.setString(7, usuario.getContrasena());
            stmt.setTimestamp(8, Timestamp.valueOf(usuario.getActualizadoEn()));
            stmt.setInt(9, usuario.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int idUsuario) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Usuario mapUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("username"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getInt("edad"),
                rs.getString("sexo"),
                rs.getInt("estatura"),
                rs.getString("contrasena"),
                rs.getTimestamp("creado_en").toLocalDateTime(),
                rs.getTimestamp("actualizado_en") != null ? rs.getTimestamp("actualizado_en").toLocalDateTime() : null
        );
    }
}
