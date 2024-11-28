package io.github.rodrigohirokii.produtosapi.service;

import io.github.rodrigohirokii.produtosapi.exceptions.RegistroDuplicadoExceptions;
import io.github.rodrigohirokii.produtosapi.model.Produto;
import io.github.rodrigohirokii.produtosapi.repository.ProdutosRepository;
import io.github.rodrigohirokii.produtosapi.validator.ProdutoValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutosService {

    private final ProdutoValidator produtoValidator;
    private final ProdutosRepository produtosRepository;

    public ProdutosService(ProdutoValidator produtoValidator, ProdutosRepository produtosRepository) {
        this.produtoValidator = produtoValidator;
        this.produtosRepository = produtosRepository;
    }

    public Produto salvarProduto(Produto produto) {
        produtoValidator.validar(produto);
        return produtosRepository.save(produto);
    }
}
