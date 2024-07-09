package com.example.services;

import com.example.models.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario getUserById(int idUsuario);
    Usuario getUserByUsername(String username);
    List<Usuario> getAllUsers();
    void createUser(Usuario usuario);
    void updateUser(Usuario usuario);
    void deleteUser(int idUsuario);
}
