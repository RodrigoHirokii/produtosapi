package io.github.rodrigohirokii.produtosapi.validator;

import io.github.rodrigohirokii.produtosapi.exceptions.RegistroDuplicadoExceptions;
import io.github.rodrigohirokii.produtosapi.model.Produto;
import io.github.rodrigohirokii.produtosapi.repository.ProdutosRepository;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProdutoValidator {

    private final ProdutosRepository produtosRepository;

    public ProdutoValidator(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }


    public void validar(Produto produto) {
        if (existeProdutoCadastrado(produto)) {
            throw new RegistroDuplicadoExceptions("Produto Já Cadastrado");
        }

    }


    public boolean existeProdutoCadastrado(Produto produto) {
        Optional<Produto> produtoEncontrado = produtosRepository.findByNomeAndDescricaoAndPreco(produto.getNome(), produto.getDescricao(), produto.getPreco());

        if (produto.getId() == null) {
            return produtoEncontrado.isPresent();
        }

        return !produto.getId().equals(produtoEncontrado.get().getId()) && produtoEncontrado.isPresent();

    }
}
