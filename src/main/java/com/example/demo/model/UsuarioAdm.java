    package com.example.demo.model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Entity
    @DiscriminatorValue("ADM")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class UsuarioAdm extends Usuario {

        private String permissoesEspeciais;
    }

