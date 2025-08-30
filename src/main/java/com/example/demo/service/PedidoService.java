package com.example.demo.service;

import com.example.demo.model.Pedido;
import java.util.List;

public interface PedidoService {

    Pedido salvar(Pedido pedido);

    Pedido buscarPorId(Long id);

    List<Pedido> listarTodos();

    Pedido atualizar(Long id, Pedido pedido);

    void deletar(Long id);
}
