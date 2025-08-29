package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UsuarioAdm extends Usuario{

    @Id
    private Long id;



}
