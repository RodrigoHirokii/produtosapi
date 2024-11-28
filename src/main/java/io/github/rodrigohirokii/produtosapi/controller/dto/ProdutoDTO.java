package io.github.rodrigohirokii.produtosapi.controller.dto;

import io.github.rodrigohirokii.produtosapi.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoDTO(@NotBlank(message = "O nome do produto é obrigatório")
                         String nome,
                         @NotBlank
                         String descricao,

                         @Positive(message = "O preço deve ser maior que zero")
                         @NotNull
                         BigDecimal preco) {


    public Produto mapearParaProduto() {
        Produto produto = new Produto();
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPreco(this.preco);

        return produto;
    }
}
