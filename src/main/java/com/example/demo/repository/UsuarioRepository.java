package com.example.demo.repository;

import com.example.demo.model.Produto;
import com.example.demo.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {

    Optional<Usuario> buscarPorNome(String nome);
}
