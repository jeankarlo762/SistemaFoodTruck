package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {

    Optional<Usuario> findByNome(String nome);

    Optional<Usuario>findById(Long id);

    boolean existsUsuarioById(Long id);

}
