package com.example.demo.service.serviceImpl;

import com.example.demo.exception.RecursoNaoEncontradoException;
import com.example.demo.exception.ValidacaoException;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com ID " + id + " não encontrado."));
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorNome(String nome) {
        return usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com nome '" + nome + "' não encontrado."));
    }

    @Override
    public Usuario buscarPorId(int id) {
        return null;
    }

    @Override
    @Transactional
    public void cadastrar(Usuario usuario) {
        if (usuario.getId() != null) {
            throw new ValidacaoException("ID deve ser nulo para cadastrar um novo usuário.");
        }
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void atualizar(Usuario usuario) {
        if (usuario.getId() == null) {
            throw new ValidacaoException("ID do usuário não pode ser nulo para atualização.");
        }

        Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com ID " + usuario.getId() + " não encontrado."));

        usuarioExistente.setNome(usuario.getNome());
        // Atualize outros campos aqui se houver
        usuarioRepository.save(usuarioExistente);
    }

    @Override
    @Transactional
    public void remover(Usuario usuario) {
        if (usuario == null || usuario.getId() == null) {
            throw new ValidacaoException("Usuário ou ID não pode ser nulo para remoção.");
        }

        if (!usuarioRepository.existsUsuarioById(usuario.getId())) {
            throw new RecursoNaoEncontradoException("Usuário com ID " + usuario.getId() + " não encontrado.");
        }
        usuarioRepository.delete(usuario);
    }
}
