package com.example.demo.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
@
public class Usuario {

    @Id
    private Long id;

    private String nomeUsuario;

    private String email;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Usuario(Long id, String nomeUsuario, String email) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
    }



}
