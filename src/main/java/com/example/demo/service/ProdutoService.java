package com.example.demo.service;

import com.example.demo.model.Produto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProdutoService {

    List<Produto> buscarTodos();

    Produto buscaPorNome(String nome);

    Produto buscaPorId(int id);

    Produto cadastrar(Produto produto);

    void remover(Produto produto);

    Produto atualizar(Produto produto);

}
