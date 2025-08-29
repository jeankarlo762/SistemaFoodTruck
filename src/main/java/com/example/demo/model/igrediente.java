package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class igrediente extends Produto{

    @Id
    private Long id;

    private Double quantidadeEstoque;
            

}
