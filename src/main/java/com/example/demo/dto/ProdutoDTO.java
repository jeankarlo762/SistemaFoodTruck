package com.example.demo.dto;

import com.example.demo.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Integer id;

    @NotBlank(message = "Nome do produto não pode ser vazio")
    private String nome;

    @NotBlank(message = "Descrição do produto não pode ser vazia")
    private String descricao;

    @NotNull(message = "Preço do produto é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    private boolean disponivel;


    public static ProdutoDTO fromEntity(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.isDisponivel()
        );
    }

    public Produto toEntity() {
        Produto produto = new com.example.demo.model.Produto();
        produto.setId(this.id);
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPreco(this.preco);
        produto.setDisponivel(this.disponivel);
        return produto;
    }
}
