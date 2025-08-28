package com.example.demo.service.serviceImpl;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }



    @Override
    public Usuario buscarPorNome(String nome) {
        return null;
    }

    @Override
    public Usuario buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Usuario> buscarTodos() {
        return List.of();
    }

    @Override
    public void cadastrar(Usuario usuario) {

    }

    @Override
    public void atualizar(Usuario usuario) {

    }

    @Override
    public void remover(Usuario usuario) {

    }
}
