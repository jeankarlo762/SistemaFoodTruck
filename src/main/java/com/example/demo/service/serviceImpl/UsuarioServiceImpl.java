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
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // 🔹 Buscar todos os usuários
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    // 🔹 Buscar usuário por ID
    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorId(int id) {
        validarId(id);
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com ID " + id + " não encontrado."));
    }

    // 🔹 Buscar usuário por nome
    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new ValidacaoException("Nome não pode ser nulo ou vazio.");
        }
        return usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com nome '" + nome + "' não encontrado."));
    }

    // 🔹 Cadastrar novo usuário
    @Override
    public Usuario cadastrar(Usuario usuario) {
        if (usuario == null) {
            throw new ValidacaoException("Usuário não pode ser nulo.");
        }
        if (usuario.getId() != null) {
            throw new ValidacaoException("ID deve ser nulo ao cadastrar um novo usuário.");
        }
        validarCamposObrigatorios(usuario);
        return usuarioRepository.save(usuario);
    }

    // 🔹 Atualizar usuário existente
    @Override
    public Usuario atualizar(Usuario usuario) {
        if (usuario == null) {
            throw new ValidacaoException("Usuário não pode ser nulo.");
        }
        validarId(usuario.getId());
        validarCamposObrigatorios(usuario);

        Usuario usuarioExistente = buscarPorId(usuario.getId());
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setNomeUsuario(usuario.getNomeUsuario());
        usuarioExistente.setEmail(usuario.getEmail());

        return usuarioRepository.save(usuarioExistente);
    }

    // 🔹 Remover usuário por ID
    @Override
    public void remover(Integer id) {
        validarId(id);
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário com ID " + id + " não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }

    // 🔹 Helpers privados
    private void validarId(Integer id) {
        if (id == null || id <= 0) {
            throw new ValidacaoException("ID inválido. Deve ser maior que zero e não nulo.");
        }
    }

    private void validarCamposObrigatorios(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new ValidacaoException("Nome do usuário é obrigatório.");
        }
        if (usuario.getNomeUsuario() == null || usuario.getNomeUsuario().isBlank()) {
            throw new ValidacaoException("Nome de usuário é obrigatório.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new ValidacaoException("Email do usuário é obrigatório.");
        }
    }
}
