    package com.example.demo.model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.List;

    @Entity
    @Table(name = "usuario")
    @Inheritance(strategy = InheritanceType.JOINED)  // define herança
    @DiscriminatorColumn(name = "tipo_usuario")     // coluna que identifica o tipo
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "nome", nullable = false)
        private String nome;

        @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Pedido> pedidos;
    }
