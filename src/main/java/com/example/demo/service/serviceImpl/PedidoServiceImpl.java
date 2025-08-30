package com.example.demo.service.serviceImpl;

import com.example.demo.model.Pedido;
import com.example.demo.service.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PedidoServiceImpl implements PedidoService {

    @Override
    public Pedido salvar(Pedido pedido) {
        return null;
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Pedido> listarTodos() {
        return List.of();
    }

    @Override
    public Pedido atualizar(Long id, Pedido pedido) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
