package com.example.demo.service;

import com.example.demo.model.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario buscarPorNome(String nome);

    Usuario buscarPorId(int id);

    List<Usuario> buscarTodos();

    Usuario cadastrar(Usuario usuario);

    Usuario atualizar(Usuario usuario);

    void remover(Integer id);
}
