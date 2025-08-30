package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)  // herança se houver subclasses
@DiscriminatorColumn(name = "tipo_usuario")     // coluna que identifica o tipo
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;  // um usuário pode ter vários pedidos

    // Construtor adicional
    public Usuario(Integer id, String nomeUsuario, String email) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
    }
}
